package com.TeamPro.Model;

import javafx.beans.property.*;
/**
 * Empleado using JavaFX properties
 */
public class EmpleadoFX {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty contraseña;
    private StringProperty tipo;

    /**
     * DB constructor
     */
    public EmpleadoFX(Integer id, String nombre, String contraseña, String tipo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.contraseña = new SimpleStringProperty(contraseña);
        this.tipo = new SimpleStringProperty(tipo);
    }
    /**
     * UI constructor
     */
    public EmpleadoFX(String nombre, String contraseña, String tipo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.contraseña = new SimpleStringProperty(contraseña);
        this.tipo = new SimpleStringProperty(tipo);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    public Integer getId() {
        return id.get();
    }
    public void setId(Integer id) {
        this.id.set(id);
    }
    public StringProperty nombreProperty() {
        return nombre;
    }
    public String getNombre() {
        return nombre.get();
    }
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    public StringProperty contraseñaProperty() {
        return contraseña;
    }
    public String getContraseña() {
        return contraseña.get();
    }
    public void setContraseña(String contraseña) {
        this.contraseña.set(contraseña);
    }
    public StringProperty tipoProperty() {
        return tipo;
    }
    public String getTipo() {
        return tipo.get();
    }
    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    /**
     * String to make an INSERT query
     * @return values to insert
     */
    public String toINSERT() {
        return nombre.get() + "', '" + contraseña.get() + "', '" + tipo.get() + "'";
    }
}
