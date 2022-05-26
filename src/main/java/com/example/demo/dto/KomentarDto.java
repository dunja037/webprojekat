package com.example.demo.dto;

import com.example.demo.entity.Komentar;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;

public class KomentarDto {
    private String user;
    private String tekst;
    private int ocena;

    public KomentarDto() {
    }

    public KomentarDto(Komentar komentar) {
        Kupac kupac = komentar.getKupac();
        this.user = kupac.getKorisnicko();
        this.ocena = komentar.getOcena();
        this.tekst = komentar.getTekst();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTekst() {
        return tekst;
    }

    public void setText(String tekst) {
        this.tekst = tekst;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}