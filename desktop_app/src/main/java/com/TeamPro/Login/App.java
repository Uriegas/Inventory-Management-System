package com.TeamPro.Login;

import com.TeamPro.MySQL;
import com.TeamPro.Window;
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

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.db.conexion();
        if((this.db.select("usuarios", "*", "tipo = 'administrador'"))) {// Si ya existe cuenta admin, abre Login
            loader = new FXMLLoader(this.getClass().getResource("/LoginResources/Login.fxml"));
        }
        else{
            loader = new FXMLLoader(this.getClass().getResource("/LoginResources/AddAdmin.fxml"));
        }
        /*Window login = loader.getController();
        login.initModel(this.db);//Initialize the data model into the controller*/
        Scene scene = loader.load();
        primaryStage.setTitle("Sistema inventario");
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream( "/LoginResources/teampro.png" )));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}