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
	

	private String nombre;
	
	public PrestamoFugaz(double valorInicial, int tiempo, double TEA, LocalDate fechaInicio, String nombre) {
		super(valorInicial, tiempo, TEA, fechaInicio, fechaInicio.plusMonths(tiempo));
		this.nombre=nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	

	
	


}
