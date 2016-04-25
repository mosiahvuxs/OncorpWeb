package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Segmento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class SegmentoDAO implements CrudDAO<Segmento> {

	@SuppressWarnings("unchecked")
	public List<Segmento> pesquisar(Segmento model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT TI.ID, UPPER(TI.DESCRICAO), TI.FLAG_ATIVO, TI.CODIGO, N.ID, N.GRUPO_NCM_ID, N.CODIGO, N.DESCRICAO, TI.GRUPO_SEGMENTO_ID FROM PUBLIC.SEGMENTO TI INNER JOIN NCM N ON N.ID = TI.NCM_ID WHERE TI.FLAG_ATIVO = ?");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(UPPER(TI.DESCRICAO)) ILIKE SEM_ACENTOS(UPPER(?))");
		}

		if (!TSUtil.isEmpty(model.getCodigo())) {

			sql.append(" AND TI.CODIGO = ?");
		}

		if (!TSUtil.isEmpty(model.getGrupoSegmento()) && !TSUtil.isEmpty(model.getGrupoSegmento().getId())) {

			sql.append(" AND TI.GRUPO_SEGMENTO_ID = ?");
		}

		sql.append(" ORDER BY TI.DESCRICAO");

		broker.setSQL(sql.toString(), model.getFlagAtivo());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}

		if (!TSUtil.isEmpty(model.getCodigo())) {

			broker.set(model.getCodigo());
		}

		if (!TSUtil.isEmpty(model.getGrupoSegmento()) && !TSUtil.isEmpty(model.getGrupoSegmento().getId())) {

			broker.set(model.getGrupoSegmento().getId());
		}

		return broker.getCollectionBean(Segmento.class, "id", "descricao", "flagAtivo", "codigo", "ncm.id", "ncm.grupoNcm.id", "ncm.codigo", "ncm.descricao", "grupoSegmento.id");
	}

	public Segmento obter(Segmento model) {

		StringBuilder sql = new StringBuilder("SELECT TI.ID, TI.DESCRICAO, TI.FLAG_ATIVO, TI.CODIGO, N.ID, N.GRUPO_NCM_ID, GN.DESCRICAO, N.CODIGO, N.DESCRICAO, TI.GRUPO_SEGMENTO_ID, GS.CODIGO, GS.DESCRICAO FROM PUBLIC.SEGMENTO TI INNER JOIN NCM N ON N.ID = TI.NCM_ID INNER JOIN GRUPO_NCM GN ON GN.ID = N.GRUPO_NCM_ID INNER JOIN GRUPO_SEGMENTO GS ON GS.ID = TI.GRUPO_SEGMENTO_ID WHERE 1 = 1");

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND TI.ID = ? ");

		} else {

			if (!TSUtil.isEmpty(model.getCodigo())) {

				sql.append(" AND TI.CODIGO = ? ");

			}

		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());

		} else {

			if (!TSUtil.isEmpty(model.getCodigo())) {

				broker.set(model.getCodigo());

			}

		}

		return (Segmento) broker.getObjectBean(Segmento.class, "id", "descricao", "flagAtivo", "codigo", "ncm.id", "ncm.grupoNcm.id", "ncm.grupoNcm.descricao", "ncm.codigo", "ncm.descricao", "grupoSegmento.id", "grupoSegmento.codigo", "grupoSegmento.descricao");

	}

	public void excluir(Segmento model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("segmentodao.excluir", model.getId());

		broker.execute();

	}

	public Segmento inserir(Segmento model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("segmento_id_seq"));

		broker.setPropertySQL("segmentodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getCodigo(), model.getNcm().getId(), model.getGrupoSegmento().getId());

		broker.execute();

		return model;

	}

	public Segmento alterar(Segmento model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("segmentodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getCodigo(), model.getNcm().getId(), model.getGrupoSegmento().getId(), model.getId());

		broker.execute();

		return model;

	}

}
