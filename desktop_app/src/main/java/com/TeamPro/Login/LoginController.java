package com.TeamPro.Login;

import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController extends Window implements Initializable {
    protected MySQL db = new MySQL();
    @FXML private TextField TfUsuario;
    @FXML private TextField TfContra;

    /**
     * Verifica las credenciales de autenticación y llama a las vistas dependiendo del tipo de usuario
     * @param event
     */
    @FXML
    void clickLogin(ActionEvent event) {
        tryLogin(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // --> No need to make connection if this is the login window
        // try{
        //     db.conexion();
        // }catch(Exception e){//Show an alert dialog
        //     System.out.println("Error al conectar a la base de datos");
        // }

        // --> When ENTER is clicked on TfUsuario move to TfContra
        TfUsuario.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER))
                TfContra.requestFocus();
        });
        // <-- When ENTER is clicked on TfUsuario move to TfContra

        // --> When ENTER is clicked on TfContra click Login
        TfContra.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER))
                tryLogin(event);
        });
        // <-- When ENTER is clicked on TfContra click Login
    }
    /**
     * Validate that the entered user is in the DB
     */
    // public String validate() {
    //     String nombre = TfUsuario.getText();
    //     String pass = TfContra.getText();
    //     return db.getTipoUsuario("usuarios", "tipo","nombre = '"+nombre+"' AND contraseña = '"+pass+"'");
    //     db.getEmpleado()
    // }
    /**
     * Try login
     * @param event
     */
    public void tryLogin(Event event) {
        // --> Every user of the app has a DB account method
        try{
            // db.setConexion(db.host, TfUsuario.getText(), TfContra.getText());
            db.setCurrentUser( db.getEmpleado(new EmpleadoFX( TfUsuario.getText(), TfContra.getText() )) );
            // System.out.println(MySQL.INFO + "In Login: " + db.holaa);
            // db.holaa++;

            // ==> Switch scene
            Stage switchscene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Sistema_CajasResources/CajaGerente.fxml"));
            System.out.println(MySQL.INFO + "Switching scene to: " + "/Sistema_CajasResources/CajaGerente.fxml");
            Scene scene = loader.load();
            ((Window)loader.getController()).initModel(this.db);//Initializa with previouly stage DB
            switchscene.setScene(scene);
            // <== Switch scene
        }catch(IllegalArgumentException e){
            Window.showAlert("Error", "Usuario invalido", "Usuario y/o contraseña invalidos");
        }catch(IOException ex){
            System.out.println(MySQL.ERROR + ex.getMessage());
            Window.showAlert("Error", "Error fatal", "No se pudo cargar la página");
        }
        // <-- Every user of the app has a DB account method

        // --> Get user and set current user
        // String tipoUsuario = validate();

        // if(tipoUsuario.length() > 0 || tipoUsuario != null){
        //     System.out.println(tipoUsuario);
        //     if(tipoUsuario.equals("administrador")){
        //         switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
        //     }else if(tipoUsuario.equals("vendedor")){
        //         switchScene(event, "/Sistema_CajasResources/CajaEmpleado.fxml");
        //     }else if(tipoUsuario.equals("gerente")){
        //         switchScene(event, "/Sistema_CajasResources/CajaGerente.fxml");
        //     }
        //     else{//Show alert, this code should never be executed, only if the tipo attribute in table usuarios is not valid
        //         Window.showAlert("Error","Ha ocurrido un error", "La Base de Datos ha sido violada");
        //     }
        // }else{
        //     System.out.println(MySQL.ERROR + "No existe el usuario");
        //     Window.showAlert("Error","Error de autenticación", "El usuario no existe o la contraseña es incorrecta");
        // }
    }
}
