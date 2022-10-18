package gestorAplicacion.administrador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Deserializador;
import gestorAplicacion.economia.Banco;
import gestorAplicacion.economia.Divisa;
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
	private List<Banco> bancos = new ArrayList<Banco>();
	
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

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	public Usuario nuevoUsuario(String cedula, String nombre, String email, LocalDate fechaIngreso, String clave) {
		Usuario usuario = new Usuario(cedula,nombre, email, fechaIngreso, clave);
		usuarios.add(usuario);
		return usuario;
	}
	
	public List<Bolsillo> getBolsillosUsuario(Usuario usu){
		
		List<Bolsillo> bolsillos = new ArrayList<Bolsillo>();
		
		for(Bolsillo i:this.bolsillos) {
			if(i.getUsuario().equals(usu)) {
				bolsillos.add(i);
			}
		}
		return bolsillos;
		
	}
	
	public List<Colchon> getColchonesUsuario(Usuario usu){
		
		List<Colchon> colchones = new ArrayList<Colchon>();
		
		for(Colchon i:this.colchones) {
			if(i.getUsuario().equals(usu)) {
				colchones.add(i);
			}
		}
		return colchones;
		
	}
	
	public Usuario getUsuarioPrincipal(String cc) {
		for(Usuario i:usuarios) {
			if(i.getCedula().equals(cc)) {
				return i;
			}
		}
		return null;
	}
	
	public double dineroTotalUsu(Usuario usu) {
		
		double total=0;
		
		for(Bolsillo i:bolsillos) {
			if(i.getUsuario().equals(usu)) {
				total+=i.getSaldo();
			}
		}
		
		for(Colchon i:colchones) {
			if(i.getUsuario().equals(usu)) {
				total+=i.getSaldo();
			}
		}
		return total;
	}
	
	public void nuevoBolsillo(Bolsillo bolsillo, Usuario usuario) {
		this.bolsillos.add(bolsillo);
		usuario.nuevoBolsillo(bolsillo);
	}
	
	public void nuevaDivisa(Divisa divisa) {
		this.divisas.add(divisa);
	}
	
}