package br.unit.pe.model;

import java.util.Date;

public class EmpresaUsuarioItemDTO {

	private EmpresaUsuarioDTO empresaUsuario;
	private Date dataIni;
	private Date dataFim;
	private boolean trabalhoAtual;

	public EmpresaUsuarioDTO getEmpresaUsuario() {
		return empresaUsuario;
	}

	public void setEmpresaUsuario(EmpresaUsuarioDTO empresaUsuario) {
		this.empresaUsuario = empresaUsuario;
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

	public boolean isTrabalhoAtual() {
		return trabalhoAtual;
	}

	public void setTrabalhoAtual(boolean trabalhoAtual) {
		this.trabalhoAtual = trabalhoAtual;
	}

	@Override
	public String toString() {
		return "EmpresaUsuarioItemDTO [empresaUsuario=" + empresaUsuario + ", dataIni=" + dataIni + ", dataFim="
				+ dataFim + ", trabalhoAtual=" + trabalhoAtual + "]";
	}

}
