package br.unit.pe.model;

public class EmpresaDTO {

	private String empresa;

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "EmpresaDTO [empresa=" + empresa + "]";
	}
	
	
}
