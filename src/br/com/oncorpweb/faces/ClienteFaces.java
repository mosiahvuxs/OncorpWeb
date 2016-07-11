package br.com.oncorpweb.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.ClienteEndereco;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.TipoIdentificador;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteFaces")
@ViewScoped
public class ClienteFaces implements Serializable {

	private Cliente cliente;
	private Item item;

	public ClienteFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.cliente = new Cliente(Boolean.FALSE, new TipoIdentificador(), new Usuario(), new ClienteEndereco());

	}

	public void salvar() {

		try {

			new ClienteDAO().inserir(this.cliente);

			String corpoEmail = Utilitarios.lerArquivo("/WEB-INF/templateEmails/mailing.html");

			corpoEmail = corpoEmail.replace("[cliente]", this.cliente.getNome().toUpperCase());

			String url = null;
			
			String clienteCriptografado = TSCryptoUtil.criptografar(this.cliente.getId().toString());

			String itemCriptografado = TSCryptoUtil.criptografar(this.item.getId().toString());

			if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

				url = "http://localhost:8080/OncorpWeb/confirmacao/c=" + clienteCriptografado + "&" + "i=" + itemCriptografado;

			} else {

				url = Constantes.URL_PRODUCAO + "/confirmacao/c=" + clienteCriptografado + "&" + "i=" + itemCriptografado;
			}

			corpoEmail = corpoEmail.replace("[urlConfirmacao]", url);

		} catch (TSApplicationException e) {

			e.printStackTrace();
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
