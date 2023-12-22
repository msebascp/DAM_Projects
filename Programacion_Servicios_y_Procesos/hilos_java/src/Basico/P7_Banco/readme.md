# Tarea 7: Banco

## Descripción

Este proyecto Java simula un sistema bancario básico con las clases `CuentaBancaria`, `AhorrarThread`, `GastarThread`, y
la clase principal `Banco`. Los hilos `AhorrarThread` añaden 100€ a la cuenta del banco, mientras que los
hilos `GastarThread` sacan 100€ de la cuenta. Sin embargo, el banco no funciona adecuadamente debido a problemas de
concurrencia y sincronización. Por evitarlo se utiliza sincronización de métodos, y de objetos.

## Tecnologías de Hilos Utilizadas

El programa utiliza la implementación de hilos en Java mediante la extensión de la clase `Thread`.

### Clase `CuentaBancaria`

La clase `CuentaBancaria` representa la cuenta bancaria con un saldo (`balance`).

- **Método `seeBalance():`** Muestra el balance actual de la cuenta.
   ```java
   public void seeBalance() {
       System.out.println("Balance: " + balance);
   }

```
- **Método `putMoney():`** Añade dinero a la cuenta.
   ```java
   public void seeBalance() {
       System.out.println("Balance: " + balance);
   }
```

- **Método `takeMoney():`** Saca dinero cuenta.
   ```java
   public void seeBalance() {
       System.out.println("Balance: " + balance);
   }

```