package com.TeamPro.DAO;

import com.TeamPro.Model.*;
import javafx.collections.*;
import java.sql.*;
import java.util.*;


public class MySQL {
    // создаем подключение к базе данных
    // --> Printers
    private static final String ERROR = "\033[91m[EROR]\033[00m ";
    private static final String WARNING = "\033[93m[WARNING]\033[00m ";
    private static final String INFO = "\033[94m[INFO]\033[00m ";
    private static final String DEBUG = "\033[95m[DEBUG]\033[00m ";
    private static final String SUCCESS = "\033[92m[SUCCESS]\033[00m ";
    // <-- Printers

    // --> Table names
    private static final String USUARIOS = "usuarios";
    private static final String PRODUCTOS = "productos";
    private static final String CORTE_CAJA = "corte_caja";
    private static final String VENTA = "venta";
    private static final String CAJA = "caja";
    // <-- Table names

    // --> Table column names (for performance)
    private static final int USUARIO_NOMBRE = 1;
    private static final int USUARIO_CONTRASENA = 2;
    private static final int USUARIO_TIPO = 3;
    private static final int USUARIO_ID = 4;

    private static final int PRODUCTO_ID = 1;
    private static final int PRODUCTO_PRECIO = 2;
    private static final int PRODUCTO_DESC = 3;
    private static final int PRODUCTO_EXISTENCIA = 4;

    private static final int CORTE_CAJA_ID = 1;
    private static final int CORTE_CAJA_INICIO = 2;
    private static final int CORTE_CAJA_FINAL = 3;
    private static final int CORTE_CAJA_TOTAL = 4;
    private static final int CORTE_CAJA_ID_USUARIO = 5;

    private static final int VENTA_ID = 1;
    private static final int VENTA_MONTO = 2;
    private static final int VENTA_FECHA = 3;
    private static final int VENTA_ID_USUARIO = 4;
    private static final int VENTA_ID_CORTE = 5;

    private static final int CAJA_ID = 1;
    private static final int CAJA_ID_USUARIO = 2;
    private static final int CAJA_SADO = 3;
    // <-- Table column names (for performance)

    private Connection conn = null;

    /**
     * Crea la conexion a la base de datos
     * @return
     */
    public Connection conexion(){
        try {       //Cambiar los datos de acceso por los datos finales
            //this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventariosDB", "inventarios_client", "inventarios123");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unidad4", "elpapi", "Contrasen&a1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * Ejecuta una sentencia SELECT en la base de datos
     * @param tabla nombre de tabla
     * @param columna nombre de la/las columna/s
     * @param condicion condicion a evaluar
     * @return devuelve booleano
     */
    public boolean select(String tabla, String columna, String condicion){
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT "+columna+ " FROM "+tabla+" WHERE "+condicion);
            return rs.next(); //Devuelve true si encuentra registro
        } catch (SQLException throwables) {
            System.out.println("No existe registro");
            return false;
        }
    }

    /**
     * Ejecuta una sentencia SELECT en la base de datos
     * @param tabla nombre de tabla
     * @param columna nombre de la/las columna/s
     * @param condicion condicion a evaluar
     * @return retorna el tipo de usuario
     */
    public String getTipoUsuario(String tabla, String columna, String condicion){
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT "+columna+ " FROM "+tabla+" WHERE "+condicion);
            System.out.println("SELECT "+columna+ " FROM "+tabla+" WHERE "+condicion);
            while(rs.next()) {
                return rs.getObject("tipo").toString(); //Devuelve true si encuentra registro
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    /**
     * Inserta una fila en la base de datos
     * @param tabla nombre de la tabla
     * @param valores valores a insertar
     */
    public void insert(String tabla, String valores){
        try {
            Statement insert = this.conn.createStatement();
            insert.execute("INSERT INTO " + tabla + " VALUES("+valores+")");
            System.out.println("Usuario agregado");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("No se pudo agregar el usuario");
        }
    }

    public void update(){}
    public void delete(){}
    /**
     * Query the database and get a list of employees
     * @return {@link List<EmpleadoFX>}
     */
    public ObservableList<EmpleadoFX> getEmpleados(){
        ObservableList<EmpleadoFX> empleados = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + USUARIOS);
            while(rs.next())
                empleados.add(new EmpleadoFX(rs.getInt(USUARIO_ID), rs.getString(USUARIO_NOMBRE), rs.getString(USUARIO_CONTRASENA), rs.getString(USUARIO_TIPO)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleados;
    }
    /**
     * Query the database and get a list of products
     */
    public ObservableList<ProductoFX> getProductos(){
        ObservableList<ProductoFX> productos = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + PRODUCTOS);
            while(rs.next())
                productos.add(new ProductoFX(rs.getInt(PRODUCTO_ID), rs.getDouble(PRODUCTO_PRECIO), rs.getString(PRODUCTO_DESC), rs.getInt(PRODUCTO_EXISTENCIA)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productos;
    }

    /**
     * Query the database and Check the database and get a list of searched products
     */
    public ObservableList<ProductoFX> getProducto(String condicion){
        ObservableList<ProductoFX> productos = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + PRODUCTOS + " WHERE LOWER(descrpcion) = LOWER('" + condicion+"')");
            while(rs.next())
                productos.add(new ProductoFX(rs.getInt(PRODUCTO_ID), rs.getDouble(PRODUCTO_PRECIO), rs.getString(PRODUCTO_DESC), rs.getInt(PRODUCTO_EXISTENCIA)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productos;
    }

    //Main
    public static void main(String[] args) {
        System.out.println(MySQL.INFO);
        System.out.println(MySQL.WARNING);
        System.out.println(MySQL.ERROR);
        System.out.println(MySQL.SUCCESS);
        System.out.println(MySQL.DEBUG);
    }
}
