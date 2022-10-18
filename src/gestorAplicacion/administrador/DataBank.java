package gestorAplicacion.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Deserializador;
import gestorAplicacion.usuario.*;


public class DataBank implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2979265545810011076L;
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
	
	public DataBank() {
		Deserializador.deserializar(this);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios=usuarios;
	}
	
	public List<Bolsillo> getBolsillos(){
		return bolsillos;
	}
	
	public void setBolsillos(List<Bolsillo> bolsillos) {
		this.bolsillos=bolsillos;
	}
	
	public Usuario nuevoUsuario(String nombre, String email, String fechaNacimiento, String fechaIngreso, String clave) {
		Usuario usuario = new Usuario(nombre, email, fechaNacimiento, fechaIngreso, clave);
		usuarios.add(usuario);
		return usuario;
	}
	
	public List<Bolsillo> getBolsillosUsuario(Usuario usu){
		
		List<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
		
		for(int i = 0; i < this.bolsillos.size(); i++) {
			if(bolsillos.get(i).getUsuario().equals(usu)) {
				bolsillos.add(bolsillos.get(i));
			}
		}
		return bolsillos;
		
	}
	
	public Usuario getUsuarioPrincipal(String cc) {
		for(int i = 0; i < usuarios.size() ; i++) {
			if(usuarios.get(i).getCedula().equals(cc)) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
}