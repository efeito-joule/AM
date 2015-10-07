package br.com.joule.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthorizationListener  implements PhaseListener, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId(); 
		ExternalContext externalContext = facesContext.getExternalContext();  
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();  

		boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1)
				||  (currentPage.lastIndexOf("cadastroAluno.xhtml") > -1); 
		// contexto da requisicao
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true); 
		
		// pega usuario da sessao
		Object currentUser = session.getAttribute("usuarioLogado"); 
		try{
			// verifica se existe usuario logado se for null redireciona para o login
			if (!isLoginPage && currentUser == null || !isLoginPage && currentUser.equals("")) { 
				externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
				externalContext.redirect("login.xhtml");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// bloqueia os caches do navegador via response
		response.setHeader("Expires", "-1");  
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidade, proxy-revalidade, private, post-check=0, pre-check=0");  
		response.setHeader("Pragma", "no-cache");
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}