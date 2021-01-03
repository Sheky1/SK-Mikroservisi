package com.sk.korisnicki.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.korisnicki.model.Kartica;

@Repository
public interface KarticeRepository extends JpaRepository<Kartica, Long> {
	
    Optional<Kartica> findKarticaById(Long id);
    
}
