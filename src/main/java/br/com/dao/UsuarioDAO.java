package br.com.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dto.UsuarioDTO;
import br.com.entities.Usuario;


@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Usuario findUsuarioByLoginAndPassword(final UsuarioDTO usuarioDTO) {
		TypedQuery<Usuario> query = em.createNamedQuery("findUsuarioByLoginAndPassword", Usuario.class);
		query.setParameter("login", usuarioDTO.getLogin());
		query.setParameter("password", usuarioDTO.getSenha());
		return query.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Usuario findUsuarioById(final UsuarioDTO usuarioDTO) {
		TypedQuery<Usuario> query = em.createNamedQuery("findUsuarioById", Usuario.class);
		query.setParameter("id", usuarioDTO.getIdUsuario());
		return query.getSingleResult();
	}
}