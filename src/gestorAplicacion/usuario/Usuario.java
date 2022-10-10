package gestorAplicacion.usuario;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	private String Nombre;
	private String Email;
	private Date fechaNacimiento;
	private Date fechaIngreso;
	private String Clave;
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Usuario(String Nombre, String Email, Date fechaNacimiento, Date fechaIngreso, String Clave) {
		
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getClave() {
		return Clave;
	}
	public void setClave(String clave) {
		Clave = clave;
	}
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
}
