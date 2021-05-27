package br.unit.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class EmpresaUsuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Empresa empresa;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;
	@NotNull
	private Date dataIni;
	@NotNull
	private Date dataFim;
	@NotNull
	private Integer diversidade;
	@NotNull
	private Integer complexidade;
	private Boolean trabalhoAtual;
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Integer getDiversidade() {
		return diversidade;
	}

	public void setDiversidade(Integer diversidade) {
		this.diversidade = diversidade;
	}

	public Integer getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(Integer complexidade) {
		this.complexidade = complexidade;
	}
	
	public Boolean getTrabalhoAtual() {
		return trabalhoAtual;
	}

	public void setTrabalhoAtual(Boolean trabalhoAtual) {
		this.trabalhoAtual = trabalhoAtual;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "EmpresaUsuario [id=" + id + ", empresa=" + empresa + ", usuario=" + usuario + ", dataIni=" + dataIni
				+ ", dataFim=" + dataFim + ", diversidade=" + diversidade + ", complexidade=" + complexidade
				+ ", trabalhoAtual=" + trabalhoAtual + ", descricao=" + descricao + "]";
	}
	
	
}
