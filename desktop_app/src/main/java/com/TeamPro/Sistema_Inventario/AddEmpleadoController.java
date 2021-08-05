package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddEmpleadoController extends Window {

    MySQL query = new MySQL();

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
    private TextField tfNombre1;

    @FXML
    private TextField tfFoto;

    @FXML
    void clickCancelar(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
        //switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
    }

    @FXML
    void clickExaminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {
        try {
        String id = tfID.getText();
        String name = tfNombre.getText();
        String Tipo = tfNombre1.getText();
        String valores = "'" + name + "'" + ", " + "'" + " " + "'" + ", " + "'" + Tipo + "'";
        query.insert("usuarios", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
