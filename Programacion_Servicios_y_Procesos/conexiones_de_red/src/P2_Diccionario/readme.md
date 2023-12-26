# Tarea 2: Diccionario

En esta tarea, se implementa un diccionario simple utilizando sockets en Java. Se crean dos clases, un
cliente (`DiccionarioCliente`) y un servidor (`DiccionarioServidor`). El cliente envía al servidor una palabra escrita
por el usuario, y el servidor responde con la traducción de la palabra si existe en su diccionario (implementado como
un `HashMap`).

## Características Principales

### DiccionarioServidor

- Escucha en el puerto 2000.
- Contiene un diccionario (implementado como un `HashMap`) con algunas palabras en inglés y sus traducciones al español.
- Recibe la palabra enviada por el cliente y devuelve la traducción si existe en el diccionario.
- Si la palabra no se encuentra, no devuelve nada.

### DiccionarioCliente

- Solicita al usuario que escriba una palabra.
- Envia la palabra al servidor y espera la respuesta.
- Configura un timeout de 5 segundos. Si el servidor no responde en ese tiempo, muestra un mensaje de "Traducción no
  encontrada".

### Tecnologías de Sockets Utilizadas

Java Sockets: Se utiliza la API de sockets de Java para establecer la comunicación entre el cliente y el servidor.