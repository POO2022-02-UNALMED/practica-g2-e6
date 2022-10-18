package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Serializador;

public class Cuenta implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5757213716524384183L;
	
	
	private int numeroCuenta;
    private double saldo;
    public static int numeroCuentas;
    
    
    public Cuenta(double inicial) {
        saldo = inicial;
        setNumeroCuenta(numeroCuentas++);
    } 

    public void depositar(double cantidad) {
        saldo = saldo + cantidad;
    }

    public void retirar(double cantidad) {
        saldo = saldo - cantidad;
    }

    public double getSaldo() {
        return saldo;
    }

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
    
}
