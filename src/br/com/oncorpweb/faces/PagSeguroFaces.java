package br.com.oncorpweb.faces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import br.com.topsys.web.util.TSFacesUtil;
import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Address;
import br.com.uol.pagseguro.domain.Document;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Phone;
import br.com.uol.pagseguro.domain.Sender;
import br.com.uol.pagseguro.domain.SenderDocument;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.direct.Holder;
import br.com.uol.pagseguro.domain.direct.checkout.CreditCardCheckout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.PaymentMode;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.SessionService;
import br.com.uol.pagseguro.service.TransactionSearchService;
import br.com.uol.pagseguro.service.TransactionService;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "pagSeguroFaces")
public class PagSeguroFaces implements Serializable {

	private String sessionId, senderHash, hashTokenCartao;

	public PagSeguroFaces() throws IOException, ScriptException, NoSuchMethodException {

		try {

			AccountCredentials accountCredentials = PagSeguroConfig.getAccountCredentials();

			final String sessionId = SessionService.createSession(accountCredentials);

			if (sessionId != null) {

				this.sessionId = sessionId;
			}

			String transactionCode = "28C18DB0-567B-4350-B418-2010550A2F13";

			Transaction transaction = null;

			try {

				transaction = TransactionSearchService.searchByCode(PagSeguroConfig.getAccountCredentials(), transactionCode);

			} catch (PagSeguroServiceException e) {
				System.err.println(e.getMessage());
			}

			if (transaction != null) {
				System.out.println("reference: " + transaction.getReference());
				System.out.println("status: " + transaction.getStatus());
			}

		} catch (PagSeguroServiceException e) {

			e.printStackTrace();
		}

	}

	public void checkoutCartaoCredito() {

		CreditCardCheckout request = new CreditCardCheckout();

		request.setPaymentMode(PaymentMode.DEFAULT);

		request.setReceiverEmail("pagseguro@oncorp.srv.br");

		request.setCurrency(Currency.BRL);

		request.setNotificationURL("http://oncorpfiscal.com.br/oncorpweb/notificacaoTransacao");

		request.setReference("REF12345");

		// request.setSender(new Sender("Moisés Rocha",
		// "c56498561304790552773@sandbox.pagseguro.com.br"));

		request.setSender(new Sender("Rolando Lero", //
				"moisessrocha@gmail.com", //
				new Phone("71", "33336666"), //
				new SenderDocument(DocumentType.CPF, "000.000.001-91")));

		request.setSenderHash(this.senderHash);

		request.addItem(new Item("1", "Notebook Prata", Integer.valueOf(1), new BigDecimal("2500.00")));
		request.addItem(new Item("2", "Notebook Rosa", Integer.valueOf(1), new BigDecimal("2500.00")));

		request.setCreditCardToken(this.hashTokenCartao);

		request.setHolder(new Holder("Rolando Lero", //
				new Phone("71", "988888888"), //
				new Document(DocumentType.CPF, "000.000.001-91"), //
				"31/05/1984"));

		request.setShippingAddress(new Address("BRA", //
				"SP", //
				"Sao Paulo", //
				"Jardim Paulistano", //
				"01452002", //
				"Av. Brig. Faria Lima", //
				"1384", //
				"5º andar"));

		request.setShippingType(ShippingType.NOT_SPECIFIED);

		request.setBillingAddress(new Address("BRA", //
				"SP", //
				"Sao Paulo", //
				"Jardim Paulistano", //
				"01452002", //
				"Av. Brig. Faria Lima", //
				"1384", //
				"5º andar"));

		request.addParameter("senderHash", this.senderHash);
		request.addParameter("creditCardHolderName", "Sandbox");
		request.addParameter("creditCardHolderBirthDate", "12/2030");
		request.addParameter("creditCardHolderAreaCode", "71");
		request.addParameter("creditCardHolderPhone", "988168848");
		request.addParameter("installmentValue", "5000.00");
		request.addParameter("installmentValue", "5000.00");
		request.addParameter("installmentQuantity", "1");

		// request.setInstallment(new Installment(1, new
		// BigDecimal("5000.00")));

		try {
			/*
			 * If you use application credential you don't need to set
			 * request.setReceiverEmail(); Set your account credentials on
			 * src/pagseguro-config.properties You can create an payment using
			 * an application credential and set an authorizationCode
			 * ApplicationCredentials applicationCredentials =
			 * PagSeguroConfig.getApplicationCredentials();
			 * applicationCredentials.setAuthorizationCode(
			 * "your_authorizationCode");
			 *
			 */

			final AccountCredentials accountCredentials = PagSeguroConfig.getAccountCredentials();

			final Transaction transaction = TransactionService.createTransaction(accountCredentials, //
					request);

			if (transaction != null) {
				System.out.println("Transaction Code - Gateway Mode: " + transaction.getCode());
			}

		} catch (PagSeguroServiceException e) {
			System.err.println(e.getMessage());
		}
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHashTokenCartao() {
		return hashTokenCartao;
	}

	public void setHashTokenCartao(String hashTokenCartao) {
		this.hashTokenCartao = hashTokenCartao;
	}

	public String getSenderHash() {
		return senderHash;
	}

	public void setSenderHash(String senderHash) {
		this.senderHash = senderHash;
	}

}
