package com.TeamPro;

import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * <h2>Server class</h2>
 * This server creates a connection to a database and handles requests from the web or a desktop client app.
 * For the server to run it must be installed a MYSQL database.<p>
 * First, create the database:<p>
 * {@code CREATE DATABSE IF NOT EXISTS inventariosDB;}<p>
 * Then, create the user by running the following command in the MySQL console:
 * {@code CREATE USER 'inventarios_client'@'localhost' IDENTIFIED BY 'inventarios123';} <p>
 * and grant it permissions to the inventoriosDB with the following command:
 * {@code GRANT SELECT,INSERT, UPDATE ON inventariosDB.* TO 'inventarios_client'@'localhost';}<p>
 * 
 * TODO: remove the grant permissions to ALL the tables in the database to only the corresponding tables.
 * 
 * To verify that the user was created correctly and granted the permissions, run the following command:
 * {@code mysql -u inventarios_app -p} <p>
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
 * 
 * Running the following command will show the table:
 * {@code SELECT * FROM prueba;}<p>
 * 
 * @author Eduardo Uriegas
 */
public class Server{
    private static final String DB = "jdbc:mysql://localhost/inventariosDB";
    private static final String USER = "inventarios_client";
    // private static final String USER = "root";
    private static final String PASSWORD = "inventarios123";
    // private static final String PASSWORD = "Jholacomo1";
    // private static final int PORT = 9001;
    public static void main( String[] args ){
        run();
    }
    /**
     * Start the server
     */
    public static void run(){
        // --> Create connection to the database
        // Connection connection = null;
        // When run starts, connect to data base
        // Properties p = new Properties();
        // p.put("user", "root");
        // p.put("password", "hola");
        System.out.println("Starting server...");
        try(Connection connection = DriverManager.getConnection(DB, USER, PASSWORD)){
            System.out.println("Connected to database");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT* FROM prueba;");
            System.out.println("Executed query: " + rs.toString());
            while (rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getString("var"));
            }
            // connection = DriverManager.getConnection(DB, p);
        } catch(SQLTimeoutException e){
            System.out.println("Timeout error");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to DB, check if it is running or with correct credentials");
        } finally {
            System.out.println("Exiting...");
        }

        // ServerSocket socket = null;
        // try{
        //     socket = new ServerSocket(PORT);
        //     System.out.println("Server started on port " + PORT);
        //     while(true){
        //         //Thread
        //     }
        // }catch(Exception e){
        //     System.out.println("Server stopped");
        // }
        // <-- Create connection to the database

        // --> Create connection to a desktop client
        // <-- Create connection to a desktop client

        // --> Create connection to a web client
        // <-- Create connection to a web client
    }
}
