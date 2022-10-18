package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import gestorAplicacion.administrador.DataBank;

public class Serializador {
	
	private static File rutaTemp = Paths.get("src\\baseDatos\\temp").toFile();
	static {
		Serializador.rutaTemp.mkdirs();
	}
	public static void serializar(DataBank databank) {
		crearArchivos();
		FileOutputStream fos;
		ObjectOutputStream oos;
		File[] docs = rutaTemp.listFiles();
		PrintWriter pw;

		for (File file : docs) {
			try {
			
				pw = new PrintWriter(file);			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		for(File file : docs) {
			if(file.getAbsolutePath().contains("usuarios.txt")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(databank.getUsuarios());
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}else if(file.getAbsolutePath().contains("bolsillos.txt")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(databank.getBolsillos());
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}else if(file.getAbsolutePath().contains("divisas.txt")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(databank.getDivisas());
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void crearArchivos() {
		File f=new File("src\\baseDatos\\temp\\usuarios.txt");
        File f2=new File("src\\baseDatos\\temp\\bolsillos.txt");
        File f3=new File("src\\baseDatos\\temp\\divisas.txt");
        if(!f.exists()){
            try{
              f.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(!f2.exists()){
            try{
              f2.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(!f3.exists()){
            try{
              f3.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
	}
}
