import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Ingreso;
import gestorAplicacion.economia.Prestamo;
import gestorAplicacion.economia.PrestamoLargoPlazo;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Colchon;
import gestorAplicacion.usuario.Cuenta;
import gestorAplicacion.usuario.Usuario;

public class Main {
    static Usuario usuario;
    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        /*Usuario usuario3 = new Usuario("3", "Oswaldo Andres Pena Rojas", "oPena@unal.edu.co", LocalDate.now(), "segura3");
        Usuario usuario2 = new Usuario("2", "David Esteban Martin Acosta", "dMartin@unal.edu.co", LocalDate.now(), "segura2");
        Usuario usuario1 = new Usuario("1", "Jaime Alberto Guzman Luna", "jGuzman@unal.edu.co", LocalDate.now(), "segura1");
        DataBank.nuevoUsuario(usuario3);
        DataBank.nuevoUsuario(usuario2);
        DataBank.nuevoUsuario(usuario1);*/


        int opcion;

        usuario = login();

        do {
            System.out.println("---- SISTEMA GESTOR DE DINERO ----");
            System.out.println("¿Que operación desea realizar?");
            System.out.println("1. Ver saldos disponibles en la cuenta");
            System.out.println("2. Ingresar dinero a su cuenta");
            System.out.println("3. Agregar bolsillo a su cuenta");
            System.out.println("4. Agregar colchon a su cuenta");
            System.out.println("5. Modificar Colchon/Bolsillo");
            System.out.println("6. Solicitar Prestamo");
            System.out.println("7. Terminar ");
            opcion = validarEntradaInt(7);

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

    //Menú de cuentas disponibles y sus respectivos saldos del usuario seleccionado en el login()
    static void saldosDisponibles() {
    	int opcion;
    	System.out.println("¿Que cuentas desea visualizar?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Dinero total");
        System.out.println("4. Volver al inicio");       
        opcion = validarEntradaInt(4);
        
        switch (opcion) {
            case 1:
            	listarBolsillos();
                break;
            case 2:
            	listarColchones();
                break;
            case 3:
                System.out.println("Dinero total: " + DataBank.dineroTotalUsu(usuario));
                break;
            case 4:
            	break;
        }
    }
    
    //Menú de seleccion a que cuenta va a realizar el ingreso de dinero y se llama a eleccionBancoMonto() donde se va a realizar el ingreso
    static void ingresaDinero() {
        int opcion, opc;
        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3);

        switch (opcion) {
            case 1:
            	System.out.println("Bolsillos: ");
            	boolean bol = listarBolsillos();
                if (bol==true) {            
                    opc = validarEntradaInt(usuario.getBolsillos().size())-1;
                    eleccionBancoMonto(usuario.getBolsillos().get(opc));
                }
                break;
            case 2:
            	System.out.println("Colchones: ");
            	boolean col = listarColchones();
                if (col==true) {            
                	opc = validarEntradaInt(usuario.getColchones().size())-1;
                    eleccionBancoMonto(usuario.getColchones().get(opc));
                }
                break;
            case 3:
                break;
        }
    }
    
