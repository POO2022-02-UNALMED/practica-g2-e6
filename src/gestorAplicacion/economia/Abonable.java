package gestorAplicacion.economia;

import gestorAplicacion.usuario.Cuenta;

public interface Abonable {
    boolean abonar(double monto, Cuenta origen);
    Divisa getDivisa();
}
