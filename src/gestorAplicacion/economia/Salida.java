package gestorAplicacion.economia;

import java.time.LocalDate;

import gestorAplicacion.usuario.Cuenta;

public class Salida extends Movimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919824199311137700L;
	
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Divisa divisaOrigen;
	private Divisa divisaDestino;
	private Banco bancoDestino;
	
	public Salida(double valorDestino, double valorOrigen, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuantaDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		super(valorDestino,valorOrigen, fechaCreacion, true, null);
		setCuentaOrigen(cuentaOrigen);
		setCuentaDestino(cuantaDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
	}
	
	public Salida(double valorDestino, LocalDate fechaCreacion, Cuenta cuentaOrigen, Banco bancoDestino, Divisa divisaOrigen) {
		super(valorDestino,valorDestino, fechaCreacion, false, bancoDestino);
		setBancoDestino(bancoDestino);
		setCuentaOrigen(cuentaOrigen);
		setDivisaOrigen(divisaOrigen);
	}
	
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
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