import java.util.List;
import java.util.Scanner;

import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Divisa;
import gestorAplicacion.usuario.Usuario;

public class Main {
	
	static DataBank databank = new DataBank();

	public static void main(String[] args) {
		
		Scanner entrada=new Scanner(System.in);
		int opcion, opcUsuario;
		Usuario usuario;
		
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
				case 1:
					
				
			}

		}while(opcion != 6);
	}
	
	static void saldosDisponibles(Usuario usu) {
		int opcion;
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("¿Que cuentas desea visualizar?");
		System.out.println("1. Bolsillos");
		System.out.println("2. Divisas");
		System.out.println("3. Dinero total");
		System.out.println("Por favor escoja una opción: ");
		opcion = entrada.nextInt();
		
		while(opcion != 1 & opcion !=2 & opcion !=3) {
			System.out.println("Por favor ingresa una opcion valida: ");
			opcion = entrada.nextInt();
		}
		
		switch(opcion) {
			case 1:
				List<Bolsillo> bolsillos = databank.getBolsillosUsuario(usu);
				for(int i = 0; i < bolsillos.size(); i++) {
					System.out.println(i+". "+bolsillos.get(i).getNombre()+"		Disponible: "+bolsillos.get(i).getSaldo());
				}
				break;
			case 2:
				List<Divisa> divisas = databank.getDivisasUsuario(usu);
				for(int i = 0; i < divisas.size(); i++) {
					System.out.println(i+". "+divisas.get(i).getNombre()+"		Disponible: "+divisas.get(i).getSaldo());
		
				}
				break;
			case 3:
				double total = databank.dineroTotalUsu(usu);
				break;
		}
	}
}

