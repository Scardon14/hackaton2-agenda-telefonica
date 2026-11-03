# Agenda Telefónica MVC
Aplicación de escritorio para la gestión de contactos telefónicos, desarrollada en Java Swing utilizando Gradle y aplicando el patrón arquitectónico Modelo-Vista-Controlador (MVC).

Este proyecto se desarrolla como parte de la **Hackathon 2 de Generation**, cuyo objetivo es construir una aplicación que permita registrar, consultar, modificar y eliminar contactos mediante una interfaz gráfica intuitiva.



## Objetivo del proyecto
Desarrollar una agenda telefónica que permita administrar contactos de manera sencilla y organizada, garantizando la validación de los datos, evitando registros duplicados y respetando la separación de responsabilidades establecida por la arquitectura MVC.

## Tecnologías utilizadas
- **Java:** lenguaje de programación.
- **Java Swing:** construcción de la interfaz gráfica de escritorio.
- **Gradle:** herramienta de automatización y gestión del proyecto.
- **IntelliJ IDEA:** entorno de desarrollo.
- **Git y GitHub:** control de versiones y trabajo colaborativo.

## Funcionalidades
La aplicación contempla las siguientes funcionalidades:

### Gestión de contactos
- Registrar nuevos contactos mediante nombre, apellido y teléfono.
- Consultar contactos registrados.
- Buscar contactos por nombre y apellido.
- Modificar el número telefónico de un contacto existente.
- Eliminar contactos de la agenda.
- Mostrar todos los contactos registrados.

### Validaciones
- Validar que el nombre y el apellido no estén vacíos.
- Validar el formato del número telefónico mediante expresiones regulares (Regex).
- Evitar el registro de contactos duplicados.
- Controlar la capacidad máxima de la agenda.
- Mostrar mensajes descriptivos cuando se presenta un error.
- Notificar las operaciones exitosas mediante mensajes visuales.

### Interfaz gráfica
- Formulario para registrar y modificar contactos.
- Tabla para visualizar los contactos registrados.
- Carga automática de los datos al seleccionar una fila de la tabla.
- Panel de estado con mensajes de éxito en color verde y errores en color rojo.
- Indicador visual de la capacidad disponible de la agenda.

## Arquitectura del proyecto
El proyecto implementa el patrón **Modelo-Vista-Controlador (MVC)**, separando las responsabilidades de la aplicación en tres capas:

| Componente | Responsabilidad |
|---|---|
| Modelo | Administra los datos de los contactos y contiene las reglas de negocio. |
| Vista | Construye la interfaz gráfica y presenta la información al usuario. |
| Controlador | Gestiona los eventos de la interfaz y coordina las operaciones entre el modelo y la vista. |

### Estructura de paquetes

```text
src/
└── main/
    └── java/
        └── com/
            └── hackaton/
                └── agenda/
                    ├── modelo/
                    │   ├── Contacto.java
                    │   └── Agenda.java
                    │
                    ├── vista/
                    │   ├── VentanaPrincipal.java
                    │   └── ComponentesGraficos.java
                    │
                    ├── controlador/
                    │   └── AgendaControlador.java
                    │
                    └── Main.java
```

**Descripción de los componentes:**

- `Contacto.java`: representa la entidad contacto y sus atributos: nombre, apellido y teléfono.
- `Agenda.java`: administra la colección de contactos, las validaciones y las operaciones de gestión.
- `VentanaPrincipal.java`: contiene la ventana principal y el formulario de registro.
- `ComponentesGraficos.java`: centraliza la construcción y personalización de los componentes gráficos, incluyendo el panel de gestión.
- `AgendaControlador.java`: conecta los eventos de la interfaz con las operaciones del modelo.
- `Main.java`: punto de entrada de la aplicación; instancia los componentes principales y establece su conexión.

## Reglas de negocio

La aplicación contempla las siguientes reglas:
1. La agenda tiene una capacidad predeterminada de 10 contactos, configurable al inicializarla.
2. No se permiten contactos duplicados.
3. Dos contactos se consideran iguales cuando tienen el mismo nombre y apellido, sin distinguir entre mayúsculas y minúsculas.
4. El nombre y el apellido son campos obligatorios.
5. El número telefónico debe cumplir con el formato establecido mediante expresiones regulares.
6. La modificación de un teléfono requiere validar previamente el nuevo número.
7. Los contactos se presentan ordenados alfabéticamente.

## Requisitos para ejecutar el proyecto
Antes de ejecutar la aplicación, se requiere:
- Tener instalado un JDK compatible con la configuración del proyecto.
- Tener instalado IntelliJ IDEA o un entorno compatible con proyectos Gradle.
- Clonar el repositorio del proyecto.
- Utilizar el Gradle Wrapper incluido en el repositorio.

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Scardon14/hackaton2-agenda-telefonica.git
```

### 2. Ingresar al directorio del proyecto

```bash
cd hackaton2-agenda-telefonica
```

### 3. Abrir el proyecto

Abrir la carpeta del proyecto desde IntelliJ IDEA y esperar a que Gradle sincronice las dependencias y configure el entorno.

### 4. Compilar el proyecto

En macOS o Linux:

```bash
./gradlew build
```

En Windows:

```bash
gradlew.bat build
```

### 5. Ejecutar la aplicación

Abrir la clase `Main.java` desde IntelliJ IDEA y ejecutar su método `main()`.

> Nota: La ejecución mediante `./gradlew run` requiere que el proyecto tenga configurado el plugin `application` y su clase principal.

## Trabajo colaborativo

El desarrollo se realiza mediante Git y GitHub, utilizando ramas independientes para organizar las contribuciones de los integrantes del equipo.

Cada integrante desarrolla una parte específica del proyecto y posteriormente integra sus cambios mediante Pull Requests.

## Objetivo de aprendizaje

Este proyecto permite poner en práctica conceptos fundamentales de programación orientada a objetos, arquitectura MVC, manejo de colecciones, validaciones mediante expresiones regulares, desarrollo de interfaces gráficas con Java Swing y trabajo colaborativo con control de versiones.

---

**Hackathon 2 – Generation**

*Desarrollo colaborativo de una agenda telefónica aplicando Java, Swing y arquitectura MVC.*