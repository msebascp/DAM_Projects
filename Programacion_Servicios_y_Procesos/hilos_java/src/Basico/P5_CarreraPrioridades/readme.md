# Tarea 5: Carrera con Prioridades

## Descripción

Este programa Java es una modificación de la tarea anterior, ahora implementando una carrera de hilos con prioridades.
Se establecen distintas prioridades para los hilos y se comparan los resultados.

## Tecnologías de Hilos Utilizadas

El programa sigue utilizando la implementación de hilos en Java mediante la extensión de la clase `Thread`.

### Clase `ContadorHilo`

La clase `ContadorHilo` ha sido actualizada para incluir la lógica de establecer prioridades y comparar los resultados
con distintos sistemas operativos.

- **Nuevo Constructor con Prioridad:** Se ha añadido un nuevo constructor que acepta la prioridad como parámetro y
  establece la prioridad del hilo actual.
   ```java
   public ContadorHilo(int priority) {
       if (priority < 1) {
           Thread.currentThread().setPriority(1);
       } else Thread.currentThread().setPriority(Math.min(priority, 10));
   }
   ```
