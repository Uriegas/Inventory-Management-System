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
    MySQL query = new MySQL();
    @FXML private TableView<VentaFX> tvTablaVentas;
    @FXML private Button btnCerrar;
    @FXML private Button btnAbrir;
    @FXML private TableView<CajaFX> tvTablaCajas;
    @FXML private TableColumn<CajaFX, Integer> id;
    @FXML private TableColumn<CajaFX, Integer> encargado;
    @FXML private TableColumn<CajaFX, Double> saldo_actual;
    @FXML private TableColumn<CajaFX, Double> ver_buttons_column;
    @FXML private Label lbIdCaja;
    @FXML private Label lbNombre;
    @FXML private Label lbApertura;
    @FXML private Label lbTotalVentas;

    // @Override
    // public void initModel(MySQL d) {
    //     super.initModel(d);
    //     tvTablaCajas.setItems(db.getCajas());
    //     System.out.println(MySQL.INFO + "Loaded cajas: " + db.getCajas().toString());
    // }
    /**
     * Que necesito para que cada actor tenga una conexion a la base de datos con sus propias credenciales.
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            query.conexion();
        } catch (Exception e) {//Show alert
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
            e.printStackTrace();
        }
        id.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        encargado.setCellValueFactory(cellData -> cellData.getValue().getIdUsuarioProperty().asObject());
        saldo_actual.setCellValueFactory(cellData -> cellData.getValue().getSaldoProperty().asObject());
        ver_buttons_column.setCellValueFactory(cellData -> cellData.getValue().getSaldoProperty().asObject());

        // id.setCellFactory(column -> {
        //     return new TableCell<CajaFX, Integer>() {
        //         @Override
        //         public void updateItem(Integer item, boolean empty) {
        //             super.updateItem(item, empty);
        //             if (item == null || empty) {
        //                 setText(null);
        //             } else {
        //                 setText(item.toString());
        //             }
        //         }
        //     };
        // });
        tvTablaCajas.setItems(query.getCajas());
        System.out.println(MySQL.INFO + "Loaded cajas: " + db.getCajas().toString());
        
        // System.out.println( MySQL.INFO + this.db.holaa);
        // --> Bind tables
        // tvTablaVentas.setItems(db.getVentas());
        // tvTablaCajas.setItems(db.getCajas());//Add button quitar caja
        // <-- Bind tables
    }

    @FXML
    void clickAbrirCaja(ActionEvent event) {
        // System.out.println( MySQL.INFO + "In Gerente: " + this.db.holaa);
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
