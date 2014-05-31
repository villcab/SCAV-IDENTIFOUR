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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="red_id", nullable=false, length=50)
	private String redId;

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

	public String getRedId() {
		return this.redId;
	}

	public void setRedId(String redId) {
		this.redId = redId;
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

}