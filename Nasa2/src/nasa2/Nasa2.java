/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nasa2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian
 */

public class Nasa2 {
    private JFrame marco;
    private JMenuBar barraMenu;
    private JPanel panelPrincipal;
    private JTextArea areaReporteMision;

    public Nasa2() {
        marco = new JFrame("Simulador de Misiones Espaciales para la NASA");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(800, 600);

        barraMenu = new JMenuBar();
        JMenu menuMisiones = new JMenu("Misiones");
        JMenuItem itemNuevaMision = new JMenuItem("Nueva Misión");
        JMenuItem itemVerMisiones = new JMenuItem("Ver Misiones");

        itemNuevaMision.addActionListener(e -> mostrarDialogoNuevaMision());
        itemVerMisiones.addActionListener(e -> mostrarReporteMision());

        menuMisiones.add(itemNuevaMision);
        menuMisiones.add(itemVerMisiones);
        barraMenu.add(menuMisiones);

        marco.setJMenuBar(barraMenu);

        panelPrincipal = new JPanel(new BorderLayout());
        areaReporteMision = new JTextArea();
        areaReporteMision.setEditable(false);

        panelPrincipal.add(new JScrollPane(areaReporteMision), BorderLayout.CENTER);
        marco.add(panelPrincipal);

        marco.setVisible(true);
    }

    private void mostrarDialogoNuevaMision() {
        DialogoNuevaMision dialogo = new DialogoNuevaMision(marco);
        dialogo.setVisible(true);
    }

    private void mostrarReporteMision() {
        try {
            List<Mision> misiones = GestorMisiones.cargarMisiones();
            StringBuilder reporte = new StringBuilder();
            for (Mision mision : misiones) {
                reporte.append("Nombre: ").append(mision.getNombre()).append("\n")
                        .append("Fecha de Lanzamiento: ").append(mision.getFechaLanzamiento()).append("\n")
                        .append("Objetivos: ").append(mision.getObjetivos()).append("\n")
                        .append("Nave Espacial: ").append(mision.getNaveEspacial()).append("\n")
                        .append("Tripulación: ").append(String.join(", ", mision.getMiembrosTripulacion())).append("\n\n");
            }
            areaReporteMision.setText(reporte.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(marco, "Error al cargar las misiones: " + e.getMessage());
        }
    }

    private void mostrarTrayectoria() {
        JFrame marcoTrayectoria = new JFrame("Trayectoria de la Misión");
        marcoTrayectoria.setSize(400, 400);
        marcoTrayectoria.add(new PanelTrayectoria());
        marcoTrayectoria.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Nasa2::new);
    }
}
