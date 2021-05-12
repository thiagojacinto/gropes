package br.unit.pe.model;

import java.util.Date;

public class EmpresaUsuarioItemDTO {

	private EmpresaUsuarioDTO empresaUsuario;
	private Date dataIni;
	private Date dataFim;

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
	
	@Override
	public String toString() {
		return "EmpresaUsuarioItemDTO [empresaUsuario=" + empresaUsuario + ", dataIni=" + dataIni + ", dataFim="
				+ dataFim + "]";
	}

}
