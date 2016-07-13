package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Cliente;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ClienteDAO implements CrudDAO<Cliente> {

	public Cliente obter(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obter", model.getId());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "email", "flagAtivo", "login", "nome", "senha", "dataNascimento", "nomeContato");
	}

	public Cliente obterPorLogin(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obterPorLogin", model.getUsuario().getLogin().toLowerCase());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "email", "flagAtivo", "login", "nome", "senha", "dataNascimento", "nomeContato");
	}

	public Cliente obterPorLoginSenha(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obterPorLoginSenha", model.getUsuario().getLogin(), model.getUsuario().getSenha());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "email", "flagAtivo", "login", "nome", "senha", "dataNascimento", "nomeContato");
	}

	@Override
	public List<Cliente> pesquisar(Cliente model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente inserir(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("clientes_id_seq"));

		broker.setPropertySQL("clientedao.inserir", model.getId(), model.getNome(), model.getIdentificador(), model.getEmail().toLowerCase(), model.getTelefone(), model.getTipoIdentificador().getId(), null, model.getFlagAtivo(), model.getDataNascimento(), model.getNomeContato());

		broker.execute();

		model.getClienteEndereco().setCliente(model);

		new ClienteEnderecoDAO().inserir(model.getClienteEndereco(), broker);

		broker.endTransaction();

		return model;
	}

	@Override
	public Cliente alterar(Cliente model) throws TSApplicationException {
		
		// TODO Auto-generated method stub
		
		return model;
	}

	public void ativar(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("UPDATE PUBLIC.CLIENTES SET FLAG_ATIVO = TRUE WHERE ID = ?");

		broker.set(model.getId());

		broker.execute();
	}

	@Override
	public void excluir(Cliente model) throws TSApplicationException {
		// TODO Auto-generated method stub

	}

}
