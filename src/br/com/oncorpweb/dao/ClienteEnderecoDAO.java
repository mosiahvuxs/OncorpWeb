package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.ClienteEndereco;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ClienteEnderecoDAO implements CrudDAO<ClienteEndereco> {

	public ClienteEndereco obter(ClienteEndereco model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clienteenderecodao.obter", model.getId());

		return (ClienteEndereco) broker.getObjectBean(ClienteEndereco.class, "id", "cep", "logradouro", "numero", "complemento", "bairro", "cidade", "estado.id", "cliente.id");
	}

	@Override
	public List<ClienteEndereco> pesquisar(ClienteEndereco model) {

		// TODO Auto-generated method stub
		
		return null;
	}

	public ClienteEndereco inserir(ClienteEndereco model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("cliente_enderecos_id_seq"));

		broker.setPropertySQL("clienteenderecodao.inserir", model.getId(), model.getCep(), model.getLogradouro(), model.getNumero(), model.getComplemento(), model.getBairro(), model.getCidade(), model.getEstado().getId(), model.getCliente().getId());

		broker.execute();

		return model;
	}

	@Override
	public ClienteEndereco alterar(ClienteEndereco model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(ClienteEndereco model) throws TSApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public ClienteEndereco inserir(ClienteEndereco model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
