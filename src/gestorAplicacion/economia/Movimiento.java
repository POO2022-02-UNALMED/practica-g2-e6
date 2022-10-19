package gestorAplicacion.economia;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102227367414792060L;
	
	
	private double valor;
	private LocalDate fechaCreacion;
	private boolean interno;
	private Banco bancoOrigen;
	
	public Movimiento(double valor, LocalDate fechaCreacion, boolean interno, Banco bancoOrigen) {
		setValor(valor);
		setFechaCreacion(fechaCreacion);
		setInterno(interno);
		setBanco(bancoOrigen);
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	public Banco getBanco() {
		return bancoOrigen;
	}
	public void setBanco(Banco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}
	
	
}