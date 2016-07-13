package br.com.oncorpweb.faces;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.ClienteEndereco;
import br.com.oncorpweb.model.Estado;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.TipoIdentificador;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteFaces")
@ViewScoped
public class ClienteFaces implements Serializable {

	private Cliente cliente;
	private Item item;
	private boolean exibirDivOk, exibirDivErro;

	public ClienteFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.cliente = new Cliente(Boolean.FALSE, new TipoIdentificador(Constantes.PESSOA_FISICA), new Usuario(), new ClienteEndereco(new Estado()));
		this.exibirDivOk = false;
		this.exibirDivErro = false;
	}

	public String setarPessoa() {

		String tipoPessoa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipoPessoa");

		if (!TSUtil.isEmpty(tipoPessoa)) {

			this.cliente.getTipoIdentificador().setId(new Long(tipoPessoa));

		}

		return null;

	}

	private boolean validarCampos() {

		boolean validado = true;

		this.exibirDivErro = false;
		this.exibirDivOk = false;

		if (Constantes.PESSOA_FISICA.equals(this.cliente.getTipoIdentificador().getId())) {

			if (TSUtil.isEmpty(this.cliente.getNome())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getIdentificador()) || this.cliente.getIdentificador().length() < 11) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getNascimento())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getEmail()) || !TSUtil.isEmailValid(this.cliente.getEmail())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getTelefone())) {

				validado = false;
			}

		} else {

			if (TSUtil.isEmpty(this.cliente.getNome())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getIdentificador()) || this.cliente.getIdentificador().length() < 11) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getNomeContato())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getEmail()) || !TSUtil.isEmailValid(this.cliente.getEmail())) {

				validado = false;
			}

			if (TSUtil.isEmpty(this.cliente.getTelefone())) {

				validado = false;
			}

		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getLogradouro())) {

			validado = false;
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getBairro())) {

			validado = false;
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getCidade())) {

			validado = false;
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getEstado().getId())) {

			validado = false;
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getCep())) {

			validado = false;
		}

		if (!validado) {

			this.exibirDivErro = true;
		}

		return validado;
	}

	public void salvar() {

		if (this.validarCampos()) {

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

				this.exibirDivOk = true;

			} catch (TSApplicationException e) {

				e.printStackTrace();
			}
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isExibirDivOk() {
		return exibirDivOk;
	}

	public void setExibirDivOk(boolean exibirDivOk) {
		this.exibirDivOk = exibirDivOk;
	}

	public boolean isExibirDivErro() {
		return exibirDivErro;
	}

	public void setExibirDivErro(boolean exibirDivErro) {
		this.exibirDivErro = exibirDivErro;
	}

}
