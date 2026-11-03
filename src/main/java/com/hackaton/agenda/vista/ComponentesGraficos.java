package com.hackaton.agenda.vista;

import com.hackaton.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/*
 * Fábrica de componentes gráficos y panel de gestión de contactos.
 */
public class ComponentesGraficos {

    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FUENTE_GENERAL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_NEGRILLA = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_TABLA = new Font("Segoe UI", Font.PLAIN, 13);

    public static final Color COLOR_PRIMARIO = new Color(26, 115, 232);
    public static final Color COLOR_PELIGRO = new Color(217, 48, 37);
    public static final Color COLOR_EXITO = new Color(30, 142, 62);
    public static final Color COLOR_FONDO_TABLA_HEAD = new Color(241, 243, 244);

    /*
     * Métodos para fabricar componentes con estilo estándar
     */
    public static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_NEGRILLA);
        return etiqueta;
    }

    public static JTextField campoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_GENERAL);
        return campo;
    }

    public static JTextField campoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setFont(FUENTE_GENERAL);
        return campo;
    }

    public static JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_NEGRILLA);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    /*
     * Panel de gestión con tabla, búsqueda, eliminación y cupo disponible
     */
    public static class PanelGestion extends JPanel {

        private JTable tablaContactos;
        private DefaultTableModel modeloTabla;
        private JScrollPane scrollTabla;

        private JTextField txtBuscarNombre;
        private JTextField txtBuscarApellido;
        private JButton btnBuscar;
        private JButton btnMostrarTodos;

        private JButton btnEliminar;

        private JLabel lblContador;
        private JLabel lblEspaciosLibres;
        private JProgressBar barraCapacidad;

        public PanelGestion() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createCompoundBorder(
                    new TitledBorder(BorderFactory.createLineBorder(new Color(200, 205, 210), 1, true),
                            " Gestión de Contactos ", TitledBorder.LEFT, TitledBorder.TOP, FUENTE_TITULO, COLOR_PRIMARIO),
                    new EmptyBorder(10, 10, 10, 10)
            ));

            construirPanelBusqueda();
            construirPanelTabla();
            construirPanelAccionesYCapacidad();
        }

        private void construirPanelBusqueda() {
            JPanel panelBusqueda = new JPanel(new GridBagLayout());
            panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(225, 228, 232)),
                    new EmptyBorder(0, 0, 10, 0)
            ));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(4, 4, 4, 4);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            JLabel lblNom = crearEtiqueta("Nombre:");
            panelBusqueda.add(lblNom, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.5;
            txtBuscarNombre = campoTexto(10);
            txtBuscarNombre.setToolTipText("Escribe el nombre a buscar");
            panelBusqueda.add(txtBuscarNombre, gbc);

            gbc.gridx = 2;
            gbc.weightx = 0;
            JLabel lblApe = crearEtiqueta("Apellido:");
            panelBusqueda.add(lblApe, gbc);

            gbc.gridx = 3;
            gbc.weightx = 0.5;
            txtBuscarApellido = campoTexto(10);
            txtBuscarApellido.setToolTipText("Escribe el apellido a buscar");
            panelBusqueda.add(txtBuscarApellido, gbc);

            gbc.gridx = 4;
            gbc.weightx = 0;
            btnBuscar = crearBoton("Buscar");
            btnBuscar.setBackground(COLOR_PRIMARIO);
            btnBuscar.setForeground(Color.WHITE);
            panelBusqueda.add(btnBuscar, gbc);

            gbc.gridx = 5;
            gbc.weightx = 0;
            btnMostrarTodos = crearBoton("Mostrar Todos");
            btnMostrarTodos.setFont(FUENTE_GENERAL);
            panelBusqueda.add(btnMostrarTodos, gbc);

            add(panelBusqueda, BorderLayout.NORTH);
        }

        private void construirPanelTabla() {
            String[] columnas = {"Nombre", "Apellido", "Teléfono"};

            modeloTabla = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tablaContactos = new JTable(modeloTabla);
            tablaContactos.setFont(FUENTE_TABLA);
            tablaContactos.setRowHeight(28);
            tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tablaContactos.setFillsViewportHeight(true);
            tablaContactos.setShowVerticalLines(true);
            tablaContactos.setShowHorizontalLines(true);
            tablaContactos.setGridColor(new Color(230, 230, 230));

            JTableHeader header = tablaContactos.getTableHeader();
            header.setFont(FUENTE_NEGRILLA);
            header.setBackground(COLOR_FONDO_TABLA_HEAD);
            header.setReorderingAllowed(false);
            header.setPreferredSize(new Dimension(header.getWidth(), 30));

            DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
            centroRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaContactos.getColumnModel().getColumn(2).setCellRenderer(centroRenderer);

            scrollTabla = new JScrollPane(tablaContactos);
            scrollTabla.setPreferredSize(new Dimension(480, 260));
            scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220)));

            add(scrollTabla, BorderLayout.CENTER);
        }

        private void construirPanelAccionesYCapacidad() {
            JPanel panelInferior = new JPanel(new BorderLayout(8, 8));
            panelInferior.setBorder(new EmptyBorder(8, 0, 0, 0));

            JPanel panelBotonEliminar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            btnEliminar = crearBoton("Eliminar Contacto");
            btnEliminar.setBackground(COLOR_PELIGRO);
            btnEliminar.setForeground(Color.WHITE);
            btnEliminar.setToolTipText("Selecciona una fila en la tabla para borrarla");
            panelBotonEliminar.add(btnEliminar);

            JPanel panelCapacidad = new JPanel(new GridLayout(2, 1, 4, 4));
            panelCapacidad.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 225, 230), 1, true),
                    new EmptyBorder(6, 10, 6, 10)
            ));
            panelCapacidad.setBackground(new Color(248, 249, 250));

            JPanel panelTextos = new JPanel(new BorderLayout());
            panelTextos.setOpaque(false);

            lblContador = new JLabel("Contactos: 0 / 10");
            lblContador.setFont(FUENTE_NEGRILLA);
            lblContador.setForeground(new Color(60, 64, 67));

            lblEspaciosLibres = new JLabel("Espacios libres: 10");
            lblEspaciosLibres.setFont(FUENTE_GENERAL);
            lblEspaciosLibres.setForeground(COLOR_EXITO);

            panelTextos.add(lblContador, BorderLayout.WEST);
            panelTextos.add(lblEspaciosLibres, BorderLayout.EAST);

            barraCapacidad = new JProgressBar(0, 10);
            barraCapacidad.setValue(0);
            barraCapacidad.setStringPainted(true);
            barraCapacidad.setString("0% ocupado");
            barraCapacidad.setFont(new Font("Segoe UI", Font.BOLD, 11));
            barraCapacidad.setForeground(COLOR_PRIMARIO);

            panelCapacidad.add(panelTextos);
            panelCapacidad.add(barraCapacidad);

            panelInferior.add(panelBotonEliminar, BorderLayout.NORTH);
            panelInferior.add(panelCapacidad, BorderLayout.SOUTH);

            add(panelInferior, BorderLayout.SOUTH);
        }

        public void actualizarTabla(List<Contacto> contactos) {
            modeloTabla.setRowCount(0);
            if (contactos != null) {
                for (Contacto c : contactos) {
                    modeloTabla.addRow(new Object[]{
                            c.getNombre(),
                            c.getApellido(),
                            c.getTelefono()
                    });
                }
            }
        }

        public void mostrarContactoEncontrado(String nombre, String apellido, String telefono) {
            modeloTabla.setRowCount(0);
            modeloTabla.addRow(new Object[]{nombre, apellido, telefono});
            if (tablaContactos.getRowCount() > 0) {
                tablaContactos.setRowSelectionInterval(0, 0);
            }
        }

        public void actualizarCapacidad(int ocupados, int capacidadMaxima, int espaciosLibres) {
            lblContador.setText("Contactos: " + ocupados + " / " + capacidadMaxima);
            lblEspaciosLibres.setText("Espacios libres: " + espaciosLibres);

            barraCapacidad.setMaximum(capacidadMaxima);
            barraCapacidad.setValue(ocupados);

            int porcentaje = capacidadMaxima > 0 ? (int) Math.round(((double) ocupados / capacidadMaxima) * 100) : 0;
            barraCapacidad.setString(porcentaje + "% (" + ocupados + "/" + capacidadMaxima + ")");

            if (ocupados >= capacidadMaxima) {
                barraCapacidad.setForeground(COLOR_PELIGRO);
                lblEspaciosLibres.setForeground(COLOR_PELIGRO);
                lblEspaciosLibres.setText("¡Agenda llena!");
            } else if (espaciosLibres <= 2) {
                barraCapacidad.setForeground(new Color(234, 134, 0));
                lblEspaciosLibres.setForeground(new Color(234, 134, 0));
            } else {
                barraCapacidad.setForeground(COLOR_PRIMARIO);
                lblEspaciosLibres.setForeground(COLOR_EXITO);
            }
        }

        public int getFilaSeleccionada() {
            return tablaContactos.getSelectedRow();
        }

        public String[] obtenerContactoDeFila(int fila) {
            if (fila >= 0 && fila < modeloTabla.getRowCount()) {
                return new String[]{
                        String.valueOf(modeloTabla.getValueAt(fila, 0)),
                        String.valueOf(modeloTabla.getValueAt(fila, 1)),
                        String.valueOf(modeloTabla.getValueAt(fila, 2))
                };
            }
            return null;
        }

        public void limpiarCamposBusqueda() {
            txtBuscarNombre.setText("");
            txtBuscarApellido.setText("");
        }

        public void addBuscarListener(ActionListener listener) {
            btnBuscar.addActionListener(listener);
        }

        public void addMostrarTodosListener(ActionListener listener) {
            btnMostrarTodos.addActionListener(listener);
        }

        public void addEliminarListener(ActionListener listener) {
            btnEliminar.addActionListener(listener);
        }

        public void addSeleccionTablaListener(ListSelectionListener listener) {
            tablaContactos.getSelectionModel().addListSelectionListener(listener);
        }

        public JTable getTablaContactos() {
            return tablaContactos;
        }

        public DefaultTableModel getModeloTabla() {
            return modeloTabla;
        }

        public JTextField getTxtBuscarNombre() {
            return txtBuscarNombre;
        }

        public JTextField getTxtBuscarApellido() {
            return txtBuscarApellido;
        }

        public JButton getBtnBuscar() {
            return btnBuscar;
        }

        public JButton getBtnMostrarTodos() {
            return btnMostrarTodos;
        }

        public JButton getBtnEliminar() {
            return btnEliminar;
        }

        public JLabel getLblContador() {
            return lblContador;
        }

        public JLabel getLblEspaciosLibres() {
            return lblEspaciosLibres;
        }

        public JProgressBar getBarraCapacidad() {
            return barraCapacidad;
        }
    }
}
