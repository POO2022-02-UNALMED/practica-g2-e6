package gestorAplicacion.usuario;

import gestorAplicacion.economia.Divisa;

import java.time.LocalDate;

public class Colchon extends Cuenta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 514183129515203955L;

	private LocalDate fechaRetiro;


	public Colchon(Usuario usuario, Divisa divisa, String nombre, LocalDate fechaRetiro) {
		super(usuario,divisa, nombre);
		setUsuario(usuario);
		setFechaRetiro(fechaRetiro);
	}

	public boolean retirar(double monto){
		if(this.fechaRetiro.isBefore(LocalDate.now())){
			return super.retirar(monto);
		}
		return false;


	}
	public LocalDate getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDate fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
}
