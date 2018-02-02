package br.com.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dto.UsuarioDTO;
import br.com.ejb.UsuarioService;
import br.com.utils.PasswordUtils;

@ManagedBean
@ViewScoped
public class CadastroUsuarioBean {
	
	@ManagedProperty(value = "#{messageBean}")
	private MessageBean messageBean;
	
	private PasswordUtils passwordUtils = new PasswordUtils();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@EJB
	private UsuarioService usuarioService;

	public String cadastrar() {
		System.out.println("Cadastro de usuário: Login: " + this.usuarioDTO.getLogin());

		this.usuarioDTO.setSenha(passwordUtils.encrypt(this.usuarioDTO.getSenha()));
		Boolean isPersistenceSuccess = this.usuarioService.saveUsuario(usuarioDTO);
		
		if(isPersistenceSuccess){
			this.messageBean.setMessage("Usuário cadastrado com sucesso!");
			return "login?faces-redirect=true";
		}else{
			FacesContext.getCurrentInstance().addMessage("mensagemCadastroUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error message", "Erro ao cadastrar usuário"));
            return "cadastrar_usuario";
		}
	}
	
	public MessageBean getMessageBean() {
		return messageBean;
	}

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	
	public String limpar() {
		System.out.println("Limpar informações do usuário.");
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