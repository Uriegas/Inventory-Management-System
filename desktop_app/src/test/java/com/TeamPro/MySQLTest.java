package com.TeamPro;

import static org.junit.Assert.assertTrue;
import java.sql.*;
import java.util.*;
import com.TeamPro.DAO.*;
import com.TeamPro.Model.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

/**
 * Test for the MySQL class aka data model.
 * This test verifies that all the methods in MySQL works as expected (CRUD).
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class MySQLTest {
    MySQL mysql;
    private static EmpleadoFX actualEmpleado;
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
    public MySQLTest(EmpleadoFX empleado, String s){
        actualEmpleado = empleado;
    }
    /**
	 * Collection of paths to files to read
     * @return Collection of tables
     */
    @Parameterized.Parameters(name = "Using EmpleadoFX: {0}")
    public static Collection<Object[]> getTestData(){
        return Arrays.asList( new Object[][]{
            {new EmpleadoFX("Juan", "juan123", "gerente"), "hola" },
            {new EmpleadoFX("Pedro", "pedro123", "gerente"), "hola" }
        });
        // EmpleadoFX[] empleados = new EmpleadoFX[2];
        // empleados[0] = new EmpleadoFX("Juan", "juan123", "gerente");
        // empleados[1] = new EmpleadoFX("Pedro", "pedro123", "gerente");
        // return Arrays.asList(empleados);
	}
    /**
     * Test INSERT query
     */
    @Test
    public void test_1_INSERT() {
        try{
            actualEmpleado = mysql.insert(actualEmpleado);
            System.out.println(MySQL.SUCCESS + "Inserted row in DB");
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
	/**
     * Test SELECT where query
     * We use equals without id because the current user has no id.
	 */
	@Test
	public void test_2_SELECT() {
        try{
            assertTrue( actualEmpleado.equalsVerification(mysql.getEmpleado(actualEmpleado)) );
            System.out.println(MySQL.SUCCESS + "Selected row from DB");
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
	}
    /**
     * Test UPDATE query
     * Change the current employee type to {@code "administrador"}
     * This test assumes that the last inserted row is the one we want to update.
     * If not, we can use {@link MySQL#getEmpleado(EmpleadoFX)} to get the last inserted row.
     * This is due to the fact that this test should run after the above tests.
     */
    @Test
    public void test_3_UPDATE() {
        try{
            actualEmpleado = mysql.getEmpleado(actualEmpleado);
            EmpleadoFX newEmpleado = new EmpleadoFX(actualEmpleado.getId(), actualEmpleado.getNombre(), actualEmpleado.getContraseña(), "administrador");
            mysql.update(actualEmpleado, newEmpleado);
            System.out.println(MySQL.SUCCESS + "Updated row in DB");
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
    /**
     * Test DELETE query
     * This test assumes that the last inserted row is the one we want to delete.
     * If not, we can use {@link MySQL#getEmpleado(EmpleadoFX)} to get the last inserted row.
     * This is due to the fact that this test should run after the above tests.
     */
    @Test
    public void test_4_DELETE() {
        try{
            actualEmpleado = mysql.getEmpleado(actualEmpleado);
            mysql.delete(actualEmpleado);
            System.out.println(MySQL.SUCCESS + "Deleted row in DB");
        }catch(Exception e){
            System.out.println(MySQL.ERROR + "Couldn't get data from DB");
            Assert.fail(e.getMessage());
        }
    }
}
