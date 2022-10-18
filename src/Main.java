import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Ingreso;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Colchon;
import gestorAplicacion.usuario.Usuario;

public class Main {
	
	static DataBank databank = new DataBank();
	static Usuario usuario;

	public static void main(String[] args) {
		
		Scanner entrada=new Scanner(System.in);
		int opcion, opcUsuario;
		
			System.out.println("---- LOGIN ----");
			System.out.println("¿Que usuario eres?");
			System.out.println("1. Jaime Alberto Guzman Luna"); 
			System.out.println("2. David Esteban Martin Acosta");
			System.out.println("3. Oswaldo Andres Pena Rojas");
			opcUsuario = entrada.nextInt();
			
		while(opcUsuario != 1 & opcUsuario !=2 & opcUsuario !=3) {
			System.out.println("Por favor ingresa una opcion valida: ");
			opcUsuario = entrada.nextInt();
		}
		
		usuario=databank.getUsuarioPrincipal(String.valueOf(opcUsuario));
		
		do {
			System.out.println("---- SISTEMA GESTOR DE DINERO ----");
			System.out.println("¿Que operación desea realizar?");
			System.out.println("1. Ver saldos disponibles en la cuenta"); 
			//Nuestra idea de modelo es que un usuario puede tener su dinero repartido en divisas y bolsillos, esas divisas heredan de una clase cuenta
			//los bolsillos heredan de cuenta tambien, para ver los saldos disponibles de un usuario, se revisan los bolsillos, consultamos 
			//si existen bolsillos asociados al usuario, los guardamos en una array x de tipo cuenta, luego revisamos las divisas, si hay divisas
			//asociadas al cliente,de igual manera se añaden a el array x de tipo cuenta y se listan por pantalla
			System.out.println("2. Ingresar dinero a su cuenta");
			//En este apartado el usuario tiene la opción de ingresar dinero a uno de sus bolsillos desde una cuenta externa, para lo cual solo debe indicar
			//el bolsillo existente y la cantidad de dinero a ingresar.
			//Tambien crearemos la funcion de realizar un avance a uno de sus bolsillo, para lo cual se verifica la cantidad de bolsillos existentes,
			//fecha de ingreso a la plataforma, cantidad de movimientos realizados, y de acuerdo a un puntaje que vamos a establecer, se le asignara una cantidad
			//maxima a desembolsar.
			System.out.println("3. Agregar bolsillo a su cuenta");
			//Se listan las divisas existentes y se pregunta con cual desea crear el bolsillo, se pregunta si desea crear el bolsillo vacio
			//o se desea agregar dinero, si escoje agregar dinero, se ejecuta la funcion 1 y se pregunta de donde quiere agregar el dinero
			//y se descuenta de ese bolsillo o divisa y se agrega a el nuevo bolsillo(si el dinero es inferior a la cantidad que tiene)
			//se crea el bolsillo y se debe actualizar el dinero total del usuario
			System.out.println("4. Modificar bolsillo");
			System.out.println("5. Solicitar credito");
			//Se tomarán en cuenta varios parametros (cantidad de ingresos, edad, contrato de empleo fijo y experiencia crediticia.
			System.out.println("6. Terminar ");
			System.out.println("Por favor escoja una opción: ");
			opcion = entrada.nextInt();

			switch(opcion) {
				case 1:saldosDisponibles();
					break;
				case 2:ingresaDinero();
					break;
				case 6:Serializador.serializar(databank);
				break;
			}

		}while(opcion != 6);
	}
	
	static void saldosDisponibles() {
		int opcion;
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("¿Que cuentas desea visualizar?");
		System.out.println("1. Bolsillos");
		System.out.println("2. Colchones");
		System.out.println("3. Dinero total");
		System.out.println("Por favor escoja una opción: ");
		opcion = entrada.nextInt();
		
		while(opcion != 1 & opcion !=2 & opcion !=3) {
			System.out.println("Por favor ingresa una opcion valida: ");
			opcion = entrada.nextInt();
		}
		
		switch(opcion) {
			case 1:
				List<Bolsillo> bolsillos = databank.getBolsillosUsuario(usuario);
				if(!bolsillos.isEmpty()) {
					int j=0;
					for(Bolsillo i:bolsillos) {
						System.out.println(j+". "+i.getNombre()+"		Disponible: "+i.getSaldo()+"		Divisa: "+i.getDivisa().getNombre());
						j++;
					}
				}else{System.out.println("El usuario no posee bolsillos...");}
				break;
			case 2:
				List<Colchon> colchones = databank.getColchonesUsuario(usuario);
				if(!colchones.isEmpty()) {
					int z=0;
					for(Colchon i:colchones) {
						System.out.println(z+". "+i.getNombre()+"		Disponible: "+i.getSaldo()+"		Fecha de retiro: "+i.getFechaRetiro());	
						z++;
					}
				}else {System.out.println("El usuario no posee colchones...");}
				break;
			case 3:
				double total = databank.dineroTotalUsu(usuario);
				System.out.println("Dinero total: "+total);
				break;
		}
	}
	
