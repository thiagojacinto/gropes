package br.unit.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(IdEmpresaUsuarioItem.class)
public class EmpresaUsuarioItem {

	@Id
	@ManyToOne
	@JoinColumn(name = "empusu_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private EmpresaUsuario empUsu;
	@Id
	@ManyToOne
	@JoinColumn(name = "tecnologia_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Tecnologia tecnologia;
	private Date dataIni;
	private Date dataFim;
	private Integer frequencia;
	private Double ca;
	private Double det;
	private Integer exp;
	private Character utilizaAtual;

	public EmpresaUsuario getEmpUsu() {
		return empUsu;
	}

	public void setEmpUsu(EmpresaUsuario empUsu) {
		this.empUsu = empUsu;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
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

	public Integer getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Double getCa() {
		return ca;
	}

	public void setCa(Double ca) {
		this.ca = ca;
	}

	public Double getDet() {
		return det;
	}

	public void setDet(Double det) {
		this.det = det;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Character getUtilizaAtual() {
		return utilizaAtual;
	}

	public void setUtilizaAtual(Character utilizaAtual) {
		this.utilizaAtual = utilizaAtual;
	}


	@Override
	public String toString() {
		return "EmpresaUsuarioItem [empUsu=" + empUsu + ", tecnologia=" + tecnologia + ", dataIni=" + dataIni
				+ ", dataFim=" + dataFim + ", frequencia=" + frequencia + ", ca=" + ca + ", det=" + det + ", exp=" + exp
				+ ", utilizaAtual=" + utilizaAtual + "]";
	}

	
}
