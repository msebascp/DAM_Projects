# Acceso a Datos en Java con JDBC

Este proyecto se centra en el acceso a bases de datos en Java utilizando JDBC, con ejemplos prácticos para interactuar
con bases de datos MySQL y H2. Hay varios paquetes con la misma práctica pero con algunas modificaciones entre ellas.
Además, en este proyecto se ha usado maven para la gestión de las dependencias.

A
continuación, se proporciona una breve descripción de los archivos clave en este proyecto:

## Archivos Principales

### `Main.java`

Este archivo contiene la clase principal `Main`, que actúa como punto de entrada y presenta un menú interactivo para
ejecutar diversas operaciones. Algunas de las operaciones incluyen:

- Crear conexiones MySQL y H2.
- Crear tablas en MySQL y H2.
- Mostrar información de una persona por ID en MySQL y H2.
- Ejecutar ejemplos de SQL Injection y demostrar cómo evitarlo.

### `JSBCOperations.java`

En esta clase abstracta se encuentran métodos que interactúan directamente con la base de datos, como la creación de
tablas y la búsqueda de datos. También incluye métodos seguros para prevenir ataques de SQL Injection.

## Clases de Conexión

### `H2Conexion.java`

Esta clase proporciona métodos para establecer una conexión a una base de datos H2.

### `MySQLConexion.java`

Similar a `H2Conexion.java`, esta clase se utiliza para crear conexiones a bases de datos MySQL.

## Uso del Proyecto

1. **Conexiones y Creación de Tablas:**
    - Utiliza las opciones del menú (1-4) para establecer conexiones y crear tablas en ambas bases de datos.

2. **Mostrar Información de Persona por ID:**
    - Las opciones 5 y 6 te permiten ingresar un ID y mostrar la información correspondiente de una persona en MySQL y
      H2, respectivamente.

3. **SQL Injection:**
    - Las opciones 7 y 8 demuestran la diferencia entre ejecutar una consulta con y sin protección contra SQL Injection
      en MySQL.

4. **Salir:**
    - La opción 9 cierra el programa.

Recuerda proporcionar configuraciones de conexión válidas en los archivos de conexión (`H2Conexion.java`
y `MySQLConexion.java`) antes de ejecutar el programa.

Este proyecto es una guía práctica para entender el acceso a bases de datos en Java utilizando JDBC.
