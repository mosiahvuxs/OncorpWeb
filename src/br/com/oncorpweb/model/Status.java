package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Status implements Serializable{
	
	private Long id;
	private String descricao;
	private Boolean flagAtivo;
	
	public Status(){
		
	}
	
	public Status(Long id) {
		this.id= id;
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
	
	

}
