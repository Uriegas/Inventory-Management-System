package com.TeamPro.Model;

import javafx.beans.property.*;
/**
 * Empleado using JavaFX properties
 */
public class EmpleadoFX {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty contrasena;
    private StringProperty tipo;

    /**
     * DB constructor
     */
    public EmpleadoFX(Integer id, String nombre, String contrasena, String tipo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.contrasena = new SimpleStringProperty(contrasena);
        this.tipo = new SimpleStringProperty(tipo);
    }
    /**
     * UI constructor: Create a new employee
     * @param nombre
     * @param contrasena
     * @param tipo
     */
    public EmpleadoFX(String nombre, String contrasena, String tipo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.contrasena = new SimpleStringProperty(contrasena);
        this.tipo = new SimpleStringProperty(tipo);
    }
    /**
     * UI constructor: In login window
     * @param nombre
     * @param contrasena
     */
    public EmpleadoFX(String nombre, String contrasena) {
        this.nombre = new SimpleStringProperty(nombre);
        this.contrasena = new SimpleStringProperty(contrasena);
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
    public StringProperty contrasenaProperty() {
        return contrasena;
    }
    public String getContrasena() {
        return contrasena.get();
    }
    public void setContrasena(String contrasena) {
        this.contrasena.set(contrasena);
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
                ", contrasena='" + contrasena + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    /**
     * String to make an INSERT query
     * @return values to insert
     */
    public String toINSERT() {
        return "'" + nombre.get() + "', '" + contrasena.get() + "', '" + tipo.get() + "'";
    }
    /**
     * String to make an UPDATE query
     * @return values to update
     */
    public String toUPDATE() {
        return "nombre = '" + nombre.get() + "', contrasena = '" + contrasena.get() + "', tipo = '" + tipo.get() + "'";
    }
    /**
     * Compare two EmpleadosFX
     * @param empleado
     * @return true if they are equals
     */
    public boolean equals(EmpleadoFX empleado) {
        return this.id.get() == empleado.id.get();
    }
    /**
     * Compare two EmpladosFX but without id
     * @param empleado
     * @return true if they are equals
     */
    public boolean equalsVerification(EmpleadoFX empleado) {
        return this.contrasena.get().equals(empleado.contrasena.get()) && this.nombre.get().equals(empleado.nombre.get());
    }
}
