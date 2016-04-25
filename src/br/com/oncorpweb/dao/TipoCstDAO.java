package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.TipoCst;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class TipoCstDAO implements CrudDAO<TipoCst> {

	@SuppressWarnings("unchecked")
	public List<TipoCst> pesquisar(TipoCst model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO, FLAG_ATIVO FROM TIPO_CST WHERE FLAG_ATIVO = TRUE ORDER BY ORDEM");

		broker.setSQL(sql.toString());

		return broker.getCollectionBean(TipoCst.class, "id", "descricao", "flagAtivo");
	}

	public TipoCst obter(TipoCst model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipocstdao.obter", model.getId());

		return (TipoCst) broker.getObjectBean(TipoCst.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(TipoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipocstdao.excluir", model.getId());

		broker.execute();

	}

	public TipoCst inserir(TipoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_cst_id_seq"));

		broker.setPropertySQL("tipocstdao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}

	public TipoCst alterar(TipoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipocstdao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

		return model;

	}

}
