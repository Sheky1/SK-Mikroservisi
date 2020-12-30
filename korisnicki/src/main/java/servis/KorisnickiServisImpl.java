package servis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import model.Korisnik;
import repository.KorisnickiRepository;
import repository.KorisnikMapper;
import security.TokenServis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.KorisnikDto;
import dto.RegistracijaKorisnikaDto;
import dto.TokenOdgovorDto;
import dto.TokenZahtevDto;
import exceptions.NotFoundException;

@Service
@Transactional
public class KorisnickiServisImpl implements KorisnickiServis {

    private TokenServis tokenServis;
    private KorisnickiRepository korisnickiRepository;
//    private UserStatusRepository userStatusRepository;
    private KorisnikMapper korisnikMapper;

    public KorisnickiServisImpl(KorisnickiRepository korisnickiRepository, TokenServis tokenServis, KorisnikMapper korisnikMapper) {
        this.korisnickiRepository = korisnickiRepository;
        this.tokenServis = tokenServis;
        this.korisnikMapper = korisnikMapper;
//        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public Page<KorisnikDto> findAll(Pageable pageable) {
        return korisnickiRepository.findAll(pageable)
                .map(korisnikMapper::korisnikToKorisnikDto);
    }

//    @Override
//    public DiscountDto findDiscount(Long id) {
//        User user = userRepository
//                .findById(id)
//                .orElseThrow(() -> new NotFoundException(String
//                        .format("User with id: %d not found.", id)));
//        List<UserStatus> userStatusList = userStatusRepository.findAll();
//        //get discount
//        Integer discount = userStatusList.stream()
//                .filter(userStatus -> userStatus.getMaxNumberOfReservations() >= user.getNumberOfReservations()
//                        && userStatus.getMinNumberOfReservations() <= user.getNumberOfReservations())
//                .findAny()
//                .get()
//                .getDiscount();
//        return new DiscountDto(discount);
//    }

    @Override
    public KorisnikDto add(RegistracijaKorisnikaDto registracijaKorisnikaDto) {
        Korisnik user = korisnikMapper.registracijaKorisnikaDtoToKorisnik(registracijaKorisnikaDto);
        korisnickiRepository.save(user);
        return korisnikMapper.korisnikToKorisnikDto(user);
    }

    @Override
    public TokenOdgovorDto login(TokenZahtevDto tokenZahtevDto) {
        //Try to find active user for specified credentials
    	Korisnik user = korisnickiRepository
                .findKorisnikByEmailAndSifra(tokenZahtevDto.getEmail(), tokenZahtevDto.getSifra())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenZahtevDto.getEmail(),
                        		tokenZahtevDto.getSifra())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenOdgovorDto(tokenServis.generate(claims));
    }
}
