package edu.upc.eetac.dsa;

public class ProductClass {

    // Atributos
    String nombre;
    double precio;
    int ventas;

    public ProductClass() {

    }

    // Constructor
    public ProductClass (String nombre, double precio) {
        this.nombre=nombre;
        this.precio=precio;
        this.ventas=0;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    //Sets & Gets
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVentas() {
        return ventas;
    }

    // Función para incrementar el número de ventas
    public void addVentas (int cantidad){
        this.ventas = this.ventas + cantidad;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", precio=" + precio +", ventas=" + ventas +"]";
    }
}
