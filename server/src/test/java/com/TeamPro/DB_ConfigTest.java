package com.TeamPro;

import java.sql.*;
import org.junit.*;

/**
 * Test that the DB is correctly configured.
 * To pass this test it is required that the DB is running and correctly configured.
 */
public class DB_ConfigTest {
    Connection conn;

    @Before
    public void setUp() {
        try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/inventariosDB", "inventarios_client", "inventarios123");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Connection to DB failed: check credentials in line 19 or that the DB is running.");
            System.out.println(Colors.toBlue("[INFO]") + " To run the DB try: sudo systemctl start mysql");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test that table usuarios exists.
     */
    @Test
    public void testUsuarios() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM usuarios");
            System.out.println(Colors.toGreen("[OK]") + " Table usuarios exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Table 'usuarios' does not exist.");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test that table productos exists.
     */
    @Test
    public void testProductos() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM productos");
            System.out.println(Colors.toGreen("[OK]") + " Table productos exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Table 'productos' does not exist.");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test that table corte_caja exists.
     */
    @Test
    public void testCorte_de_caja() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM corte_caja");
            System.out.println(Colors.toGreen("[OK]") + " Table corte_caja exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Table 'corte_caja' does not exist.");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test that table venta exists.
     */
    @Test
    public void testVentas() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM ventas");
            System.out.println(Colors.toGreen("[OK]") + " Table ventas exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Table 'ventas' does not exist.");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Tet that table caja exists.
     */
    @Test
    public void testCaja() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM caja");
            System.out.println(Colors.toGreen("[OK]") + " Table caja exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[FAIL]") + " Table 'caja' does not exist.");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test that table prueba exists.
     */
    @Test
    public void testPrueba() {
        try {
            conn.createStatement().executeQuery("SELECT * FROM prueba");
            System.out.println(Colors.toGreen("[OK]") + " Table prueba exists.");
        } catch (SQLException e) {
            System.out.println(Colors.toYellow("[WARNING]") + " Table 'prueba' does not exist, needed by ServerTest!!");
            System.out.println(Colors.toBlue("[INFO]") + " Table prueba will be created.");
            try {
                conn.createStatement().executeUpdate("CREATE TABLE prueba (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255))");
                System.out.println(Colors.toGreen("[OK]") + " Table prueba created.");
            } catch (SQLException e1) {
                System.out.println(Colors.toRed("[FAIL]") + " Table prueba could not be created.");
                System.out.println(Colors.toBlue("[INFO]") + " Create it manually by running: CREATE TABLE prueba (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255))");
                Assert.fail(e1.getMessage());
            }
        }
    }
}
