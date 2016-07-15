package br.com.oncorpweb.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.dao.CrudDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.Grupo;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

@Stateless
public class ClienteBS extends CrudBS<Cliente> {

	@EJB
	private UsuarioBS usuarioBS;

	@EJB
	private ClienteEnderecoBS clienteEnderecoBS;

	@Override
	protected CrudDAO<Cliente> getCrudDAO() {

		return new ClienteDAO();
	}

	public Cliente inserir(Cliente model) throws TSApplicationException {

		model.setUsuario(new Usuario());
		model.getUsuario().setNome(model.getNome());
		model.getUsuario().setEmail(model.getEmail());
		model.getUsuario().setSenha(TSUtil.gerarSenha().toLowerCase());
		model.getUsuario().setGrupo(new Grupo(Constantes.GRUPO_CLIENTE_WEB));
		model.getUsuario().setFlagAtivo(Boolean.FALSE);

		model.setUsuario(this.usuarioBS.inserir(model.getUsuario()));

		model = this.getCrudDAO().inserir(model);

		model.getClienteEndereco().setCliente(model);

		model.setClienteEndereco(this.clienteEnderecoBS.inserir(model.getClienteEndereco()));

		return model;

	}

}
