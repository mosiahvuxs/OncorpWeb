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

	public Usuario obterPorEmail(Usuario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("usuariodao.obterPorEmail", model.getEmail().toLowerCase());

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

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("usuarios_id_seq"));

		broker.setPropertySQL("usuariodao.inserir", model.getId(), model.getEmail(), model.getFlagAtivo(), model.getLogin(), model.getNome(), model.getSenha(), model.getGrupo().getId());

		broker.execute();

		return model;
	}

	public void ativar(Usuario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE PUBLIC.USUARIOS SET FLAG_ATIVO = TRUE WHERE ID = ?");

		broker.set(model.getId());

		broker.execute();
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
