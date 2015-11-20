package pojo;

public class Avion {
	private int id;
	private Aerolinea aerolinea;
	private Modelo modelo;
	
	public Avion(){
		
	}
	
	public Avion(int id, Aerolinea aerolinea, Modelo modelo){
		this.id=id;
		this.aerolinea=aerolinea;
		this.modelo=modelo;
		
	}
	
	public Avion(Aerolinea aerolinea, Modelo modelo){
		this.aerolinea=aerolinea;
		this.modelo=modelo;
		
	}
	
	public int getId(){
		return id;
	}
	public Aerolinea getAerolinea(){
		return aerolinea;
	}
	public Modelo getModelo(){
		return modelo;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public void setAerolinea(Aerolinea aerolinea){
		this.aerolinea=aerolinea;
	}
	public void setModelo(Modelo modelo){
		this.modelo=modelo;
	}
	public String toString(){
		String a="Id: "+id+" Aerolinea: "+aerolinea+"Modelo: "+modelo;
		return a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aerolinea == null) ? 0 : aerolinea.hashCode());
		result = prime * result + id;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
		Avion other = (Avion) obj;
		if (aerolinea == null) {
			if (other.aerolinea != null)
				return false;
		} else if (!aerolinea.equals(other.aerolinea))
			return false;
		if (id != other.id)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}
}
// no se si funciona el hashcode y el equals











