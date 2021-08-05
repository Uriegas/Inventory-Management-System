package com.TeamPro.Login;

import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    MySQL db = new MySQL();
    FXMLLoader loader;

    public static void main(String[] args) {
        launch(args);
    }
    /**
     * TODO: Add a user to the DB that only has GRANT privileges to the usuarios table
     * to use it in the app verification.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            this.db.conexion();
        }catch(Exception e){//Show alert
            System.out.println("Error al conectar a la base de datos");
        }
        if((this.db.select("usuarios", "*", "tipo = 'administrador'"))) {// Si ya existe cuenta admin, abre Login
            loader = new FXMLLoader(this.getClass().getResource("/LoginResources/Login.fxml"));
        }
        else{
            loader = new FXMLLoader(this.getClass().getResource("/LoginResources/AddAdmin.fxml"));
        }
        // --> Show login window
        //((Window)loader.getController()).initModel(db);
        Scene scene = loader.load();
        primaryStage.setTitle("Sistema inventario");
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream( "/LoginResources/teampro.png" )));
        primaryStage.setScene(scene);
        primaryStage.show();
        // <-- Show login window
    }
}