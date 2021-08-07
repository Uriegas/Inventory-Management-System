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
import java.util.ResourceBundle;

public class LoginController extends Window implements Initializable {
    // protected MySQL db = new MySQL();
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
     * Try login
     * @param event
     */
    public void tryLogin(Event event) {
        // ==> Find the user in the DB, otherwise can't login
        try{
            db.setCurrentUser( db.getEmpleado(new EmpleadoFX(TfUsuario.getText(), TfContra.getText())) );
            System.out.println(MySQL.INFO + "Login successful as " + db.getCurrentUser().toString());
            // ==> Switch scene
            Stage switchscene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            if(db.getCurrentUser().getTipo().equals("administrador"))
                loader.setLocation(getClass().getResource("/Sistema_InventarioResources/Inventario.fxml"));
            else if(db.getCurrentUser().getTipo().equals("gerente"))
                loader.setLocation(getClass().getResource("/Sistema_CajasResources/CajaGerente.fxml"));
            else if(db.getCurrentUser().getTipo().equals("vendedor"))
                loader.setLocation(getClass().getResource("/Sistema_CajasResources/CajaEmpleado.fxml"));
            System.out.println(MySQL.INFO + "Switching scene to the " + db.getCurrentUser().getTipo() + " view");
            Scene scene = loader.load();
            ((Window)loader.getController()).initModel(this.db);//Initializa with previouly stage DB
            switchscene.setScene(scene);
            // <== Switch scene
        }catch(IllegalArgumentException e){
            Window.showAlert("Error", "Usuario invalido", "Usuario y/o contrasena invalidos");
            System.out.println(MySQL.ERROR + e.getMessage());
        }catch(IOException ex){
            System.out.println(MySQL.ERROR + ex.getMessage());
            ex.printStackTrace();
            Window.showAlert("Error", "Error fatal", "No se pudo cargar la página");
        }
        // <== Find the user in the DB, otherwise can't login
    }
}
