# POO-Unidad-4-Actividad-1
## Actividad 7: Cajeros en Red y Web server

### Architecture  
It is possible to develop this project without a server that handles requests and has a connection to the database, instead each client makes direct requests to the database.  
Here arise a problem of authentication, how could a desktop app client connect if it cannot access the database and therefore make a new account request?; the easiest way to handle this is define that the admin is the only one capable of creating accounts; the other solution is to use a server.

### Requirements
* MySQL (tested with MariaDB 10.5.10)

### Execute
For starting the server is necessary to run MySQL with:
```
sudo systemctl start mysql
```
Once the server is run the desktop app can is the client that connects to the server

### Conceptos
#### Caja
* Atributos: Efectivo
* Metodos: Retirar y Depositar

Corte de caja. Comparar el dinero que hay (físico) contra el registrado en el programa.
* Atributos:
1. Caja (donde)
2. Usuario (quién)
3. Fecha y Hora (cuándo)


### Objetivo de la Práctica

El alumno deberá demostrar solura en los siguientes temas de la Programación Orientada a Objetos.
  * Programación para red
  * Manejo de métodos de procesamiento distribuido
  * Manejo de excepciones y errores.
  * Pruebas Unitarias
  * Interfaces gráficas
  * Paquetes

### Descripción de la práctica.

Se deberá implementar un sistema de gestión de inventarios en almacén, vinculado a un conjunto de cajas y una página de búsqueda de productos por parte del cliente.

Esta actividad comprende tres sub-sistemas que se encuentran interconectados entre si:

  1. *Sistema de gestión de inventarios*: Como su nombre lo indica es un sistema que mantiene una base de datos global que permite el manejo de inventarios en algún establecimiento o negocio, en él se tiene el ingreo y egreso de productos y las solicitudes periodicas y/o automáticas de productos hacia los proveedores. El sistema de inventarios mantiene una continua conexión con las cajas para concer el ingreo y devoluciones de productos.
  2. *Sistema de cajas*: Se tienen un conjunto de cajas de cobro que realizan el corte por cada usuario (cobrador), tienen la capacidad de realizar cobros de ventas de productos, así como sus devoluciones. Será un usuario "gerente" quien pueda realizar el corte de caja de todas las transacciones de todas las cajas durante ciertos periodos por ejemplo: al día, semanal, mensual.
  3. *Página web*: El sistema mantiene solicitudes http para la búsqueda de productos existentes en el almacen o tienda.

### Requerimientos Funcionales
Se deberá hacer uso de los siguientes temas:

  * Programación distribuida
  * Programación en red
  * Bases de datos
  * Manejo de excepciones
  * Excepciones propias
  * Propagación de errores.
  * Pruebas Unitarias
  * Pruebas de integración
  * Notación infija
  * Interfaces gráficas

### Requerimientos no funcionales

  * 

### Entregables:

  1. Código de la implementación documentado mediante JavaDoc.
  2. Se deberá generar el archivo Jar => **Se subirá a plataforma google classroom**
  3. Se deberá generar un reporte de actividades en formato PDF => **Se subirá a plataforma google classroom**
