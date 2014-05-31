package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mu_menu database table.
 * 
 */
@Entity
@Table(name="mu_menu")
@NamedQuery(name="MuMenu.findAll", query="SELECT m FROM MuMenu m")
public class MuMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_id", unique=true, nullable=false)
	private Long menuId;

	private Integer depende;

	private Boolean estado;

	private Float nivel;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(length=50)
	private String url;

	//bi-directional many-to-one association to MuMenu
	@ManyToOne
	@JoinColumn(name="menu_id_padre")
	private MuMenu muMenu;

	//bi-directional many-to-one association to MuMenu
	@OneToMany(mappedBy="muMenu", fetch = FetchType.EAGER)
	private List<MuMenu> muMenus;

	//bi-directional many-to-one association to MuRolMenu
	@OneToMany(mappedBy="muMenu")
	private List<MuRolMenu> muRolMenus;

	public MuMenu() {
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Integer getDepende() {
		return this.depende;
	}

	public void setDepende(Integer depende) {
		this.depende = depende;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Float getNivel() {
		return this.nivel;
	}

	public void setNivel(Float nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MuMenu getMuMenu() {
		return this.muMenu;
	}

	public void setMuMenu(MuMenu muMenu) {
		this.muMenu = muMenu;
	}

	public List<MuMenu> getMuMenus() {
		return this.muMenus;
	}

	public void setMuMenus(List<MuMenu> muMenus) {
		this.muMenus = muMenus;
	}

	public MuMenu addMuMenus(MuMenu muMenus) {
		getMuMenus().add(muMenus);
		muMenus.setMuMenu(this);

		return muMenus;
	}

	public MuMenu removeMuMenus(MuMenu muMenus) {
		getMuMenus().remove(muMenus);
		muMenus.setMuMenu(null);

		return muMenus;
	}

	public List<MuRolMenu> getMuRolMenus() {
		return this.muRolMenus;
	}

	public void setMuRolMenus(List<MuRolMenu> muRolMenus) {
		this.muRolMenus = muRolMenus;
	}

	public MuRolMenu addMuRolMenus(MuRolMenu muRolMenus) {
		getMuRolMenus().add(muRolMenus);
		muRolMenus.setMuMenu(this);

		return muRolMenus;
	}

	public MuRolMenu removeMuRolMenus(MuRolMenu muRolMenus) {
		getMuRolMenus().remove(muRolMenus);
		muRolMenus.setMuMenu(null);

		return muRolMenus;
	}

}