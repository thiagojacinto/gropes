package br.unit.pe.model;

public class EnderecoDTO {

	private String rua;
	private String numero;
	private String complemento;
	private String cep;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "EnderecoDTO [rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", cep=" + cep + "]";
	}

	
}
