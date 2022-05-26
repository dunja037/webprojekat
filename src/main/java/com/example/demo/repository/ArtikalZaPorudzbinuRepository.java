package com.example.demo.repository;

import com.example.demo.entity.ArtikalZaPorudzbinu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalZaPorudzbinuRepository extends JpaRepository<ArtikalZaPorudzbinu, Long> {

}