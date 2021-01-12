package com.sk.letovi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.security.CheckSecurity;
import com.sk.letovi.servis.AvionServis;

@RestController
@RequestMapping("/avion")
public class AvionController {

    private AvionServis avionServis;

    public AvionController(AvionServis avionServis) {
        this.avionServis = avionServis;
    }

    @GetMapping
    public ResponseEntity<Page<AvionDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(avionServis.findAll(pageable), HttpStatus.OK);
    }
    
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AvionDto> saveAvion(@RequestHeader("Authorization") String authorization, @RequestBody @Valid KreiranjeAvionaDto kreiranjeAvionaDto) {
        return new ResponseEntity<>(avionServis.add(kreiranjeAvionaDto), HttpStatus.CREATED);
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<AvionDto> deleteAvion(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(avionServis.delete(id), HttpStatus.OK);
    }

}
