package gestorAplicacion.economia;

import java.time.LocalDate;

public class PrestamoFugaz extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3388280595710146580L;
	/**
	 * 
	 */
	

	private String[] referencia;
	
	public PrestamoFugaz(double valorInicial,LocalDate fechaInicio, String[] referencia) {
		super(valorInicial, 6 , 34.49, fechaInicio,fechaInicio.plusMonths(6));
		setReferencia(referencia);

	}

	public String[] getReferencia() {
		return referencia;
	}

	public void setReferencia(String[] referencia) {
		this.referencia = referencia;
	}
	
	
	
	
	
	
	

	
	


}
