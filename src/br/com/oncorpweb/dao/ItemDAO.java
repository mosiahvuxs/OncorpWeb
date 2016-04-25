package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Item;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ItemDAO implements CrudDAO<Item> {

	public Item obterTotal(Item model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM PUBLIC.ITENS WHERE FLAG_ATIVO = ? AND EMPRESA_ID = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getDescricao()))) {

			sql.append(" AND SEM_ACENTOS(UPPER(DESCRICAO)) ILIKE SEM_ACENTOS(UPPER(?))");
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

			sql.append(" AND CODIGO_BARRAS = ?");

		}

		broker.setSQL(sql.toString(), model.getFlagAtivo());

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getDescricao()))) {

			broker.set("%" + model.getDescricao() + "%");

		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

			broker.set(model.getCodigoBarras());

		}

		return (Item) broker.getObjectBean(Item.class, "total");
	}

	@SuppressWarnings("unchecked")
	public List<Item> pesquisar(Item model, Long limit, Long offSet) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT I.ID, I.SEGMENTO_ID, I.FLAG_ATIVO, I.DESCRICAO, I.CODIGO_BARRAS, I.VALOR_UNITARIO, I.UNIDADE_MEDIDA_ID, I.CODIGO_INTERNO, I.EMPRESA_ID, I.OBSERVACAO, I.DATA_CADASTRO, I.DATA_ATUALIZACAO, I.DATA_ARQUIVO, I.ARQUIVO, I.STATUS_ID FROM PUBLIC.ITENS I WHERE I.FLAG_ATIVO = ? AND I.EMPRESA_ID = 1");

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getDescricao()))) {

			sql.append(" AND SEM_ACENTOS(UPPER(I.DESCRICAO)) ILIKE SEM_ACENTOS(UPPER(?))");
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

			sql.append(" AND I.CODIGO_BARRAS = ?");

		}

		sql.append(" ORDER BY I.DESCRICAO");

		if (!TSUtil.isEmpty(limit)) {

			sql.append(" LIMIT ?");
		}

		if (!TSUtil.isEmpty(offSet)) {

			sql.append(" OFFSET ?");
		}

		broker.setSQL(sql.toString(), model.getFlagAtivo());

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getDescricao()))) {

			broker.set("%" + model.getDescricao() + "%");

		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

			broker.set(model.getCodigoBarras());

		}

		if (!TSUtil.isEmpty(limit)) {

			broker.set(limit);
		}

		if (!TSUtil.isEmpty(offSet)) {

			broker.set(offSet);
		}

		return broker.getCollectionBean(Item.class, "id", "segmento.id", "flagAtivo", "descricao", "codigoBarras", "valorUnitario", "unidadeMedida.id", "codigoInterno", "empresa.id", "observacao", "dataCadastro", "dataAtualizacao", "dataArquivo", "arquivo", "status.id");
	}

	@Override
	public Item inserir(Item model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item alterar(Item model) throws TSApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Item model) throws TSApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public Item obter(Item model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> pesquisar(Item model) {
		// TODO Auto-generated method stub
		return null;
	}

}