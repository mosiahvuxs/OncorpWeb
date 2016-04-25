package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Usuario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class UsuarioDAO implements CrudDAO<Usuario> {

	public Usuario obter(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obter", model.getId());

		return (Usuario) broker.getObjectBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha");
	}

	public Usuario obterPorLogin(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterPorLogin", model.getLogin().toLowerCase());

		return (Usuario) broker.getObjectBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha");
	}

	public Usuario obterPorLoginSenha(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterPorLoginSenha", model.getLogin(), model.getSenha());

		return (Usuario) broker.getObjectBean(Usuario.class, "id", "email", "flagAtivo", "login", "nome", "senha");
	}

	@Override
	public List<Usuario> pesquisar(Usuario model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario inserir(Usuario model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario alterar(Usuario model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Usuario model) throws TSApplicationException {
		// TODO Auto-generated method stub
		
	}

}
