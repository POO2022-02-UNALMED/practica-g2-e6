package gestorAplicacion.economia;

import java.io.Serializable;

public abstract class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102227367414792060L;
	
	
	private double valor;
	private String fechaCreacion;
	private boolean interno;
	private Banco banco;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
}