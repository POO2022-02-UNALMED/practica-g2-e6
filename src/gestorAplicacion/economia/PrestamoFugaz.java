package gestorAplicacion.economia;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoFugaz extends Prestamo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	public PrestamoFugaz(String nombre, double valorInicial, double valorPagado, int tiempo, float TEA, String fechaInicio, String fechaPago, boolean cumplida) {
		super(valorInicial, valorPagado, tiempo, TEA, fechaPago, fechaPago, cumplida);
		this.nombre=nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	

	
	


}
