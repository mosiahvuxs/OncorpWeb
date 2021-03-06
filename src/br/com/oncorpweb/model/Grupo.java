package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Grupo implements Serializable {

	private Long id;
	private String descricao;

	public Grupo() {

	}

	public Grupo(Long id) {

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

}
