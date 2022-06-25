package com.FINALPROJECT.MotoRider.dto;




public class ProductToPurchaseDTO {

    private long idProducto;

    private int cantidad;


    public ProductToPurchaseDTO(long idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }


    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
