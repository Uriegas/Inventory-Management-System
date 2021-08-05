package com.TeamPro;

import java.io.Console;
import java.sql.*;
import java.util.*;

/**
 * <h2>Server class</h2>
 * This server creates a connection to a database and handles requests from the web or a desktop client app.
 * Before running the server, make your the database is running: <p>
 * {@code systemctl status mysql}<p>
 * Otherwise run the following command: <p>
 * {@code systemctl start mysql}<p>
 * For the server to run it must be installed a MYSQL database.<p>
 * First, create the database:<p>
 * {@code CREATE DATABSE IF NOT EXISTS inventariosDB;}<p>
 * Then, create the user by running the following command in the MySQL console:
 * {@code CREATE USER 'inventarios_client'@'localhost' IDENTIFIED BY 'inventarios123';} <p>
 * and grant it permissions to the inventoriosDB with the following command:
 * {@code GRANT SELECT,INSERT, UPDATE, DELETE ON inventariosDB.* TO 'inventarios_client'@'localhost';}<p>
 * 
 * TODO: remove the grant permissions to ALL the tables in the database to only the corresponding tables.
 * 
 * To verify that the user was created correctly and granted the permissions, run the following command:
 * {@code mysql -u inventarios_client -p} <p>
 * Then, see if the database was created correctly:
 * {@code SHOW DATABASES;}<p>
 * The inventariosDB should be present in the list.
 * // --> Ignore this
 * To verify your database credentials for admin run the following code: {@code mysql -u root -p},
 * then enter the password, finally verify that the database exist ({@code SHOW DATABSES}) or create them.
 * Otherwise in a fresh installation you will need to create a user and password. 
 * // <-- Ignore this
 * 
 * <h3>Create tables</h3>
 * First, change the using database with:
 * {@code USE inventariosDB;}<p>
 * Then create a test table and check if it was created correctly:<p>
 * {@code CREATE TABLE prueba(id int not null, var varchar(255));}<p>
 * {@code SHOW TABLES;}<p>
 * {@code INSERT INTO prueba(id, var) VALUES(1, 'hola');}<p>
 * {@code INSERT INTO prueba(id, var) VALUES(2, 'como');}<p>
 * 
 * Running the following command will show the table:
 * {@code SELECT * FROM prueba;}<p>
 * 
 * @author Eduardo Uriegas
 */
public class Server{
    // --> Column names (instead of strings for performance)
    // private static final int VAR = 1;
    // private static final int NAME = 2;
    // <-- Column names

    private static final String DB = "jdbc:mysql://localhost/inventariosDB";
    private static String USER = "inventarios_client";
    private static String PASSWORD = "inventarios123";
    private Connection connection;
    /**
     * Create a new server and connect to the database.
     * @param USER
     * @param PASSWORD
     */
    public Server(String USER, String PASSWORD) throws SQLException {
        Server.USER = USER;
        Server.PASSWORD = PASSWORD;
        connection = DriverManager.getConnection(DB, USER, PASSWORD);
    }
    /**
     * Default constructor
     */
    public Server() throws SQLException {
        this("inventarios_client", "inventarios123");
    }
    /**
     * Start the server. Create a connection to the database and wait for requests.
     */
    public void run() throws SQLException {
        // --> Create connection to the database
        System.out.println("Starting server...");
        connection = DriverManager.getConnection(DB, USER, PASSWORD);
        System.out.println(Colors.toGreen("[OK]") + " Connected to database");

        Console console = System.console();
        // --> Query loop
        while(true) {
            System.out.print("Enter a query statement: ");
            String query = console.readLine();
            if(query.equals("exit"))
                break;
            // --> Execute query
            try {
                HashMap<String, List<String>> result = query(query);
                // --> Print result
                System.out.println("Result: " + result);
            } catch (SQLException e) {
                System.out.println(Colors.toRed("[ERROR]") + " " + e.getMessage());
            }
        }
        // <-- Query loop
        // <-- Create connection to the database

        // --> Create connection to a desktop client
        // <-- Create connection to a desktop client

        // --> Create connection to a web client
        // <-- Create connection to a web client
    }

    /**
     * Query the database with a prepared statement
     * @param query
     * @return query result
     */
    public HashMap<String, List<String>> query(String statement) throws SQLException {
        System.out.println("Executing query: " + statement);
        // try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(statement);
            System.out.println(Colors.toGreen("[OK]") + " Query executed");
            HashMap<String, List<String>> result = new HashMap<>();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) // Add column names
                result.put(rs.getMetaData().getColumnName(i), new ArrayList<>());

            while (rs.next())
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) // Add values
                    result.get(rs.getMetaData().getColumnName(i)).add(rs.getString(i));

            return result;
        // } catch(SQLException e){
        //     System.out.println(Colors.toRed("[ERROR]") + " Query failed");
        // }
    }
    /**
     * Insert, update and delete statement
     * @param statement
     * @throws SQLException
     */
    public void update(String statement) throws SQLException {
        System.out.println("Executing insert: " + statement);
        // try {
            Statement st = connection.createStatement();
            st.executeUpdate(statement);
            System.out.println(Colors.toGreen("[OK]") + " Insert|Update|Delete executed");
        // } catch(SQLException e){
        //     System.out.println(Colors.toRed("[ERROR]") + " Insert failed");
        // }
    }
    /**
     * Get a specific row from a HashMap<String, List<String>> aka. table<p>
     * Index start from 0
     * @param table
     * @param row
     * @return row in a {@link HashMap}
     */
    public HashMap<String, String> getRow(HashMap<String, List<String>> map, int row) {
        HashMap<String, String> rowMap = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
            rowMap.put(entry.getKey(), entry.getValue().get(row));
        return rowMap;
    }
    public static void main( String[] args ){
        try {
            new Server().run();
        } catch(SQLTimeoutException e){
            System.out.println(Colors.toYellow("[TIMEOUT ERROR]") + " Couldn't establish a connection to: " + DB+USER+PASSWORD);
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[SQL ERROR]") + " Couldn't connect to DB, check if it is running or with correct credentials");
        } finally {
            System.out.println("Exiting...");
        }
    }
}
