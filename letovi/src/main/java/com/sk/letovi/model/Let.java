package com.sk.letovi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Let {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
	private String pocetnaDestinacija;
    @Column
	private String krajnjaDestinacija;
    @Column
    private int duzinaLeta;
    @Column
    private int cena;
    @ManyToOne
    private Avion avion;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
    
    

}
