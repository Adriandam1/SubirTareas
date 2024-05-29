import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AplicacionIntercambioCriptomonedas extends JFrame {

    private JTextArea areaPrecios;
    private JTextArea areaCartera;
    private JTextField campoComprar;
    private JTextField campoVender;
    private JButton botonActualizarPrecios;
    private JButton botonComprar;
    private JButton botonVender;

    public AplicacionIntercambioCriptomonedas() {
        setTitle("Plataforma de Intercambio de Criptomonedas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarInterfaz();
        actualizarAreaCartera();
    }

    private void inicializarInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        areaPrecios = new JTextArea(10, 50);
        areaPrecios.setEditable(false);
        panel.add(new JScrollPane(areaPrecios), BorderLayout.NORTH);

        areaCartera = new JTextArea(10, 50);
        areaCartera.setEditable(false);
        panel.add(new JScrollPane(areaCartera), BorderLayout.CENTER);

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout());

        campoComprar = new JTextField(10);
        campoVender = new JTextField(10);

        botonComprar = new JButton("Comprar");
        botonVender = new JButton("Vender");
        botonActualizarPrecios = new JButton("Actualizar Precios");

        panelControles.add(new JLabel("Comprar:"));
        panelControles.add(campoComprar);
        panelControles.add(botonComprar);
        panelControles.add(new JLabel("Vender:"));
        panelControles.add(campoVender);
        panelControles.add(botonVender);
        panelControles.add(botonActualizarPrecios);

        panel.add(panelControles, BorderLayout.SOUTH);

        add(panel);

        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarCriptomoneda();
            }
        });

        botonVender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                venderCriptomoneda();
            }
        });

        botonActualizarPrecios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecios();
            }
        });
    }

    private void actualizarPrecios() {
        String respuestaJson = API.obtenerPrecios();
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(respuestaJson);
            StringBuilder precios = new StringBuilder();
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String nombre = (String) jsonObj.get("name");
                Double precio = (Double) jsonObj.get("current_price");
                precios.append(nombre).append(": $").append(precio).append("\n");
            }
            areaPrecios.setText(precios.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void comprarCriptomoneda() {
        String[] partes = campoComprar.getText().split(" ");
        if (partes.length == 2) {
            String criptomoneda = partes[0];
            double cantidad = Double.parseDouble(partes[1]);
            Cartera.gestionarCompra(criptomoneda, cantidad);
            actualizarAreaCartera();
        }
    }

    private void venderCriptomoneda() {
        String[] partes = campoVender.getText().split(" ");
        if (partes.length == 2) {
            String criptomoneda = partes[0];
            double cantidad = Double.parseDouble(partes[1]);
            Cartera.gestionarVenta(criptomoneda, cantidad);
            actualizarAreaCartera();
        }
    }

    private void actualizarAreaCartera() {
        StringBuilder textoCartera = new StringBuilder();
        for (Map.Entry<String, Double> entrada : Cartera.obtenerCartera().entrySet()) {
            textoCartera.append(entrada.getKey()).append(": ").append(entrada.getValue()).append("\n");
        }
        areaCartera.setText(textoCartera.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionIntercambioCriptomonedas().setVisible(true);
            }
        });
    }
}
