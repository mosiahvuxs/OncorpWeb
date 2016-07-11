package br.com.oncorpweb.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.ClienteEndereco;
import br.com.oncorpweb.model.TipoIdentificador;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteFaces")
@ViewScoped
public class ClienteFaces implements Serializable {

	private Cliente cliente;

	public ClienteFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.cliente = new Cliente(new TipoIdentificador(), new Usuario(), new ClienteEndereco());

	}

	public void salvar() {

		try {

			new ClienteDAO().inserir(this.cliente);

			String corpoEmail = Utilitarios.lerArquivo("/WEB-INF/templateEmails/mailing.html");

			corpoEmail = corpoEmail.replace("[cliente]", this.cliente.getNome().toUpperCase());

			corpoEmail = corpoEmail.replace("[urlConfirmacao]", "");

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
