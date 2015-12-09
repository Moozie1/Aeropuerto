package dbM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import pojo.Aerolinea;
import pojo.Billete;
import pojo.Equipaje;
import pojo.Modelo;
import pojo.Pasajero;
import pojo.Pista;
import pojo.Trabajador;
import pojo.Vuelo;

public class DbMenu{
	
	private static DbManager gestor;
	private static Scanner sc=new Scanner(System.in);
	
	public static void opcion1(){
		
	
		System.out.println("¿Quieres crear las tablas? (y/n)");
		String crearTablas=sc.nextLine();
		if(crearTablas.equalsIgnoreCase("y") || crearTablas.equalsIgnoreCase("s")){
			
			gestor.createTablaModelo();
			gestor.createTablaPista();
			gestor.createTablaTrabajador();
			gestor.createTablaAerolinea();
			gestor.createTablaEquipaje();
			gestor.createTablaAvion();
			gestor.createTablaBillete();
			gestor.createTablaPasajero();
			gestor.createTablaTerminal();
			gestor.createTablaVuelo();
			
		
		}
	
		System.out.println("¿Qué quieres hacer? \n"
				+ "1.Añadir elemento \n"
				+ "2.Ver tabla \n"
				+ "3.Salir");
		
		
		int opcion1=Integer.parseInt(sc.nextLine());
		
		 switch(opcion1){
		 
		 case 1: 
			 //le he cambiado los eeeeeeeeenombres a los metodos de los menus, esto vende más
			 menuEscogerTablaAñadir();
	
			 break;
			 
		 case 2: 
			 menuEscogerTablaVer();
			
		 case 3: 
			 //menu3() eres basura hermano;
			 break;
		 default: 
			 System.out.println("Opción no válida");
			 opcion1();
		 }
		 
		 
	}
	
