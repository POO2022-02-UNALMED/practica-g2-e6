package gestorAplicacion.economia;

import gestorAplicacion.usuario.Divisa;
import gestorAplicacion.usuario.Usuario;

public class Ingreso extends Movimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919824199311137700L;
	
	private Usuario usuarioOrigen;
	private Usuario usuarioDestino;
	private Divisa divisaOrigen;
	private Divisa divisaDestino;
	private Banco bancoOrigen;
	
	public Ingreso(Usuario usuarioOrigen, Usuario usuarioDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		setUsuarioOrigen(usuarioOrigen);
		setUsuarioDestino(usuarioDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
	}
	
	public Ingreso(Banco bancoOrigen, Usuario usuarioDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		setBancoOrigen(bancoOrigen);
		setUsuarioDestino(usuarioDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
	}
	
	public Usuario getUsuarioOrigen() {
		return usuarioOrigen;
	}
	public void setUsuarioOrigen(Usuario usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	public Divisa getDivisaOrigen() {
		return divisaOrigen;
	}
	public void setDivisaOrigen(Divisa divisaOrigen) {
		this.divisaOrigen = divisaOrigen;
	}
	public Divisa getDivisaDestino() {
		return divisaDestino;
	}
	public void setDivisaDestino(Divisa divisaDestino) {
		this.divisaDestino = divisaDestino;
	}
	public Banco getBancoOrigen() {
		return bancoOrigen;
	}
	public void setBancoOrigen(Banco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}
	
	
	
}
