package pojo;

public class Vuelo {
	
	private int id;
	private Avion avion;
	private Terminal terminal;
	private Pista pista;
	
	
	public Vuelo(){
		
	}
	public Vuelo(int id, Avion avion, Terminal terminal, Pista pista){
		this.id=id;
		this.avion=avion;
		this.terminal=terminal;
		this.pista=pista;
	}
	
	public Vuelo(Avion avion, Terminal terminal, Pista pista){
		this.avion=avion;
		this.terminal=terminal;
		this.pista=pista;
	}
	public int getId(){
		return id;
	}
	public Avion getAvion(){
		return avion;
	}
	public Terminal getTerminal(){
		return terminal;
	}
	public void setAvion(Avion avion){
		this.avion=avion;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setTerminal(Terminal terminal){
		this.terminal=terminal;
	}
	
	public String toString(){
		String a="Id: "+id+" Vuelo: "+avion+" Terminal: "+terminal+" Pista: "+pista;
		return a;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avion == null) ? 0 : avion.hashCode());
		result = prime * result + id;
		result = prime * result + ((pista == null) ? 0 : pista.hashCode());
		result = prime * result + ((terminal == null) ? 0 : terminal.hashCode());
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
		Vuelo other = (Vuelo) obj;
		if (avion == null) {
			if (other.avion != null)
				return false;
		} else if (!avion.equals(other.avion))
			return false;
		if (id != other.id)
			return false;
		if (pista == null) {
			if (other.pista != null)
				return false;
		} else if (!pista.equals(other.pista))
			return false;
		if (terminal == null) {
			if (other.terminal != null)
				return false;
		} else if (!terminal.equals(other.terminal))
			return false;
		return true;
	}
	
	
}
