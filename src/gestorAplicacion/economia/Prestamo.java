package gestorAplicacion.economia;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Prestamo implements Serializable, Abonable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5514442359859569278L;
	
	protected double valorInicial;
	protected double valorPagado;
	protected int tiempo;			//meses diferidos
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private Divisa divisa;
	private boolean cumplida;

	private LocalDate ultimaFechaPago;

	public Prestamo(double valorInicial,int tiempo ,LocalDate fechaInicio, LocalDate fechaFinal) {
		setValorInicial(valorInicial);
		valorPagado = 0;
		setTiempo(tiempo);
		setFechaInicio(fechaInicio);
		setFechaPago(fechaFinal);
		setDivisa(Divisa.COP);
		setCumplida(false);
	}
	
	protected abstract void setTEA(double monto);
	protected abstract void setTEA(double monto, Garantia garantia);
	
	public double getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(double valorPagado) {
		this.valorPagado = valorPagado;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaPago() {
		return fechaFinal;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaFinal = fechaPago;
	}
	public Divisa getDivisa() {
		return divisa;
	}
	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}
	public boolean isCumplida() {
		return cumplida;
	}
	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}
}
