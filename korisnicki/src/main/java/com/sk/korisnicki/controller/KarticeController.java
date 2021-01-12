package com.sk.korisnicki.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;
import com.sk.korisnicki.security.CheckSecurity;
import com.sk.korisnicki.servis.KarticeServis;

@RestController
@RequestMapping("/kartice")
public class KarticeController {

    private KarticeServis karticeServis;

    public KarticeController(KarticeServis karticeServis) {
        this.karticeServis = karticeServis;
    }
    
	@GetMapping("/{id}")
	@CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
	public ResponseEntity<List<KarticaDto>> getAllByKorisnik(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
	    return new ResponseEntity<>(karticeServis.findByKorisnik(id), HttpStatus.OK);
	}

    @PostMapping
	@CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<KarticaDto> saveKartica(@RequestHeader("Authorization") String authorization, @RequestBody @Valid KreiranjeKarticeDto kreiranjeKarticeDto) {
        return new ResponseEntity<>(karticeServis.add(kreiranjeKarticeDto), HttpStatus.CREATED);
    }

}
