package com.sk.korisnicki.servis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.sk.korisnicki.model.Korisnik;
import com.sk.korisnicki.repository.KorisnickiRepository;
import com.sk.korisnicki.security.TokenServis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.korisnicki.dto.KorisnikDto;
import com.sk.korisnicki.dto.RegistracijaKorisnikaDto;
import com.sk.korisnicki.dto.TokenOdgovorDto;
import com.sk.korisnicki.dto.TokenZahtevDto;

import com.sk.korisnicki.exceptions.NotFoundException;
import com.sk.korisnicki.mappers.KorisnikMapper;

@Service
public class KorisnickiServisImpl implements KorisnickiServis {

    private TokenServis tokenServis;
    private KorisnickiRepository korisnickiRepository;
    private KorisnikMapper korisnikMapper;

    public KorisnickiServisImpl(KorisnickiRepository korisnickiRepository, TokenServis tokenServis, KorisnikMapper korisnikMapper) {
        this.korisnickiRepository = korisnickiRepository;
        this.tokenServis = tokenServis;
        this.korisnikMapper = korisnikMapper;
    }

    @Override
    public Page<KorisnikDto> findAll(Pageable pageable) {
        return korisnickiRepository.findAll(pageable).map(korisnikMapper::korisnikToKorisnikDto);
    }

    @Override
    public KorisnikDto findKorisnik(Long id) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
        return korisnikMapper.korisnikToKorisnikDto(korisnik);
    }

    @Override
    public KorisnikDto add(RegistracijaKorisnikaDto registracijaKorisnikaDto) {
        Korisnik noviKorisnik = korisnikMapper.registracijaKorisnikaDtoToKorisnik(registracijaKorisnikaDto);
        korisnickiRepository.save(noviKorisnik);
        return korisnikMapper.korisnikToKorisnikDto(noviKorisnik);
    }

	@Override
	public KorisnikDto update(Long id, RegistracijaKorisnikaDto registracijaKorisnikaDto) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
        Korisnik updateKorisnik = korisnikMapper.korisnikToUpdateKorisnik(korisnik, registracijaKorisnikaDto);
        korisnickiRepository.save(updateKorisnik);
        return korisnikMapper.korisnikToKorisnikDto(updateKorisnik);
	}

    @Override
    public TokenOdgovorDto login(TokenZahtevDto tokenZahtevDto) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikByEmailAndSifra(tokenZahtevDto.getEmail(), tokenZahtevDto.getSifra())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa email-om: %s i sifrom: %s ne postoji.", tokenZahtevDto.getEmail(), tokenZahtevDto.getSifra())));
        Claims claims = Jwts.claims();
        claims.put("id", korisnik.getId());
        claims.put("role", korisnik.getRole().getNaziv());
        //Generate token
        return new TokenOdgovorDto(tokenServis.generate(claims));
    }
}
