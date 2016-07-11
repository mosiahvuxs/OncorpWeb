package br.com.oncorpweb.faces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.Item;
import br.com.topsys.exception.TSApplicationException;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "cobrancaFaces")
public class CobrancaFaces implements Serializable {

	private Cliente cliente;
	private Item item;
	private String parametro;

	public CobrancaFaces() {

	}

	public void iniciar() throws TSApplicationException, IOException {

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
