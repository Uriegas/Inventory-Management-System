package com.TeamPro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController{

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
            System.out.println("Es igual");
            new EjecutarInventario().switchScene(event, "/inventario.fxml");
        }else if(TfUsuario.getText().equals("empleado") && TfContra.getText().equals("empleado") ){
            new EjecutarCajas().switchScene(event, "/CajaEmpleado.fxml");
        }else if(TfUsuario.getText().equals("gerente") && TfContra.getText().equals("gerente") ){
            new EjecutarCajas().switchScene(event, "/CajaGerente.fxml");
        }
    }
}
