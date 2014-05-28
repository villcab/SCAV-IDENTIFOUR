package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mu_usuario database table.
 * 
 */
@Entity
@Table(name="mu_usuario")
@NamedQuery(name="MuUsuario.findAll", query="SELECT m FROM MuUsuario m")
public class MuUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usuario_id", unique=true, nullable=false)
	private Long usuarioId;

	@Column(nullable=false)
	private Boolean estado;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(nullable=false, length=50)
	private String username;

	//bi-directional many-to-one association to MuRol
	@ManyToOne
	@JoinColumn(name="rol_id")
	private MuRol muRol;

	public MuUsuario() {
	}

	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MuRol getMuRol() {
		return this.muRol;
	}

	public void setMuRol(MuRol muRol) {
		this.muRol = muRol;
	}

}