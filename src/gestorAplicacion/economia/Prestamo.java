package gestorAplicacion.economia;

import java.io.Serializable;

public class Prestamo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5514442359859569278L;
	
	private double valorInicual;
	private double valorPagado;
	private int tiempo;
	private float TEA;
	private String fechaInicio;
	private String fechaPago;
	
	public double getValorInicual() {
		return valorInicual;
	}
	public void setValorInicual(double valorInicual) {
		this.valorInicual = valorInicual;
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
	public float getTEA() {
		return TEA;
	}
	public void setTEA(float tEA) {
		TEA = tEA;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
}
