package com.hackaton.agenda.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void setUp() {
        agenda = new Agenda(3);
    }

    @Test
    @DisplayName("Añadir contactos y verificar cantidad y espacios libres")
    void testAñadirContacto() {
        assertEquals(3, agenda.espaciosLibres());
        assertFalse(agenda.agendaLlena());

        Contacto c1 = new Contacto("María", "Alonso", "+57 300 111 2222");
        agenda.añadirContacto(c1);

        assertEquals(1, agenda.getCantidadContactos());
        assertEquals(2, agenda.espaciosLibres());
        assertTrue(agenda.existeContacto(c1));
    }

    @Test
    @DisplayName("No permitir duplicados bajo ninguna circunstancia")
    void testNoPermitirDuplicados() {
        Contacto c1 = new Contacto("Juan", "Pérez", "3001112222");
        agenda.añadirContacto(c1);

        Contacto duplicado = new Contacto("juan", "PÉREZ", "3109999999");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> agenda.añadirContacto(duplicado));
        assertTrue(ex.getMessage().toLowerCase().contains("ya existe"));
    }

    @Test
    @DisplayName("Control de capacidad máxima (agendaLlena)")
    void testCapacidadMaxima() {
        agenda.añadirContacto(new Contacto("A", "A", "3001111111"));
        agenda.añadirContacto(new Contacto("B", "B", "3002222222"));
        agenda.añadirContacto(new Contacto("C", "C", "3003333333"));

        assertTrue(agenda.agendaLlena());
        assertEquals(0, agenda.espaciosLibres());

        Contacto extra = new Contacto("D", "D", "3004444444");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> agenda.añadirContacto(extra));
        assertTrue(ex.getMessage().toLowerCase().contains("llena"));
    }

    @Test
    @DisplayName("Listar contactos ordenados alfabéticamente por apellido y nombre")
    void testListarContactosOrdenados() {
        agenda.añadirContacto(new Contacto("Carlos", "Zapata", "3001111111"));
        agenda.añadirContacto(new Contacto("Beatriz", "Alonso", "3002222222"));
        agenda.añadirContacto(new Contacto("Alberto", "Alonso", "3003333333"));

        List<Contacto> ordenados = agenda.listarContactos();
        assertEquals(3, ordenados.size());
        assertEquals("Alonso", ordenados.get(0).getApellido());
        assertEquals("Alberto", ordenados.get(0).getNombre());

        assertEquals("Alonso", ordenados.get(1).getApellido());
        assertEquals("Beatriz", ordenados.get(1).getNombre());

        assertEquals("Zapata", ordenados.get(2).getApellido());
    }

    @Test
    @DisplayName("Buscar contacto por nombre y apellido devuelve el teléfono correcto")
    void testBuscarContacto() {
        agenda.añadirContacto(new Contacto("Laura", "Méndez", "+57 315 654 3210"));

        String tel = agenda.buscarContacto("laura", "MÉNDEZ");
        assertNotNull(tel);
        assertEquals("+57 315 654 3210", tel);

        assertThrows(IllegalArgumentException.class, () -> agenda.buscarContacto("No", "Existe"));
    }

    @Test
    @DisplayName("Eliminar contacto existente")
    void testEliminarContacto() {
        Contacto c = new Contacto("Pedro", "García", "3005556666");
        agenda.añadirContacto(c);
        assertEquals(1, agenda.getCantidadContactos());

        agenda.eliminarContacto(c);
        assertEquals(0, agenda.getCantidadContactos());
        assertEquals(3, agenda.espaciosLibres());
    }

    @Test
    @DisplayName("Modificar teléfono de un contacto")
    void testModificarTelefono() {
        agenda.añadirContacto(new Contacto("Elena", "Ramos", "3001112222"));
        agenda.modificarTelefono("Elena", "Ramos", "+57 320 999 8877");

        assertEquals("+57 320 999 8877", agenda.buscarContacto("Elena", "Ramos"));
    }
}
