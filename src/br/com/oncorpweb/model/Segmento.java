package br.com.oncorpweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@XmlRootElement(name = "segmento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Segmento implements Serializable {

	private Long id;
	private String descricao;
	
	@XmlTransient
	private Boolean flagAtivo;
	
	private String codigo;
	
	@XmlElementWrapper(name = "segmentoCsts")
	@XmlElement(name = "segmentoCst")		
	private List<SegmentoCst> segmentoCsts;
	
	@XmlTransient	
	private Date vigenciaFinal;
	
	private Ncm ncm;
	
	private GrupoSegmento grupoSegmento;

	public Segmento() {

	}

	public Segmento(String codigo) {
		this.codigo = codigo;
	}

	
	public Segmento(Boolean flagAtivo) {

		this.flagAtivo = flagAtivo;

	}

	public Segmento(GrupoSegmento grupoSegmento, Boolean flagAtivo) {

		this.grupoSegmento = grupoSegmento;
		this.flagAtivo = flagAtivo;

	}

	public Segmento(GrupoSegmento grupoSegmento) {

		this.grupoSegmento = grupoSegmento;

	}

	public Segmento(Long id) {
		this.id =id;
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

	public List<SegmentoCst> getSegmentoCsts() {
		return segmentoCsts;
	}

	public void setSegmentoCsts(List<SegmentoCst> segmentoCsts) {
		this.segmentoCsts = segmentoCsts;
	}

	public Date getVigenciaFinal() {
		return vigenciaFinal;
	}

	public void setVigenciaFinal(Date vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
	}

	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public GrupoSegmento getGrupoSegmento() {
		return grupoSegmento;
	}

	public void setGrupoSegmento(GrupoSegmento grupoSegmento) {
		this.grupoSegmento = grupoSegmento;
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
		Segmento other = (Segmento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
