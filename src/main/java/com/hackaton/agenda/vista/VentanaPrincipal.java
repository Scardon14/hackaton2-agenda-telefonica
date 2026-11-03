package com.hackaton.agenda.vista;
import com.hackaton.agenda.modelo.Contacto; //importamos la clase Contacto para trabajar con los datos de la agenda
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*; // importaciones de Java para diseño y eventos.
import java.awt.event.ActionListener;
import java.util.List;

/*Clase VentanaPrincipal: es la ventana principal de la aplicación Agenda Telefónica, su responsabilidad dentro del patrón MVC es mostrar la interfaz
gráfica y permitir que el controlador acceda a sus componentes.No contiene reglas de negocio ni decide qué sucede al presionarlos botones.
Esa responsabilidad corresponde al controlador.*/
public class VentanaPrincipal extends JFrame {

  /*atributos que usara nuestra ventana, modificador de acceso private porque estos atributos solamente pueden ser accedidos directamente desde la propia clase
    esto es encapsulamiento*/
    //para campos de texto
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellido = new JTextField();
    private JTextField txtTelefono = new JTextField();

    // botones disponibles en el formulario
    private JButton btnAgregar = new JButton("Añadir Contacto");
    private JButton btnModificar = new JButton("Modificar Teléfono");
    private JButton btnLimpiar = new JButton("Limpiar Formulario");

    // atributos del panel de gestion

    //panel creado en ComponentesGraficos que contiene: búsqueda, tabla de contactos, eliminación y capacidad.
    private ComponentesGraficos.PanelGestion panelGestion;

    // para la etiqueta de mostrar mensajes
    private JLabel lblMensajeEstado;

    /*Constructor de la ventana: Se ejecuta cuando se crea un objeto VentanaPrincipal, primero configura las propiedades generales de la ventana
    y después construye todos sus componentes gráficos*/
    public VentanaPrincipal() {

        //define el título que aparece en la parte superior
        super("Agenda Telefónica MVC");

        //configura el tamaño, ubicación y comportamiento de la ventana
        configurarVentana();

        //construye y organiza los componentes de la interfaz.
        inicializarComponentes();
    }
    /*configuracion de la ventana, configura las propiedades principales del JFrame: acción al cerrar la ventana, tamaño inicial, tamaño mínimo permitido, ubicación centrada
    en la pantalla, distribución general mediante BorderLayout.*/
    private void configurarVentana() {

        //cierra la aplicación cuando el usuario cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //establece el ancho y alto inicial de la ventana.
        setSize(980, 560);

        //evita que la ventana se reduzca demasiado.
        setMinimumSize(new Dimension(880, 500));

        //centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        //establece el administrador de distribución principal.
        setLayout(new BorderLayout(10, 10));
    }

