package br.com.oncorpweb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@XmlRootElement(name = "regimeTributario")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegimeTributario implements Serializable{
	
	private Long id;
	private String descricao;
	
	@XmlTransient
	private Boolean flagAtivo;
	@XmlTransient
	private Empresa empresa;
	
	public RegimeTributario(){
		
	}
	
	public RegimeTributario(Long id) {
		this.id = id;
	}
	public RegimeTributario(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public RegimeTributario(Boolean flagAtivo, Empresa empresa) {
		this.empresa = empresa;
		
		this.flagAtivo = flagAtivo;
	}

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
	public Boolean getFlagAtivo() {
		return flagAtivo;
	}
	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegimeTributario other = (RegimeTributario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
