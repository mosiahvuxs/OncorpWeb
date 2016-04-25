package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UnidadeMedida implements Serializable {

	private Long id;
	private String descricao;
	private Boolean flagAtivo;
	private String codigo;
	private Empresa empresa;

	public UnidadeMedida() {

	}

	public UnidadeMedida(Boolean flagAtivo, Empresa empresa) {

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
