
//en el model Propietario aumentar
@Override
public String toString() {
	return "Propietario [ci=" + ci + ", apellidos=" + apellidos + ", nombres=" + nombres + ", nroLicencia=" + nroLicencia + "]";
}

//en el model TelefonoPropietario
@Override
public String toString() {
	return String.valueOf(telefono);
}

//bi-directional many-to-many association to Propietario
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
	name="propietario_vehiculo"
	, joinColumns={
		@JoinColumn(name="ci", nullable=false)
		}
	, inverseJoinColumns={
		@JoinColumn(name="placa", nullable=false)
		}
	)
private List<Vehiculo> vehiculos;
	
	
//bi-directional many-to-many association to Vehiculo
@ManyToMany(mappedBy="vehiculos", fetch = FetchType.EAGER)
private List<Propietario> propietarios;

//bi-directional many-to-one association to Administrador de entorno
@OneToMany(mappedBy="administradorEntorno", fetch = FetchType.EAGER)
private List<Entorno> entornos;
	
	
//equals en vehiculos
@Override
public boolean equals(Object o) {
	Vehiculo v = (Vehiculo) o;
	if (v.getPlaca().equals(placa)) {
		return true;
	}
	return false;
}