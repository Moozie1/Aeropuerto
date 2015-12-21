package dbM;
/*DUDAS: 
 * 1. En seleccionar pista por estado solo imprime 1 pista. (Solucionado)
 * 2. En el menu de escoger que hacer, si no pones un número salta excepción y en el switch hay un default¿?
 * 3. Al intentar actualizar el estado de una pista, da error de que la query no devuelve ningun resultado(sql) (SOLUCIONADO)
 * 4. Con el método borrarPista pasa lo mismo que con el de actualizar, dice que la query no devuelve ningun resultado (SOLUCIONADO)
 * 5. Ni idea de JPA
 * 6. Al realizar un case de un switch ¿hay alguna manera de volver que no se llamar en todos los cases al primer método?
 * 7. 
 */
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
import pojo.Avion;
import pojo.Billete;
import pojo.Equipaje;
import pojo.Modelo;
import pojo.Pasajero;
import pojo.Pista;
import pojo.Terminal;
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
				+ "3.Otras opciones \n"
				+ "4.Salir");
		
		
		int opcion1=Integer.parseInt(sc.nextLine());
		if(opcion1<5){
		 switch(opcion1){
		 
		 case 1: 
			
			 menuEscogerTablaAñadir();
	
			 break;
			 
		 case 2: 
			 menuEscogerTablaVer();
			 
			break;
		 case 3: 
			 menuEscogerOtrasOpciones();
		 case 4:
			 System.exit(0);			
		 default: 
			 
				 System.out.println("Opción no válida");
				 opcion1();
			
		 }	 
		 }	
		else{
			System.out.println("Opción no válida");
			opcion1();
		}
		 
		 
	}
	
	public static void menuEscogerOtrasOpciones(){
		System.out.println("¿Qué quieres hacer?");
		System.out.println("1.Seleccionar pista por estado"
				+ "\n2.Actualizar estado pista"
				+"\n3.Borrar pista"
				+"\n4.Cerrar todas las pistas"
				+"\n5.Abrir todas las pistas");
		
		int x=sc.nextInt();
		sc.nextLine();
		switch(x){
		case 1:
			System.out.println("¿Qué pistas quieres ver? \n 1.Abierta(s) \n 2.Cerrada(s)");
			int xx=sc.nextInt();
			sc.nextLine();
			switch(xx){
				case 1:
					String estadoAbierta="Abierta";
					List<Pista> pista1=gestor.seleccionarPistaPorEstado(estadoAbierta);
					for(Pista pista:pista1){
						System.out.println(pista.toString());
					}
					break;
				case 2:
					String estadoCerrada="Cerrada";
					List<Pista> pistasCerradas=gestor.seleccionarPistaPorEstado(estadoCerrada);
					for(Pista pista:pistasCerradas){
						System.out.println(pista.toString());
					}
					break;
			}
			break;
			
		case 2:
			System.out.println("Indique el id de la pista que quiere actualizar");
			List<Pista> pista1=gestor.selectPista();
			for(Pista pista:pista1){
				System.out.println(pista.toString());				
			}
			int idPistaActualizarEstado=sc.nextInt();
			sc.nextLine();
			//Pista pistaSeleccionada=gestor.seleccionarPistaPorId(idPistaActualizarEstado);
			//Pista pistaSeleccionada=new Pista();
			System.out.println("¿Qué estado quieres asignar a esta pista?"
					+ "\n 1.Abierta \n 2.Cerrada");
			int estadoPista=sc.nextInt();
			sc.nextLine();
			if(estadoPista==1){
				String abierta="Abierta";
				gestor.actualizarEstadoTablaPista(idPistaActualizarEstado, abierta);
			
				System.out.println("Pista actualizada: ");			
			Pista pistaActualizada=gestor.seleccionarPistaPorId(idPistaActualizarEstado);
			System.out.println(pistaActualizada.toString());
			}
			if(estadoPista==2){
				String cerrada="Cerrada";
				gestor.actualizarEstadoTablaPista(idPistaActualizarEstado, cerrada);
				
				System.out.println("Pista actualizada: ");
				Pista pistaActualizada=gestor.seleccionarPistaPorId(idPistaActualizarEstado);
				System.out.println(pistaActualizada.toString());
			}
			
			break;
		case 3:
			System.out.println("Seleccione el id de la pista que quiere borrar");
			int idPistaBorrar=sc.nextInt();
			gestor.borrarPistaPorId(idPistaBorrar);
		
		
		case 4:
			gestor.cerrarTodasLasPistas();
			System.out.println("Todas las pistas están cerradas");
			break;
		
		case 5:
			gestor.abrirTodasLasPistas();
			System.out.println("Todas las pistas están abiertas");
			break;
		}
				
	}
	
	
			
			/*System.out.println("¿Qué pistas quiere ver? Abierta(s)/Cerrada(s)");
			String estado=sc.nextLine();
			if(estado.equalsIgnoreCase("abierta")||estado.equalsIgnoreCase("abiertas")){
				gestor.seleccionarPistaPorEstado(estado);
			}
			if (estado.equalsIgnoreCase("cerrada")||estado.equalsIgnoreCase("cerradas")){
			gestor.seleccionarPistaPorEstado(estado);
			}
			else{
				System.out.println("Parámetro no válido");
				menuEscogerOtrasOpciones();
			}
			
	
	*/
		
	
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
			
			List<Aerolinea> aerolinea1=gestor.selectAerolinea();
			for(Aerolinea aerolineaLista:aerolinea1){
				System.out.println(aerolineaLista.toString());
			}
			System.out.println("Introduce id de la aerolinea a la que pertenece:");
			int idAe=sc.nextInt();
			sc.nextLine();
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
			
			opcion1();
		
		case 2:
			// FUNCIONA
			System.out.println("Añadiendo Aerolínea . . . ");
			System.out.println("Nombre: ");
			String nombreAerolinea=sc.nextLine();
			
			System.out.println("Aeropuerto base: ");
			String aeropuertoBase=sc.nextLine();
			
			System.out.println("Nacionalidad: ");
			String nacionalidad=sc.nextLine();
			
			Aerolinea aerolinea4=new Aerolinea(nombreAerolinea, aeropuertoBase, nacionalidad);
			gestor.insertTablaAerolinea(aerolinea4);
			
			System.out.println("Añadida aerolínea: "+aerolinea4.toString());
			opcion1();
		
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
			opcion1();
		
		case 4:
			System.out.println("Añadiendo pista . . . ");
			
			System.out.println("Estado: ");
			String estado=sc.nextLine();
			
			System.out.println("Orientación: ");
			String orientacion=sc.nextLine();
			
			System.out.println("Longitud: ");
			int longitud=sc.nextInt();
			sc.nextLine();
			
			Pista pista=new Pista(estado, orientacion, longitud);
			gestor.insertTablaPista(pista);
			
			System.out.println("Añadida pista: "+pista.toString());
			opcion1();
			
		case 5:
			System.out.println("Añadiendo billete . . . ");
			
			System.out.println("Clase: ");
			String clase=sc.nextLine();
			
			System.out.println("Asiento (número):");
			int asiento=sc.nextInt();
			
			System.out.println("Vuelo: ");
			List<Vuelo> vuelo1=gestor.selectVuelo();
			for(Vuelo vuelo:vuelo1){
				System.out.println(vuelo.toString());
			}			
			System.out.println("Introduce id del vuelo al que pertenece:");
			int idV=sc.nextInt();
			Vuelo vuelo=gestor.seleccionarVueloPorId(idV);
			
					
			System.out.println("Pasajero: ");
			List <Pasajero> pasajeroLista=gestor.selectPasajero();
			for(Pasajero pasajero:pasajeroLista){
				System.out.println(pasajero.toString());
			}
			System.out.println("Introduce el pasajero al que pertenece");
			int idP=sc.nextInt();
			Pasajero pasajero=gestor.seleccionarPasajeroPorId(idP);
			
			
			
			Billete billete =new Billete(clase, asiento, vuelo, pasajero);
			gestor.insertTablaBillete(billete);
			
			System.out.println("Añadido billete: "+billete.toString());
			
			opcion1();
			
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
			
			
			Pasajero pasajero2=new Pasajero(nombre, nacionalidadP, sexo, nPasaporte, fechaNac1);
			gestor.insertTablePasajero(pasajero2);
			
			System.out.println("Añadido pasajero:  "+pasajero2.toString());
			}
			catch (IOException e) {
				
				e.printStackTrace();
				
				}
			opcion1();
		case 7:
			System.out.println("Añadiendo equipaje . . . ");
				
			System.out.println("Dimension: ");
			int dimension=sc.nextInt();
			
			System.out.println("Peso: ");
			int peso=sc.nextInt();
			sc.nextLine();
			System.out.println("Color ");
			String color=sc.nextLine();
			
			System.out.println("Pasajero:");
			List <Pasajero> pasajero1=gestor.selectPasajero();
			for(Pasajero pasajeroLista1:pasajero1){
				System.out.println(pasajeroLista1.toString());
			}
			System.out.println("Selecciona el id del pasajero al que pertenece:");
			int idPas=sc.nextInt();
			Pasajero pasajeroPorId=gestor.seleccionarPasajeroPorId(idPas);
			
			System.out.println("Vuelo:");
			List<Vuelo> vueloLista=gestor.selectVuelo();
			for(Vuelo vuelo5:vueloLista){
				System.out.println(vuelo5.toString());
			}
			System.out.println("Selecciona el id del vuelo al que pertenece:");
			int idVue=sc.nextInt();
			Vuelo vuelo6=gestor.seleccionarVueloPorId(idVue);
			
			Equipaje equipaje=new Equipaje(dimension, peso, color, pasajeroPorId, vuelo6);
			gestor.insertTablaEquipaje(equipaje);
			
			System.out.println("Añadido equipaje "+equipaje.toString());
				
			opcion1();
		case 8:
			System.out.println("Añadiendo terminal . . . ");
			System.out.println("Nombre:");
			String nombreTerminal=sc.nextLine();
			
			System.out.println("Numero de pistas:");
			int numeroDePistas=sc.nextInt();
			
			Terminal terminal=new Terminal(nombreTerminal, numeroDePistas);
			gestor.insertTablaTerminal(terminal);
			
			System.out.println("Añadida terminal"+terminal.toString());
			opcion1();
			
		case 9:
			System.out.println("Añadiendo vuelo . . . ");
			
			System.out.println("Avion:");
			List<Avion> avion1=gestor.selectAvion();
			for(Avion avion:avion1){
				System.out.println(avion.toString());
			}
			System.out.println("Seleccione el id del avion al que pertenece:");
			int idAvionVue=sc.nextInt();
			Avion avionVue=gestor.seleccionarAvionPorId(idAvionVue);
			
			System.out.println("Terminal:");
			List<Terminal> terminal1=gestor.selectTerminal();
			for(Terminal terminalLista:terminal1){
				System.out.println(terminalLista.toString());
			}
			System.out.println("Seleccionar el id de la terminal a la que pertenece:");
			int idTerm=sc.nextInt();
			Terminal terminalVue=gestor.seleccionarTerminalPorId(idTerm);
			
			System.out.println("Pista:");
			List<Pista> pista1=gestor.selectPista();
			for(Pista pistaLista:pista1){
				System.out.println(pistaLista.toString());
				
			}
			System.out.println("Seleccionar el id de la pista a la que pertenece:");
			int idPistaTerm=sc.nextInt();
			Pista pistaVue=gestor.seleccionarPistaPorId(idPistaTerm);
			
			Vuelo vuelo2=new Vuelo(avionVue, terminalVue, pistaVue);
			gestor.insertTablaVuelo(vuelo2);
			
			System.out.println("Añadido vuelo "+vuelo2.toString());
			
			opcion1();
		case 10:
			
			System.out.println("Añadiendo avión . . . ");
			
			System.out.println("Aerolinea:");
			List<Aerolinea> aerolinea3=gestor.selectAerolinea();
			for(Aerolinea aerolineaLista:aerolinea3){
				System.out.println(aerolineaLista.toString());
			}
			System.out.println("Seleccionar el id de la aerolinea a la que pertenece:");
			int aerolineaAv=sc.nextInt();
			Aerolinea aerolineaAvion=gestor.seleccionarAerolineaPorId(aerolineaAv);
			
			System.out.println("Modelo");
			List<Modelo> modelo1=gestor.selectModelo();
			for(Modelo modeloLista:modelo1){
				System.out.println(modeloLista.toString());
			}
			System.out.println("Seleccionar el id del modelo al que pertenece");
			int modeloAv=sc.nextInt();
			Modelo modeloAvion=gestor.seleccionarModeloPorId(modeloAv);
			
			Avion avion=new Avion(aerolineaAvion, modeloAvion);
			gestor.insertTablaAvion(avion);
			
			System.out.println("Añadido avión: "+avion.toString());
			
			opcion1();
		
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
		
		opcion1();
		 
		case 2: 
			List<Aerolinea> aerolinea1=gestor.selectAerolinea();
			for(Aerolinea aerolinea:aerolinea1){
				System.out.println(aerolinea.toString());
			}
			opcion1();
		case 3://Funciona
			List<Modelo> modelo1=gestor.selectModelo();
			for(Modelo modelo:modelo1){
				System.out.println(modelo.toString());
			}
			opcion1();
		case 4://Funciona
			List<Pista> pista1=gestor.selectPista();
			for(Pista pista:pista1){
				System.out.println(pista.toString());
				
			}
			opcion1();
		case 5: 
			List<Billete> billete1=gestor.selectBillete();
			for(Billete billete:billete1){
				System.out.println(billete.toString());
			}
			opcion1();
		case 6:
			List <Pasajero> pasajero1=gestor.selectPasajero();
			for(Pasajero pasajero:pasajero1){
				System.out.println(pasajero.toString());
			}
			opcion1();
		case 7: List <Equipaje> equipaje1=gestor.selectEquipaje();
			for(Equipaje equipaje:equipaje1){
				System.out.println(equipaje.toString());				
			}
			opcion1();
		
		case 8: List<Terminal> terminal1=gestor.selectTerminal();
			for(Terminal terminal:terminal1){
				System.out.println(terminal.toString());
			}
			opcion1();
		
		case 9: List<Vuelo> vuelo1=gestor.selectVuelo();
			for(Vuelo vuelo:vuelo1){
				System.out.println(vuelo.toString());
			}
			opcion1();
		case 10: List<Avion> avion1=gestor.selectAvion();
			for(Avion avion:avion1){
				System.out.println(avion.toString());
			}
			opcion1();
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

