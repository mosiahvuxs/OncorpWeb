package br.com.oncorpweb.faces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.Paginacao;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
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
	private String filtro, urlFiltro;

	public PesquisaItemFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.item = new Item(Boolean.TRUE);

		this.paginaCorrente = 1L;

		this.exibirDivResultado = false;

	}

	public String pesquisar() {

		this.itens = new ArrayList<Item>();

		if (TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras())) && TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

			TSFacesUtil.addErrorMessage("Favor informar um dos campos para realizar a pesquisa.");

		} else {

			if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao())) && this.item.getDescricao().length() > 3) {

				this.paginaCorrente = 1L;

				this.exibirDivResultado = true;

				this.filtro = null;

				this.urlFiltro = null;

				ItemDAO itemDAO = new ItemDAO();

				Item model = itemDAO.obterTotal(this.item);

				this.item.setTotal(0L);

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras()))) {

					this.filtro = this.item.getCodigoBarras();

					this.urlFiltro = "&codigoBarras=" + this.item.getCodigoBarras();
				}

				if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

					if (!TSUtil.isEmpty(this.filtro)) {

						this.filtro = this.filtro + " E " + this.item.getDescricao();

						this.urlFiltro = this.urlFiltro + "&descricao=" + this.item.getDescricao();

					} else {

						this.filtro = this.item.getDescricao();

						this.urlFiltro = "&descricao=" + this.item.getDescricao();
					}

				}

				if (!TSUtil.isEmpty(model) && model.getTotal() > 0) {
					
					if(model.getTotal() > 100L){
						
						this.item.setTotal(100L);
					
					} else {
						
						this.item.setTotal(model.getTotal());
					}

					this.popularPaginacao(model.getTotal(), this.paginaCorrente, this.urlFiltro);

					Long offSet = 0L;

					if (!this.paginaCorrente.equals(1L)) {

						offSet = (this.paginaCorrente - 1L) * Constantes.LIMITE_LINHAS;
					}

					this.itens = itemDAO.pesquisar(this.item, Constantes.LIMITE_LINHAS, offSet);

					

				}

			} else {

				TSFacesUtil.addErrorMessage("O campo Descrição deve ter mais de 3 caracteres.");
			}

		}

		return null;
	}

	public String paginar() {

		this.itens = new ArrayList<Item>();

		ItemDAO itemDAO = new ItemDAO();

		if (!TSUtil.isEmpty(this.urlFiltro)) {

			String[] filtros = this.urlFiltro.split("&");

			if (!TSUtil.isEmpty(filtros)) {

				String pagina = filtros[0].substring(filtros[0].lastIndexOf("=") + 1);

				if (TSUtil.isNumeric(pagina) && new Integer(pagina) > 0) {

					this.paginaCorrente = new Long(pagina);

					if (filtros.length > 0) {

						for (int i = 0; i < filtros.length; i++) {

							if (filtros[i].contains("descricao=")) {

								this.item.setDescricao(filtros[i].substring(filtros[i].lastIndexOf("=") + 1));
							}
						}

						for (int i = 0; i < filtros.length; i++) {

							if (filtros[i].contains("codigoBarras=")) {

								this.item.setCodigoBarras(filtros[i].substring(filtros[i].lastIndexOf("=") + 1));
							}
						}

					}

					if (TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras())) && TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

						try {

							Utilitarios.redirectPesquisa();

						} catch (IOException e) {

							e.printStackTrace();
						}

					} else {

						Item model = itemDAO.obterTotal(this.item);

						if (!TSUtil.isEmpty(model) && model.getTotal() > 0) {

							this.exibirDivResultado = true;

							if(model.getTotal() > 100L){
								
								this.item.setTotal(100L);
							
							} else {
								
								this.item.setTotal(model.getTotal());
							}

							this.urlFiltro = "";

							if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getCodigoBarras()))) {

								this.urlFiltro = "&codigoBarras=" + this.item.getCodigoBarras();
							}

							if (!TSUtil.isEmpty(TSUtil.tratarString(this.item.getDescricao()))) {

								if (!TSUtil.isEmpty(this.urlFiltro)) {

									this.urlFiltro = this.urlFiltro + "&descricao=" + this.item.getDescricao();

								} else {

									this.urlFiltro = "&descricao=" + this.item.getDescricao();
								}

							}

							this.popularPaginacao(model.getTotal(), this.paginaCorrente, this.urlFiltro);

							Long offSet = 0L;

							if (!this.paginaCorrente.equals(1L)) {

								offSet = (this.paginaCorrente - 1L) * Constantes.LIMITE_LINHAS;
							}

							this.itens = itemDAO.pesquisar(this.item, Constantes.LIMITE_LINHAS, offSet);

						}
					}
				}

			} else {

				try {

					Utilitarios.redirectPesquisa();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}

		return null;
	}

	private void popularPaginacao(Long total, Long page, String urlFitro) {

		this.paginacao = new ArrayList<Paginacao>();

		int count = 1;

		while (total > 0) {

			Paginacao model = new Paginacao();

			model.setPagina(new Long(count));

			model.setUrl(model.getPagina() + "-" + urlFitro);

			if (model.getPagina().equals(page)) {

				model.setCurrent(true);

				this.paginaCorrente = new Long(model.getPagina());
			}

			this.paginacao.add(model);

			count++;

			if (this.paginacao.size() == 10) {

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

	public String getUrlFiltro() {
		return urlFiltro;
	}

	public void setUrlFiltro(String urlFiltro) {
		this.urlFiltro = urlFiltro;
	}
}
