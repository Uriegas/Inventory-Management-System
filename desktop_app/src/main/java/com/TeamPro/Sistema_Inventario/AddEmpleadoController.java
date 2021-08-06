package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddEmpleadoController extends Window {

    MySQL query = new MySQL();

    @FXML
    private ComboBox<String> cbPuesto;
    @FXML
    private ImageView ivFoto;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfContra;

    @FXML
    private TextField tfFoto;

    @FXML
    void clickCancelar(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickExaminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {
        try {
        String name = tfNombre.getText();
        String contra = tfContra.getText();
        String puesto = cbPuesto.getValue();

        EmpleadoFX emp = new EmpleadoFX(name, contra, puesto);

        query.conexion();
        query.insert(emp);
            Window.showAlert("Completado", "Accion completada con exito", "Todo en orden");
        } catch (Exception e) {
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
            e.printStackTrace();
        }
    }
}
