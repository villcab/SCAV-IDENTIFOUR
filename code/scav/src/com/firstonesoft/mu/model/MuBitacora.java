package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mu_bitacora database table.
 * 
 */
@Entity
@Table(name="mu_bitacora")
@NamedQuery(name="MuBitacora.findAll", query="SELECT m FROM MuBitacora m")
public class MuBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique=true, nullable=false)
	private Date fecha;

	@Column(length=200)
	private String accion;

	@Column(name="direccion_ip", length=50)
	private String direccionIp;

	@Column(length=50)
	private String formulario;

	@Column(length=50)
	private String usuario;

	public MuBitacora() {
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public String getFormulario() {
		return this.formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}