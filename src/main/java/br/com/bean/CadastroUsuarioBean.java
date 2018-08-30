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
public class CadastroUsuarioBean {
	
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@EJB
	private UsuarioService usuarioService;

	public String cadastrar() {
		System.out.println("Cadastro de usu�rio: Login: " + this.usuarioDTO.getLogin());

		Boolean isPersistenceSuccess = this.usuarioService.saveUsuario(this.getUsuarioDTO());
		
		if(isPersistenceSuccess){
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usu�rio cadastrado com sucesso!"));
			return "login";
		}else{
			FacesContext.getCurrentInstance().addMessage(
					"null", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao cadastrar usu�rio"));
            return "cadastrar_usuario";
		}
	}
	
	public String limpar() {
		System.out.println("Limpar informa��es do usu�rio.");
		this.usuarioDTO = new UsuarioDTO();
        return "cadastrar_usuario";
	}
	
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}