package br.com.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public abstract class ValidateSession {

	@PostConstruct
	public void validaUsuarioLogado() {
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado") == null) {
			NavigationHandler handler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	    	handler.handleNavigation(FacesContext.getCurrentInstance(), null, "login?faces-redirect=true");
	    	FacesContext.getCurrentInstance().renderResponse();
		}
	}
}
