package gestorAplicacion.economia;

import java.io.Serializable;

public class Banco implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5615885459241495302L;
	
	private String nombre;
	private boolean convenio;
	
	public Banco(String nombre, boolean convenio) {
		setNombre(nombre);
		setConvenio(convenio);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isConvenio() {
		return convenio;
	}

	public void setConvenio(boolean convenio) {
		this.convenio = convenio;
	}
}
