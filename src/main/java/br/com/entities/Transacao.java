package br.com.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transacao")
@SequenceGenerator(name = "SEQ_TRANSACAO", sequenceName = "SEQ_TRANSACAO", initialValue = 1)
public class Transacao {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACAO")
	@Column(name="IDTRANSACAO")
	private Integer	idTransacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA")
	private Calendar data;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="VALOR")
	private BigDecimal valor;
	
	@Column(name="IDTIPOTRANSACAO")
	private Integer idTipoTransacao;

	@ManyToOne
	@JoinColumn(name = "IDUSUARIO")
	private Usuario idUsuario;

	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getIdTipoTransacao() {
		return idTipoTransacao;
	}

	public void setIdTipoTransacao(Integer idTipoTransacao) {
		this.idTipoTransacao = idTipoTransacao;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
}
