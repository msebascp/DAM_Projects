# Tarea 6: Fork/Join

En esta tarea, se utiliza el framework Fork/Join para realizar operaciones de búsqueda y reemplazo en un fichero de
texto. El programa crea tareas que buscan la palabra "Java" y la reemplazan por "JAVA", dividiendo el trabajo en
subtareas gestionadas por Fork/Join.

## Clase `Main`

La clase `Main` representa el programa principal encargado de coordinar la ejecución del programa Fork/Join.

### Métodos

#### `main(String[] args)`

El método principal `main` realiza las siguientes acciones:

1. **Lectura del Fichero:** Lee el contenido del fichero de texto y almacena las líneas en un ArrayList.

2. **Creación de la Tarea Fork/Join:** Se instancia la clase `forkJoin` pasándole el ArrayList de líneas, la primera
   línea y la última línea como parámetros.

3. **Ejecución de la Tarea y Obtención del Resultado:** Se invoca la tarea Fork/Join utilizando `invoke()`, y se obtiene
   el resultado mediante `join()`.

4. **Escritura del Resultado en un Nuevo Fichero:** El resultado obtenido se escribe en un nuevo fichero de texto.

## Clase `forkJoin`

La clase `forkJoin` hereda de `RecursiveTask<ArrayList<String>>`, y se encarga de realizar la búsqueda y reemplazo de
palabras en un rango específico de líneas.

### Atributos

- `texto`: ArrayList que almacena las líneas del fichero de texto.
- `MAXLINES`: Constante que define el límite máximo de líneas para realizar la sustitución directa sin dividir el
  trabajo.
- `primeraLinea`: Índice de la primera línea en el rango asignado a la tarea.
- `ultimaLinea`: Índice de la última línea en el rango asignado a la tarea.
- `palabraBuscar`: Palabra que se busca en las líneas del texto.
- `palabraSustituir`: Palabra con la que se reemplaza la palabra buscada.

### Métodos

#### `compute()`

El método `compute()` realiza la operación de búsqueda y reemplazo. Si el rango de líneas asignado es menor o igual
a `MAXLINES`, realiza la sustitución directa. Si es mayor, divide el trabajo en dos subtareas recursivas y combina los
resultados.

### Uso de Fork/Join

En este programa, el uso de Fork/Join simplifica la implementación de tareas recursivas y permite dividir el trabajo en
subtareas que se ejecutan de manera eficiente en paralelo, mejorando así el rendimiento del programa.
