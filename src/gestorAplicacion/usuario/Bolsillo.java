package gestorAplicacion.usuario;


public class Bolsillo extends Cuenta{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479140568742305806L;
	private String nombre;
	private Usuario usuario;


	public Bolsillo(double saldo, String Nombre, Usuario usuario) {
		super(saldo);
		setNombre(nombre);
		setUsuario(usuario);
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

}
