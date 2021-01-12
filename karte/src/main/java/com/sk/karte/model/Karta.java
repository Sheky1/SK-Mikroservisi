package com.sk.karte.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Karta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
	private Long idUsera;
    @Column
    private Long idLeta;
    @Column
    private int cena;
    @Column
    private boolean otkazanaKarta;
    @Column
    private String datumKreiranja;
    
    public Karta() {
    }
    
	public Karta(Long idUsera, Long idLeta, int cena) {
		super();
		this.idUsera = idUsera;
		this.idLeta = idLeta;
		this.cena = cena;
		this.otkazanaKarta = false;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z\"");
		Date date = new Date(System.currentTimeMillis());
		this.datumKreiranja = formatter.format(date);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isOtkazanaKarta() {
		return otkazanaKarta;
	}

	public void setOtkazanaKarta(boolean otkazanaKarta) {
		this.otkazanaKarta = otkazanaKarta;
	}

}
