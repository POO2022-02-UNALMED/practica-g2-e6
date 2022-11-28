package gestorAplicacion.economia;

import gestorAplicacion.usuario.Usuario;

import java.time.LocalDate;

public class PrestamoFugaz extends Prestamo {

    private static final long serialVersionUID = -3388280595710146580L;

    public PrestamoFugaz(Usuario usuario, double valorInicial, LocalDate fechaInicio, Divisa divisa) {
        super(usuario,valorInicial, 6, fechaInicio, fechaInicio.plusMonths(6), divisa);
        setTEA(valorInicial);
    }

    @Override
    protected void setTEA(double monto) {
        if (monto < Divisa.COP.ConvertToDivisa(1000000, this.getDivisa())[0]) {
            super.TEA = super.baseTEAAlto;
        } else {
            super.TEA = super.baseTEABajo;
        }
    }

    @Override
    protected void setTEA(double monto, Garantia garantia) {
        this.setTEA(monto);
    }

    public double getTEA() {
        return TEA;
    }
}
