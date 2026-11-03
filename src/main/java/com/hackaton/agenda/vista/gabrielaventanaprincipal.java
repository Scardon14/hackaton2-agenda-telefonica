package com.hackaton.agenda.vista;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame { /*aqui creamos la clase y heredamos de la clase JFrame caracteristicas, metodos*/

    /*atributos que usara nuestra ventana, modificador de acceso private porque estos atributos solamente pueden ser accedidos directamente desde la propia clase
    esto es encapsulamiento*/
    //para campos de texto
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    // para la etiqueta de mostrar mensajes
    private JLabel lblMensaje;

    //para el boton de agregar contacto
    private JButton btnAgregar;

    //constructor
    public VentanaPrincipal(){
        setTitle("Agenda Telefónica"); // establece el titulo que aparece en la barra superior de la ventana
        setSize(450,400); // define las dimensiones de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // indica que sucede cuando el usuario cierra la ventana, EXIT_ON_CLOSE finaliza el programa
        setLocationRelativeTo(null); // centra la ventana en la pantalla
    }
    //construir interfaz
    private String crearInterfaz(){

        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(15,15)); /*estamos creando un objeto de tipo JPanel, y dentro del constructor le estamos indicando como
        organizar sus componentes 15px horizontal y verticalmente*/

        //panel encabezado
        JLabel titulo = ComponentesGraficos.crearEtiqueta("Agenda Telefónca");

        //panel del formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4,2, 10, 10)); /*usamos gridlayout porque necesitamos organizar los componentes de manera ordenada
        4 filas, 2 columnas, 10, 10 indican la separacion entre celdass*/

        //crear campos de texto, usamos el mismo metodo pero no reutilizamos el objeto, se crean 3 objetos diferentes
        txtNombre = ComponentesGraficos.crearCampoTexto();
        txtApellido = ComponentesGraficos.crearCampoTexto();
        txtTelefono = ComponentesGraficos.crearCampoTexto();

        //crear el boton de agregar contacto
        btnAgregar = ComponentesGraficos.crearBoton("Agregar");

        //agregar componentes al formulario

        panelFormulario.add(ComponentesGraficos.crearEtiqueta("Nombre: ")); // add es un metodo que permite agregar un componente a un contenedor
        panelFormulario.add(txtNombre);

        panelFormulario.add(ComponentesGraficos.crearEtiqueta("Apellido: "));
        panelFormulario.add(txtApellido);

        panelFormulario.add(ComponentesGraficos.crearEtiqueta("Teléfono: "));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("")); // etiqueta vacia para ocupar la primera celda de la ultima fila y el btn ocupe la segunda
        panelFormulario.add(btnAgregar);

        //panel de estado
        lblMensaje = new JLabel(" ");

        //agrgamos los paneles a la ventana

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(lblMensaje, BorderLayout.SOUTH);

        //aqui agregamos el panel principal al JFrame
        add(panelPrincipal);

        //agregamos los metodos
        public String getNombre() {
            return txtNombre.getText().trim(); /*txtNombre.getText() obtiene lo que el usuario escribio - trim elimina espacios al principio y al final*/
        }
        public String getApellido() {
            return txtApellido.getText().trim();
        }
        public String getTelefono() {
            return txtTelefono.getText().trim();
        }
        public JButton getBtnAgregar() {
            return btnAgregar;
        }
        public void mostrarMensajeError(String mensaje) { // usaoms void porque no devuelve ningun resultado, su unica funcion es mostrar un mensaje
            lblMensaje.setText(mensaje);
            lblMensaje.setForeground(Color.RED);
        }
        public void mostrarMensajeExito(String mensaje) {
            lblMensaje.setText(mensaje);
            lblMensaje.setForeground(new Color(0, 128, 0));
        }

        public void limpiarFormulario() {
            txtNombre.setText("");
            txtApellido.setText("");
            txtTelefono.setText("");
            lblMensaje.setText(" ");
        }
    }