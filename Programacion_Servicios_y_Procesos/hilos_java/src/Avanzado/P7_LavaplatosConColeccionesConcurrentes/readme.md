# Tarea 7: Lavaplatos con Colecciones Concurrentes

En esta tarea, se modifica la implementación del lavaplatos del tema anterior, sustituyendo la clase `Stack` utilizada
para la pila de platos por una colección concurrente adecuada.

## Clase `PiladePlatos`

La clase `PiladePlatos` representa el lavaplatos mejorado con colecciones concurrentes.

### Atributos

- `pila`: `BlockingDeque<Plato>` - Una cola de bloqueo (BlockingDeque) que almacena los platos lavados. Se
  utiliza `LinkedBlockingDeque` para garantizar la concurrencia.

### Métodos

#### `lavar(Plato plato)`

El método `lavar` agrega un plato a la pila de manera concurrente. Utiliza el método `putFirst()` de `BlockingDeque`
para insertar el plato al principio de la cola. Imprime un mensaje indicando que el plato ha sido lavado y la cantidad
de platos en la pila.

#### `secar()`

El método `secar` retira un plato de la pila de manera concurrente. Utiliza el método `takeFirst()` de `BlockingDeque`
para tomar el primer plato de la cola. Imprime un mensaje indicando que el plato ha sido secado y la cantidad de platos
restantes en la pila.

### Uso de Colecciones Concurrentes

La implementación utiliza `BlockingDeque`, una interfaz de Java Collections Framework diseñada para soportar
concurrencia. La elección de esta colección garantiza que las operaciones de inserción y extracción sean seguras y se
gestionen adecuadamente en entornos con múltiples hilos.

El uso de colecciones concurrentes es crucial en situaciones donde múltiples hilos pueden acceder y modificar la
estructura de datos compartida, asegurando la coherencia y la integridad de los datos.
