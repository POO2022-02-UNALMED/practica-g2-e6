package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.usuario.*;

public class Deserializador {
	
	private static File rutaTemp = new File("src/baseDatos/temp".replaceAll("/", System.getProperty("file.separator")));
	static {
		Deserializador.rutaTemp.mkdirs();
	}
	public static void deserializar(DataBank databank) {
		File[] docs = rutaTemp.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;
		
		for(File file : docs) {
			if(file.getAbsolutePath().contains("usuarios.txt")) {
				try{
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					databank.setUsuarios((List<Usuario>) ois.readObject());
				} catch(FileNotFoundException e){
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			
			
			}else if(file.getAbsolutePath().contains("bolsillos.txt")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					databank.setBolsillos((List<Bolsillo>) ois.readObject());
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
