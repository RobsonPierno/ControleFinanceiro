package br.com.bean;

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
	
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@EJB
	private UsuarioService usuarioService;

	public String efetuarLogin() {
		System.out.println("Efetuando login " + this.usuarioDTO.getLogin());

		this.setUsuarioDTO(this.usuarioService.getUsuario(usuarioDTO));
		if(usuarioDTO != null){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", this.getUsuarioDTO());
			return "tela_inicial?faces-redirect=true";
		}else{
			FacesContext.getCurrentInstance().addMessage(
					"null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Usuário não encontrado ou senha incorreta."));
			
            this.setUsuarioDTO(new UsuarioDTO());
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