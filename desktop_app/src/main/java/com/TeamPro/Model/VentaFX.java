package com.TeamPro.Model;

import java.util.*;
import javafx.beans.property.*;

/**
 * Venta using JavaFX properties
 */
public class VentaFX {
    private IntegerProperty id;
    private IntegerProperty idCliente;
    private IntegerProperty idCorte;
    private DoubleProperty monto;
    private ObjectProperty<Date> fecha;

    /**
     * DB constructor
     * @param id
     * @param idCliente
     * @param idCorte
     * @param monto
     * @param fecha
     */
    public VentaFX(int id, int idCliente, int idCorte, double monto, Date fecha) {
        this.id = new SimpleIntegerProperty(id);
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.idCorte = new SimpleIntegerProperty(idCorte);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleObjectProperty<>(fecha);
    }
    /**
     * UI constructor
     * @param idCliente
     * @param idCorte
     * @param monto
     * @param fecha
     */
    public VentaFX(int idCliente, int idCorte, double monto) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.idCorte = new SimpleIntegerProperty(idCorte);
        this.monto = new SimpleDoubleProperty(monto);
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
     * @return idCliente property
     */
    public IntegerProperty idClienteProperty() {
        return idCliente;
    }
    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente.get();
    }
    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }
    /**
     * @return idCorte property
     */
    public IntegerProperty idCorteProperty() {
        return idCorte;
    }
    /**
     * @return the idCorte
     */
    public int getIdCorte() {
        return idCorte.get();
    }
    /**
     * @param idCorte the idCorte to set
     */
    public void setIdCorte(int idCorte) {
        this.idCorte.set(idCorte);
    }
    /**
     * @return monto property
     */
    public DoubleProperty montoProperty() {
        return monto;
    }
    /**
     * @return the monto
     */
    public double getMonto() {
        return monto.get();
    }
    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto.set(monto);
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
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + getId() + "', '" + getIdCliente() + "', '" + getMonto()+ "', '" + getFecha() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return " Monto = '" + getMonto() + "', fecha = " + getFecha().toString() + "',id_u = " + getIdCliente() + ", id_cc = " + getIdCorte();
    }
}
