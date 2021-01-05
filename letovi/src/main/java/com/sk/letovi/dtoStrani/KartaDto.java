package com.sk.letovi.dtoStrani;

public class KartaDto {

	private Long id;
	private Long idUsera;
    private Long idLeta;
    private int cena;
    private boolean otkazanaKarta;
    
    public KartaDto() {
    }
    
	public KartaDto(Long id, Long idUsera, Long idLeta, int cena) {
		super();
		this.id = id;
		this.idUsera = idUsera;
		this.idLeta = idLeta;
		this.cena = cena;
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
