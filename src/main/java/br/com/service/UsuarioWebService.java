package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.dto.UsuarioDTO;

@WebService
@Stateless
public class UsuarioWebService {

	@WebResult(name="Usuarios")
	public List<UsuarioDTO> getAllUsers(){
		return null;
	}
	
	@WebResult(name="Usuario")
	public UsuarioDTO getUserById(@WebParam(name="idUsuario") final Integer userId){
		return null;
	}
}
