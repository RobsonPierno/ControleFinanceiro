package br.com.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dto.UsuarioDTO;
import br.com.ejb.UsuarioService;
import br.com.utils.PasswordUtils;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	@ManagedProperty(value = "#{messageBean}")
	private MessageBean messageBean;
	
	private PasswordUtils passwordUtils = new PasswordUtils();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	
	@EJB
	private UsuarioService usuarioService;

	public String efetuarLogin() {
		System.out.println("Efetuando login " + this.usuarioDTO.getLogin());
		this.messageBean.setMessage("");

		this.usuarioDTO.setSenha(passwordUtils.encrypt(this.usuarioDTO.getSenha()));
		usuarioDTO = this.usuarioService.getUsuario(usuarioDTO);
		
		if(usuarioDTO != null){

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioDTO);

			return "tela_inicial?faces-redirect=true";
		}else{
            this.usuarioDTO = new UsuarioDTO();
            return "login";
		}
	}
	
	public MessageBean getMessageBean() {
		return messageBean;
	}

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
}