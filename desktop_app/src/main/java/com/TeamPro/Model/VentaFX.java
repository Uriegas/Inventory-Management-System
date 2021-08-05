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
    public VentaFX(Integer id, Integer idCliente, Integer idCorte, Double monto, Date fecha) {
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
    public VentaFX(Integer idCliente, Integer idCorte, Double monto) {
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
     * @return idCliente property
     */
    public IntegerProperty idClienteProperty() {
        return idCliente;
    }
    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente.get();
    }
    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
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
    public Integer getIdCorte() {
        return idCorte.get();
    }
    /**
     * @param idCorte the idCorte to set
     */
    public void setIdCorte(Integer idCorte) {
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
    public Double getMonto() {
        return monto.get();
    }
    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
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
}
