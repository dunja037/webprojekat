package com.example.demo.service;

import com.example.demo.dto.PorudzbinaDto;
import com.example.demo.entity.*;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.MenadzerRepository;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenadzerService {
    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;


    public String addmanager(Korisnik korisnik){
        Menadzer menadzer = new Menadzer(korisnik);
        menadzerRepository.save(menadzer);
        return "Uspesno ste sacuvali  menadzera.";
    }

    public Set<Porudzbina> getPorudzbineZaRestoran(Menadzer menadzer){
        Set<Porudzbina> porudzbine = porudzbinaRepository.getByRestoran(menadzer.getRestoran());
        return porudzbine;
    }

    public Restoran findrestoran(Korisnik korisnik){
        Menadzer temp = menadzerRepository.getById(korisnik.getIdKorisnika());
        return temp.getRestoran();
    }

    public Set<PorudzbinaDto> findporudzbine(Korisnik korisnik){
        Menadzer temp = menadzerRepository.getById(korisnik.getIdKorisnika());
        Set<Porudzbina> porudzbine = porudzbinaRepository.getByRestoran(temp.getRestoran());
        Set<PorudzbinaDto> porudzbineDto = new HashSet<>();
        PorudzbinaDto tempp;
        for(Porudzbina p : porudzbine){
            tempp = new PorudzbinaDto(p);
            porudzbineDto.add(tempp);
        }
        return porudzbineDto;
    }

}
