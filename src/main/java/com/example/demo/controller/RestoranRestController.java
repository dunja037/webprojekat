package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.RestoranRepository;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;

@RestController
public class RestoranRestController {
    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    RestoranService restoranService;


    @GetMapping("/")
    public List<RestoranDto> welcome(){
        List<RestoranDto> restorani = restoranService.restorani();
        return  restorani;
    }

    @GetMapping("/restoran/{ime}")
    public ResponseEntity<RestoranDto> restoran(@PathVariable (value = "ime") String ime){
        Restoran restoran = restoranRepository.findByNaziv(ime);
        if(restoran == null){
            return new ResponseEntity("Restoran nije pronadjen.", HttpStatus.NOT_FOUND);
        }
        RestoranDto restoranDto = new RestoranDto(restoran);
        return ResponseEntity.ok(restoranDto);

    }


    @GetMapping("/search/{ime}")
    public ResponseEntity<RestoranDto> findbyime(@PathVariable(value = "ime") String ime){
        Restoran restoran = restoranRepository.findByNaziv(ime);
        if(restoran == null){
            return new ResponseEntity("Restoran nije pronadjen.", HttpStatus.NOT_FOUND);
        }
        RestoranDto restoranDto = new RestoranDto(restoran);
        return ResponseEntity.ok(restoranDto);
    }

    @GetMapping("/search/tip/{tip}")
    public ResponseEntity<List<RestoranDto>> findbytiprestorana(@PathVariable(value = "tip") String tip){
        List<RestoranDto>restorani = restoranService.restorani_tip(tip);
        return ResponseEntity.ok(restorani);
    }

    @GetMapping("/search/lokacija")
    public ResponseEntity<RestoranDto> findbylokacija(@RequestBody String lokacija){
        RestoranDto restoranDto = restoranService.restorani_lokacija(lokacija);
        if(restoranDto == null){
            return new ResponseEntity("Nije validna lokacija", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(restoranDto);
    }

    @PostMapping("/add/artikal")
    public ResponseEntity<String> addArtikal (@RequestBody Artikal artikal, HttpSession session){
        Korisnik logovaniKorisnik = (Menadzer)session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.MENADZER) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        String response = restoranService.addArtikal(artikal, logovaniKorisnik);
        return ResponseEntity.ok(response);
    }
}
