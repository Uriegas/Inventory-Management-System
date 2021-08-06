package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddProductoController{

    MySQL query = new MySQL();

    @FXML
    private ImageView ivFoto;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TextField tfStock;

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

        String desc = tfDescripcion.getText();
        String precio = tfPrecio.getText();
        String cant = tfStock.getText();

        ProductoFX prod = new ProductoFX(Double.valueOf(precio), desc, Integer.valueOf(cant));
        try {
            query.conexion();
            query.insert(prod);
            Window.showAlert("Completado", "Accion completada con exito", "Todo en orden");
        } catch (Exception e) {//Show alert
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
        }
    }
}
