import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductManagerTest {

    //Log
    final static Logger log = Logger.getLogger(ProductManagerTest.class.getName());

    //Hay que declarar objecto tipo ProductManager
    static ProductManager pm;
    static UsuarioClass usuario1, usuario2, usuario3;
    static ProductClass producto1, producto2, producto3;
    static PedidoClass pedido1, pedido2, pedido3;
    static List<LProductoClass> lp1, lp2, lp3;

    //Before
    @BeforeClass
    public static void setUp(){
        //No hace falta crearlo con el singleton llamamos instancia
        pm=ProductManagerImpl.getInstance();
        lp1= new ArrayList<>();
        lp2= new ArrayList<>();
        lp3= new ArrayList<>();

        //Añadir usuarios(Usuario)
        pm.addUser("Laia");
        pm.addUser("Maria");
        pm.addUser("Izan");
        //Añadir producto(Ventas, precio y nombre)
        producto1 = new ProductClass("Melocotón", 2);
        producto2 = new ProductClass("Platano", 3);
        producto3 = new ProductClass("Ciruela", 5);

        pm.addProducto(producto1);
        pm.addProducto(producto2);
        pm.addProducto(producto3);

    }

    //After
    @AfterClass
    public static void tearDown(){
        pm=null;
    }

    //Tests
    //Test método de test para realizar un pedido
    @Test
    public void realizarPedido() throws UserNotFoundException{
        try {
            LProductoClass l1 = new LProductoClass();
            l1.producto = "Melocotón";
            l1.cantidad = 1;
            LProductoClass l2 = new LProductoClass();
            l2.producto = "Platano";
            l2.cantidad = 4;
            LProductoClass l3 = new LProductoClass();
            l3.producto = "Ciruela";
            l3.cantidad = 5;

            lp1.add(l1);
            lp2.add(l2);
            lp3.add(l3);

            pedido1 = new PedidoClass(lp1);
            pedido2 = new PedidoClass(lp2);

            pm.RealizarPedido("Laia", pedido1);
            pm.RealizarPedido("Izan", pedido2);
        }

        catch (UserNotFoundException e){
            log.error("El ususario no existe: " +e.getMessage());

        }
    }

    //Test método de test para servir un pedido
    @Test
    public void servirPedido(){
        try {
            LProductoClass l1 = new LProductoClass();
            l1.producto = "Melocotón";
            l1.cantidad = 1;
            LProductoClass l2 = new LProductoClass();
            l2.producto = "Platano";
            l2.cantidad = 4;
            LProductoClass l3 = new LProductoClass();
            l3.producto = "Ciruela";
            l3.cantidad = 5;

            lp1.add(l1);
            lp2.add(l2);
            lp3.add(l3);

            pedido1 = new PedidoClass(lp1);
            pedido2 = new PedidoClass(lp2);

            pm.RealizarPedido("Laia", pedido1);
            pm.RealizarPedido("Izan", pedido2);
        }

        catch (UserNotFoundException e){
            e.printStackTrace();
            log.error("El usuario no existe: " +e.getMessage());

        }

        pedido1=this.pm.ServirPedido();
        pedido2=this.pm.ServirPedido();

        if (pedido1!=null) assertEquals(pedido1.productos.get(0).producto, "Melocotón", "Melcotón");
        if (pedido2!=null) assertEquals(pedido2.productos.get(1).producto, "Platano", "Platano");
    }

    @Test
    public void findAllProductsOrderedbyPrice(){
        List<ProductClass> ret =this.pm.findAllProductsOrderedByPrice();

        assertEquals(ret.get(0).nombre, "Melocotón");
        assertEquals(ret.get(1).nombre, "Platano");
        assertEquals(ret.get(2).nombre, "Ciruela");
    }

    @Test
    public void findAllProductsOrderedbyVentas(){
        List<ProductClass> ret =this.pm.findAllProductsOrderedByVentas();

        assertEquals(ret.get(0).nombre, "Melocotón");
        assertEquals(ret.get(1).nombre, "Platano");
        assertEquals(ret.get(2).nombre, "Ciruela");

    }

    @Test
    public void damePedidosUsuario() {
        try {
            LinkedList<PedidoClass> ret = this.pm.damePedidoUsuario("Andrea");
        }

        catch (UserNotFoundException e) {
            log.error("El usuario no existe: " + e.getMessage());

        }
    }

}
