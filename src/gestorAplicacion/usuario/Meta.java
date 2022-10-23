package gestorAplicacion.usuario;

import gestorAplicacion.economia.Abonable;
import gestorAplicacion.economia.Contable;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Salida;

import java.io.Serializable;

public class Meta implements Serializable, Abonable, Contable {

	private static final long serialVersionUID = 659116063038663746L;
	private String nombre;
	private boolean cumplida;
	private String fechaCumplimiento;
	private String fechaInicio;

	private double objetivo;

	private Divisa divisa;

	private double saldo = 0;

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isCumplida() {
		return cumplida;
	}
	
	public void setCumplida(boolean cumplida) {
		this.cumplida = cumplida;
	}
	
	public String getFechaCumplimiento() {
		return fechaCumplimiento;
	}
	
	public void setFechaCumplimiento(String fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public double getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(double objetivo) {
		this.objetivo = objetivo;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public Salida abonar(double monto) {
		return null;
	}
}
