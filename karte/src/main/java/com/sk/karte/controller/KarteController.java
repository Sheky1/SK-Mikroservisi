package com.sk.karte.controller;

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

import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.security.CheckSecurity;
import com.sk.karte.servis.KarteServis;

@RestController
@RequestMapping("/karte")
public class KarteController {

    private KarteServis karteServis;

    public KarteController(KarteServis karteServis) {
        this.karteServis = karteServis;
    }

//    @GetMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
//    public ResponseEntity<Page<KartaDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
//        return new ResponseEntity<>(karteServis.findAll(pageable), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<KartaDto> saveLet(@RequestBody @Valid KreiranjeKarteDto kreiranjeLetaDto) {
        return new ResponseEntity<>(karteServis.add(kreiranjeLetaDto), HttpStatus.CREATED);
    }
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<KartaDto> deleteLet(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(karteServis.delete(id), HttpStatus.OK);
//    }

}
