package com.sk.letovi.dto;

import javax.validation.constraints.NotBlank;

public class FilterLetaDto {
	
	private String pocetnaDestinacija;
	@NotBlank
	private String krajnjaDestinacija;
    private int duzinaLeta;
    private int cena;
	public String getPocetnaDestinacija() {
		return pocetnaDestinacija;
	}
	public void setPocetnaDestinacija(String pocetnaDestinacija) {
		this.pocetnaDestinacija = pocetnaDestinacija;
	}
	public String getKrajnjaDestinacija() {
		return krajnjaDestinacija;
	}
	public void setKrajnjaDestinacija(String krajnjaDestinacija) {
		this.krajnjaDestinacija = krajnjaDestinacija;
	}
	public int getDuzinaLeta() {
		return duzinaLeta;
	}
	public void setDuzinaLeta(int duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}

}
