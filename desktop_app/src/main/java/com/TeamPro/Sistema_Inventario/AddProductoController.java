package com.TeamPro.Sistema_Inventario;

<<<<<<< HEAD
import com.TeamPro.MySQL;
=======
import com.TeamPro.DAO.MySQL;
import com.TeamPro.Model.ProductoFX;
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9
import com.TeamPro.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

<<<<<<< HEAD
public class AddProductoController extends Window {

    MySQL query = new MySQL();


    @FXML
    private ImageView ivFoto;
=======
public class AddProductoController{
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9

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
<<<<<<< HEAD

        String id = tfID.getText();
        String desc = tfDescripcion.getText();
        String Precio = tfPrecio.getText();
        String cant = tfStock.getText();
        String valores = id + ", " + "'" + desc + "'" + ", " + Precio + ", " + cant;
        query.insert("Productos", valores);
=======
        String desc = tfDescripcion.getText();
        String precio = tfPrecio.getText();
        String cant = tfStock.getText();

        ProductoFX prod = new ProductoFX(Double.valueOf(precio), desc, Integer.valueOf(cant));
        try {
            query.conexion();
            query.insert(prod);
            Window.showAlert("Completado", "Accion completada con exito", "Todo en orden");
        } catch (Exception e) {//Show alert
            Window.showAlert("Error", "Error al insertar datos", "Datos introducidos invalidos");
        }
>>>>>>> 945bafb2c5ec9d47f716c8e82206afdc2c257db9
    }
}
