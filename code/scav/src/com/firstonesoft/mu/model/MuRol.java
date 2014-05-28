package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mu_rol database table.
 * 
 */
@Entity
@Table(name="mu_rol")
@NamedQuery(name="MuRol.findAll", query="SELECT m FROM MuRol m")
public class MuRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rol_id", unique=true, nullable=false)
	private Long rolId;

	private Long descripcion;

	@Column(nullable=false)
	private Boolean estado;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to MuRolMenu
	@OneToMany(mappedBy="muRol")
	private List<MuRolMenu> muRolMenus;

	//bi-directional many-to-one association to MuUsuario
	@OneToMany(mappedBy="muRol")
	private List<MuUsuario> muUsuarios;

	public MuRol() {
	}

	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public Long getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(Long descripcion) {
		this.descripcion = descripcion;
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

	public List<MuRolMenu> getMuRolMenus() {
		return this.muRolMenus;
	}

	public void setMuRolMenus(List<MuRolMenu> muRolMenus) {
		this.muRolMenus = muRolMenus;
	}

	public MuRolMenu addMuRolMenus(MuRolMenu muRolMenus) {
		getMuRolMenus().add(muRolMenus);
		muRolMenus.setMuRol(this);

		return muRolMenus;
	}

	public MuRolMenu removeMuRolMenus(MuRolMenu muRolMenus) {
		getMuRolMenus().remove(muRolMenus);
		muRolMenus.setMuRol(null);

		return muRolMenus;
	}

	public List<MuUsuario> getMuUsuarios() {
		return this.muUsuarios;
	}

	public void setMuUsuarios(List<MuUsuario> muUsuarios) {
		this.muUsuarios = muUsuarios;
	}

	public MuUsuario addMuUsuario(MuUsuario muUsuario) {
		getMuUsuarios().add(muUsuario);
		muUsuario.setMuRol(this);

		return muUsuario;
	}

	public MuUsuario removeMuUsuario(MuUsuario muUsuario) {
		getMuUsuarios().remove(muUsuario);
		muUsuario.setMuRol(null);

		return muUsuario;
	}

}