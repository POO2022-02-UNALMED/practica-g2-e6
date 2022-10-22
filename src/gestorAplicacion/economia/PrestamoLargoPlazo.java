package gestorAplicacion.economia;
import java.time.LocalDate;

public class PrestamoLargoPlazo extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] referencia;
	private double TEA;

	public PrestamoLargoPlazo(double valorIncial, int tiempo, LocalDate fechaInicio, String[] referencia) {
		super(valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo));
		setReferencia(referencia);
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
		if(monto<5000000) {
			this.TEA = 34.49;
		}else {
			this.TEA = 30.69;
		}
		
	}
	
	public double getTEA() {
		return TEA;
	}
	
}
	

	