package br.com.oncorpweb.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

@SuppressWarnings("serial")
public class TipoCst implements Serializable {

	private Long id;

	private String descricao, styleClassSelecionado;

	private Boolean flagAtivo;

	private Empresa empresa;

	private List<SegmentoCst> segmentoCsts;

	private List<SelectItem> csts;

	public TipoCst() {

	}

	public TipoCst(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public TipoCst(Boolean flagAtivo, Empresa empresa) {

		this.flagAtivo = flagAtivo;

		this.empresa = empresa;

	}

	public TipoCst(Long id) {
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
		TipoCst other = (TipoCst) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<SegmentoCst> getSegmentoCsts() {
		return segmentoCsts;
	}

	public void setSegmentoCsts(List<SegmentoCst> segmentoCsts) {
		this.segmentoCsts = segmentoCsts;
	}

	public List<SelectItem> getCsts() {
		return csts;
	}

	public void setCsts(List<SelectItem> csts) {
		this.csts = csts;
	}

	public String getStyleClassSelecionado() {
		return styleClassSelecionado;
	}

	public void setStyleClassSelecionado(String styleClassSelecionado) {
		this.styleClassSelecionado = styleClassSelecionado;
	}

}
