package pojo;

public class Equipaje {
	
	private int dimension, peso,id;
	private String color;
	
	
	public Equipaje(int dimension, int peso, String color) {
		super();
		this.dimension = dimension;
		this.peso = peso;
		this.color = color;
	}
	public Equipaje() {
		super();
	}

	public Equipaje(int dimension, int peso, int id, String color) {
		super();
		this.dimension = dimension;
		this.peso = peso;
		this.id = id;
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + dimension;
		result = prime * result + id;
		result = prime * result + peso;
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
		Equipaje other = (Equipaje) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (dimension != other.dimension)
			return false;
		if (id != other.id)
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equipaje [dimension=" + dimension + ", peso=" + peso + ", id=" + id + ", color=" + color + "]";
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