	static void ingresaDinero() {
		int opcion;
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("¿Para donde va su dinero?");
		System.out.println("1. Bolsillos");
		System.out.println("2. Colchones");
		System.out.println("Por favor escoja una opción: ");
		opcion = entrada.nextInt();
		
		while(opcion != 1 & opcion !=2) {
			System.out.println("Por favor ingresa una opcion valida: ");
			opcion = entrada.nextInt();
		}
		
		switch(opcion) {
		case 1:
			System.out.println("Elija el bolsillo destino");
			List<Bolsillo> bolsillos = databank.getBolsillosUsuario(usuario);
			int j=0;
			for(Bolsillo i:bolsillos) {
				System.out.println(j+". "+i.getNombre());
				j++;
			}
			System.out.println("Por favor escoja una opción: ");
			opcion = entrada.nextInt();
			break;
		case 2:
			System.out.println("Elija el colchon destino");
			List<Colchon> colchones = databank.getColchonesUsuario(usuario);
			int z=0;
			for(Colchon i:colchones) {
				System.out.println(z+". "+i.getNombre());	
				z++;
			}
			System.out.println("Por favor escoja una opción: ");
			opcion = entrada.nextInt();
			break;
		}
	}
	
	static void eleccionBancoMonto(Bolsillo bolsillo) {
		int opcion,cantidad;
		double monto;
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("Elija el banco por medio del cual quiere hacer el ingreso");
		List<Banco> bancos = databank.getBancos();
		int j=0;
		for(Banco i:bancos) {
			System.out.println(j+". "+i.getNombre());
			j++;
		}
		System.out.println("Por favor escoja una opción: ");
		opcion = entrada.nextInt();
		
		System.out.println("Elija la cantidad que desea ingresar");
		System.out.println(montoDivisa(bolsillo.getDivisa()));
		
		cantidad = entrada.nextInt();
		monto =  montoIngreso(bolsillo.getDivisa(),cantidad);
		
		Ingreso ingreso = new Ingreso(monto,LocalDate.now(),false,bancos.get(opcion), bolsillo, bolsillo.getDivisa(), bolsillo.getDivisa());
		usuario.nuevoIngreso(ingreso);
	}
	
	static String montoDivisa(Divisa divisa) {
		String divisas="";
		if(divisa.getNombre().equals("EURO")) {
			divisas = "1. 10 Euros\n2.100 Euros\n3.200 Euros\n4.500 Euros\nPor favor escoja una opción:\n";
		}else if(divisa.getNombre().equals("PESO")) {
			divisas = "1. 10.000 Pesos\n2.100.000 Pesos\n3.200.000 Pesos\n4.500.000 Pesos\nPor favor escoja una opción:\n";
		}else if(divisa.getNombre().equals("DOLAR")){
			divisas = "1. 10 Dolares\n2.100 Dolares\n3.200 Dolares\n4.500 Dolares\nPor favor escoja una opción:\n";
		}
		return divisas;
	}
	
	static double montoIngreso(Divisa divisa, int cantidad) {
		double monto = 0;
		
		switch(divisa.getNombre()) {
		case "EURO":
			if(cantidad == 1) {monto=10;}
			else if(cantidad == 2) {monto=100;}
			else if(cantidad == 3) {monto=200;}
			else {monto=500;}
			break;
		case "PESO":
			if(cantidad == 1) {monto=10000;}
			else if(cantidad == 2) {monto=100000;}
			else if(cantidad == 3) {monto=200000;}
			else {monto=500000;}
			break;
		case "DOLAR":
			if(cantidad == 1) {monto=10;}
			else if(cantidad == 2) {monto=100;}
			else if(cantidad == 3) {monto=200;}
			else {monto=500;}
			break;
		}
		return monto;
	}
}

