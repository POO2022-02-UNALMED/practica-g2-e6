package administrador;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Garantia;
import gestorAplicacion.economia.Prestamo;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Colchon;
import gestorAplicacion.usuario.Meta;
import gestorAplicacion.usuario.Usuario;

public abstract class Utils {
    //Se listan las divisas del sistema
    public static void listarDivisas() {
    	int j = 1;
    	System.out.println("--------------------------------------------------------------------------");
        for (Divisa i : Divisa.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
    	System.out.println("--------------------------------------------------------------------------");
    }
    //Se listan los bancos del sistema
    public static void listarBancos() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Banco i : Banco.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    //Se listan las garantias del sistema
    public static void listarGarantias() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Garantia i : Garantia.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    //Se listan los usuarios del sistema
    public static void listarUsuarios() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Usuario i : DataBank.getUsuarios()) {
            System.out.println(j + ". " + i.getNombre());
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    
    //Se listan los bolsillos del usuario que pasa como referencia
    public static boolean listarBolsillos(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getBolsillos().isEmpty()) {
            int j = 1;
            for (Bolsillo i : usuario.getBolsillos()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tDisponible: " + String.format("%.2f",i.getSaldo()) + "\t\tDivisa: " + i.getDivisa());
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
    public static boolean listarColchones(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getColchones().isEmpty()) {
            int j = 1;
            for (Colchon i : usuario.getColchones()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tDisponible: " + String.format("%.2f",i.getSaldo()) + "\t\tFecha de retiro: " + i.getFechaRetiro() + "		Divisa: " + i.getDivisa());
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE COLCHONES...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }
    
  //Se listan las metas del usuario que se seleccionó en el login()
    public static boolean listarMetas(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getMetas().isEmpty()) {
            int j = 1;
            for (Meta i : usuario.getMetas()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tcumplido: "+(i.isCumplida()?"Si":"No")+"\t\tDisponible: " + String.format("%.2f",i.getSaldo()) + "\t\tDivisa: " + i.getDivisa() + " cantidad objetivo: " + String.format("%.2f",i.getObjetivo()));
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE METAS...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }
    
  //Se listan los prestamos del usuario que se seleccionó en el login()
    public static boolean listarPrestamos(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getPrestamos().isEmpty()) {
            int j = 1;
            for (Prestamo i : usuario.getPrestamos()) {
                System.out.println(j + ". " + i.getClass().getSimpleName() + "\t\tcumplido: "+(i.isCumplida()?"Si":"No")+"\t\tValor: " + String.format("%.2f",i.getValorInicial()) + "\t\tDivisa: " + i.getDivisa() + " cantidad Pagada: " + String.format("%.2f",i.getValorPagado()));
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE PRESTAMOS...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }
    
 
    
    //Retorna la cantidad de dias entre dos fechas, inicio: inicio el credito, fin: la fecha de pago (LocalDate.now() para obtener fecha del sistema)
    public static long diasEntreFechas(LocalDate inicio, LocalDate fin){
        return Math.abs(DAYS.between(inicio, fin));
    }
    //Retorna la cantidad de meses entre dos fechas, inicio: inicio el credito, fin: la fecha de pago (LocalDate.now() para obtener fecha del sistema)
    public static long mesesEntreFechas(LocalDate inicio, LocalDate fin){
        return Math.abs(MONTHS.between(inicio, fin));
    }
}
