package br.unit.pe.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@IdClass(IdTecnologiaUsuario.class)
public class TecnologiaUsuario {

	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;
	@Id
	@ManyToOne
	@JoinColumn(name = "id_tecnologia")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Tecnologia tecnologia;
	private Double inovatividade;
	private Date conheceDesde;
	private Date estudaDesde;
	private Date estudouAte;

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Double getInovatividade() {
		return inovatividade;
	}

	public void setInovatividade(Double inovatividade) {
		this.inovatividade = inovatividade;
	}

	public Date getConheceDesde() {
		return conheceDesde;
	}

	public void setConheceDesde(Date conheceDesde) {
		this.conheceDesde = conheceDesde;
	}

	public Date getEstudaDesde() {
		return estudaDesde;
	}

	public void setEstudaDesde(Date estudaDesde) {
		this.estudaDesde = estudaDesde;
	}

	public Date getEstudouAte() {
		return estudouAte;
	}

	public void setEstudouAte(Date estudouAte) {
		this.estudouAte = estudouAte;
	}

}
