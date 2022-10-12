package gestorAplicacion.usuario;

import java.util.ArrayList;

public class Usuario{
	
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	private String Nombre;
	private String Email;
	private String fechaNacimiento;
	private String fechaIngreso;
	private String Clave;
	private ArrayList<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
	private double Total;
	public static int numeroUsuarios;
	
	public Usuario(String Nombre, String Email, String fechaNacimiento, String fechaIngreso, String Clave){
		setNombre(Nombre);
		setEmail(Email);
		setFechaNacimiento(fechaNacimiento);
		setFechaIngreso(fechaIngreso);
		setClave(Clave);
		numeroUsuarios++;
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

	public ArrayList<Bolsillo> getBolsillos() {
		return bolsillos;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public boolean agregarBolsillo(String Nombre, double Total) {
		if(Total<getTotal()) {
			bolsillos.add(new Bolsillo(Nombre,Total,this));
			setTotal(getTotal()-Total);
			return true;
		}else {
			return false;
		}
		
	}
	
}
