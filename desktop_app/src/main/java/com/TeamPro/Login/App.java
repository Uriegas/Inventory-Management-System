package com.TeamPro.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/LoginResources/Login.fxml"));
        //FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AddAdmin.fxml"));
        Scene scene = loader.load();
        primaryStage.setTitle("Sistema inventario");
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream( "/LoginResources/teampro.png" )));
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}