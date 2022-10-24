package gestorAplicacion.economia;

import gestorAplicacion.usuario.Cuenta;

public interface Abonable <T> {
	T abonar(double monto, Cuenta origen);
    Divisa getDivisa();
    Movimiento terminar(Cuenta cuenta);
}
