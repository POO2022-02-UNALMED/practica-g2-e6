package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;

import administrador.DataBank;

public class Deserializador {

    private static final File rutaTemp = Path.of("src", "baseDatos", "temp").toFile();

    static {
        Deserializador.rutaTemp.mkdirs();
    }

    public static void deserializar() {
        File[] docs = rutaTemp.listFiles() != null ? rutaTemp.listFiles() : new File[0];
        FileInputStream fis;
        ObjectInputStream ois;
        assert docs != null;
        for (File file : docs) {
            for (String[] element : DataBank.filesList) {
                if (file.getAbsolutePath().endsWith(element[0])) {
                    try {
                        fis = new FileInputStream(file);
                        ois = new ObjectInputStream(fis);
                        DataBank.class.getMethod(element[1], Object.class).invoke(new DataBank(), ois.readObject());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
