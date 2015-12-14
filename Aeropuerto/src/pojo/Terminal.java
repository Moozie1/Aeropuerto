package pojo;

public class Terminal {
	private int id;
	private String nombre;
	private int numeroDePistas;
	
	
	public Terminal(int id, String nombre, int numeroDePistas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroDePistas = numeroDePistas;
	}


	public Terminal(String nombre, int numeroDePistas) {
		super();
		this.nombre = nombre;
		this.numeroDePistas = numeroDePistas;
	}


	
	public Terminal() {
		
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getNumeroDePistas() {
		return numeroDePistas;
	}


	public void setNumeroDePistas(int numeroDePistas) {
		this.numeroDePistas = numeroDePistas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + numeroDePistas;
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
		Terminal other = (Terminal) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numeroDePistas != other.numeroDePistas)
			return false;
		return true;
	}


	
	public String toString() {
		return "Id: " + id + "\nNombre: " + nombre + "\nNumeroDePistas: " + numeroDePistas + "\n----------";
	}
	
	
}
