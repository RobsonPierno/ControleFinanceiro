package br.com.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MainMenuBean {
	
    public String popularInput() {
    	return "popularInput?faces-redirect=true";
    }
    
    public String inserirIf() {
    	return "inserirIf?faces-redirect=true";
    }
    
    public String home() {
    	return "tela_inicial?faces-redirect=true";
    }
    
    public String logout() {
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
    	return "login?faces-redirect=true";
    }
}