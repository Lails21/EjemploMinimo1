package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.LProductoClass;

import java.util.ArrayList;
import java.util.List;

public class PedidoClass {

    // Atributos
    private UsuarioClass usuario;
    List<LProductoClass> productos;

    // Constructor vacio para el JSON y aprovechamos para crear una lista vacía para luego llenarla
    public PedidoClass(){
        this.productos=new ArrayList<>();
    }

    // Constructor creado por nosotros
    public PedidoClass(List<LProductoClass> productos){
        this.productos=productos;
    }

    // Sets & Gets
    public UsuarioClass getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioClass usuario) {
        this.usuario = usuario;
    }

    public List<LProductoClass> getProductos() {
        return productos;
    }

    public void setProductos(List<LProductoClass> productos) {
        this.productos = productos;
    }
}
