package br.com.oncorpweb.business;

import javax.ejb.Stateless;

import br.com.oncorpweb.dao.CrudDAO;
import br.com.oncorpweb.dao.UsuarioDAO;
import br.com.oncorpweb.model.Usuario;
import br.com.topsys.exception.TSApplicationException;

@Stateless
public class UsuarioBS extends CrudBS<Usuario> {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	protected CrudDAO<Usuario> getCrudDAO() {

		return this.usuarioDAO;
	}
	
	public void ativar(Usuario model) throws TSApplicationException{
		
		this.usuarioDAO.ativar(model);
	}

}
