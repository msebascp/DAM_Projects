# Tarea 6: Join Threads

## Descripción

Este programa Java modifica los hilos de la Tarea 3: Carrera de Hilos para que cada hilo espere a que termine el
anterior. En este caso, el hilo B espera a que termine el hilo A, y el hilo C espera a que termine el hilo B.

## Tecnologías de Hilos Utilizadas

El programa sigue utilizando la implementación de hilos en Java mediante la extensión de la clase `Thread`.

### Clase `ContadorHilo`

La clase `ContadorHilo` ha sido actualizada para incluir la lógica de esperar a que termine el hilo anterior antes de
iniciar su ejecución.

- **Nuevo Atributo `waitThread`:** Se ha añadido un nuevo atributo `waitThread` que representa al hilo al cual se espera
  antes de iniciar la ejecución.
   ```java
   private Thread waitThread = null;

```