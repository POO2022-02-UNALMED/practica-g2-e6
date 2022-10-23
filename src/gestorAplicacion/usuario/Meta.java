package gestorAplicacion.usuario;

import gestorAplicacion.economia.Abonable;
import gestorAplicacion.economia.Contable;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.economia.Salida;

import java.io.Serializable;
import java.time.LocalDate;

public class Meta implements Serializable, Abonable, Contable {

	private static final long serialVersionUID = 659116063038663746L;

	private Usuario usuario;

	private String nombre;
	private boolean cumplida;
	private LocalDate fechaCumplimiento;
	private LocalDate fechaInicio;

	private double objetivo;

	private Divisa divisa;

	private double saldo = 0;

	public Meta(Usuario usuario, String nombre, LocalDate fechaInicio, double objetivo, Divisa divisa) {
		this.nombre = nombre;
		this.cumplida = false;
		this.fechaCumplimiento = null;
		this.fechaInicio = fechaInicio;
		this.objetivo = objetivo;
		this.divisa = divisa;
		this.usuario = usuario;
		this.saldo = 0;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaCumplimiento() {
		return fechaCumplimiento;
	}

	public void setFechaCumplimiento(LocalDate fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Override
	public Salida abonar(double monto) {
		return null;
	}
}
