package com.sk.karte.dto;

public class OtkazivanjeKarteDto {

	private Long idLeta;
	private int duzinaLeta;
	
	public OtkazivanjeKarteDto() {
	}
	
	public OtkazivanjeKarteDto(Long idLeta, int duzinaLeta) {
		this.idLeta = idLeta;
		this.duzinaLeta = duzinaLeta;
	}
	public Long getIdLeta() {
		return idLeta;
	}
	public void setIdLeta(Long idLeta) {
		this.idLeta = idLeta;
	}
	public int getDuzinaLeta() {
		return duzinaLeta;
	}
	public void setDuzinaLeta(int duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}
	
	
	
}
