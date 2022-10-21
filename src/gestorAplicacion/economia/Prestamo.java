package gestorAplicacion.economia;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Prestamo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5514442359859569278L;
	
	protected double valorInicial;
	protected double valorPagado;
	protected int tiempo;			//meses diferidos
	private double TEA;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private boolean cumplida; // Con la intencion de que si se cumple el pago, imprima algun string
	
	public Prestamo(double valorInicial,int tiempo , double TEA, LocalDate fechaInicio, LocalDate fechaFinal) {
		setValorInicial(valorInicial);
		valorPagado = 0;
		setTiempo(tiempo);
		setTEA(TEA);
		setFechaInicio(fechaInicio);
		setFechaPago(fechaFinal);
		setCumplida(false);
	}
	
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
	public double getTEA() {
		return TEA;
	}
	public void setTEA(double tEA) {
		TEA = tEA;
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
	public boolean isCumplida() {
		return cumplida;
	}
	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}
	
}
