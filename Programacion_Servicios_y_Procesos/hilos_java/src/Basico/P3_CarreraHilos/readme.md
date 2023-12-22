# Tarea 3: Carrera de Hilos

## Descripción

Este programa Java implementa una carrera de hilos, donde tres hilos compiten por contar hasta 1000. Cada hilo tiene un
nombre (A, B y C) y cuenta de manera concurrente hasta 1000. El programa principal espera a que todos los hilos
terminen, mostrando la cuenta actual de cada hilo después de cada iteración con un intervalo de 100ms.

## Tecnologías de Hilos Utilizadas

El programa utiliza la implementación de hilos en Java mediante la extensión de la clase `Thread`.

### Clase `ContadorHilo`

La clase `ContadorHilo` extiende la clase `Thread` y representa los hilos que participan en la carrera.

