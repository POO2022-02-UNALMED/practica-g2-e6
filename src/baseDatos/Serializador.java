package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import gestorAplicacion.administrador.DataBank;

public class Serializador {
	private static String rutaTemp = new File("src\\baseDatos\\temp").getAbsolutePath();
	private static final ArrayList<String> data = new ArrayList<String>();
	static {
		new File(rutaTemp).getParentFile().mkdirs();
	}

	public static void addClassToData(String data) {
		Serializador.data.add(data);
	}

	public static void serializar(DataBank databank) {
		FileOutputStream fos;
		ObjectOutputStream oss;
		PrintWriter pw;

		for (String name : data) {
			File file = new File((rutaTemp + "\\" + name + ".txt"));
			try {
				if (!file.exists()) {
					pw = new PrintWriter(file);
				}
				fos = new FileOutputStream(file);
				oss = new ObjectOutputStream(fos);
				oss.writeObject(databank.getClass().getMethod("get" + name).invoke(databank));
				fos.close();
				oss.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
