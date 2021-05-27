package br.unit.pe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotBlank
	private Date conheceDesde;
	@NotBlank
	private Date estudaDesde;
	@NotBlank
	private Date estudouAte;
	@NotBlank
	private Integer aplicacaoPratica;
	
	
	//private Character maisDe24Meses;

	

	/*public Character getMaisDe24Meses() {
		return maisDe24Meses;
	}

	public void setMaisDe24Meses(Character maisDe24Meses) {
		this.maisDe24Meses = maisDe24Meses;
	}*/

	public Integer getAplicacaoPratica() {
		return aplicacaoPratica;
	}

	public void setAplicacaoPratica(Integer aplicacaoPratica) {
		this.aplicacaoPratica = aplicacaoPratica;
	}

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

	@Override
	public String toString() {
		return "TecnologiaUsuario [usuario=" + usuario + ", tecnologia=" + tecnologia + ", inovatividade="
				+ inovatividade + ", conheceDesde=" + conheceDesde + ", estudaDesde=" + estudaDesde + ", estudouAte="
				+ estudouAte + "] ";
						//+ "maisDe24Meses=" + maisDe24Meses + "]";
	}
}
