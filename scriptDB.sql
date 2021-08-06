create database inventariosDB;

use inventariosDB;

create or replace table usuarios(
id int not null primary key AUTO_INCREMENT,
nombre varchar(80) not null,
contraseña varchar(30) not null,
tipo varchar(30) not null
);

create or replace table productos(
id int not null primary key AUTO_INCREMENT,
precio decimal(10,2) not null,
descrpcion varchar(100) not null,
existencia int(5) not null
);

create or replace table caja(
id int not null primary key AUTO_INCREMENT,
id_u int not null,
saldo_caja int not null,
CONSTRAINT fk_caja_usuario FOREIGN KEY (id_u) REFERENCES usuarios (id) ON DELETE CASCADE
);

create or replace table corte_caja(
id int not null primary key AUTO_INCREMENT,
inicio date  not null,
fin date  not null,
total decimal(10,2) not null,
id_u int not null,
id_c int not null,
CONSTRAINT fk_corte_gerente FOREIGN KEY (id_u) REFERENCES usuarios (id) ON DELETE CASCADE,
CONSTRAINT fk_fromcaja_corte FOREIGN KEY (id_c) REFERENCES caja (id) ON DELETE CASCADE
);

create or replace table ventas (
id int not null primary key AUTO_INCREMENT,
id_u int not null,
id_p int not null,
cantidad int not null,
fecha date not null,
CONSTRAINT fk_producto FOREIGN KEY (id_p) REFERENCES productos (id) ON DELETE CASCADE
);



---Tabla de prueba (necesaria solo para tests)
create or replace table prueba(
    id int not null primary key,
    value varchar(30)
);
