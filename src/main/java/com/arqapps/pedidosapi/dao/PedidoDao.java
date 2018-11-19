package com.arqapps.pedidosapi.dao;

import com.arqapps.pedidosapi.database.Database;
import com.arqapps.pedidosapi.model.Pedido;

import java.util.List;

public class PedidoDao {
    private static List<Pedido> pedidos = Database.getPedidos();

    public PedidoDao() {
        pedidos.add(new Pedido(1, "pedido", 240L, false));
        pedidos.add(new Pedido(2, "pedido", 240L, false));
        pedidos.add(new Pedido(3, "pedido", 240L, false));
        pedidos.add(new Pedido(4, "pedido", 240L, false));
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
