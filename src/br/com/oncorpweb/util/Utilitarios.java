package br.com.oncorpweb.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import br.com.topsys.util.TSUtil;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static String formatarUrl(String input) {

		String nowhitespace = Pattern.compile("[\\s]").matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = Pattern.compile("[^\\w-]").matcher(normalized).replaceAll("");

		return slug.toLowerCase(Locale.getDefault());
	}

	public static Long getId(String parametro) {

		if (!TSUtil.isEmpty(TSUtil.tratarString(parametro)) && parametro.matches(".*\\d.*")) {

			if (parametro.contains("-")) {

				String[] id = parametro.split("-");

				if (!TSUtil.isEmpty(id) && TSUtil.isNumeric(id[0])) {

					return new Long(id[0]);
				}

			} else {

				return new Long(parametro);
			}

		}

		return null;
	}

}
