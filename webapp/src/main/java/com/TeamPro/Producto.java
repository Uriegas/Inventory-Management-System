package com.TeamPro;
/**
 * Class to represent a product
 */
public class Producto {
    private Integer id;
    private String descripcion;
    private double precio;
    private int stock;
    /**
     * DB constructor
     * @param id
     * @param descripcion
     * @param precio
     * @param stock
     */
    public Producto(Integer id, String descripcion, double precio, int stock) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    /**
     * Non DB constructor
     * @param descripcion
     * @param precio
     * @param stock
     */
    public Producto(String descripcion, double precio, int stock) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * Set the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Set the descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * Set the precio
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }
    /**
     * Set the stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
    /**
     * @return a string in HTML format of the object
     */
    public String toHTML() {
        return "<tr>" +
                "<td>" + id + "</td>" +
                "<td>" + descripcion + "</td>" +
                "<td>" + precio + "</td>" +
                "<td>" + stock + "</td>" +
                "</tr>";
    }
}
