package com.sk.karte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.karte.model.Karta;

@Repository
public interface KarteRepository extends JpaRepository<Karta, Long> {

    Optional<Karta> findKartaById(Long id);
    Optional<Karta> findByIdUseraAndIdLeta(Long userId, Long letId);
    Optional<List<Karta>> findByIdLeta(Long letId);
    Page<Karta> findByIdUsera(Pageable pageable, Long idUsera);
}
