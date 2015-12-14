package pojo;

import java.sql.Date;

public class Trabajador {
	private int id;
	private Aerolinea aerolinea;
	private String nombre;
	private Date fechaDeNacimiento;
	private Date fechaInicioTrabajo;
	
	
	public Trabajador(){
		
	}
	public Trabajador(Aerolinea aerolinea, String nombre, Date fechaDeNacimiento, Date fechaInicioTrabajo){
		this.aerolinea=aerolinea;
		this.nombre=nombre;
		this.fechaDeNacimiento=fechaDeNacimiento;
		this.fechaInicioTrabajo=fechaInicioTrabajo;
		
	}
	
	public Trabajador(int id, Aerolinea aerolinea, String nombre, Date fechaDeNacimiento, Date fechaInicioTrabajo){
		this.id=id;
		this.aerolinea=aerolinea;
		this.nombre=nombre;
		this.fechaDeNacimiento=fechaDeNacimiento;
		this.fechaInicioTrabajo=fechaInicioTrabajo;
	}
	
	public String toString(){
		String c;
		c = "Id: "+id +"\n"+"Aerolinea: \n"+aerolinea +"\n \n"+"Nombre:"+nombre+"\n"+"Fecha de Nacimiento:" + 
		fechaDeNacimiento+"\n"+"Fecha De inicio de contrato:"+ fechaInicioTrabajo+"\n"+"---------------";
		return c;
	}
		
	
	public Aerolinea getAerolinea(){
		return aerolinea;
		
	}
	public void setAerolinea(Aerolinea a){
		this.aerolinea= a;
		
		
	}
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public Date getFechaInicioTrabajo() {
		return fechaInicioTrabajo;
	}
	public void setFechaInicioTrabajo(Date fechaInicioTrabajo) {
		this.fechaInicioTrabajo = fechaInicioTrabajo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aerolinea == null) ? 0 : aerolinea.hashCode());
		result = prime * result + ((fechaDeNacimiento == null) ? 0 : fechaDeNacimiento.hashCode());
		result = prime * result + ((fechaInicioTrabajo == null) ? 0 : fechaInicioTrabajo.hashCode());
		result = prime * result + id;
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
		Trabajador other = (Trabajador) obj;
		if (aerolinea == null) {
			if (other.aerolinea != null)
				return false;
		} else if (!aerolinea.equals(other.aerolinea))
			return false;
		if (fechaDeNacimiento == null) {
			if (other.fechaDeNacimiento != null)
				return false;
		} else if (!fechaDeNacimiento.equals(other.fechaDeNacimiento))
			return false;
		if (fechaInicioTrabajo == null) {
			if (other.fechaInicioTrabajo != null)
				return false;
		} else if (!fechaInicioTrabajo.equals(other.fechaInicioTrabajo))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	

}
