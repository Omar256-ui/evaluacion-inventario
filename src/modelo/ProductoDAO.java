/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omar
 */
package modelo;


import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProductoDAO {

    public List<Producto> obtenerTodos() {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = mapearProducto(rs);
                if (p != null) {
                    productos.add(p);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return productos;
    }

    public boolean insertar(Producto p) {

        String sql = """
            INSERT INTO productos
            (codigo, nombre, descripcion, precio, cantidad, categoria, activo)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setBigDecimal(4, p.getPrecio());
            ps.setInt(5, p.getCantidad());
            ps.setString(6, p.getCategoria());
            ps.setBoolean(7, p.isActivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Producto p) {

        String sql = """
            UPDATE productos SET
            codigo = ?, nombre = ?, descripcion = ?, precio = ?,
            cantidad = ?, categoria = ?, activo = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setBigDecimal(4, p.getPrecio());
            ps.setInt(5, p.getCantidad());
            ps.setString(6, p.getCategoria());
            ps.setBoolean(7, p.isActivo());
            ps.setInt(8, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {

        Producto p = new Producto();

        p.setId(rs.getInt("id"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setCategoria(rs.getString("categoria"));
        p.setActivo(rs.getBoolean("activo"));

        return p;
    }
}