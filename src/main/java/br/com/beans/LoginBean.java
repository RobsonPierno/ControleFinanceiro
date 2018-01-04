package br.com.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dto.UsuarioDTO;
import br.com.ejb.UsuarioService;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();	
	
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@EJB
	private UsuarioService usuarioService;

	public String efetuarLogin() {
		System.out.println("Efetuando login " + this.usuarioDTO.getLogin());

		usuarioDTO = this.usuarioService.getUsuario(usuarioDTO);
		
		if(usuarioDTO != null){
			
			facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuarioDTO);
					
			return "tela_inicial?faces-redirect=true";
		}else{
			facesContext.addMessage(null,  new FacesMessage("Login ou Senha inválido"));
            this.usuarioDTO = new UsuarioDTO();
            return "login";
		}
	}
	
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}