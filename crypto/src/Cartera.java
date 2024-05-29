import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Cartera {

    private static final String NOMBRE_ARCHIVO = "cartera.txt";

    public static Map<String, Double> obtenerCartera() {
        Map<String, Double> cartera = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                cartera.put(partes[0], Double.parseDouble(partes[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cartera;
    }

    public static void guardarCartera(Map<String, Double> cartera) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Map.Entry<String, Double> entrada : cartera.entrySet()) {
                writer.write(entrada.getKey() + "," + entrada.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gestionarCompra(String criptomoneda, double cantidad) {
        Map<String, Double> cartera = obtenerCartera();
        cartera.put(criptomoneda, cartera.getOrDefault(criptomoneda, 0.0) + cantidad);
        guardarCartera(cartera);
    }

    public static void gestionarVenta(String criptomoneda, double cantidad) {
        Map<String, Double> cartera = obtenerCartera();
        cartera.put(criptomoneda, cartera.getOrDefault(criptomoneda, 0.0) - cantidad);
        guardarCartera(cartera);
    }
}
