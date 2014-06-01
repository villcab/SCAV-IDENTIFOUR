package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sincronizador database table.
 * 
 */
@Entity
@Table(name="sincronizador")
@NamedQuery(name="Sincronizador.findAll", query="SELECT s FROM Sincronizador s")
public class Sincronizador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="sincronizador_id_seq_generator", sequenceName="sincronizador_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sincronizador_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="id_entorno", nullable=false)
	private Integer idEntorno;

	@Column(name="ref_id", nullable=false, length=50)
	private String refId;

	@Column(nullable=false, length=50)
	private String tabla;

	@Column(nullable=false, length=1)
	private String transaccion;

	public Sincronizador() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdEntorno() {
		return this.idEntorno;
	}

	public void setIdEntorno(Integer idEntorno) {
		this.idEntorno = idEntorno;
	}

	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTransaccion() {
		return this.transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	@Override
	public String toString() {
		return "Sincronizador [id=" + id + ", idEntorno=" + idEntorno
				+ ", refId=" + refId + ", tabla=" + tabla + ", transaccion="
				+ transaccion + "]";
	}
	

}