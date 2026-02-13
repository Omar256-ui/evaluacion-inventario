/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omar
 */
import controlador.InventarioController;
import javax.swing.SwingUtilities;

import modelo.InventarioObservable;
import modelo.ProductoTableModel;

import vista.FormularioProducto;
import vista.PanelTabla;
import vista.MainFrame;


public class MainApp {

    public static void main(String[] args) {

        
        SwingUtilities.invokeLater(() -> {

            try {
               
                InventarioObservable inventario = new InventarioObservable();

              
                ProductoTableModel tableModel = new ProductoTableModel(inventario);

              
                FormularioProducto formulario = new FormularioProducto();
                PanelTabla panelTabla = new PanelTabla(tableModel);

            
                MainFrame frame = new MainFrame(formulario, panelTabla);

              
                new InventarioController(inventario, formulario, panelTabla);

                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}