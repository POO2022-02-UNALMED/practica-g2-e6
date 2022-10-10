package gestorAplicacion.usuario;

import java.util.ArrayList;

public class Usuario{
	
	private String Nombre;
	private String Email;
	private String fechaNacimiento;
	private String fechaIngreso;
	private String Clave;
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Usuario(String Nombre, String Email, String fechaNacimiento, String fechaIngreso, String Clave){
		this.setNombre(Nombre);
		this.setEmail(Email);
		this.setFechaNacimiento(fechaNacimiento);
		this.setFechaIngreso(fechaIngreso);
		this.setClave(Clave);
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
		return Clave;
	}
	public void setClave(String clave) {
		Clave = clave;
	}
	
	public String toString() {
		return this.getNombre()+" "+this.getClave();
		
	}
	
}
