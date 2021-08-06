package com.TeamPro.Sistema_Inventario;

<<<<<<< HEAD
import com.TeamPro.MySQL;
=======
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AddEmpleadoController extends Window {
<<<<<<< HEAD

    MySQL query = new MySQL();

    @FXML
    private ImageView ivFoto;
=======
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9

    MySQL query = new MySQL();

    @FXML
    private ComboBox<String> cbPuesto;
    @FXML
    private ImageView ivFoto;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfContra;

    @FXML
    private TextField tfNombre1; // es el tipo de usuario o el "puesto"

    @FXML
    private TextField tfFoto;

    @FXML
    void clickCancelar(ActionEvent event) {
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickExaminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {
<<<<<<< HEAD
String id = tfID.getText();
String name = tfNombre.getText();
String Tipo = tfNombre1.getText();
String valores = id + ", " + "'" + name + "'" + ", " + "'" + Tipo +"'";
query.insert("Usuarios", "");

=======
        try {
        String name = tfNombre.getText();
        String contra = tfContra.getText();
        String puesto = cbPuesto.getValue();

        EmpleadoFX emp = new EmpleadoFX(name, contra, puesto);

        query.conexion();
        query.insert(emp);
            Window.showAlert("Completado", "Accion completada con exito", "Todo en orden");
        } catch (Exception e) {
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
            e.printStackTrace();
        }
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9
    }
}
