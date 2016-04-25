package br.com.oncorpweb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@XmlRootElement(name = "estado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Estado implements Serializable {

	private Long id;
	private String descricao;
	
	@XmlTransient
	private Boolean flagAtivo;
	
	private String codigo;
	
	private Pais pais;
	
	@XmlTransient
	private Empresa empresa;

	public Estado() {

	}

	public Estado(Pais pais) {
		this.pais = pais;
	}

	public Estado(Long id) {

		this.id = id;

	}

	public Estado(Pais pais, Empresa empresa) {
		this.pais = pais;

		this.empresa = empresa;

	}

	public Estado(Long id, Empresa empresa) {

		this.id = id;

		this.empresa = empresa;

	}

	public Estado(String codigo, Pais pais) {

		this.pais = pais;

		this.codigo = codigo;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
