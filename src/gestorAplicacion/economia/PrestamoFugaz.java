package gestorAplicacion.economia;

import java.time.LocalDate;

public class PrestamoFugaz extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388280595710146580L;
	
	private double TEA;
	
	public PrestamoFugaz(double valorInicial, LocalDate fechaInicio) {
		super(valorInicial, 6, fechaInicio, fechaInicio.plusMonths(6));
		setTEA(valorInicial);
	}
	
	@Override
	protected void setTEA(double monto) {
		if(monto<400000) {
			this.TEA = 36.49;
		}else {
			this.TEA = 35.69;
		}
	}
	
	public double getTEA() {
		return TEA;
	}
	
}
