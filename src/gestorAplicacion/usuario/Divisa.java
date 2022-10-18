package gestorAplicacion.usuario;

import java.io.Serializable;

public class Divisa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338275442776943532L;
	
	private String nombre;
	private double tasaCambio;
	
	public Divisa(String nombre, double tasaCambio) {
		setNombre(nombre);
		setTasaCambio(tasaCambio);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getTasaCambio() {
		return tasaCambio;
	}

	public void setTasaCambio(double tasaCambio) {
		this.tasaCambio = tasaCambio;
	}

	
	
}
