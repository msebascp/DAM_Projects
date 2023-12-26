# Tarea 1: Servidor de ECO

En esta tarea, se implementa una aplicación cliente-servidor utilizando sockets en Java para realizar el eco de
mensajes. El objetivo es crear un servidor (`EcoServidor`) que escuche en el puerto 6000 y convierta los mensajes
recibidos en mayúsculas, devolviéndolos al cliente. El cliente (`EcoCliente`) se conecta al servidor (localhost si está
en la misma máquina), envía mensajes al servidor y espera la respuesta de eco. La comunicación finaliza cuando el
servidor recibe la palabra "CIAO".

## Tecnologías de Sockets Utilizadas

- **Java Sockets:** Se utiliza la API de sockets de Java para establecer la comunicación entre el cliente y el servidor.

## Implementación del Servidor (EcoServidor)

El servidor espera mensajes del cliente y los devuelve en mayúsculas hasta que recibe la palabra "ciao". Utiliza
un `ServerSocket` para aceptar conexiones entrantes y maneja la entrada y salida de datos con un `DataInputStream` y
un `DataOutputStream`.

## Implementación del Cliente (EcoCliente)

El cliente se conecta al servidor utilizando un Socket, envía mensajes al servidor y espera la respuesta de eco. La
comunicación continúa hasta que el usuario escribe "ciao" y recibe "CIAO" del servidor.
