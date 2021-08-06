package com.TeamPro.Model;

import javafx.beans.property.*;

/**
 * Producto using JavaFX properties
 */
public class ProductoFX {
    private IntegerProperty id;
    private DoubleProperty precio;
    private StringProperty nombre;
    private IntegerProperty stock;

    /**
     * DB constructor
     * @param id
     * @param precio
     * @param nombre
     * @param stock
     */
    public ProductoFX(Integer id, Double precio, String nombre, Integer stock) {
        this.id = new SimpleIntegerProperty(id);
        this.precio = new SimpleDoubleProperty(precio);
        this.nombre = new SimpleStringProperty(nombre);
        this.stock = new SimpleIntegerProperty(stock);
    }
    /**
     * UI constructor
     * @param
     * @param precio
     * @param nombre
     */
    public ProductoFX(Double precio, String nombre, Integer stock) {
        this.precio = new SimpleDoubleProperty(precio);
        this.nombre = new SimpleStringProperty(nombre);
        this.stock = new SimpleIntegerProperty(stock);
    }
    /**
     * @return id property
     */
    public IntegerProperty idProperty() {
        return id;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id.get();
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id.set(id);
    }
    /**
     * @return precio property
     */
    public DoubleProperty precioProperty() {
        return precio;
    }
    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio.get();
    }
    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }
    /**
     * @return nombre property
     */
    public StringProperty nombreProperty() {
        return nombre;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre.get();
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    /**
     * @return stock property
     */
    public IntegerProperty stockProperty() {
        return stock;
    }
    /**
     * @return the stock
     */
    public Integer getStock() {
        return stock.get();
    }
    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock.set(stock);
    }
    /**
     * String to make an INSERT query
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + precio.get() + "', '" + nombre.get() + "', '" + stock.get() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return "precio = " + precio.get() + ", descrpcion = '" + nombre.get() + "', existencia = " + stock.get();
    }
}
