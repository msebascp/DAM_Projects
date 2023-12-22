# Tarea 8: Lavaplatos

En esta práctica, simulamos el proceso de lavar y secar platos en casa mediante el uso de hilos y sincronización. Se
definen las siguientes clases y métodos:

## Clase `Plato`

La clase `Plato` tiene un atributo entero para identificar distintos platos, denominado "numPlato".

## Clase `PilaDePlatos`

La clase `PilaDePlatos` representa una pila (cola LIFO) que almacena hasta 5 platos.

### Métodos:

- `lavarPlatos(Plato plato)`: Añade un plato a la pila si hay espacio. Si no hay espacio, espera a que haya espacio.

- `secarPlatos()`: Saca un plato de la pila si hay platos. Si no hay platos, espera a que haya platos.

Además, se utiliza la sincronización de métodos para evitar condiciones de carrera.

## Hilos

- **Hilo Lavador (`LavadorThread`):** Recibe un número N de platos y un objeto `PilaDePlatos`. El método `run` del hilo
  lavará y pondrá los N platos en la `PilaDePlatos`, con una pausa de 50 ms entre cada plato.

- **Hilo Secador (`SecadorThread`):** Recibe un número N de platos y un objeto `PilaDePlatos`. El método `run` del hilo
  secará y sacará los N platos de la `PilaDePlatos`, con una pausa de 50 ms entre cada plato.

## Clase `Main`

En el método `main` de la clase principal, se crea un objeto `PilaDePlatos` y se lanzan los hilos de tipo `Lavador`
y `Secador`. Estos hilos deben coordinarse para lavar y secar 20 platos.

Ejemplo de salida esperada:

- Lavado Plato #1, Platos en la Pila: 1
- Secado Plato #1, Platos en la Pila: 0
- Lavado Plato #2, Platos en la Pila: 1...

## Tecnologías Utilizadas

- **Hilos y Sincronización:** Se utilizan hilos para simular el lavado y secado de platos de forma concurrente. La
  sincronización se emplea para evitar condiciones de carrera al acceder a la pila de platos.
- **Deque y ArrayDeque:** Se utilizan las clases `Deque` y `ArrayDeque` de Java para implementar la estructura de pila (
  cola LIFO) para los platos.

La práctica demuestra el uso de hilos y sincronización para coordinar tareas concurrentes y evitar problemas de
concurrencia.
