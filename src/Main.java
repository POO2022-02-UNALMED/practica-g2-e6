import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Serializador;
import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.administrador.Utils;
import gestorAplicacion.administrador.Validador;
import gestorAplicacion.economia.*;
import gestorAplicacion.usuario.*;

import static gestorAplicacion.administrador.Validador.*;

public class Main {
    static Usuario usuario;

    //Se implementa login para la facilidad de prueba del profesor y los monitores, se listan los usuarios agregados anteriormente
    static Usuario login() {
        System.out.println("---- LOGIN ----");
        System.out.println("¿Que usuario eres?");
        Utils.listarUsuarios();
        return DataBank.getUsuarios().get(validarEntradaInt(DataBank.getUsuarios().size(), true, 0, false) - 1);
    }

    public static void main(String[] args) {

        Usuario usuario3 = new Usuario("3", "Oswaldo Andres Pena Rojas", "oPena@unal.edu.co", LocalDate.now(), "segura3");
        Usuario usuario2 = new Usuario("2", "David Esteban Martin Acosta", "dMartin@unal.edu.co", LocalDate.now(), "segura2");
        Usuario usuario1 = new Usuario("1", "Jaime Alberto Guzman Luna", "jGuzman@unal.edu.co", LocalDate.now(), "segura1");
        DataBank.nuevoUsuario(usuario3);
        DataBank.nuevoUsuario(usuario2);
        DataBank.nuevoUsuario(usuario1);


        int option;

        usuario = login();

        do {
            System.out.println("---- SISTEMA GESTOR DE DINERO ----");
            System.out.println("¿Que operación desea realizar?");
            System.out.println("1. Ver saldos disponibles en la cuenta");
            System.out.println("2. Ingresar dinero a su cuenta");
            System.out.println("3. Mover dinero en su cuenta");
            System.out.println("4. Enviar y sacar dinero de su cuenta");
            System.out.println("5. Agregar bolsillo a su cuenta");
            System.out.println("6. Agregar colchón a su cuenta");
            System.out.println("7. Agregar Meta a su cuenta");
            System.out.println("8. Modificar Colchón/Bolsillo/Meta");
            System.out.println("9. Solicitar Préstamo");
            System.out.println("10. Abonar a un préstamo o Meta");//TODO
            System.out.println("11. Logout");
            System.out.println("12. Terminar ");
            option = validarEntradaInt(11, true, 0, false);

            switch (option) {
                case 1 -> saldosDisponibles();
                case 2 -> ingresaDinero();
                case 3 -> moverDineroInterno();
                case 4 -> enviarYSacarDinero();
                case 5 -> agregarBolsillo();
                case 6 -> agregarColchon();
                case 7 -> agregarMeta();
                case 8 -> opcionModificar();
                case 9 -> solicitarPrestamo();
                case 11 -> {
                    usuario = login();
                }
                case 12 -> Serializador.serializar();
                default -> System.out.println("OPCIÓN EN DESARROLLO");
            }
        } while (option != 12);
        System.exit(0);
    }

