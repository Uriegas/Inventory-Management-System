package com.TeamPro.DAO;

import com.TeamPro.Model.*;
import javafx.collections.*;
import java.sql.*;
/**
 * Handles all the database operations
 */
public class MySQL {

    // создаем подключение к базе данных
    // --> Printers
    public static final String ERROR = "\033[91m[EROR]\033[00m ";
    public static final String WARNING = "\033[93m[WARNING]\033[00m ";
    public static final String INFO = "\033[94m[INFO]\033[00m ";
    public static final String DEBUG = "\033[95m[DEBUG]\033[00m ";
    public static final String SUCCESS = "\033[92m[SUCCESS]\033[00m ";
    // <-- Printers

    // --> Table names
    private static final String USUARIOS = "usuarios";
    private static final String PRODUCTOS = "productos";
    private static final String CORTE_CAJAS = "corte_caja";
    private static final String VENTAS = "venta";
    private static final String CAJAS = "caja";
    // <-- Table names

    // --> Table column names (for performance)
    private static final int USUARIO_ID = 1;
    private static final int USUARIO_NOMBRE = 2;
    private static final int USUARIO_CONTRASENA = 3;
    private static final int USUARIO_TIPO = 4;

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
    private static final int CAJA_SALDO = 3;
    // <-- Table column names (for performance)

    // --> Default Credentials
    private String host = "jdbc:mysql://localhost/inventariosDB";
    private String user = "inventarios_client";
    private String password = "inventarios123";
    private Connection conn = null;
    // <-- Default Credentials

    /**
     * Default constructor<p>
     * You shoud make the connection manually with
     * {@link DAO.MySQL#connect(String, String, String)}
     */
    public MySQL() {
    }
    /**
     * Connection constructor
     * @param String host
     * @param String user
     * @param String password
     */
    public MySQL(String host, String user, String password) throws SQLException {
        conexion(host, user, password);
    }
    /**
     * Specific conection
     * @param String host
     * @param String user
     * @param String password
     * @return {@link Connection}
     */
    public Connection conexion(String host, String user, String password) throws SQLException {
        this.host = host;
        this.user = user;
        this.password = password;
        return conexion();
    }
    /**
     * Crea la conexion a la base de datos
     * @return {@link Connection}
     */
    public Connection conexion() throws SQLException {
        return this.conn = DriverManager.getConnection(host, user, password);
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
    public void insert(String tabla, String valores) throws SQLException {
        Statement insert = this.conn.createStatement();
        insert.execute("INSERT INTO " + tabla + " VALUES(0,"+valores+")");
        System.out.println("Elemento agregado");
    }

    public void update(String tabla, String valores, Integer id) throws SQLException {
        Statement update = this.conn.createStatement();
        update.execute("UPDATE " + tabla + " SET " + valores + " WHERE id = "+id);
        System.out.println("Elemento agregado");
    }
    public void delete(String tabla, Integer id) throws SQLException {
        Statement delete = this.conn.createStatement();
        delete.execute("DELETE FROM " + tabla + " WHERE id = "+id);
        System.out.println("Elemento eliminado");
    }
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
     * Query the database and get a list of searched employees
     * @return {@link List<EmpleadoFX>}
     */
    public ObservableList<EmpleadoFX> getEmpleado(String condicion){
        ObservableList<EmpleadoFX> empleados = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + USUARIOS + " WHERE LOWER(nombre) = LOWER('" + condicion+"')");
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
     * Query the database and get a list of sales
     */
    public ObservableList<VentaFX> getVentas(){
        ObservableList<VentaFX> ventas = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + VENTAS);
            while(rs.next())
                ventas.add( new VentaFX(rs.getInt(VENTA_ID), rs.getInt(VENTA_ID_USUARIO), rs.getInt(VENTA_ID_CORTE), rs.getDouble(VENTA_MONTO), rs.getDate(VENTA_FECHA)) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ventas;
    }
    /**
     * Query the database and get a list of cajas
     */
    public ObservableList<CajaFX> getCajas(){
        ObservableList<CajaFX> cajas = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + CAJAS);
            while(rs.next())
                cajas.add( new CajaFX(rs.getInt(CAJA_ID), rs.getInt(CAJA_ID_USUARIO), rs.getDouble(CAJA_SALDO)) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cajas;
    }

    /**
     * Query the database and get a list of corte de cajas
     */
    public ObservableList<CorteCajaFX> getCortes(){
        ObservableList<CorteCajaFX> cortes = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + CORTE_CAJAS);
            while(rs.next())
                cortes.add( new CorteCajaFX(rs.getInt(CORTE_CAJA_ID), rs.getDate(CORTE_CAJA_INICIO), rs.getDate(CORTE_CAJA_FINAL), rs.getDouble(CORTE_CAJA_TOTAL), rs.getInt(CORTE_CAJA_ID_USUARIO)) );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cortes;
    }
    // --> Inserts
    /**
     * Inserts a new user in the database
     * @param nombre nombre del usuario
     */
    public void insert(EmpleadoFX empleado) throws SQLException {
        insert(USUARIOS, empleado.toINSERT() );
    }
    /**
     * Inserts a new product in the database
     * @param producto producto a insertar
     */
    public void insert(ProductoFX producto) throws SQLException {
        insert(PRODUCTOS, producto.toINSERT() );
    }
    /**
     * Inserts a new corte_caja in the database
     * @param corteCaja corte de caja a insertar
     */
    public void insert(CorteCajaFX corte){}
    /**
     * Inserts a new venta in the database
     * @param venta venta a insertar
     */
    public void insert(VentaFX venta){}
    /**
     * Inserts a new caja in the database
     * @param caja caja a insertar
     */
    public void insert(CajaFX caja){}
    // <-- Inserts

    // --> Updates
    /**
     * Updates a user in the database
     * @param empleado empleado a actualizar
     */
    public void update(EmpleadoFX empleado) throws SQLException {
        update(USUARIOS, empleado.toUPDATE(), empleado.getId());
    }
    /**
     * Updates a product in the database
     * @param producto producto a actualizar
     */
    public void update(ProductoFX producto) throws SQLException {
        update(PRODUCTOS, producto.toUPDATE(), producto.getId());
    }
    /**
     * Updats a corte_caja in the database
     * @param corteCaja corte de caja a actualizar
     */
    public void update(CorteCajaFX corte){}
    /**
     * Updates a venta in the database
     * @param venta venta a actualizar
     */
    public void update(VentaFX venta){}
    /**
     * Updates a caja in the database
     * @param caja caja a actualizar
     */
    public void update(CajaFX caja){}
    // <-- Updates

    // --> Deletes
    /**
     * Deletes a user in the database, find it and delete it
     * @param empleado empleado a borrar
     */
    public void delete(EmpleadoFX empleado) throws SQLException {
        delete(USUARIOS, empleado.getId());
    }
    /**
     * Deletes a product in the database, find it and delete it
     * @param producto producto a borrar
     */
    public void delete(ProductoFX producto) throws SQLException {
        delete(PRODUCTOS, producto.getId());
    }
    /**
     * Deletes a corte_caja in the database, find it and delete it
     * @param corteCaja corte de caja a borrar
     */
    public void delete(CorteCajaFX corte){}
    /**
     * Deletes a venta in the database, find it and delete it
     * @param venta venta a borrar
     */
    public void delete(VentaFX venta){}
    /**
     * Deletes a caja in the database, find it and delete it
     * @param caja caja a borrar
     */
    public void delete(CajaFX caja){}
    // <-- Deletes

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
