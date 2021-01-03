package com.sk.korisnicki.servis;

import com.sk.korisnicki.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface KorisnickiServis {

    Page<KorisnikDto> findAll(Pageable pageable);

    KorisnikDto add(RegistracijaKorisnikaDto registracijaKorisnikaDto);

    TokenOdgovorDto login(TokenZahtevDto tokenZahtevDto);
}
