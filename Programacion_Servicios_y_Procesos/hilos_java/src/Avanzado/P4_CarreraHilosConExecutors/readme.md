# Tarea 4: Carrera Executors

En esta tarea, se realiza una modificación en la carrera de hilos del tema anterior para permitir la elección del número
de hilos competidores en la carrera. Además, se inician los hilos utilizando un executor `Fixed` o `Cached`.

## Clase RunnerThreadMain

La clase `RunnerThreadMain` representa el programa principal que organiza una carrera de hilos.

### Métodos

#### main(String[] args)

El método principal `main` permite configurar y ejecutar la carrera de hilos con la opción de elegir el número de
competidores. Utiliza un `ThreadPoolExecutor` para gestionar los hilos de manera eficiente.

1. **Solicitud del Número de Hilos:** Se utiliza un `Scanner` para solicitar al usuario el número de hilos competidores.

2. **Creación de Hilos y Executor:** Se crean instancias de `RunnerThread` y se añaden al executor utilizando el
   método `execute`. El tipo de executor utilizado puede ser `Fixed` o `Cached`, dependiendo de la elección.

3. **Monitoreo de la Carrera:** Se utiliza un bucle para monitorear el progreso de la carrera, imprimiendo la posición
   de cada corredor. El bucle continúa hasta que todos los hilos han finalizado.

4. **Cierre del Executor:** Se apaga el executor después de que todos los hilos han terminado.

## Uso de Executors

En este ejemplo, se utiliza un `ThreadPoolExecutor` obtenido de `Executors.newCachedThreadPool()`. Este executor es
apropiado para un número variable de tareas y reutiliza hilos existentes para tareas nuevas cuando están disponibles. El
método `execute` se encarga de asignar una tarea a un hilo del pool.

Alternativamente, se podría utilizar `Executors.newFixedThreadPool(n)` para crear un executor de tamaño fijo, donde `n`
es el número de hilos en el pool. Esto garantiza que siempre haya exactamente `n` hilos ejecutando tareas en paralelo.

En ambos casos, la elección entre `Fixed` y `Cached` depende de los requisitos específicos y del comportamiento deseado
para la aplicación.
