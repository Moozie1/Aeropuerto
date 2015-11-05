package pojo;

public class Billete {
	private int id;
	private String clase;
	private int asiento;
	private Vuelo vuelo;
	private Pasajero pasajero;
	
	public Billete(){
		
	}
	public Billete(int id, String clase, int asiento, Vuelo vuelo, Pasajero pasajero){
		this.id=id;
		this.clase=clase;
		this.asiento=asiento;
		this.vuelo=vuelo;
		this.pasajero=pasajero;
	}
	
	public Billete(String clase, int asiento, Vuelo vuelo, Pasajero pasajero){
		this.clase=clase;
		this.asiento=asiento;
		this.vuelo=vuelo;
		this.pasajero=pasajero;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getClase(){
		return clase;
	}
	public void setClase(String c){
		clase=c;
	}
	public int getAsiento() {
		return asiento;
	}
	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}
	public Vuelo getVuelo(){
		return vuelo; //La verdad que no estoy muy seguro de que este get sea necesario..
	}
	
	public void setVuelo(Vuelo vuelo){
		this.vuelo=vuelo; 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + asiento;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + id;
		result = prime * result + ((pasajero == null) ? 0 : pasajero.hashCode());
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
		Billete other = (Billete) obj;
		if (asiento != other.asiento)
			return false;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (id != other.id)
			return false;
		if (pasajero == null) {
			if (other.pasajero != null)
				return false;
		} else if (!pasajero.equals(other.pasajero))
			return false;
		if (vuelo == null) {
			if (other.vuelo != null)
				return false;
		} else if (!vuelo.equals(other.vuelo))
			return false;
		return true;
	}
	public String getPasajero(){
		return pasajero.toString();
	}
	public void setPasajero(Pasajero pasajero){ //y ya puestos a tirar de tres..
		this.pasajero=pasajero;
	}
	public String toString(){
		String a="Id: "+id+" clase: "+clase+" asiento: "+asiento+" vuelo: "+ vuelo+" pasajero: "+pasajero;
		return a;
	}
	// Falta el equals y el hashcode pero al intentar generarlos me da error por lo de la foreign key
}
