package com.sk.letovi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.security.CheckSecurity;
import com.sk.letovi.servis.LetoviServis;

@RestController
@RequestMapping("/letovi")
public class LetoviController {

    private LetoviServis letoviServis;

    public LetoviController(LetoviServis letoviServis) {
        this.letoviServis = letoviServis;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<LetDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(letoviServis.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LetDto> saveKorisnik(@RequestBody @Valid KreiranjeLetaDto kreiranjeLetaDto) {
        return new ResponseEntity<>(letoviServis.add(kreiranjeLetaDto), HttpStatus.CREATED);
    }

}
