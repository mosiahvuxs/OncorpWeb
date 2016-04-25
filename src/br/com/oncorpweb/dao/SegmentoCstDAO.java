package br.com.oncorpweb.dao;

import java.util.List;

import br.com.oncorpweb.model.SegmentoCst;
import br.com.oncorpweb.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class SegmentoCstDAO implements CrudDAO<SegmentoCst> {

	@Override
	public SegmentoCst inserir(SegmentoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("segmento_cst_id_seq"));

		broker.setPropertySQL("segmentocstdao.inserir", model.getId(), model.getSegmento().getId(), model.getCst().getId(), model.getTipoOperacao().getId(), model.getAliquota(), model.getRegimeTributario().getId(), model.getEstado().getId(), model.getLeiInterno(), model.getLeiLinkInterno(), model.getLeiSaida(), model.getLeiLinkSaida(), model.getSubItemSt(), model.getMvaInterno(), model.getMvaExterno(), model.getMvaImportado(), model.getMvaOriginalIndustria(), model.getMvaOriginalAtacado(), model.getBaseReduzida(), model.getCest().getId(), model.getValorPauta(), model.getRamoEmpresa().getId(), model.getVigenciaInicial(), model.getVigenciaFinal(), model.getMvaOriginal(), model.getMvaAjustado(), model.getAliquotaSt(), model.getNatureza().getId());

		broker.execute();

		return model;

	}

	@Override
	public SegmentoCst alterar(SegmentoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("segmentocstdao.alterar", model.getSegmento().getId(), model.getCst().getId(), model.getTipoOperacao().getId(), model.getAliquota(), model.getRegimeTributario().getId(), model.getEstado().getId(), model.getLeiInterno(), model.getLeiLinkInterno(), model.getLeiSaida(), model.getLeiLinkSaida(), model.getSubItemSt(), model.getMvaInterno(), model.getMvaExterno(), model.getMvaImportado(), model.getMvaOriginalIndustria(), model.getMvaOriginalAtacado(), model.getBaseReduzida(), model.getCest().getId(), model.getValorPauta(), model.getRamoEmpresa().getId(), model.getVigenciaInicial(), model.getVigenciaFinal(), model.getMvaOriginal(), model.getMvaAjustado(), model.getAliquotaSt(), model.getNatureza().getId(), model.getId());

		broker.execute();

		return model;
	}

	@Override
	public void excluir(SegmentoCst model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("segmentocstdao.excluir", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<SegmentoCst> pesquisar(SegmentoCst model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT TIC.ID, TIC.SEGMENTO_ID, TIC.CST_ID, CS.CODIGO, CS.DESCRICAO, CS.TIPO_CST_ID, TC.DESCRICAO, TIC.TIPO_OPERACAO_ID, TOP.DESCRICAO, TIC.ALIQUOTA, TIC.REGIME_TRIBUTARIO_ID, RT.DESCRICAO, TIC.ESTADO_ID, ES.DESCRICAO, TIC.LEI_INTERNO, TIC.LEI_LINK_INTERNO, TIC.LEI_SAIDA, TIC.LEI_LINK_SAIDA, TIC.SUBITEM_ST, TIC.MVA_INTERNO, TIC.MVA_EXTERNO, TIC.MVA_IMPORTADO, TIC.MVA_ORIGINAL_INDUSTRIA, TIC.MVA_ORIGINAL_ATACADO, TIC.BASE_REDUZIDA, C.ID, C.CODIGO, C.DESCRICAO, TIC.VALOR_PAUTA, TIC.RAMO_EMPRESA_ID, RE.DESCRICAO, TIC.VIGENCIA_INICIAL, TIC.VIGENCIA_FINAL, TIC.MVA_ORIGINAL, TIC.MVA_AJUSTADO, TIC.ALIQUOTA_ST, TIC.NATUREZA_ID, NAT.DESCRICAO, NAT.CODIGO FROM PUBLIC.SEGMENTO_CST TIC LEFT OUTER JOIN CEST C ON C.ID = TIC.CEST_ID LEFT OUTER JOIN ESTADOS ES ON ES.ID = TIC.ESTADO_ID LEFT OUTER JOIN NATUREZAS NAT ON NAT.ID = TIC.NATUREZA_ID INNER JOIN CST CS ON CS.ID = TIC.CST_ID INNER JOIN TIPO_CST TC ON TC.ID = CS.TIPO_CST_ID INNER JOIN RAMO_EMPRESA RE ON RE.ID = TIC.RAMO_EMPRESA_ID INNER JOIN REGIME_TRIBUTARIOS RT ON RT.ID = TIC.REGIME_TRIBUTARIO_ID INNER JOIN TIPO_OPERACOES TOP ON TOP.ID = TIC.TIPO_OPERACAO_ID WHERE 1 = 1 ");

		if (!TSUtil.isEmpty(model.getSegmento()) && !TSUtil.isEmpty(model.getSegmento().getId())) {

			sql.append(" AND TIC.SEGMENTO_ID = ?");

		}

		if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getId())) {

			sql.append(" AND TIC.CST_ID = ?");

		}

		if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getTipo()) && !TSUtil.isEmpty(model.getCst().getTipo().getId())) {

			sql.append(" AND CS.TIPO_CST_ID = ?");

			if (!model.getCst().getTipo().getId().equals(Constantes.TIPO_CST_ICMS)) {

				model.setOrdenacao(" ORDER BY RE.DESCRICAO, RT.DESCRICAO, TOP.DESCRICAO, CS.CODIGO");

			} else {

				if (!TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

					sql.append(" AND TIC.ESTADO_ID = ?");

				}

				model.setOrdenacao(" ORDER BY RE.DESCRICAO, RT.DESCRICAO, TOP.DESCRICAO, ES.DESCRICAO, CS.CODIGO");
			}

		} else {

			model.setOrdenacao(" ORDER BY TIC.ID");

		}

		if (!TSUtil.isEmpty(model.getTipoOperacao()) && !TSUtil.isEmpty(model.getTipoOperacao().getId())) {

			sql.append(" AND TIC.TIPO_OPERACAO_ID = ?");

		}

		if (!TSUtil.isEmpty(model.getRegimeTributario()) && !TSUtil.isEmpty(model.getRegimeTributario().getId())) {

			sql.append(" AND TIC.REGIME_TRIBUTARIO_ID = ?");

		}

		if (!TSUtil.isEmpty(model.getRamoEmpresa()) && !TSUtil.isEmpty(model.getRamoEmpresa().getId())) {

			sql.append(" AND TIC.RAMO_EMPRESA_ID = ?");

		}

		if (!TSUtil.isEmpty(model.getFiltroSql())) {

			sql.append(model.getFiltroSql());
		}

		sql.append(model.getOrdenacao());

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getSegmento()) && !TSUtil.isEmpty(model.getSegmento().getId())) {

			broker.set(model.getSegmento().getId());

		}

		if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getId())) {

			broker.set(model.getCst().getId());

		}

		if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getTipo()) && !TSUtil.isEmpty(model.getCst().getTipo().getId())) {

			broker.set(model.getCst().getTipo().getId());

			if (model.getCst().getTipo().getId().equals(Constantes.TIPO_CST_ICMS) && !TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

				broker.set(model.getEstado().getId());

			}

		}

		if (!TSUtil.isEmpty(model.getTipoOperacao()) && !TSUtil.isEmpty(model.getTipoOperacao().getId())) {

			broker.set(model.getTipoOperacao().getId());

		}

		if (!TSUtil.isEmpty(model.getRegimeTributario()) && !TSUtil.isEmpty(model.getRegimeTributario().getId())) {

			broker.set(model.getRegimeTributario().getId());

		}

		if (!TSUtil.isEmpty(model.getRamoEmpresa()) && !TSUtil.isEmpty(model.getRamoEmpresa().getId())) {

			broker.set(model.getRamoEmpresa().getId());

		}

		return broker.getCollectionBean(SegmentoCst.class, "id", "segmento.id", "cst.id", "cst.codigo", "cst.descricao", "cst.tipo.id", "cst.tipo.descricao", "tipoOperacao.id", "tipoOperacao.descricao", "aliquota", "regimeTributario.id", "regimeTributario.descricao", "estado.id", "estado.descricao", "leiInterno", "leiLinkInterno", "leiSaida", "leiLinkSaida", "subItemSt", "mvaInterno", "mvaExterno", "mvaImportado", "mvaOriginalIndustria", "mvaOriginalAtacado", "baseReduzida", "cest.id", "cest.codigo", "cest.descricao", "valorPauta", "ramoEmpresa.id", "ramoEmpresa.descricao", "vigenciaInicial", "vigenciaFinal", "mvaOriginal", "mvaAjustado", "aliquotaSt", "natureza.id", "natureza.descricao", "natureza.codigo");
	}

	@Override
	public SegmentoCst obter(SegmentoCst model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT TIC.ID, TIC.SEGMENTO_ID, TIC.CST_ID, CS.CODIGO, CS.DESCRICAO, CS.TIPO_CST_ID, TC.DESCRICAO, TIC.TIPO_OPERACAO_ID, TOP.DESCRICAO , TIC.ALIQUOTA, TIC.REGIME_TRIBUTARIO_ID, RT.DESCRICAO, TIC.ESTADO_ID, ES.DESCRICAO, TIC.LEI_INTERNO, TIC.LEI_LINK_INTERNO, TIC.LEI_SAIDA, TIC.LEI_LINK_SAIDA, TIC.SUBITEM_ST, TIC.MVA_INTERNO, TIC.MVA_EXTERNO, TIC.MVA_IMPORTADO, TIC.MVA_ORIGINAL_INDUSTRIA, TIC.MVA_ORIGINAL_ATACADO, TIC.BASE_REDUZIDA, C.ID, C.CODIGO, C.CODIGO, TIC.VALOR_PAUTA, TIC.RAMO_EMPRESA_ID, RE.DESCRICAO, TIC.VIGENCIA_INICIAL, TIC.VIGENCIA_FINAL, TIC.MVA_ORIGINAL, TIC.MVA_AJUSTADO, TIC.ALIQUOTA_ST, TIC.NATUREZA_ID FROM PUBLIC.SEGMENTO_CST TIC LEFT OUTER JOIN CEST C ON C.ID = TIC.CEST_ID LEFT OUTER JOIN ESTADOS ES ON ES.ID = TIC.ESTADO_ID INNER JOIN CST CS ON CS.ID = TIC.CST_ID INNER JOIN TIPO_CST TC ON TC.ID = CS.TIPO_CST_ID INNER JOIN RAMO_EMPRESA RE ON RE.ID = TIC.RAMO_EMPRESA_ID INNER JOIN REGIME_TRIBUTARIOS RT ON RT.ID = TIC.REGIME_TRIBUTARIO_ID INNER JOIN TIPO_OPERACOES TOP ON TOP.ID = TIC.TIPO_OPERACAO_ID");

		if (!TSUtil.isEmpty(model.getId())) {

			sql.append(" AND TIC.ID = ?");

		} else {

			if (!TSUtil.isEmpty(model.getSegmento()) && !TSUtil.isEmpty(model.getSegmento().getId())) {

				sql.append(" AND TIC.SEGMENTO_ID = ?");

			}

			if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getId())) {

				sql.append(" AND CS.ID = ?");

			}

			if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getTipo()) && !TSUtil.isEmpty(model.getCst().getTipo().getId())) {

				sql.append(" AND CS.TIPO_CST_ID = ?");

			}

			if (!TSUtil.isEmpty(model.getTipoOperacao()) && !TSUtil.isEmpty(model.getTipoOperacao().getId())) {

				sql.append(" AND TIC.TIPO_OPERACAO_ID = ?");

			}

			if (!TSUtil.isEmpty(model.getRegimeTributario()) && !TSUtil.isEmpty(model.getRegimeTributario().getId())) {

				sql.append(" AND TIC.REGIME_TRIBUTARIO_ID = ?");

			}

			if (!TSUtil.isEmpty(model.getRamoEmpresa()) && !TSUtil.isEmpty(model.getRamoEmpresa().getId())) {

				sql.append(" AND TIC.RAMO_EMPRESA_ID = ?");

			}

			if (!TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

				sql.append(" AND TIC.ESTADO_ID = ?");

			}

		}

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getId())) {

			broker.set(model.getId());

		} else {

			if (!TSUtil.isEmpty(model.getSegmento()) && !TSUtil.isEmpty(model.getSegmento().getId())) {

				broker.set(model.getSegmento().getId());

			}

			if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getId())) {

				broker.set(model.getCst().getId());

			}

			if (!TSUtil.isEmpty(model.getCst()) && !TSUtil.isEmpty(model.getCst().getTipo()) && !TSUtil.isEmpty(model.getCst().getTipo().getId())) {

				broker.set(model.getCst().getTipo().getId());

			}

			if (!TSUtil.isEmpty(model.getTipoOperacao()) && !TSUtil.isEmpty(model.getTipoOperacao().getId())) {

				broker.set(model.getTipoOperacao().getId());

			}

			if (!TSUtil.isEmpty(model.getRegimeTributario()) && !TSUtil.isEmpty(model.getRegimeTributario().getId())) {

				broker.set(model.getRegimeTributario().getId());

			}

			if (!TSUtil.isEmpty(model.getRamoEmpresa()) && !TSUtil.isEmpty(model.getRamoEmpresa().getId())) {

				broker.set(model.getRamoEmpresa().getId());

			}

			if (!TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

				broker.set(model.getEstado().getId());

			}

		}

		return (SegmentoCst) broker.getObjectBean(SegmentoCst.class, "id", "segmento.id", "cst.id", "cst.codigo", "cst.descricao", "cst.tipo.id", "cst.tipo.descricao", "tipoOperacao.id", "tipoOperacao.descricao", "aliquota", "regimeTributario.id", "regimeTributario.descricao", "estado.id", "estado.descricao", "leiInterno", "leiLinkInterno", "leiSaida", "leiLinkSaida", "subItemSt", "mvaInterno", "mvaExterno", "mvaImportado", "mvaOriginalIndustria", "mvaOriginalAtacado", "baseReduzida", "cest.id", "cest.codigo", "cest.descricao", "valorPauta", "ramoEmpresa.id", "ramoEmpresa.descricao", "vigenciaInicial", "vigenciaFinal", "mvaOriginal", "mvaAjustado", "aliquotaSt", "natureza.id");
	}

}
