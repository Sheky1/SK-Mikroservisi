package com.sk.korisnicki.listener;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.korisnicki.dto.OtkazivanjeKarteDto;
import com.sk.korisnicki.servis.KorisnickiServis;

@Component
public class OtkazivanjeKarteListener {
	
	private KorisnickiServis korisnickiServis;
	private ObjectMapper objectMapper;

	public OtkazivanjeKarteListener(KorisnickiServis korisnickiServis, ObjectMapper objectMapper) {
		this.korisnickiServis = korisnickiServis;
		this.objectMapper = objectMapper;
	}

	@JmsListener(destination = "${destination.otkazivanje-karte}", concurrency = "5-10")
	public void handleOtkazivanjeKarte(Message message) {
		try {
			String jsonText = ((TextMessage) message).getText();
			OtkazivanjeKarteDto otkazivanjeKarteDto = objectMapper.readValue(jsonText, OtkazivanjeKarteDto.class);
			korisnickiServis.otkazivanjeKarte(otkazivanjeKarteDto.getIdLeta(), otkazivanjeKarteDto.getDuzinaLeta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
