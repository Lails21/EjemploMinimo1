package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.PedidoClass;
import edu.upc.eetac.dsa.ProductClass;

import java.util.LinkedList;
import java.util.List;

public interface ProductManager {

    // Métodos
    // Realizar un pedido (que puede estar formado por diferentes productos y cantidades por parte de un usuario identificado
    void RealizarPedido (String u, PedidoClass p) throws UserNotFoundException;
    // Servir un pedido (por orden de llegadas)
    PedidoClass ServirPedido ();
    // Listado de productos ordenado (ascendentemente) por precio
    List<ProductClass> findAllProductsOrderedByPrice();
    // Listado de productos ordenado (descendentemente) por número de ventas
    List<ProductClass> findAllProductsOrderedByVentas();
    // Listado de pedidos de un usuario que ya hayan sido realizados
    LinkedList<PedidoClass> damePedidoUsuario (String usuario) throws UserNotFoundException;

    void addUser(String u);
    void addProducto (ProductClass p);
    List<ProductClass> allProducts();
    int size();
}
