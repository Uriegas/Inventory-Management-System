package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventarioController extends Window implements Initializable {

    MySQL query = new MySQL();

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
    private TableView<ProductoFX> tvDatosProductos = new TableView<>();
    @FXML
    private TableColumn<ProductoFX, Integer> prodIdCol;
    @FXML
    private TableColumn<ProductoFX, String> prodDescCol;
    @FXML
    private TableColumn<ProductoFX, Double> prodPrecioCol;
    @FXML
    private TableColumn<ProductoFX, String> prodFotoCol;
    @FXML
    private TableColumn<ProductoFX, Integer> prodStockCol;
    @FXML
    private TableColumn<ProductoFX, String> prodAccionesCol;

    @FXML
    private Pane pEmpleados;

    @FXML
    private Button btnAgregarEmpleado;

    @FXML
    private TextField tfBuscarEmpleado;

    @FXML
    private Button btnBuscarEmpleado;

    @FXML
    private TableView<EmpleadoFX> tvDatosEmpleado  = new TableView<>();
    @FXML
    private TableColumn<EmpleadoFX, Integer> empIdCol;
    @FXML
    private TableColumn<EmpleadoFX, String> empNombCol;
    @FXML
    private TableColumn<EmpleadoFX, String> empPuestoCol;


    @FXML
    void clickInventario(ActionEvent event) {
        pInventario.toFront();
    }
    @FXML
    void clickFindProduct(ActionEvent event) {

    }
    @FXML
    void clickAddProduct(ActionEvent event) {
        //switchScene(event, "/Sistema_InventarioResources/AddProducto.fxml");
        createPopUp(event, "/Sistema_InventarioResources/AddProducto.fxml");
    }

    @FXML
    void clickEmpleados(ActionEvent event) {
        pEmpleados.toFront();
    }
    @FXML
    void clickAddEmployee(ActionEvent event) {
        //switchScene(event, "/Sistema_InventarioResources/AddEmpleado.fxml");
        createPopUp(event, "/Sistema_InventarioResources/AddEmpleado.fxml");
    }

    @FXML
    void clickFindEmployee(ActionEvent event) {

    }
    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        query.conexion();

        // --> Inicializamos las columnas de la tabla de productos
        this.prodIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        this.prodDescCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.prodPrecioCol.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        this.prodFotoCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.prodStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        // <-- Inicializamos las columnas de la tabla de productos

        // --> Inicializamos las columnas de la tabla de empleados
        this.empIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        this.empNombCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.empPuestoCol.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        // <-- Inicializamos las columnas de la tabla de empleados

        //Agregamo las filas a la tabla de productos
        tvDatosProductos.setItems(query.getProductos());
        //Agregamo las filas a la tabla de empleados
        tvDatosEmpleado.setItems(query.getEmpleados());
    }
}