package com.TeamPro.Sistema_Cajas;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import com.TeamPro.Window;
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.util.*;

public class CajaGerenteController extends Window implements Initializable {
    @FXML private TableView<VentaWithProducto> tvTablaVentas;
    @FXML private TableColumn<VentaWithProducto, String> producto;
    @FXML private TableColumn<VentaWithProducto, Integer> cantidad;
    @FXML private TableColumn<VentaWithProducto, Date> fecha;
    @FXML private TableColumn<VentaWithProducto, Double> total;
    @FXML private TableView<CajaWithEmp> tvTablaCajas = new TableView<>();
    @FXML private TableColumn<CajaWithEmp, Integer> id;
    @FXML private TableColumn<CajaWithEmp, String> encargado;
    @FXML private TableColumn<CajaWithEmp, Double> saldo_actual;
    @FXML private Button btnCorte;
    @FXML private Label lbIdCaja;
    @FXML private Label lbTotalVentas;
    @FXML private Label lbNombre;

    /**
     * initialize model
     */
    @Override
    public void initModel(MySQL d) {
        super.initModel(d);
        try {
        tvTablaCajas.setItems(db.getCajaswithEmpleados());
        System.out.println(MySQL.SUCCESS + "Loaded cajas: " + db.getCajas().toString());
        } catch (SQLException e) {
            System.out.println( MySQL.ERROR + "Error al cargar la tabla de cajas");
            Window.showAlert("Error", "Petición a DB falló", "Ocurrió un error al cargar la tabla de cajas");
        }
    }
    /**
     * Constructor del controlador
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.id.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        this.encargado.setCellValueFactory(cellData -> cellData.getValue().getEncargadoProperty());
        this.saldo_actual.setCellValueFactory(cellData -> cellData.getValue().getSaldoProperty().asObject());

        // ==> Create a TableColumn with a custom cell value factory: button 
        Callback<TableColumn<CajaWithEmp, Void>, TableCell<CajaWithEmp, Void>> callBack = new Callback<TableColumn<CajaWithEmp, Void>, TableCell<CajaWithEmp, Void>>() {
            @Override
            public TableCell<CajaWithEmp, Void> call(TableColumn<CajaWithEmp, Void> param) {
                return new TableCell<CajaWithEmp, Void>() {
                    final Button btn = new Button("Ver");
                    {
                        btn.setOnAction(e -> {
                            CajaWithEmp c = (CajaWithEmp) getTableView().getItems().get(getIndex());
                            updateRightWindow(c);
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {;
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        TableColumn<CajaWithEmp, Void> action = new TableColumn<CajaWithEmp, Void>("Ver");
        action.setCellFactory(callBack);
        tvTablaCajas.getColumns().add(action);
        // <== Create a TableColumn with a custom cell value factory: button

        // ==> Ventas table columns
        producto.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        cantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        fecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        total.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());
        // <== Ventas table columns

    }
    /**
     * Return to login window
     */
    @FXML
    void clickLogout(ActionEvent event) {
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
    /**
     * Update the right window with the selected CajaWithEmp
     * @param caja
     */
    public void updateRightWindow(CajaWithEmp caja) {
        System.out.println(MySQL.DEBUG + "Selected " + caja.toString());
        lbNombre.setText(caja.getEncargado());
        lbIdCaja.setText(String.valueOf(caja.getId()));
        // ==> Button corte de caja
        btnCorte.setOnAction(e -> createCorte(caja));
        // <== Button corte de caja
        // ==> Get ventas of this caja
        try{
            lbTotalVentas.setText("$ " + db.getTotalSales(caja.getIdUsuario()));
            tvTablaVentas.setItems(db.getVentaswithEmpleados(caja.getIdUsuario()));
            System.out.println(MySQL.DEBUG + "SELECT " + db.getVentaswithEmpleados(caja.getIdUsuario()).toString());
        }catch(SQLException ex){
            System.out.println(MySQL.ERROR + ex.getMessage());
            Window.showAlert("Error in Query", "Petición a DB falló", "Ocurrió un error al cargar la tabla de ventas");
        }
        // <== Get ventas of this caja
    }
    /**
     * Make a new Corte de caja
     * @param caja
     */
    public void createCorte(CajaFX c){
        // ==> Create a new corte
        try{
            //Dialog to get the money count
            TextInputDialog dialog = new TextInputDialog(String.valueOf(db.getTotalSales(c.getIdUsuario())));
            dialog.setTitle("Corte de caja");
            dialog.setHeaderText("Ingrese el conteo de dinero de la caja " + c.getId());
            dialog.setContentText("$ ");
            Optional<String> result = dialog.showAndWait();

            LocalDate now = LocalDate.now();
            Date fin = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(MySQL.DEBUG + "Today date: " + fin.toString());
            System.out.println(MySQL.DEBUG + "Today: " + now.toString());

            // ==> Get the spreed: 1 week, 3 days, 1 day choice dialog
            ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>(
                    "1 día",
                    "3 días",
                    "1 semana"
            );
            choiceDialog.setTitle("Corte de caja");
            choiceDialog.setHeaderText("Seleccione el tiempo de corte de la caja " + c.getId());
            choiceDialog.setContentText("Plazo de corte de caja ");
            choiceDialog.showAndWait().ifPresent(action -> {
                // ==> Create a new corte
                try{
                    int days = 0;
                    if(action.equals("1 día"))
                        days = 1;
                    else if(action.equals("3 días"))
                        days = 3;
                    else if(action.equals("1 semana"))
                        days = 7;
                    LocalDate inicio = now.minusDays(days);
                    Date inicioDate = Date.from(inicio.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    CorteCajaFX corte = new CorteCajaFX(inicioDate, fin, Integer.valueOf(result.get()) - c.getSaldo() , c.getIdUsuario());
                    db.insert(corte);
                }catch(SQLException ex){
                    System.out.println(MySQL.ERROR + ex.getMessage());
                    Window.showAlert("Error in Query", "Petición a DB falló", "Ocurrió un error al crear el corte");
                }
            });
            // <== Get the spreed: 1 week, 3 days, 1 day choice dialog
            System.out.println(MySQL.SUCCESS + "Corte creado");

            //Show succes message dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Corte creado");
            alert.setHeaderText("Corte creado");
            alert.setContentText("Corte creado con éxito");
            alert.showAndWait();
        }catch(SQLException ex){
            System.out.println(MySQL.ERROR + ex.getMessage());
            Window.showAlert("Error in Query", "Petición a DB falló", "Ocurrió un error al crear el corte");
        }
        // <== Create a new corte
    }
}
