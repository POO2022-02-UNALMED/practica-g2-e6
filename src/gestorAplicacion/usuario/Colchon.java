package gestorAplicacion.usuario;

import java.time.LocalDate;

import gestorAplicacion.economia.Divisa;

public class Colchon extends Cuenta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 514183129515203955L;
	
	private String nombre;
	private LocalDate fechaRetiro;


	public Colchon(Usuario usuario, Divisa divisa, String nombre, LocalDate fechaRetiro) {
		super(usuario,divisa);
		setNombre(nombre);
		setUsuario(usuario);
		setFechaRetiro(fechaRetiro);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDate fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
}
