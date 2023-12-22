# Tarea 1: Banco con Locks

En esta tarea, se modifica la implementación del banco del tema anterior para utilizar Locks en lugar de sincronización
de métodos.

El programa simula un banco con una cuenta bancaria compartida entre múltiples hilos. Se utilizan Locks para garantizar
la exclusión mutua y sincronizar el acceso a la cuenta bancaria. La cuenta tiene métodos para añadir y restar dinero, y
se crean hilos para ahorrar y gastar dinero coordinadamente.

## Tecnologías de Hilos Utilizadas

- **Locks (ReentrantLock):** Se utilizan Locks para garantizar la exclusión mutua y sincronizar el acceso a la cuenta
  bancaria.

## Clase CuentaBancaria

La clase `CuentaBancaria` representa la cuenta bancaria utilizada en el programa. Aquí se explican sus métodos y
atributos más relevantes.

### Atributos

- **balance (int):** Este atributo almacena el saldo actual de la cuenta bancaria.

- **lock (ReentrantLock):** Se utiliza un objeto `ReentrantLock` para implementar la sincronización mediante locks. Este
  lock garantiza la exclusión mutua, permitiendo que solo un hilo acceda a los métodos críticos a la vez.

### Métodos

### meterDinero(int cantidad)

El método `meterDinero` añade la cantidad especificada al saldo de la cuenta. Se utiliza un lock para garantizar la
exclusión mutua y evitar posibles condiciones de carrera.

### sacarDinero(int cantidad)

El método `sacarDinero` resta la cantidad especificada al saldo de la cuenta. Al igual que en `meterDinero`, se utiliza
un lock para garantizar la exclusión mutua y prevenir condiciones de carrera.

## Uso de Locks

La implementación de los métodos `meterDinero` y `sacarDinero` utiliza el lock (`ReentrantLock`) para asegurar la
exclusión mutua. Antes de realizar la operación crítica, se adquiere el lock con `lock.lock()`, y después de la
operación, se libera el lock con `lock.unlock()`. Esto evita que múltiples hilos modifiquen el saldo al mismo tiempo,
garantizando la coherencia y consistencia de los datos.

