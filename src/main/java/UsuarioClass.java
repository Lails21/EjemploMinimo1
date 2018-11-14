import java.util.LinkedList;

public class UsuarioClass {

    // Atributos
    String nombre;
    LinkedList<PedidoClass> pedidos;

    // Constructor vacio para el JSON
    public UsuarioClass(){

    }

    // Constructor creado por nosotros, solo le pasamos el nombre (no la lista de pedidos, la inicializamos después vacía)
    public UsuarioClass (String nombre){
        this.nombre=nombre;
        this.pedidos = new LinkedList<>();
    }

    // Sets & Gets
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<PedidoClass> getPedidos() {
        return pedidos;
    }

    public void setPedidos(LinkedList<PedidoClass> pedidos) {
        this.pedidos = pedidos;
    }

    // Función para saber que pedido ha hecho el usuario
    public void addPedido (PedidoClass pedido){
        this.pedidos.add(pedido);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.pedidos.size(); i++)
            s = "Producto [Name:" + pedidos.get(0).productos.get(i).producto + ", Cantidad: " + pedidos.get(0).productos.get(i).cantidad + "]";
            return s;
        }
}
