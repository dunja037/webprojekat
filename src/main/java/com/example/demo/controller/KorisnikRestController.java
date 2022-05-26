package com.example.demo.controller;

import com.example.demo.dto.KorisnikDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Pol;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Korisnik korisnik){
        String response = korisnikService.register(korisnik);
        return ResponseEntity.ok(response);
    }

    //Logging endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        //Validating data
        if(loginDto.getKorisnicko().isEmpty() || loginDto.getLozinka().isEmpty()){
            return new ResponseEntity("Netacni podaci.", HttpStatus.BAD_REQUEST);
        }

        Korisnik logovan_korisnik = korisnikService.login(loginDto.getKorisnicko(), loginDto.getLozinka());
        if(logovan_korisnik == null){
            return new ResponseEntity("Nepostojeci korisnik.", HttpStatus.NOT_FOUND);
        }
        session.setAttribute("korisnik", logovan_korisnik);
        return ResponseEntity.ok("Uspesno ste se ulogovali.");

    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session){
        Korisnik logovanKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(logovanKorisnik == null){
            return new ResponseEntity("Zabranjeno.", HttpStatus.FORBIDDEN);
        }
        session.invalidate();
        return ResponseEntity.ok("Uspesno ste se ulogovali.");
    }

    @GetMapping("/profil")
    public ResponseEntity setup(HttpSession session){
        Korisnik logovanKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovanKorisnik == null){
            return new ResponseEntity("Zabranjeno.", HttpStatus.FORBIDDEN);
        }
        KorisnikDto logovani_korisnikDTO = new KorisnikDto(logovanKorisnik);
        return ResponseEntity.ok(logovani_korisnikDTO);
    }

    @PostMapping("/profil")
    public ResponseEntity<String> setprofil(@RequestBody KorisnikDto korisnikDto, HttpSession session){
        Korisnik logovaniKorisnik = (Korisnik)session.getAttribute("korisnik");
        String response = korisnikService.setaccount(korisnikDto,logovaniKorisnik);
        return ResponseEntity.ok (response);
    }


}
