package br.unit.pe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Tecnologia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique=true)
	private String descricao;
	private Double relevancia;

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

	public Double getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(Double relevancia) {
		this.relevancia = relevancia;
	}

	@Override
	public String toString() {
		return "Tecnologia [id=" + id + ", descricao=" + descricao + ", relevancia=" + relevancia + "]";
	}

	
}
