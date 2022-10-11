package gestorAplicacion.usuario;

import java.util.ArrayList;

public class Bolsillo {
	
	private String Nombre;
	private double Total;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Bolsillo(String Nombre, double Total, ArrayList<Usuario> usuarios) {
		setNombre(Nombre);
		setTotal(Total);
		setUsuarios(usuarios);
	}
	
	public Bolsillo(String Nombre, double Total, Usuario usuario) {
		setNombre(Nombre);
		setTotal(Total);
		agregarUsuario(usuario);
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
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
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	
}
