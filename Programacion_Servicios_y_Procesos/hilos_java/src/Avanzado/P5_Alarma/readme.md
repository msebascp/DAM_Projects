# Tarea 5: Alarma

En esta tarea, se implementa una alarma programable que permite al usuario establecer un tiempo y una unidad de tiempo
para la alarma. La alarma emitirá avisos cuando falten 5 unidades de tiempo o menos.

## Clase AlarmaMain

La clase `AlarmaMain` representa el programa principal de la alarma.

### Métodos

#### main(String[] args)

El método principal `main` permite la interacción con el usuario para programar la alarma. Utiliza
un `ScheduledThreadPoolExecutor` para gestionar la ejecución de la alarma y los avisos.

1. **Entrada del Tiempo de la Alarma:** Se solicita al usuario que ingrese la cantidad de tiempo para la alarma y se
   resta la cantidad de tiempo para los avisos (5 unidades de tiempo).

2. **Selección de la Unidad de Tiempo:** Se utiliza el método `pedirUnidadTiempo` para que el usuario elija la unidad de
   tiempo en la que quiere programar la alarma.

3. **Creación de la Alarma y Avisos:** Se crean instancias de la clase `Alarma` y `Avisos`. La alarma se programa para
   sonar después del tiempo ingresado, y los avisos se programan para sonar periódicamente a partir de la cantidad de
   tiempo para avisos.

## Clase Avisos

La clase `Avisos` implementa la interfaz `Runnable` y se encarga de emitir los avisos de la alarma cuando falten 5
unidades de tiempo o menos.

### Métodos

#### run()

El método `run` se ejecuta periódicamente para verificar si quedan 5 unidades de tiempo o menos. Si es así, emite el
aviso correspondiente.

## Uso de ScheduledThreadPoolExecutor

En este ejemplo, se utiliza un `ScheduledThreadPoolExecutor` para programar la ejecución de la alarma y los avisos. La
alarma se programa con el método `schedule` para sonar después del tiempo ingresado, y los avisos se programan
con `scheduleAtFixedRate` para sonar periódicamente.

La elección de `ScheduledThreadPoolExecutor` proporciona una manera eficiente de gestionar la ejecución programada de
tareas en un entorno multiproceso. El executor se encarga automáticamente de la planificación y ejecución de las tareas
en los momentos deseados.
