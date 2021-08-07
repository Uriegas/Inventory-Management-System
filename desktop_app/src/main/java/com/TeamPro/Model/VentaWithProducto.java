package com.TeamPro.Model;

import java.util.*;
import javafx.beans.property.*;

/**
 * Add price, description and total to a sale
 */
public class VentaWithProducto extends VentaFX {
    private DoubleProperty precio;
    private StringProperty descripcion;
    private DoubleProperty total;
    /**
     * DB constructor
     * @param id
     * @param id_usuario
     * @param id_producto
     * @param cantidad
     * @param fecha
     * @param precio
     * @param descripcion
     */
    public VentaWithProducto(int id, int id_usuario, int id_producto, int cantidad, Date fecha, double precio, String descripcion, double total) {
        super(id, id_usuario, id_producto, cantidad, fecha);
        this.precio = new SimpleDoubleProperty(precio);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.total = new SimpleDoubleProperty(total);
    }
    /**
     * @return the precio property
     */
    public DoubleProperty precioProperty() {
        return precio;
    }
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio.get();
    }
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio.set(precio);
    }
    /**
     * @return the descripcion property
     */
    public StringProperty descripcionProperty() {
        return descripcion;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion.get();
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    /**
     * @return the total property
     */
    public DoubleProperty totalProperty() {
        return total;
    }
    /**
     * @return the total
     */
    public double getTotal() {
        return total.get();
    }
    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total.set(total);
    }
    /**
     * toString method
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " precio= " + this.precio.get() + " descripcion= " + this.descripcion.get();
    }
}
