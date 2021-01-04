package com.sk.korisnicki.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.korisnicki.dto.RezervisanjeKarteDto;
import com.sk.korisnicki.servis.KorisnickiServis;

@Component
public class RezervacijaKarteListener {
	
	private KorisnickiServis korisnickiServis;
	private ObjectMapper objectMapper;

	public RezervacijaKarteListener(KorisnickiServis korisnickiServis, ObjectMapper objectMapper) {
		this.korisnickiServis = korisnickiServis;
		this.objectMapper = objectMapper;
	}

	@JmsListener(destination = "${destination.rezervisanje-karte}", concurrency = "5-10")
	public void handleRezervacijaKarte(Message message) {
		try {
			String jsonText = ((TextMessage) message).getText();
			System.out.println("Poruka stigla " + jsonText);
			RezervisanjeKarteDto rezervisanjeKarteDto = objectMapper.readValue(jsonText, RezervisanjeKarteDto.class);
			korisnickiServis.rezervacijaKarte(rezervisanjeKarteDto.getIdUsera(), rezervisanjeKarteDto.getDuzinaLeta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
