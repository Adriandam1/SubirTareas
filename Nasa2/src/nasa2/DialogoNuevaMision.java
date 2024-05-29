/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nasa2;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adrian
 */

public class DialogoNuevaMision extends JDialog {
    private JTextField campoNombre;
    private JTextField campoFechaLanzamiento;
    private JTextArea areaObjetivos;
    private JTextField campoNaveEspacial;
    private JTextArea areaMiembrosTripulacion;
    private JButton botonGuardar;

    public DialogoNuevaMision(JFrame parent) {
        super(parent, "Nueva Misión", true);
        setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel(new GridLayout(5, 2));
        panelEntrada.add(new JLabel("Nombre de la Misión:"));
        campoNombre = new JTextField();
        panelEntrada.add(campoNombre);

        panelEntrada.add(new JLabel("Fecha de Lanzamiento (YYYY-MM-DD):"));
        campoFechaLanzamiento = new JTextField();
        panelEntrada.add(campoFechaLanzamiento);

        panelEntrada.add(new JLabel("Objetivos:"));
        areaObjetivos = new JTextArea(3, 20);
        panelEntrada.add(new JScrollPane(areaObjetivos));

        panelEntrada.add(new JLabel("Nave Espacial:"));
        campoNaveEspacial = new JTextField();
        panelEntrada.add(campoNaveEspacial);

        panelEntrada.add(new JLabel("Miembros de la Tripulación (separados por comas):"));
        areaMiembrosTripulacion = new JTextArea(3, 20);
        panelEntrada.add(new JScrollPane(areaMiembrosTripulacion));

        botonGuardar = new JButton("Guardar Misión");
        botonGuardar.addActionListener(e -> guardarMision());

        add(panelEntrada, BorderLayout.CENTER);
        add(botonGuardar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void guardarMision() {
        String nombre = campoNombre.getText();
        String fechaLanzamientoString = campoFechaLanzamiento.getText();
        String objetivos = areaObjetivos.getText();
        String naveEspacial = campoNaveEspacial.getText();
        String[] miembrosTripulacionArray = areaMiembrosTripulacion.getText().split(",");

        try {
            Date fechaLanzamiento = new Date(fechaLanzamientoString); // Formato simplificado para el ejemplo
            ArrayList<String> miembrosTripulacion = new ArrayList<>();
            for (String miembro : miembrosTripulacionArray) {
                miembrosTripulacion.add(miembro.trim());
            }
            Mision mision = new Mision(nombre, fechaLanzamiento, objetivos, naveEspacial, miembrosTripulacion);
            GestorMisiones.guardarMision(mision);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la misión: " + e.getMessage());
        }
    }
}
