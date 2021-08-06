package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.EmpleadoFX;
import com.TeamPro.Model.ProductoFX;
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModEmpleadoController implements Initializable {

    EmpleadoFX empleado = null;
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
        String nombre = tfNombre.getText();
        String contra = tfContra.getText();
        String tipo = cbPuesto.getValue();

        System.out.println("nuevos datos: "+nombre+" "+ contra+ " "+ tipo);

        this.empleado.setNombre(nombre);
        this.empleado.setContraseña(contra);
        this.empleado.setTipo(tipo);
        try {
            query.conexion();
            query.update(this.empleado);
        } catch (Exception e) {//Show alert
            System.out.println(e.getMessage());
        }
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public void modificar(EmpleadoFX empleado){
        this.empleado = empleado;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfNombre.setText(this.empleado.getNombre());
        tfContra.setText(this.empleado.getContraseña());
        cbPuesto.setValue(this.empleado.getTipo());
    }
}
