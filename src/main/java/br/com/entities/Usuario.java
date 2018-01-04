package br.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({	@NamedQuery(name="findUsuarioByLoginAndPassword", query="SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :password"),
				@NamedQuery(name="findUsuarioById", query="SELECT u FROM Usuario u WHERE u.idUsuario = :id")})

@Entity
@Table(name="usuario")
@SequenceGenerator(name = "SEQ_USUARIOS", sequenceName = "SEQ_USUARIOS", initialValue = 1)
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
	@Column(name="IDUSUARIO")
	private Integer idUsuario;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="SENHA")
	private String senha;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
