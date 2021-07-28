package com.TeamPro.Sistema_Cajas;

import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CajaGerenteController extends Window {

    @FXML
    private TableView<?> tvTablaVentas;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnAbrir;

    @FXML
    private TableView<?> tvTablaCajas;

    @FXML
    private Label lbIdCaja;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbApertura;

    @FXML
    private Label lbTotalVentas;

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