    /*inicializar componentes: construye la estructura principal de la interfaz, la ventana se divide en tres secciones: NORTH-Encabezado, CENTER-Formulario y panel de gestión.
     SOUTH-Panel de mensajes de estado*/
    private void inicializarComponentes() {

        //panel que contiene el título y subtítulo
        JPanel panelEncabezado = new JPanel(new BorderLayout());

        //establece el color de fondo del encabezado
        panelEncabezado.setBackground(
                ComponentesGraficos.COLOR_PRIMARIO
        );

        //agrega espacio interno alrededor del contenido
        panelEncabezado.setBorder(
                new EmptyBorder(12, 16, 12, 16)
        );

        //título principal de la aplicación
        JLabel lblTitulo = new JLabel("AGENDA TELEFÓNICA MVC");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        lblTitulo.setForeground(Color.WHITE);

        //subtítulo de la aplicacion
        JLabel lblSubtitulo = new JLabel(
                "Patrón Modelo-Vista-Controlador"
        );

        lblSubtitulo.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        lblSubtitulo.setForeground(
                new Color(230, 240, 255)
        );

        //ubica el título a la izquierda
        panelEncabezado.add(lblTitulo, BorderLayout.WEST);

        //ubica el subtítulo a la derecha
        panelEncabezado.add(lblSubtitulo, BorderLayout.EAST);

        //agrega el encabezado en la parte superior de la ventana
        add(panelEncabezado, BorderLayout.NORTH);

        /*divide el espacio central en dos columnas, la primera contiene el formulario, la segunda contiene el panel de gestión.*/
        JPanel panelCentral = new JPanel(
                new GridLayout(1, 2, 12, 0)
        );

        //agrega márgenes alrededor del panel central.
        panelCentral.setBorder(
                new EmptyBorder(10, 12, 5, 12)
        );

        // Construye el formulario de registro
        JPanel panelRegistro = construirPanelRegistro();

        // Agrega el formulario a la primera columna.
        panelCentral.add(panelRegistro);

        //crea el panel de gestión
        panelGestion = new ComponentesGraficos.PanelGestion();

        //agrega el panel de gestión a la segunda columna
        panelCentral.add(panelGestion);

        //ubica el panel central en la ventana
        add(panelCentral, BorderLayout.CENTER);

        //construye el panel donde aparecerán los mensajes
        JPanel panelEstado = construirPanelEstado();

        //ubica el panel de estado en la parte inferior
        add(panelEstado, BorderLayout.SOUTH);
    }

