package com.arqapps.pedidosapi.database;

import com.arqapps.pedidosapi.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Pedido> pedidos = new ArrayList<>();

    public static List<Pedido> getPedidos() {
        return pedidos;
    }
}
