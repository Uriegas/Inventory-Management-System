package com.TeamPro.Login;

import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
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

/**
 * Permite crear un usuario administrador cuando no existe dicho usuario
 */
public class AddAdminController extends Window implements Initializable{

    Image img;
    protected MySQL db = new MySQL();

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


    /**
     * Permite agregar una foto de usuario
     * @param event
     */
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

    /**
     * Toma los datos ingresados, manda a insertar dicho usuario y cambia de vista
     * @param event
     */
    @FXML
    void clickGuardar(ActionEvent event) {

        if(!tfNombre.getText().isEmpty() && !tfUsername.getText().isEmpty() && !tfPassword.getText().isEmpty() ) {
            String datos = /*"011, "+*/"'"+tfNombre.getText()+"', '"+tfPassword.getText()+"', 'administrador'";
            try{
            this.db.insert("usuarios", datos );
                Window.showAlert("Completado", "Accion completada con exito", "Todo en orden");
            }catch(Exception e){//Show alert of error on insert
                Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");

            }
            switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
        }
        else
            lblError.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            this.db.conexion();
        }catch(Exception e){//Show alert
            Window.showAlert("Error", "Error al conectar a la base de datos", "Datos introducidos invalidos");
        }
        this.img = new Image(this.getClass().getResource("/LoginResources/userAdmin.jpg").toString(),false);
        clip.setFill(new ImagePattern(this.img));
        clip.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
    }
}

