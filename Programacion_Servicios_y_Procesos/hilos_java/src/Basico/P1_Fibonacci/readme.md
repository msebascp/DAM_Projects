# Tarea 1: Fibonacci

## Descripción

Este programa Java calcula y muestra la Sucesión de Fibonacci hasta el término especificado por el parámetro N. La
Sucesión de Fibonacci es una serie donde cada término es la suma de los dos anteriores.

## Tecnologías de Hilos Utilizadas

El programa utiliza el concepto de hilos en Java, que permite la ejecución concurrente de tareas. En este caso, se
implementa el manejo de hilos mediante la extensión de la clase `Thread`.

### Clase `Fibonacci`

La clase `Fibonacci` extiende la clase `Thread`, que es una de las formas de trabajar con hilos en Java. Al
extender `Thread`, la clase `Fibonacci` se convierte en un hilo ejecutable. Aquí se destacan algunas de las tecnologías
de hilos utilizadas:

- **`run()` Método Override:** El método `run()` contiene el código que se ejecutará en el hilo. En este caso, se
  calcula y muestra la Sucesión de Fibonacci.

- **Inicio del Hilo:** El hilo se inicia utilizando el método `start()`, que internamente llama al método `run()`.

