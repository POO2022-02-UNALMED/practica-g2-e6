package gestorAplicacion.economia;
import java.io.Serializable;

public class PrestamoLargoPlazo extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String referencia;
	private String nombre;
	

	public PrestamoLargoPlazo(double valorIncial, double valorPagado, int tiempo, float TEA, String fechaInicio, String fechaPago, boolean cumplida, String nombre, String referencia) {
		super(valorIncial, valorPagado, tiempo, TEA, fechaPago, fechaPago, cumplida);
		this.nombre = nombre;
		this.referencia = referencia;
		
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

	
	
}
	

	