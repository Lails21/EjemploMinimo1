package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/ProductManager", description = "Endpoint to Track edu.upc.eetac.dsa.services")
@Path("/ProductManager")
public class ProductManagerService {
    static UsuarioClass usuario1, usuario2, usuario3;
    static ProductClass producto1, producto2, producto3;
    static PedidoClass pedido1, pedido2, pedido3;
    static List<LProductoClass> lp1, lp2, lp3;

    private ProductManager pm;

    public ProductManagerService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.size()==0) {
            producto1 = new ProductClass("Melocotón", 2);
            producto2 = new ProductClass("Platano", 3);
            producto3 = new ProductClass("Ciruela", 5);
            this.pm.addProducto(producto1);
            this.pm.addProducto(producto2);
            this.pm.addProducto(producto3);
            this.pm.addUser("Maria");
        }


    }


    @POST
    @ApiOperation(value = "realizar pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    @Path("/RP")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response RealizarPedido(PedidoClass p) throws UserNotFoundException {
        String user = p.getUsuario().getNombre();
        try{
            this.pm.RealizarPedido(user, p);
            return Response.status(201).build();
        }
        catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();

        }
    }

    @DELETE
    @ApiOperation(value = "servir pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/SP")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ServirPedido() {
           PedidoClass pedido = this.pm.ServirPedido();
            return Response.status(201).entity(pedido).build();

    }


    @GET
    @ApiOperation(value = "obtener productos por precio ASC", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProductClass   .class, responseContainer="ArrayList"),
    })
    @Path("/ASC")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductosPorPrecioASC() {
        List<ProductClass> productos = this.pm.findAllProductsOrderedByPrice();
        GenericEntity<List<ProductClass>> entity = new GenericEntity<List<ProductClass>>(productos) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "obtener productos por ventas DES", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProductClass   .class, responseContainer="ArrayList"),
    })
    @Path("/DES")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllProductsOrderedByVentas() {
        List<ProductClass> productos = this.pm.findAllProductsOrderedByVentas();
        GenericEntity<List<ProductClass>> entity = new GenericEntity<List<ProductClass>>(productos) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all orders of a client", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PedidoClass.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos (@PathParam("user") String usuario){
        List<PedidoClass> pedidos;
        try{
            pedidos=this.pm.damePedidoUsuario(usuario);
            GenericEntity<List<PedidoClass>> entity = new GenericEntity<List<PedidoClass>>(pedidos) {};
            return Response.status(201).entity(entity).build();

        }
        catch (UserNotFoundException e){
            e.printStackTrace();
            return Response.status(404).build();

        }
    }


}
