package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestorAplicacion.usuario.Usuario;

public class Serializador {
	private static File rutaTemp = new File("src\\baseDatos\\temp".replaceAll("\\",System.getProperty("file.separator"));
	
	
	public static void serializar() {
		FileOutputStream fos;
		ObjectOutputStream oss;
		File[] docs = rutaTemp.listFiles();
		PrintWriter pw;
		
		for (File file : docs) {
			if(!file.exists()) {
				pw = new PrintWriter(file);
			}
		}
		
		for (File file : docs) {
			
			if(file.getAbsolutePath().contains("usuarios")) {
				try {
					fos = new FileOutputStream(file);
					oss = new ObjectOutputStream(fos);
					oss.writeObject(Usuario.getUsuarios());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(file.getAbsolutePath().contains("bolsillos")) {
				try {
					fos = new FileOutputStream(file);
					oss = new ObjectOutputStream(fos);
					oss.writeObject(usu.getBolsillos());
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
