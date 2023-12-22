# Tarea 4: Carrera de la Muerte

## Descripción

Este programa Java es una modificación de la tarea anterior, donde se implementa una carrera de hilos. En esta versión,
cuando un hilo alcanza la marca de 1000, se comprueba en el programa principal y se solicita la finalización de todos
los procesos restantes. El código ha sido adaptado para incluir esta nueva funcionalidad.

## Tecnologías de Hilos Utilizadas

El programa sigue utilizando la implementación de hilos en Java mediante la extensión de la clase `Thread`.

### Clase `ContadorHilo`

La clase `ContadorHilo` extiende la clase `Thread` y representa los hilos que participan en la carrera.

- **Añadido Método `setFinish(boolean finish):`** Este método permite cambiar el estado de la variable `finish`, que se
  utiliza para finalizar el hilo cuando alcanza 1000 o cuando se decide detener la carrera.
   ```java
   public void setFinish(boolean finish) {
       this.finish = finish;
   }

```