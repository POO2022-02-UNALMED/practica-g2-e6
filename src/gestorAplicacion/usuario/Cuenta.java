package gestorAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import baseDatos.Serializador;

public class Cuenta implements Serializable{
    
    private int numeroCuenta;
    private double saldo;
    private String nombre;
    
    public String getNombre(){
        return nombre;
    }
    
    public Cuenta(int cuenta, double inicial) {
        numeroCuenta = cuenta;
        saldo = inicial;
    } 

    public void depositar(double cantidad) {
        saldo = saldo + cantidad;
    }

    public void retirar(double cantidad) {
        saldo = saldo - cantidad;
    }

    public double saldo() {
        return saldo;
    }  
    
}
