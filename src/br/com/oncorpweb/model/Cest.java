package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cest implements Serializable {

	private Long id;

	private String codigo;

	private String descricao;

	public Cest(String codigo) {
		this.codigo = codigo;
	}

	public Cest() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
