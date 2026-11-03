package com.hackaton.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/* Lógica de negocio de la agenda telefónica.
 * No importa nada de javax.swing: si algo sale mal, lanza una excepción
 * con un mensaje claro y el controlador decide cómo mostrarlo. */

public class Agenda {
    private static final int CAPACIDAD_POR_DEFECTO = 10;

    private final List<Contacto> contactos;
    private final int capacidadMaxima;
    /** Agenda con capacidad predeterminada de 10 contactos. */
    public Agenda() {
        this(CAPACIDAD_POR_DEFECTO);
    }

    /** Agenda con capacidad configurable. */
    public Agenda(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad de la agenda debe ser mayor que cero");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    /**
     * Valida campos obligatorios, espacio disponible y duplicados antes de insertar.
     */
    public void añadirContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo");
        }
        if (estaVacio(c.getNombre())) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (estaVacio(c.getApellido())) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (!Contacto.validarTelefono(c.getTelefono())) {
            throw new IllegalArgumentException("Formato de teléfono inválido. Ej: +34 600 000 000 o 600000000");
        }
        if (agendaLlena()) {
            throw new IllegalArgumentException("La agenda está llena (máximo " + capacidadMaxima + " contactos)");
        }
        if (existeContacto(c)) {
            throw new IllegalArgumentException("Ya existe un contacto llamado "
                    + c.getNombre() + " " + c.getApellido());
        }
        contactos.add(c);
    }

    /** Alias de añadirContacto, por si el equipo prefiere este nombre. */
    public void agregarContacto(Contacto c) {
        añadirContacto(c);
    }

    /** true si ya hay un contacto con el mismo nombre y apellido (usa equals de Contacto). */
    public boolean existeContacto(Contacto c) {
        return c != null && contactos.contains(c);
    }

    /**
     * Devuelve una COPIA de los contactos ordenada alfabéticamente
     * por nombre y luego por apellido, sin distinguir mayúsculas.
     * Es una copia para que nadie pueda modificar la lista interna (encapsulamiento).
     */
    public List<Contacto> listarContactos() {
        List<Contacto> copia = new ArrayList<>(contactos);
        copia.sort(Comparator
                .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER));
        return copia;
    }

    /** Devuelve el teléfono del contacto o lanza excepción si no existe. */
    public String buscarContacto(String nombre, String apellido) {
        return obtenerContacto(nombre, apellido).getTelefono();
    }

    /** Elimina el contacto si existe; si no, lanza excepción. */
    public void eliminarContacto(Contacto c) {
        if (c == null || !contactos.remove(c)) {
            throw new IllegalArgumentException("No se encontró el contacto a eliminar");
        }
    }

    /** Cambia el teléfono de un contacto existente, previa validación con Regex. */
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        Contacto c = obtenerContacto(nombre, apellido);
        if (!Contacto.validarTelefono(nuevoTelefono)) {
            throw new IllegalArgumentException("Formato de teléfono inválido. Ej: +34 600 000 000 o 600000000");
        }
        c.setTelefono(nuevoTelefono.trim());
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    // ----------------- Métodos privados de apoyo -----------------

    /* Busca recorriendo la lista y comparando sin mayúsculas.     */

    private Contacto obtenerContacto(String nombre, String apellido) {
        if (estaVacio(nombre) || estaVacio(apellido)) {
            throw new IllegalArgumentException("Escribe nombre y apellido para buscar");
        }
        for (Contacto c : contactos) {
            if (c.getNombre().trim().equalsIgnoreCase(nombre.trim())
                    && c.getApellido().trim().equalsIgnoreCase(apellido.trim())) {
                return c;
            }
        }
        throw new IllegalArgumentException("No existe el contacto " + nombre.trim() + " " + apellido.trim());
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isBlank();
    }

}

