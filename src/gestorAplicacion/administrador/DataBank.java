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
	private List<Colchon> colchones = new ArrayList<Colchon>();
	private List<Divisa> divisas = new ArrayList<Divisa>();
	
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
	
	public List<Colchon> getColchon() {
		return colchones;
	}

	public void setColchon(List<Colchon> colchones) {
		this.colchones = colchones;
	}
	
	public List<Divisa> getDivisas(){
		return divisas;
	}
	
	public void setDivisas(List<Divisa> divisas) {
		this.divisas=divisas;
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
				bolsillos.add(this.bolsillos.get(i));
			}
		}
		return bolsillos;
		
	}
	
	public List<Colchon> getColchonesUsuario(Usuario usu){
		
		List<Colchon> colchones = new ArrayList<Colchon>();
		
		for(int i = 0; i < this.colchones.size(); i++) {
			if(colchones.get(i).getUsuario().equals(usu)) {
				colchones.add(this.colchones.get(i));
			}
		}
		return colchones;
		
	}
	
	public Usuario getUsuarioPrincipal(String cc) {
		for(int i = 0; i < usuarios.size() ; i++) {
			if(usuarios.get(i).getCedula().equals(cc)) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
	public double dineroTotalUsu(Usuario usu) {
		
		double total=0;
		
		for(int i = 0; i < this.bolsillos.size(); i++) {
			if(bolsillos.get(i).getUsuario().equals(usu)) {
				total+=bolsillos.get(i).getSaldo();
			}
		}
		
		for(int i = 0; i < this.colchones.size(); i++) {
			if(colchones.get(i).getUsuario().equals(usu)) {
				total+=colchones.get(i).getSaldo();
			}
		}
		return total;
	}
	
}