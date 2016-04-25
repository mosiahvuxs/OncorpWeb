package br.com.oncorpweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.oncorpweb.util.Constantes;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
public class Item implements Serializable {

	private Long id, total;
	private String descricao;
	private Segmento segmento;
	private Boolean flagAtivo;
	private String codigoBarras, erro, token, arquivo, caminhoArquivo, caminhoArquivoMobile;
	private Ncm ncm;
	private BigDecimal valorUnitario, mvaInterno, mvaExterno, mvaImportado, baseReduzida, precoMedioCusto, precoMedioVenda, mvaOriginalIndustria, mvaOriginalAtacado, mvaOriginal, mvaAjustado, valorPauta;
	private UnidadeMedida unidadeMedida;
	private String codigoInterno;
	private Boolean flagImportado;
	private Boolean selecionado;
	private String mensagemErro;
	private Status status;

	private Empresa empresa;
	private Boolean flagExcluir, flagNovo, flagLeitura, flagReadOnly;
	private String codigoBarrasInicial, codigoBarrasFinal;
	private String observacao;
	private Date dataCadastro, dataAtualizacao, dataArquivo, dataAtualizacaoInicial, dataAtualizacaoFinal;

	private String subItemSt;
	private Usuario usuario;

	private Cest cest;
	private List<SegmentoCst> segmentoCstIcmsSaidas, segmentoCstIcmsEntradas;

	private Cst cstPisSaida;
	private Cst cstCofinsSaida;
	private Cst cstIcmsSaida;
	private Cst cstIpiSaida;

	private Cst cstPisEntrada;
	private Cst cstCofinsEntrada;
	private Cst cstIcmsEntrada;
	private Cst cstIpiEntrada;
	private Natureza natureza;

	private Long quantidade;

	public Item() {

	}

	public Item(Segmento segmento) {

		this.segmento = segmento;

	}

	public Item(Segmento segmento, Boolean flagAtivo) {

		this.segmento = segmento;
		this.flagAtivo = flagAtivo;

	}

	public Item(Usuario usuario, Empresa empresa, String token, Boolean flagAtivo) {

		this.usuario = usuario;
		this.empresa = empresa;
		this.token = token;
		this.flagAtivo = flagAtivo;
	}

	public Item(Usuario usuario, Empresa empresa, String token, String codigoBarras, String descricao, Boolean flagAtivo) {

		this.usuario = usuario;
		this.empresa = empresa;
		this.token = token;
		this.codigoBarras = codigoBarras;
		this.descricao = descricao;
		this.flagAtivo = flagAtivo;
	}
	
	public Item(Usuario usuario, Empresa empresa, String token, String codigoBarras, String descricao, Boolean flagAtivo, String codigoInterno) {

		this.usuario = usuario;
		this.empresa = empresa;
		this.token = token;
		this.codigoBarras = codigoBarras;
		this.descricao = descricao;
		this.flagAtivo = flagAtivo;
		this.codigoInterno = codigoInterno;
	}

	public Item(Empresa empresa) {

		this.empresa = empresa;
	}

	public Item(Boolean flagAtivo, Empresa empresa) {

		this.flagAtivo = flagAtivo;

		this.empresa = empresa;

	}

	public Item(Long id) {
		this.id = id;
	}

