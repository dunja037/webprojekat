package com.example.demo.service;


import com.example.demo.dto.KomentarDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RestoranService {
    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

    @Autowired
    KomentarService komentarService;

    @Autowired
    ArtikalService artikalService;

    @Autowired
    LokacijaService lokacijaService;

    @Autowired
    MenadzerService menadzerService;


    public List<RestoranDto> restorani(){
        List<Restoran> restorani = restoranRepository.findAll();
        List<RestoranDto> restoraniDto = new ArrayList<>();
        RestoranDto temp;
        for(Restoran restoran : restorani){
            temp = new RestoranDto(restoran);
            restoraniDto.add(temp);
        }
        return restoraniDto;
    }

    public List<RestoranDto> restorani_tip(String tip_restorana){
        List<Restoran> restorani = restoranRepository.findByTip(tip_restorana);
        List<RestoranDto> restoranDtos = new ArrayList<>();
        RestoranDto temp;
        for(Restoran r : restorani) {
            temp = new RestoranDto(r);
            restoranDtos.add(temp);
        }
        return restoranDtos;
    }

    public Set<KomentarDto> komentari(Restoran restoran){
        Set<KomentarDto> komentari = komentarService.dtoKomentariPoRestoranu(restoran);
        return komentari;
    }

    public Set<Artikal> artikli(Restoran restoran){
        return artikalService.dtoArtikliPoRestoranu(restoran);
    }

    public float ocena(Restoran restoran)
    {
        float prosekOcena = komentarService.getProsekOcena(restoran);
        return prosekOcena;
    }


    public RestoranDto restorani_lokacija(String adresa){
        Lokacija lokacija = lokacijaService.findByAdress(adresa);
        if(lokacija == null){
            return null;
        }

        List<Restoran> restorani = restoranRepository.findAll();
        for(Restoran r : restorani){
            for(Lokacija l : r.getLokacija()){
                if(l.equals(lokacija)){
                    return new RestoranDto(r);
                }
            }
        }
        return null;
    }

    public String addArtikal(Artikal artikal, Korisnik menadzer){
        Restoran restoran = menadzerService.findrestoran(menadzer);

        if(restoran == null){
            return "Vi ne posedujete restoran";
        }
        Set<Artikal> artikli = restoran.getArtikli();
        for(Artikal a : artikli){
            if (a.getNaziv().equals(artikal.getNaziv()) &&
                    a.getKolicina() == artikal.getKolicina() &&
                    a.getTip().equals(artikal.getTip())) {

                return "Artikal vec postoji";
            }
            if(a.getIdArtikla() == artikal.getIdArtikla()){
                return "ID vec postoji";
            }
        }
        artikli.add(artikal);
        restoran.setArtikli(artikli);
        restoranRepository.save(restoran);
        return "Uspesno ste dodali artikal.";

    }



}