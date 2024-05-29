/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nasa2;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Adrian
 */

public class PanelTrayectoria extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(50, 50, 200, 200); // Ejemplo de línea representando la trayectoria
    }
}
