/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Omar
 */
package util;

    

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// Esta es una implementación genérica
public class BarcodeGenerator {
    
    public static ImageIcon generarCode128(String codigo) {
        try {
          
            return generarBarcodeSimulado(codigo);
        } catch (Exception e) {
            throw new RuntimeException("Error generando código de barras", e);
        }
    }
    
    private static ImageIcon generarBarcodeSimulado(String codigo) {
       
        JLabel label = new JLabel("[" + codigo + "]");
        label.setFont(new Font("Monospaced", Font.BOLD, 20));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        
       
        java.awt.Image img = new BufferedImage(300, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        label.setSize(300, 100);
        label.paint(g2d);
        
        return new ImageIcon(img);
    }
}