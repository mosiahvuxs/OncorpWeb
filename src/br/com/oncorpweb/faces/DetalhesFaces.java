package br.com.oncorpweb.faces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oncorpweb.dao.ItemDAO;
import br.com.oncorpweb.dao.SegmentoCstDAO;
import br.com.oncorpweb.dao.TipoCstDAO;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.SegmentoCst;
import br.com.oncorpweb.model.TipoCst;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.util.TSUtil;

@ManagedBean(name = "detalhesFaces")
@ViewScoped
public class DetalhesFaces {

	private Item item;
	private String parametro;
	private List<TipoCst> tipoCsts;

	public void obter() {

		Long id = Utilitarios.getId(this.parametro);

		if (!TSUtil.isEmpty(id)) {

			this.item = new ItemDAO().obter(new Item(id));

			if (!TSUtil.isEmpty(this.item)) {

				this.tipoCsts = new TipoCstDAO().pesquisar(new TipoCst());

				if (!TSUtil.isEmpty(this.tipoCsts) && !TSUtil.isEmpty(this.item.getSegmento()) && !TSUtil.isEmpty(this.item.getSegmento().getId())) {

					List<SegmentoCst> segmentoCsts = new SegmentoCstDAO().pesquisar(new SegmentoCst(this.item.getSegmento()));

					if (!TSUtil.isEmpty(segmentoCsts)) {

						for (TipoCst tipoCst : tipoCsts) {

							tipoCst.setSegmentoCsts(new ArrayList<>());

							for (SegmentoCst segmentoCst : segmentoCsts) {

								if (tipoCst.getId().equals(segmentoCst.getCst().getTipo().getId())) {

									tipoCst.getSegmentoCsts().add(segmentoCst);

								} else if (tipoCst.getId().equals(segmentoCst.getCst().getTipo().getId())) {

									tipoCst.getSegmentoCsts().add(segmentoCst);

								} else if (tipoCst.getId().equals(segmentoCst.getCst().getTipo().getId())) {

									tipoCst.getSegmentoCsts().add(segmentoCst);

								} else if (tipoCst.getId().equals(segmentoCst.getCst().getTipo().getId())) {

									tipoCst.getSegmentoCsts().add(segmentoCst);
								}

							}
						}
					}

				}

			} else {

				try {

					Utilitarios.redirectIndex();

				} catch (IOException e) {

					e.printStackTrace();
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

}
