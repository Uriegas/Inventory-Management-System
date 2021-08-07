package com.TeamPro.Model;

import javafx.beans.property.*;
import java.util.*;

/**
 * Corte Caja using JavaFX properties
 */
public class CorteCajaFX {
    private IntegerProperty id;
    private ObjectProperty<Date> inicio;
    private ObjectProperty<Date> fin;
    private DoubleProperty total;
    private IntegerProperty idUsuario;

    /**
     * DB constructor
     * @param id
     * @param inicio
     * @param fin
     * @param total
     * @param idUsuario
     */
    public CorteCajaFX( Integer id, Date inicio, Date fin, double total, int idUsuario ) {
        this.id = new SimpleIntegerProperty( id );
        this.inicio = new SimpleObjectProperty<>( inicio );
        this.fin = new SimpleObjectProperty<>( fin );
        this.total = new SimpleDoubleProperty( total );
        this.idUsuario = new SimpleIntegerProperty( idUsuario );
    }
    /**
     * UI constructor
     * @param inicio
     * @param fin
     * @param total
     * @param idUsuario
     */
    public CorteCajaFX( Date inicio, Date fin, double total, int idUsuario ) {
        this.inicio = new SimpleObjectProperty<>( inicio );
        this.fin = new SimpleObjectProperty<>( fin );
        this.total = new SimpleDoubleProperty( total );
        this.idUsuario = new SimpleIntegerProperty( idUsuario );
    }
    /**
     * @return id property
     */
    public IntegerProperty getIdProperty() {
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
    public void setId( Integer id ) {
        this.id.set( id );
    }
        /**
     * @return inicio property
     */
    public ObjectProperty<Date> getInicioProperty() {
        return inicio;
    }
    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio.get();
    }
    /**
     * @param inicio the inicio to set
     */
    public void setInicio( Date inicio ) {
        this.inicio.set( inicio );
    }
    /**
     * @return fin property
     */
    public ObjectProperty<Date> getFinProperty() {
        return fin;
    }
    /**
     * @return the fin
     */
    public Date getFin() {
        return fin.get();
    }
    /**
     * @param fin the fin to set
     */
    public void setFin( Date fin ) {
        this.fin.set( fin );
    }
    /**
     * @return total property
     */
    public DoubleProperty getTotalProperty() {
        return total;
    }
    /**
     * @return the total
     */
    public Double getTotal() {
        return total.get();
    }
    /**
     * @param total the total to set
     */
    public void setTotal( Double total ) {
        this.total.set( total );
    }
    /**
     * @return idUsuario property
     */
    public IntegerProperty getIdUsuarioProperty() {
        return idUsuario;
    }
    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario.get();
    }
    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario( Integer idUsuario ) {
        this.idUsuario.set( idUsuario );
    }
    /**
     * String to make an INSERT query
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + getId() + ", '" + getInicio() + "', '" + getFin() + "', '" + getTotal() + "', '" + getIdUsuario() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return "inicio = '" + getInicio().toString() + "', fin = '" + getFin().toString() + "', total = " + getTotal() + ", id_u = " + getIdUsuario() + ", id_c = " + getId();
    }
}
