package com.bap.api.dto;


public class Respuesta39127 {

    private int codigoMensaje;
  
    private int codigoSolicitud;
    protected Long codigoProducto;
    protected String descripcionProducto;

    public int getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(int codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public int getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(int codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

}
