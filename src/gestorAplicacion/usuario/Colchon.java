package gestorAplicacion.usuario;

public class Colchon extends Cuenta{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 514183129515203955L;
	
	private String nombre;
	private String fechaRetiro;


	public Colchon(Usuario usuario, Divisa divisa, double saldo, String Nombre, String fechaRetiro) {
		super(usuario,divisa);
		this.depositar(saldo);
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

	public String getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
}
