package com.sk.korisnicki.controller;

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

import com.sk.korisnicki.dto.KorisnikDto;
import com.sk.korisnicki.dto.RegistracijaKorisnikaDto;
import com.sk.korisnicki.dto.TokenOdgovorDto;
import com.sk.korisnicki.dto.TokenZahtevDto;

//import com.sk.korisnicki.security.CheckSecurity;

import javax.validation.Valid;

import com.sk.korisnicki.servis.KorisnickiServis;

@RestController
@RequestMapping("/korisnik")
public class KorisnickiController {

    private KorisnickiServis korisnickiServis;

    public KorisnickiController(KorisnickiServis korisnickiServis) {
        this.korisnickiServis = korisnickiServis;
    }

//    @ApiOperation(value = "Get all users")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Sorting criteria in the format: property(,asc|desc). " +
//                            "Default sort order is ascending. " +
//                            "Multiple sort criteria are supported.")})
    @GetMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<KorisnikDto>> getAllUsers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(korisnickiServis.findAll(pageable), HttpStatus.OK);
    }

//    @GetMapping("/{id}/kartica")
//    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(korisnickiServis.findDiscount(id), HttpStatus.OK);
//    }

//    @ApiOperation(value = "Register user")
    @PostMapping
    public ResponseEntity<KorisnikDto> saveKorisnik(@RequestBody @Valid RegistracijaKorisnikaDto registracijaKorisnikaDto) {
        return new ResponseEntity<>(korisnickiServis.add(registracijaKorisnikaDto), HttpStatus.CREATED);
    }

//    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenOdgovorDto> loginUser(@RequestBody @Valid TokenZahtevDto tokenZahtevDto) {
        return new ResponseEntity<>(korisnickiServis.login(tokenZahtevDto), HttpStatus.OK);
    }

}
