package com.TeamPro.Model;

import javafx.beans.property.*;
/**
 * The same {@link CajaFX} with a {@link StringProperty} for the name of the employee
 */
public class CajaWithEmp extends CajaFX {
    private StringProperty encargado;
    /**
     * DB constructor
     */
    public CajaWithEmp(int id, int id_emp, String encargado, double saldo){
        super(id, id_emp, saldo);
        this.encargado = new SimpleStringProperty(encargado);
    }
    /**
     * Get emplooyee name property
     * @return
     */
    public StringProperty getEncargadoProperty(){
        return this.encargado;
    }
    /**
     * Get name of the employee
     * @return
     */
    public String getEncargado() {
        return encargado.get();
    }
    /**
     * Set name of the employee
     */
    public void setEncargado(String encargado) {
        this.encargado.set(encargado);
    }
    /**
     * To string method
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + ", nombre_encargado=" + this.getEncargado();
    }
}
