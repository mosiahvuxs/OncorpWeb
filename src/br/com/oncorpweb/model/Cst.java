package br.com.oncorpweb.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@XmlRootElement(name = "cst")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cst implements Serializable {

	private Long id;
	private String codigo;
	private TipoCst tipo; // 1 ICMS,2 PIS, 3 COFINS,4 IPI
	private String descricao;
	
	@XmlTransient
	private String descricaoFormatada;
	
	@XmlTransient
	private Boolean flagAtivo;
	@XmlTransient
	private Empresa empresa;
	@XmlTransient
	private String leiInterno, leiLinkInterno, leiSaida, leiLinkSaida,ordernacao;
	@XmlTransient
	private Natureza natureza;
	@XmlTransient
	private BigDecimal aliquota;
	@XmlTransient
	private SegmentoCst segmentoCst;

	public Cst() {

	}

	public Cst(TipoCst tipo) {

		this.tipo = tipo;
	}


	public Cst(String codigo, TipoCst tipo) {

		this.codigo = codigo;
		this.tipo = tipo;
	}

	public Cst(Long id) {
		this.id = id;
	}

	public Cst(Long id, Empresa empresa) {

		this.id = id;

		this.empresa = empresa;
	}

	public Cst(TipoCst tipoCst, Boolean flagAtivo, String ordenacao) {

		this.tipo = tipoCst;
		this.flagAtivo = flagAtivo;
		this.ordernacao = ordenacao;
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

	public TipoCst getTipo() {
		return tipo;
	}

	public void setTipo(TipoCst tipo) {
		this.tipo = tipo;
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

	public String getOrdernacao() {
		return ordernacao;
	}

	public void setOrdernacao(String ordernacao) {
		this.ordernacao = ordernacao;
	}

	public String getDescricaoFormatada() {

		if (!TSUtil.isEmpty(this.descricao) && !TSUtil.isEmpty(this.codigo)) {

			this.descricaoFormatada = "CÓDIGO - " + this.codigo + " - " + this.descricao;
		}

		return descricaoFormatada;
	}

	public void setDescricaoFormatada(String descricaoFormatada) {
		this.descricaoFormatada = descricaoFormatada;
	}

	public Natureza getNatureza() {
		return natureza;
	}

	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public SegmentoCst getSegmentoCst() {
		return segmentoCst;
	}

	public void setSegmentoCst(SegmentoCst segmentoCst) {
		this.segmentoCst = segmentoCst;
	}

	public String getLeiInterno() {
		return leiInterno;
	}

	public void setLeiInterno(String leiInterno) {
		this.leiInterno = leiInterno;
	}

	public String getLeiLinkInterno() {
		return leiLinkInterno;
	}

	public void setLeiLinkInterno(String leiLinkInterno) {
		this.leiLinkInterno = leiLinkInterno;
	}

	public String getLeiSaida() {
		return leiSaida;
	}

	public void setLeiSaida(String leiSaida) {
		this.leiSaida = leiSaida;
	}

	public String getLeiLinkSaida() {
		return leiLinkSaida;
	}

	public void setLeiLinkSaida(String leiLinkSaida) {
		
		this.leiLinkSaida = leiLinkSaida;
		
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
		Cst other = (Cst) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;

	}

}
