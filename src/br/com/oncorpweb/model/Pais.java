package br.com.oncorpweb.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@XmlRootElement(name = "pais")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pais implements Serializable {

	private Long id;
	private String descricao;
	private String codigo;
	
	@XmlTransient
	private Boolean flagAtivo;
	@XmlTransient	
	private List<Estado> estados;
	@XmlTransient	
	private Estado estado;
	@XmlTransient	
	private Empresa empresa;
	
	public Pais(){
		
	}

	public Pais(Long id) {
		this.id = id;
	}

	public Pais(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Pais(Estado estado, Empresa empresa) {

		this.estado = estado;
		
		this.empresa = empresa;
		
	}

	public Pais(Boolean flagAtivo, Empresa empresa) {
		this.flagAtivo = flagAtivo;
		
		this.empresa = empresa;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
