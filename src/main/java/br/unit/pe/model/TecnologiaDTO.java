package br.unit.pe.model;

import java.util.Date;

public class TecnologiaDTO {

	private String tecnologia;
	//private Integer dificuldade;
	private Integer frequenciaDeUso;
	private Boolean utilizaAtual;
	private Date dataIni;
	private Date dataFim;
	
	
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


	public Date getDataIni() {
		return dataIni;
	}


	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
