package br.unit.pe.model;

import java.util.Date;

public class TecnologiaUsuarioDTO {

	private TecnologiaDTO tecnologia;
	private boolean utilizaAtual;
	private Date dataIni;
	private Date dataFim;
	//private boolean maisDe24Meses;
	private Integer aplicacaoPratica;

	public Integer getAplicacaoPratica() {
		return aplicacaoPratica;
	}

	public void setAplicacaoPratica(Integer aplicacaoPratica) {
		this.aplicacaoPratica = aplicacaoPratica;
	}

	public TecnologiaDTO getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(TecnologiaDTO tecnologia) {
		this.tecnologia = tecnologia;
	}

	public boolean isUtilizaAtual() {
		return utilizaAtual;
	}

	public void setUtilizaAtual(boolean utilizaAtual) {
		this.utilizaAtual = utilizaAtual;
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

	/*public boolean isMaisDe24Meses() {
		return maisDe24Meses;
	}

	public void setMaisDe24Meses(boolean maisDe24Meses) {
		this.maisDe24Meses = maisDe24Meses;
	}*/

	@Override
	public String toString() {
		return "TecnologiaUsuarioDTO [tecnologia=" + tecnologia + ", utilizaAtual=" + utilizaAtual + ", dataIni="
				+ dataIni + ", dataFim=" + dataFim  + "]";
	}

}
