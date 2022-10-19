package gestorAplicacion.economia;

import java.time.LocalDate;

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
	
	public Ingreso(double valor, LocalDate fechaCreacion, boolean interno,Cuenta cuentaOrigen, Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		this(valor, fechaCreacion, true,null, cuentaOrigen, cuentaDestino,divisaOrigen,divisaDestino);
	}

	public Ingreso(double valor, LocalDate fechaCreacion , Banco bancoOrigen, Cuenta cuentaDestino, Divisa divisa) {
		this(valor,fechaCreacion,false, bancoOrigen, null,cuentaDestino,divisa,divisa);
	}

	public Ingreso(double valor, LocalDate fechaCreacion, boolean interno, Banco banco, Cuenta cuentaOrigen, Cuenta cuentaDestino, Divisa divisaOrigen, Divisa divisaDestino) {
		super(valor, fechaCreacion, interno, banco);
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.divisaOrigen = divisaOrigen;
		this.divisaDestino = divisaDestino;
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
		
}
