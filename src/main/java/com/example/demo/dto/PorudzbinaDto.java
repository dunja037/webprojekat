package com.example.demo.dto;

import com.example.demo.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

public class PorudzbinaDto {

    private Set<ArtikalZaPorudzbinu> porucenirtikli = new HashSet<>();

    private RestoranDto restoran;

    private Date datum;

    private float cena;

    private KorisnikDto kupac; //Mozda napraviti neki KupacDto ako treba da se ispise tip kupca

    Status status;


    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Porudzbina p) {
        this.porucenirtikli = p.getStavke();
        this.restoran = new RestoranDto(p.getRestoran());
        this.datum = p.getDatum();
        this.cena = p.getCena();
        this.kupac = new KorisnikDto(p.getKupac());
        this.status = p.getStatus();
    }


    public Set<ArtikalZaPorudzbinu> getArtikalZaPorudzbinu() {
        return porucenirtikli;
    }

    public void setArtikalZaPorudzbinu(Set<ArtikalZaPorudzbinu> porucenirtikli) {
        this.porucenirtikli = porucenirtikli;
    }

    public RestoranDto getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = new RestoranDto(restoran);
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public KorisnikDto getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = new KorisnikDto(kupac);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