	public Item(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Item(Boolean flagAtivo, Empresa empresa, String codigoBarras) {
		this.flagAtivo = flagAtivo;

		this.empresa = empresa;

		this.codigoBarras = codigoBarras;
	}

	public Item(Long id, Empresa empresa) {
		this.empresa = empresa;

		this.id = id;
	}
	
	public Item(String codigoBarras, Empresa empresa) {
		this.empresa = empresa;

		this.codigoBarras = codigoBarras;
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

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public BigDecimal getValorUnitario() {
		return TSUtil.isEmpty(this.valorUnitario) ? BigDecimal.ZERO : this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Boolean getFlagImportado() {
		return flagImportado;
	}

	public void setFlagImportado(Boolean flagImportado) {
		this.flagImportado = flagImportado;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getDescricaoFormatada() {

		if (!TSUtil.isEmpty(this.codigoBarras) && !TSUtil.isEmpty(this.descricao)) {

			return this.codigoBarras + "-" + this.descricao;

		} else {

			return "";
		}

	}

	public Boolean getSelecionado() {
		return TSUtil.isEmpty(this.selecionado) ? Boolean.FALSE : this.selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Boolean getFlagExcluir() {
		return flagExcluir;
	}

	public void setFlagExcluir(Boolean flagExcluir) {
		this.flagExcluir = flagExcluir;
	}

	public Boolean getFlagNovo() {
		return flagNovo;
	}

	public void setFlagNovo(Boolean flagNovo) {
		this.flagNovo = flagNovo;
	}

	public String getCodigoBarrasFinal() {
		return codigoBarrasFinal;
	}

	public void setCodigoBarrasFinal(String codigoBarrasFinal) {
		this.codigoBarrasFinal = codigoBarrasFinal;
	}

	public String getCodigoBarrasInicial() {
		return codigoBarrasInicial;
	}

	public void setCodigoBarrasInicial(String codigoBarrasInicial) {
		this.codigoBarrasInicial = codigoBarrasInicial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public BigDecimal getMvaExterno() {

		if (TSUtil.isEmpty(mvaExterno)) {

			this.mvaExterno = BigDecimal.ZERO;
		}

		return mvaExterno;
	}

	public void setMvaExterno(BigDecimal mvaExterno) {
		this.mvaExterno = mvaExterno;
	}

	public BigDecimal getBaseReduzida() {

		if (TSUtil.isEmpty(baseReduzida)) {

			this.baseReduzida = BigDecimal.ZERO;
		}

		return baseReduzida;
	}

	public void setBaseReduzida(BigDecimal baseReduzida) {
		this.baseReduzida = baseReduzida;
	}

	public BigDecimal getMvaInterno() {

		if (TSUtil.isEmpty(mvaInterno)) {

			this.mvaInterno = BigDecimal.ZERO;
		}

		return mvaInterno;
	}

	public void setMvaInterno(BigDecimal mvaInterno) {
		this.mvaInterno = mvaInterno;
	}

	public BigDecimal getMvaImportado() {

		if (TSUtil.isEmpty(mvaImportado)) {

			this.mvaImportado = BigDecimal.ZERO;
		}

		return mvaImportado;
	}

	public void setMvaImportado(BigDecimal mvaImportado) {
		this.mvaImportado = mvaImportado;
	}

	public String getSubItemSt() {
		return subItemSt;
	}

	public void setSubItemSt(String subItemSt) {
		this.subItemSt = subItemSt;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getCaminhoArquivo() {

		if (!TSUtil.isEmpty(this.arquivo) && !TSUtil.isEmpty(this.dataArquivo)) {

			this.caminhoArquivo = Constantes.PASTA_ARQUIVOS + TSUtil.getAnoMes(this.dataArquivo);

		}

		return caminhoArquivo;
	}

	public String getCaminhoArquivoFormatado() {

		if (!TSUtil.isEmpty(this.arquivo) && !TSUtil.isEmpty(this.dataArquivo)) {

			if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

				this.caminhoArquivo = Constantes.PASTA_ARQUIVOS + TSUtil.getAnoMes(this.dataArquivo) + this.arquivo;

			} else {

				this.caminhoArquivo = Constantes.FW_EXIBICAO + TSUtil.getAnoMes(this.dataArquivo) + this.arquivo;
			}

		}

		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}

	public Date getDataArquivo() {
		return dataArquivo;
	}

	public void setDataArquivo(Date dataArquivo) {
		this.dataArquivo = dataArquivo;
	}

	public String getCaminhoArquivoMobile() {
		return caminhoArquivoMobile;
	}

	public void setCaminhoArquivoMobile(String caminhoArquivoMobile) {
		this.caminhoArquivoMobile = caminhoArquivoMobile;
	}

	public Cest getCest() {
		return cest;
	}

	public void setCest(Cest cest) {
		this.cest = cest;
	}

	public List<SegmentoCst> getSegmentoCstIcmsSaidas() {
		return segmentoCstIcmsSaidas;
	}

	public void setSegmentoCstIcmsSaidas(List<SegmentoCst> segmentoCstIcmsSaidas) {
		this.segmentoCstIcmsSaidas = segmentoCstIcmsSaidas;
	}

	public List<SegmentoCst> getSegmentoCstIcmsEntradas() {
		return segmentoCstIcmsEntradas;
	}

	public void setSegmentoCstIcmsEntradas(List<SegmentoCst> segmentoCstIcmsEntradas) {
		this.segmentoCstIcmsEntradas = segmentoCstIcmsEntradas;
	}

	public Cst getCstPisSaida() {
		return cstPisSaida;
	}

	public void setCstPisSaida(Cst cstPisSaida) {
		this.cstPisSaida = cstPisSaida;
	}

	public Cst getCstCofinsSaida() {
		return cstCofinsSaida;
	}

	public void setCstCofinsSaida(Cst cstCofinsSaida) {
		this.cstCofinsSaida = cstCofinsSaida;
	}

	public Cst getCstIcmsSaida() {
		return cstIcmsSaida;
	}

	public void setCstIcmsSaida(Cst cstIcmsSaida) {
		this.cstIcmsSaida = cstIcmsSaida;
	}

	public Cst getCstIpiSaida() {
		return cstIpiSaida;
	}

	public void setCstIpiSaida(Cst cstIpiSaida) {
		this.cstIpiSaida = cstIpiSaida;
	}

	public Cst getCstPisEntrada() {
		return cstPisEntrada;
	}

	public void setCstPisEntrada(Cst cstPisEntrada) {
		this.cstPisEntrada = cstPisEntrada;
	}

	public Cst getCstCofinsEntrada() {
		return cstCofinsEntrada;
	}

	public void setCstCofinsEntrada(Cst cstCofinsEntrada) {
		this.cstCofinsEntrada = cstCofinsEntrada;
	}

	public Cst getCstIcmsEntrada() {
		return cstIcmsEntrada;
	}

	public void setCstIcmsEntrada(Cst cstIcmsEntrada) {
		this.cstIcmsEntrada = cstIcmsEntrada;
	}

	public Cst getCstIpiEntrada() {
		return cstIpiEntrada;
	}

	public void setCstIpiEntrada(Cst cstIpiEntrada) {
		this.cstIpiEntrada = cstIpiEntrada;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public BigDecimal getPrecoMedioCusto() {
		return TSUtil.isEmpty(this.precoMedioCusto) ? BigDecimal.ZERO : this.precoMedioCusto;
	}

	public void setPrecoMedioCusto(BigDecimal precoMedioCusto) {
		this.precoMedioCusto = precoMedioCusto;
	}

	public BigDecimal getPrecoMedioVenda() {
		return TSUtil.isEmpty(this.precoMedioVenda) ? BigDecimal.ZERO : this.precoMedioVenda;
	}

	public void setPrecoMedioVenda(BigDecimal precoMedioVenda) {
		this.precoMedioVenda = precoMedioVenda;
	}

	public BigDecimal getMvaOriginal() {
		return mvaOriginal;
	}

	public void setMvaOriginal(BigDecimal mvaOriginal) {
		this.mvaOriginal = mvaOriginal;
	}

	public BigDecimal getMvaAjustado() {
		return mvaAjustado;
	}

	public void setMvaAjustado(BigDecimal mvaAjustado) {
		this.mvaAjustado = mvaAjustado;
	}

	public BigDecimal getValorPauta() {
		return valorPauta;
	}

	public void setValorPauta(BigDecimal valorPauta) {
		this.valorPauta = valorPauta;
	}

	public Natureza getNatureza() {
		return natureza;
	}

	public void setNatureza(Natureza natureza) {
		this.natureza = natureza;
	}

	public Date getDataAtualizacaoInicial() {
		return dataAtualizacaoInicial;
	}

	public void setDataAtualizacaoInicial(Date dataAtualizacaoInicial) {
		this.dataAtualizacaoInicial = dataAtualizacaoInicial;
	}

	public Date getDataAtualizacaoFinal() {
		return dataAtualizacaoFinal;
	}

	public void setDataAtualizacaoFinal(Date dataAtualizacaoFinal) {
		this.dataAtualizacaoFinal = dataAtualizacaoFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBarras == null) ? 0 : codigoBarras.hashCode());
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
		Item other = (Item) obj;
		if (codigoBarras == null) {
			if (other.codigoBarras != null)
				return false;
		} else if (!codigoBarras.equals(other.codigoBarras))
			return false;
		return true;
	}

	public Boolean getFlagLeitura() {
		return TSUtil.isEmpty(this.flagLeitura) ? Boolean.FALSE : this.flagLeitura;
	}

	public void setFlagLeitura(Boolean flagLeitura) {
		this.flagLeitura = flagLeitura;
	}

	public Boolean getFlagReadOnly() {
		return TSUtil.isEmpty(this.flagReadOnly) ? Boolean.FALSE : this.flagReadOnly;
	}

	public void setFlagReadOnly(Boolean flagReadOnly) {
		this.flagReadOnly = flagReadOnly;
	}

	public Long getQuantidade() {
		return TSUtil.isEmpty(this.quantidade) ? 0L : this.quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getMvaOriginalIndustria() {
		return mvaOriginalIndustria;
	}

	public void setMvaOriginalIndustria(BigDecimal mvaOriginalIndustria) {
		this.mvaOriginalIndustria = mvaOriginalIndustria;
	}

	public BigDecimal getMvaOriginalAtacado() {
		return mvaOriginalAtacado;
	}

	public void setMvaOriginalAtacado(BigDecimal mvaOriginalAtacado) {
		this.mvaOriginalAtacado = mvaOriginalAtacado;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}