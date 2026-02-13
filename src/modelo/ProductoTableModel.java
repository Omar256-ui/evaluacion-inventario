/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Omar
 */
package modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

public class ProductoTableModel extends AbstractTableModel implements Observer {

    private List<Producto> productos;
    private InventarioObservable observable;

    private final String[] columnNames = {
        "ID", "Código", "Nombre", "Descripción", "Precio",
        "Cantidad", "Categoría", "Código Barras", "Estado"
    };

    public ProductoTableModel(InventarioObservable observable) {
        this.observable = observable;
        this.productos = observable.obtenerProductos();

        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }

        observable.addObserver(this);
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return productos == null ? 0 : productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 4: return Double.class;
            case 5: return Integer.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Producto p = productos.get(rowIndex);

        switch (columnIndex) {
            case 0: return p.getId();
            case 1: return p.getCodigo();
            case 2: return p.getNombre();
            case 3: return p.getDescripcion();
            case 4: return p.getPrecio();
            case 5: return p.getCantidad();
            case 6: return p.getCategoria();
            case 7: return p.getCodigo();
            case 8: return p.isActivo() ? "Activo" : "Inactivo";
            default: return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        productos = observable.obtenerProductos();
        if (productos == null) {
            productos = new ArrayList<>();
        }
        fireTableDataChanged();
    }
}