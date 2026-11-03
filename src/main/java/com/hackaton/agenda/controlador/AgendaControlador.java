package com.hackaton.agenda.controlador;

import com.hackaton.agenda.modelo.Agenda;
import com.hackaton.agenda.modelo.Contacto;
import com.hackaton.agenda.vista.VentanaPrincipal;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AgendaControlador {
    private final Agenda agenda;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
        registrarEventos();
        refrescarTabla();
        vista.mostrarMensajeExito("Agenda lista. Espacios libres: " + agenda.espaciosLibres());
    }

    // ----------------- Registro de listeners -----------------

    private void registrarEventos() {
        vista.getBtnAgregar().addActionListener(e -> ejecutar(this::agregar));
        vista.getBtnBuscar().addActionListener(e -> ejecutar(this::buscar));
        vista.getBtnModificar().addActionListener(e -> ejecutar(this::modificar));
        vista.getBtnEliminar().addActionListener(e -> ejecutar(this::eliminar));
        vista.getBtnLimpiar().addActionListener(e -> {
            vista.limpiarFormulario();
            vista.getTabla().clearSelection();
            vista.mostrarMensajeExito("Formulario limpio");
        });

        // Sincronización: al seleccionar una fila, se rellenan los campos
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarFilaSeleccionada();
            }
        });
    }

    // ----------------- Acciones de cada botón -----------------

    private void agregar() {
        // El constructor de Contacto valida campos vacíos y el teléfono (Regex)
        Contacto nuevo = new Contacto(vista.getNombre(), vista.getApellido(), vista.getTelefono());
        agenda.añadirContacto(nuevo);   // valida agenda llena y duplicados
        refrescarTabla();
        vista.limpiarFormulario();
        vista.mostrarMensajeExito("Contacto agregado. Espacios libres: " + agenda.espaciosLibres());
    }

    private void buscar() {
        String telefono = agenda.buscarContacto(vista.getNombre(), vista.getApellido());
        vista.setTelefono(telefono);
        vista.mostrarMensajeExito("Teléfono encontrado: " + telefono);
    }

    private void modificar() {
        agenda.modificarTelefono(vista.getNombre(), vista.getApellido(), vista.getTelefono());
        refrescarTabla();
        vista.mostrarMensajeExito("Teléfono actualizado");
    }

    private void eliminar() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        // Se obtiene el teléfono real para construir un Contacto válido
        String telefono = agenda.buscarContacto(nombre, apellido);

        int respuesta = JOptionPane.showConfirmDialog(vista,
                "¿Eliminar a " + nombre.trim() + " " + apellido.trim() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta != JOptionPane.YES_OPTION) {
            vista.mostrarMensajeError("Eliminación cancelada");
            return;
        }

        agenda.eliminarContacto(new Contacto(nombre, apellido, telefono));
        refrescarTabla();
        vista.limpiarFormulario();
        vista.mostrarMensajeExito("Contacto eliminado. Espacios libres: " + agenda.espaciosLibres());
    }

    // ----------------- Apoyo -----------------

    /**
     * Ejecuta una acción y convierte cualquier error en un mensaje rojo,
     * para que nunca aparezcan excepciones en la consola.
     */
    private void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException ex) {
            vista.mostrarMensajeError(ex.getMessage());
        } catch (RuntimeException ex) {
            vista.mostrarMensajeError("Ocurrió un error inesperado: " + ex.getMessage());
        }
    }

    /** Vacía la tabla y la vuelve a llenar con la lista ordenada del modelo. */
    private void refrescarTabla() {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);
        for (Contacto c : agenda.listarContactos()) {
            modelo.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono()});
        }
    }

    /** Copia la fila seleccionada de la tabla a los campos del formulario. */
    private void cargarFilaSeleccionada() {
        JTable tabla = vista.getTabla();
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return;
        }
        int filaModelo = tabla.convertRowIndexToModel(fila);
        DefaultTableModel modelo = vista.getModeloTabla();
        vista.setNombre(String.valueOf(modelo.getValueAt(filaModelo, 0)));
        vista.setApellido(String.valueOf(modelo.getValueAt(filaModelo, 1)));
        vista.setTelefono(String.valueOf(modelo.getValueAt(filaModelo, 2)));
    }
}
