package pojo;

public class Equipaje {
	private int id;
	private int dimension;
	private int peso;
	private String color;
	private Pasajero pasajero;
	private Vuelo vuelo;
	
	public Equipaje(int id, int dimension, int peso, String color, Pasajero pasajero, Vuelo vuelo){
		this.id=id;
		this.dimension=dimension;
		this.peso=peso;
		this.color=color;
		this.pasajero=pasajero;
		this.vuelo=vuelo;
	}
	public Equipaje(int dimension, int peso, String color, Pasajero pasajero, Vuelo vuelo){
		this.dimension=dimension;
		this.peso=peso;
		this.color=color;
		this.pasajero=pasajero;
		this.vuelo=vuelo;
	}
	public Equipaje(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	
	public String toString() {
		return "Id: "+ id + "\nDimension: " + dimension + "\nPeso: " + peso + "\nColor: " + color + "\nPasajero:\n"
				+pasajero + "\nVuelo:" + vuelo + "\n----------";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + dimension;
		result = prime * result + id;
		result = prime * result + ((pasajero == null) ? 0 : pasajero.hashCode());
		result = prime * result + peso;
		result = prime * result + ((vuelo == null) ? 0 : vuelo.hashCode());
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
		if (pasajero == null) {
			if (other.pasajero != null)
				return false;
		} else if (!pasajero.equals(other.pasajero))
			return false;
		if (peso != other.peso)
			return false;
		if (vuelo == null) {
			if (other.vuelo != null)
				return false;
		} else if (!vuelo.equals(other.vuelo))
			return false;
		return true;
	}
	
}