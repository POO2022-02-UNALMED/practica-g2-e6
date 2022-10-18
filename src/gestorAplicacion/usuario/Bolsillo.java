package gestorAplicacion.usuario;

import java.io.Serializable;

public class Bolsillo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479140568742305806L;
	private String Nombre;
	private double Total;
	private Usuario usuario;


	public Bolsillo(String Nombre, double Total, Usuario usuario) {
		setNombre(Nombre);
		setTotal(Total);
		setUsuario(usuario);
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
