package pojo;

public class Pista {
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getOrientacion() {
		return orientacion;
	}



	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}



	public int getLongitud() {
		return longitud;
	}



	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}



	private int id;
	private String estado;
	private String orientacion;
	private int longitud;
	
	
	
	public Pista(int id, String estado, String orientacion, int longitud) {
		
		this.id = id;
		this.estado = estado;
		this.orientacion = orientacion;
		this.longitud = longitud;
	}



	public Pista() {
		
	}



	public Pista(String estado, String orientacion, int longitud) {
	
		this.estado = estado;
		this.orientacion = orientacion;
		this.longitud = longitud;
	}
	
	public String toString(){
		String diego="Estado: "+estado+"\n"+" Orientacion: "+orientacion+"\n"+" Longitud: "+longitud;
		return diego;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + longitud;
		result = prime * result + ((orientacion == null) ? 0 : orientacion.hashCode());
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
		Pista other = (Pista) obj;
		if (id != other.id)
			return false;
		if (longitud != other.longitud)
			return false;
		if (orientacion == null) {
			if (other.orientacion != null)
				return false;
		} else if (!orientacion.equals(other.orientacion))
			return false;
		return true;
	}
	
	
	
	
	 
	 
	 
	 
	 
	 
	 
	

}
