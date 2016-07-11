package br.com.oncorpweb.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.oncorpweb.dao.ClienteDAO;
import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@RequestScoped
@ManagedBean(name = "confirmacaoFaces")
public class ConfirmacaoFaces implements Serializable {

	private Cliente cliente;
	private Item item;
	private String parametro;

	public ConfirmacaoFaces() {

	}

	public void iniciar() throws TSApplicationException, IOException {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.parametro)) && this.parametro.contains("c") && this.parametro.contains("i")) {

			String[] params = this.parametro.split("&");

			if (!TSUtil.isEmpty(params) && TSUtil.isNumeric(params[0]) && TSUtil.isNumeric(params[1])) {

				ClienteDAO clienteDAO = new ClienteDAO();

				Cliente cliente = clienteDAO.obter(new Cliente(new Long(params[0])));

				Item item = new ItemDAO().obter(new Item(new Long(params[1])));

				if (!TSUtil.isEmpty(cliente) && !TSUtil.isEmpty(item)) {

					if (!cliente.getFlagAtivo()) {

						clienteDAO.ativar(cliente);
					}

					if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

						TSFacesUtil.getFacesContext().getExternalContext().redirect("http://localhost:8080/OncorpWeb/" + "/cobranca/" + this.parametro);

					} else {

						TSFacesUtil.getFacesContext().getExternalContext().redirect(Constantes.URL_PRODUCAO + "cobranca/" + this.parametro);
					}

				} else {

					Utilitarios.redirectIndex();
				}

			}

		}
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
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

}
