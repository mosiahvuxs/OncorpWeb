package br.com.oncorpweb.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Paginacao implements Serializable {

	private Long pagina, inicio, fim;
	private boolean current, next, nextPage;
	private String url;

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pagina == null) ? 0 : pagina.hashCode());
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
		Paginacao other = (Paginacao) obj;
		if (pagina == null) {
			if (other.pagina != null)
				return false;
		} else if (!pagina.equals(other.pagina))
			return false;
		return true;
	}

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public Long getInicio() {
		return inicio;
	}

	public void setInicio(Long inicio) {
		this.inicio = inicio;
	}

	public Long getFim() {
		return fim;
	}

	public void setFim(Long fim) {
		this.fim = fim;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
