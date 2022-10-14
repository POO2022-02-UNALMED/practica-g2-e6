package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;

public class Meta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private int Plazo;
	private boolean Automatico;
	private String Nombre;
	private boolean Cumplida;
	private String fechaCumplimiento;
	private String fechaInicio;
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public int getPlazo() {
		return Plazo;
	}
	
	public void setPlazo(int plazo) {
		Plazo = plazo;
	}
	
	public boolean isAutomatico() {
		return Automatico;
	}
	
	public void setAutomatico(boolean automatico) {
		Automatico = automatico;
	}
	
	public String getNombre() {
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public boolean isCumplida() {
		return Cumplida;
	}
	
	public void setCumplida(boolean cumplida) {
		Cumplida = cumplida;
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
	

}
