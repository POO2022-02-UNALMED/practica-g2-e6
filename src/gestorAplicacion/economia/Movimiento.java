package gestorAplicacion.economia;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102227367414792060L;
	
	
	private double valorDestino;
	private double valorOrigen;
	private LocalDate fechaCreacion;
	private boolean interno;
	private Banco bancoOrigen;
	
	public Movimiento(double valorDestino, double valorOrigen, LocalDate fechaCreacion, boolean interno, Banco bancoOrigen) {
		setValorDestino(valorDestino);
		setValorOrigen(valorOrigen);
		setFechaCreacion(fechaCreacion);
		setInterno(interno);
		setBanco(bancoOrigen);
	}
	
	public double getValorDestino() {
		return valorDestino;
	}
	public void setValorDestino(double valorDestino) {
		this.valorDestino = valorDestino;
	}
	public double getValorOrigen() {
		return valorOrigen;
	}
	public void setValorOrigen(double valorOrigen) {
		this.valorOrigen = valorOrigen;
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