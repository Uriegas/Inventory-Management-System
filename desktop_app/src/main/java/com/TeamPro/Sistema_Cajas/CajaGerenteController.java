package com.TeamPro.Sistema_Cajas;

import com.TeamPro.Window;
import com.TeamPro.Model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CajaGerenteController extends Window {
    @FXML private TableView<VentaFX> tvTablaVentas;
    @FXML private Button btnCerrar;
    @FXML private Button btnAbrir;
    @FXML private TableView<CajaFX> tvTablaCajas;
    @FXML private Label lbIdCaja;
    @FXML private Label lbNombre;
    @FXML private Label lbApertura;
    @FXML private Label lbTotalVentas;
    /**
     * Que necesito para que cada actor tenga una conexion a la base de datos con sus propias credenciales.
     */
    public void initialize() {
        // --> Bind tables
        // tvTablaVentas.setItems(db.getVentas());
        // tvTablaCajas.setItems(db.getCajas());//Add button quitar caja
        // <-- Bind tables
    }

    @FXML
    void clickAbrirCaja(ActionEvent event) {

    }

    @FXML
    void clickCerrarCaja(ActionEvent event) {

    }
    @FXML
    void clickLogout(ActionEvent event) {
        switchScene(event, "/LoginResources/Login.fxml");
    }

}
