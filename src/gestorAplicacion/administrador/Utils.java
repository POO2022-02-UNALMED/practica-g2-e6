package gestorAplicacion.administrador;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Garantia;
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
    //Retorna la cantidad de dias entre dos fechas, inicio: inicio el credito, fin: la fecha de pago (LocalDate.now() para obtener fecha del sistema)
    public static long diasEntreFechas(LocalDate inicio, LocalDate fin){
        return DAYS.between(inicio, fin);
    }
}
