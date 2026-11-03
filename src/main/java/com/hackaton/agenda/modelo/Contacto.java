package com.hackaton.agenda.modelo;


package com.hackaton.agenda.modelo;

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

    // Setter del teléfono (para modificarTelefono)
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Validar teléfono
    public static boolean validarTelefono(String telefono) {
        return telefono != null &&
                telefono.trim().matches(REGEX_TELEFONO);
    }
}

