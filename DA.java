
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