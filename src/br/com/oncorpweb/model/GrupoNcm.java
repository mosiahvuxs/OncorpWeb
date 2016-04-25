package br.com.oncorpweb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement(name = "grupoNcm")
@XmlAccessorType(XmlAccessType.FIELD)
public class GrupoNcm implements Serializable{
	
	private Long id;
	private String descricao;
	
	@XmlTransient
	private Boolean flagAtivo;
	
	@XmlTransient
	private Empresa empresa;
	
	private String codigo;
	
	public GrupoNcm(){
		
	}
	
	public GrupoNcm(String descricao, Empresa empresa) {
		
		this.descricao = descricao;
		
		this.empresa = empresa;
		
	}
	public GrupoNcm(Boolean flagAtivo, Empresa empresa) {
		
		this.flagAtivo = flagAtivo;
		
		this.empresa = empresa;
	}

	public GrupoNcm(Long id) {
		this.id = id;
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
	
	public String getDescricaoFormatada(){
		
		if(!TSUtil.isEmpty(this.descricao) && this.descricao.length()>70){
			
			return this.codigo + "-" + this.descricao.substring(0, 70) + "...";
			
		}else{
			
			return this.codigo + "-" + this.descricao;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
