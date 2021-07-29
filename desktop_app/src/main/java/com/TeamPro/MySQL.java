package com.TeamPro;

import java.sql.*;


public class MySQL {

    private Connection conn = null;

    /**
     * Crea la conexion a la base de datos
     * @return
     */
    public Connection conexion(){
        try {       //Cambiar los datos de acceso por los datos finales
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
}
