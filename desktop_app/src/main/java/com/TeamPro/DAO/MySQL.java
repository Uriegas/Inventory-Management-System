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
    private static final String VENTAS = "ventas";
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
    private static final int VENTA_ID_USUARIO = 2;
    private static final int VENTA_ID_PRODUCTO = 3;
    private static final int VENTA_CANTIDAD = 4;
    private static final int VENTA_FECHA = 5;

    private static final int CAJA_ID = 1;
    private static final int CAJA_ID_USUARIO = 2;
    private static final int CAJA_SALDO = 3;
    // <-- Table column names (for performance)

    // --> Default Credentials
    private String host = "jdbc:mysql://localhost/inventariosDB";
    private String user = "inventarios_client";
    private String password = "inventarios123";
    public static Connection conn = null;
    // <-- Default Credentials
    private static EmpleadoFX currentUser;

    // ============================ Conexiones ============================
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
     * Change conexion
     * @param Connection conn
     */
    public void setConexion(Connection conn) {
        this.conn = conn;
    }
    /**
     * @return {@link Connection}
     */
    public Connection getConexion() {
        return this.conn;
    }
    /**
     * Change conexion
     * @param String host
     * @param String user
     * @param String password
     * @throws SQLException after connection failure try reconnect with previous credentials, if this fails nothing can be done
     */
    public void setConexion(String host, String user, String password) throws SQLException {
        this.conexion(host, user, password);
        System.out.println(SUCCESS + "Conexion exitosa");
        //If connection is successful, change credentials
        this.host = host;
        this.user = user;
        this.password = password;
    }
    // ============================ Current User ============================
    /**
     * Set the current user
     * @param EmpleadoFX currentUser
     */
    public void setCurrentUser(EmpleadoFX currentUser) {
        MySQL.currentUser = currentUser;
    }
    /**
     * Get the current user
     * @return EmpleadoFX
     */
    public EmpleadoFX getCurrentUser() {
        return MySQL.currentUser;
    }

    // ============================ Last ID ============================
    /**
     * Get the current user's id
     */
    public int getLastID(String table) throws SQLException {
        Statement select = this.conn.createStatement();
        // Get the id of this employee
        ResultSet rs = select.executeQuery("SELECT AUTO_INCREMENT - 1 FROM information_schema.tables WHERE table_name = '" + table + "'AND table_schema = DATABASE( );");
        rs.next();
        return rs.getInt(1);
    }
    /**
     * Get last id in USUARIOS table
     * @return last inserted id in USUARIOS table
     */
    public int getLastIDUsuarios() throws SQLException {
        return getLastID(USUARIOS);
    }
    /**
     * Get last id in PRODUCTOS table
     * @return last inserted id in PRODUCTOS table
     */
    public int getLastIDProductos() throws SQLException {
        return getLastID(PRODUCTOS);
    }
    /**
     * Get last id in CORTE_CAJAS table
     * @return last inserted id in CORTE_CAJAS table
     */
    public int getLastIDCorteCaja() throws SQLException {
        return getLastID(CORTE_CAJAS);
    }
    /**
     * Get last id in VENTAS table
     * @return last inserted id in VENTAS table
     */
    public int getLastIDVentas() throws SQLException {
        return getLastID(VENTAS);
    }
    /**
     * Get last id in CAJAS table
     * @return last inserted id in CAJAS table
     */
    public int getLastIDCajas() throws SQLException {
        return getLastID(CAJAS);
    }

    // ============================ SELECT's ============================
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
     * Find an employee by its password, not recomended using this method because passwords are not unique
     */
    public EmpleadoFX getEmpleado(EmpleadoFX empleado) {
        for(EmpleadoFX emp : getEmpleados()){
            System.out.println(MySQL.INFO + emp.toString());
            if(empleado.equalsVerification(emp))
                return emp;
        }
        throw new IllegalArgumentException("No existe el empleado");
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
    /**
     * Query the database and get a list of sales
     */
    public ObservableList<VentaFX> getVentas(){
        ObservableList<VentaFX> ventas = FXCollections.observableArrayList();
        try {
            Statement select = this.conn.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM " + VENTAS);
            while(rs.next())
                ventas.add( new VentaFX(rs.getInt(VENTA_ID), rs.getInt(VENTA_ID_USUARIO), rs.getInt(VENTA_ID_PRODUCTO), rs.getInt(VENTA_CANTIDAD), rs.getDate(VENTA_FECHA)) );
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
    // ============================ INSERT's ============================
    /**
     * Inserta una fila en la base de datos
     * @param tabla nombre de la tabla
     * @param valores valores a insertar
     */
    public void insert(String tabla, String valores) throws SQLException {
        Statement insert = this.conn.createStatement();
        insert.execute("INSERT INTO " + tabla + " VALUES(0,"+valores+")");
        System.out.println(MySQL.INFO + "Elemento agregado");
    }
    /**
     * Inserts a new user in the database
     * @param EmpleadoFX empleado to insert
     * @return {@link EmpleadoFX} the newly inserted user with the id
     */
    public EmpleadoFX insert(EmpleadoFX empleado) throws SQLException {
        insert(USUARIOS, empleado.toINSERT() );
        return new EmpleadoFX(getLastIDUsuarios(), empleado.getNombre(), empleado.getContraseña(), empleado.getTipo());
    }
    /**
     * Inserts a new product in the database
     * @param producto producto a insertar
     * @return {@link ProductoFX} the newly inserted product with the id
     */
    public ProductoFX insert(ProductoFX producto) throws SQLException {
        insert(PRODUCTOS, producto.toINSERT() );
        return new ProductoFX(getLastIDProductos(), producto.getPrecio(), producto.getNombre(), producto.getStock());
    }
    /**
     * Inserts a new corte_caja in the database
     * @param corteCaja corte de caja a insertar
     * @return {@link CorteCajaFX} the newly inserted corte_caja with the id
     */
    public CorteCajaFX insert(CorteCajaFX corte) throws SQLException {
        insert(CORTE_CAJAS, corte.toINSERT() );
        return new CorteCajaFX(getLastIDCorteCaja(), corte.getInicio(), corte.getFin(), corte.getTotal(), corte.getIdUsuario());
    }
    /**
     * Inserts a new venta in the database
     * @param venta venta a insertar
     * @return {@link VentaFX} the newly inserted venta with the id
     */
    public VentaFX insert(VentaFX venta) throws SQLException {
        insert(VENTAS, venta.toINSERT() );
        return new VentaFX(getLastIDVentas(), venta.getid_usuario(), venta.getid_producto(), venta.getcantidad(), venta.getFecha());
    }
    /**
     * Inserts a new caja in the database
     * @param caja caja a insertar
     * @return {@link CajaFX} the newly inserted caja with the id
     */
    public CajaFX insert(CajaFX caja) throws SQLException {
        insert(CAJAS, caja.toINSERT() );
        return new CajaFX(getLastIDCajas(), caja.getIdUsuario(), caja.getSaldo());
    }

    // ============================ UPDATE's ============================
    public void update(String tabla, String valores, int id) throws SQLException {
        Statement update = this.conn.createStatement();
        update.execute("UPDATE " + tabla + " SET " + valores + " WHERE id = "+id);
        System.out.println(MySQL.INFO + "Elemento agregado");
    }
    /**
     * Updates a user in the database
     * @param empleado empleado a actualizar
     * @deprecated
     */
    public void update(EmpleadoFX empleado) throws SQLException {
        update(USUARIOS, empleado.toUPDATE(), empleado.getId());
    }
    /**
     * Update a product in the database
     * @param producto producto a actualizar
     * @deprecated
     */
    public void update(ProductoFX producto) throws SQLException {
        update(PRODUCTOS, producto.toUPDATE(), producto.getId());
    }
    /**
     * Updates a user in the database
     * @param empleado empleado a actualizar
     * @param newEmpleado nuevos valores del empleado
     */
    public void update(EmpleadoFX empleado, EmpleadoFX newEmpleado) throws SQLException {
        update(USUARIOS, newEmpleado.toUPDATE(), empleado.getId());
    }
    /**
     * Updates a product in the database
     * @param producto producto a actualizar
     * @param newEmpleado nuevos valores del producto
     */
    public void update(ProductoFX producto,ProductoFX newProducto) throws SQLException {
        update(PRODUCTOS, newProducto.toUPDATE(), producto.getId());
    }
    /**
     * Updats a corte_caja in the database
     * @param corteCaja corte de caja a actualizar
     * @param newCorte nuevos valores del corte de caja
     */
    public void update(CorteCajaFX corte, CorteCajaFX newCorte) throws SQLException {
        update(CORTE_CAJAS, newCorte.toUPDATE(), corte.getId());
    }
    /**
     * Updates a venta in the database
     * @param venta venta a actualizar
     * @param newVenta nuevos valores de la venta
     */
    public void update(VentaFX venta, VentaFX newVenta) throws SQLException {
        update(VENTAS, venta.toUPDATE(), venta.getId());
    }
    /**
     * Updates a caja in the database
     * @param caja caja a actualizar
     * @param newCaja nuevos valores de la caja
     */
    public void update(CajaFX caja, CajaFX newCaja) throws SQLException {
        update(CAJAS, caja.toUPDATE(), caja.getId());
    }

    // ============================ DELETE's ============================
    public void delete(String tabla, int id) throws SQLException {
        Statement delete = this.conn.createStatement();
        delete.execute("DELETE FROM " + tabla + " WHERE id = "+id);
        System.out.println(MySQL.INFO + "Elemento " + id + " eliminado de " + tabla);
    }
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
    public void delete(CorteCajaFX corte) throws SQLException {
        delete(CORTE_CAJAS, corte.getId());
    }
    /**
     * Deletes a venta in the database, find it and delete it
     * @param venta venta a borrar
     */
    public void delete(VentaFX venta) throws SQLException {
        delete(VENTAS, venta.getId());
    }
    /**
     * Deletes a caja in the database, find it and delete it
     * @param caja caja a borrar
     */
    public void delete(CajaFX caja) throws SQLException {
        delete(CAJAS, caja.getId());
    }

    // ============================ Extra ============================
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
     * This method executes the following query:<p>
     * {@code SELECT caja.id, usuarios.nombre, caja.saldo_caja FROM caja INNER JOIN usuarios ON caja.id_u = usuarios.id WHERE caja.id = <id>;}
     * @param id id of the caja
     * @return list of query
     */
    public ObservableList<CajaWithEmp> getCajaswithEmpleados() throws SQLException {
        ObservableList<CajaWithEmp> cajas = FXCollections.observableArrayList();
        Statement select = this.conn.createStatement();
        ResultSet rs = select.executeQuery("SELECT caja.id, usuarios.id, usuarios.nombre, caja.saldo_caja FROM caja INNER JOIN usuarios ON caja.id_u = usuarios.id;");
        while(rs.next())
            cajas.add( new CajaWithEmp(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)) );
        return cajas;
    }
    /**
     * Get the total sales in money of an employee (cajero)
     * This method executes the following query:<p>
     * {@code SELECT SUM(productos.precio * ventas.cantidad) FROM ventas JOIN productos ON ventas.id_p = productos.id WHERE ventas.id_u = <usuario_id>}
     * @return the sum of product * cantidad for each sale of the employee
     */
    public double getTotalSales(int usuario_id) throws SQLException {
        Statement select = this.conn.createStatement();
        ResultSet rs = select.executeQuery("SELECT SUM(productos.precio * ventas.cantidad) FROM ventas JOIN productos ON ventas.id_p = productos.id WHERE ventas.id_u = "+usuario_id + ";");
        rs.next(); //It is a one row query we don't need to iterate
        return rs.getDouble(1);
    }
    /**
     * Get the list of sales of an employee (cajero) to display in the GUI
     * This method executes the following query:<p>
     * {@code SELECT * FROM ventas JOIN productos ON ventas.id_p = productos.id WHERE ventas.id_u = 4 ORDER BY ventas.id;}
     * @param usuario_id id of the employee
     * @return the list of sales of the employee
     */
    public ObservableList<VentaWithProducto> getVentaswithEmpleados(int usuario_id) throws SQLException {
        ObservableList<VentaWithProducto> ventas = FXCollections.observableArrayList();
        Statement select = this.conn.createStatement();
        ResultSet rs = select.executeQuery("SELECT ventas.id, ventas.id_u, ventas.id_p, ventas.cantidad, ventas.fecha, productos.precio, productos.descrpcion, ventas.cantidad * productos.precio FROM ventas JOIN productos ON ventas.id_p = productos.id WHERE ventas.id_u =" +usuario_id + " ORDER BY ventas.id;");
        while(rs.next())
            ventas.add( new VentaWithProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getDouble(6), rs.getString(7), rs.getDouble(8)) );
        return ventas;
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
