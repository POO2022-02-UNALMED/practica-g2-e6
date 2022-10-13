package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import gestorAplicacion.usuario.Usuario;

public class Serializador {
	private static String rutaTemp = new File(
			"src\\baseDatos\\temp".replaceAll("\\", System.getProperty("file.separator"))).getAbsolutePath();
	private static final ArrayList<String> data = new ArrayList<String>();
	static {
		new File(rutaTemp).getParentFile().mkdirs();
	}

	public static void addClassToData(String data) {
		Serializador.data.add(data);
	}

	public static void serializar(Object administator) {
		FileOutputStream fos;
		ObjectOutputStream oss;
		PrintWriter pw;

		for (String name : data) {
			File file = new File((rutaTemp + "\\" + name + ".txt").replaceAll("\\", System.getProperty("file.separator")));
			try {
				if (!file.exists()) {
					pw = new PrintWriter(file);
				}
				fos = new FileOutputStream(file);
				oss = new ObjectOutputStream(fos);
				oss.writeObject(administator.getClass().getMethod("get" + name).invoke(administator));
				fos.close();
				oss.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
