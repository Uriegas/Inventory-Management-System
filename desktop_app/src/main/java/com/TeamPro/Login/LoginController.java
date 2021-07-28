package com.TeamPro.Login;

import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController extends Window {

    @FXML
    private TextField TfUsuario;

    @FXML
    private TextField TfContra;

    @FXML
    private Button btnLogin;

    @FXML
    void clickLogin(ActionEvent event) {
        System.out.println("CLickk");

        if(TfUsuario.getText().equals("admin") && TfContra.getText().equals("admin") ){
            switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
        }else if(TfUsuario.getText().equals("empleado") && TfContra.getText().equals("empleado") ){
            switchScene(event, "/Sistema_CajasResources/CajaEmpleado.fxml");
        }else if(TfUsuario.getText().equals("gerente") && TfContra.getText().equals("gerente") ){
            switchScene(event, "/Sistema_CajasResources/CajaGerente.fxml");
        }
    }
}
