package com.TeamPro;

import java.sql.*;
import java.util.*;
import com.TeamPro.DAO.*;
import com.TeamPro.Model.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for the MySQL class aka data model.
 * This test verifies that all the methods in MySQL works as expected (CRUD).
 */
@RunWith(Parameterized.class)
public class MySQLTest {
    MySQL mysql;
    /**
     * Setup for the server.<p>
     * Change the conexion parameters if required.
     * @see MySQL#conexion(String, String, String)
     */
    @Before
    public void setUp(){
        try {
            mysql = new MySQL();
            mysql.conexion();
        } catch(SQLTimeoutException e){
            System.out.println(MySQL.ERROR + "Couldn't establish a connection to DB ");
        } catch (SQLException e) {
            System.out.println(MySQL.ERROR + "Couldn't connect to DB, check if it is running or with correct credentials");
        } finally {
            System.out.println("Exiting...");
        }
    }
    /**
     * Delete inserted rows
     */
    // @After
    // public void tearDown(){
    //     try {
    //         server.update("DELETE FROM prueba;");//Delete all rows from table prueba
    //     } catch (SQLException e) {
    //         System.out.println(MySQL.ERROR + "Couldn't delete rows from DB");
    //     }
    // }
    /**
     * Constructor with expected value injection
     * @param path path to load file
     */
    public MySQLTest(String host, String user, String password){
        try {
            mysql = new MySQL(host, user, password);
        } catch(SQLTimeoutException e){
            System.out.println(MySQL.ERROR + "Couldn't establish a connection to DB ");
        } catch (SQLException e) {
            System.out.println(MySQL.ERROR + "Couldn't connect to DB, check if it is running or with correct credentials");
        } finally {
            System.out.println("Exiting...");
        }
    }
    /**
	 * Collection of paths to files to read
     * @return Collection of tables
     */
    @Parameterized.Parameters(name = "Using DB: {0}")
    public static Collection<Object> getTestData(){
        return Arrays.asList(
            new EmpleadoFX("Juan", "juan123", "gerente"),
            new EmpleadoFX("Pedro", "pedro123", "gerente")
        );
	}
	/**
     * Test SELECT query
	 */
	@Test
	public void testSELECT() {
        try{
            mysql.getEmpleados();
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
	}
    /**
     * Test INSERT query
     */
    @Test
    public void testINSERT() {
        try{
            mysql.insert(new EmpleadoFX("Juan", "juan123", "gerente"));
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test UPDATE query
     */
    @Test
    public void testUPDATE() {
        try{
            mysql.update(new EmpleadoFX("Juan", "juan123", "gerente"));
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test DELETE query
     */
    @Test
    public void testDELETE() {
        try{
            mysql.delete( new EmpleadoFX("Juan", "juan123", "gerente"));
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
}
