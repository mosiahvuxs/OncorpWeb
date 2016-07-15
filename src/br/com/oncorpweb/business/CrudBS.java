package br.com.oncorpweb.business;

import java.io.Serializable;
import java.util.List;

import br.com.oncorpweb.dao.CrudDAO;
import br.com.topsys.exception.TSApplicationException;

public abstract class CrudBS<T extends Serializable> {

	public CrudBS() {

	}

	protected abstract CrudDAO<T> getCrudDAO();

	public List<T> pesquisar(T model) {
		return this.getCrudDAO().pesquisar(model);
	}

	public T obter(T model) {
		return this.getCrudDAO().obter(model);
	}

	public T inserir(T model) throws TSApplicationException {
		return this.getCrudDAO().inserir(model);
	}

	public T alterar(T model) throws TSApplicationException {
		return this.getCrudDAO().alterar(model);
	}

	public void excluir(T model) throws TSApplicationException {
		getCrudDAO().excluir(model);
	}

}
