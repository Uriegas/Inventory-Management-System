package com.TeamPro;

import java.sql.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
/**
 * Test the server.<p>
 * Create a server and test if it can:
 * <ul>
 *     <li>query to database</li>
 *     <li>handle request</li>
 * </ul>
 */
@RunWith(Parameterized.class)
public class ServerTest {
    String expected_result;
    String actual_query;
    Server server;
    /**
     * Setup for the server
     */
    @Before
    public void setUp(){
        try {
            server = new Server();
            server.run();
        } catch(SQLTimeoutException e){
            System.out.println(Colors.toYellow("[TIMEOUT ERROR]:") + "Couldn't establish a connection to DB ");
        } catch (SQLException e) {
            System.out.println(Colors.toRed("[SQL ERROR]:") + "Couldn't connect to DB, check if it is running or with correct credentials");
        } finally {
            System.out.println("Exiting...");
        }
    }
    /**
     * Constructor with expected value injection
     * @param path path to load file
     */
    public ServerTest(String actual, String expected){
        this.actual_query = actual;
        this.expected_result = expected;
    }
    /**
	 * Collection of paths to files to read
     * @return Collection of tables
     */
    @Parameterized.Parameters(name = "Actual is {0}")
    public static Collection<Object[]> getTestData(){
		return Arrays.asList( new Object[][] {
			//List some movies
			{ "SELECT * FROM prueba WHERE id = 1;",
				"{var=[hola], id=[1]}"},
			{ "SELECT * FROM prueba WHERE id = 2;",
				"{var=[como], id=[2]}"}
		});
	}
	/**
	 * Test if the movie is correctly loaded
	 * Assert that strings are equal
	 */
	@Test
	public void testQueries() throws Exception{
		//assert that strings are equal
        try {
            Assert.assertEquals(expected_result, server.query(actual_query).toString());
        } catch (Exception e) {
            Assert.fail("Insert data please");
        }
	}

    // @Test
    // public void testRequest() throws Exception{
    //     Assert.assertEquals(server.request(actual_query), expected_result);
    // }
}
