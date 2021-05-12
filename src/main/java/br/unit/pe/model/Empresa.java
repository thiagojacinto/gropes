package br.unit.pe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String descricao;
	private Character autonomo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character isAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Character autonomo) {
		this.autonomo = autonomo;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", descricao=" + descricao + ", autonomo=" + autonomo + "]";
	}
	

}
