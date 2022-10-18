package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.List;

import gestorAplicacion.administrador.DataBank;
import gestorAplicacion.economia.Divisa;
import gestorAplicacion.usuario.*;

public class Deserializador {
	
	private static File rutaTemp = Paths.get("src\\baseDatos\\temp").toFile();
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
			
			
			}else if(file.getAbsolutePath().contains("divisas.txt")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					databank.setDivisas((List<Divisa>) ois.readObject());
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
