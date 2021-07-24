package com.TeamPro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddEmpleadoController extends Window{
    @FXML
    private ImageView ivFoto;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnExaminar;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfFoto;

    @FXML
    void clickCancelar(ActionEvent event) {
        switchScene(event, "/Inventario.fxml");
    }

    @FXML
    void clickExaminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {

    }
}
