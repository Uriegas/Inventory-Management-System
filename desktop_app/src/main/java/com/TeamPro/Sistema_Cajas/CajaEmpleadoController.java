package com.TeamPro.Sistema_Cajas;
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CajaEmpleadoController extends Window {

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

    @FXML
    private ListView<?> lvProductos;

    @FXML
    private Label lbTotalPagar;

    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }

}