	public static void menuEscogerTablaAñadir(){
		System.out.println("¿En qué tabla quieres añadir el elemento?");
		System.out.println("1.Trabajador \n"
				+ "2.Aerolínea \n"
				+ "3.Modelo \n"
				+ "4.Pista \n"
				+ "5.Billete \n"
				+ "6.Pasajero \n"
				+ "7.Equipaje \n"
				+ "8.Terminal \n"
				+ "9.Vuelo \n"
				+ "10.Avión");
	
		int a=sc.nextInt();
		sc.nextLine();
		
		switch(a){
	
		case 1: 
			//Al hacer un select sobre trabajador, con las fechas no sale lo que debería salir (en la terminal)
			System.out.println("Añadiendo trabajador . . . ");
			System.out.println("¿A qué aerolínea pertenece?: ");
			gestor.selectAerolinea();
			System.out.println("Introduce id de la aerolinea a la que pertenece:");
			int idAe=sc.nextInt();
			Aerolinea aerolinea=gestor.seleccionarAerolineaPorId(idAe);
		
			
			System.out.println("Nombre del trabajador: ");
			String nombreTrabajador=sc.nextLine();
			
			System.out.println("Fecha de nacimiento (yyyy-MM-dd):");
			String fechaNacimiento1 = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate p = LocalDate.parse(fechaNacimiento1, formatter);
			Date fechaNacimiento =Date.valueOf(p);
			
			System.out.println("Fecha inicio contrato (yyyy-MM-dd):");
			String fechaContrato1=sc.nextLine();
			LocalDate p2 = LocalDate.parse(fechaContrato1, formatter);
			Date fechaContrato=Date.valueOf(p2);
			
			Trabajador trabajador1=new Trabajador(aerolinea, nombreTrabajador, fechaNacimiento, fechaContrato);
			gestor.insertTablaTrabajador(trabajador1);
			
			System.out.println("Añadido trabajador: "+trabajador1.toString());
			break;
		
		case 2:
			// FUNCIONA
			System.out.println("Añadiendo Aerolínea . . . ");
			System.out.println("Nombre: ");
			String nombreAerolinea=sc.nextLine();
			
			System.out.println("Aeropuerto base: ");
			String aeropuertoBase=sc.nextLine();
			
			System.out.println("Nacionalidad: ");
			String nacionalidad=sc.nextLine();
			
			Aerolinea aerolinea1=new Aerolinea(nombreAerolinea, aeropuertoBase, nacionalidad);
			gestor.insertTablaAerolinea(aerolinea1);
			
			System.out.println("Añadida aerolínea: "+aerolinea1.toString(aerolinea1));
			break;
		
		case 3:
			//Funciona
			System.out.println("Añadiendo modelo . . . ");
			System.out.println("Capacidad: ");
			int capacidad=sc.nextInt();
			sc.nextLine();
			
			System.out.println("Nombre: ");
			String nombreModelo=sc.nextLine();
			
			System.out.println("Asiento");
			String asientoModelo=sc.nextLine();
			
			Modelo modelo=new Modelo(capacidad, nombreModelo, asientoModelo);
			gestor.insertTablaModelo(modelo);
			
			System.out.println("Añadido modelo: "+modelo.toString());
			break;
		
		case 4:
			System.out.println("Añadiendo pista . . . ");
			
			System.out.println("Estado: ");
			String estado=sc.nextLine();
			
			System.out.println("Orientación: ");
			String orientacion=sc.nextLine();
			
			System.out.println("Longitud: ");
			int longitud=sc.nextInt();
			
			Pista pista=new Pista(estado, orientacion, longitud);
			gestor.insertTablaPista(pista);
			break;
			
		case 5:
			System.out.println("Añadiendo billete . . . ");
			
			System.out.println("Clase: ");
			String clase=sc.nextLine();
			
			System.out.println("Asiento (número):");
			int asiento=sc.nextInt();
			
			System.out.println("Vuelo: ");
			int id_vuelo=sc.nextInt();
			
			
			System.out.println("Pasajero: ");
			int id_pasajero=sc.nextInt();
			
			
			//Billete billete =new Billete(clase, asiento, id_vuelo, id_pasajero);
			
			//System.out.println("");
			break;
		case 6:
			System.out.println("Añadiendo pasajero . . . ");
			
			System.out.println("Nombre: ");
			String nombre=sc.nextLine();
			
			System.out.println("Nacionalidad:");
			String nacionalidadP=sc.nextLine();
			
			System.out.println("Sexo:");
			String sexo=sc.nextLine();
			
			System.out.println("Número de pasaporte: ");
			int nPasaporte=sc.nextInt();
			
			System.out.println("Fecha nacimiento (yyyy-MM-dd)");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (isr);
			String fechaNac;
			try {
				fechaNac = br.readLine();
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate p3 = LocalDate.parse(fechaNac, formato);
			Date fechaNac1=Date.valueOf(p3);
			
			
			Pasajero pasajero=new Pasajero(nombre, nacionalidadP, sexo, nPasaporte, fechaNac1);
			gestor.insertTablePasajero(pasajero);
			
			System.out.println("Añadido pasajero:  "+pasajero.toString());
			}
			catch (IOException e) {
				
				e.printStackTrace();
				
				}
			break;
		case 7:
			System.out.println("Añadiendo equipaje . . . ");
				
				
				
				
			break;
		case 8:
			System.out.println("Añadiendo terminal . . . ");
			
			break;
			
		case 9:
			System.out.println("Añadiendo vuelo . . . ");
			
			break;
		case 10:
			
			System.out.println("Añadiendo avión . . . ");
			
			break;
		
		default: 
			System.out.println(a+" no es válido");
			System.out.println("Seleccione una opción válida");
			menuEscogerTablaAñadir();
			
		}
	}
	//Este es el segundo menu, para imprimir la tabla que se seleccione
	public static void menuEscogerTablaVer(){
		
		System.out.println("¿Qué tabla quieres ver?");
		System.out.println("1.Trabajador \n"
				+ "2.Aerolínea \n"
				+ "3.Modelo \n"
				+ "4.Pista \n"
				+ "5.Billete \n"
				+ "6.Pasajero \n"
				+ "7.Equipaje \n"
				+ "8.Terminal \n"
				+ "9.Vuelo \n"
				+ "10.Avión");
	
		int aa=sc.nextInt();
		sc.nextLine();
		
		switch(aa){
		case 1: //Funciona
		
		List<Trabajador> trabajador1=gestor.selectTrabajador();
		 
		for(Trabajador trabajador:trabajador1){
			System.out.println(trabajador.toString());
		}
		
		 break;
		 
		case 2: //imprime las direcciones de memoria, no las aerolineas que están creadas
			List<Aerolinea> aerolinea1=gestor.selectAerolinea();
			for(Aerolinea aerolinea:aerolinea1){
				System.out.println(aerolinea.toString());
			}
			 break;
		case 3://Funciona
			List<Modelo> modelo1=gestor.selectModelo();
			for(Modelo modelo:modelo1){
				System.out.println(modelo.toString());
			}
			break;
		case 4://Funciona
			List<Pista> pista1=gestor.selectPista();
			for(Pista pista:pista1){
				System.out.println(pista.toString());
				
			}
			break;
		case 5: // NO FUNCIONA hay error en dmManager en el SELECT
			List<Billete> billete1=gestor.selectBillete();
			for(Billete billete:billete1){
				System.out.println(billete.toString());
			}
			break;
		case 6:
			List <Pasajero> pasajero1=gestor.selectPasajero();
			for(Pasajero pasajero:pasajero1){
				System.out.println(pasajero.toString());
			}
			break;
		case 7: List <Equipaje> equipaje1=gestor.selectEquipaje();
			for(Equipaje equipaje:equipaje1){
				System.out.println(equipaje.toString());				
			}
			break;
		}
	}
	
	public static void menu3(){
		opcion1();
	}
	public static void main(String []args) {
		gestor = new DbManager("Airport.db");
		opcion1();
		
	}
	
}

