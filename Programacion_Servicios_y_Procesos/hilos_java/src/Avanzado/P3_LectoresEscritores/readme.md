# Tarea 3: Lectores – Escritores

En esta tarea, se presenta una implementación básica de lectores y escritores utilizando un fichero. Se utiliza un
objeto `ReadWriteLock` de tipo `ReentrantReadWriteLock` para controlar el acceso concurrente.

## Clase EditorTexto

La clase `EditorTexto` representa un editor simple de texto que permite leer y escribir en un fichero.

### Atributos

- **file (File):** El fichero sobre el cual se realiza la operación de lectura y escritura.

- **lock (ReadWriteLock):** Un objeto `ReentrantReadWriteLock` utilizado para gestionar el acceso concurrente de
  lectores y escritores.

### Métodos

#### Constructor: EditorTexto(File file)

El constructor recibe un objeto `File` que representa el fichero sobre el cual se realizarán las operaciones.

#### leer()

El método `leer` se encarga de leer todo el contenido del fichero. Utiliza el lock de lectura (`readLock()`) para
garantizar que varias instancias de este método puedan ejecutarse simultáneamente sin interferencias. Se utiliza un
bloque `try-catch` para manejar las excepciones relacionadas con la lectura del fichero.

#### escribir()

El método `escribir` añade una línea al final del fichero. Utiliza el lock de escritura (`writeLock()`) para garantizar
la exclusión mutua en operaciones de escritura concurrentes. En este ejemplo, se añaden tres líneas al fichero.

## Uso de ReadWriteLock

Se utiliza un `ReentrantReadWriteLock` para gestionar el acceso concurrente. Los métodos `read()` y `write()` adquieren
y liberan los locks de lectura y escritura, respectivamente. Esto permite que varios lectores puedan acceder
simultáneamente, siempre que no haya un escritor presente. Sin embargo, si hay un escritor, ningún lector o escritor
adicional podrá obtener el lock hasta que el escritor haya terminado.
