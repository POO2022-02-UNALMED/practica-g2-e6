package gestorAplicacion.usuario;

public class Divisa extends Cuenta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338275442776943532L;
	
	private String nombre;
	private Usuario usuario;
	private double tasaCambio;
	
	public Divisa(double inicial, String nombre, Usuario usuario, double tasaCambio) {
		super(inicial);
		setNombre(nombre);
		setUsuario(usuario);
		setTasaCambio(tasaCambio);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getTasaCambio() {
		return tasaCambio;
	}

	public void setTasaCambio(double tasaCambio) {
		this.tasaCambio = tasaCambio;
	}

	
	
}
