package com.arqapps.pedidosapi.dao;

import com.arqapps.pedidosapi.database.Database;
import com.arqapps.pedidosapi.model.Pedido;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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

    public Pedido getPedido(int id) {
        Optional<Pedido> pedido = pedidos.stream()
                .filter(p -> id == p.getId())
                .findFirst();
        return pedido.orElse(null);
    }

    public Pedido addPedido(Pedido pedido) {
        int max ;
        int size = pedidos.size();
        if (size > 0)
            max = Math.toIntExact(pedidos.get(size - 1).getId() + 1);
        else
            max = 1;
        pedido.setId(max);
        pedidos.add(pedido);

        return pedido;
    }


    public Pedido updatePedido(Pedido pedido) {
        OptionalInt index = getPos(pedido.getId());
        if (index.isPresent()) {
            pedidos.set(index.getAsInt(), pedido);
            return pedido;
        }
        return null;
    }

    public Pedido deletePedido(long id) {
        OptionalInt index = getPos(id);
        if (index.isPresent()) {
            pedidos.remove(index.getAsInt());
            return pedidos.get(index.getAsInt());
        }
        return null;
    }

    private OptionalInt getPos(long id) {
        return IntStream.range(0, pedidos.size())
                .filter(i -> pedidos.get(i).getId() == id)
                .findFirst();
    }
}
