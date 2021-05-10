package br.unit.pe.model;

public class TecnologiaDTO {

	private String tecnologia;
	private Integer dificuldade;

	
	public String getTecnologia() {
		return tecnologia;
	}


	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}


	public Integer getDificuldade() {
		return dificuldade;
	}


	public void setDificuldade(Integer dificuldade) {
		this.dificuldade = dificuldade;
	}


	@Override
	public String toString() {
		return "TecnologiaDTO [tecnologia=" + tecnologia + ", dificuldade=" + dificuldade + "]";
	}

}
