package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;


public class PerfilCredito implements Serializable {
    private Usuario usuario;
    private float capacidadDeuda;
    private comportamientoPago comportamientoPago;

    public static ArrayList<PerfilCredito> getPerfilCredito() {
        return PerfilCredito;	}

    public static void setPerfilCredito(ArrayList<PerfilCredito> PerfilCredito) {
        PerfilCredito.PerfilCredito = PerfilCredito;
    }

    public void setcapacidadDeuda(float nivelDeuda) {
        this.capacidadDeuda = nivelDeuda;	}

    public float getcapacidadDeuda() {
        return this.capacidadDeuda;	}

    public comportamientoPago getcomportamientoPago() {
        return this.comportamientoPago; }

}