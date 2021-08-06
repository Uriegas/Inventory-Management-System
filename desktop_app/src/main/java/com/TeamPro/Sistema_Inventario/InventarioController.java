package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventarioController extends Window implements Initializable {

    MySQL query = new MySQL();

    @FXML
    private ImageView ivLogo;

    @FXML
    private ImageView ivFoto;

    @FXML
    private Pane pInventario;

    @FXML
    private TextField tfBuscarProducto;

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
    private Pane pEmpleados;

    @FXML
    private TextField tfBuscarEmpleado;

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
        tvDatosProductos.getItems().clear();
        tvDatosProductos.setItems(query.getProducto(tfBuscarProducto.getText()));
    }
    @FXML
    void clickAddProduct(ActionEvent event) {
        //switchScene(event, "/Sistema_InventarioResources/AddProducto.fxml");
        createPopUp(event, "/Sistema_InventarioResources/AddProducto.fxml");
    }
    @FXML
    void clickMostrarTodoProd(ActionEvent event){
        tvDatosProductos.getItems().clear();
        tvDatosProductos.setItems(query.getProductos());
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
        tvDatosEmpleado.getItems().clear();
        tvDatosEmpleado.setItems(query.getEmpleado(tfBuscarEmpleado.getText()));
    }
    @FXML
    void clickMostrarTodoEmp(ActionEvent event){
        tvDatosEmpleado.getItems().clear();
        tvDatosEmpleado.setItems(query.getEmpleados());
    }

    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            query.conexion();
        } catch (Exception e) {//Show alert
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
            e.printStackTrace();
        }

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

        //Agregamos las filas a la tabla de productos
        tvDatosProductos.setItems(query.getProductos());
        addButtonIntoTableProducts();
        //Agregamos las filas a la tabla de empleados
        tvDatosEmpleado.setItems(query.getEmpleados());
        addButtonIntoTableEmployees();
    }

    public void addButtonIntoTableProducts(){
        // --> Botones en tabla
        TableColumn<ProductoFX, Void> colBtn = new TableColumn("Acciones"); // Creamos la columna
        Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>> cellFactory = new Callback<TableColumn<ProductoFX, Void>, TableCell<ProductoFX, Void>>() {
            @Override
            public TableCell<ProductoFX, Void> call(final TableColumn<ProductoFX, Void> param) {
                final TableCell<ProductoFX, Void> cell = new TableCell<ProductoFX, Void>() {

                    // --> Creamos el boton
                    private final Button btnModificar = new Button("Mod");
                    {
                        // --> Definimos su accion al hacer clic
                        btnModificar.setOnAction((ActionEvent event) -> {
                            ProductoFX producto = getTableView().getItems().get(getIndex()); //toma el producto seleccionado
                            popUpProducto(event, producto);
                            System.out.println("Se modificara " + producto.getNombre()); // ----- Aqui pondras el codigo o llamada a un metodo que agregue el producto a la otra tabla
                        });
                        // <-- Definimos su accion al hacer clic
                    }
                    // <-- Creamos el boton
                    private final Button btnElimianar = new Button("Del");
                    {
                        // --> Definimos su accion al hacer clic
                        btnElimianar.setOnAction((ActionEvent event) -> {
                            ProductoFX producto = getTableView().getItems().get(getIndex()); //toma el producto seleccionado
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("Confirmacion");
                            alert.setContentText("¿Deseas eliminar el producto "+producto.getNombre()+"?");

                            System.out.println("ID: "+producto.getId());

                            Optional<ButtonType> opcion = alert.showAndWait();
                            if(opcion.get() == ButtonType.OK){
                                try {
                                    query.delete(producto); //Aqui mando el producto a eliminar
                                    tvDatosProductos.getItems().clear(); 
                                    tvDatosProductos.setItems(query.getProductos());
                                    Window.showAlert("Completado", "", "Eliminado exitoso");
                                } catch (Exception e) {
                                    Window.showAlert("Error", "Error al eliminar", "No se pudo eliminar el produco seleccionado");
                                }
                            }else{
                                alert.close();
                            }
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
                            HBox botonesAcciones = new HBox(btnModificar, btnElimianar);
                            setGraphic(botonesAcciones);
                        }
                    }
                    // <-- Lo agregamos a la columna
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tvDatosProductos.getColumns().add(colBtn);   //Agregamos la columna a la tabla
        // --> Botones en tabla
    }

    public void addButtonIntoTableEmployees(){
        // --> Botones en tabla
        TableColumn<EmpleadoFX, Void> colBtn = new TableColumn("Acciones"); // Creamos la columna
        Callback<TableColumn<EmpleadoFX, Void>, TableCell<EmpleadoFX, Void>> cellFactory = new Callback<TableColumn<EmpleadoFX, Void>, TableCell<EmpleadoFX, Void>>() {
            @Override
            public TableCell<EmpleadoFX, Void> call(final TableColumn<EmpleadoFX, Void> param) {
                final TableCell<EmpleadoFX, Void> cell = new TableCell<EmpleadoFX, Void>() {

                    // --> Creamos el boton
                    private final Button btnModificar = new Button("Mod");
                    {
                        // --> Definimos su accion al hacer clic
                        btnModificar.setOnAction((ActionEvent event) -> {
                            EmpleadoFX usuario = getTableView().getItems().get(getIndex()); //toma el producto seleccionado
                            popUpEmpleado(event, usuario);
                            System.out.println("Se modificara " + usuario.getNombre()); // ----- Aqui pondras el codigo o llamada a un metodo que agregue el producto a la otra tabla
                        });
                        // <-- Definimos su accion al hacer clic
                    }
                    // <-- Creamos el boton
                    private final Button btnElimianar = new Button("Del");
                    {
                        // --> Definimos su accion al hacer clic
                        btnElimianar.setOnAction((ActionEvent event) -> {
                            EmpleadoFX usuario = getTableView().getItems().get(getIndex()); //toma el producto seleccionado
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("Confirmacion");
                            alert.setContentText("¿Deseas eliminar el usuario "+usuario.getNombre()+"?");

                            Optional<ButtonType> opcion = alert.showAndWait();
                            if(opcion.get() == ButtonType.OK){
                                try {
                                    query.delete(usuario); //Aqui mando el producto a eliminar
                                    tvDatosEmpleado.getItems().clear();
                                    tvDatosEmpleado.setItems(query.getEmpleados());
                                    Window.showAlert("Completado", "", "Eliminado exitoso");
                                } catch (Exception e) {
                                    Window.showAlert("Error", "Error al eliminar", "No se pudo eliminar el produco seleccionado");
                                }
                            }else{
                                alert.close();
                            }
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
                            HBox botonesAcciones = new HBox(btnModificar, btnElimianar);
                            setGraphic(botonesAcciones);
                        }
                    }
                    // <-- Lo agregamos a la columna
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tvDatosEmpleado.getColumns().add(colBtn);   //Agregamos la columna a la tabla
        // --> Botones en tabla
    }

    public void popUpProducto(ActionEvent event, ProductoFX productoFX){

        Stage dialog = new Stage(); // new stage
        dialog.initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/Sistema_InventarioResources/ModProducto.fxml"));
        loader.setController(new ModProductoController());
        ((ModProductoController)loader.getController()).modificar(productoFX);
        try{
            Scene scene = loader.load();
            dialog.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
        // Defines a modal window that blocks events from being
        // delivered to any other application window.
        dialog.initOwner(((Node)event.getTarget()).getScene().getWindow());
        dialog.show();
    }

    public void popUpEmpleado(ActionEvent event, EmpleadoFX empleadoFX){

        Stage dialog = new Stage(); // new stage
        dialog.initModality(Modality.WINDOW_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/Sistema_InventarioResources/ModEmpleado.fxml"));
        loader.setController(new ModEmpleadoController());
        ((ModEmpleadoController)loader.getController()).modificar(empleadoFX);
        try{
            Scene scene = loader.load();
            dialog.setScene(scene);
        }catch(IOException ex){ex.printStackTrace();}
        // Defines a modal window that blocks events from being
        // delivered to any other application window.
        dialog.initOwner(((Node)event.getTarget()).getScene().getWindow());
        dialog.show();
    }
}