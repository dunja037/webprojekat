package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import java.io.Serializable;
import javax.persistence.*;

;

@Entity
public class Porudzbina implements Serializable {
    @Id
    @GeneratedValue
    private UUID uuid;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ArtikalZaPorudzbinu> artikalzaporudzbinu = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "restoranIdLokacija")
    @JsonIgnore
    private Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date datum;

    @Column
    private float cena;

    @ManyToOne
    @JoinColumn(name = "korisnickoIme")
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    @Column
    Status status;


    public Porudzbina() {
    }

    public Porudzbina(UUID uuid, Set<ArtikalZaPorudzbinu> artikalzaporudzbinu, Restoran restoran, Date datum, float cena, Kupac kupac, Status status) {
        this.uuid = uuid;
        this.artikalzaporudzbinu = artikalzaporudzbinu;
        this.restoran = restoran;
        this.datum = datum;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<ArtikalZaPorudzbinu> getStavke() {
        return artikalzaporudzbinu;
    }

    public void setArtikalzaporudzbinu(Set<ArtikalZaPorudzbinu> artikalzaporudzbinu) {
        this.artikalzaporudzbinu = artikalzaporudzbinu;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
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

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
