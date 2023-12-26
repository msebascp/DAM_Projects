# Tarea 6: Chat Multicast

En esta tarea, hemos creado un proyecto llamado `MulticastChat` que implementa un chat utilizando el protocolo
multicast. Aquí se detalla la implementación:

## Proyecto MulticastChat

El proyecto `MulticastChat` consta de dos clases principales:

### Clase `MulticastChat`

La clase principal `MulticastChat` realiza las siguientes funciones:

- Pide al usuario que introduzca un nickname y se intenta conectar al grupo multicast.
- Permite al usuario introducir mensajes por la consola, los cuales se envían al resto de usuarios en el grupo.
- El formato de mensaje es "nickname: mensaje".
- Recibe y muestra en la consola los mensajes escritos por otros usuarios.
- Implementa un hilo que periódicamente recibe los mensajes del grupo y los muestra por pantalla.
- El chat finaliza cuando se envía el mensaje "ciao".

Este diseño permite una comunicación en tiempo real entre usuarios conectados al grupo multicast, proporcionando una
experiencia de chat interactiva.