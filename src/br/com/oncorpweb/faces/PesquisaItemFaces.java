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
import br.com.topsys.web.util.TSFacesUtil;

@ManagedBean(name = "pesquisaItemFaces")
@ViewScoped
public class PesquisaItemFaces {

	private Item item;
	private List<Item> itens;
	private List<Paginacao> paginacao;
	private Long paginaCorrente, offSet;
	private boolean exibirDivResultado;
	private String filtro;

	public PesquisaItemFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.item = new Item(Boolean.TRUE);

		this.paginaCorrente = 1L;

		this.exibirDivResultado = false;

	}

	public String pesquisar() {

		if (TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras())) && TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

			TSFacesUtil.addErrorMessage("Favor informar um dos campos para realizar a pesquisa.");

		} else {

			this.paginaCorrente = 1L;

			this.exibirDivResultado = true;

			this.filtro = null;

			this.itens = new ArrayList<Item>();

			ItemDAO itemDAO = new ItemDAO();

			Item model = itemDAO.obterTotal(this.item);

			this.item.setTotal(0L);

			if (!TSUtil.isEmpty(model) && model.getTotal() > 0) {

				this.popularPaginacao(model.getTotal(), this.paginaCorrente);

				Long offSet = 0L;

				if (!this.paginaCorrente.equals(1L)) {

					offSet = (this.paginaCorrente - 1L) * Constantes.LIMITE_LINHAS;
				}

				this.itens = itemDAO.pesquisar(this.item, Constantes.LIMITE_LINHAS, offSet);

				this.item.setTotal(model.getTotal());

			}

			if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras()))) {

				this.filtro = this.item.getCodigoBarras();
			}

			if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

				if (!TSUtil.isEmpty(this.filtro)) {

					this.filtro = this.filtro + " E " + this.item.getDescricao();

				} else {

					this.filtro = this.item.getDescricao();
				}

			}

		}

		return null;
	}

	public String paginar() {
		
		this.item.setCodigoBarras(TSFacesUtil.getRequestParameter("codigoBarras"));
		this.item.setDescricao(TSFacesUtil.getRequestParameter("descricao"));

		this.itens = new ArrayList<Item>();

		ItemDAO itemDAO = new ItemDAO();

		Item model = itemDAO.obterTotal(this.item);

		if (!TSUtil.isEmpty(model) && model.getTotal() > 0) {
			
			this.exibirDivResultado = true;

			this.item.setTotal(model.getTotal());

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

			if (total > Constantes.LIMITE_LINHAS) {

				total = total - Constantes.LIMITE_LINHAS;

			} else {

				total = 0L;
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

	public boolean isExibirDivResultado() {
		return exibirDivResultado;
	}

	public void setExibirDivResultado(boolean exibirDivResultado) {
		this.exibirDivResultado = exibirDivResultado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
