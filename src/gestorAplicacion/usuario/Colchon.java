package gestorAplicacion.usuario;

public class Colchon extends Cuenta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 514183129515203955L;
	
	private String nombre;
	private Usuario usuario;
	private String fechaRetiro;


	public Colchon(double saldo, String Nombre, Usuario usuario, String fechaRetiro) {
		super(saldo);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
}
