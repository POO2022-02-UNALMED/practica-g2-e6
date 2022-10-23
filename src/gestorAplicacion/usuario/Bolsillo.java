package gestorAplicacion.usuario;

import gestorAplicacion.economia.Contable;
import gestorAplicacion.economia.Divisa;

import java.io.Serializable;

public class Bolsillo extends Cuenta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479140568742305806L;
	private String nombre;

	public Bolsillo(Usuario usuario, Divisa divisa, String nombre) {
		super(usuario,divisa);
		setNombre(nombre);
		setUsuario(usuario);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
