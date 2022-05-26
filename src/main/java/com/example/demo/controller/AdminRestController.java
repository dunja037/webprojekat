package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Uloga;
import com.example.demo.service.AdminService;
import com.example.demo.service.DostavljacService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/add/menadzer")
    public ResponseEntity add_menadzer(@RequestBody Korisnik korisnik, HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.ADMIN) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        String response = menadzerService.addmanager(korisnik);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add/dostavljac")
    public ResponseEntity add_dostavljac(@RequestBody Korisnik korisnik, HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.ADMIN) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        String response = dostavljacService.adddelivery(korisnik);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/korisnici")
    public ResponseEntity<List<KorisnikDto>> korisnici(HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.ADMIN) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        List<KorisnikDto> korisnici = adminService.uvid_u_korisnike();
        return new ResponseEntity<List<KorisnikDto>>(korisnici, HttpStatus.OK);
    }

    @PostMapping("/add/restoran")
    public ResponseEntity add_restoran(@RequestBody Restoran restoran, HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.ADMIN) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        adminService.napravi_restoran(restoran);
        return ResponseEntity.ok("Uspesno ste napravili restoran.");
    }

    @PostMapping("/restoran/{ime}/setmenadzer")
    public ResponseEntity add_restoran_menadzer(@PathVariable (value = "ime") String ime,@RequestBody String menadzer, HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null || logovaniKorisnik.getUloga() != Uloga.ADMIN) {
            return new ResponseEntity("Nemate dozvolu za to.", HttpStatus.FORBIDDEN);
        }
        String response = adminService.set_restoran_menager(ime, menadzer);
        return ResponseEntity.ok(response);
    }

}
