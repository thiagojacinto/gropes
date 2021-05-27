package br.unit.pe.model;

import java.util.Date;
import java.util.List;

public class EmpresaUsuarioDTO {

	private EmpresaDTO empresa;
	private Date dataIni;
	private Date dataFim;
	private Integer dificuldade;
	private List<TecnologiaDTO> tecnologias;
	private String descricao;
	private boolean trabalhoAtual;
	private boolean autonomo;
	private Integer diversidade;
	
	public boolean isAutonomo() {
		return autonomo;
	}

	public void setAutonomo(boolean autonomo) {
		this.autonomo = autonomo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
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

	public Integer getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Integer dificuldade) {
		this.dificuldade = dificuldade;
	}

	public List<TecnologiaDTO> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<TecnologiaDTO> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	public boolean isTrabalhoAtual() {
		return trabalhoAtual;
	}

	public void setTrabalhoAtual(boolean trabalhoAtual) {
		this.trabalhoAtual = trabalhoAtual;
	}

	public Integer getDiversidade() {
		return diversidade;
	}

	public void setDiversidade(Integer diversidade) {
		this.diversidade = diversidade;
	}

	@Override
	public String toString() {
		return "EmpresaUsuarioDTO [empresa=" + empresa + ", dataIni=" + dataIni + ", dataFim=" + dataFim
				+ ", dificuldade=" + dificuldade + ", tecnologias=" + tecnologias + ", descricao=" + descricao
				+ ", trabalhoAtual=" + trabalhoAtual + ", autonomo=" + autonomo + ", diversidade=" + diversidade + "]";
	}
}