    //Se selecciona el banco por medio del cual hará el ingreso de dinero y se pide la cantidad en la divisa actual de la cuenta
    static void eleccionBancoMonto(Cuenta cuenta) {
        int opcBanco;
        double cantidad;
        System.out.println("Elija el banco por medio del cual quiere hacer el ingreso");
        int j = 1;
        for (Banco i : Banco.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        opcBanco = validarEntradaInt(Banco.values().length) - 1;

        System.out.println("Ingrese la cantidad que desea ingresar en " + cuenta.getDivisa() + " (utilice ',' para el símbolo decimal) (Cantidad maxima 10000000): ");
        cantidad = validarEntradaDouble(10000000);
        Ingreso ingreso = new Ingreso(cantidad, LocalDate.now(), Banco.values()[opcBanco], cuenta, cuenta.getDivisa());
        usuario.nuevoIngreso(ingreso);
    }
    
    //Se controla cualquier posible problema en la entrada de los datos de tipo int
    static int validarEntradaInt(int opcMax) {
        int opcUsuario;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.err.println("PORFAVOR INGRESE UN DATO VALIDO");
                }
                System.out.println("Por favor escoja una opción: ");
               
                opcUsuario = entrada.nextInt();
            } catch (Exception e) {
                opcUsuario = 0;
                entrada.next();
            }
            repeted = true;
        } while (opcUsuario <= 0 || opcUsuario > opcMax);
        return opcUsuario;
    }
    
    //Se controla cualquier posible problema en la entrada de los datos de tipo double
    static double validarEntradaDouble(double cantMax) {
        double cantidad;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                	System.err.println("CANTIDAD INCORRECTA (RECUERDE SEPARAR CON ',' LOS DECIMALES) Y QUE LA CANTIDAD SEA MENOR A ("+cantMax+")");
                }
                System.out.println("Por favor ingrese una cantidad: ");
               
                cantidad = entrada.nextDouble();
            } catch (Exception e) {
            	cantidad = 0;
                entrada.next();
            }
            repeted = true;
        } while (cantidad <= 0 || cantidad > cantMax);
        return cantidad;
    }
    
    //Se implementa login para la facilidad de prueba del profesor y los monitores, se listan los usuarios agregados anteriormente
    static Usuario login() {
    	System.out.println("---- LOGIN ----");
        System.out.println("¿Que usuario eres?");    
        int j = 1;
        for (Usuario i : DataBank.getUsuarios()) {
        	System.out.println(j + ". " + i.getNombre());
        	j++;
        }
        return DataBank.getUsuarioPorCC(String.valueOf(validarEntradaInt(DataBank.getUsuarios().size())));
    }
    
    //Se agrega un bolsillo al usuario que se seleccionó en el login() con el nombre y la divisa seleccionada por el usuario
    static void agregarBolsillo() {
    	int divisa;
    	String nombre;
    	
        System.out.println("Elija la divisa que desea utilizar en el bolsillo");
        int j = 1;
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        divisa = validarEntradaInt(Divisa.values().length) - 1;
        
        System.out.println("Escriba el nombre que desea asignarle al bolsillo: ");
        nombre = entrada.next();
        
        Bolsillo bolsillo = new Bolsillo(usuario,Divisa.values()[divisa],nombre);
        usuario.nuevoBolsillo(bolsillo);
    }
    
  //Se agrega un colchon al usuario que se seleccionó en el login() con el nombre, la divisa y la fecha de retiro seleccionada por el usuario
    static void agregarColchon() {
    	int divisa,fecha;
    	String nombre;
    	
        System.out.println("Elija la divisa que desea utilizar en el colchon");
        int j = 1;
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        divisa = validarEntradaInt(Divisa.values().length) - 1;
        
        System.out.println("Escriba el nombre que desea asignarle al colchon: ");
        nombre = entrada.next();
        
        System.out.println("Elija la fecha en que desea liberar el colchon: ");
        for(int i = 1; i < 5; i++) {
        	System.out.println(i + ". "+LocalDate.now().plusMonths(i));
        }
        fecha = validarEntradaInt(4);
        
        Colchon colchon = new Colchon(usuario,Divisa.values()[divisa],nombre,LocalDate.now().plusMonths(fecha));
        usuario.nuevoColchon(colchon);
    }
    
    //Menú para la eleccion de modificacion, sea bolsillo o colchon, luego se envia la eleccion a la funcion modificar
    static void opcionModificar() {
    	int opcion, opc;
    	System.out.println("¿Que desea modificar?");
        System.out.println("1. Bolsillo");
        System.out.println("2. Colchon");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3);
        
        switch(opcion) {
        	case 1:
        		System.out.println("Bolsillos: ");
            	boolean bol = listarBolsillos();
                if (bol==true) {            
                    opc = validarEntradaInt(usuario.getBolsillos().size())-1;
                    modificar(usuario.getBolsillos().get(opc));
                }
                break;
        	case 2:
        		System.out.println("Colchones: ");
            	boolean col = listarColchones();
                if (col==true) {            
                    opc = validarEntradaInt(usuario.getColchones().size())-1;
                    modificar(usuario.getColchones().get(opc));
                }
        		break;
        	case 3:
        		break;
        }
    }
    
    //Menú para modificar nombre o divisa de un bolsillo
    static void modificar(Bolsillo bolsillo) {
    	int opcion, divisa;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3);
        
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
                divisa = validarEntradaInt(Divisa.values().length) - 1;
                double[] nuevoSaldo = bolsillo.getDivisa().ConvertToDivisa(bolsillo.getSaldo(),Divisa.values()[divisa]);
                bolsillo.setDivisa(Divisa.values()[divisa]);
                bolsillo.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: "+nuevoSaldo[1]);
                break;
        	case 3:
        		break;
        }
    }
    
    //Menú para modificar nombre, divisa o fecha minima de retiro de un colchon
    static void modificar(Colchon colchon) {
    	int opcion, divisa;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Cambiar fecha");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4);
        
        switch(opcion) {
        	case 1:
        		System.out.println("Nuevo nombre:");
        		String nombre = entrada.next();
        		colchon.setNombre(nombre);
        		break;
        	case 2:
        		System.out.println("Nueva divisa:");
                listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length) - 1;
                double[] nuevoSaldo = colchon.getDivisa().ConvertToDivisa(colchon.getSaldo(),Divisa.values()[divisa]);
                colchon.setDivisa(Divisa.values()[divisa]);
                colchon.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: "+nuevoSaldo[1]);
                break;
        	case 3:
        		System.out.println("¿Que desea modificar?");
                System.out.println("1. Aumentar 1 mes");
                System.out.println("2. Disminuir 1 mes");
                System.out.println("3. Volver al inicio");
                opcion = validarEntradaInt(3);
                
                switch(opcion) {
                	case 1:
                		colchon.setFechaRetiro(colchon.getFechaRetiro().plusMonths(1));
                		break;
                	case 2:
                		colchon.setFechaRetiro(colchon.getFechaRetiro().minusMonths(1));
                		break;
                	case 3:
                		break;
                }
                break;
        	case 4:
        		break;
        }
    }
    
    //Se listan los bolsillos del usuario que se seleccionó en el login()
    static boolean listarBolsillos() {
    	System.out.println("---------------------------------------------------------");
        if (!usuario.getBolsillos().isEmpty()) {
            int j = 1;
            for (Bolsillo i : usuario.getBolsillos()) {
                System.out.println(j + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Divisa: " + i.getDivisa());
                j++;
            }
            System.out.println("---------------------------------------------------------");
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE BOLSILLOS...\n");
            System.out.println("---------------------------------------------------------");
            return false;
        }
    }
    
    //Se listan los colchones del usuario que se seleccionó en el login()
    static boolean listarColchones() {
    	System.out.println("---------------------------------------------------------");
        if (!usuario.getColchones().isEmpty()) {
            int j = 1;
            for (Colchon i : usuario.getColchones()) {
                System.out.println(j + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Fecha de retiro: " + i.getFechaRetiro() + "		Divisa: " + i.getDivisa());
                j++;
                System.out.println("---------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE COLCHONES...\n");
            System.out.println("---------------------------------------------------------");
            return false;
        }
    }
    
    //Se listan las divisas del sistema
    static void listarDivisas() {
    	int j = 1;
    	System.out.println("---------------------------------------------------------");
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("---------------------------------------------------------");
    }
    
    //Menú para que el usuario seleccione que prestamo desea
    static void solicitarPrestamo() {
    	int opcion;
    	System.out.println("¿Que tipo de prestamo desea solicitar?");
        System.out.println("1. Prestamo fugaz");
        System.out.println("2. Prestamo a largo plazo");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3);

        switch(opcion) {
        	case 1:
        		break;
        	case 2:
        		peticionPrestamoLP();
        		break;
        	case 3: 
        		break;
        }
    }
    
    //Se inicia la peticion del prestamo a largo plazo con algunos datos basicos para validar si es apto
    static void peticionPrestamoLP() {
    	int hijos;
    	double ingresoMensual, dineroSolicitado;
    	
    	System.out.println("---- Criterios para validar el credito ----");
    	System.out.println("¿Cuantos hijos tiene?: ");
    	hijos = validarEntradaInt(100);//100 porque el metodo necesita un valor máximo de entrada y se espera sea imposible de alcanzar un valor maximo de 100 hijos
    	
    	System.out.println("Digite su ingreso mensual en COP (utilice ',' para el símbolo decimal) (Cantidad maxima 20000000):");
    	ingresoMensual = validarEntradaDouble(20000000);
    	
    	System.out.println("¿Cuanto dinero desea solicitar para realizar el prestamo? (utilice ',' para el símbolo decimal) (Cantidad maxima 50000000): ");
    	dineroSolicitado = validarEntradaDouble(50000000);
    	
    	if(hijos <= 2 && ingresoMensual>=1500000 && dineroSolicitado>2000000 && usuario.getIngresos().size()>3 && DataBank.dineroTotalUsu(usuario)>500000) {
    		AceptadoPrestamoLP(dineroSolicitado);
    	} else {
    		System.out.println("PRESTAMO RECHAZADO/CANCELADO...");
    	}
    }
    
    //Si el usuario es apto para un prestado a largo plazo se le solicitan unos datos para guardar como garantia, se genera el prestamo y se agrega a los prestamos realizados por el usuario
    static void AceptadoPrestamoLP(double dineroSolicitado) {
    	int opcTiempo, tiempo = 0;
    	String[] referencia = null;
    	System.out.println("¿A cuantos meses desea solicitar el prestamo?");
        System.out.println("1. 12 meses");
        System.out.println("2. 24 meses");
        System.out.println("3. 36 meses");
        System.out.println("4. 60 meses");
        System.out.println("5. Volver al inicio");
        opcTiempo = validarEntradaInt(5);
        
    	System.out.println("Escriba el nombre de una referencia: ");
    	referencia[0] = entrada.next();
    
    	System.out.println("Escriba el numero telefonico de la referencia: ");
    	referencia[1] = entrada.next();
    	
    	switch(opcTiempo) {
    		case 1:tiempo = 12;
        		break;
    		case 2:tiempo = 24;
    			break;
    		case 3:tiempo = 36;
    			break;
    		case 4:tiempo = 60;
    			break;
    		case 5:tiempo = 0;
    			break;
    	}
    	
    	if(tiempo!=0) {
    		PrestamoLargoPlazo prestamo = new PrestamoLargoPlazo(dineroSolicitado, tiempo, LocalDate.now(), referencia);
    		usuario.nuevoPrestamo(prestamo);
    		System.out.println("PRESTAMO APROBADO...");
    	}else {
    		System.out.println("PRESTAMO RECHAZADO/CANCELADO...");
    	}
    }	
    
  //Se inicia la peticion del prestamo fugaz con algunos datos basicos para validar si es apto
    static void prestamoFugaz() {
    	int opcion;
    	List<Ingreso> ingresos;
    	if(usuario.getFechaIngreso().isBefore(LocalDate.now().minusDays(2)) == true &&
    			usuario.getIngresos().size() > 1 &&
    			DataBank.dineroTotalUsu(usuario) > 10000) {
    		
    		double ingresoTotPesos=0;
    		for(Ingreso i : usuario.getIngresos()) {
    			double[] pesos = i.getDivisaDestino().ConvertToDivisa(i.getValor(), Divisa.COP);
    			ingresoTotPesos += pesos[0];
    		}
    		//El prestamo fugaz ofrece 2 opciones cuando el usuario es apto
			double montoPrestamo1 = ingresoTotPesos*0.1;//el 10% de los ingresos realizados desde que sea inferior a 1 millon
    		double montoPrestamo2 = ingresoTotPesos*0.4;//o el 40% de los ingresos realizados desde que sea inferior a 2 millones
    		
    		if(montoPrestamo1 > 1000000) {
    			montoPrestamo1 = 1000000;
    		}
    		
    		if(montoPrestamo2 > 2000000) {
    			montoPrestamo2 = 2000000;
    		}
    		
    		System.out.println("¿Que cantidad desea prestar?");
            System.out.println("1. "+montoPrestamo1);
            System.out.println("2. "+montoPrestamo2);
            opcion = validarEntradaInt(2);
            
            switch(opcion) {
        	case 1:
        		//PrestamoFugaz prestamo = new PrestamoFugaz(montoPrestamo1,LocalDate.now(),referencia);
        		//usuario.nuevoPrestamo(prestamo);
        		break;
        	case 2:	
        		//PrestamoFugaz prestamo2 = new PrestamoFugaz(montoPrestamo2,LocalDate.now(),referencia);
        		//usuario.nuevoPrestamo(prestamo2);
        		break; 	
            }
    	}  
    }
}

