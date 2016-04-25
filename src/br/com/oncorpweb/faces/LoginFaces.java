package br.com.oncorpweb.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.oncorpweb.dao.UsuarioDAO;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Constantes;
import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@ManagedBean(name = "loginFaces")
@SessionScoped
@SuppressWarnings("serial")
public class LoginFaces extends TSMainFaces {

	private Usuario usuario;

	public LoginFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.usuario = new Usuario();
	}

	public String autenticar() {

		this.usuario.setSenha(TSCryptoUtil.gerarHash(this.usuario.getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

		Usuario usuario = new UsuarioDAO().obterPorLoginSenha(this.usuario);

		if (!TSUtil.isEmpty(usuario)) {

			if (usuario.getFlagAtivo()) {

				super.addObjectInSession(Constantes.USUARIO_CONECTADO, usuario);

				return PESQUISA;

			} else {

				TSFacesUtil.addErrorMessage("Usuário inativo.");
			}

		} else {

			TSFacesUtil.addErrorMessage("Dados inválidos.");
		}

		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
