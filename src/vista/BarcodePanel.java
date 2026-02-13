/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Omar
 */
package vista;


import util.BarcodeGenerator;
import javax.swing.*;
import java.awt.*;

public class BarcodePanel extends JPanel {
    private JLabel lblBarcode;
    private String currentCode;
    
    public BarcodePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Código de Barras"));
        
        lblBarcode = new JLabel("", JLabel.CENTER);
        lblBarcode.setPreferredSize(new Dimension(300, 100));
        add(lblBarcode, BorderLayout.CENTER);
    }
    
    public void generarBarcode(String codigo) {
        this.currentCode = codigo;
        try {
            ImageIcon barcodeImage = BarcodeGenerator.generarCode128(codigo);
            lblBarcode.setIcon(barcodeImage);
            lblBarcode.setText("");
        } catch (Exception e) {
            lblBarcode.setIcon(null);
            lblBarcode.setText("Error: " + codigo);
            JOptionPane.showMessageDialog(this, 
                "Error generando código de barras: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar() {
        lblBarcode.setIcon(null);
        lblBarcode.setText("");
        currentCode = null;
    }
}