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
        pedidos.add(new Pedido(1, "Ensalada de frutas", 254, false));
        pedidos.add(new Pedido(2, "Papas con pollo ", 789, false));
        pedidos.add(new Pedido(3, "Jugo de mora", 456, true));
        pedidos.add(new Pedido(4, "Banana Split", 789, false));
        pedidos.add(new Pedido(5, "Pizza familiar", 456, false));
        pedidos.add(new Pedido(6, "Lomo de res", 123, false));
        pedidos.add(new Pedido(7, "Parrillada JR", 456, true));
        pedidos.add(new Pedido(8, "Jarra de limonada", 123, false));
        pedidos.add(new Pedido(9, "Sanduche Mixto", 852, false));
        pedidos.add(new Pedido(10, "Cuarto de Chorizo", 936, true));
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /** Busca y retorna un {@link Pedido} según el id ingresado
     * @param id utilizado para buscar el pedido
     * @return Retorna un pedido si es econtrado o un null en caso contrario
     */
    public Pedido getPedido(int id) {
        Optional<Pedido> pedido = pedidos.stream()
                .filter(p -> id == p.getId())
                .findFirst();
        return pedido.orElse(null);
    }

    public Pedido addPedido(Pedido pedido) {
        int size = pedidos.size();
        int max = size > 0 ? Math.toIntExact(pedidos.get(size - 1).getId() + 1) : 1;
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

    /** Retorna la posición del Pedido encontrado según el id
     * @param id Id utilizado para la búsqueda del pedido
     * @return OptionalInt que servirá para obtener la posición encontrada o saber si no se encontró la posición
     */
    private OptionalInt getPos(long id) {
        return IntStream.range(0, pedidos.size())
                .filter(i -> pedidos.get(i).getId() == id)
                .findFirst();
    }
}