    //OPCIÓN1
    //Menú de cuentas disponibles y sus respectivos saldos del usuario seleccionado en el login()
    static void saldosDisponibles() {
        int option;
        System.out.println("¿Que cuentas desea visualizar?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Metas");
        System.out.println("4. Dinero total");
        System.out.println("5. Volver al inicio");
        option = validarEntradaInt(5, true, 0, false);

        switch (option) {
            case 1 -> usuario.listarBolsillos();
            case 2 -> usuario.listarColchones();
            case 3 -> usuario.listarMetas();
            case 4 -> {
                double[] dineroTot = usuario.getDineroTotal();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Dinero total: ");
                int j = 0;
                for (double i : dineroTot) {
                    System.out.println(Divisa.values()[j] + ": " + i);
                    j++;
                }
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    //OPCIÓN2
    //Menú de selección a que cuenta va a realizar el ingreso de dinero y se llama a elección BancoMonto() donde se va a realizar el ingreso
    static void ingresaDinero() {
        int option, opc;
        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Volver al inicio");
        option = validarEntradaInt(3, true, 1, true);
        boolean bool = false;
        List<Cuenta> list = new ArrayList<>();
        switch (option) {
            case 1 -> {
                System.out.println("Bolsillos: ");
                bool = usuario.listarBolsillos();
                list.addAll(usuario.getBolsillos());
            }
            case 2 -> {
                System.out.println("Colchones: ");
                bool = usuario.listarColchones();
                list.addAll(usuario.getColchones());
            }
        }
        if (bool) {
            opc = validarEntradaInt(list.size(), true, 1, true) - 1;
            electionBancoMonto(list.get(opc));
        }
    }

    //Se selecciona el banco por medio del cual hará el ingreso de dinero y se pide la cantidad en la divisa actual de la cuenta
    static void electionBancoMonto(Cuenta cuenta) {
        int opcBanco;
        double cantidad;
        System.out.println("Elija el banco por medio del cual quiere hacer el ingreso");
        Utils.listarBancos();
        opcBanco = validarEntradaInt(Banco.values().length, true, 1, true) - 1;
        System.out.println("Ingrese la cantidad que desea ingresar en " + cuenta.getDivisa() + " (utilice ',' para el símbolo decimal) (Cantidad maxima 10000000): ");
        cantidad = Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false);
        Ingreso ingreso = new Ingreso(cantidad, LocalDate.now(), Banco.values()[opcBanco], cuenta, cuenta.getDivisa());
        usuario.nuevoIngreso(ingreso);
        System.out.println("Su nuevo saldo es de " + cuenta.getSaldo() + " " + cuenta.getDivisa());
    }

    //Opcion3
    static void moverDineroInterno() {
        int option;
        Cuenta destino, origen;
        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Colchones");
        System.out.println("3. Volver al inicio");
        option = validarEntradaInt(3, true, 1, true);
        boolean bool = false;
        List<Cuenta> list = new ArrayList<>();
        switch (option) {
            case 1 -> {
                System.out.println("Bolsillos: ");
                bool = usuario.listarBolsillos();
                list.addAll(usuario.getBolsillos());
            }
            case 2 -> {
                System.out.println("Colchones: ");
                bool = usuario.listarColchones();
                list.addAll(usuario.getColchones());
            }
        }
        if (bool) {
            int opc = validarEntradaInt(list.size(), true, 1, true) - 1;
            destino = list.get(opc);
            origen = seleccionarCuentaDeOrigen(destino);
            if (origen != null) {
                System.out.println("Ingrese la cantidad a transferir (en " + origen.getDivisa() + ")(entre 0 y " + origen.getSaldo() + ")");
                double monto = Validador.validarEntradaDouble(origen.getSaldo(), true, 0, false);
                double[] monto2 = origen.getDivisa().ConvertToDivisa(monto, destino.getDivisa());
                boolean retirado = origen.retirar(monto);
                if (!retirado) {
                    return;
                }
                destino.depositar(monto2[0]);
                System.out.println("Movimiento exitosos con una trm de: " + monto2[1]);
                System.out.println("Nuevo saldo en el origen de: " + origen.getSaldo());
                System.out.println("Nuevo saldo en el destino de: " + destino.getSaldo());
            }
        }
    }

    static Cuenta seleccionarCuentaDeOrigen(Cuenta destino) {
        boolean repet = false;
        do {
            int option, opc;
            Cuenta origen;
            repet = false;
            System.out.println("¿De donde sale su dinero?");
            System.out.println("1. Bolsillos");
            System.out.println("2. Colchones");
            System.out.println("3. Volver al inicio");
            option = validarEntradaInt(3, true, 1, true);
            boolean bool = false;
            List<Cuenta> list = new ArrayList<>();
            switch (option) {
                case 1 -> {
                    System.out.println("Bolsillos: ");
                    bool = usuario.listarBolsillos();
                    list.addAll(usuario.getBolsillos());
                }
                case 2 -> {
                    System.out.println("Colchones: ");
                    bool = usuario.listarColchones();
                    list.addAll(usuario.getColchones());
                }
            }
            if (bool) {
                opc = validarEntradaInt(list.size(), true, 1, true) - 1;
                origen = list.get(opc);
                if (origen == destino) {
                    System.err.println("NO PUEDES ENVIAR EL DINERO AL MISMO LUGAR");
                    repet = true;
                } else {
                    return origen;
                }
            }
        } while (repet);
        return null;
    }

    //OPCION 4
    private static void enviarYSacarDinero() {
        int option;
        Cuenta destino = null;
        System.out.println("¿Desea hacer un envio o retiro?");
        System.out.println("1. Retiro");
        System.out.println("2. Envio");
        System.out.println("3. volver al menu");
        option = Validador.validarEntradaInt(3, true, 1, true);
        switch (option) {
            case 2:
                System.out.println("Seleccione a quien le desea enviar el dinero");
                Utils.listarUsuarios();
                destino = DataBank.getUsuarios().get(Validador.validarEntradaInt(DataBank.getUsuarios().size(), true, 1, true) - 1).getBolsillos().get(0);
                break;
            case 3:
                return;
        }
        Cuenta origen = seleccionarCuentaDeOrigen(destino);
        if (origen != null) {
            System.out.println("Ingrese la cantidad a transferir (en " + origen.getDivisa() + ")(entre 0 y " + origen.getSaldo() + ")");
            double monto = Validador.validarEntradaDouble(origen.getSaldo(), true, 0, false);
            Salida salida;
            boolean retirado = false;
            if (destino != null) {
                double[] monto2 = origen.getDivisa().ConvertToDivisa(monto, destino.getDivisa());
                salida = new Salida(monto2[0], LocalDate.now(), origen, destino, origen.getDivisa(), destino.getDivisa());
                retirado = usuario.nuevaSalida(salida);
                if (retirado) {
                    Ingreso ingreso = new Ingreso(monto2[0], LocalDate.now(), origen, destino, origen.getDivisa(), destino.getDivisa());
                    destino.getUsuario().nuevoIngreso(ingreso);
                }
            } else {
                System.out.println("Seleccione el Banco Al que va a retirar");
                Utils.listarBancos();
                int opcBanco = validarEntradaInt(Banco.values().length, true, 1, true) - 1;
                salida = new Salida(monto, LocalDate.now(), origen, Banco.values()[opcBanco], origen.getDivisa());
                retirado = usuario.nuevaSalida(salida);
            }
            if (retirado) {
                System.out.println("Retiro Exitoso");
                System.err.println("Nuevo saldo en " + origen.getNombre() + " es: " + origen.getSaldo());
            }

        }
    }

    //OPCIÓN 5
    //Se agrega un bolsillo al usuario que se seleccionó en el login() con el nombre y la divisa seleccionada por el usuario
    static void agregarBolsillo() {
        int divisa;
        String nombre;
        System.out.println("Elija la divisa que desea utilizar en el bolsillo");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 0, false) - 1;

        System.out.println("Escriba el nombre que desea asignarle al bolsillo: ");
        nombre = Validador.validarEntradaTexto(false);
        Bolsillo bolsillo = new Bolsillo(usuario, Divisa.values()[divisa], nombre);
        usuario.nuevoBolsillo(bolsillo);
        System.out.println("Bolsillo " + nombre + " AGREGADO CON EXITO");
    }

    //OPCIÓN6
    //Se agrega un colchón al usuario que se seleccionó en el login() con el nombre, la divisa y la fecha de retiro seleccionada por el usuario
    static void agregarColchon() {
        int divisa, fecha;
        String nombre;
        System.out.println("Elija la divisa que desea utilizar en el colchón");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;

        System.out.println("Escriba el nombre que desea asignarle al colchon: ");
        nombre = Validador.validarEntradaTexto(true);

        System.out.println("Elija la fecha en que desea liberar el colchon: ");
        for (int i = 1; i <= 12; i++) {
            System.out.println(i + ". " + LocalDate.now().plusMonths(i));
        }
        fecha = validarEntradaInt(12, true, 1, true);
        Colchon colchon = new Colchon(usuario, Divisa.values()[divisa], nombre, LocalDate.now().plusMonths(fecha));
        usuario.nuevoColchon(colchon);
        System.out.println("Colchon " + nombre + " AGREGADO CON EXITO");

    }

    //OPCION 7
    private static void agregarMeta() {
        int divisa;
        String nombre;
        double objetivo;
        System.out.println("Elija la divisa que desea utilizar la meta: ");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;

        System.out.println("Escriba el nombre que desea asignarle a la meta: ");
        nombre = Validador.validarEntradaTexto(true);
        System.out.println("ingrese el valor objetivo que desea asignarle a la meta (recuerde que no podra sacar el dinero de una meta hasta alcanzar el objetivo): ");
        objetivo = validarEntradaDouble(Double.MAX_VALUE, true, 0, true);
        Meta meta = new Meta(usuario, nombre, LocalDate.now(), objetivo, Divisa.values()[divisa]);
        usuario.nuevaMeta(meta);
        System.out.println("Meta Agregada Con Exito");
    }

    //OPCION 8
    //Menú para la eleccion de modificacion, sea bolsillo o colchon, luego se envia la eleccion a la funcion modificar
    static void opcionModificar() {
        int opcion, opc;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Bolsillo");
        System.out.println("2. Colchon");
        System.out.println("3. Meta"); //TODO
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);
        boolean bool = false;
        List<Contable> list = new ArrayList<>();
        switch (opcion) {
            case 1 -> {
                System.out.println("Bolsillos: ");
                bool = usuario.listarBolsillos();
                list.addAll(usuario.getBolsillos());
            }
            case 2 -> {
                System.out.println("Colchones: ");
                bool = usuario.listarColchones();
                list.addAll(usuario.getColchones());
            }
            case 3 -> {
                System.out.println("Metas: ");
                bool = usuario.listarMetas();
                list.addAll(usuario.getMetas());
            }
        }
        if (bool) {
            opc = validarEntradaInt(list.size(), true, 1, true) - 1;
            modificar(list.get(opc));
        }
    }

