package com.hackaton.agenda.modelo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    // Validación de teléfonos colombianos
    public static final String REGEX_TELEFONO =
            "^(\\+57[\\s-]?)?([1-9]\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{4}|[1-9]\\d{9})$";

    private static final Pattern PATTERN_TELEFONO =
            Pattern.compile(REGEX_TELEFONO);

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null &&
                PATTERN_TELEFONO.matcher(telefono.trim()).matches();
    }

    // Comparar contactos por nombre y apellido
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Contacto)) return false;

        Contacto otro = (Contacto) objeto;

        return Objects.equals(
                nombre == null ? null : nombre.trim().toLowerCase(),
                otro.nombre == null ? null : otro.nombre.trim().toLowerCase()
        ) && Objects.equals(
                apellido == null ? null : apellido.trim().toLowerCase(),
                otro.apellido == null ? null : otro.apellido.trim().toLowerCase()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombre == null ? null : nombre.trim().toLowerCase(),
                apellido == null ? null : apellido.trim().toLowerCase()
        );
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}
