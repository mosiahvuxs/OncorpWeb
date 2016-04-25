package br.com.oncorpweb.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement(name = "ncm")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ncm implements Serializable {

	private Long id;
	
	private String descricao;
	
	@XmlTransient
	private String descricaoFormatada;
	
	@XmlTransient
	private Boolean flagAtivo;
	private String codigo;
	
	@XmlTransient
	private Empresa empresa;
	
	private String codigoExcecao;
	
	private GrupoNcm grupoNcm;

	public Ncm() {

	}

	public Ncm(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Ncm(Boolean flagAtivo, Empresa empresa) {

		this.flagAtivo = flagAtivo;

		this.empresa = empresa;
	}

	public Ncm(Long id) {
		this.id = id;
	}

	public Ncm(Boolean flagAtivo, Empresa empresa, GrupoNcm grupoNcm) {

		this.flagAtivo = flagAtivo;

		this.empresa = empresa;

		this.grupoNcm = grupoNcm;
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

	public String getDescricaoFormatada() {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.descricao))) {

			if (this.descricao.length() > 70) {

				this.descricaoFormatada = this.descricao.substring(0, 70) + "...";

			} else {

				this.descricaoFormatada = this.descricao;
			}
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigo))) {

			if (!TSUtil.isEmpty(TSUtil.tratarString(this.descricaoFormatada))) {

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigoExcecao))) {

					this.descricaoFormatada = this.codigo + "-" + this.codigoExcecao + "-" + this.descricaoFormatada;

				} else {

					this.descricaoFormatada = this.codigo + "-" + this.descricaoFormatada;
				}

			} else {

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.codigoExcecao))) {

					this.descricaoFormatada = this.codigo + "-" + this.codigoExcecao;

				} else {

					this.descricaoFormatada = this.codigo;
				}
			}

		}

		return descricaoFormatada;

	}

	public void setDescricaoFormatada(String descricaoFormatada) {
		this.descricaoFormatada = descricaoFormatada;
	}

	public String getCodigoFormatado() {

		if (!TSUtil.isEmpty(this.codigoExcecao)) {

			return this.codigo + "-" + this.codigoExcecao;

		}

		return this.codigo;

	}

	public String getCodigoExcecao() {
		return codigoExcecao;
	}

	public void setCodigoExcecao(String codigoExcecao) {
		this.codigoExcecao = codigoExcecao;
	}

	public GrupoNcm getGrupoNcm() {
		return grupoNcm;
	}

	public void setGrupoNcm(GrupoNcm grupoNcm) {
		this.grupoNcm = grupoNcm;
	}

}
