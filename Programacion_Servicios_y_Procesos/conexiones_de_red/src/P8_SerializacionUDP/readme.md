# Tarea 8: Serialización UDP. Subasta

En esta tarea, hemos creado una aplicación de serialización con dos clases principales: `ServidorSubasta`,
y `ClienteSubasta`. A continuación, se detalla la implementación de ambas:

## ServidorSubasta

La clase ServidorSubasta representa el servidor de la subasta que realiza las siguientes funciones:

- Espera a que se conecten 3 clientes y les envía el objeto Producto.
- Espera las pujas de los clientes y determina el ganador.
- Rellena el objeto Producto con el precio final y el nombre del comprador.
- Envía el objeto Producto completo a los clientes.

## ClienteSubasta

La clase ClienteSubasta representa el cliente de la subasta que realiza las siguientes funciones:

- Se conecta al servidor y recibe el objeto Producto.
- Muestra por consola la información del producto.
- El usuario escribe en una línea su nombre y la puja (ej: Pepe 150).
- Envía la información al servidor y espera la resolución de la subasta.
- Recibe el objeto Producto completo con el resultado y lo muestra por consola.

