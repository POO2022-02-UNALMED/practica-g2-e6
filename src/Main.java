import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Ingreso;
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
        int opcion, opcUsuario;
        boolean repeted = false;
        do {
            try {
                if (repeted) {
                    System.err.println("PORFAVOR INGRESE UN DATO VALIDO PIROBO");
                }
                System.out.println("---- LOGIN ----");
                System.out.println("¿Que usuario eres?");
                System.out.println("1. Jaime Alberto Guzman Luna");
                System.out.println("2. David Esteban Martin Acosta");
                System.out.println("3. Oswaldo Andres Pena Rojas");
                opcUsuario = entrada.nextInt();
            } catch (Exception e) {
                opcUsuario = 0;
                entrada.next();
            }
            repeted = true;
        } while (opcUsuario <= 0 || opcUsuario >= DataBank.getUsuarios().size());

        usuario = DataBank.getUsuarioPorCC(String.valueOf(opcUsuario));

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

            switch (opcion) {
                case 1:
                    saldosDisponibles();
                    break;
                case 2:
                    ingresaDinero();
                    break;
                //case 3:agregarBolsillo();
                case 6:
                    Serializador.serializar();
                    break;
            }

        } while (opcion != 6);
    }

    static void saldosDisponibles() {
        int opcion;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¿Que cuentas desea visualizar?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Dinero total");
        System.out.println("Por favor escoja una opción: ");
        opcion = entrada.nextInt();

        while (opcion != 1 & opcion != 2 & opcion != 3) {
            System.out.println("Por favor ingresa una opcion valida: ");
            opcion = entrada.nextInt();
        }

        switch (opcion) {
            case 1:
                List<Bolsillo> bolsillos = usuario.getBolsillos();
                if (!bolsillos.isEmpty()) {
                    int j = 0;
                    for (Bolsillo i : bolsillos) {
                        System.out.println(j + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Divisa: " + i.getDivisa());
                        j++;
                    }
                } else {
                    System.out.println("El usuario no posee bolsillos...");
                }
                break;
            case 2:
                List<Colchon> colchones = usuario.getColchones();
                if (!colchones.isEmpty()) {
                    int z = 0;
                    for (Colchon i : colchones) {
                        System.out.println(z + ". " + i.getNombre() + "		Disponible: " + i.getSaldo() + "		Fecha de retiro: " + i.getFechaRetiro());
                        z++;
                    }
                } else {
                    System.out.println("El usuario no posee colchones...");
                }
                break;
            case 3:
                double total = DataBank.dineroTotalUsu(usuario);
                System.out.println("Dinero total: " + total);
                break;
        }
    }

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
                List<Bolsillo> bolsillos = usuario.getBolsillos();
                if (!bolsillos.isEmpty()) {
                    System.out.println("Elija el bolsillo destino");
                    int j = 1;
                    for (Bolsillo i : bolsillos) {
                        System.out.println(j + ". " + i.getNombre());
                        j++;
                    }
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt();
                    Main.eleccionBancoMonto(bolsillos.get(opcion - 1));
                } else {
                    System.out.println("El usuario no posee bolsillos...");
                }
                break;
            case 2:
                List<Colchon> colchones = usuario.getColchones();
                if (!colchones.isEmpty()) {
                    System.out.println("Elija el colchon destino");
                    int z = 1;
                    for (Colchon i : colchones) {
                        System.out.println(z + ". " + i.getNombre());
                        z++;
                    }
                    System.out.println("Por favor escoja una opción: ");
                    opcion = entrada.nextInt();
                    Main.eleccionBancoMonto(colchones.get(opcion - 1));
                    break;
                } else {
                    System.out.println("El usuario no posee colchones...");
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
}

