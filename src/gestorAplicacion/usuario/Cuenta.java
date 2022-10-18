package gestorAplicacion.usuario;

import java.io.Serializable;


public class Cuenta implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5757213716524384183L;
	
	
	private int numeroCuenta;
    private double saldo;
    private Divisa divisa;
    public static int numeroCuentas;
    
    
    public Cuenta(Divisa divisa) {
        saldo = 0;
        setDivisa(divisa);
        setNumeroCuenta(numeroCuentas++);
    } 

    public void depositar(double cantidad) {
        saldo = saldo + cantidad;
    }

    public boolean retirar(double cantidad) {
    	if(cantidad<saldo) {
    		saldo = saldo - cantidad;
    		return true;
    	}else {
    		return false;
    	}
    }

    public double getSaldo() {
        return saldo;
    }

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
    
}
