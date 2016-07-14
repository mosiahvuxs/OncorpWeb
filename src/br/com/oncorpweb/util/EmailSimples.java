package br.com.oncorpweb.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSimples {

	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		
		//TSEmailUtil.enviar("no_reply@oncorp.srv.br", "Oncorp Teste", "Teste", "moisessrocha@gmail.com", "text/plain", Constantes.SMPT);
		
		System.out.println("SimpleEmail Start");

		Properties props = System.getProperties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", Constantes.SMPT);
		
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.port", "465"); // porta
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");


		Session session = Session.getInstance(props, null);

		MimeMessage msg = new MimeMessage(session);
		// set message headers
		msg.addHeader("Content-type", "text/html; charset=UTF-8");

		msg.setFrom(new InternetAddress("no_reply@oncorp.srv.br", "NoReply-Oncorp"));

		//msg.setReplyTo(InternetAddress.parse("no_reply@oncorp.srv.br", false));

		msg.setSubject("Teste", "UTF-8");

		msg.setText("Testando.", "UTF-8");

		msg.setSentDate(new java.util.Date());

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("moisessrocha@gmail.com", false));
		System.out.println("Message is ready");

		Transport.send(msg);

		System.out.println("EMail Sent Successfully!!");
		
			

	}
}
