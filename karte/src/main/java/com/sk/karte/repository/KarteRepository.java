package com.sk.karte.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.karte.model.Karta;

@Repository
public interface KarteRepository extends JpaRepository<Karta, Long> {

    Optional<Karta> findLetById(Long id);
    Optional<Karta> findByIdUseraAndIdLeta(Long userId, Long letId);
}
