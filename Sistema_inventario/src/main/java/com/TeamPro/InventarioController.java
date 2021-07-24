package com.TeamPro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InventarioController extends Window{

    @FXML
    private ImageView ivLogo;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Label lbNombre;

    @FXML
    private ImageView ivFoto;

    @FXML
    private Label lbNombreNegocio;

    @FXML
    private Pane pInventario;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TextField tfBuscarProducto;

    @FXML
    private Button btnBuscarProducto;

    @FXML
    private TableView<?> tvDatosProductos;

    @FXML
    private Pane pEmpleados;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private TextField tfBuscarEmpleado;

    @FXML
    private Button btnBuscarEmpleado;

    @FXML
    private TableView<?> tvDatosEmpleado;


    @FXML
    void clickInventario(ActionEvent event) {
        pInventario.toFront();
    }
    @FXML
    void clickFindProduct(ActionEvent event) {

    }
    @FXML
    void clickAddProduct(ActionEvent event) {
        switchScene(event, "/AddProducto.fxml");
        //createPopUp(event, "/AddProducto.fxml");
    }

    @FXML
    void clickEmpleados(ActionEvent event) {
        pEmpleados.toFront();
    }
    @FXML
    void clickAddEmployee(ActionEvent event) {
        switchScene(event, "/AddEmpleado.fxml");
        //createPopUp(event, "/AddEmpleado.fxml");
    }

    @FXML
    void clickFindEmployee(ActionEvent event) {

    }
}
