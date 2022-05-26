package com.example.demo.repository;

import com.example.demo.entity.Komentar;
import com.example.demo.entity.Restoran;
import com.example.demo.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
    public Set<Komentar> getByRestoran(Restoran restoran);
}
