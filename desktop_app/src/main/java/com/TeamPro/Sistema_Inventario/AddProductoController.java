package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddProductoController extends Window {

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
        //switchScene(event, "/Sistema_InventarioResources/Inventario.fxml");
    }

    @FXML
    void clickExaminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {
        String id = tfID.getText();
        String desc = tfDescripcion.getText();
        String precio = tfPrecio.getText();
        String cant = tfStock.getText();

        ProductoFX prod = new ProductoFX(Integer.valueOf(id), Double.valueOf(precio), desc, Integer.valueOf(cant));
        try {
            query.conexion();
            query.insert(prod);
        } catch (Exception e) {//Show alert
            System.out.println(e.getMessage());
        }
        /*ObservableList<ProductoFX> prdouctoTmp = FXCollections.observableArrayList();
        prdouctoTmp.add(prod);*/

    }
}
