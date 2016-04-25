package br.com.oncorpweb.dao;

import java.util.List;

import br.com.topsys.exception.TSApplicationException;

public interface CrudDAO<T> {

	public T obter(T model);

	public List<T> pesquisar(T model);

	public T inserir(T model) throws TSApplicationException;

	public T alterar(T model) throws TSApplicationException;

	public void excluir(T model) throws TSApplicationException;

}
