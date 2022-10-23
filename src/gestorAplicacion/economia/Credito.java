package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.usuario.Usuario;

public class Credito implements Serializable {

    private Usuario cliente;
    private float deuda;
    private float cuotaMensual;

    private static ArrayList<Credito> credito = new ArrayList<>();

    public Credito (Usuario cliente, float deuda,float cuotaMensual) {
        this.cliente = cliente;
        this.deuda = deuda;
        this.cuotaMensual = cuotaMensual;

        credito.add(this);
    }

    public static float simulacionCredito(float monto, int plazo) {
        float deuda = (float) ((1+((0.10/12)*plazo))*monto);
        return deuda;

    }

    public static ArrayList<Credito> getCredito() {
        return credito; }

    public static void setCredito(ArrayList<Credito> credito) {
        Credito.credito = credito; }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente; }

    public Usuario getCliente() {
        return this.cliente; }

    public void setDeuda(float deuda) {
        this.deuda = deuda;	}

    public float getDeuda() {
        return this.deuda; }

    public void setCuotaMensual(float cuotaMensual) {
        this.cuotaMensual = cuotaMensual;	}

    public float getCuotaMensual() {
        return this.cuotaMensual; }

}
