package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Cliente;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ClienteDAO implements CrudDAO<Cliente> {

	@Override
	public Cliente inserir(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("clientes_id_seq"));

		broker.setPropertySQL("clientedao.inserir", model.getId(), model.getNome(), model.getIdentificador(), model.getEmail().toLowerCase(), model.getTelefone(), model.getTipoIdentificador().getId(), model.getUsuario().getId(), model.getDataNascimento(), model.getNomeContato());

		broker.execute();

		return model;
	}

	public Cliente obter(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obter", model.getId());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "nome", "identificador", "email", "telefone", "tipoIdentificador.id", "nomeContato", "dataNascimento", "clienteEndereco.id", "clienteEndereco.logradouro", "clienteEndereco.numero", "clienteEndereco.complemento", "clienteEndereco.bairro", "clienteEndereco.cidade", "clienteEndereco.estado.id", "clienteEndereco.cep", "usuario.id", "usuario.nome", "usuario.email", "usuario.flagAtivo", "usuario.grupo.id", "usuario.senha");
	}

	@Override
	public List<Cliente> pesquisar(Cliente model) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Cliente alterar(Cliente model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void excluir(Cliente model) throws TSApplicationException {
		// TODO Auto-generated method stub
	}

}
