package br.unit.pe.model;

public class TecnologiaDTO {

	private String tecnologia;
	//private Integer dificuldade;
	private Integer frequenciaDeUso;
	private Boolean utilizaAtual;
	
	
	public Boolean getUtilizaAtual() {
		return utilizaAtual;
	}


	public void setUtilizaAtual(Boolean utilizaAtual) {
		this.utilizaAtual = utilizaAtual;
	}


	@Override
	public String toString() {
		return "TecnologiaDTO [tecnologia=" + tecnologia + ", frequenciaDeUso=" + frequenciaDeUso + ", utilizaAtual="
				+ utilizaAtual + "]";
	}


	public String getTecnologia() {
		return tecnologia;
	}


	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}


	public Integer getFrequenciaDeUso() {
		return frequenciaDeUso;
	}


	public void setFrequenciaDeUso(Integer frequenciaDeUso) {
		this.frequenciaDeUso = frequenciaDeUso;
	}

	
	
	/*public Integer getDificuldade() {
		return dificuldade;
	}


	public void setDificuldade(Integer dificuldade) {
		this.dificuldade = dificuldade;
	}*


	@Override
	public String toString() {
		return "TecnologiaDTO [tecnologia=" + tecnologia + ", dificuldade=" + dificuldade + "]";
	}*/

	
}
