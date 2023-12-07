# Práctica 6 - Lectura de XML con SAX en Java

## Main

### Descripción

La clase `Main` contiene un programa que utiliza SAX (Simple API for XML) para realizar la lectura de un archivo XML
llamado `ms.xml`.

### Ejecución

1. Ejecuta el archivo `Main.java`.
2. La aplicación leerá el archivo `ms.xml` utilizando SAX y mostrará en consola la información del documento XML.

### Funcionalidades

- **Lectura de XML con SAX:** Utiliza SAX para analizar y procesar el contenido del archivo XML.
- **Manejo de Excepciones:** Maneja excepciones relacionadas con la configuración del parser SAX.

---

## Controladora

### Descripción

La clase `Controladora` es una implementación de `DefaultHandler` que define métodos para manejar eventos específicos
durante el análisis del documento XML.

### Métodos Implementados

- **startDocument:** Se ejecuta al comienzo del documento XML.
- **endDocument:** Se ejecuta al final del documento XML.
- **startElement:** Se ejecuta al encontrar un elemento de apertura en el documento XML. Imprime información según el
  tipo de elemento.
- **characters:** Se ejecuta cuando se encuentran caracteres dentro de un elemento. Imprime el contenido de los
  elementos.

### Funcionalidades

- **Manejo de Eventos SAX:** Define acciones para diferentes eventos durante el análisis SAX.
- **Impresión de Información:** Muestra en consola información específica de ciertos elementos del XML.

---

**Tecnologías Utilizadas:**

- Java
- SAX (Simple API for XML)

