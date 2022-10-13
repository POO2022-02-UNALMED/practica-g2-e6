package gestorAplicacion.movimiento;

import java.io.Serializable;

public abstract class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102227367414792060L;
	
	private int tipo;
	private boolean recurrente;
	private int recurrencia;
	private double valor;
	private String fecha;
	private boolean automatico;
	private double trm;
	private boolean interno;
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public boolean isRecurrente() {
		return recurrente;
	}
	public void setRecurrente(boolean recurrente) {
		this.recurrente = recurrente;
	}
	public int getRecurrencia() {
		return recurrencia;
	}
	public void setRecurrencia(int recurrencia) {
		this.recurrencia = recurrencia;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public boolean isAutomatico() {
		return automatico;
	}
	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
	}
	public double getTrm() {
		return trm;
	}
	public void setTrm(double trm) {
		this.trm = trm;
	}
	public boolean isInterno() {
		return interno;
	}
	public void setInterno(boolean interno) {
		this.interno = interno;
	}
	
	
}