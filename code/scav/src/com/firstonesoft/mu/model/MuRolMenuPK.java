package com.firstonesoft.mu.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mu_rol_menu database table.
 * 
 */
@Embeddable
public class MuRolMenuPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rol_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Long rolId;

	@Column(name="menu_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Long menuId;

	public MuRolMenuPK() {
	}
	public Long getRolId() {
		return this.rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	public Long getMenuId() {
		return this.menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MuRolMenuPK)) {
			return false;
		}
		MuRolMenuPK castOther = (MuRolMenuPK)other;
		return 
			this.rolId.equals(castOther.rolId)
			&& this.menuId.equals(castOther.menuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rolId.hashCode();
		hash = hash * prime + this.menuId.hashCode();
		
		return hash;
	}
}