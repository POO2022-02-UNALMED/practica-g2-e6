package gestorAplicacion.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Deserializador;
import gestorAplicacion.usuario.*;


public class Departamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
	
	public Departamento() {
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
	
}