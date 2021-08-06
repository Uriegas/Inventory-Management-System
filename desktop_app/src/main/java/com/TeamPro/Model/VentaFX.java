package com.TeamPro.Model;

import java.util.*;
import javafx.beans.property.*;

/**
 * Venta using JavaFX properties
 */
public class VentaFX {
    private IntegerProperty id;
    private IntegerProperty id_usuario;
    private IntegerProperty id_producto;
    private IntegerProperty cantidad;
    private ObjectProperty<Date> fecha;

    /**
     * DB constructor
     * @param id
     * @param id_usuario
     * @param id_producto
     * @param cantidad
     * @param fecha
     */
    public VentaFX(int id, int id_usuario, int id_producto, int cantidad, Date fecha) {
        this.id = new SimpleIntegerProperty(id);
        this.id_usuario = new SimpleIntegerProperty(id_usuario);
        this.id_producto = new SimpleIntegerProperty(id_producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.fecha = new SimpleObjectProperty<>(fecha);
    }
    /**
     * UI constructor
     * @param id_usuario
     * @param id_producto
     * @param cantidad
     * @param fecha
     */
    public VentaFX(int id_usuario, int id_producto, int cantidad) {
        this.id_usuario = new SimpleIntegerProperty(id_usuario);
        this.id_producto = new SimpleIntegerProperty(id_producto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.fecha = new SimpleObjectProperty<>(new Date());
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
    public int getId() {
        return id.get();
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id.set(id);
    }
        /**
     * @return id_usuario property
     */
    public IntegerProperty id_usuarioProperty() {
        return id_usuario;
    }
    /**
     * @return the id_usuario
     */
    public int getid_usuario() {
        return id_usuario.get();
    }
    /**
     * @param id_usuario the id_usuario to set
     */
    public void setid_usuario(int id_usuario) {
        this.id_usuario.set(id_usuario);
    }
    /**
     * @return id_producto property
     */
    public IntegerProperty id_productoProperty() {
        return id_producto;
    }
    /**
     * @return the id_producto
     */
    public int getid_producto() {
        return id_producto.get();
    }
    /**
     * @param id_producto the id_producto to set
     */
    public void setid_producto(int id_producto) {
        this.id_producto.set(id_producto);
    }
    /**
     * @return cantidad property
     */
    public IntegerProperty cantidadProperty() {
        return cantidad;
    }
    /**
     * @return the cantidad
     */
    public int getcantidad() {
        return cantidad.get();
    }
    /**
     * @param cantidad the cantidad to set
     */
    public void setcantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }
    /**
     * @return fecha property
     */
    public ObjectProperty<Date> fechaProperty() {
        return fecha;
    }
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha.get();
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha.set(fecha);
    }
    /**
     * String to make an INSERT query
     * No need to insert id it is automatically generated
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + getid_usuario() + "', '" + getid_producto() + "', '" + getcantidad()+ "', '" + getFecha() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return " cantidad = '" + getcantidad() + "', fecha = " + getFecha().toString() + "',id_u = " + getid_usuario() + ", id_p = " + getid_producto();
    }
}
