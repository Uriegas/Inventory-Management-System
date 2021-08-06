package com.TeamPro.Sistema_Inventario;

import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ModProductoController implements Initializable {

    ProductoFX producto = null;

    MySQL query = new MySQL();

    @FXML
    private ImageView ivFoto;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TextField tfStock;

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
        String desc = tfDescripcion.getText();
        String precio = tfPrecio.getText();
        String cant = tfStock.getText();

        System.out.println("nuevos datos: "+desc+" "+ precio+ " "+ cant);

        this.producto.setPrecio(Double.valueOf(precio));
        this.producto.setNombre(desc);
        this.producto.setStock(Integer.valueOf(cant));
        try {
            query.conexion();
            query.update(this.producto);
        } catch (Exception e) {//Show alert
            System.out.println(e.getMessage());
        }
        Node source = (Node)event.getSource();
        Stage stage = (Stage)source.getScene().getWindow();
        stage.close();
    }

    public void modificar(ProductoFX producto){
        this.producto = producto;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfDescripcion.setText(this.producto.getNombre());
        tfPrecio.setText(this.producto.getPrecio().toString());
        tfStock.setText(this.producto.getStock().toString());
    }
}
