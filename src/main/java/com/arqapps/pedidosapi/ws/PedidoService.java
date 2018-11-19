package com.arqapps.pedidosapi.ws;

import com.arqapps.pedidosapi.dao.PedidoDao;
import com.arqapps.pedidosapi.model.Pedido;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("order")
public class PedidoService {
    private static PedidoDao pedidoDao = new PedidoDao();

    public PedidoService() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPedidos() {
        return Response.ok(pedidoDao.getPedidos()).build();
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
