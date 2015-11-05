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
import pojo.Modelo;
import pojo.Pista;
import pojo.Trabajador;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ListaModelo;
	}
	

	// ______________________________________________AEROLINEA__________________________________

	public void createTablaAerolinea(){
		
		
		
		try{
			Statement stmt1 = c.createStatement();
			
			String sql = "create table Aerolinea (" + "id integrer, " + "asiento integrer not null," 
			+ "nombre text not null,"
					+ "capacidad integrer not null," + "primary key (id)" + ")" + ";";
			stmt1.executeQuery(sql);
			c.close();
		    }
		catch(Exception e){
			e.printStackTrace();
			}
		
		
		}
	public void insertTablaAerolinea(Aerolinea aerolinea){
		
		try{
		//QUE PASA AQUI?¿?
		//Statement stmt1 = c.createStatement();
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
		//holaholasrdtfcvjydhfdgfkgjyfhkvj,hg jeeeeFFFEEEEEEEDE
		
		
		
		
		
		
		
		
		
		
		
		
		

}

}
