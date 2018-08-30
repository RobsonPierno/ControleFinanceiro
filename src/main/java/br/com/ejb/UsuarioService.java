package br.com.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceException;

import br.com.dao.UsuarioDAO;
import br.com.dto.UsuarioDTO;
import br.com.entity.Usuario;
import br.com.utils.PasswordUtils;
import br.com.utils.UsuarioUtils;

@Stateless
public class UsuarioService {

	@EJB
	private UsuarioDAO usuarioDAO;
	
	private static UsuarioUtils usuarioUtils = new UsuarioUtils();
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDTO getUsuario(UsuarioDTO usuarioDTO) {
		this.criptografarSenha(usuarioDTO);
		
		if(usuarioDTO.getIdUsuario() != null) {
			usuarioDTO = this.getUsuarioById(usuarioDTO);
		}else{
			usuarioDTO = this.getUsuarioByLoginAndPassword(usuarioDTO);
		}		
		return usuarioDTO;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boolean saveUsuario(final UsuarioDTO usuarioDTO) {
		Boolean retorno = Boolean.TRUE;
		try {
			this.criptografarSenha(usuarioDTO);
			Usuario usuario = usuarioUtils.convertDTOToEntity(usuarioDTO);
			this.usuarioDAO.saveUsuario(usuario);
		}catch(PersistenceException ex) {
			System.out.println("Erro ao persistir usuário " + usuarioDTO.getLogin() + "\n" + ex.getMessage());
			retorno = Boolean.FALSE;
		}
		return retorno;
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	private UsuarioDTO getUsuarioByLoginAndPassword(final UsuarioDTO usuarioDTO) {
		Usuario usuario = this.usuarioDAO.findUsuarioByLoginAndPassword(usuarioDTO);
		return usuarioUtils.convertEntityToDTO(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	private UsuarioDTO getUsuarioById(final UsuarioDTO usuarioDTO) {
		Usuario usuario = this.usuarioDAO.findUsuarioById(usuarioDTO);
		return usuarioUtils.convertEntityToDTO(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	private List<UsuarioDTO> getAllUsuarios() {
		List<Usuario> usuarios = this.usuarioDAO.findAllUsuarios();
		return usuarioUtils.convertListEntityToDTO(usuarios);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	private void deleteUsuario(final UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioUtils.convertDTOToEntity(usuarioDTO);
		this.usuarioDAO.deleteUsuario(usuario);
	}
	
	private void criptografarSenha(final UsuarioDTO usuarioDTO) {
		PasswordUtils passwordUtils = new PasswordUtils();
		String senhaEncriptada = passwordUtils.encrypt(usuarioDTO.getSenha());
		usuarioDTO.setSenha(senhaEncriptada);
	}
}
