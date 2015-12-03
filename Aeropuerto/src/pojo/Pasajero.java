package pojo;

import java.sql.Date;

public class Pasajero {
	
	private String nombre, nacionalidad, sexo;
	private int id, nPasaporte;
	private Date fechaDeNacimiento;
	
	
	public Pasajero(int id, String nombre, String nacionalidad, String sexo, int nPasaporte, Date fechaDeNacimiento) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.id = id;
		this.nPasaporte = nPasaporte;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public Pasajero(String nombre, String nacionalidad, String sexo, int nPasaporte, Date fechaDeNacimiento) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.sexo = sexo;
		this.nPasaporte = nPasaporte;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public Pasajero() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaDeNacimiento == null) ? 0 : fechaDeNacimiento.hashCode());
		result = prime * result + id;
		result = prime * result + nPasaporte;
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}
	public String toString(){
		String s="Nombre: "+nombre+" Nacionalidad: "+nacionalidad+" Sexo: "+sexo+" Número pasaporte: "+nPasaporte+" Fecha de nacimiento: "+fechaDeNacimiento;
	return s;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		if (fechaDeNacimiento == null) {
			if (other.fechaDeNacimiento != null)
				return false;
		} else if (!fechaDeNacimiento.equals(other.fechaDeNacimiento))
			return false;
		if (id != other.id)
			return false;
		if (nPasaporte != other.nPasaporte)
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getnPasaporte() {
		return nPasaporte;
	}
	public void setnPasaporte(int nPasaporte) {
		this.nPasaporte = nPasaporte;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public int getNPasaporte(){
		return nPasaporte;
	}
	public void setNPasaporte(int nPasaporte){
		this.nPasaporte=nPasaporte;
	}

}
