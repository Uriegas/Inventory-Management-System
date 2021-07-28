package com.TeamPro.Login;

import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAdminController extends Window implements Initializable{

    Image img;

    @FXML
    private TextField tfNombre;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    Circle clip = new Circle(250,200,80);

    @FXML
    private Label lblError;

    @FXML
    void clickAdd(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona el una foto");

        File seleccionado = fc.showOpenDialog(null);

        if(seleccionado != null){
            this.img = new Image(seleccionado.toURI().toString(),false);
            clip.setFill(new ImagePattern(this.img));
        }else{
            System.out.println("No se encontro el archivo");
        }
    }

    @FXML
    void clickGuardar(ActionEvent event) {
        if(!tfNombre.getText().isEmpty() && !tfUsername.getText().isEmpty() && !tfPassword.getText().isEmpty() )
            switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
        else
            lblError.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Agregar algo para comprobar si existe cuenta admin

        this.img = new Image(this.getClass().getResource("/userAdmin.jpg").toString(),false);
        clip.setFill(new ImagePattern(this.img));
        clip.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
    }
}

