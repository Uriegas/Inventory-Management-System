package com.TeamPro.Sistema_Cajas;
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CajaEmpleadoController extends Window implements Initializable {

    MySQL query = new MySQL();

    @FXML
    private TableView<?> tvProductosCarrito;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lbCantidadProductos;

    @FXML
    private Button btnVaciar;

    @FXML
    private TextField tfBusqueda;

    @FXML
    private Button btnBuscar;

    /*@FXML
    private ListView<?> lvProductos;*/

    @FXML
    private Label lbTotalPagar;

    public void initialize() {
    }

    @FXML
    private TableView<ProductoFX> tvproductos = new TableView<>();
    @FXML
    private TableColumn<ProductoFX, String> prodDescCol;
    @FXML
    private TableColumn<ProductoFX, Double> prodPrecioCol;
    @FXML
    private TableColumn<ProductoFX, Integer> prodStockCol;


    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }

    @FXML
    void clickBuscar(ActionEvent event){
        ObservableList<ProductoFX> productoBuscado = FXCollections.observableArrayList();
        productoBuscado = query.getProducto(tfBusqueda.getText());
        tvproductos.getItems().clear();
        tvproductos.setItems(productoBuscado);
    }
    @FXML
    void clickMostrarTodo(ActionEvent event){
        tvproductos.getItems().clear();
        tvproductos.setItems(query.getProductos());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            query.conexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // --> Inicializamos las columnas de la tabla de productos
        this.prodDescCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.prodPrecioCol.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        this.prodStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        // <-- Inicializamos las columnas de la tabla de productos

        tvproductos.setItems(query.getProductos());
    }
}