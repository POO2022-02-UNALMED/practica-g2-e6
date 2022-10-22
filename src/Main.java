import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Ingreso;
import gestorAplicacion.economia.Prestamo;
import gestorAplicacion.economia.PrestamoFugaz;
import gestorAplicacion.economia.PrestamoLargoPlazo;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Colchon;
import gestorAplicacion.usuario.Cuenta;
import gestorAplicacion.usuario.Usuario;

public class Main {
    static Usuario usuario;

    public static void main(String[] args) {

        /*Usuario usuario3 = new Usuario("3", "Oswaldo Andres Pena Rojas", "oPena@unal.edu.co", LocalDate.now(), "segura3");
        Usuario usuario2 = new Usuario("2", "David Esteban Martin Acosta", "dMartin@unal.edu.co", LocalDate.now(), "segura2");
        Usuario usuario1 = new Usuario("1", "Jaime Alberto Guzman Luna", "jGuzman@unal.edu.co", LocalDate.now(), "segura1");
        DataBank.nuevoUsuario(usuario3);
        DataBank.nuevoUsuario(usuario2);
        DataBank.nuevoUsuario(usuario1);*/


        Scanner entrada = new Scanner(System.in);
        int opcion;

        usuario = login();

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
            //Se listan las divisas existentes y se pregunta con cual desea crear el bolsillo, se pide nombre y se agrega un nuevo bolsillo al usuario
            System.out.println("4. Agregar colchon a su cuenta");
            //Se listan las divisas existentes y se pregunta con cual desea crear el colchon, se pide nombre y fecha para sacar el dinero,
            //luego se agrega el colchon a la cuenta
            System.out.println("5. Modificar Colchon/Bolsillo");
            System.out.println("6. Solicitar Prestamo");
            //Se tomarán en cuenta varios parametros (cantidad de ingresos, edad, contrato de empleo fijo y experiencia crediticia.
            System.out.println("7. Terminar ");
            System.out.println("Por favor escoja una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:saldosDisponibles();
                    break;
                case 2:ingresaDinero();
                    break;
                case 3:agregarBolsillo();
                	break;
                case 4:agregarColchon();
                	break;
                case 5:opcionModificar();
                	break;
                case 6:solicitarPrestamo();
                    break;
                case 7:Serializador.serializar();
                	break;
            }

        } while (opcion != 7);
    }

    static void saldosDisponibles() {
    	Scanner entrada = new Scanner(System.in);
    	int opcion;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.err.println("PORFAVOR INGRESE UN DATO VALIDO...");
                }
                System.out.println("¿Que cuentas desea visualizar?");
                System.out.println("1. Bolsillos");
                System.out.println("2. Colchones");
                System.out.println("3. Dinero total");
                System.out.println("3. Volver al inicio");
                System.out.println("Por favor escoja una opción: ");
                
                opcion = entrada.nextInt();
            } catch (Exception e) {
            	opcion = 0;
                entrada.next();
            }
            repeted = true;
        } while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4);

        switch (opcion) {
            case 1:listarBolsillos();
                break;
            case 2:
            	listarColchones();
                break;
            case 3:
                double total = DataBank.dineroTotalUsu(usuario);
                System.out.println("Dinero total: " + total);
                break;
            case 4:
            	break;
        }
    }
    
    //TODO: Try catch
    static void ingresaDinero() {
        int opcion;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Volver al inicio");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();

        while (opcion != 1 & opcion != 2 & opcion != 3) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }

        switch (opcion) {
            case 1:
            	System.out.println("Bolsillos: ");
            	boolean bol = listarBolsillos();
                if (bol==true) {            
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt();
                    eleccionBancoMonto(usuario.getBolsillos().get(opcion - 1));
                }
                break;
            case 2:
            	System.out.println("Colchones: ");
            	boolean col = listarColchones();
                if (col==true) {            
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt();
                    eleccionBancoMonto(usuario.getColchones().get(opcion - 1));
                }
                break;
            case 3:
                break;
        }
    }

    static void eleccionBancoMonto(Cuenta cuenta) {
        int opcion;
        double cantidad;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Elija el banco por medio del cual quiere hacer el ingreso");
        int j = 1;
        for (Banco i : Banco.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt() - 1;

        System.out.println("Ingrese la cantidad que desea ingresar en " + cuenta.getDivisa() + ": (utilice ',' para el símbolo decimal)");
        cantidad = entrada.nextDouble();
        Ingreso ingreso = new Ingreso(cantidad, LocalDate.now(), Banco.values()[opcion], cuenta, cuenta.getDivisa());
        usuario.nuevoIngreso(ingreso);
    }
    
    //TODO: No se gestiona bien la eleccion del usuario en el while, en pruebas realizada solo ejecuta con el 1
    static Usuario login() {
    	Scanner entrada = new Scanner(System.in);
        int opcUsuario;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.err.println("PORFAVOR INGRESE UN DATO VALIDO PIROBO");
                }
                System.out.println("---- LOGIN ----");
                System.out.println("¿Que usuario eres?");
                
                int j = 1;
                for (Usuario i : DataBank.getUsuarios()) {
                    System.out.println(j + ". " + i.getNombre());
                    j++;
                }
                System.out.println("Por favor escoja una opción: ");
               
                opcUsuario = entrada.nextInt();
            } catch (Exception e) {
                opcUsuario = 0;
                entrada.next();
            }
            repeted = true;
        } while (opcUsuario <= 0 || opcUsuario >= DataBank.getUsuarios().size());

        return DataBank.getUsuarioPorCC(String.valueOf(opcUsuario));
    }
    
    //TODO: falta try catch para controlar la entrada
    static void agregarBolsillo() {
    	
    	Scanner entrada = new Scanner(System.in);
    	int divisa;
    	String nombre;
    	
        System.out.println("Elija la divisa que desea utilizar en el bolsillo");
        int j = 1;
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("Por favor escoja una opción: ");
        divisa = entrada.nextInt() - 1;
        
        System.out.println("Escriba el nombre que desea asignarle al bolsillo: ");
        nombre = entrada.next();
        
        Bolsillo bolsillo = new Bolsillo(usuario,Divisa.values()[divisa],nombre);
        usuario.nuevoBolsillo(bolsillo);
    }
    
  //TODO: falta try catch para controlar la entrada
    static void agregarColchon() {
    	
    	Scanner entrada = new Scanner(System.in);
    	int opcion,fecha;
    	String nombre;
    	
        System.out.println("Elija la divisa que desea utilizar en el colchon");
        int j = 1;
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt() - 1;
        
        System.out.println("Escriba el nombre que desea asignarle al colchon: ");
        nombre = entrada.next();
        
        System.out.println("Elija la fecha en que desea liberar el colchon: ");
        for(int i = 1; i < 5; i++) {
        	System.out.println(i + ". "+LocalDate.now().plusMonths(i));
        }
        System.out.println("Por favor escoja una opción: ");
        fecha = entrada.nextInt();
        
        
        Colchon colchon = new Colchon(usuario,Divisa.values()[opcion],nombre,LocalDate.now().plusMonths(fecha));
        usuario.nuevoColchon(colchon);
    }
    
    //TODO: try catch
    static void opcionModificar() {
    	int opcion;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Que desea modificar?");
        System.out.println("1. Bolsillo");
        System.out.println("2. Colchon");
        System.out.println("3. Volver al inicio");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();

        while (opcion != 1 && opcion != 2 && opcion != 3) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }
        
        switch(opcion) {
        	case 1:
        		System.out.println("Bolsillos: ");
            	boolean bol = listarBolsillos();
                if (bol==true) {            
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt()-1;
                    modificar(usuario.getBolsillos().get(opcion));
                }
                break;
        	case 2:
        		System.out.println("Colchones: ");
            	boolean col = listarBolsillos();
                if (col==true) {            
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt()-1;
                    modificar(usuario.getColchones().get(opcion));
                }
        		break;
        	case 3:
        		break;
        }
    }
    
    //TODO: try catch
    static void modificar(Bolsillo bolsillo) {
    	int opcion, divisa;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();

        while (opcion != 1 && opcion != 2) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }
        
        switch(opcion) {
        	case 1:
        		System.out.println("Nuevo nombre:");
        		String nombre = entrada.next();
        		bolsillo.setNombre(nombre);
        		break;
        	case 2:
        		System.out.println("Nueva divisa:");
        		int j = 1;
                listarDivisas();
                System.out.println("Por favor escoja una opción: ");
                divisa = entrada.nextInt() - 1;
                double[] nuevoSaldo = bolsillo.getDivisa().ConvertToDivisa(bolsillo.getSaldo(),Divisa.values()[divisa]);
                bolsillo.setDivisa(Divisa.values()[divisa]);
                bolsillo.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: "+nuevoSaldo[1]);
                break;
        }
    }
    
  //TODO: try catch
    static void modificar(Colchon colchon) {
    	int opcion, divisa;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Cambiar fecha");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();

        while (opcion != 1 && opcion != 2 && opcion != 3) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }
        
        switch(opcion) {
        	case 1:
        		System.out.println("Nuevo nombre:");
        		String nombre = entrada.next();
        		colchon.setNombre(nombre);
        		break;
        	case 2:
        		System.out.println("Nueva divisa:");
        		int j = 1;
                listarDivisas();
                System.out.println("Por favor escoja una opción: ");
                divisa = entrada.nextInt() - 1;
                double[] nuevoSaldo = colchon.getDivisa().ConvertToDivisa(colchon.getSaldo(),Divisa.values()[divisa]);
                colchon.setDivisa(Divisa.values()[divisa]);
                colchon.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: "+nuevoSaldo[1]);
                break;
        	case 3:
        		if(cambiarFechaColchon()==1) {
        			colchon.setFechaRetiro(colchon.getFechaRetiro().plusMonths(1));
        		}else if(cambiarFechaColchon()==2) {
        			colchon.setFechaRetiro(colchon.getFechaRetiro().minusMonths(1));
        		}
        }
    }
    
    static int cambiarFechaColchon() {
    	int opcion;
        Scanner entrada = new Scanner(System.in);
    	System.out.println("¿Que desea modificar?");
        System.out.println("1. Aumentar 1 mes");
        System.out.println("2. Disminuir 1 mes");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();
        
        while (opcion != 1 && opcion != 2) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }
        
        switch(opcion) {
        	case 1:return 1;
        	case 2:return 2;
        	default:return 0;
        }
    }
    
    static boolean listarBolsillos() {
        if (!usuario.getBolsillos().isEmpty()) {
            int j = 1;
            for (Bolsillo i : usuario.getBolsillos()) {
                System.out.println(j + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Divisa: " + i.getDivisa());
                j++;
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE BOLSILLOS...\n");
            return false;
        }
    }
    
    static boolean listarColchones() {
        if (!usuario.getColchones().isEmpty()) {
            int j = 1;
            for (Colchon i : usuario.getColchones()) {
                System.out.println(j + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Fecha de retiro: " + i.getFechaRetiro() + "		Divisa: " + i.getDivisa());
                j++;
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE COLCHONES...\n");
            return false;
        }
    }
    
    static void listarDivisas() {
    	int j = 1;
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
    }
    
    static void solicitarPrestamo() {
    	int opcion;
        Scanner entrada = new Scanner(System.in);
    	System.out.println("¿Que tipo de prestamo desea solicitar?");
        System.out.println("1. Prestamo fugaz");
        System.out.println("2. Prestamo a largo plazo");
        System.out.println("3. Volver al inicio");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();
        
        while (opcion != 1 && opcion != 2 && opcion != 3) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }
        
        switch(opcion) {
        	case 1:prestamoFugaz();
        		break;
        		
        	case 2:prestamoALargoPlazo();
        		break;
        		
        	case 3: 
        		break;
   
        }
    }
    
    static boolean prestamoALargoPlazo() {
    	int hijos, tiempo = 0;
    	double ingresoMensual, dineroSolicitado;
    	
    	Scanner entrada = new Scanner(System.in);
    	
    	System.out.println("---- Criterios para validar el credito ----");
    	System.out.println("");
    	
    	System.out.println("¿Cuantos hijos tiene?: ");
    	hijos = entrada.nextInt();
    	
    	System.out.println("Digite su ingreso mensual en COP: ");
    	ingresoMensual = entrada.nextDouble();
    	
    	System.out.println("¿Cuanto dinero desea solicitar para realizar el prestamo?");
    	dineroSolicitado = entrada.nextDouble();
    	
    	System.out.println("Ingrese a cuantos meses desea solicitar el prestamo, tomando el numero de meses como enteros.");
    	tiempo = entrada.nextInt();
    	
    	if(hijos < 2 && ingresoMensual>=1500000 && dineroSolicitado>1000000 && usuario.getIngresos().size()>3 && DataBank.dineroTotalUsu(usuario)>1000000 ) {
    		double TEA = calcularTEA(dineroSolicitado);
    		PrestamoLargoPlazo prestamoLargoPlazo = new PrestamoLargoPlazo(dineroSolicitado, tiempo, TEA, LocalDate.now(), LocalDate.now().plusMonths(tiempo));
    		System.out.println("Su prestamo HA SIDO APROBADO!!!");
    		
    		usuario.nuevoPrestamo(prestamoLargoPlazo);
    		
    		return true;
    		
    	} else {
    		System.out.println("Su prestamo ha sido rechazado :(");
    		return false;
    	}
    	
    }
    
    //Calcular TEA Interes Anual
    static double calcularTEA(double cantidad) {
    	if(cantidad > 1000000) {
    		return 30.5;
    	} else {
    		return 35.5;
    	}
    }
    
    
    static boolean prestamoFugaz() {
    	
    	int hijos, tiempo = 0;
    	double ingresoMensual, dineroSolicitado;
    	
    	Scanner entrada = new Scanner(System.in);
    	
    	System.out.println("---- Criterios para validar el credito ----");
    	System.out.println("");
    	
    	System.out.println("¿Cuantos hijos tiene?: ");
    	hijos = entrada.nextInt();
    	
    	System.out.println("Digite su ingreso mensual en COP: ");
    	ingresoMensual = entrada.nextDouble();
    	
    	System.out.println("¿Cuanto dinero desea solicitar para realizar el prestamo?");
    	dineroSolicitado = entrada.nextDouble();
    	
    	System.out.println("Ingrese a cuantos meses desea solicitar el prestamo, tomando el numero de meses como enteros.");
    	tiempo = entrada.nextInt();
    	
    	if(hijos < 4 && ingresoMensual>=800000 && usuario.getIngresos().size()>3 && DataBank.dineroTotalUsu(usuario)>200000 ) {
    		double TEA = calcularTEA(dineroSolicitado);
    		
    		PrestamoFugaz prestamoFugaz = new PrestamoFugaz(dineroSolicitado, tiempo, TEA, LocalDate.now(), LocalDate.now().plusMonths(tiempo));
    		System.out.println("Su prestamo HA SIDO APROBADO!!!");
    		
    		usuario.nuevoPrestamo(prestamoFugaz);
    		
    		return true;
    		
    	} else {
    		System.out.println("Su prestamo ha sido rechazado :(");
    		return false;
    	}
    	
    }	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    
    
}

