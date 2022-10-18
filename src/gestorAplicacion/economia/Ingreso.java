package gestorAplicacion.economia;

import gestorAplicacion.usuario.Cuenta;

public class Ingreso extends Movimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919824199311137700L;
	
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Divisa divisaOrigen;
	private Divisa divisaDestino;
	private Banco bancoOrigen;
	
	public Ingreso(double valor, String fechaCreacion, boolean interno, Banco banco ,Cuenta cuentaOrigen, Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		super(valor, fechaCreacion, interno, banco);
		setCuentaOrigen(cuentaOrigen);
		setCuentaDestino(cuentaDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
	}
	
	public Ingreso(double valor, String fechaCreacion, boolean interno, Banco banco ,Banco bancoOrigen, Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		super(valor, fechaCreacion, interno, banco);
		setBancoOrigen(bancoOrigen);
		setCuentaDestino(cuentaDestino);
		setDivisaOrigen(divisaOrigen);
		setDivisaDestino(divisaDestino);
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
	public Banco getBancoOrigen() {
		return bancoOrigen;
	}
	public void setBancoOrigen(Banco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}
		
}
