package br.com.oncorpweb.faces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.EmpresaDAO;
import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.dao.SegmentoCstDAO;
import br.com.oncorpweb.dao.TipoCstDAO;
import br.com.oncorpweb.model.Cst;
import br.com.oncorpweb.model.Empresa;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.SegmentoCst;
import br.com.oncorpweb.model.TipoCst;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.util.TSUtil;

@ManagedBean(name = "detalhesFaces")
@ViewScoped
public class DetalhesFaces {

	private Item item;
	private String parametro;
	private List<TipoCst> tipoCsts;
	private SegmentoCst segmentoCst;
	private Empresa empresa;

	public DetalhesFaces() {

		this.empresa = new EmpresaDAO().obter(new Empresa(Constantes.EMPRESA_MASTER));

	}

	public void obter() {

		Long id = Utilitarios.getId(this.parametro);

		if (!TSUtil.isEmpty(id)) {

			this.item = new ItemDAO().obter(new Item(id, this.empresa));

			if (!TSUtil.isEmpty(this.item)) {

				this.tipoCsts = new TipoCstDAO().pesquisar(new TipoCst());

				if (!TSUtil.isEmpty(this.tipoCsts) && !TSUtil.isEmpty(this.item.getSegmento()) && !TSUtil.isEmpty(this.item.getSegmento().getId())) {

					List<TipoCst> tipoCsts = new ArrayList<>();

					tipoCsts.addAll(this.tipoCsts);

					if (!TSUtil.isEmpty(this.item.getSegmento().getId())) {

						SegmentoCst model = new SegmentoCst(this.item.getSegmento());

						model.setRamoEmpresa(this.empresa.getRamo());
						model.setRegimeTributario(this.empresa.getRegimeTributario());
						model.setCst(new Cst());

						for (TipoCst tipoCst : tipoCsts) {

							model.getCst().setTipo(tipoCst);

							if (tipoCst.getId().equals(Constantes.TIPO_CST_ICMS)) {

								model.setEstado(this.empresa.getEstado());
							}

							tipoCst.setSegmentoCsts(new SegmentoCstDAO().pesquisar(model));

						}

						this.tipoCsts = new ArrayList<TipoCst>();

						for (TipoCst tipoCstAux : tipoCsts) {

							if (!TSUtil.isEmpty(tipoCstAux.getSegmentoCsts())) {

								this.tipoCsts.add(tipoCstAux);

							}
						}

						if (!TSUtil.isEmpty(this.tipoCsts)) {

							this.tipoCsts.get(0).setStyleClassSelecionado("selecionado");
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

		} else {

			try {

				Utilitarios.redirectPesquisa();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public String listener() {

		this.getSegmentoCst();

		return null;

	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public List<TipoCst> getTipoCsts() {
		return tipoCsts;
	}

	public void setTipoCsts(List<TipoCst> tipoCsts) {
		this.tipoCsts = tipoCsts;
	}

	public SegmentoCst getSegmentoCst() {
		return segmentoCst;
	}

	public void setSegmentoCst(SegmentoCst segmentoCst) {
		this.segmentoCst = segmentoCst;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
