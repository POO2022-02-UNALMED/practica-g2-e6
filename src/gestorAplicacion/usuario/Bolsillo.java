package gestorAplicacion.usuario;


public class Bolsillo extends Cuenta{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2479140568742305806L;
	private String nombre;

	public Bolsillo(Usuario usuario, double saldo,Divisa divisa, String Nombre) {
		super(usuario,divisa);
		this.depositar(saldo);
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
