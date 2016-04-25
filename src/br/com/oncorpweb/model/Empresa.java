package br.com.oncorpweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class Empresa implements Serializable {

	private Long id;
	private String descricao;
	private String nomeFantasia;
	private Boolean flagAtivo, flagTrial;
	private String jndi;
	private Estado estado;
	private Empresa empresa;
	private RegimeTributario regimeTributario;
	private BigDecimal faturamento;
	private String identificador;
	private String chaveControle;
	private Integer quantidadeDiasAutenticacao, quantidadeAcessosSimultaneos;
	private RamoEmpresa ramo;
	private List<Usuario> usuarios;
	private Boolean flagArquivoXml;

	public Empresa() {

	}

	public Empresa(String identificador) {

		this.identificador = identificador;
	}

	public Empresa(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Empresa(Long id) {
		this.id = id;
	}

	public Empresa(Long id, String identificador, String descricao) {
		this.id = id;
		this.identificador = identificador;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getJndi() {
		return jndi;
	}

	public void setJndi(String jndi) {
		this.jndi = jndi;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public RegimeTributario getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(RegimeTributario regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public BigDecimal getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(BigDecimal faturamento) {
		this.faturamento = faturamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getChaveControle() {
		return chaveControle;
	}

	public void setChaveControle(String chaveControle) {
		this.chaveControle = chaveControle;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getFlagTrial() {

		if (TSUtil.isEmpty(this.flagTrial)) {

			this.flagTrial = Boolean.FALSE;
		}

		return flagTrial;
	}

	public void setFlagTrial(Boolean flagTrial) {
		this.flagTrial = flagTrial;
	}

	public Integer getQuantidadeDiasAutenticacao() {

		if (TSUtil.isEmpty(this.quantidadeDiasAutenticacao)) {

			this.quantidadeDiasAutenticacao = 0;
		}

		return quantidadeDiasAutenticacao;
	}

	public void setQuantidadeDiasAutenticacao(Integer quantidadeDiasAutenticacao) {
		this.quantidadeDiasAutenticacao = quantidadeDiasAutenticacao;
	}

	public Integer getQuantidadeAcessosSimultaneos() {

		if (TSUtil.isEmpty(this.quantidadeAcessosSimultaneos)) {

			this.quantidadeAcessosSimultaneos = 0;
		}

		return quantidadeAcessosSimultaneos;
	}

	public void setQuantidadeAcessosSimultaneos(Integer quantidadeAcessosSimultaneos) {
		this.quantidadeAcessosSimultaneos = quantidadeAcessosSimultaneos;
	}

	public RamoEmpresa getRamo() {
		return ramo;
	}

	public void setRamo(RamoEmpresa ramo) {
		this.ramo = ramo;
	}

	public Boolean getFlagArquivoXml() {
		return flagArquivoXml;
	}

	public void setFlagArquivoXml(Boolean flagArquivoXml) {
		this.flagArquivoXml = flagArquivoXml;
	}

}
