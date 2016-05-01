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

		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM PUBLIC.ITENS WHERE FLAG_ATIVO = ? AND EMPRESA_ID = ?");

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getDescricao()))) {

			sql.append(" AND SEM_ACENTOS(UPPER(DESCRICAO)) ILIKE SEM_ACENTOS(UPPER(?))");
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

			sql.append(" AND CODIGO_BARRAS = ?");

		}

		broker.setSQL(sql.toString(), model.getFlagAtivo(), model.getEmpresa().getId());

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

		sql.append("SELECT I.ID, I.SEGMENTO_ID, I.FLAG_ATIVO, I.DESCRICAO, I.CODIGO_BARRAS, I.VALOR_UNITARIO, I.UNIDADE_MEDIDA_ID, I.CODIGO_INTERNO, I.EMPRESA_ID, I.OBSERVACAO, I.DATA_CADASTRO, I.DATA_ATUALIZACAO, I.DATA_ARQUIVO, I.ARQUIVO, I.STATUS_ID FROM PUBLIC.ITENS I WHERE I.FLAG_ATIVO = ? AND I.EMPRESA_ID = ?");

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

		broker.setSQL(sql.toString(), model.getFlagAtivo(), model.getEmpresa().getId());

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
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT I.ID, I.SEGMENTO_ID, SEG.CODIGO, SEG.DESCRICAO, SEG.GRUPO_SEGMENTO_ID, GS.DESCRICAO, GS.CODIGO, NC.ID, NC.DESCRICAO, NC.CODIGO, NC.CODIGO_EXCECAO, GNC.ID, GNC.DESCRICAO, GNC.CODIGO, I.FLAG_ATIVO, I.DESCRICAO, I.CODIGO_BARRAS, I.VALOR_UNITARIO, I.UNIDADE_MEDIDA_ID, I.CODIGO_INTERNO, I.EMPRESA_ID, I.OBSERVACAO, I.DATA_CADASTRO, I.DATA_ATUALIZACAO, I.DATA_ARQUIVO, I.ARQUIVO, I.STATUS_ID FROM PUBLIC.ITENS I LEFT OUTER JOIN SEGMENTO SEG ON SEG.ID = I.SEGMENTO_ID LEFT OUTER JOIN GRUPO_SEGMENTO GS ON GS.ID = SEG.GRUPO_SEGMENTO_ID LEFT OUTER JOIN NCM NC ON NC.ID = SEG.NCM_ID LEFT OUTER JOIN GRUPO_NCM GNC ON GNC.ID = NC.GRUPO_NCM_ID WHERE I.EMPRESA_ID = ?");

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND I.ID = ?");

		} else {

			if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

				sql.append(" AND I.CODIGO_BARRAS = ?");

			}

		}

		broker.setSQL(sql.toString(), model.getEmpresa().getId());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());

		} else {

			if (!TSUtil.isEmpty(TSUtil.tratarString(model.getCodigoBarras()))) {

				broker.set(model.getCodigoBarras());

			}

		}

		return (Item) broker.getObjectBean(Item.class, "id", "segmento.id", "segmento.codigo", "segmento.descricao", "segmento.grupoSegmento.id", "segmento.grupoSegmento.descricao", "segmento.grupoSegmento.codigo", "segmento.ncm.id", "segmento.ncm.descricao", "segmento.ncm.codigo", "segmento.ncm.codigoExcecao", "segmento.ncm.grupoNcm.id", "segmento.ncm.grupoNcm.descricao", "segmento.ncm.grupoNcm.codigo", "flagAtivo", "descricao", "codigoBarras", "valorUnitario", "unidadeMedida.id", "codigoInterno", "empresa.id", "observacao", "dataCadastro", "dataAtualizacao", "dataArquivo", "arquivo", "status.id");
	}

	@Override
	public List<Item> pesquisar(Item model) {
		// TODO Auto-generated method stub
		return null;
	}

}