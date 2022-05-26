package com.example.demo;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	private RestoranRepository restoranRepository;

	@Override
	public void run(String... args) throws Exception{
		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnicko("ime");
		korisnik.setIme("Aleksandra");
		korisnik.setPrezime("Tomic");
		korisnik.setLozinka("sifra");

		this.korisnikRepository.save(korisnik);
		List<Korisnik> lista = this.korisnikRepository.findAll();
		for(Korisnik k : lista){
			System.out.println(k);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}