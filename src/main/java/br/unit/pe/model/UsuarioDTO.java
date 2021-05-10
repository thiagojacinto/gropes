package br.unit.pe.model;

public class UsuarioDTO {

	private String nome;
	private String email;
	private String senha;
	private EnderecoDTO endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [nome=" + nome + ", email=" + email + ", senha=" + senha + ", endereco=" + endereco + "]";
	}
}
