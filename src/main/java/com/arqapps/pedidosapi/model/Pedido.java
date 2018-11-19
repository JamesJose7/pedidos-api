package com.arqapps.pedidosapi.model;

public class Pedido {
    private long id;
    private String description;
    private long costoEnCentavos;
    private boolean estaCompletado;

    public Pedido() {}

    public Pedido(long id, String description, long costoEnCentavos, boolean estaCompletado) {
        this.id = id;
        this.description = description;
        this.costoEnCentavos = costoEnCentavos;
        this.estaCompletado = estaCompletado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCostoEnCentavos() {
        return costoEnCentavos;
    }

    public void setCostoEnCentavos(long costoEnCentavos) {
        this.costoEnCentavos = costoEnCentavos;
    }

    public boolean isEstaCompletado() {
        return estaCompletado;
    }

    public void setEstaCompletado(boolean estaCompletado) {
        this.estaCompletado = estaCompletado;
    }
}
