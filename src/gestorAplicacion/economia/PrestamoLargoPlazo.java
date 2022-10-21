package gestorAplicacion.economia;
import java.time.LocalDate;

public class PrestamoLargoPlazo extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public PrestamoLargoPlazo(double valorIncial, int tiempo, double TEA, LocalDate fechaInicio, LocalDate fechaFinal, boolean cumplida) {
		super(valorIncial, tiempo, TEA, fechaInicio, fechaFinal, cumplida);
		
	}
	
}
	

	