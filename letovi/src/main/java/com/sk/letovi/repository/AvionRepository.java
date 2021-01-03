package com.sk.letovi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.letovi.model.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long>  {

    Optional<Avion> findAvionById(Long id);
	
}
