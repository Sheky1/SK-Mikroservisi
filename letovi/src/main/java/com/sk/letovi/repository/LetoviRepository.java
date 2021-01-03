package com.sk.letovi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.letovi.model.Let;

@Repository
public interface LetoviRepository extends JpaRepository<Let, Long> {

    Optional<Let> findLetById(Long id);
}
