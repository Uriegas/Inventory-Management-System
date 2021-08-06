package com.TeamPro;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Window;
/**
 * Abstract Window class, needed because of switching scenes and implementing global CSS properties
 */
public abstract class Window {
    protected MySQL db;
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
            System.out.println(MySQL.INFO + "Switching scene to: " + FXML);
            Scene scene = loader.load();
            ((Window)loader.getController()).initModel(this.db);//Initializa with previouly stage DB
            switchscene.setScene(scene);
        }catch(IOException ex){System.out.println(MySQL.ERROR + ex.getMessage());}
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
            dialog.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
        // Defines a modal window that blocks events from being
        // delivered to any other application window.
        dialog.initOwner(((Node)e.getTarget()).getScene().getWindow());
        dialog.show();
    }
    /**
     * Abstract method for initializing the db connection
     * @param d
     */
    public void initModel(MySQL d){
        if(this.db != null)
            throw new IllegalStateException("Database connection only can be instantiated once");
        else{
            this.db = d;
        }
    }
    /**
     * Show an alert window
     * @param title
     * @param message
     * @param message
     */
    public static void showAlert(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
