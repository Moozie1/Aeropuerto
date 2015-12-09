package pojo;

public class Modelo {
	private int id;
	private int capacidad;
	private String nombre;
	private String asientos;
	
	public Modelo(){
		
	}
	
	public Modelo(int capacidad, String nombre, String asientos){
		this.capacidad=capacidad;
		this.nombre=nombre;
		this.asientos=asientos;
	}
	
	public Modelo(int id, int capacidad, String nombre, String asientos){
		this.id=id;
		this.capacidad=capacidad;
		this.nombre=nombre;
		this.asientos=asientos;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capaciddad) {
		this.capacidad = capaciddad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsientos() {
		return asientos;
	}

	public void setAsientos(String asiento) {
		this.asientos = asiento;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asientos == null) ? 0 : asientos.hashCode());
		result = prime * result + capacidad;
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
		Modelo other = (Modelo) obj;
		if (asientos == null) {
			if (other.asientos != null)
				return false;
		} else if (!asientos.equals(other.asientos))
			return false;
		if (capacidad != other.capacidad)
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

	public String toString(){
		String diego="Id: " +id +"Capacidad: "+capacidad+" Nombre: "+nombre+" Asiento: "+asientos;
		return diego;
		
	}

}

