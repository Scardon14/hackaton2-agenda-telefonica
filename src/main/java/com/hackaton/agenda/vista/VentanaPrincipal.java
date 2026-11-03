package com.hackaton.agenda.vista;

import com.hackaton.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/*
 * Ventana principal que une el espacio del formulario de registro
 * con la tabla de gestión y la barra inferior de estado.
 */
public class VentanaPrincipal extends JFrame {

    /* Formulario de integrante 3 */
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellido = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JButton btnAgregar = new JButton("Añadir Contacto");
    private JButton btnModificar = new JButton("Modificar Teléfono");
    private JButton btnLimpiar = new JButton("Limpiar Formulario");

    // Componentes de Gestión (Integrante 4)
    private ComponentesGraficos.PanelGestion panelGestion;
    private JLabel lblMensajeEstado;

    public VentanaPrincipal() {
        super("Agenda Telefónica MVC");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 560);
        setMinimumSize(new Dimension(880, 500));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        JPanel panelEncabezado = new JPanel(new BorderLayout());
        panelEncabezado.setBackground(ComponentesGraficos.COLOR_PRIMARIO);
        panelEncabezado.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel lblTitulo = new JLabel("AGENDA TELEFÓNICA MVC");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Patrón Modelo-Vista-Controlador");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(230, 240, 255));

        panelEncabezado.add(lblTitulo, BorderLayout.WEST);
        panelEncabezado.add(lblSubtitulo, BorderLayout.EAST);
        add(panelEncabezado, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 12, 0));
        panelCentral.setBorder(new EmptyBorder(10, 12, 5, 12));

        // Formulario de integrante 3
        JPanel panelRegistro = construirPanelRegistro();
        panelCentral.add(panelRegistro);

        // Panel de Gestión (Integrante 4)
        panelGestion = new ComponentesGraficos.PanelGestion();
        panelCentral.add(panelGestion);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelEstado = construirPanelEstado();
        add(panelEstado, BorderLayout.SOUTH);
    }

    /* Formulario de integrante 3 */
    private JPanel construirPanelRegistro() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                new TitledBorder(BorderFactory.createLineBorder(new Color(200, 205, 210), 1, true),
                        " Formulario de integrante 3 ", TitledBorder.LEFT, TitledBorder.TOP,
                        ComponentesGraficos.FUENTE_TITULO, ComponentesGraficos.COLOR_PRIMARIO),
                new EmptyBorder(30, 20, 30, 20)
        ));

        JLabel lblPlaceholder = new JLabel("Espacio reservado para el Formulario de Registro (Integrante 3)", SwingConstants.CENTER);
        lblPlaceholder.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        lblPlaceholder.setForeground(new Color(120, 125, 130));

        panel.add(lblPlaceholder, BorderLayout.CENTER);
        return panel;
    }

    private JPanel construirPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(210, 215, 220)),
                new EmptyBorder(6, 16, 6, 16)
        ));
        panel.setBackground(new Color(245, 247, 250));

        lblMensajeEstado = new JLabel("Sistema listo para operar.");
        lblMensajeEstado.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        lblMensajeEstado.setForeground(new Color(90, 95, 100));

        panel.add(lblMensajeEstado, BorderLayout.WEST);
        return panel;
    }

    public void mostrarMensajeExito(String mensaje) {
        lblMensajeEstado.setText(mensaje);
        lblMensajeEstado.setForeground(ComponentesGraficos.COLOR_EXITO);
    }

    public void mostrarMensajeError(String mensaje) {
        lblMensajeEstado.setText(mensaje);
        lblMensajeEstado.setForeground(ComponentesGraficos.COLOR_PELIGRO);
    }

    public void cargarContactoEnFormulario(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }

    // Métodos para leer campos de texto (acordados con el equipo)
    public String getNombre() {
        return txtNombre.getText();
    }

    public String getApellido() {
        return txtApellido.getText();
    }

    public String getTelefono() {
        return txtTelefono.getText();
    }

    // Métodos para rellenar campos de texto (acordados con el equipo)
    public void setNombre(String nombre) {
        txtNombre.setText(nombre != null ? nombre : "");
    }

    public void setApellido(String apellido) {
        txtApellido.setText(apellido != null ? apellido : "");
    }

    public void setTelefono(String telefono) {
        txtTelefono.setText(telefono != null ? telefono : "");
    }

    // Botones del formulario y de la gestión (acordados con el equipo)
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return panelGestion.getBtnBuscar();
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return panelGestion.getBtnEliminar();
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnMostrarTodos() {
        return panelGestion.getBtnMostrarTodos();
    }

    // Tabla y modelo (acordados con el equipo)
    public JTable getTabla() {
        return panelGestion.getTablaContactos();
    }

    public DefaultTableModel getModeloTabla() {
        return panelGestion.getModeloTabla();
    }

    // Delegación hacia el panel de gestión (Integrante 4)
    public void actualizarTabla(List<Contacto> contactos) {
        panelGestion.actualizarTabla(contactos);
    }

    public void mostrarContactoEncontrado(String nombre, String apellido, String telefono) {
        panelGestion.mostrarContactoEncontrado(nombre, apellido, telefono);
    }

    public void actualizarCapacidad(int ocupados, int maximo, int disponibles) {
        panelGestion.actualizarCapacidad(ocupados, maximo, disponibles);
    }

    public int getFilaSeleccionadaTabla() {
        return panelGestion.getFilaSeleccionada();
    }

    public String[] obtenerContactoDeFila(int fila) {
        return panelGestion.obtenerContactoDeFila(fila);
    }

    public void addAgregarListener(ActionListener l) {
        btnAgregar.addActionListener(l);
    }

    public void addModificarListener(ActionListener l) {
        btnModificar.addActionListener(l);
    }

    public void addLimpiarListener(ActionListener l) {
        btnLimpiar.addActionListener(l);
    }

    public void addBuscarListener(ActionListener l) {
        panelGestion.addBuscarListener(l);
    }

    public void addMostrarTodosListener(ActionListener l) {
        panelGestion.addMostrarTodosListener(l);
    }

    public void addEliminarListener(ActionListener l) {
        panelGestion.addEliminarListener(l);
    }

    public void addSeleccionTablaListener(ListSelectionListener l) {
        panelGestion.addSeleccionTablaListener(l);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public ComponentesGraficos.PanelGestion getPanelGestion() { return panelGestion; }
    public JLabel getLblMensajeEstado() { return lblMensajeEstado; }
}