    static void modificar(Contable x) {
        if (x instanceof Bolsillo then) {
            modificar(then);
        } else if (x instanceof Colchon then) {
            modificar(then);
        } else if (x instanceof Meta then) {
            modificar(then);
        }
    }

    //Menú para modificar nombre o divisa de un bolsillo
    static void modificar(Bolsillo bolsillo) {
        int opcion, divisa;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3, true, 1, true);

        switch (opcion) {
            case 1:
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                bolsillo.setNombre(nombre);
                break;
            case 2:
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = bolsillo.getDivisa().ConvertToDivisa(bolsillo.getSaldo(), Divisa.values()[divisa]);
                bolsillo.setDivisa(Divisa.values()[divisa]);
                bolsillo.setSaldo(nuevoSaldo[0]);
                System.err.println("Tasa de cambio: " + nuevoSaldo[1]);
                System.err.println("Nuevo saldo: " + nuevoSaldo[0]);
                break;
            case 3:
                return;
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    //Menú para modificar nombre, divisa o fecha minima de retiro de un colchon
    static void modificar(Colchon colchon) {
        int opcion, divisa;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Cambiar fecha");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);

        switch (opcion) {
            case 1 -> {
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                colchon.setNombre(nombre);
            }
            case 2 -> {
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = colchon.getDivisa().ConvertToDivisa(colchon.getSaldo(), Divisa.values()[divisa]);
                colchon.setDivisa(Divisa.values()[divisa]);
                colchon.setSaldo(nuevoSaldo[0]);
                System.err.println("Tasa de cambio: " + nuevoSaldo[1]);
                System.err.println("El nuevo saldo es: " + nuevoSaldo[0]);
            }
            case 3 -> {
                System.out.println("¿Cuanto lo desea modificar?");
                System.out.println("1. dias");
                System.out.println("2. meses");
                System.out.println("3. años");
                System.out.println("4. Volver al inicio");
                opcion = validarEntradaInt(4, true, 1, true);
                int limite = 0;
                int total = 0;
                switch (opcion) {
                    case 1:
                        limite = 31;
                        break;
                    case 2:
                        limite = 12;
                        break;
                    case 3:
                        limite = 10;
                        break;
                    case 4:
                        return;
                }
                System.out.println("Ingrese la cantidad que desa modificar (entre 1 y " + limite + ")");
                total = Validador.validarEntradaInt(limite, true, 1, true);

                System.out.println("¿Aumentar o reducir?");
                System.out.println("1. Aumentar");
                System.out.println("2. reducir");
                System.out.println("3. volver al inicio");
                int opcion2 = Validador.validarEntradaInt(3, true, 1, true);
                if (opcion2 == 3) {
                    return;
                } else if (opcion2 == 2) {
                    switch (opcion) {
                        case 1 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusDays(total));
                        case 2 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusMonths(total));
                        case 3 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusYears(total));
                    }
                } else {
                    switch (opcion) {
                        case 1 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusDays(total));
                        case 2 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusMonths(total));
                        case 3 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusYears(total));
                    }
                }
            }
            case 4 -> {
                return;
            }
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    //TODO
    static void modificar(Meta meta) {
        int opcion, divisa;
        System.out.println("¿Que desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Nuevo objetivo");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);

        switch (opcion) {
            case 1:
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                meta.setNombre(nombre);
                break;
            case 2:
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = meta.getDivisa().ConvertToDivisa(meta.getSaldo(), Divisa.values()[divisa]);
                meta.setDivisa(Divisa.values()[divisa]);
                meta.setSaldo(nuevoSaldo[0]);
                System.err.println("Tasa de cambio: " + nuevoSaldo[1]);
                System.err.println("Nuevo saldo: " + nuevoSaldo[0]);
                break;
            case 3:
                System.out.println("Nuevo Objetivo (en " + meta.getDivisa() + "): ");
                meta.setObjetivo(Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false));
                break;
            case 4:
                return;
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    //OPCIÓN 9
    //Menú para que el usuario seleccione que prestamo desea
    static void solicitarPrestamo() {
        System.out.println("Seleccione la divisa con la que solicitara el prestamo");
        Utils.listarDivisas();
        int divisa = Validador.validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
        int opcion;
        System.out.println("¿Que tipo de prestamo desea solicitar?");
        System.out.println("1. Prestamo fugaz");
        System.out.println("2. Prestamo a largo plazo");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3, true, 1, true);

        switch (opcion) {
            case 1:
                peticionPrestamoF(Divisa.values()[divisa]);
                break;
            case 2:
                peticionPrestamoLP(Divisa.values()[divisa]);
                break;
            case 3:
                break;
        }
    }

    //Se inicia la peticion del prestamo a largo plazo con algunos datos basicos para validar si es apto
    static void peticionPrestamoLP(Divisa divisa) {
        int hijos, tiempo = 0, edad;
        double ingresoMensual, dineroSolicitado;

        System.out.println("---- Criterios para validar el credito ----");
        System.out.println("¿Cuantos hijos tiene?: ");
        hijos = Validador.validarEntradaInt(Integer.MAX_VALUE, true, 1, true);

        System.out.println("Digite su ingreso mensual en " + divisa + " (utilice ',' para el símbolo decimal):");
        ingresoMensual = Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false);

        System.out.println("¿Cuanto dinero desea solicitar para realizar el prestamo? " + divisa + "(utilice ',' para el símbolo decimal) ");
        dineroSolicitado = Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false);

        System.out.println("¿Cuantos Años tiene usted?");
        edad = Validador.validarEntradaInt(120, true, 18, true);

        double puntaje = 0;
        if (80 - edad >= 1) {
            System.out.println("¿A cuantos Años desea solicitar el prestamo?");
            tiempo = Validador.validarEntradaInt(80 - edad, true, 1, true);
            if (dineroSolicitado / (tiempo * 12) < ingresoMensual * 0.5) {
                puntaje = -hijos + divisa.ConvertToDivisa(ingresoMensual, Divisa.USD)[0] / 200 + tiempo - divisa.ConvertToDivisa(dineroSolicitado, Divisa.USD)[0] / (tiempo * 12) / 1000;
            }

        }
        if (puntaje > 5) {
            AceptadoPrestamoLP(dineroSolicitado, tiempo, divisa);
        } else {
            System.err.println("PRESTAMO RECHAZADO/CANCELADO...");
        }
    }

    //Si el usuario es apto para un prestado a largo plazo se le solicitan unos datos para guardar como garantía, se genera el préstamo y se agrega a los préstamos realizados por el usuario
    static void AceptadoPrestamoLP(double dineroSolicitado, int tiempo, Divisa divisa) {
        String[] referencia = new String[2];
        int opcGarantia = -1, opc;
        System.out.println("Escriba el nombre de una referencia: ");
        referencia[0] = validarEntradaTexto(true);

        System.out.println("Escriba el numero telefonico de la referencia: ");
        referencia[1] = validarEntradaTexto(true);
        System.out.println("¿Desea dar alguna garantía para reducir la taza de interes?");
        System.out.println("1. Si");
        System.out.println("2. No");
        opc = Validador.validarEntradaInt(2, true, 1, true);
        if (opc == 1) {
            System.out.println("Escoja el Elemento que dejara Como garantia");
            Utils.listarGarantias();
            opcGarantia = Validador.validarEntradaInt(Garantia.values().length, true, 1, true) - 1;
        }
        System.out.println("Escoja el bolsillo al que se le enviara el dinero");
        usuario.listarBolsillos();
        int bolsillo = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
        PrestamoLargoPlazo prestamo;
        if (opcGarantia < 0) {
            prestamo = new PrestamoLargoPlazo(dineroSolicitado, tiempo, LocalDate.now(), divisa, referencia);
        } else {
            prestamo = new PrestamoLargoPlazo(dineroSolicitado, tiempo, LocalDate.now(), divisa, referencia, Garantia.values()[opcGarantia]);
        }
        usuario.nuevoPrestamo(prestamo, usuario.getBolsillos().get(bolsillo));
        System.out.println("PRESTAMO APROBADO...");

    }

    //Se inicia la peticion del prestamo fugaz con algunos datos basicos para validar si es apto
    static void peticionPrestamoF(Divisa divisa) {
        int opcion;
        double montoPrestamo = 0;
        if (usuario.getFechaIngreso().isBefore(LocalDate.now().minusDays(0)) &&
                usuario.getIngresos().size() > 1) {

            double ingresoTotPesos = 0;
            for (Ingreso i : usuario.getIngresos()) {
                double[] pesos = i.getDivisaDestino().ConvertToDivisa(i.getValor(), Divisa.COP);
                ingresoTotPesos += pesos[0];
            }
            //El prestamo fugaz ofrece 2 opciones cuando el usuario es apto
            double montoPrestamo1 = Divisa.COP.ConvertToDivisa(Math.min(ingresoTotPesos * 0.1, 1000000), divisa)[0];//el 10% de los ingresos realizados desde que sea inferior a 1 millon
            double montoPrestamo2 = Divisa.COP.ConvertToDivisa(Math.min(ingresoTotPesos * 0.4, 2000000), divisa)[0];//o el 40% de los ingresos realizados desde que sea inferior a 2 millones

            System.out.println("¿Que cantidad desea prestar?");
            System.out.println("1. " + montoPrestamo1);
            System.out.println("2. " + montoPrestamo2);
            System.out.println("3. Volver al inicio");
            opcion = Validador.validarEntradaInt(3, true, 1, true);

            switch (opcion) {
                case 1 -> montoPrestamo = montoPrestamo1;
                case 2 -> montoPrestamo = montoPrestamo2;
                case 3 -> montoPrestamo = 0;
            }
            if (montoPrestamo != 0) {
                System.out.println("Escoja el bolsillo al que se le enviara el dinero");
                usuario.listarBolsillos();
                int bolsillo = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
                PrestamoFugaz prestamo = new PrestamoFugaz(montoPrestamo, LocalDate.now(), divisa);
                usuario.nuevoPrestamo(prestamo, usuario.getBolsillos().get(bolsillo));
                System.out.println("PRESTAMO APROBADO...");
            } else {
                System.err.println("PRESTAMO RECHAZADO/CANCELADO...");
            }
        } else {
            System.err.println("PRESTAMO RECHAZADO/CANCELADO...");
        }
    }
}

