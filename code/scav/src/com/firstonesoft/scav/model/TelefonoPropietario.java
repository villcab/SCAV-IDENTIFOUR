package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefono_propietario database table.
 * 
 */
@Entity
@Table(name="telefono_propietario")
@NamedQuery(name="TelefonoPropietario.findAll", query="SELECT t FROM TelefonoPropietario t")
public class TelefonoPropietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer telefono;

	//bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name="ci", nullable=false)
	private Propietario propietario;

	public TelefonoPropietario() {
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	@Override
	public String toString() {
		return String.valueOf(telefono);
	}

}