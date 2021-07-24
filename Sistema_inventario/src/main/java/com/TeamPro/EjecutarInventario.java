package com.TeamPro;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class EjecutarInventario {

    public void switchScene(Event e, String FXML){
        Stage switchscene = (Stage) ((Node)e.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Inventario.fxml"));
            Scene scene = loader.load();
            switchscene.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
    }
}