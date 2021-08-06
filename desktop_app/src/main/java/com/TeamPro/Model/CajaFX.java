package com.TeamPro.Model;

import javafx.beans.property.*;

/**
 * Caja using JavaFX properties
 */
public class CajaFX {
    private IntegerProperty id;
    private IntegerProperty idUsuario;
    private DoubleProperty saldo;

    /**
     * DB constructor
     * @param id
     * @param idUsuario
     * @param saldo
     */
     public CajaFX( int id, int idUsuario, double saldo ) {
        this.id = new SimpleIntegerProperty( id );
        this.idUsuario = new SimpleIntegerProperty( idUsuario );
        this.saldo = new SimpleDoubleProperty( saldo );
    }
    /**
     * UI constructor
     * @param idUsuario
     * @param saldo
     */
    public CajaFX( int idUsuario, double saldo ) {
        this.idUsuario = new SimpleIntegerProperty( idUsuario );
        this.saldo = new SimpleDoubleProperty( saldo );
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
    public int getId() {
        return id.get();
    }
    /**
     * @param id the id to set
     */
    public void setId( int id ) {
        this.id.set( id );
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
    public int getIdUsuario() {
        return idUsuario.get();
    }
    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario( int idUsuario ) {
        this.idUsuario.set( idUsuario );
    }
    /**
     * @return saldo property
     */
    public DoubleProperty getSaldoProperty() {
        return saldo;
    }
    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo.get();
    }
    /**
     * @param saldo the saldo to set
     */
    public void setSaldo( double saldo ) {
        this.saldo.set( saldo );
    }
    /**
     * String to make an INSERT query
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + getId() + "', '" + getIdUsuario() + "', '" + getSaldo() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return " id_u = " + getIdUsuario() + ", saldo_caja = " + getSaldo();
    }
}
