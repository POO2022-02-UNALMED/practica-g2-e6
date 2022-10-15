import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		int opcion;
		do {
			System.out.println("---- SISTEMA GESTOR DE DINERO ----");
			System.out.println("¿Que operación desea realizar?");
			System.out.println("1. Ver todos sus saldos disponibles en la cuenta");
			System.out.println("2. Ingresar dinero a su cuenta");
			System.out.println("3. Agregar bolsillo a su cuenta");
			System.out.println("4. Modificar bolsillo");
			System.out.println("5. Solicitar credito");
			System.out.println("6. Ver todas las solicitudes pendientes");
			System.out.println("7. Terminar ");
			System.out.println("Por favor escoja una opción: ");
			opcion = entrada.nextInt();

			switch(opcion) {
				case 1:
					
				
			}

		}while(opcion != 7);
	}

}
