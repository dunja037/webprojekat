package com.example.demo.service;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

    public List<KorisnikDto> uvid_u_korisnike(){
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();
        KorisnikDto temp;
        for(Korisnik k : korisnici){
            temp = new KorisnikDto(k);
            korisniciDto.add(temp);
        }
        return korisniciDto;
    }

    public void napravi_restoran(Restoran restoran){
        restoranRepository.save(restoran);
    }

    public String set_restoran_menager (String ime, String menadzer_ime){
        Menadzer menadzer = menadzerRepository.findByKorisnicko(menadzer_ime);
        Restoran restoran = restoranRepository.findByNaziv(ime);
        if(menadzer == null || restoran == null){
            return "Greska, nesto nije validan podatak. ";
        }
        menadzer.setRestoran(restoran);
        menadzerRepository.save(menadzer);
        return "Uspesno izvrseno postavljanje menadzera. ";
    }
}