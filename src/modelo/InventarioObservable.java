/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omar
 */
package modelo;

import java.util.List;
import java.util.Observable;

public class InventarioObservable extends Observable {

    private ProductoDAO productoDAO;

    public InventarioObservable() {
        this.productoDAO = new ProductoDAO();
    }

    public void agregarProducto(Producto producto) {
        if (productoDAO.insertar(producto)) {
            setChanged();
            notifyObservers("AGREGAR");
        }
    }

    public void actualizarProducto(Producto producto) {
        if (productoDAO.actualizar(producto)) {
            setChanged();
            notifyObservers("ACTUALIZAR");
        }
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.obtenerTodos();
    }

    //  MÉTODO QUE FALTABA
    public void notificarCargaInicial() {
        setChanged();
        notifyObservers("CARGA_INICIAL");
    }
}