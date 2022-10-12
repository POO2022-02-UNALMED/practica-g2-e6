package gestorAplicacion.usuario;

import baseDatos.Conexion;

public class Probar {

	public static void main(String[] args) {
		Conexion conn = new Conexion();
		conn.guardar(new Usuario("Manuela", "123manu@unal.edu.co", "enero", "hoy", "vinilostopia"));
		//TODO:  
	}

}
