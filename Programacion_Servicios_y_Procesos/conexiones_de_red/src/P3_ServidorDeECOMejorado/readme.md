# Tarea 3: Servidor de Eco Mejorado

En esta tarea, se mejora la Tarea 1, haciendo que la conexión cliente-servidor sea independiente de las máquinas en las
que están ejecutándose. Se pide al usuario por consola la IP del servidor o el nombre de host al que conectarse. Además,
se permite que más de un cliente esté conectado al servidor, y este debe hacer eco de los mensajes de diferentes
clientes, enviándoles a cada uno sus propias respuestas.

## Características Principales

### EcoServidorMejorado

- Escucha en el puerto 6000.
- Acepta conexiones de múltiples clientes.
- Crea un hilo (`SocketThread`) para manejar cada conexión de cliente.

### EcoClienteMejorado

- Solicita al usuario la dirección IP y el puerto del servidor.
- Establece la conexión con el servidor.
- Envia mensajes al servidor y espera la respuesta.