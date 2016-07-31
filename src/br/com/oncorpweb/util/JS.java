package br.com.oncorpweb.util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.script.ScriptException;

public class JS {

	public static void main(String[] args) throws IOException, ScriptException {
		// TODO Auto-generated method stub

		// String s =
		// "https://stc.pagseguro.uol.com.br/pagseguro/api/v2/checkout/pagseguro.directpayment.js";

		URL url = new URL("https://stc.pagseguro.uol.com.br/pagseguro/api/v2/checkout/pagseguro.directpayment.js");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

		// System.out.println(br.readLine());

		FileWriter arq = new FileWriter("d:\\teste\\pagseguroproducao.js");
		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.print(br.readLine());

		arq.close();

	}

}
