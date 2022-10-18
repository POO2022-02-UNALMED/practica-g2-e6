package gestorAplicacion.economia;

import gestorAplicacion.usuario.Usuario;

public class Salida extends Movimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919824199311137700L;
	
	private Usuario usuarioOrigen;
	private Usuario usuarioDestino;
	private Divisa divisaOrigen;
	private Divisa divisaDestino;
	private Banco bancoDestino;
	
	public Salida(Usuario usuarioOrigen, Usuario usuarioDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		setUsuarioOrigen(usuarioOrigen);
		setUsuarioDestino(usuarioDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
	}
	
	public Salida(Usuario usuarioOrigen, Banco bancoDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		setBancoDestino(bancoDestino);
		setUsuarioDestino(usuarioOrigen);
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
	public Banco getBancoDestino() {
		return bancoDestino;
	}
	public void setBancoDestino(Banco bancoDestino) {
		this.bancoDestino = bancoDestino;
	}
	
	
	
}