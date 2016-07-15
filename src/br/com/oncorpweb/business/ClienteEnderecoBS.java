package br.com.oncorpweb.business;

import javax.ejb.Stateless;

import br.com.oncorpweb.dao.ClienteEnderecoDAO;
import br.com.oncorpweb.dao.CrudDAO;
import br.com.oncorpweb.model.ClienteEndereco;

@Stateless
public class ClienteEnderecoBS extends CrudBS<ClienteEndereco> {

	private ClienteEnderecoDAO clienteEnderecoDAO = new ClienteEnderecoDAO();

	@Override
	protected CrudDAO<ClienteEndereco> getCrudDAO() {

		return this.clienteEnderecoDAO;
	}

}
