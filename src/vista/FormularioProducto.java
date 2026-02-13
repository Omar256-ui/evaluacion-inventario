/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

/**
 *
 * @author Omar
 */
package vista;

import modelo.Producto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormularioProducto extends JPanel {

    private JTextField txtCodigo, txtNombre, txtDescripcion, txtPrecio, txtCantidad, txtCategoria;
    private JCheckBox chkActivo;
    private JButton btnGuardar, btnActualizar, btnLimpiar, btnGenerarCodigo;
    private BarcodePanel barcodePanel;
    private JLabel lblId;

    public FormularioProducto() {
        initComponents();
        configurarLayout();
    }

    private void initComponents() {
        // Inicializar componentes
        lblId = new JLabel("Nuevo Producto");
        txtCodigo = new JTextField(20);
        txtNombre = new JTextField(20);
        txtDescripcion = new JTextField(20);
        txtPrecio = new JTextField(10);
        txtCantidad = new JTextField(10);
        txtCategoria = new JTextField(15);
        chkActivo = new JCheckBox("Activo", true);

        btnGuardar = new JButton("Guardar Nuevo");
        btnActualizar = new JButton("Actualizar");
        btnLimpiar = new JButton("Limpiar");
        btnGenerarCodigo = new JButton("Generar Código");

        barcodePanel = new BarcodePanel();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de entrada de datos
        JPanel panelDatos = new JPanel(new GridBagLayout());
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelDatos.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panelDatos.add(lblId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDatos.add(new JLabel("Código*:"), gbc);
        gbc.gridx = 1;
        JPanel panelCodigo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelCodigo.add(txtCodigo);
        panelCodigo.add(btnGenerarCodigo);
        panelDatos.add(panelCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDatos.add(new JLabel("Nombre*:"), gbc);
        gbc.gridx = 1;
        panelDatos.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelDatos.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        panelDatos.add(txtDescripcion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelDatos.add(new JLabel("Precio*:"), gbc);
        gbc.gridx = 1;
        panelDatos.add(txtPrecio, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panelDatos.add(new JLabel("Cantidad*:"), gbc);
        gbc.gridx = 3;
        panelDatos.add(txtCantidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelDatos.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        panelDatos.add(txtCategoria, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        panelDatos.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 3;
        panelDatos.add(chkActivo, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);

        add(panelDatos, BorderLayout.NORTH);
        add(barcodePanel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // Métodos para obtener/establecer datos
    public Producto getProducto() {
        Producto producto = new Producto();
        producto.setCodigo(txtCodigo.getText().trim());
        producto.setNombre(txtNombre.getText().trim());
        producto.setDescripcion(txtDescripcion.getText().trim());
        producto.setPrecio(new java.math.BigDecimal(txtPrecio.getText().trim()));
        producto.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
        producto.setCategoria(txtCategoria.getText().trim());
        producto.setActivo(chkActivo.isSelected());
        return producto;
    }

    public void setProducto(Producto producto) {
        if (producto.getId() > 0) {
            lblId.setText(String.valueOf(producto.getId()));
        } else {
            lblId.setText("Nuevo Producto");
        }
        txtCodigo.setText(producto.getCodigo());
        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(producto.getPrecio().toString());
        txtCantidad.setText(String.valueOf(producto.getCantidad()));
        txtCategoria.setText(producto.getCategoria());
        chkActivo.setSelected(producto.isActivo());

        // Generar código de barras
        if (producto.getCodigo() != null && !producto.getCodigo().isEmpty()) {
            barcodePanel.generarBarcode(producto.getCodigo());
        }
    }

    public void limpiarCampos() {
        lblId.setText("Nuevo Producto");
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtCategoria.setText("");
        chkActivo.setSelected(true);
        barcodePanel.limpiar();
    }

    // Métodos para agregar listeners
    public void agregarGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void agregarActualizarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
    }

    public void agregarLimpiarListener(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }

    public void agregarGenerarCodigoListener(ActionListener listener) {
        btnGenerarCodigo.addActionListener(listener);
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setCodigo(String nuevoCodigo) {
        txtCodigo.setText(nuevoCodigo);
    }

    public void generarCodigoBarras(String nuevoCodigo) {
        barcodePanel.generarBarcode(nuevoCodigo);
    }
}
