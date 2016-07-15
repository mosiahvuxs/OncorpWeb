package br.com.oncorpweb.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.oncorpweb.util.Constantes;

@SuppressWarnings("serial")
public class AutorizacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();

		String paginaAtual = context.getViewRoot().getViewId();

		boolean paginaLogin = paginaAtual.endsWith("login.xhtml");

		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		if (!paginaAtual.contains("confirmacao") && !paginaLogin && session.getAttribute(Constantes.USUARIO_CONECTADO) == null) {

			session.removeAttribute(Constantes.AUTENTICACAO_FACES);

			context.getExternalContext().invalidateSession();

			NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();

			navigationHandler.handleNavigation(context, null, "login");

		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.RESTORE_VIEW;
	}

}
