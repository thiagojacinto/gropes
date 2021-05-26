package br.unit.pe.model;

import java.util.List;

public class InfoRegistro {

	private List<EmpresaUsuarioItem> empresaUsuarioItens;
	private List<TecnologiaUsuario> tecnologiasUsuario;

	public List<EmpresaUsuarioItem> getEmpresaUsuarioItens() {
		return empresaUsuarioItens;
	}

	public void setEmpresaUsuarioItens(List<EmpresaUsuarioItem> empresaUsuarioItens) {
		this.empresaUsuarioItens = empresaUsuarioItens;
	}

	public List<TecnologiaUsuario> getTecnologiasUsuario() {
		return tecnologiasUsuario;
	}

	public void setTecnologiasUsuario(List<TecnologiaUsuario> tecnologiasUsuario) {
		this.tecnologiasUsuario = tecnologiasUsuario;
	}

}
