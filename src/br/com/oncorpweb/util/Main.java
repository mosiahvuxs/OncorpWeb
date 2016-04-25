package br.com.oncorpweb.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(formatarUrl("6667 Informações do produto R$ 6.000,90"));

	}
	
	public static String formatarUrl(String input) {

		String nowhitespace = Pattern.compile("[\\s]").matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = Pattern.compile("[^\\w-]").matcher(normalized).replaceAll("");

		return slug.toLowerCase(Locale.getDefault());
	}

}
