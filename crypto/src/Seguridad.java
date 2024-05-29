import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class Seguridad {

    private static final String ALGORITMO = "AES";

    public static String cifrar(String datos, String clave) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);
        byte[] datosCifrados = cipher.doFinal(datos.getBytes());
        return Base64.getEncoder().encodeToString(datosCifrados);
    }

    public static String descifrar(String datosCifrados, String clave) throws Exception {
        SecretKeySpec claveSecreta = new SecretKeySpec(clave.getBytes(), ALGORITMO);
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
        byte[] datosOriginales = cipher.doFinal(Base64.getDecoder().decode(datosCifrados));
        return new String(datosOriginales);
    }

    public static void main(String[] args) {
        try {
            String clave = "mi-clave-secreta";  // Reemplazar con una clave segura
            String original = "Hola, Mundo!";
            String cifrado = cifrar(original, clave);
            String descifrado = descifrar(cifrado, clave);

            System.out.println("Original: " + original);
            System.out.println("Cifrado: " + cifrado);
            System.out.println("Descifrado: " + descifrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
