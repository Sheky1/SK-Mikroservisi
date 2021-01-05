package com.sk.letovi.dtoStrani;

public class RezervisanjeKarteDto {

	private Long idUsera;
	private Long idLeta;
	private int duzinaLeta;

	public RezervisanjeKarteDto() {
	}

	public RezervisanjeKarteDto(Long idUsera, Long idLeta, int cena) {
		super();
		this.idUsera = idUsera;
		this.idLeta = idLeta;
		this.duzinaLeta = cena;
	}

	public Long getIdUsera() {
		return idUsera;
	}

	public void setIdUsera(Long idUsera) {
		this.idUsera = idUsera;
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
