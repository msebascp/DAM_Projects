# Tarea 2: Lavaplatos con Locks y Conditions

En esta tarea, se implementa la simulación del proceso de lavar y secar platos con Locks y Conditions. Se utiliza un
objeto de tipo `ReentrantLock` para garantizar la exclusión mutua y dos condiciones (`Condition`) para controlar el
acceso a la pila de platos, una para cuando la pila está llena y otra para cuando la pila está vacía.

## Clase PiladePlatos

La clase `PiladePlatos` representa la pila de platos utilizada en el programa. Aquí se explican sus métodos y atributos
más relevantes.

### Atributos

- **pila (Stack<Plato>):** La pila de platos que se utiliza para almacenar los platos lavados.

- **lock (ReentrantLock):** Un objeto `ReentrantLock` que se utiliza para garantizar la exclusión mutua en las
  operaciones críticas.

- **conditionPilaLlena (Condition):** Una condición que se utiliza para esperar cuando la pila está llena.

- **conditionPilaVacia (Condition):** Una condición que se utiliza para esperar cuando la pila está vacía.

### Métodos

#### lavar(Plato plato)

Este método se encarga de lavar un plato y colocarlo en la pila. Utiliza el lock y la condición `conditionPilaLlena`
para esperar si la pila está llena y notifica a los hilos que están esperando a que la pila no esté vacía.

#### secar()

El método `secar` se encarga de secar un plato de la pila. Utiliza el lock y la condición `conditionPilaVacia` para
esperar si la pila está vacía. Luego, notifica a los hilos que están esperando a que la pila no esté llena y devuelve el
plato secado.

## Uso de Locks y Conditions

Se utiliza un `ReentrantLock` para garantizar la exclusión mutua en las operaciones de lavado y secado de platos. Las
condiciones (`Condition`) `conditionPilaLlena` y `conditionPilaVacia` se utilizan para coordinar los hilos y evitar el
acceso a la pila en situaciones no deseadas, como cuando la pila está llena o vacía. Los métodos `await()` y `signal()`
se utilizan para suspender y reanudar la ejecución de los hilos de manera segura.
