package com.sk.karte.listener;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.karte.dto.OtkazivanjeKarteDto;
import com.sk.karte.servis.KarteServis;

@Component
public class OtkazivanjeKarteListener {
	
	private KarteServis karteServis;
	private ObjectMapper objectMapper;

	public OtkazivanjeKarteListener(KarteServis karteServis, ObjectMapper objectMapper) {
		this.karteServis = karteServis;
		this.objectMapper = objectMapper;
	}

	@JmsListener(destination = "${destination.otkazivanje-karte}", concurrency = "5-10")
	public void handleOtkazivanjeKarte(Message message) {
		try {
			String jsonText = ((TextMessage) message).getText();
			OtkazivanjeKarteDto otkazivanjeKarteDto = objectMapper.readValue(jsonText, OtkazivanjeKarteDto.class);
			karteServis.otkazivanjeKarte(otkazivanjeKarteDto.getIdLeta(), otkazivanjeKarteDto.getDuzinaLeta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
