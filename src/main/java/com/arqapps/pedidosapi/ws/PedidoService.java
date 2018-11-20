package com.arqapps.pedidosapi.ws;

import com.arqapps.pedidosapi.dao.PedidoDao;
import com.arqapps.pedidosapi.model.Pedido;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("order")
public class PedidoService {
    private static PedidoDao pedidoDao = new PedidoDao();

    public PedidoService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPedidos() {
        return Response.ok(pedidoDao.getPedidos()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Pedido pedido = pedidoDao.getPedido(id);
        if (pedido != null) {
            return Response.ok(pedido).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Pedido pedido, @Context UriInfo uriInfo) {
        Pedido newProduct = pedidoDao.addPedido(pedido);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI newUri = uriBuilder.path(String.valueOf(newProduct.getId())).build();
        return Response.created(newUri).entity(newProduct).status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePedido(@PathParam("id") long id, Pedido pedido) {
        pedido.setId(id);
        Pedido updatedPedido = pedidoDao.updatePedido(pedido);
        if (updatedPedido != null)
            return Response.ok(updatedPedido).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePedido(@PathParam("id") long id) {
        Pedido deletedPedido = pedidoDao.deletePedido(id);
        if (deletedPedido != null)
            return Response.ok(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
