package dbM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Aerolinea;
import pojo.Avion;
import pojo.Billete;
import pojo.Equipaje;
import pojo.Modelo;
import pojo.Pasajero;
import pojo.Pista;
import pojo.Terminal;
import pojo.Trabajador;
import pojo.Vuelo;

public class DbManager {

	private Connection c;

	public DbManager(String nombre) {

		try {
			Class.forName("org.sqlite.JDBC");

			c = DriverManager.getConnection("jdbc:sqlite:" + nombre); //./db/clara.db
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ______________________________________________________TRABAJADOR______________________________________________
	public void createTablaTripulacion() {
		try {
			Statement stmt1 = c.createStatement();

			String p = "create table TRABAJADOR (" + "id integer primary key autoincrement, " + "tripulacion text not null,"
					+ "nombre text not null," + "fechaDeNacimiento DATE," + "fechaInicioTrabajo DATE)"
					 + ";";

			stmt1.executeUpdate(p);
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertTablaTripulacion(Trabajador trabajador) {
		try {

			String o = "Insert into Trabajador (tripulacion, nombre, fechaDeNacimiento,fechaInicioTrabajo)"
					+ "values (?,?,?,?);";

			PreparedStatement prep = c.prepareStatement(o);
			prep.setString(1, trabajador.getTripulacion());
			prep.setString(2, trabajador.getNombre());
			prep.setDate(3, trabajador.getFechaDeNacimiento());
			prep.setDate(4, trabajador.getFechaInicioTrabajo());
			
			prep.executeUpdate();
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public List<Trabajador> selectTrabajador(){
		List<Trabajador> trabajador=new ArrayList<Trabajador>();
		try{
			Statement stmt5=c.createStatement();
			String sql="select * from TRABAJADOR";
			ResultSet rs=stmt5.executeQuery(sql);
			while(rs.next()){
				int id =rs.getInt("id");
				String tripulacion=rs.getString("tripulacion");
				String nombre=rs.getString("nombre");
				Date fechaNacimiento=rs.getDate("fechaDeNacimiento");
				Date fechaContrato=rs.getDate("fechaInicioTrabajo");
			Trabajador trb=new Trabajador(id, tripulacion, nombre, fechaNacimiento, fechaContrato);
			trabajador.add(trb);
			}
			
			
			c.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return trabajador;
		
		
	}

	// ________________________________________PISTA________________________________

	public void createTablaPista() {
		try {
			Statement stmt1 = c.createStatement();
			String p = "create table PISTA (" + "id integer, " + "estado text not null," + "orientacion text not null,"
					+ "longitud integrer not null," + "primary key (id)" + ")" + ";";

			stmt1.executeUpdate(p);
			stmt1.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void insertTablaPista(Pista pista) {

		String sql = "Insert into Pista (estado, orientacion, longitud) " + "values (?,?,?);";
		try {
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, pista.getEstado());
			p.setString(2, pista.getOrientacion());
			p.setInt(3, pista.getLongitud());
			p.executeUpdate();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Pista> selectPista() {

		List<Pista> listaPistas = new ArrayList<Pista>();

		try {

			Statement stmt1 = c.createStatement();

			String sql = "select * from PISTA";
			ResultSet rs = stmt1.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String estado = rs.getString("estado");
				String orientacion = rs.getString("orientacion");
				int longitud = rs.getInt("longitud");

				Pista p1 = new Pista(id, estado, orientacion, longitud);
				listaPistas.add(p1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPistas;

	}

	// ---------------------MODELO---------------------------------------------------------

	public void createTablaModelo() {
		Statement stmt1;
		try {
			stmt1 = c.createStatement();

			String p = "create table MODELO (" + "id integrer primary key autoincrement, " + "asiento integrer not null,"
					+ "nombre text not null," + "capacidad integrer not null,"  
					+ ")" + ";";
			stmt1.executeUpdate(p);
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertTablaModelo(Modelo modelo) {
		try {
		
			String sql = "Insert into Modelo (capacidad, nombre, asiento)" + "values (?,?,?);";
		
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, modelo.getCapacidad());
			prep.setString(2, modelo.getNombre());
			prep.setString(3, modelo.getAsiento());
			
			prep.executeUpdate();
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Modelo> selectModelo() {

		List<Modelo> ListaModelo = new ArrayList<Modelo>();
		try {

			Statement stm1 = c.createStatement();

			String sql = "Select * from MODELO";
			ResultSet rs = stm1.executeQuery(sql);
			while (rs.next()) {
				int id=rs.getInt("id");
				int capacidad = rs.getInt("capacidad");
				String asiento = rs.getString("asiento");
				String nombre = rs.getString("nombre");

				Modelo m1 = new Modelo(id, capacidad, nombre, asiento);
				ListaModelo.add(m1);
			}
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ListaModelo;
	}
	

	// ______________________________________________AEROLINEA__________________________________

	public void createTablaAerolinea(){
		
		// por qué el createAerolinea tiene esos atributos???
		//habiamos copiado y pegado de modelo
		
		try{
			Statement stmt1 = c.createStatement();
			
			String sql = "create table Aerolinea (" + "id integrer, " + "nombre text not null," 
			+ "aeropuertoBase text not null,"+"nacionalidad text default internacional,"
					+ "primary key (id)" + ")" + ";";
			stmt1.executeQuery(sql);
			c.close();
		    }
		catch(Exception e){
			e.printStackTrace();
			}
		
		
		}
	public void insertTablaAerolinea(Aerolinea aerolinea){
		
		try{
		
		
		String sql = "Insert into Aerolinea(nombre, aeropuertoBase, nacionalidad)" + "values (?,?,?);";
		PreparedStatement  p = c.prepareStatement(sql);
		p.setString(1, aerolinea.getNombre());
		p.setString(2, aerolinea.getAeropuertoBase());
		p.setString(3, aerolinea.getNacionalidad());
		p.executeUpdate();
		c.close();
		
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}
	public List<Aerolinea> selectAerolinea() {
		List<Aerolinea> ListaAerolinea=new ArrayList<Aerolinea>();
	try {
		Statement stm=c.createStatement();
		String sql="SELECT * from aerolinea";
		ResultSet rs=stm.executeQuery(sql);
		while(rs.next()){
			int id=rs.getInt("id");
			String nombre=rs.getString("nombre");
			String aeropuertoBase=rs.getString("aeropuertoBase");
			String nacionalidad=rs.getString("nacionalidad");
			
			Aerolinea ae1=new Aerolinea(id, nombre, aeropuertoBase, nacionalidad);
			ListaAerolinea.add(ae1);
		}
		c.close();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return ListaAerolinea;
	
	
	
	}
		
		//--------------------------------Avion----------------------------------------
	
	public void createTablaAvion(){
		try{
		Statement stm=c.createStatement();
		String sql="create table Avion("  // taller_id REFERENCES contrato(id) ON UPDATE CASCADE ON DELETE SET NULL)
		stm.executeQuery(sql);
		
		c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertTablaAvion(){
		try{
		String sql="INSERT into avion(aerolinea, modelo)"+"values(?,?);";
		PreparedStatement p=c.prepareStatement(sql);
		p.setAerolinea(1, avion.getAerolinea()); // es de tres
		p.setModelo(2, avion.getModelo()); // otro más de 3
		p.executeUpdate();
		c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	// HAY VARIOS FALLOS PORQUE NO SE QUE HAY QUE HACER CON LAS FOREIGN KEYS
	public List<Avion> selectAvion(){
		List<Avion> ListaAvion=new ArrayList<Avion>();
		
		try {
			Statement stm=c.createStatement();
			String sql="SELECT * from AVION";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()){
			int id=rs.getInt("id");
			Aerolinea aerolinea=rs.getAerolinea("aerolinea");
			Modelo modelo=rs.getModelo("modelo");
			
			Avion av1=new Avion(id, aerolinea, modelo);
			ListaAvion.add(av1);
			}	
			c.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ListaAvion;
	}
		
	//---------------------------BILLETE--------------------------------------------
	public void createTablaBillete(){        //******falta el SQL******
		try {
			Statement stm=c.createStatement();
			String sql=""; //AQUÍ
			stm.executeQuery(sql);
			c.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insertTablaBillete(){
		String sql="INSERT into BILLETE(clase, asiento, vuelo, pasajero,)"+"values(?,?,?,?);";
		try {
			PreparedStatement prep=c.prepareStatement(sql);
			//prep.setString(1, billete.getClase());
			//prep.setInt(2, billete.getAsiento());
			//prep.setVuelo
			//prep.setPasajero
			prep.executeUpdate();
			c.close();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public List<Billete> selectBillete(){ 
		
		
	List<Billete> ListaBillete=new ArrayList<Billete>();
	try{
		Statement stm=c.createStatement();
		String sql="SELECT * from BILLETE";
		ResultSet rs=stm.executeQuery(sql);
		
		while(rs.next()){
			int id=rs.getInt("id");
			String clase=rs.getString("clase");
			Vuelo vuelo=rs.getVuelo("vuelo");
			Pasajero pasajero=rs.getPasajero("pasajero");
			int asiento=rs.getInt("asiento");
			Billete b1=new Billete(id, clase, asiento, vuelo, pasajero);
			ListaBillete.add(b1);
		}
		c.close();
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return ListaBillete;
	}
	
	//----------------------------------------------------EQUIPAJE-------------------------------------------------
	
	
	public void createTableEquipaje(){
		try{
		Statement stm=c.createStatement();
		String sql="CREATE table EQUIPAJE("; // T.E.R.M.I.N.A.R.R.R.R.R.R.R
		stm.executeQuery(sql);
		c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void insertTablaEquipaje(){
		String sql="INSERT into EQUIPAJE(dimension, peso, color)"+"values(?,?,?);";
		try{
		PreparedStatement pstm=c.prepareStatement(sql);
		pstm.setInt(1,equipaje.getDimension());
		pstm.setInt(2, equipaje.getPeso());
		pstm.setString(3,equipaje.getColor());
		pstm.executeUpdate();
		c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Equipaje> insertEquipaje(){
		List<Equipaje> listaEquipaje=new ArrayList<Equipaje>();
		try{
			Statement stm=c.createStatement();
			String sql="INSERT * from EQUIPAJE";
			ResultSet rs=stm.executeQuery(sql);
			
			while(rs.next()){
				int id=rs.getInt("id");
				int dimension=rs.getInt("dimension");
				int peso=rs.getInt("peso");
				String color=rs.getString("color");
				Equipaje equipaje=new Equipaje(id, dimension, peso, color);
				listaEquipaje.add(equipaje);
			}
			c.close();
		}
			catch(Exception e){
				e.printStackTrace();
		
		}
		return listaEquipaje;
	}
	
		
		
	
	
	
	
	
	
	
	
	
	
		
	//----------------------------------------------------PASAJERO-------------------------------------------------------	
	public void createTablaPasajero(){
		try{
		Statement stm=c.createStatement();
		String sql="CREATE table PASAJERO("; //TERMINAAAAAAAR
		stm.executeQuery(sql);
		c.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void insertTablePasajero(){
		String sql="INSERT into PASAJERO(nombre, nacionalidad, sexo, npasaporte, fechaDeNacimiento"+"values(?,?,?,?,?);";//COMPROBAR
		try{
		PreparedStatement pstm=c.prepareStatement(sql);
		pstm.setString(1, pasajero.getNombre());
		pstm.setString(2, pasajero.getNacionalidad());
		pstm.setString(3, pasajero.getSexo());
		pstm.setInt(4, pasajero.getNPasaporte());
		pstm.setDate(5, fechaDeNacimiento);
		pstm.executeUpdate();
		c.close();
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<Pasajero> selectPasajero(){
		List<Pasajero> listaPasajero=new ArrayList<Pasajero>();
		String sql="SELECT * from PASAJERO";
		try{
		Statement stm=c.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		while(rs.next()){
			int id=rs.getInt("id");
			String nombre=rs.getString("nombre");
			String nacionalidad=rs.getString("nacionalidad");
			String sexo=rs.getString("sexo");
			int nPasaporte=rs.getInt("npasaporte");
			Date fechaDeNacimiento=rs.getDate("fechadenacimiento");
			Pasajero pasajero1=new Pasajero(id, nombre, nacionalidad, sexo, nPasaporte, fechaDeNacimiento);
			listaPasajero.add(pasajero1);
		}
		c.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return listaPasajero;
	}
	
	
	
	
	
	
	
	
	
	
		
	//---------------------------------------------------TERMINAL--------------------------------------------------------
	
		public void createTablaTerminal(){
			try{
				Statement stm=c.createStatement();
				String sql="CREATE table TERMINAL("; // TERMINARR FHIUFHIUEHIUFHIUHFUIH
				stm.executeQuery(sql);
				c.close();
				
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
		public void insertTablaTerminal(){
			String sql="INSERT into TERMINAL(nombre, numeroDePistas)"+"values(?,?);"; // COMPROBAR ESTO
			try{
			PreparedStatement pstm=c.prepareStatement(sql);
			pstm.setString(1, terminal.getNombre());
			pstm.setInt(2, terminal.getNumeroDePistas);
			pstm.executeUpdate();
			c.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public List<Terminal> selectTerminal(){
			List<Terminal> listaTerminal=new ArrayList<Terminal>();
			try{
			Statement stm=c.createStatement();
			String sql="SELECT * from TERMIANL";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()){
			String nombre=rs.getString("nombre");
			int numeroDePistas=rs.getInt("numeroDePistas");
			
			Terminal terminal1=new Terminal(nombre, numeroDePistas);
			
			listaTerminal.add(terminal1);
			}
			c.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return listaTerminal;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------------------VUELO------------------------------------------------------------
			
		public void createTablaVuelo(){
			try{
			Statement stm=c.createStatement();
			String sql="CREATE table VUELO("; // TERMINARRR
			stm.executeQuery(sql);
			c.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void insertTablaVuelo(){
			String sql="INSERT into VUELO(avion, terminal)"+"values(?,?);"; //comprobar también
			try{
				PreparedStatement pstm=c.prepareStatement(sql);
				pstm.setAvion(1, vuelo.getAvion());
				pstm.setTerminal(2, vuelo.getTerminal());
				pstm.executeUpdate();
				c.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public List<Vuelo> selectVuelo(){
			List<Vuelo> listaVuelo=new ArrayList <Vuelo>();
			String sql=("SELECT * from VUELO");
			try{
			Statement stm=c.createStatement();
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()){
			
			Avion avion=rs.getAvion();
			Terminal terminal=rs.getTerminal("terminal");
			Vuelo vuelo1=new Vuelo(avion, terminal);
			listaVuelo.add(vuelo1);
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		
}





