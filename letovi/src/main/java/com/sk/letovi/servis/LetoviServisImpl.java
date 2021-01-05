package com.sk.letovi.servis;

import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.dtoStrani.KartaDto;
import com.sk.letovi.dtoStrani.OtkazivanjeKarteDto;
import com.sk.letovi.exceptions.NotFoundException;
import com.sk.letovi.mapper.LetoviMapper;
import com.sk.letovi.model.Avion;
import com.sk.letovi.model.Let;
import com.sk.letovi.repository.AvionRepository;
import com.sk.letovi.repository.LetoviRepository;

@Service
public class LetoviServisImpl implements LetoviServis {

    private LetoviRepository letoviRepository;
    private AvionRepository avionRepository;
    private LetoviMapper letoviMapper;
    private RestTemplate karteServisRestTemplate;
    private JmsTemplate jmsTemplate;
    private String otkazivanjeKarte;
    private ObjectMapper objectMapper;

    public LetoviServisImpl(LetoviRepository letoviRepository, LetoviMapper letoviMapper, 
    							AvionRepository avionRepository, RestTemplate karteServisRestTemplate,
    							 JmsTemplate jmsTemplate, @Value("${destination.otkazivanje-karte}") String otkazivanjeKarte,
    							 ObjectMapper objectMapper) {
        this.letoviRepository = letoviRepository;
        this.avionRepository = avionRepository;
        this.letoviMapper = letoviMapper;
        this.karteServisRestTemplate = karteServisRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.otkazivanjeKarte = otkazivanjeKarte;
        this.objectMapper = objectMapper;
    }
    
	@Override
	public Page<LetDto> findAll(Pageable pageable) {
		Page<LetDto> sviLetovi = letoviRepository.findAll(pageable).map(letoviMapper::letToLetDto);
		System.out.println(sviLetovi);
		List<LetDto> listaSvihLetova = new ArrayList<LetDto>();
		for (LetDto letDto : sviLetovi) {
			listaSvihLetova.add(letDto);
			System.out.println(letDto);
		}
		List<LetDto> listaZaIzbacivanje = new ArrayList<LetDto>();
		for (LetDto letDto : listaSvihLetova) {
			if(letDto.getBrojKarata() == letDto.getAvionDto().getKapacitetPutnika()) listaZaIzbacivanje.add(letDto);
		}
		listaSvihLetova.removeAll(listaZaIzbacivanje);
		Page<LetDto> letovi = new PageImpl<LetDto>(listaSvihLetova);
		
        return letovi;
	}
    
	@Override
	public LetDto findLet(Long id) { 
		Let let= letoviRepository
			    .findLetById(id)
			    .orElseThrow(() -> new NotFoundException(String
			    .format("Korisnik sa id-jem: %s ne postoji.", id)));
		return letoviMapper.letToLetDto(let);
	}

	@Override
	public LetDto add(KreiranjeLetaDto kreiranjeLetaDto) {
    	Avion avion = avionRepository
                .findAvionById(kreiranjeLetaDto.getIdAviona())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", kreiranjeLetaDto.getIdAviona())));
    	
        Let novLet = letoviMapper.kreiranjeLetaDtoToLet(kreiranjeLetaDto);
        novLet.setAvion(avion);
        letoviRepository.save(novLet);
        
        avion.getLetovi().add(novLet);
        avionRepository.save(avion);
        
        return letoviMapper.letToLetDto(novLet);
	}

	@Override
	public LetDto delete(Long id) {
    	Let let= letoviRepository
                .findLetById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
    	Avion avion = avionRepository
                .findAvionById(let.getAvion().getId())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", let.getAvion().getId())));
    	
		@SuppressWarnings("unchecked")
		ResponseEntity<List<KartaDto>> responseEntityKarteDto = 
				karteServisRestTemplate.exchange("/karte/let/" + id, HttpMethod.GET, null, (Class<List<KartaDto>>)(Object)List.class);
		
    	List<KartaDto> listaKarata = responseEntityKarteDto.getBody();
    	if(listaKarata.size() != 0) {
    		let.setOtkazanLet(true);

    		try {
    			jmsTemplate.convertAndSend(new ActiveMQTopic(otkazivanjeKarte), objectMapper.writeValueAsString(new OtkazivanjeKarteDto(id, let.getDuzinaLeta())));
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
		
		
    	avion.getLetovi().remove(let);
    	avionRepository.save(avion);
    	let.setOtkazanLet(true);
    	return letoviMapper.letToLetDto(let);
	}

	@Override
	public void rezervacijaKarte(Long idLeta) {
    	Let let= letoviRepository
                .findLetById(idLeta)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", idLeta)));
    	let.setBrojKarata(let.getBrojKarata() + 1);
    	letoviRepository.save(let);
	}

}
