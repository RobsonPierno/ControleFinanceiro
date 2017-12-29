package br.com.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.entities.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();	

	public String efetuarLogin() {
		System.out.println("Efetuando login " + this.usuario.getLogin());

		//buscar usuario pelo email e senha
		
		if(usuario != null){
			
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
					
			return "tela_inicial?faces-redirect=true";
		}else{
			facesContext.addMessage(null,  new FacesMessage("Login ou Senha inválido"));
            this.usuario = new Usuario();
            return "login";
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
