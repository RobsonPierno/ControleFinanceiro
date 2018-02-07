package br.com.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dto.UsuarioDTO;
import br.com.entity.Usuario;


@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void saveUsuario(final Usuario usuario) {
		em.persist(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void deleteUsuario(final Usuario usuario) {
		em.remove(usuario);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Usuario findUsuarioByLoginAndPassword(final UsuarioDTO usuarioDTO) {
		TypedQuery<Usuario> query = em.createNamedQuery("findUsuarioByLoginAndPassword", Usuario.class);
		query.setParameter("login", usuarioDTO.getLogin());
		query.setParameter("password", usuarioDTO.getSenha());
		
		Usuario usuario;
		try {
			usuario = query.getSingleResult();
		}catch (NoResultException ex){
			usuario = null;
		}
		return usuario;
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Usuario findUsuarioById(final UsuarioDTO usuarioDTO) {
		TypedQuery<Usuario> query = em.createNamedQuery("findUsuarioById", Usuario.class);
		query.setParameter("id", usuarioDTO.getIdUsuario());
		
		Usuario usuario;
		try {
			usuario = query.getSingleResult();
		}catch (NoResultException ex){
			usuario = null;
		}
		return usuario;
	}

	public List<Usuario> findAllUsuarios() {
		TypedQuery<Usuario> query = em.createNamedQuery("findAllUsuarios", Usuario.class);
		return query.getResultList();
	}
}
