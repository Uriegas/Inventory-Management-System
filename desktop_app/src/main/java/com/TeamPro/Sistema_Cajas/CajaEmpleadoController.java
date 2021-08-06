package com.TeamPro.Sistema_Cajas;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
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

    @FXML private TableView<ProductoFX> tvProductosCarrito;
    @FXML private TableColumn<ProductoFX,String> prodNombre;
    @FXML private TableColumn<ProductoFX,Integer> prodCant;
    @FXML private TableColumn<ProductoFX,Integer> prodTotal;
    @FXML private Button btnConfirmar;
    @FXML private Label lbCantidadProductos;
    @FXML private Button btnVaciar;
    @FXML private TextField tfBusqueda;
    @FXML private Button btnBuscar;
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
            e.printStackTrace();
        }
        // --> Inicializamos las columnas de la tabla de productos
        this.prodDescCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        this.prodPrecioCol.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        this.prodStockCol.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        // <-- Inicializamos las columnas de la tabla de productos

        // -->Inicializamos las columnas de la tabla del carrito (Esto da null, i dont know why)
        this.prodNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        //<--Inicializamos las columnas de la tabla del carrito

        //Aquí agrega los elementos de la BD a la tabla de productos
        tvproductos.setItems(query.getProductos());
        addButtonIntoTable(); //Agregamos la columna de acciones

    }
    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }
    @FXML
    void clickBuscar(ActionEvent event){
        /*ObservableList<ProductoFX> productoBuscado = FXCollections.observableArrayList();
        productoBuscado = query.getProducto(tfBusqueda.getText());*/
        tvproductos.getItems().clear();
        tvproductos.setItems(query.getProducto(tfBusqueda.getText()));
    }
    @FXML
    void clickMostrarTodo(ActionEvent event){
        tvproductos.getItems().clear();
        tvproductos.setItems(query.getProductos());
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

                            //System.out.println("Se agrego " + producto.getNombre() + " al carrito"); // ----- Aqui pondras el codigo o llamada a un metodo que agregue el producto a la otra tabla
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
}