# Tarea 8: Banco Atómico

En esta tarea, se modifica la implementación del banco del tema anterior, utilizando Variables Atómicas para garantizar
operaciones atómicas sin necesidad de sincronización explícita.

## Clase `CuentaBancaria`

La clase `CuentaBancaria` representa la cuenta bancaria con un balance atómico.

### Atributos

- `balance`: `AtomicInteger` - Un entero atómico que almacena el saldo de la cuenta. Se utiliza `AtomicInteger` para
  permitir operaciones atómicas sin la necesidad de bloqueos explícitos.

- `lock`: `ReentrantLock` - Un candado reentrante utilizado para sincronizar operaciones críticas. Aunque las
  operaciones atómicas reducen la necesidad de bloqueos, se mantiene un candado para ciertas secciones críticas.

### Métodos

#### `CuentaBancaria(int balance)`

Constructor que recibe el saldo inicial de la cuenta y lo establece como un `AtomicInteger`.

#### `verBalance()`

Muestra el balance de la cuenta utilizando el método `get()` de `AtomicInteger`.

#### `meterDinero(int cantidad)`

Suma la cantidad indicada al balance de la cuenta de manera atómica utilizando `getAndAdd()` de `AtomicInteger`. Se
utiliza un candado para garantizar la exclusión mutua durante la operación.

#### `sacarDinero(int cantidad)`

Resta la cantidad indicada al balance de la cuenta de manera atómica utilizando `getAndAdd()` de `AtomicInteger` con un
valor negativo. Al igual que en `meterDinero`, se utiliza un candado para garantizar la exclusión mutua.

## Uso de Variables Atómicas

La implementación utiliza `AtomicInteger` para el balance de la cuenta. Las operaciones `getAndAdd()` de esta clase
proporcionan una manera segura y atómica de actualizar el saldo sin necesidad de utilizar bloqueos explícitos. El
candado `ReentrantLock` se mantiene para ciertas secciones críticas donde se necesita garantizar la exclusión mutua.
