import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);
		int opcion;
		
		
		
		do {
			System.out.println("---- SISTEMA GESTOR DE DINERO ----");
			System.out.println("¿Que operación desea realizar?");
			System.out.println("1. Ver todos sus saldos disponibles en la cuenta"); 
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
			System.out.println("6. Terminar ");
			System.out.println("Por favor escoja una opción: ");
			opcion = entrada.nextInt();

			switch(opcion) {
				case 1:
					
				
			}

		}while(opcion != 6);
	}

}
