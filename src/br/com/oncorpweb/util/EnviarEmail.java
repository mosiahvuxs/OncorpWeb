package br.com.oncorpweb.util;

import java.util.List;

import javax.mail.internet.AddressException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.com.topsys.util.TSUtil;

public final class EnviarEmail {

	private EnviarEmail() {

	}

	public static void enviarEmailSimples(String remetente, String destinatario, String assunto, String mensagem, String personsalizacao) throws AddressException {

		SimpleEmail email = new SimpleEmail();

		email.setCharset(EmailConstants.UTF_8);

		email.setHostName(Constantes.SMPT);

		email.setSmtpPort(Constantes.PORTA);

		email.setAuthenticator(new DefaultAuthenticator(Constantes.EMAIL_SISTEMA_SMTP, Constantes.SENHA_SMTP));

		try {

			email.setFrom(remetente, personsalizacao);

			email.setDebug(false);

			email.setSubject(assunto);

			email.setMsg(mensagem);

			email.addTo(destinatario);

			email.send();

		} catch (EmailException e) {

			e.printStackTrace();
		}
	}

	public static void enviarEmailHtml(String remetente, String destinatario, String assunto, String mensagem, List<String> destinatarios, List<String> anexoList, String personalizacao) throws EmailException {

		HtmlEmail email = new HtmlEmail();

		email.setCharset(EmailConstants.UTF_8);

		email.setHostName(Constantes.SMPT);

		email.setSmtpPort(Constantes.PORTA);

		email.setAuthenticator(new DefaultAuthenticator(Constantes.EMAIL_SISTEMA_SMTP, Constantes.SENHA_SMTP));

		email.setFrom(remetente, personalizacao);

		email.setDebug(false);
		email.setSubject(assunto);

		if (!TSUtil.isEmpty(anexoList)) {

			for (String arquivo : anexoList) {

				EmailAttachment anexo = new EmailAttachment();

				anexo.setPath(arquivo);

				anexo.setDisposition(EmailAttachment.ATTACHMENT);

				email.attach(anexo);
			}

		}

		email.setHtmlMsg(mensagem);

		if (!TSUtil.isEmpty(destinatario)) {

			email.addBcc(destinatario);
		}

		if (!TSUtil.isEmpty(destinatarios)) {

			for (String d : destinatarios) {

				email.addBcc(d);

			}

		}

		email.send();

	}

}