package br.com.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.dto.UsuarioDTO;
import br.com.entity.Usuario;

public class UsuarioUtils {

	public UsuarioDTO convertEntityToDTO(final Usuario entity) {
		if(entity == null) {
			return null;
		}
		
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setIdUsuario(entity.getIdUsuario());
		dto.setLogin(entity.getLogin());
		dto.setSenha(entity.getSenha());
		
		return dto;
	}
	
	public Usuario convertDTOToEntity(final UsuarioDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Usuario entity = new Usuario();
		
		entity.setIdUsuario(dto.getIdUsuario());
		entity.setLogin(dto.getLogin());
		entity.setSenha(dto.getSenha());
		
		return entity;
	}

	public List<UsuarioDTO> convertListEntityToDTO(final List<Usuario> usuarios) {
		List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
		
		usuarios.forEach(usu->listUsuarioDTO.add(this.convertEntityToDTO(usu)));
		
		return listUsuarioDTO;
	}
}
