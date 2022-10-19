package gestorAplicacion.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Deserializador;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.usuario.*;


public class DataBank implements Serializable {

    public static String[][] filesList = {
            // 1. name of file, 2.setterName, 3. getterName
            {"usuarios.txt", "setUsuarios", "getUsuarios"},
            {"divisas.txt", "setDivisas", "getDivisas"}
    };
    private static final long serialVersionUID = 2979265545810011076L;
    private static List<Usuario> usuarios = new ArrayList<Usuario>();
    private static List<Divisa> divisas = new ArrayList<Divisa>();
    private static List<Banco> bancos = new ArrayList<Banco>();

    static {
        Deserializador.deserializar();
    }

    //Getters and setters

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(Object usuarios) {
        DataBank.usuarios = (List<Usuario>) usuarios;
    }

    public static List<Divisa> getDivisas() {
        return divisas;
    }

    public static void setDivisas(Object divisas) {
        DataBank.divisas = (List<Divisa>)divisas;
    }

    public static List<Banco> getBancos() {
        return bancos;
    }

    public static void setBancos(List<Banco> bancos) {
        DataBank.bancos = bancos;
    }


    // Add data to lists
    public static void nuevoUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void nuevaDivisa(Divisa divisa) {
        divisas.add(divisa);
    }

    public static Usuario getUsuarioPorCC(String cc) {
        for (Usuario i : usuarios) {
            if (i.getCedula().equals(cc)) {
                return i;
            }
        }
        return null;
    }

    //TODO: Migrate to User class (logic cross)
    public static double dineroTotalUsu(Usuario usuario) {

        double total = 0;

        for (Bolsillo i : usuario.getBolsillos()) {
            total += i.getSaldo();
        }

        for (Colchon i : usuario.getColchones()) {
            total += i.getSaldo();
        }
        return total;
    }

}