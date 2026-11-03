package com.hackaton.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Lógica de negocio de la agenda telefónica.
 * Maneja el almacenamiento de contactos, control de duplicados y cupos.
 */
public class Agenda {

    private static final int CAPACIDAD_POR_DEFECTO = 10;

    private final List<Contacto> contactos;
    private final int capacidadMaxima;

    public Agenda() {
        this(CAPACIDAD_POR_DEFECTO);
    }

    public Agenda(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad de la agenda debe ser mayor que cero");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    /*
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
            throw new IllegalArgumentException("Formato de teléfono inválido. Ej: +57 300 123 4567 o 3001234567");
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

    public void agregarContacto(Contacto c) {
        añadirContacto(c);
    }

    public boolean existeContacto(Contacto c) {
        return c != null && contactos.contains(c);
    }

    /*
     * Devuelve una copia ordenada alfabéticamente por apellido y luego por nombre.
     */
    public List<Contacto> listarContactos() {
        List<Contacto> copia = new ArrayList<>(contactos);
        copia.sort(Comparator
                .comparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return copia;
    }

    public String buscarContacto(String nombre, String apellido) {
        return obtenerContacto(nombre, apellido).getTelefono();
    }

    public void eliminarContacto(Contacto c) {
        if (c == null || !contactos.remove(c)) {
            throw new IllegalArgumentException("No se encontró el contacto a eliminar");
        }
    }

    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        Contacto c = obtenerContacto(nombre, apellido);
        if (!Contacto.validarTelefono(nuevoTelefono)) {
            throw new IllegalArgumentException("Formato de teléfono inválido. Ej: +57 300 123 4567 o 3001234567");
        }
        c.setTelefono(nuevoTelefono.trim());
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return Math.max(0, capacidadMaxima - contactos.size());
    }

    public int getCantidadContactos() {
        return contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public Contacto obtenerContacto(String nombre, String apellido) {
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
        return texto == null || texto.trim().isEmpty();
    }
}
