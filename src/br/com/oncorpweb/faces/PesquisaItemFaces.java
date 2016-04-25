package br.com.oncorpweb.faces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.Paginacao;
import br.com.oncorpweb.util.Constantes;
import br.com.topsys.util.TSUtil;

@ManagedBean(name = "pesquisaItemFaces")
@ViewScoped
public class PesquisaItemFaces {

	private Item item;
	private List<Item> itens;
	private List<Paginacao> paginacao;
	private Long paginaCorrente, offSet;

	public PesquisaItemFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.item = new Item(Boolean.TRUE);

		this.paginaCorrente = 1L;

	}

	public String pesquisar() {

		this.itens = new ArrayList<Item>();

		ItemDAO itemDAO = new ItemDAO();

		Item model = itemDAO.obterTotal(this.item);

		if (!TSUtil.isEmpty(model) && model.getTotal() > 0) {

			this.popularPaginacao(model.getTotal(), this.paginaCorrente);

			Long offSet = 0L;

			if (!this.paginaCorrente.equals(1L)) {

				offSet = (this.paginaCorrente - 1L) * Constantes.LIMITE_LINHAS;
			}

			this.itens = itemDAO.pesquisar(this.item, Constantes.LIMITE_LINHAS, offSet);

		}

		return null;
	}

	private void popularPaginacao(Long total, Long page) {

		this.paginacao = new ArrayList<Paginacao>();

		int count = 1;

		while (total > 0) {

			Paginacao model = new Paginacao();

			model.setPagina(new Long(count));

			if (model.getPagina().equals(page)) {

				model.setCurrent(true);

				this.paginaCorrente = new Long(model.getPagina());
			}

			this.paginacao.add(model);

			count++;

			if (this.paginacao.size() == Constantes.LIMITE_LINHAS.intValue()) {

				total = 0L;

			} else {

				if (total > Constantes.LIMITE_LINHAS) {

					total = total - Constantes.LIMITE_LINHAS;

				} else {

					total = 0L;
				}
			}

		}
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Paginacao> getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(List<Paginacao> paginacao) {
		this.paginacao = paginacao;
	}

	public Long getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente(Long paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	public Long getOffSet() {
		return offSet;
	}

	public void setOffSet(Long offSet) {
		this.offSet = offSet;
	}
}
