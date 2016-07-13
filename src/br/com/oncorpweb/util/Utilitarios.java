package br.com.oncorpweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;

import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

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
	
	@SuppressWarnings("static-access")
	public static void redirectIndex() throws IOException{
		
		ExternalContext externalContext = TSFacesUtil.getFacesContext().getCurrentInstance().getExternalContext(); 
		
		externalContext.redirect(externalContext.getRequestContextPath() + "/");
	}
	
	@SuppressWarnings("static-access")
	public static void redirectPesquisa() throws IOException{
		
		ExternalContext externalContext = TSFacesUtil.getFacesContext().getCurrentInstance().getExternalContext(); 
		
		externalContext.redirect(externalContext.getRequestContextPath() + "/pesquisa");
	}
	
	public static byte[] getBytes(File file) {

		int len = (int) file.length();

		byte[] sendBuf = new byte[len];

		FileInputStream inFile = null;

		try {

			inFile = new FileInputStream(file);

			inFile.read(sendBuf, 0, len);

		} catch (FileNotFoundException fnfex) {

			fnfex.printStackTrace();

		} catch (IOException ioex) {

			ioex.printStackTrace();
		}

		return sendBuf;
	}
	
	public static String lerArquivo(String caminhoArquivo) {

		try {

			return new String(Utilitarios.getBytes(new File(TSFacesUtil.getServletContext().getRealPath("") + caminhoArquivo)), "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return null;

	}
	
	public static boolean isValidDate(String data) {

		if (TSUtil.isEmpty(TSUtil.tratarString(data))) {

			return false;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(TSDateUtil.DD_MM_YYYY);

		if (data.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}

		dateFormat.setLenient(false);

		try {

			dateFormat.parse(data.trim());

		} catch (ParseException pe) {

			return false;
		}

		return true;
	}

}
