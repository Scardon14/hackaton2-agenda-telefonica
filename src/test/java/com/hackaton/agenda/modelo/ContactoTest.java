package com.hackaton.agenda.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactoTest {

    @Test
    @DisplayName("Crear contacto válido correctamente")
    void testCrearContactoValido() {
        Contacto c = new Contacto("Carlos", "Gómez", "+57 300 123 4567");
        assertEquals("Carlos", c.getNombre());
        assertEquals("Gómez", c.getApellido());
        assertEquals("+57 300 123 4567", c.getTelefono());
    }

    @Test
    @DisplayName("No permitir nombres vacíos o con sólo espacios")
    void testNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Contacto("", "Pérez", "3001234567"));
        assertThrows(IllegalArgumentException.class, () -> new Contacto("   ", "Pérez", "3001234567"));
        assertThrows(IllegalArgumentException.class, () -> new Contacto(null, "Pérez", "3001234567"));
    }

    @Test
    @DisplayName("No permitir apellidos vacíos o con sólo espacios")
    void testApellidoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Contacto("Juan", "", "3001234567"));
        assertThrows(IllegalArgumentException.class, () -> new Contacto("Juan", "   ", "3001234567"));
        assertThrows(IllegalArgumentException.class, () -> new Contacto("Juan", null, "3001234567"));
    }

    @Test
    @DisplayName("Validación Regex de teléfonos colombianos (+57)")
    void testRegexTelefono() {
        assertTrue(Contacto.validarTelefono("+57 300 123 4567"));
        assertTrue(Contacto.validarTelefono("+57 310 987 6543"));
        assertTrue(Contacto.validarTelefono("+573001234567"));
        assertTrue(Contacto.validarTelefono("3001234567"));
        assertTrue(Contacto.validarTelefono("315 765 4321"));
        assertTrue(Contacto.validarTelefono("+57 601 234 5678")); // Fijo unificado Bogotá
        assertTrue(Contacto.validarTelefono("6012345678"));

        assertFalse(Contacto.validarTelefono("abc"));
        assertFalse(Contacto.validarTelefono("12345"));
        assertFalse(Contacto.validarTelefono(""));
        assertFalse(Contacto.validarTelefono(null));
    }

    @Test
    @DisplayName("Criterio de Igualdad: ignora mayúsculas y minúsculas en nombre y apellido")
    void testEqualsYHashCode() {
        Contacto c1 = new Contacto("Ana", "López", "3001112222");
        Contacto c2 = new Contacto("ANA", "lópez", "3103334444");
        Contacto c3 = new Contacto("Pedro", "López", "3001112222");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1, c3);
    }
}
