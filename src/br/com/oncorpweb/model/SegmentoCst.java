package br.com.oncorpweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class SegmentoCst implements Serializable {

	private Long id;

	private Segmento segmento;

	private Cst cst;
	private TipoOperacao tipoOperacao;
	private RegimeTributario regimeTributario;
	private Estado estado;
	private String subItemSt, leiInterno, leiLinkInterno, leiSaida, leiLinkSaida;

	private String ordenacao;

	private BigDecimal aliquota, aliquotaSt, mvaInterno, mvaExterno, mvaImportado, baseReduzida, valorPauta, mvaOriginalIndustria, mvaOriginalAtacado, mvaOriginal, mvaAjustado;

	private Cest cest;

	private Date vigenciaInicial;

	private Date vigenciaFinal;

	private RamoEmpresa ramoEmpresa;

	private Boolean flagHistorico;

	private Natureza natureza;

	private String filtroSql;

	public SegmentoCst() {

	}

	public SegmentoCst(Long id) {

		this.id = id;
	}

	public SegmentoCst(Date vigenciaInicial, Date vigenciaFinal) {

		this.vigenciaInicial = vigenciaInicial;
		this.vigenciaFinal = vigenciaFinal;
	}

	public SegmentoCst(Cst cst) {

		this.cst = cst;
	}

	public SegmentoCst(Segmento segmento) {

		this.segmento = segmento;

	}

	public SegmentoCst(Segmento segmento, Cst cst) {

		this.segmento = segmento;
		this.cst = cst;

	}

	public SegmentoCst(Segmento segmento, Estado estado, RegimeTributario regimeTributario, RamoEmpresa ramo, Cst cst) {

		this.segmento = segmento;

		this.estado = estado;

		this.regimeTributario = regimeTributario;

		this.ramoEmpresa = ramo;

		this.cst = cst;

	}

	public SegmentoCst(Segmento segmento, RegimeTributario regimeTributario, RamoEmpresa ramo) {

		this.segmento = segmento;

		this.regimeTributario = regimeTributario;

		this.ramoEmpresa = ramo;

	}

	public SegmentoCst(Segmento segmento, Estado estado, RegimeTributario regimeTributario, RamoEmpresa ramo, TipoOperacao tipoOperacao, Cst cst) {

		this.segmento = segmento;

		this.estado = estado;

		this.regimeTributario = regimeTributario;

		this.ramoEmpresa = ramo;

		this.tipoOperacao = tipoOperacao;

		this.cst = cst;

	}

	public SegmentoCst(Segmento segmento, RegimeTributario regimeTributario, RamoEmpresa ramo, TipoOperacao tipoOperacao, Cst cst) {

		this.segmento = segmento;

		this.regimeTributario = regimeTributario;

		this.ramoEmpresa = ramo;

		this.tipoOperacao = tipoOperacao;

		this.cst = cst;

	}

	public SegmentoCst(Segmento segmento, Estado estado, RegimeTributario regimeTributario, RamoEmpresa ramo) {

		this.segmento = segmento;

		this.regimeTributario = regimeTributario;

		this.ramoEmpresa = ramo;

		this.estado = estado;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Cst getCst() {
		return cst;
	}

	public void setCst(Cst cst) {
		this.cst = cst;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public RegimeTributario getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(RegimeTributario regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getAliquota() {
		return TSUtil.isEmpty(this.aliquota) ? BigDecimal.ZERO : this.aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public String getSubItemSt() {
		return TSUtil.tratarString(this.subItemSt);
	}

	public void setSubItemSt(String subItemSt) {
		this.subItemSt = subItemSt;
	}

	public BigDecimal getMvaInterno() {
		return TSUtil.isEmpty(this.mvaInterno) ? BigDecimal.ZERO : this.mvaInterno;
	}

	public void setMvaInterno(BigDecimal mvaInterno) {
		this.mvaInterno = mvaInterno;
	}

	public BigDecimal getMvaExterno() {
		return TSUtil.isEmpty(this.mvaExterno) ? BigDecimal.ZERO : this.mvaExterno;
	}

	public void setMvaExterno(BigDecimal mvaExterno) {
		this.mvaExterno = mvaExterno;
	}

	public BigDecimal getMvaImportado() {
		return TSUtil.isEmpty(this.mvaImportado) ? BigDecimal.ZERO : this.mvaImportado;
	}

	public void setMvaImportado(BigDecimal mvaImportado) {
		this.mvaImportado = mvaImportado;
	}

	public BigDecimal getBaseReduzida() {
		return TSUtil.isEmpty(this.baseReduzida) ? BigDecimal.ZERO : this.baseReduzida;
	}

	public void setBaseReduzida(BigDecimal baseReduzida) {
		this.baseReduzida = baseReduzida;
	}

	public BigDecimal getValorPauta() {
		return TSUtil.isEmpty(this.valorPauta) ? BigDecimal.ZERO : this.valorPauta;
	}

	public void setValorPauta(BigDecimal valorPauta) {
		this.valorPauta = valorPauta;
	}

	public Cest getCest() {
		return cest;
	}

	public void setCest(Cest cest) {
		this.cest = cest;
	}

	public Date getVigenciaInicial() {	
		return vigenciaInicial;
	}

	public void setVigenciaInicial(Date vigenciaInicial) {
		this.vigenciaInicial = vigenciaInicial;
	}

	public Date getVigenciaFinal() {
		return vigenciaFinal;
	}

	public void setVigenciaFinal(Date vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
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

	public RamoEmpresa getRamoEmpresa() {
		return ramoEmpresa;
	}

	public void setRamoEmpresa(RamoEmpresa ramoEmpresa) {
		this.ramoEmpresa = ramoEmpresa;
	}

	public BigDecimal getMvaOriginal() {
		return TSUtil.isEmpty(this.mvaOriginal) ? BigDecimal.ZERO : this.mvaOriginal;
	}

	public void setMvaOriginal(BigDecimal mvaOriginal) {
		this.mvaOriginal = mvaOriginal;
	}

	public BigDecimal getMvaAjustado() {
		return TSUtil.isEmpty(this.mvaAjustado) ? BigDecimal.ZERO : this.mvaAjustado;
	}

	public void setMvaAjustado(BigDecimal mvaAjustado) {
		this.mvaAjustado = mvaAjustado;
	}

	public Boolean getFlagHistorico() {
		return TSUtil.isEmpty(this.flagHistorico) ? Boolean.FALSE : this.flagHistorico;
	}

	public void setFlagHistorico(Boolean flagHistorico) {
		this.flagHistorico = flagHistorico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((ramoEmpresa == null) ? 0 : ramoEmpresa.hashCode());
		result = prime * result + ((regimeTributario == null) ? 0 : regimeTributario.hashCode());
		result = prime * result + ((tipoOperacao == null) ? 0 : tipoOperacao.hashCode());
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
		SegmentoCst other = (SegmentoCst) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (ramoEmpresa == null) {
			if (other.ramoEmpresa != null)
				return false;
		} else if (!ramoEmpresa.equals(other.ramoEmpresa))
			return false;
		if (regimeTributario == null) {
			if (other.regimeTributario != null)
				return false;
		} else if (!regimeTributario.equals(other.regimeTributario))
			return false;
		if (tipoOperacao == null) {
			if (other.tipoOperacao != null)
				return false;
		} else if (!tipoOperacao.equals(other.tipoOperacao))
			return false;
		return true;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}

	public BigDecimal getAliquotaSt() {
		return TSUtil.isEmpty(this.aliquotaSt) ? BigDecimal.ZERO : this.aliquotaSt;
	}

	public void setAliquotaSt(BigDecimal aliquotaSt) {
		this.aliquotaSt = aliquotaSt;
	}

	public Natureza getNatureza() {
		return natureza;
	}

	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}

	public String getFiltroSql() {
		return filtroSql;
	}

	public void setFiltroSql(String filtroSql) {
		this.filtroSql = filtroSql;
	}

	public BigDecimal getMvaOriginalIndustria() {
		return TSUtil.isEmpty(this.mvaOriginalIndustria) ? BigDecimal.ZERO : this.mvaOriginalIndustria;
	}

	public void setMvaOriginalIndustria(BigDecimal mvaOriginalIndustria) {
		this.mvaOriginalIndustria = mvaOriginalIndustria;
	}

	public BigDecimal getMvaOriginalAtacado() {
		return TSUtil.isEmpty(this.mvaOriginalAtacado) ? BigDecimal.ZERO : this.mvaOriginalAtacado;
	}

	public void setMvaOriginalAtacado(BigDecimal mvaOriginalAtacado) {
		this.mvaOriginalAtacado = mvaOriginalAtacado;
	}

}
