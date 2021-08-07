# DB Setup 
======================

Esta es una guia para configurar la base de datos necesaria para que el [server](server/src/main/java/com/TeamPro/Server.java) funcione.

## Instalación 

1.- Descargar el archivo de insalacion MSI Installer de MySql Community

2.- Ejecutar el fichero descargado para ir a las pantallas de instalacion

3.- Elegir el tipo de instalacion, la opcion "Developer Default" nos instalara tanto el servidor mysql como el cliente Mysqlworkbench(entorno grafico desde el que podemos crear esquemas y objetos)

4.- En la ventana en la que nos pide indicar la contrasena para el usuario root (usuario con privilegios de administracion) podemos agregar otra cuenta desde el boton "Add User"

## Configuración Usuarios

1.- Seleccionamos la opcion de add user en la pantalla de configuracion de usuario root de la instalacion

2.- Damos el nombre de usuario "inventarios_client"

3.- Asignamos el host y rol del usuario 

4.- establecemos una contrasena

5.- El usuario puede ser creado tambien desde la consola de mysql

6.- desde la consola de nuestro SO usamos el comando "mysql" lo que nos  devolvera un resultado que confimra que accedimos al servidor mysql

7.- usamos el comando "CREATE USER 'nuevo_usuario'@'localhost' IDENTIFIED BY 'contrasena';" para crear un nuevo usuario 

8.- Y con el comando "GRANT ALL PRIVILEGES ON * . * TO 'nuevo_usuario'@'localhost';
" le otorgamos todos los privilegios

Explicación en documentación de [Server.java](server/src/main/java/com/TeamPro/Server.java)

## Creación Tablas

Como crear un esquema y los objetos de nuestra base de datos con ayuda de mysql workbench

1.-Abrir mysqlworkbench

2.-Abrir la conexion creada durante la instalacion

3.-crear un nuevo archivo para ejecutar consultas sql

4.-copiar y pegar el script "scriptDB" a excepcion de la tabla "prueba"

5.- Seleccionar todo el script y dar clic en el boton "Execute the selected portion of the script" (icono de rayo).

6.- Refrescar el explorador de esquemas ubicado del lado izquierdo (debere verse creado el esquema y la stablas del script)