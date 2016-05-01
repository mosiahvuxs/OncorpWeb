package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.Empresa;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class EmpresaDAO implements CrudDAO<Empresa> {

	@Override
	public Empresa obter(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("empresadao.obter", model.getId(), model.getIdentificador(), model.getChaveControle());

		return (Empresa) broker.getObjectBean(Empresa.class, "id", "descricao", "flagAtivo", "jndi", "empresa.id", "identificador", "estado.id", "estado.descricao", "estado.codigo", "regimeTributario.id", "faturamento", "nomeFantasia", "chaveControle", "quantidadeDiasAutenticacao", "flagTrial", "ramo.id", "quantidadeAcessosSimultaneos", "flagArquivoXml");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> pesquisar(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO, FLAG_ATIVO, JNDI, CHAVE_CONTROLE, QUANTIDADE_DIAS_AUTENTICACAO, FLAG_TRIAL, ESTADO_ID, RAMO_EMPRESA_ID, QUANTIDADE_ACESSOS_SIMULTANEOS, FLAG_ARQUIVO_XML, PARCEIRO_ID FROM EMPRESAS WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(DESCRICAO) ILIKE ?");
		}

		sql.append(" ORDER BY DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}

		return broker.getCollectionBean(Empresa.class, "id", "descricao", "flagAtivo", "jndi", "chaveControle", "quantidadeDiasAutenticacao", "flagTrial", "estado.id", "ramo.id", "quantidadeAcessosSimultaneos", "flagArquivoXml", "parceiro.id");

	}

	@Override
	public Empresa inserir(Empresa model) throws TSApplicationException {

		return model;
	}

	@Override
	public Empresa alterar(Empresa model) throws TSApplicationException {

		return model;

	}

	@Override
	public void excluir(Empresa model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("empresadao.excluir", model.getId());

		broker.execute();

	}

}
