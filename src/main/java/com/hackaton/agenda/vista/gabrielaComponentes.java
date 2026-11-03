package com.hackaton.agenda.vista;
import javax.swing.*; // importacion de la biblioteca Swing que permite crear interfaces graficas de escritorio
import java.awt.*; // AWT significa Abstract Window Toolkit, es otra biblioteca de Java que contiene herramientas para trabajar con interfaces graficas, en este caso para que podamos usar : Font, Color, BorderLayout, FlowLayout

public class ComponentesGraficos {

    //Con este metodo creamos etiquetas
    public static JLabel crearEtiqueta(String texto){ /*public permite usar el metodo desde otra clase - static, permite llamar la metodo sin necesidad de crear
    un objeto de la clase, nos permite usar el metodo directamente desde la clase - JLabel, indica el tipo de componente de la libreria Swing que debe devolver,
    en este caso mostrar textos simples e informativos*/
        JLabel etiqueta = new JLabel(texto); /*aqui creamos el objeto - JLabel, tipo de objeto - etiqueta, nombre de nuestra variable -
        new, palabra reservada para crear un objeto - JLabel(texto), constructor de la clase JLabel que recibe el texto que queremos mostrar */
        etiqueta.setFont(new Font ("Arial", Font.BOLD, 14)); /*Aqui configuramos la tipografia de la etiqueta- setFont(, es un metodo que nos permite cambiar la fuente
        del componente - Font, una clase de la biblioteca AWT, aqui recibe 3 argumentos 1. familia tipografica, 2. estilo negrita 3. tamaño de la letra
         Font.PLAIN- Normal, Font.ITALIC-Cursiva*/
        return etiqueta;
    }

    //Con este metodo creamos los campos de texto
    public static JTextField crearCampoTexto(){ /*no tiene parametros porque a diferencia del metodo anterior no necesitamos recibir un texto para construir el campo*/
        JTextField campo = new JTextField(20); /*este componente permite recibir informacion escrita por el usuario- 20 es la cantidad de columnas que tendra el campo, el ancho del campo*/
        campo.setFont(new Font ("Arial", Font.PLAIN, 14));
        return campo;
    }

    // Con este metodo creamos los botones
    public static JButton crearBoton(String texto){/*devuelve un objeto de tipo jbutton, recibe el texto que sera el contenido del boton  */
        JButton boton = new JButton(texto);/*este componente permite que el usuario ejecute una accion mediante un click*/
        boton.setFont(new Font ("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false); // pequeño contorno de enfoque, efecto visual desactivado
        return boton;
    }