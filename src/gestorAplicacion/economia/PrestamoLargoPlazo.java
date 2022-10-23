package gestorAplicacion.economia;

import java.time.LocalDate;

public class PrestamoLargoPlazo extends Prestamo {

    private static final long serialVersionUID = 1L;

    private String[] referencia;
    private double TEA;

    private Garantia garantia;

    public PrestamoLargoPlazo(double valorIncial, int tiempo, LocalDate fechaInicio, Divisa divisa, String[] referencia, Garantia garantia) {
        super(valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo), divisa);
        setReferencia(referencia);
        setGarantia(garantia);
        setTEA(valorIncial, garantia);
    }

    public PrestamoLargoPlazo(double valorIncial, int tiempo, LocalDate fechaInicio, Divisa divisa, String[] referencia) {
        super(valorIncial, tiempo, fechaInicio, fechaInicio.plusYears(tiempo), divisa);
        setReferencia(referencia);
        setGarantia(null);
        setTEA(valorIncial);
    }

    public String[] getReferencia() {
        return referencia;
    }

    public void setReferencia(String[] referencia) {
        this.referencia = referencia;
    }

    @Override
    protected void setTEA(double monto) {
        this.setTEA(monto, null);
    }

    @Override
    protected void setTEA(double monto, Garantia garantia) {
        double acumulado = 0;
        if (monto < Divisa.COP.ConvertToDivisa(5000000, this.getDivisa())[0]) {
            acumulado += 34.49 - 30;
        } else {
            acumulado += 30.69 - 30;
        }
        switch (garantia) {
            case Vivienda -> acumulado += 6;
            case Lote -> acumulado += 9;
            case Carro -> acumulado += 16;
            case Moto -> acumulado += 22;
            default -> acumulado += 30;
        }
        this.TEA = acumulado;

    }

    public double getTEA() {
        return TEA;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

    @Override
    public Salida abonar(double monto) {
        return null;
    }
}
	

	