# Tarea 5: Servidor Multicast

En esta tarea, hemos implementado una aplicación multicast con un servidor y varios clientes que se conectan al grupo
multicast. Aquí se detallan los proyectos involucrados y su funcionalidad:

## Servidor - `ServidorMulticastMensajes`

El servidor multicast, realiza las siguientes funciones:

- Pide mensajes de texto al usuario por consola.
- Envía los mensajes a todos los clientes conectados al grupo multicast.
- Finaliza cuando se ingresa el mensaje "stop".

## Cliente - ClienteMulticastMensajes

El cliente multicast, denominado ClienteMulticastMensajes, realiza las siguientes funciones:

- Se conecta al servidor multicast y muestra por consola cada mensaje recibido del servidor.
- Finaliza cuando recibe el mensaje "stop".

