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

			c = DriverManager.getConnection("jdbc:sqlite:./db/"+ nombre); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ______________________________________________________TRABAJADOR______________________________________________
	public void createTablaTrabajador() {
		try {
			Statement stmt1 = c.createStatement();

			String p = "create table TRABAJADOR (" + "id integer primary key autoincrement, "  
					+"aerolinea_id REFERENCES aerolinea(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "nombre text not null," + "fechaDeNacimiento DATE," + "fechaInicioTrabajo DATE)"
					 + ";";

			stmt1.executeUpdate(p);
			stmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertTablaTrabajador(Trabajador trabajador) {
		try {

			String o = "Insert into Trabajador (aerolinea_id, nombre, fechaDeNacimiento,fechaInicioTrabajo)"
					+ "values (?,?,?,?);";

			PreparedStatement prep = c.prepareStatement(o);
			prep.setInt(1, trabajador.getAerolinea().getId());
			prep.setString(2, trabajador.getNombre());
			prep.setDate(3, trabajador.getFechaDeNacimiento());
			prep.setDate(4, trabajador.getFechaInicioTrabajo());
			
			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	public void cerrarConexion(){
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				int aerolinea1=rs.getInt("aerolinea_id");
				Aerolinea aerolinea=seleccionarAerolineaPorId(aerolinea1);
				String nombre=rs.getString("nombre");
				Date fechaNacimiento=rs.getDate("fechaDeNacimiento");
				Date fechaContrato=rs.getDate("fechaInicioTrabajo");
			Trabajador trb=new Trabajador(id, aerolinea, nombre, fechaNacimiento, fechaContrato);
			trabajador.add(trb);
			}
			
			
			stmt5.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return trabajador;
				
		}
	
	public void borrarPasajeroPorId(int idBorraPasajero){
		String sql="DELETE FROM pasajero WHERE id=?";
		try{
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1, idBorraPasajero);
			prep.executeUpdate();
			prep.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
			p.close();
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
	
	public List<Pista> seleccionarPistaPorEstado(String estadoP){
		List<Pista> pista=new ArrayList<Pista>();
		try {
			String sql="SELECT * FROM pista WHERE estado=?";			
			PreparedStatement p=c.prepareStatement(sql);
			p.setString(1, estadoP);
			p.executeQuery();
			ResultSet rs=p.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String estado=rs.getString("estado");
				String orientacion=rs.getString("orientacion");
				int longitud=rs.getInt("longitud");
				Pista pistaNueva=new Pista(id, estado, orientacion, longitud);
				pista.add(pistaNueva);
								
			}
			p.close();
		}
		catch (SQLException e) {
						e.printStackTrace();
		}		
		return pista;
	}
	
	public Pista seleccionarPistaPorId(int idP){
		Pista pista=null;
		try{
			String sql="SELECT * FROM pista WHERE id=? ";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1, idP);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String estado=rs.getString("estado");
				String orientacion=rs.getString("orientacion");
				int longitud=rs.getInt("longitud");
				pista=new Pista(id, estado, orientacion, longitud);
				prep.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return pista;
	}
	
	
	
	
	public void actualizarEstadoTablaPista(int idActP, String estadoPista){
		
		try{
			String sql="UPDATE pista SET estado=? WHERE id=?";
			PreparedStatement pre=c.prepareStatement(sql);
			pre.setString(1, estadoPista);
			pre.setInt(2, idActP);			
			pre.executeUpdate();
			pre.close();						
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void borrarPistaPorId(int idPistaBorrar){
		
		try{
		String sql="DELETE FROM pista WHERE id=?";
		PreparedStatement prep=c.prepareStatement(sql);
		prep.setInt(1, idPistaBorrar);
		prep.executeUpdate();
		prep.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void cerrarTodasLasPistas(){
		try{
			String sql="UPDATE pista SET estado='Cerrada'";
			Statement stmt=c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void abrirTodasLasPistas(){
		try{
			String sql="UPDATE pista SET estado='Abierta'";
			Statement stmt=c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
 //*****************************************************************************************

	// ---------------------MODELO---------------------------------------------------------

	public void createTablaModelo() {
		Statement stmt1;
		try {
			stmt1 = c.createStatement();

			String p = "create table MODELO (id integer primary key autoincrement, asiento integer not null,"
					+ "nombre text not null," + "capacidad integer not null);";
			stmt1.executeUpdate(p);
			
			stmt1.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertTablaModelo(Modelo modelo) {
		try {
		
			String sql = "Insert into Modelo (capacidad, nombre, asiento)" + "values (?,?,?);";
		
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, modelo.getCapacidad());
			prep.setString(2, modelo.getNombre());
			prep.setString(3, modelo.getAsientos());
			
			prep.executeUpdate();
			prep.close();
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
			stm1.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ListaModelo;
	}
	
	public Modelo seleccionarModeloPorId(int idM){
		Modelo modelo=null;
		try{
			String sql="SELECT * from MODELO WHERE id=?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1, idM);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				int capacidad=rs.getInt("capacidad");
				String nombre=rs.getString("nombre");
				String asientos=rs.getString("asiento");
			modelo=new Modelo(id, capacidad, nombre, asientos);
			prep.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return modelo;
	}
	//*********************************************************************************

	// ______________________________________________AEROLINEA__________________________________

	public void createTablaAerolinea(){
		
		// por qué el createAerolinea tiene esos atributos???
		//habiamos copiado y pegado de modelo
		
		try{
			Statement stmt1 = c.createStatement();
			
			String sql = "create table AEROLINEA (" + "id integer, " + "nombre text not null," 
			+ "aeropuertoBase text not null,"+"nacionalidad text default internacional,"
					+ "primary key (id));";
			stmt1.executeUpdate(sql);
			stmt1.close();
		    }
		catch(Exception e){
			e.printStackTrace();
			}
		
		
		}
	public void insertTablaAerolinea(Aerolinea aerolinea){
		
		try{
		
		String sql = "Insert into Aerolinea (nombre, aeropuertoBase, nacionalidad)" + "values (?,?,?);";
		PreparedStatement  p = c.prepareStatement(sql);
		p.setString(1, aerolinea.getNombre());
		p.setString(2, aerolinea.getAeropuertoBase());
		p.setString(3, aerolinea.getNacionalidad());
		p.executeUpdate();
		p.close();
		
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
		stm.close();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return ListaAerolinea;
	
	
	
	}
	
	public Aerolinea seleccionarAerolineaPorId(int idA){
		Aerolinea aerolinea=null;
		try{
			String sql="SELECT * from AEROLINEA WHERE id=?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1,idA);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String nombre=rs.getString("nombre");
				String aeropuertoBase=rs.getString("aeropuertoBase");
				String nacionalidad=rs.getString("nacionalidad");
				aerolinea=new Aerolinea(id, nombre, aeropuertoBase, nacionalidad);
				prep.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return aerolinea;
	}
	//*********************************************************************************************
		
		//--------------------------------Avion----------------------------------------
	
	public void createTablaAvion(){
		try{
		Statement stmt2= c.createStatement();
		String sql="create table AVION(id integer primary key autoincrement,"
				+ " modelo_id REFERENCES modelo(id)," 
				+ " aerolinea_id REFERENCES aerolinea(id) ON UPDATE CASCADE ON DELETE SET NULL);"; 
		// taller_id REFERENCES contrato(id) ON UPDATE CASCADE ON DELETE SET NULL)
		stmt2.executeUpdate(sql);
		
		stmt2.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertTablaAvion(Avion avion){
		try{
		String sql="INSERT into avion(aerolinea_id, modelo_id)"+"values(?,?);";
		PreparedStatement p=c.prepareStatement(sql);
		p.setInt(1, avion.getAerolinea().getId()); // es de tres (entró)
		p.setInt(2, avion.getModelo().getId()); // otro más de 3 (y también entró)
		p.executeUpdate();
		p.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Avion> selectAvion(){
		List<Avion> ListaAvion=new ArrayList<Avion>();
		
		try {
			Statement stm=c.createStatement();
			String sql="SELECT * from AVION";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()){
			int id=rs.getInt("id");
			int aerolinea1 = rs.getInt("aerolinea_id");
			Aerolinea aerolinea=seleccionarAerolineaPorId(aerolinea1);
			
			int modelo1= rs.getInt("modelo_id");
			Modelo modelo=seleccionarModeloPorId(modelo1);
			Avion av1=new Avion(id, aerolinea, modelo);
			ListaAvion.add(av1);
			}	
			stm.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ListaAvion;
	}
	public Avion seleccionarAvionPorId(int idA){
		Avion avion=null;
		try{
			String sql="SELECT * from AVION WHERE id=?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1,idA);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				int id = rs.findColumn("id");
				int aerolinea1 = rs.getInt("aerolinea_id");
				Aerolinea aerolinea = seleccionarAerolineaPorId(aerolinea1);
				int modelo1 = rs.getInt("modelo_id");
				Modelo modelo = seleccionarModeloPorId(modelo1);
						
			
				
				avion=new Avion(id,aerolinea, modelo);
				prep.close();
			}}
			catch(Exception e){
				e.printStackTrace();
			}
		return avion;
		 
			}
		//*****************************************************************************************
	//---------------------------BILLETE--------------------------------------------
	public void createTablaBillete(){        
		try {
			Statement stm=c.createStatement();
			String sql="create table BILLETE(ID integer primary key autoincrement, ASIENTO integer not null, CLASE text not null,"
					+ "pasajero_id integer REFERENCES pasajero(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "vuelo_id integer REFERENCES vuelo(id) ON UPDATE CASCADE ON DELETE SET NULL);";
			
			stm.executeUpdate(sql);
			stm.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insertTablaBillete(Billete billete){
		String sql="INSERT into BILLETE(clase, asiento, vuelo_id, pasajero_id)"+"values(?,?,?,?);";
		try {
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setString(1, billete.getClase());
			prep.setInt(2, billete.getAsiento());
			prep.setInt(3, billete.getVuelo().getId());
			prep.setInt(4, billete.getPasajero().getId());
			prep.executeUpdate();
			prep.close();
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
			int vuelo1 = rs.getInt("vuelo_id");
			Vuelo vuelo = seleccionarVueloPorId(vuelo1);
			int pasajero1 = rs.getInt("pasajero_id");
			Pasajero pasajero=seleccionarPasajeroPorId(pasajero1);
			int asiento=rs.getInt("asiento");
			Billete b1=new Billete(id, clase, asiento, vuelo, pasajero);
			ListaBillete.add(b1);
		}
		stm.close();
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return ListaBillete;
	}
	
	
	//----------------------------------------------------EQUIPAJE-------------------------------------------------
	
	
	public void createTablaEquipaje(){
		try{
		Statement stm=c.createStatement();
		String sql="CREATE table EQUIPAJE(id integer primary key autoincrement,"
				+ "DIMENSION integer not null, PESO integer not null, COLOR text not null,"
				+ "pasajero_id integer REFERENCES pasajero(id) ON UPDATE CASCADE ON DELETE SET NULL,"
				+ "vuelo_id integer REFERENCES pasajero(id) ON UPDATE CASCADE ON DELETE SET NULL);"; 
		stm.executeUpdate(sql);
		stm.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void insertTablaEquipaje(Equipaje equipaje){
		String sql="INSERT into EQUIPAJE(dimension, peso, color, pasajero_id, vuelo_id)"+"values(?,?,?,?,?);";
		try{
		PreparedStatement pstm=c.prepareStatement(sql);
		pstm.setInt(1,equipaje.getDimension());
		pstm.setInt(2, equipaje.getPeso());
		pstm.setString(3,equipaje.getColor());
		pstm.setInt(4,equipaje.getPasajero().getId());
		pstm.setInt(5, equipaje.getVuelo().getId());
		pstm.executeUpdate();
		pstm.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Equipaje> selectEquipaje(){
		List<Equipaje> listaEquipaje=new ArrayList<Equipaje>();
		try{
			Statement stm=c.createStatement();
			String sql="SELECT * from EQUIPAJE";
			ResultSet rs=stm.executeQuery(sql);
			
			while(rs.next()){
				int id=rs.getInt("id");
				int dimension=rs.getInt("dimension");
				int peso=rs.getInt("peso");
				int vuelo1 = rs.getInt("vuelo_id");
				Vuelo vuelo=seleccionarVueloPorId(vuelo1);
				int pasajero1 =rs.getInt("pasajero_id");
				Pasajero pasajero = seleccionarPasajeroPorId(pasajero1);
				String color=rs.getString("color");
				Equipaje equipaje=new Equipaje(id, dimension, peso, color,pasajero,vuelo);
				listaEquipaje.add(equipaje);
			}
			stm.close();
		}
			catch(Exception e){
				e.printStackTrace();
		
		}
		return listaEquipaje;
	}
	public List<Equipaje> seleccionarEquipajePorColor(String color){
		List<Equipaje> equipaje=new ArrayList<Equipaje>();
		try{
			String sql="SELECT * FROM equipaje WHERE color=?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setString(1, color);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
			int id=rs.getInt("id");
			int dimension=rs.getInt("dimension");
			int peso=rs.getInt("peso");
			String color2=rs.getString("color");
			int idPasajero=rs.getInt("pasajero_id");
			Pasajero pasajero=seleccionarPasajeroPorId(idPasajero);
			int idVuelo=rs.getInt("vuelo_id");
			Vuelo vuelo=seleccionarVueloPorId(idVuelo);
			Equipaje equipaje2=new Equipaje(id, dimension, peso, color2, pasajero, vuelo);
			equipaje.add(equipaje2);
			}
			prep.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return equipaje;
	}

	
		
	//----------------------------------------------------PASAJERO-------------------------------------------------------	
	public void createTablaPasajero(){
		try{
		Statement stm=c.createStatement();
		String sql="CREATE table PASAJERO(ID integer primary key autoincrement, NOMBRE text not null,"
				+ "NACIONALIDAD text default internacional, NPASAPORTE integer not null, SEXO text not null,"
				+ "FechaNacimiento date);"; 
		stm.executeUpdate(sql);
		stm.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void insertTablePasajero(Pasajero pasajero){
		String sql="INSERT into PASAJERO(nombre, nacionalidad, sexo, npasaporte, fechaNacimiento)"+"values(?,?,?,?,?);";//COMPROBAR
		try{
		PreparedStatement pstm=c.prepareStatement(sql);
		pstm.setString(1, pasajero.getNombre());
		pstm.setString(2, pasajero.getNacionalidad());
		pstm.setString(3, pasajero.getSexo());
		pstm.setInt(4, pasajero.getNPasaporte());
		pstm.setDate(5, pasajero.getFechaDeNacimiento());
		pstm.executeUpdate();
		pstm.close();
	
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
			Date fechaDeNacimiento=rs.getDate("fechaNacimiento");
			Pasajero pasajero1=new Pasajero(id, nombre, nacionalidad, sexo, nPasaporte, fechaDeNacimiento);
			listaPasajero.add(pasajero1);
		}
		stm.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return listaPasajero;
	}
	public Pasajero seleccionarPasajeroPorId(int idP){
		Pasajero pasajero=null;
		try{
			String sql="SELECT * from PASAJERO WHERE id=?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1,idP);
			prep.executeQuery();
			ResultSet rs=prep.executeQuery();
			while(rs.next()){
				int id=rs.getInt("id");
				String nombre=rs.getString("nombre");
				String nacionalidad=rs.getString("nacionalidad");
				String sexo=rs.getString("sexo");
				int nPasaporte=rs.getInt("nPasaporte");
				Date fechaDeNacimiento=rs.getDate("fechaNacimiento");
				pasajero=new Pasajero(id, nombre, nacionalidad, sexo, nPasaporte, fechaDeNacimiento);
				prep.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return pasajero;
	}
	
	
	//***********************************************************************************************************
	
		
	//---------------------------------------------------TERMINAL--------------------------------------------------------
	
		public void createTablaTerminal(){
			try{
				Statement stm=c.createStatement();
				String sql="CREATE table TERMINAL(ID integer primary key autoincrement,"
						+ "NOMBRE text not null, nPistas integer not null);"; 
				stm.executeUpdate(sql);
				stm.close();
				
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
		public void insertTablaTerminal(Terminal terminal){
			String sql="INSERT into TERMINAL(nombre, nPistas)"+"values(?,?);"; // COMPROBAR ESTO
			try{
			PreparedStatement pstm=c.prepareStatement(sql);
			pstm.setString(1, terminal.getNombre());
			pstm.setInt(2, terminal.getNumeroDePistas());
			pstm.executeUpdate();
			pstm.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public List<Terminal> selectTerminal(){
			List<Terminal> listaTerminal=new ArrayList<Terminal>();
			try{
			Statement stm=c.createStatement();
			String sql="SELECT * from TERMINAL";
			ResultSet rs=stm.executeQuery(sql);
			while(rs.next()){
			int id=rs.getInt("id");
			String nombre=rs.getString("nombre");
			int numeroDePistas=rs.getInt("nPistas");
			
			Terminal terminal1=new Terminal(id, nombre, numeroDePistas);
			
			listaTerminal.add(terminal1);
			}
			stm.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return listaTerminal;
		}
		public Terminal seleccionarTerminalPorId(int idT){
			Terminal terminal=null;
			try{
				String sql="SELECT * from TERMINAL WHERE id=?";
				PreparedStatement prep=c.prepareStatement(sql);
				prep.setInt(1, idT);
				prep.executeQuery();
				ResultSet rs=prep.executeQuery();
				while(rs.next()){
					int id=rs.getInt("id");
					String nombre=rs.getString("nombre");
					int numeroDePistas=rs.getInt("nPistas");
				terminal=new Terminal(id, nombre, numeroDePistas);
				prep.close();
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return terminal;
		}
		
	
	
	
	//**********************************************************************
	
	
	//----------------------------------------------------VUELO------------------------------------------------------------
			
		public void createTablaVuelo(){
			try{
			Statement stm=c.createStatement();
			String sql="CREATE table VUELO(ID integer primary key autoincrement,"
					+ "id_terminal integer REFERENCES terminal(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "id_avion integer REFERENCES avion(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "id_pista integer REFERENCES pista(id) ON UPDATE CASCADE ON DELETE SET NULL);"; 
			stm.executeUpdate(sql);
			stm.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public void insertTablaVuelo(Vuelo vuelo){
			String sql="INSERT into VUELO(id_avion, id_terminal, id_pista)"+"values(?,?,?);"; //comprobar también
			try{
				PreparedStatement pstm=c.prepareStatement(sql);
				pstm.setObject(1, vuelo.getAvion().getId());
				pstm.setObject(2, vuelo.getTerminal().getId());
				pstm.setObject(3, vuelo.getPista().getId());
				pstm.executeUpdate();
				pstm.close();
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
				int id = rs.getInt("id");
				int terminalId = rs.getInt("id_terminal");
				Terminal terminal=seleccionarTerminalPorId(terminalId);
				int avionId = rs.getInt("id_avion");
				Avion avion=seleccionarAvionPorId(avionId);
				int pistaId = rs.getInt("id_pista");
				Pista pista=seleccionarPistaPorId(pistaId);
			Vuelo v=new Vuelo(id, avion, terminal, pista);
			listaVuelo.add(v);
			
			}
			stm.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return listaVuelo;
		}
		
		public Vuelo seleccionarVueloPorId(int idV){
			Vuelo vuelo=null;
			try{
				String sql=("SELECT * from VUELO WHERE id=?");
				PreparedStatement prep=c.prepareStatement(sql);
				prep.setInt(1,idV);
				prep.executeQuery();
				ResultSet rs=prep.executeQuery();
				while(rs.next()){
					int id=rs.getInt("id");
					int avion1=rs.getInt("id_avion");
					Avion avion=seleccionarAvionPorId(avion1);
					int pista1=rs.getInt("id_pista");
					Pista pista=seleccionarPistaPorId(pista1);
					int terminal1=rs.getInt("id_terminal");
					Terminal terminal=seleccionarTerminalPorId(terminal1);
					vuelo=new Vuelo(id,avion,terminal,pista);
					prep.close();
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return vuelo;
		}
		
		public void borrarVueloPorId(int idBorrar){
			String sql="DELETE from vuelo WHERE id=?";
			try{
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setInt(1, idBorrar);
			prep.executeUpdate();
			prep.close();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		
		
		
		//********************************************************************	
}





