	package pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="AEROLINEA")
public class Aerolinea implements Serializable {
	@Id
	@GeneratedValue(generator="nombreAerolinea")
	@TableGenerator(name="nombreAerolinea", table="sqlite_sequence",
	pkColumnName="name", valueColumnName="seq",pkColumnValue="AEROLINEA")
	//@OneToMany(mappedBy="aerolinea")
	private int id;
	@Column(name="nombre")	
	private String nombre;
	@Column(name="aeropuertoBase")
	private String aeropuertoBase;
	@Column(name="nacionalidad")
	private String nacionalidad;
	
	
	
	public Aerolinea(int id, String nombre, String aeropuertoBase, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.aeropuertoBase = aeropuertoBase;
		this.nacionalidad = nacionalidad;
		this.id = id;
	}


	public Aerolinea(String nombre, String aeropuertoBase, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.aeropuertoBase = aeropuertoBase;
		this.nacionalidad = nacionalidad;
	}


	public Aerolinea() {
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aeropuertoBase == null) ? 0 : aeropuertoBase.hashCode());
		result = prime * result + id;
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aerolinea other = (Aerolinea) obj;
		if (aeropuertoBase == null) {
			if (other.aeropuertoBase != null)
				return false;
		} else if (!aeropuertoBase.equals(other.aeropuertoBase))
			return false;
		if (id != other.id)
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
		return true;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getAeropuertoBase() {
		return aeropuertoBase;
	}


	public void setAeropuertoBase(String aeropuertoBase) {
		this.aeropuertoBase = aeropuertoBase;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString(){
		String s=" Id: "+id+"\n"+" Nombre: "+nombre +"\n"+" Aeropuerto base: "+aeropuertoBase+"\n"+" Nacionalidad: "+nacionalidad+"\n----------\n";
		return s;
	}
	

}
