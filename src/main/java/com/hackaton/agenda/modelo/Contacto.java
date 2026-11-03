package com.hackaton.agenda.modelo;

import java.util.Objects;
import java.util.regex.Pattern;

/*
 * Entidad que representa un contacto en la agenda telefónica.
 */
public class Contacto implements Comparable<Contacto> {

    private String nombre;
    private String apellido;
    private String telefono;

    // Expresión regular para números colombianos (celulares o fijos unificados de 10 dígitos)
    // Admite con prefijo internacional +57 o sin él (ej: +57 300 123 4567, 3001234567, 601 123 4567)
    public static final String REGEX_TELEFONO = "^(\\+57[\\s-]?)?([1-9]\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{4}|[1-9]\\d{9})$";
    private static final Pattern PATTERN_TELEFONO = Pattern.compile(REGEX_TELEFONO);

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        return PATTERN_TELEFONO.matcher(telefono.trim()).matches();
    }

    public static boolean esTelefonoValido(String telefono) {
        return validarTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío ni tener solo espacios.");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío ni tener solo espacios.");
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El formato del teléfono no es válido (ej: +57 300 123 4567 o 3001234567).");
        }
        this.telefono = telefono.trim();
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Contacto)) return false;
        Contacto otro = (Contacto) objeto;
        return nombre != null && otro.nombre != null &&
               apellido != null && otro.apellido != null &&
               nombre.equalsIgnoreCase(otro.nombre) &&
               apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nombre != null ? nombre.toLowerCase() : "",
                apellido != null ? apellido.toLowerCase() : ""
        );
    }

    @Override
    public int compareTo(Contacto otro) {
        if (otro == null) return 1;
        int compApellido = this.apellido.compareToIgnoreCase(otro.apellido);
        if (compApellido != 0) {
            return compApellido;
        }
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
