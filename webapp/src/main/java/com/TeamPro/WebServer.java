package com.TeamPro;

import java.io.*;
import java.net.*;


/**
 * The webserver class is a client of the database
 * and a server for a http request.
 *
 */
public class WebServer extends Thread {
    //--> Credentials
    private static final String DB = "jdbc:mysql://localhost/inventariosDB";
    private static String USER = "inventarios_client";
    private static String PASSWORD = "inventarios123";
    //<-- Credentials

    //--> Server variables
    final private ServerSocket serverSocket;
    final public static int MAX_CLIENTS = 3000;
    final private SubServer[] m_clientConnections = new SubServer[ MAX_CLIENTS ];
    //<-- Server variables

    public WebServer( int port ) throws Exception {
        serverSocket = new ServerSocket( port );
        System.out.println( "Server started on port " + port );
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

                    // --> Send client response
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("Server: Bot");
                    // this blank line signals the end of the headers
                    out.println("");
                    // Send the HTML page
                    out.println("<H1>Welcome to the Ultra Mini-WebServer</H1>");
                    out.flush();
                    // <-- Send client response
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
}
