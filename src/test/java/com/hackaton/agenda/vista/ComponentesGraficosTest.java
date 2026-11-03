package com.hackaton.agenda.vista;

import com.hackaton.agenda.modelo.Contacto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Pruebas para verificar que la tabla y los controles de gestión funcionen bien.
 */
class ComponentesGraficosTest {

    @BeforeAll
    static void initHeadless() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    @DisplayName("Crea la tabla y los botones correctamente")
    void testInicializacionPanelGestion() {
        ComponentesGraficos.PanelGestion panel = new ComponentesGraficos.PanelGestion();

        assertNotNull(panel.getTablaContactos());
        assertNotNull(panel.getModeloTabla());
        assertNotNull(panel.getBtnBuscar());
        assertNotNull(panel.getBtnEliminar());
        assertNotNull(panel.getBtnMostrarTodos());
        assertNotNull(panel.getBarraCapacidad());
        assertNotNull(panel.getLblContador());

        assertEquals(3, panel.getModeloTabla().getColumnCount());
        assertEquals("Nombre", panel.getModeloTabla().getColumnName(0));
        assertEquals("Apellido", panel.getModeloTabla().getColumnName(1));
        assertEquals("Teléfono", panel.getModeloTabla().getColumnName(2));

        assertFalse(panel.getModeloTabla().isCellEditable(0, 0));
    }

    @Test
    @DisplayName("Llena la tabla con los contactos del listado")
    void testActualizarTabla() {
        ComponentesGraficos.PanelGestion panel = new ComponentesGraficos.PanelGestion();

        List<Contacto> lista = Arrays.asList(
                new Contacto("Ana", "Alonso", "+57 300 111 2222"),
                new Contacto("Carlos", "Gómez", "3103334444")
        );

        panel.actualizarTabla(lista);
        assertEquals(2, panel.getModeloTabla().getRowCount());

        String[] contactoUno = panel.obtenerContactoDeFila(0);
        assertEquals("Ana", contactoUno[0]);
        assertEquals("Alonso", contactoUno[1]);
        assertEquals("+57 300 111 2222", contactoUno[2]);

        String[] contactoDos = panel.obtenerContactoDeFila(1);
        assertEquals("Carlos", contactoDos[0]);
        assertEquals("Gómez", contactoDos[1]);
        assertEquals("3103334444", contactoDos[2]);
    }

    @Test
    @DisplayName("Actualiza las etiquetas y la barra de cupo")
    void testActualizarCapacidad() {
        ComponentesGraficos.PanelGestion panel = new ComponentesGraficos.PanelGestion();
        panel.actualizarCapacidad(7, 10, 3);

        assertEquals("Contactos: 7 / 10", panel.getLblContador().getText());
        assertEquals("Espacios libres: 3", panel.getLblEspaciosLibres().getText());
        assertEquals(7, panel.getBarraCapacidad().getValue());
        assertEquals(10, panel.getBarraCapacidad().getMaximum());
    }
}
