package com.arqapps.pedidosapi.ws;

import com.arqapps.pedidosapi.dao.PedidoDao;
import com.arqapps.pedidosapi.model.Pedido;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * Maneja todas las solicitudes a través del endpoint /order/
 */
@Path("order")
public class PedidoService {
    private static PedidoDao pedidoDao = new PedidoDao();

    public PedidoService() {
    }

    /** Maneja la solicitud GET al endpoint /order/
     * @return {@link Response} con el codigo de estado 200 y la lista de {@link Pedido}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPedidos() {
        return Response.ok(pedidoDao.getPedidos()).build();
    }

    /** Maneja la solicitud GET al endpoint /order/{id}
     * @param id Id del {@link Pedido} que se va a consultar
     * @return El {@link Pedido} encontrado según su id o un código de estado 404 si no se encuentra el pedido
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id) {
        Pedido pedido = pedidoDao.getPedido(id);
        if (pedido != null)
            return Response.ok(pedido).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /** Maneja la solicitud POST al endpoint /order/ para guardar un nuevo pedido
     * @param pedido {@link Pedido} que llega a través de JSON para almacenar en la BD
     * @param uriInfo Información de la URI de la solicitud entrante
     * @return El {@link Pedido} creado
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Pedido pedido, @Context UriInfo uriInfo) {
        Pedido newProduct = pedidoDao.addPedido(pedido);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI newUri = uriBuilder.path(String.valueOf(newProduct.getId())).build();
        return Response.created(newUri).entity(newProduct).status(Response.Status.CREATED).build();
    }


    /** Maneja la solicitud PUT en el endpoint /order/{id} para actualizar un {@link Pedido}
     * @param id id del {@link Pedido} a actualizar
     * @param pedido {@link Pedido} que contiene la información nueva a actualizar
     * @return El {@link Pedido} actualizado o un código de estado 404 si no se encuentra el pedido
     */
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

    /** Maneja la solicitud DELETE en el endpoint /order/{id} para eliminar un pedido
     * @param id El id del {@link Pedido} a borrar
     * @return Un código de estado 204 si se logra borrar el pedido y un 404 si no se encuentra el pedido
     */
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
