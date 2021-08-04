package com.TeamPro;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * The webserver class is a client of the database
 * and a server for a http request. <p>
 * Example data (add to DB):<br>
 * <ul>
 *     <li>INSERT INTO productos VALUES(1, 15.42, 'jabon ZOTE', 800);</li>
 *     <li>INSERT INTO productos VALUES(2, 15.99, 'cuaderno NORMA', 100);</li>
 *     <li>INSERT INTO productos VALUES(3, 5.99, 'taka taka', 1000);</li>
 * </ul>
 */
public class WebServer extends Thread {
    //--> Credentials
    private static final String DB = "jdbc:mysql://localhost/inventariosDB";
    private static String USER = "inventarios_client";
    private static String PASSWORD = "inventarios123";
    private Connection conn;
    //<-- Credentials

    //--> Server variables
    final private ServerSocket serverSocket;
    final public static int MAX_CLIENTS = 3000;
    final private SubServer[] m_clientConnections = new SubServer[ MAX_CLIENTS ];
    //<-- Server variables

    public WebServer( int port ) throws Exception {
        serverSocket = new ServerSocket( port );
        System.out.println( "Server started on port " + port );
        // --> Connect to the database
        conn = DriverManager.getConnection( DB, USER, PASSWORD );
        // <-- Connect to the database
        start();
        // drop();
    }

    @Override
    public void run() {
        while ( !this.interrupted() ) {
            //wait for clients
            Socket connection;
            try {
                connection = this.serverSocket.accept();
                assignConnectionToSubServer( connection );
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
            }
        }
    }

    public void assignConnectionToSubServer( Socket connection ) {
         for ( int i = 0 ; i < MAX_CLIENTS ; i++ ) {

             //find an unassigned subserver (waiter)
             if ( this.m_clientConnections[ i ] == null ) {
                  this.m_clientConnections[ i ] = new SubServer( connection , i );
                  break;
             }
         }
    }
    protected class SubServer extends Thread {

        final private int id;
        final private Socket m_connection;

        //you can store additional client properties here if you want, for example:
        // private int m_gameRating = 1500;

        public SubServer( Socket connection , int id ) {
            this.id = id;
            this.m_connection = connection;
            start();
        }

        @Override
        public void run() {
            // while( !this.interrupted() ) { //Live Connection
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(m_connection.getInputStream()));
                    PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter( m_connection.getOutputStream() ) ) );
                    System.out.println( "[" + id + "] " + Colors.toGreen( "Client connected" ) );

                    // --> Get URL from client
                    String s = ".";
                    String url = "";
                    URL Url = null;
                    while( !s.isEmpty() ) {
                        s = in.readLine();
                        if( s.startsWith( "GET" ) ) {
                            // String url = s.substring( s.indexOf( " " ) + 1 );
                            url = s.split( " " )[1];
                        }
                        if( s.startsWith( "Host: " ) ) {//Add host to beggining of URL
                            StringBuilder host = new StringBuilder();
                            host.append("http://" + s.substring( 6 ));
                            url = host.toString() + url;
                            Url = new URL( url );
                            System.out.println( Colors.toBlue("[URL] ") + Url );
                        }
                        System.out.println(Colors.toGreen("[CLIENT] ") + s);
                    }
                    // <-- Get URL from client

                    // --> Get response from DB
                    String query_in_table = "";
                    try{
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM productos");
                        List<Producto> productos = new ArrayList<Producto>();
                        // --> Create a list of products
                        while( rs.next() ) {
                            productos.add( new Producto( rs.getInt( "id_p" ), rs.getString( "descrpcion" ), rs.getFloat( "precio" ), rs.getInt( "existencia" ) ) );
                        }
                        System.out.println(Colors.toYellow("[QUERY] ") + productos.toString());
                        query_in_table = "<table><tr><th>ID</th><th>Descripcion</th><th>Precio</th><th>Existencia</th></tr>";
                        for( Producto p : productos ) {
                            query_in_table += p.toHTML() + "\n";
                        }
                        query_in_table += "</table>";
                        // <-- Create a list of products
                        System.out.println(Colors.toYellow("[RESPONSE] ") + productos.toString());

                        // query_in_table = query_to_html_table(res);
                    }catch(SQLException e){
                        System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
                    }
                    // <-- Get response from DB
                    
                    // --> Send client response (HTML)
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Server: Bot");
                    // this blank line signals the end of the headers
                    out.println("");
                    // Send the HTML page
                    out.println("<H1>Welcome to Tienda: LPY</H1>");
                    out.println("<H2>Productos:</H2>");
                    out.println(query_in_table);
                    out.flush();
                    // <-- Send client response (HTML)
                }catch(IOException e){
                    System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
                }
                //TODO: Check if a socket was closed by the client and close the connection
                // while(true){//Close interrupted socket
                //     //Wait 10 seconds for hearbeat
                //     try{
                //         Thread.sleep(10000);
                //         if(this.m_connection.isClosed()){
                //             this.m_connection.close();
                //             System.out.println( "[" + id + "] " + Colors.toRed( "Client disconnected" ) );
                //             break;
                //         }
                //     }catch(Exception e){
                //         System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
                //     }
                // }
            // }
        }

        //as an example, if you read String messages from your client,
        //just call this method from the run() method to process the client request
        public void process( String message ) {

        }

        /**
         * terminates the connection with this client (i.e. stops serving him)
         */
        public void close() {
            try {
                 this.m_connection.close();
            } catch ( IOException e ) {
                 //ignore
            }
        }
    }
        /**
         * Check if the client is still connected
         */
        // public void drop() {
        //     try {
        //         for ( int i = 0 ; i < MAX_CLIENTS ; i++ ) {
        //             if ( m_clientConnections[ i ] != null ) {
        //                 if ( m_clientConnections[ i ].m_connection.equals( m_connection ) ) {
        //                     m_clientConnections[ i ] = null;
        //                     System.out.println( "[" + id + "] " + Colors.toYellow( "Client disconnected" ) );
        //                     break;
        //                 }
        //             }
        //         }
        //     } catch (Exception e) {
        //         System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
        //     }
        // }

    public static void main( String[] args ){
        try {
            new WebServer(80).run();
        } catch ( Exception e ) {
            System.out.println( Colors.toRed("[ERROR] ") + e.getMessage() );
        }
    }
    /**
     * Query the database with a prepared statement
     * @param query
     * @return query result
     */
    public HashMap<String, List<String>> query(String statement, Connection conn) throws SQLException {
        System.out.println("Executing query: " + statement);
        // try {
            Statement st = conn.createStatement();
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
    /**
     * Convert a HashMap<String, List<String>> aka. table to a horizontal HTML table
     * @param table
     * @return HTML table
     */
    public String query_to_html_table(HashMap<String, List<String>> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sb.append("<tr>");
            sb.append("<td>" + entry.getKey() + "</td>");
            for (String value : entry.getValue())
                sb.append("<td>" + value + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
