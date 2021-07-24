package com.TeamPro;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.*;

/**
 * Abstract Window class, needed because of switching scenes and implementing global CSS properties
 */
public abstract class Window {
    /**
     * Switch the current scene (window) to the specified FXML file
	 * @param FXML file
     * @param e
     */
    public void switchScene(Event e, String FXML){
        Stage switchscene = (Stage) ((Node)e.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(FXML));
            Scene scene = loader.load();
            /*scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());//Add css*/
            switchscene.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
    }
    /**
     * Creates a new pop up from the current window
     * @param e event to handle this pop up
     * @param FXML the fxml file to load this pop up
     */
    public void createPopUp(Event e, String FXML){
        Stage dialog = new Stage(); // new stage
        dialog.initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(FXML));
        try{
            Scene scene = loader.load();
            /*scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());//Add css*/
            dialog.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
        // Defines a modal window that blocks events from being
        // delivered to any other application window.
        dialog.initOwner(((Node)e.getTarget()).getScene().getWindow());
        // dialog.setOnCloseRequest(event ->{
        //     Node source = (Node)event.getSource();
        //     Stage stage = (Stage)source.getScene().getWindow();
        //     stage.close();
        // });
        dialog.show();
    }
}
