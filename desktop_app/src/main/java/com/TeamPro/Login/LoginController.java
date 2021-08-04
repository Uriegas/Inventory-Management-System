package com.TeamPro.Login;

import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Window implements Initializable {
    protected MySQL db = new MySQL();

    @FXML
    private TextField TfUsuario;

    @FXML
    private TextField TfContra;

    /**
     * Verifica las credenciales de autenticación y llama a las vistas dependiendo del tipo de usuario
     * @param event
     */
    @FXML
    void clickLogin(ActionEvent event) {
        String nombre = TfUsuario.getText();
        String pass = TfContra.getText();
        String tipoUsuario = db.getTipoUsuario("usuarios", "tipo","nombre = '"+nombre+"' AND contraseña = '"+pass+"'");

        if(tipoUsuario.length() > 0){
            System.out.println(tipoUsuario);
            if(tipoUsuario.equals("administrador")){
                switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
            }else if(tipoUsuario.equals("vendedor")){
                switchScene(event, "/Sistema_CajasResources/CajaEmpleado.fxml");
            }else if(tipoUsuario.equals("gerente")){
                switchScene(event, "/Sistema_CajasResources/CajaGerente.fxml");
            }
        }else{
            System.out.println("No existe el usuario");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db.conexion();
    }
}
