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
    private String html = "";
    //<-- Credentials

    //--> Server variables
    final private ServerSocket serverSocket;
    final public static int MAX_CLIENTS = 3000;
    final private SubServer[] m_clientConnections = new SubServer[ MAX_CLIENTS ];
    //<-- Server variables

    public WebServer( int port ) throws Exception {
        // --> Load html
        try{
            File file = new File( this.getClass().getResource( "/index.html" ).getFile() );
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            System.out.println( Colors.toBlue("[INFO]") + " Loading file: " + file.getAbsolutePath() );
            String line = "";
            while ( (line = br.readLine()) != null )
                html += line;
            br.close();
        } catch ( IOException e ) {
            System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
        }
        // <-- Load html
        serverSocket = new ServerSocket( port );
        System.out.println( "Server started on port " + port );
        conn = DriverManager.getConnection( DB, USER, PASSWORD );
        start();
    }

    @Override
    public void run() {
        Socket connection;
        try {
            connection = this.serverSocket.accept();
            assignConnectionToSubServer( connection );
        } catch (IOException e) {
            System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
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

        public SubServer( Socket connection , int id ) {
            this.id = id;
            this.m_connection = connection;
            start();
        }
        @Override
        public void run() {
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
                synchronized(html){
                    System.out.println(Colors.toYellow("[HTML] ") + html);
                    out.println(html);
                }
                out.println(query_in_table);
                out.flush();
                // <-- Send client response (HTML)
            }catch(IOException e){
                System.out.println(Colors.toRed("[ERROR] ") + e.getMessage());
            }
        }
        public void close() {
            try {
                 this.m_connection.close();
            } catch ( IOException e ) {
                 //ignore
            }
        }
    }
    public static void main( String[] args ){
        try {
            new WebServer(80).run();
        } catch ( Exception e ) {
            System.out.println( Colors.toRed("[ERROR] ") + e.getMessage() );
        }
    }
}
