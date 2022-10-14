package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;

public class Meta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 659116063038663746L;
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private int plazo;
	private boolean automatico;
	private String nombre;
	private boolean cumplida;
	private String fechaCumplimiento;
	private String fechaInicio;
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public int getPlazo() {
		return plazo;
	}
	
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	
	public boolean isAutomatico() {
		return automatico;
	}
	
	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
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
