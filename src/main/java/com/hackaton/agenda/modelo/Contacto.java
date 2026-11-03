package com.hackaton.agenda.modelo;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }
        // Comparar contactos ignorando mayúsculas y minúsculas
        @Override
        public boolean equals(Object objeto) {

            if (this == objeto) {
                return true;
            }

            if (!(objeto instanceof Contacto)) {
                return false;
            }

            Contacto otro = (Contacto) objeto;

            return nombre.equalsIgnoreCase(otro.nombre)
                    && apellido.equalsIgnoreCase(otro.apellido);
        }
    }
