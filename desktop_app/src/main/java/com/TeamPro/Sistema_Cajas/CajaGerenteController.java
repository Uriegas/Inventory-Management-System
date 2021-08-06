package com.TeamPro.Sistema_Cajas;

import java.io.*;
import java.net.*;
import java.util.*;
import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

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
    public void initialize(URL url, ResourceBundle rb) {
        // System.out.println( MySQL.INFO + this.db.holaa);
        // --> Bind tables
        // tvTablaVentas.setItems(db.getVentas());
        // tvTablaCajas.setItems(db.getCajas());//Add button quitar caja
        // <-- Bind tables
    }

    @FXML
    void clickAbrirCaja(ActionEvent event) {
        System.out.println( MySQL.INFO + "In Gerente: " + this.db.holaa);
    }

    @FXML
    void clickCerrarCaja(ActionEvent event) {

    }
    /**
     * Return to login window
     */
    @FXML
    void clickLogout(ActionEvent event) {
        // System.out.println( MySQL.INFO + "In Gerente: " + this.db.holaa);
        // db.holaa++;
        // ==> Switch scene
        Stage switchscene = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/LoginResources/Login.fxml"));
            System.out.println(MySQL.INFO + "Switching scene to: " + "/LoginResources/Login.fxml");
            Scene scene = loader.load();
            ((Window)loader.getController()).initModel(this.db);//Initializa with previouly stage DB
            switchscene.setScene(scene);
        }catch(IOException ex){System.out.println(MySQL.ERROR + ex.getMessage());}
        // <== Switch scene
    }
}
