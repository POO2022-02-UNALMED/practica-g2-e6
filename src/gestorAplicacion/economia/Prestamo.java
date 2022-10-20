package gestorAplicacion.economia;

import java.io.Serializable;

public abstract class Prestamo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5514442359859569278L;
	
	protected double valorInicial;
	protected double valorPagado;
	protected int tiempo;
	private float TEA;
	private String fechaInicio;
	private String fechaPago;
	private boolean cumplida; // Con la intencion de que si se cumple el pago, imprima algun string
	
	public Prestamo(double valorInicial, double valorPagado, int tiempo, float TEA, String fechaInicio, String fechaPago, boolean cumplida) {
		setValorInicial(valorInicial);
		setValorPagado(valorPagado);
		setTiempo(tiempo);
		setTEA(TEA);
		setFechaInicio(fechaInicio);
		setFechaPago(fechaPago);
		setCumplida(cumplida);
	}
	
	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	//public double getValorInicual() {
		//return valorInicial;
	//}
	//public void setValorInicual(double valorInicial) {
		//this.valorInicial = valorInicial;
	//}
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
	
	public boolean getCumplida(boolean cumplida) {
		return cumplida;
		
	}
	public boolean isCumplida() {
		return cumplida;
	}
	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}
	
	
	
}
