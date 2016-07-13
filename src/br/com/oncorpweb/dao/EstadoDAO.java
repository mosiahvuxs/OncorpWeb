package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Estado;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class EstadoDAO implements CrudDAO<Estado> {

	@SuppressWarnings("unchecked")
	public List<Estado> pesquisar(Estado model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("estadodao.pesquisar", model.getPais().getId());

		return broker.getCollectionBean(Estado.class, "id", "descricao", "flagAtivo", "codigo", "pais.id");

	}

	public Estado obter(Estado model) {

		StringBuilder sql = new StringBuilder("SELECT ID, DESCRICAO, FLAG_ATIVO, CODIGO, PAIS_ID FROM ESTADOS WHERE 1 = 1 ");

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND ID = ? ");

		} else {

			if (!TSUtil.isEmpty(model.getCodigo()) && !TSUtil.isEmpty(model.getPais()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getPais().getId()))) {

				sql.append(" AND CODIGO = ? AND PAIS_ID = ? ");

			}

		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());

		} else {

			if (!TSUtil.isEmpty(model.getCodigo()) && !TSUtil.isEmpty(model.getPais()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getPais().getId()))) {

				broker.set(model.getCodigo(), model.getPais().getId());

			}

		}

		return (Estado) broker.getObjectBean(Estado.class, "id", "descricao", "flagAtivo", "codigo", "pais.id");

	}

	public void excluir(Estado model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("estadodao.excluir", model.getId());

		broker.execute();

	}

	public Estado inserir(Estado model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("estados_id_seq"));

		broker.setPropertySQL("estadodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getCodigo().toUpperCase(), model.getPais().getId());

		broker.execute();

		return model;

	}

	public Estado alterar(Estado model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("estadodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getCodigo().toUpperCase(), model.getId());

		broker.execute();

		return model;

	}

	public void excluirTudo(Estado model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("estadodao.excluirTudo", model.getPais().getId());

		broker.execute();

	}

}
