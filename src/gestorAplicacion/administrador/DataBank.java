package gestorAplicacion.administrador;

import java.io.Serializable;
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

	public void nuevoUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	
	public Usuario getUsuarioPrincipal(String cc) {
		for(Usuario i:usuarios) {
			if(i.getCedula().equals(cc)) {
				return i;
			}
		}
		return null;
	}
	
	public double dineroTotalUsu(Usuario usuario) {
		
		double total=0;
		
		for(Bolsillo i:usuario.getBolsillos()) {
			total+=i.getSaldo();
		}
		
		for(Colchon i:usuario.getColchones()) {
			total+=i.getSaldo();
		}
		return total;
	}
	
	public void nuevaDivisa(Divisa divisa) {
		this.divisas.add(divisa);
	}
	
}