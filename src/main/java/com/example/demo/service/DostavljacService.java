package com.example.demo.service;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.DostavljacRepository;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DostavljacService {
    @Autowired
    private DostavljacRepository dostavljacRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    public String adddelivery(Korisnik korisnik){
        Dostavljac dostavljac = new Dostavljac(korisnik);
        dostavljacRepository.save(dostavljac);
        return "Uspesno izvrseno postaljanje dostavljaca";
    }
}
