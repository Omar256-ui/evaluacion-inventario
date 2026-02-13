/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omar
 */


package vista;
import modelo.ProductoTableModel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class PanelTabla extends JPanel {
    private JTable tablaProductos;
    private ProductoTableModel tableModel;
    private JLabel lblContador;
    
    public PanelTabla(ProductoTableModel model) {
        this.tableModel = model;
        initComponents();
        configurarLayout();
    }
    
    private void initComponents() {
        tablaProductos = new JTable(tableModel);
        lblContador = new JLabel("Total productos: 0");
        
        // Configurar tabla
        tablaProductos.setRowHeight(30);
        tablaProductos.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Configurar renderizador para código de barras
        TableColumn barcodeColumn = tablaProductos.getColumnModel().getColumn(7);
        barcodeColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);
                
                // Simular código de barras (en evaluación real usar JBarcode)
                if (value != null) {
                    label.setText("| " + value.toString() + " |");
                    label.setFont(new Font("Monospaced", Font.PLAIN, 10));
                }
                
                return label;
            }
        });
    }
    
    private void configurarLayout() {
        setLayout(new BorderLayout());
        
        // Panel superior con contador
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Lista de Productos"));
        panelSuperior.add(Box.createHorizontalStrut(20));
        panelSuperior.add(lblContador);
        
        // Panel de tabla con scroll
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public JTable getTabla() {
        return tablaProductos;
    }
    
    public void actualizarContador() {
        int total = tableModel.getRowCount();
        lblContador.setText("Total productos: " + total);
    }
}