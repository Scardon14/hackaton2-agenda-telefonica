
package com.hackaton.agenda.modelo;

import java.util.Objects;

public class Contacto {

    // Atributos
    private String nombre;
    private String apellido;
    private String telefono;

    // Expresión regular para validar el teléfono
    private static final String REGEX_TELEFONO =
            "^(\\+\\d{1,3}\\s?)?\\d{3}\\s?\\d{3}\\s?\\d{3}$";

    // Constructor vacío
    public Contacto() {
    }

    // Constructor con parámetros
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // Getter del nombre
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter del apellido
    public String getApellido() {
        return apellido;
    }

    // Setter del apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter del teléfono
    public String getTelefono() {
        return telefono;
    }

    // Setter del teléfono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Validar teléfono
    public static boolean validarTelefono(String telefono) {
        return telefono != null &&
                telefono.trim().matches(REGEX_TELEFONO);
    }

    // Dos contactos son iguales si tienen el mismo nombre y apellido
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

