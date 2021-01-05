package com.sk.letovi.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.letovi.dtoStrani.RezervisanjeKarteDto;
import com.sk.letovi.servis.LetoviServis;

@Component
public class RezervacijaKarteListener {
	
	private LetoviServis letoviServis;
	private ObjectMapper objectMapper;

	public RezervacijaKarteListener(LetoviServis letoviServis, ObjectMapper objectMapper) {
		this.letoviServis = letoviServis;
		this.objectMapper = objectMapper;
	}

	@JmsListener(destination = "${destination.rezervisanje-karte}", concurrency = "5-10")
	public void handleRezervacijaKarte(Message message) {
		try {
			String jsonText = ((TextMessage) message).getText();
			RezervisanjeKarteDto rezervisanjeKarteDto = objectMapper.readValue(jsonText, RezervisanjeKarteDto.class);
			letoviServis.rezervacijaKarte(rezervisanjeKarteDto.getIdLeta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
