package com.FINALPROJECT.MotoRider.dto;

public class MotoToPurchaseDTO {

    private long id;
    private int cantidad;

    public MotoToPurchaseDTO(long id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
