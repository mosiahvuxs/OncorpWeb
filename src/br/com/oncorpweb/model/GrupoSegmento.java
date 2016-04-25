package br.com.oncorpweb.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class GrupoSegmento implements Serializable {

	private Long id;
	private String codigo, descricao, descricaoFormatada;
	private Boolean flagAtivo;

	public GrupoSegmento() {

	}

	public GrupoSegmento(Long id) {

		this.id = id;
	}

	public GrupoSegmento(Boolean flagAtivo) {

		this.flagAtivo = flagAtivo;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
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

	public String getDescricaoFormatada() {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigo)) && !TSUtil.isEmpty(TSUtil.tratarString(this.descricao))) {

			this.descricaoFormatada = this.codigo + "-" + this.descricao;

		} else {

			if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigo))) {

				this.descricaoFormatada = this.codigo;

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.descricaoFormatada))) {

					this.descricaoFormatada = this.descricaoFormatada + "-" + this.descricao;
				}

			} else {

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.descricao))) {

					this.descricaoFormatada = this.descricao;
				}
			}
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.descricaoFormatada))) {

			this.descricaoFormatada = this.descricaoFormatada.toUpperCase();
		}

		return descricaoFormatada;
	}

	public void setDescricaoFormatada(String descricaoFormatada) {
		this.descricaoFormatada = descricaoFormatada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
