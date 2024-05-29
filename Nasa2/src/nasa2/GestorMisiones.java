/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nasa2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */

public class GestorMisiones {
    private static final String NOMBRE_ARCHIVO = "misiones.txt";

    public static void guardarMision(Mision mision) throws IOException, ClassNotFoundException {
        List<Mision> misiones = cargarMisiones();
        misiones.add(mision);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(misiones);
        }
    }

    public static List<Mision> cargarMisiones() throws IOException, ClassNotFoundException {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Mision>) ois.readObject();
        }
    }
}

