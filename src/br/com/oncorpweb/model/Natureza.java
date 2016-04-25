package br.com.oncorpweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement(name = "natureza")
@XmlAccessorType(XmlAccessType.FIELD)
public class Natureza implements Serializable {

	private Long id;
	private String codigo;
	private String descricao;
	
	@XmlTransient
	private String descricaoFormatada;
	
	@XmlTransient
	private Cst cstPis;
	
	@XmlTransient
	private Date dataInicioEscrituracao;
	@XmlTransient
	private Date dataFinalEscrituracao;

	public Natureza() {

	}
	
	public Natureza(String codigo) {
		this.codigo = codigo;
	}	

	public Natureza(Cst cstPis) {

		this.cstPis = cstPis;
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

	public Cst getCstPis() {
		return cstPis;
	}

	public void setCstPis(Cst cstPis) {
		this.cstPis = cstPis;
	}

	public Date getDataInicioEscrituracao() {
		return dataInicioEscrituracao;
	}

	public void setDataInicioEscrituracao(Date dataInicioEscrituracao) {
		this.dataInicioEscrituracao = dataInicioEscrituracao;
	}

	public Date getDataFinalEscrituracao() {
		return dataFinalEscrituracao;
	}

	public void setDataFinalEscrituracao(Date dataFinalEscrituracao) {
		this.dataFinalEscrituracao = dataFinalEscrituracao;
	}

	public String getDescricaoFormatada() {

		if (!TSUtil.isEmpty(this.codigo) && !TSUtil.isEmpty(this.descricao)) {

			this.descricaoFormatada = this.codigo + " - " + this.descricao;
		}
		return descricaoFormatada;
	}

	public void setDescricaoFormatada(String descricaoFormatada) {
		this.descricaoFormatada = descricaoFormatada;
	}

}
