package com.arqapps.pedidosapi.ws;

import com.arqapps.pedidosapi.dao.PedidoDao;
import com.arqapps.pedidosapi.model.Pedido;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("order")
public class PedidoService {
    private static PedidoDao pedidoDao = new PedidoDao();

    public PedidoService() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getAllPedidos() {
        return pedidoDao.getPedidos();
    }
}
