package com.sk.korisnicki.dto;

public class RezervisanjeKarteDto {

	private Long idUsera;
	private int duzinaLeta;

	public RezervisanjeKarteDto() {
	}

	public RezervisanjeKarteDto(Long idUsera, int cena) {
		super();
		this.idUsera = idUsera;
		this.duzinaLeta = cena;
	}

	public Long getIdUsera() {
		return idUsera;
	}

	public void setIdUsera(Long idUsera) {
		this.idUsera = idUsera;
	}

	public int getDuzinaLeta() {
		return duzinaLeta;
	}

	public void setDuzinaLeta(int duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}
	
	
}
