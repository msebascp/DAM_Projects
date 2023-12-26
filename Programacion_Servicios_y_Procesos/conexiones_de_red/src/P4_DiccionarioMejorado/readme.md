# Tarea 4: Diccionario Mejorado

En esta tarea, hemos mejorado el diccionario implementado anteriormente para hacerlo más versátil y permitir una
comunicación independiente entre el cliente y el servidor, independientemente de las máquinas en las que se estén
ejecutando. A continuación, se detallan los cambios y mejoras realizados:

## Cambios y Mejoras

- Independencia de Máquinas:
  Ahora, la conexión entre el cliente y el servidor es independiente de las máquinas en las que se están ejecutando.
  Esto se logra mediante la especificación de la dirección IP del servidor o el nombre de host al que el cliente se
  conectará.

- Múltiples Clientes:
  La aplicación permite más de un cliente conectado al servidor simultáneamente. Cada cliente puede realizar solicitudes
  de traducción de palabras al servidor de forma independiente.

- Búsqueda en el Diccionario:
  El servidor realiza búsquedas en el diccionario para las palabras proporcionadas por cualquier cliente. Si encuentra
  una coincidencia, devuelve la traducción correspondiente al cliente.

- Finalización del Cliente:
  El cliente continuará pidiendo al usuario palabras para traducir hasta que se escriba la palabra "stop". Este
  mecanismo proporciona una forma de finalizar la comunicación.
