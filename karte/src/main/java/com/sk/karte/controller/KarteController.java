package com.sk.karte.controller;

import java.util.List;

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
    
	@GetMapping("/korisnik/{id}")
	@CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
	public ResponseEntity<Page<KartaDto>> getAllByKorisnik(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, Pageable pageable) {
	    return new ResponseEntity<>(karteServis.findByKorisnik(id, pageable), HttpStatus.OK);
	}
    
	@GetMapping("/let/{id}")
	@CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
	public ResponseEntity<List<KartaDto>> getAll(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
	    return new ResponseEntity<>(karteServis.findByLet(id), HttpStatus.OK);
	}

    @PostMapping
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<KartaDto> saveKarta(@RequestHeader("Authorization") String authorization, @RequestBody @Valid KreiranjeKarteDto kreiranjeLetaDto) {
        return new ResponseEntity<>(karteServis.add(kreiranjeLetaDto), HttpStatus.CREATED);
    }

}
