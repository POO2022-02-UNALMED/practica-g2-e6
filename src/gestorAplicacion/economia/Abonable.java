package gestorAplicacion.economia;

import gestorAplicacion.usuario.Cuenta;

public interface Abonable {
    Salida abonar(double monto, Cuenta origen);
}
