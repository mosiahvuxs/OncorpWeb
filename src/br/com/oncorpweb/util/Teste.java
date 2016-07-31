package br.com.oncorpweb.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Teste {

	private static final String TARGET_URL = "http://localhost:8080/OncorpWeb/tester.xhtml";

	public static void main(String[] args) {
		String response = readResponse(doHttpPost(TARGET_URL, "usernamebox=BLOB&passwordbox=password"));

		System.out.println("Response : \n" + response);
	}

	public static HttpURLConnection doHttpPost(String targetUrl, String urlEncodedContent) {
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) (new URL(targetUrl).openConnection());
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);

			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8");

			HttpURLConnection.setFollowRedirects(true);
			urlConnection.setRequestMethod("POST");

			DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());

			// throws IOException
			dataOutputStream.writeBytes(urlEncodedContent);
			dataOutputStream.flush();
			dataOutputStream.close();

			return urlConnection;

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String readResponse(HttpURLConnection urlConnection) {
		BufferedReader bufferedReader = null;
		try {

			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String responeLine;

			StringBuilder response = new StringBuilder();

			while ((responeLine = bufferedReader.readLine()) != null) {
				response.append(responeLine);
			}

			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally // closing stream
		{
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
