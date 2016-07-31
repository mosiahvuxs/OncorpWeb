package br.com.oncorpweb.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.SessionService;

/**
 * Servlet implementation class CobrancaPagSeguro
 */
@WebServlet("/CobrancaPagSeguro")
public class CobrancaPagSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CobrancaPagSeguro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "http://localhost:8080/OncorpWeb/tester.xhtml";

		WebDriver unitDriver = new HtmlUnitDriver();
		unitDriver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);
		
		// unitDriver.setJavascriptEnabled(true);

		// WebDriver unitDriver = new FirefoxDriver();

		unitDriver.get(url);

		WebElement form = unitDriver.findElement(By.id("form"));

		form.click();

		// button = unitDriver.findElement(By.id("enviarDados"));

		// button.click();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
