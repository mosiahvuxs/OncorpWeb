package br.com.oncorpweb.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.oncorpweb.business.UsuarioBS;
import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@RequestScoped
@ManagedBean(name = "confirmacaoFaces")
public class ConfirmacaoFaces implements Serializable {

	@EJB
	private UsuarioBS usuarioBS;
	private String parametro;

	public ConfirmacaoFaces() {

	}

	public void iniciar() throws TSApplicationException, IOException {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.parametro)) && this.parametro.contains("clienteId=")) {

			String[] params = this.parametro.split("clienteId=");

			if (!TSUtil.isEmpty(params[0])) {

				String clienteCriptografado = params[0];

				String clienteDescriptografado = TSCryptoUtil.desCriptografar(clienteCriptografado);

				if (!TSUtil.isEmpty(clienteDescriptografado) && TSUtil.isNumeric(clienteDescriptografado)) {

					Cliente cliente = new ClienteDAO().obter(new Cliente(new Long(clienteDescriptografado)));

					String itemCriptografado = null;

					String itemDescriptografado = null;

					if (!TSUtil.isEmpty(cliente)) {

						if (!cliente.getUsuario().getFlagAtivo()) {

							this.usuarioBS.ativar(cliente.getUsuario());
						}

						TSFacesUtil.addObjectInSession(Constantes.USUARIO_CONECTADO, cliente.getUsuario());

						if (this.parametro.contains("&itemId=")) {

							Item item = null;

							params = this.parametro.split("&itemId=");

							if (!TSUtil.isEmpty(params[0])) {

								itemCriptografado = params[0];

								itemDescriptografado = TSCryptoUtil.desCriptografar(itemCriptografado);

								if (!TSUtil.isEmpty(itemDescriptografado) && TSUtil.isNumeric(itemDescriptografado)) {

									item = new ItemDAO().obter(new Item(new Long(itemDescriptografado)));

									if (!TSUtil.isEmpty(item)) {

										Utilitarios.redirectCobranca(clienteCriptografado, itemCriptografado);
									
									} else {
										
										Utilitarios.redirectPesquisa();
									}

								}

							}

						} else {

							Utilitarios.redirectPesquisa();
						}

					} else {

						Utilitarios.redirectIndex();
					}

				}
			}

		} else {

			Utilitarios.redirectIndex();
		}
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
