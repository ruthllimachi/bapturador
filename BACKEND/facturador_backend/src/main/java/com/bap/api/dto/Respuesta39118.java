package com.bap.api.dto;

import java.util.List;

import com.api.fesc_39118.ActividadesDto;
import com.api.fesc_39118.ParametricasDto;
import com.api.fesc_39118.ProductosDto;
import com.api.fesc_39118.RespuestaCodigosMensajesSoapDto;

public class Respuesta39118 {

    
    private List<ParametricasDto> listaParametricasDto;
    private List<ActividadesDto> listaActividadesDto;
    private List<ProductosDto> listaProductosDto;    
    /* para nuevo producto*/
    private Long codigoSolicitud;
    private String codigoActividad;
    private Long codigoProducto;
    private String descripcionProducto;
    /******/    
    private List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto;
    private boolean transaccion;

    public List<ParametricasDto> getListaParametricasDto() {
        return listaParametricasDto;
    }

    public void setListaParametricasDto(List<ParametricasDto> listaParametricasDto) {
        this.listaParametricasDto = listaParametricasDto;
    }

    public List<ActividadesDto> getListaActividadesDto() {
        return listaActividadesDto;
    }

    public void setListaActividadesDto(List<ActividadesDto> listaActividadesDto) {
        this.listaActividadesDto = listaActividadesDto;
    }

    public List<ProductosDto> getListaProductosDto() {
        return listaProductosDto;
    }

    public void setListaProductosDto(List<ProductosDto> listaProductosDto) {
        this.listaProductosDto = listaProductosDto;
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

    public boolean isTransaccion() {
        return transaccion;
    }

    public void setTransaccion(boolean transaccion) {
        this.transaccion = transaccion;
    }   

    /**
     * @return the listaRespuestaCodigosMensajesSoapDto
     */
    public List<RespuestaCodigosMensajesSoapDto> getListaRespuestaCodigosMensajesSoapDto() {
        return listaRespuestaCodigosMensajesSoapDto;
    }

    /**
     * @param listaRespuestaCodigosMensajesSoapDto the listaRespuestaCodigosMensajesSoapDto to set
     */
    public void setListaRespuestaCodigosMensajesSoapDto(List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto) {
        this.listaRespuestaCodigosMensajesSoapDto = listaRespuestaCodigosMensajesSoapDto;
    }

    /**
     * @return the codigoSolicitud
     */
    public Long getCodigoSolicitud() {
        return codigoSolicitud;
    }

    /**
     * @param codigoSolicitud the codigoSolicitud to set
     */
    public void setCodigoSolicitud(Long codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    /**
     * @return the codigoActividad
     */
    public String getCodigoActividad() {
        return codigoActividad;
    }

    /**
     * @param codigoActividad the codigoActividad to set
     */
    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

   

}
