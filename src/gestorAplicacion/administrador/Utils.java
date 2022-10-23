package gestorAplicacion.administrador;

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
    public static void listarBancos() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Banco i : Banco.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void listarGarantias() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Garantia i : Garantia.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    public static void listarUsuarios() {
        int j = 1;
        System.out.println("--------------------------------------------------------------------------");
        for (Usuario i : DataBank.getUsuarios()) {
            System.out.println(j + ". " + i.getNombre());
            j++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }
}
