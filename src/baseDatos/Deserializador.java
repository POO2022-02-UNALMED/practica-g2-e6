package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import gestorAplicacion.administrador.Departamento;
import gestorAplicacion.usuario.Bolsillo;
import gestorAplicacion.usuario.Usuario;

public class Deserializador {
	private static File rutaTemp = new File("src\\baseDatos\\temp");
	
	public static void deserializar(Departamento depto) {
		File[] docs = rutaTemp.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;
		
		for(File file : docs) {
			if(file.getAbsolutePath().contains("usuarios")) {
				try{
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					depto.setUsuarios((List<Usuario>) ois.readObject());
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			
			
			}else if(file.getAbsolutePath().contains("bolsillos")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					depto.setBolsillos((List<Bolsillo>) ois.readObject());
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
