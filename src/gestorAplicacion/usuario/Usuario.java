package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;


public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -64431385135968757L;
	
	private String cedula;
	private String nombre;
	private String email;
	private String fechaNacimiento;
	private String fechaIngreso;
	private String clave;
	private double total;
	public static int numeroUsuarios;
	
	public Usuario(String nombre, String email, String fechaNacimiento, String fechaIngreso, String clave){
		setNombre(nombre);
		setEmail(email);
		setFechaNacimiento(fechaNacimiento);
		setFechaIngreso(fechaIngreso);
		setClave(clave);
		numeroUsuarios++;
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String toString() {
		return this.getNombre()+" "+this.getClave();
		
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
