# Tarea 7: Serialización TCP. Usuario

En esta tarea, hemos creado una aplicación de serialización con dos clases: `DatosUsuarioServidor`
y `DatosUsuarioCliente`. A continuación, se detalla la implementación de ambas:

## DatosUsuarioServidor

La clase `DatosUsuarioServidor` representa el servidor que realiza las siguientes funciones:

- Abre un servidor en el puerto 6000.
- Cuando se conecta un cliente, crea un objeto de la clase `Usuario`, lo envía al cliente y espera la respuesta del
  cliente.
- Imprime en la consola la información del objeto `Usuario` recibido desde el cliente.

## DatosUsuarioCliente

La clase DatosUsuarioCliente representa el cliente que realiza las siguientes funciones:

- Se conecta al servidor y recibe un objeto Usuario.
- Pide al usuario real que introduzca el nombre de usuario y la contraseña.
- Envia el objeto de vuelta al servidor.
