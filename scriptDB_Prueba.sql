--Este script es de prueba, inicializa valores por defectuo para probar la app

---Add users
INSERT INTO usuarios(nombre, contraseña, tipo) VALUES ('administrador', 'administrador','administrador');
INSERT INTO usuarios(nombre, contraseña, tipo) VALUES ('gerente', 'gerente123','gerente');
INSERT INTO usuarios(nombre, contraseña, tipo) VALUES ('cajero1', 'cajero123','vendedor');
INSERT INTO usuarios(nombre, contraseña, tipo) VALUES ('cajero2', 'cajero123','vendedor');
INSERT INTO usuarios(nombre, contraseña, tipo) VALUES ('cajero2', 'cajero123','vendedor');

---Add products
INSERT INTO productos( precio, descrpcion, existencia) VALUES (89.99, 'Pizza de Luigis', 100);
INSERT INTO productos( precio, descrpcion, existencia) VALUES (20.10, 'Jabon ZOTE', 800);
INSERT INTO productos( precio, descrpcion, existencia) VALUES (450.00, 'Asador de Carne', 90);

---Add cajas
INSERT INTO caja(id_u, saldo_caja) VALUES (3, 100.00);
INSERT INTO caja(id_u, saldo_caja) VALUES (4, 100.00);
INSERT INTO caja(id_u, saldo_caja) VALUES (5, 100.00);

---Algunas Ventas. Todos los empleados tienen el mismo total de ventas
--- Cajero 1
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 3, 5, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 2, 4, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 2, 9, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (3, 1, 6, '2021-06-07');

--- Cajero 2
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 3, 5, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 2, 4, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 2, 9, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (4, 1, 6, '2021-06-07');

--- Cajero 3
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 3, 5, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 1, 2, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 2, 4, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 2, 9, '2021-06-07');
INSERT INTO ventas(id_u, id_p, cantidad, fecha) VALUES (5, 1, 6, '2021-06-07');

---Extra: estos SELECT fueron importantes para desarrollar la app
---SELECT todas las ventas de un usuario
SELECT * FROM ventas
WHERE ventas.id_u = <usuario_id>;


---SELECT todas las ventas con precios de un usuario
SELECT *
FROM ventas JOIN productos
ON ventas.id_p = productos.id
WHERE ventas.id_u = <usuario_id>
ORDER BY ventas.id;


---SELECT todas las ventas de un usuario (solo columnas importantes)
SELECT productos.descrpcion, ventas.cantidad, ventas.fecha, ventas.cantidad * productos.precio
FROM ventas JOIN productos
ON ventas.id_p = productos.id
WHERE ventas.id_u = <usuario_id>
ORDER BY ventas.id;


--SELECT total de ventas de un usuario
SELECT SUM(productos.precio * ventas.cantidad)
FROM ventas JOIN productos
ON ventas.id_p = productos.id
WHERE ventas.id_u = <usuario_id>