    /*construye el formulario para registrar y modificar contactos, contiene:campo de nombre, campo de apellido, campo de teléfono, boton añadir, boton modificar telefono
   boton limpiar formulario, retorna un JPanel que se incorpora a la ventana principal*/
    private JPanel construirPanelRegistro() {

        // panel principal del formulario
        JPanel panel = new JPanel(
                new BorderLayout(15, 20)
        );

        // agrega un borde con ttulo y margenes internos
        panel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createLineBorder(new Color(200, 205, 210), 1, true),
                        " Registro de contactos ", TitledBorder.LEFT, TitledBorder.TOP, ComponentesGraficos.FUENTE_TITULO, ComponentesGraficos.COLOR_PRIMARIO),
                new EmptyBorder(25, 20, 25, 20))
        );

        //etiquetas y campos de texto
        // GridLayout organiza los componentes en 3 filas y 2 columnas
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 20));

        // etiquetas que identifican cada campo
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblTelefono = new JLabel("Teléfono:");

        // aplicamos la fuente definida en ComponentesGraficos
        lblNombre.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        lblApellido.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        lblTelefono.setFont(ComponentesGraficos.FUENTE_NEGRILLA);

        // aplicamos el estilo general a los campos
        txtNombre.setFont(ComponentesGraficos.FUENTE_GENERAL);
        txtApellido.setFont(ComponentesGraficos.FUENTE_GENERAL);
        txtTelefono.setFont(ComponentesGraficos.FUENTE_GENERAL);

        // textos de ayuda que aparecen al pasar el cursor.
        txtNombre.setToolTipText("Ingrese el nombre del contacto");
        txtApellido.setToolTipText("Ingrese el apellido del contacto");
        txtTelefono.setToolTipText("Ingrese el número de teléfono");

        // Agregamos las etiquetas y sus respectivos campos.
        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);

        panelCampos.add(lblApellido);
        panelCampos.add(txtApellido);

        panelCampos.add(lblTelefono);
        panelCampos.add(txtTelefono);

        //botones del formulario
        //organiza los tres botones verticalmente
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 12));

        //establece la fuente de los botones
        btnAgregar.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        btnModificar.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        btnLimpiar.setFont(ComponentesGraficos.FUENTE_NEGRILLA);

        //personaliza el botón de agregar
        btnAgregar.setBackground(ComponentesGraficos.COLOR_PRIMARIO);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);

        //evita el borde de enfoque visual en los otros botones
        btnModificar.setFocusPainted(false);
        btnLimpiar.setFocusPainted(false);

        //agrega los botones al panel
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnLimpiar);

        //los campos se ubican en el centro
        panel.add(panelCampos, BorderLayout.CENTER);

        //los botones se ubican en la parte inferior
        panel.add(panelBotones, BorderLayout.SOUTH);

        //retorna el formulario terminado
        return panel;
    }

    /*construye la sección inferior de la ventana, aquí se mostrarán los mensajes que el controlador envíe, después de realizar una operación,
    los mensajes de error aparecerán en rojo y los exitosos aparecerán en verde*/
    private JPanel construirPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());

        // agrega un borde superior y mrgenes internas
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(210, 215, 220)),
                new EmptyBorder(6, 16, 6, 16)));

        //color de fondo del panel
        panel.setBackground(new Color(245, 247, 250));

        //mensaje inicial que aparece al abrir la aplicacion
        lblMensajeEstado = new JLabel("SBienvenid@ a tu agenda de contactos!");
        lblMensajeEstado.setFont(ComponentesGraficos.FUENTE_NEGRILLA);
        lblMensajeEstado.setForeground(new Color(90, 95, 100));
        // Ubica el mensaje a la izquierda.
        panel.add(lblMensajeEstado, BorderLayout.WEST);
        return panel;
    }
    /*los metodos que muestran el mensaje de exito en el panel de estado, tambien recibe el mensaje como parmetro y cambia su color a verde*/
    public void mostrarMensajeExito(String mensaje) {
        lblMensajeEstado.setText(mensaje);
        lblMensajeEstado.setForeground(ComponentesGraficos.COLOR_EXITO);
    }

    /*muestra el mensaje de error en el panel de estadoRecibe el mensaje como parametro y cambia su color a rojo*/
    public void mostrarMensajeError(String mensaje) {
        lblMensajeEstado.setText(mensaje);
        lblMensajeEstado.setForeground(ComponentesGraficos.COLOR_PELIGRO);
    }

    /* metodos paracarga los datos de un contacto seleccionado en la tabla dentro de los campos del formulario
    el controlador llama a este método cuando el usuario selecciona una fila de la tabla*/
    public void cargarContactoEnFormulario(String nombre, String apellido, String telefono){
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }
    /*limpia todos los campos del formulario, también devuelve el cursor al campo de nombre*/
    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtNombre.requestFocus();
    }
    /*obtiene el nombre escrito por el usuario, trim() elimina los espacios al inicio y al final*/
    public String getNombre() {
        return txtNombre.getText().trim();
    }
    /*obtiene el apellido escrito por el usuario*/
    public String getApellido() {
        return txtApellido.getText().trim();
    }
    /*obtiene el telefono escrito por el usuario, se usa String porque un telefono puede contener el símbolo +, espacios y ceros al inicio*/
    public String getTelefono() {
        return txtTelefono.getText().trim();
    }
    /*coloca un nombre en el campo correspondiente,si el valor recibido es null, deja el campo vacío*/
    public void setNombre(String nombre) {
        txtNombre.setText(nombre != null ? nombre : "");
    }
    /*coloca un apellido en el campo correspondiente*/
    public void setApellido(String apellido) {
        txtApellido.setText(apellido != null ? apellido : "");
    }
    /*coloca un teléfono en el campo correspondiente*/
    public void setTelefono(String telefono) {
        txtTelefono.setText(telefono != null ? telefono : "");
    }
    /*retorna el btn de agregar.El controlador utilizará este método para escuchar el evento de registro de un contacto*/
    public JButton getBtnAgregar() {
        return btnAgregar;
    }
    /*retorna el botón de búsqueda del panel de gestion*/
    public JButton getBtnBuscar() {
        return panelGestion.getBtnBuscar();
    }
    /*retorna el botón para modificar el teléfono*/
    public JButton getBtnModificar() {
        return btnModificar;
    }
    /*retorna el botón para eliminar un contacto*/
    public JButton getBtnEliminar() {
        return panelGestion.getBtnEliminar();
    }

    /*retorna el botón para limpiar el formulario*/
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /*retorna el botón que muestra todos los contactos*/
    public JButton getBtnMostrarTodos() {
        return panelGestion.getBtnMostrarTodos();
    }
    /*retorna la tabla de contactos (JTable), permite que el controlador consulte la tabla y sus filas*/
    public JTable getTabla() {
        return panelGestion.getTablaContactos();
    }

    /*retorna el modelo de datos de la tabla, DefaultTableModel administra las filas y columnas que se muestran en el JTable*/
    public DefaultTableModel getModeloTabla() {

        return panelGestion.getModeloTabla();
    }

    /*actualiza las filas de la tabla con una lista de contactos, recibe la lista que entrega el controlador y delega la actualización al PanelGestion*/
    public void actualizarTabla(List<Contacto> contactos) {
        panelGestion.actualizarTabla(contactos);
    }

    /*muestra en la tabla únicamente el contacto encontrado, recibe nombre, apellido y teléfono*/
    public void mostrarContactoEncontrado(String nombre, String apellido, String telefono){
        panelGestion.mostrarContactoEncontrado(nombre, apellido, telefono);
    }

    /*actualiza la información visual de la capacidad de la agenda, ocupados: cantidad de contactos registrados, maximo: capacidad máxima de la agenda
     disponibles: espacios que todavía están libres*/
    public void actualizarCapacidad(int ocupados, int maximo, int disponibles){
        panelGestion.actualizarCapacidad(ocupados, maximo, disponibles);
    }

    /*otiene el índice de la fila seleccionada en la tabla, retorna -1 cuando no hay ninguna fila seleccionada*/
    public int getFilaSeleccionadaTabla() {
        return panelGestion.getFilaSeleccionada();
    }


    /*obtiene los datos de un contacto a partir del número de fila, retorna un arreglo con: posición 0-nombre, posición 1-apellido, posición 2- teléfono,si la fila no existe,
    retorna null*/
    public String[] obtenerContactoDeFila(int fila) {
        return panelGestion.obtenerContactoDeFila(fila);
    }

    /*conecta el evento del botón agregar, recibe un ActionListener creado por el controlador¡*/
    public void addAgregarListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
    }

    /*conecta el evento del botón modificar*/
    public void addModificarListener(ActionListener listener) {
        btnModificar.addActionListener(listener);
    }

    /*conecta el evento del botón limpiar*/
    public void addLimpiarListener(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }
    /*conecta el evento del botón buscar, la conexión se delega al PanelGestion*/
    public void addBuscarListener(ActionListener listener) {
        panelGestion.addBuscarListener(listener);
    }

    /*conecta el evento del botón mostrar todos*/
    public void addMostrarTodosListener(ActionListener listener) {
        panelGestion.addMostrarTodosListener(listener);
    }

    /*conecta el evento del botón eliminar*/
    public void addEliminarListener(ActionListener listener) {
        panelGestion.addEliminarListener(listener);
    }


    /*conecta un evento de selección de filas de la tabla, se utiliza ListSelectionListener porque el evento ocurre cuando el usuario selecciona o cambia una fila*/
    public void addSeleccionTablaListener(ListSelectionListener listener){
        panelGestion.addSeleccionTablaListener(listener);
    }
    /*retorna directamente el campo de nombre, permite que el controlador acceda al componente cuando necesite realizar alguna operación específica*/
    public JTextField getTxtNombre() {

        return txtNombre;
    }
    /*retorna directamente el campo de apellido*/
    public JTextField getTxtApellido() {
        return txtApellido;
    }
    /*retorna directamente el campo de teléfono*/
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }
    /*retorna el panel de gestión completo */
    public ComponentesGraficos.PanelGestion getPanelGestion() {
        return panelGestion;
    }
    /*retorna la etiqueta que muestra los mensajes de estado*/
    public JLabel getLblMensajeEstado() {
        return lblMensajeEstado;
    }

}
