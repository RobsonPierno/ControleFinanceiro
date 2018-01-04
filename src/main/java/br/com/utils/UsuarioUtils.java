package br.com.utils;

import br.com.dto.UsuarioDTO;
import br.com.entities.Usuario;

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
}
