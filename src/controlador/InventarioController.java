/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author Omar
 */

import modelo.*;
import vista.*;

import javax.swing.*;
import java.math.BigDecimal;

public class InventarioController {

    private InventarioObservable modelo;
    private FormularioProducto vistaFormulario;
    private PanelTabla vistaTabla;
    private ProductoTableModel tableModel;

    public InventarioController(InventarioObservable modelo,
                                FormularioProducto vistaFormulario,
                                PanelTabla vistaTabla) {

        this.modelo = modelo;
        this.vistaFormulario = vistaFormulario;
        this.vistaTabla = vistaTabla;
        this.tableModel = (ProductoTableModel) vistaTabla.getTabla().getModel();

        initControllers();
        cargarDatosIniciales();
    }

    private void initControllers() {

        vistaFormulario.agregarGuardarListener(e -> guardarProducto());
        vistaFormulario.agregarActualizarListener(e -> actualizarProducto());
        vistaFormulario.agregarLimpiarListener(e -> limpiarFormulario());
        vistaFormulario.agregarGenerarCodigoListener(e -> generarCodigo());

        vistaTabla.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarProductoSeleccionado();
            }
        });
    }

    
    private void cargarDatosIniciales() {
        modelo.notificarCargaInicial(); 
        vistaTabla.actualizarContador();
    }

    private void guardarProducto() {
        try {
            Producto nuevo = vistaFormulario.getProducto();

            if (!validarProducto(nuevo)) return;

            modelo.agregarProducto(nuevo);

            JOptionPane.showMessageDialog(null,
                    "Producto guardado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();
            vistaTabla.actualizarContador();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProducto() {
        int selectedRow = vistaTabla.getTabla().getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione un producto para actualizar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Producto actualizado = vistaFormulario.getProducto();
            actualizado.setId((int) tableModel.getValueAt(selectedRow, 0));

            if (!validarProducto(actualizado)) return;

            modelo.actualizarProducto(actualizado);

            JOptionPane.showMessageDialog(null,
                    "Producto actualizado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            vistaTabla.actualizarContador();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarProducto(Producto producto) {

        if (producto.getCodigo() == null || producto.getCodigo().isEmpty()) return false;
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) return false;
        if (producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) return false;
        if (producto.getCantidad() < 0) return false;

        return true;
    }

   private void cargarProductoSeleccionado() {

    int row = vistaTabla.getTabla().getSelectedRow();
    if (row < 0) return;

    Producto p = new Producto();

    p.setId((int) tableModel.getValueAt(row, 0));
    p.setCodigo((String) tableModel.getValueAt(row, 1));
    p.setNombre((String) tableModel.getValueAt(row, 2));
    p.setDescripcion((String) tableModel.getValueAt(row, 3));

    Object precioObj = tableModel.getValueAt(row, 4);
    if (precioObj instanceof BigDecimal) {
        p.setPrecio((BigDecimal) precioObj);
    } else if (precioObj instanceof Double) {
        p.setPrecio(BigDecimal.valueOf((Double) precioObj));
    } else if (precioObj != null) {
        p.setPrecio(new BigDecimal(precioObj.toString()));
    }

    p.setCantidad((int) tableModel.getValueAt(row, 5));
    p.setCategoria((String) tableModel.getValueAt(row, 6));
    p.setActivo("Activo".equals(tableModel.getValueAt(row, 8)));

    vistaFormulario.setProducto(p);
}

    private void limpiarFormulario() {
        vistaFormulario.limpiarCampos();
        vistaTabla.getTabla().clearSelection();
    }

    private void generarCodigo() {
        String codigo = "PROD-" + String.format("%03d", (int)(Math.random() * 1000));
        vistaFormulario.setCodigo(codigo);
        vistaFormulario.generarCodigoBarras(codigo);
    }
}