package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mu_rol_menu database table.
 * 
 */
@Entity
@Table(name="mu_rol_menu")
@NamedQuery(name="MuRolMenu.findAll", query="SELECT m FROM MuRolMenu m")
public class MuRolMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MuRolMenuPK id;

	@Column(nullable=false)
	private Boolean estado;

	//bi-directional many-to-one association to MuMenu
	@ManyToOne
	@JoinColumn(name="menu_id", nullable=false, insertable=false, updatable=false)
	private MuMenu muMenu;

	//bi-directional many-to-one association to MuRol
	@ManyToOne
	@JoinColumn(name="rol_id", nullable=false, insertable=false, updatable=false)
	private MuRol muRol;

	public MuRolMenu() {
	}

	public MuRolMenuPK getId() {
		return this.id;
	}

	public void setId(MuRolMenuPK id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public MuMenu getMuMenu() {
		return this.muMenu;
	}

	public void setMuMenu(MuMenu muMenu) {
		this.muMenu = muMenu;
	}

	public MuRol getMuRol() {
		return this.muRol;
	}

	public void setMuRol(MuRol muRol) {
		this.muRol = muRol;
	}

}