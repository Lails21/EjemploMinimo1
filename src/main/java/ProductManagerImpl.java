import java.util.*;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {
    // Inicializamos el log4j llamando al archivo de propiedades
    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    // SINGLETON (fachada)
    // Inicializamos producto
    private static ProductManager instance;

    // Método que retorna el instance y si es nulo la inicializamos
    public static ProductManager getInstance(){
        if (instance == null){
            // Creamos una nueva instancia
            instance = new ProductManagerImpl();
        }
        return instance;
    }

    // Constructor privado (forma parte del Singleton)
    private ProductManagerImpl(){
        productos = new ArrayList<>();
        pedidos = new LinkedList<PedidoClass>();
        usuarios = new HashMap<>();
    }

    // Declaramos las listas que hemos definido en las clases
    private LinkedList<PedidoClass> pedidos;
    private List<ProductClass> productos;
    private HashMap<String, UsuarioClass> usuarios;

    // Obtenemos el tamaño de la lista de productos
    public int size (){
        log.info("Tamaño lista de productos:" +this.productos.size());
        return this.productos.size();
    }

    // Función para añadir un usuario deseado
    public void addUser (String u){
        log.info("Usuario añadido:" +u);
        usuarios.put(u, new UsuarioClass(u));
    }

    // Función para añadir un producto deseado
    public void addProducto(ProductClass p){
        log.info("Producto añadido:" +p);
        this.productos.add(p);
    }

    // Listamos todos los productos que han pedido
    public List<ProductClass> allProducts() {
        List<ProductClass> ret = new ArrayList<>();
        ret.addAll(this.productos);
        log.info("Todos los productos:" +ret);
        return ret;
    }

    // Listado de productos ordenado (ascendentemente) por precio
    public List<ProductClass> findAllProductsOrderedByPrice() {
        // Creamos una copia de la lista para no modificar la actual
        List<ProductClass> ret = new ArrayList<ProductClass>();
        ret.addAll(this.productos);
        log.info("Lista antes de ordenarla:" + this.productos);
        // Creamos la ordenación
        Collections.sort(ret, new Comparator<ProductClass>() {
            public int compare(ProductClass p1, ProductClass p2) {
                return (int) (p1.getPrecio() - (p2.getPrecio()));
            }
        });
        log.info("Lista ordenada:" +ret);
        return ret;
    }

    // Listado de productos ordenado (descendentemente) por número de ventas
    public List<ProductClass> findAllProductsOrderedByVentas() {
        // Creamos una copia de la lista para no modificar la actual
        List<ProductClass> ret = new ArrayList<ProductClass>();
        ret.addAll(this.productos);
        log.info("Lista antes de ordenarla:" + this.productos);

        // Creamos la ordenación
        Collections.sort(ret, new Comparator<ProductClass>() {
            public int compare(ProductClass p1, ProductClass p2) {
                return (-1)*(p1.getVentas() - (p2.getVentas()));
            }
        });
        log.info("Lista ordenada:" +ret);
        return ret;
    }

    // Listado de pedidos de un usuario que ya hayan sido realizados
    public LinkedList<PedidoClass> damePedidoUsuario (String usuario) throws UserNotFoundException{
        LinkedList<PedidoClass> pedidos;

        // Miramos si existe el usuario que nos han pasado
        UsuarioClass theUser = this.usuarios.get(usuario);
        log.info("Usuario:" +theUser);
        if (theUser != null){
            pedidos=theUser.getPedidos();
        }
        else {
            log.error("User not found");
            throw new UserNotFoundException();
        }
        log.info("Pedidos realizados por ese usuario:" +pedidos);
        return pedidos;
    }

    // Realizar un pedido (que puede estar formado por diferentes productos y cantidades por parte de un usuario identificado
    public void RealizarPedido (String u, PedidoClass p) throws UserNotFoundException {
        // Miramos si existe el usuario que nos han pasado
        UsuarioClass theUser = this.usuarios.get(u);
        log.info("Usuario:" +theUser+ "Pedido:" + p);
        if (theUser != null){
            // Asignamos el producto al usuario que lo ha comprado y lo añadimos a su lista de pedidos
            p.setUsuario(theUser);
            this.pedidos.add(p);
            log.info("Lista de productos del usuario:" +this.pedidos);
        }
        else {
            log.error("User not found");
            throw new UserNotFoundException();
        }
    }

    // Servimos un pedido que nos han realizado
    public PedidoClass ServirPedido (){
        // Eliminamos el pedido de la lista de GetPedidos (pedidos pendientes--> pop)
        for (PedidoClass p : this.pedidos) {
            log.info(""+p.getUsuario().nombre);
        }

       PedidoClass p = this.pedidos.remove();
        log.info("Pedido eliminado:" +p);
        // Incrementamos el numero de ventas de ese producto
        process(p);
        // Añadimos ese producto a la lista de pedidos del usuario
        UsuarioClass u = p.getUsuario();
        u.addPedido(p);
        log.info("Lista de pedidos del usuario:"+u.getPedidos());
        return null;
    }

    // Método para incrementar las ventas en ese producto vendido
    private void process(PedidoClass p){
    List<LProductoClass> listaproducto = p.getProductos();
    ProductClass producto;
    log.info("Producto antes de incrementar ventas:"+p);
    // Recorremos cada linea para pasarlo a producto y asi incrementar las ventas
        for (LProductoClass lp: listaproducto){
            producto = this.getProducto(lp.producto);
            producto.addVentas(lp.cantidad);
            log.info("Ventas:"+producto.getVentas());
        }
    }

    // Método que nos da todas los atributos públicos de Producto a partir de su nombre
    private ProductClass getProducto (String producto){
        log.debug("I'm in");
        ProductClass p = null;
        for (int i = 0; i < this.productos.size();i++){
            if(producto.equals(this.productos.get(i).nombre)){
                p=this.productos.get(i);
            }
        }
        return p;
    }






}
