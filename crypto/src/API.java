import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class API {

    public static String obtenerPrecios() {
        String apiUrl = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd";
        StringBuilder contenido = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String lineaEntrada;

            while ((lineaEntrada = in.readLine()) != null) {
                contenido.append(lineaEntrada);
            }

            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contenido.toString();
    }

    public static void main(String[] args) {
        System.out.println(obtenerPrecios());
    }
}

