package com.TeamPro.Sistema_Cajas;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CajaEmpleadoController extends Window implements Initializable {

    MySQL query = new MySQL();
    DoubleProperty totalPagar = new SimpleDoubleProperty(0.00);
    IntegerProperty totalProductos = new SimpleIntegerProperty(0);

    @FXML private TableView<ProductoFX> tvProductosCarrito  = new TableView<>();
    @FXML private TableColumn<ProductoFX,String> prodNombre;
    @FXML private TableColumn<ProductoFX,Integer> prodCant;
    @FXML private TableColumn<ProductoFX,Integer> prodTotal;
    TableColumn<ProductoFX, Void> colSpinner = new TableColumn<ProductoFX, Void>("Cantidad"); // Creamos la columna
    @FXML private Label lbCantidadProductos;
    @FXML private TextField tfBusqueda;
    @FXML private Label lbTotalPagar;
    @FXML private TableView<ProductoFX> tvproductos = new TableView<>();
    @FXML private TableColumn<ProductoFX, String> prodDescCol;
    @FXML private TableColumn<ProductoFX, Double> prodPrecioCol;
    @FXML private TableColumn<ProductoFX, Integer> prodStockCol;
    /*@FXML
    private ListView<?> lvProductos;*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            query.conexion();
        } catch (SQLException e) {
            Window.showAlert("Error", "Error al conectar a la base de datos", "Datos introducidos invalidos");
            e.printStackTrace();
        }
        // --> Inicializamos las columnas de la tabla de productos
        this.prodDescCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.prodPrecioCol.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        this.prodStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        addButtonIntoTable(); //Agregamos la columna de acciones
        // <-- Inicializamos las columnas de la tabla de productos

        // -->Inicializamos las columnas de la tabla del carrito (Esto da null, i dont know why)
        this.prodNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        addTextFieldIntoTable();
        tvProductosCarrito.getColumns().get(1);
        //<--Inicializamos las columnas de la tabla del carrito

        //Aquí agrega los elementos de la BD a la tabla de productos
        tvproductos.setItems(query.getProductos());

        lbTotalPagar.textProperty().bind(totalPagar.asString());
        lbCantidadProductos.textProperty().bind(totalProductos.asString());

    }
    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }
    @FXML
    void clickBuscar(ActionEvent event){
        tvproductos.getItems().clear();
        tvproductos.setItems(query.getProducto(tfBusqueda.getText()));
    }
    @FXML
    void clickMostrarTodo(ActionEvent event){
        tvproductos.getItems().clear();
        tvproductos.setItems(query.getProductos());
    }
    @FXML
    void clickConfirmar(ActionEvent event){
        tvProductosCarrito.getItems().size();
        System.out.println(tvProductosCarrito.getItems().get(0).getCantidadVenta());
        //query.insert(new VentaFX());
    }
    public void addButtonIntoTable(){
        // --> Botones en tabla

        TableColumn<ProductoFX, Void> colBtn = new TableColumn<ProductoFX, Void>("Acciones"); // Creamos la columna
        Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>> cellFactory = new Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>>() {
            @Override
            public TableCell<ProductoFX, Void> call(final TableColumn<ProductoFX, Void> param) {
                final TableCell<ProductoFX, Void> cell = new TableCell<ProductoFX, Void>() {
                    // --> Creamos el boton
                    private final Button btn = new Button("Agregar");
                    {
                        // --> Definimos su accion al hacer clic
                        btn.setOnAction((ActionEvent event) -> {
                            ProductoFX producto = getTableView().getItems().get(getIndex()); //toma el producto seleccionado
                            tvProductosCarrito.getItems().add(producto); // <- Agrega el producto a la tabla de carrito
                            totalPagar();
                            totalProductos();
                        });
                        // <-- Definimos su accion al hacer clic
                    }
                    // <-- Creamos el boton
                    // --> Lo agregamos a la columna
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                    // <-- Lo agregamos a la columna
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tvproductos.getColumns().add(colBtn);   //Agregamos la columna a la tabla
        // --> Botones en tabla
    }

    public void addTextFieldIntoTable(){
        // --> TextField en tabla
        Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>> cellFactory = new Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>>() {
            @Override
            public TableCell<ProductoFX, Void> call(final TableColumn<ProductoFX, Void> param) {
                final TableCell<ProductoFX, Void> cell = new TableCell<ProductoFX, Void>() {

                    // --> Creamos el TextField
                    private final TextField textField = new TextField();
                    {
                        // --> Definimos su accion al hacer clic
                        textField.setOnAction((ActionEvent event) -> {
                            ProductoFX selectedItem = tvProductosCarrito.getSelectionModel().getSelectedItem();
                            if (selectedItem != null) {
                                System.out.println("Entra");
                                System.out.println(textField.getText());
                                selectedItem.setCantidadVenta(Integer.parseInt(textField.getText()));

                            }
                        });
                        // <-- Definimos su accion al hacer clic
                    }
                    // <-- Creamos el TextField
                    // --> Lo agregamos a la columna
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(textField);
                        }
                    }
                    // <-- Lo agregamos a la columna
                };
                return cell;
            }
        };
        colSpinner.setCellFactory(cellFactory);
        tvProductosCarrito.getColumns().add(colSpinner);   //Agregamos la columna a la tabla
        // --> TextField en tabla
    }

    public void totalPagar(){
        this.totalPagar.set(0.00);
        double tmp = 0.0;
        for(int i = 0; i < tvProductosCarrito.getItems().size(); i++){
            tmp += tvProductosCarrito.getItems().get(i).getPrecio();
        }
        this.totalPagar.setValue(tmp);
    }

    public void totalProductos(){
/*
        for(int i = 0; i < this.colSpinner.getTableView().getColumns().size(); i++){
            System.out.println(this.colSpinner.getTableView().getColumns().get(i).getId());
            System.out.println(this.colSpinner.getText());
            System.out.println(this.colSpinner.getCellData(1));
        }
        System.out.println(this.colSpinner.getId());*/
        this.totalProductos.setValue(tvProductosCarrito.getItems().size());
    }
}