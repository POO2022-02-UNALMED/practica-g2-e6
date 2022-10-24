package gestorAplicacion.usuario;

import gestorAplicacion.economia.Contable;
import gestorAplicacion.economia.Divisa;

import java.io.Serializable;


public abstract class Cuenta implements Serializable, Contable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5757213716524384183L;
	
	
	private int numeroCuenta;
	private Usuario usuario;
    private double saldo;
    private Divisa divisa;
	private String nombre;
	
    protected Cuenta(Usuario usuario, Divisa divisa, String nombre)  {
        saldo = 0;
        setUsuario(usuario);
        setDivisa(divisa);
		setNombre(nombre);
    }
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void depositar(double cantidad) {
        saldo = saldo + cantidad;
    }

    public boolean retirar(double cantidad) {
    	if(cantidad<=saldo) {
    		saldo = saldo - cantidad;
    		return true;
    	}else {
    		return false;
    	}
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}
    
}
