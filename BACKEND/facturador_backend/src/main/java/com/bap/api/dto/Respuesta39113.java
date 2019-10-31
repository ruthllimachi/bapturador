/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import com.api.fesc_39113.RecepcionErrorDetalleDto;
import java.util.List;

/**
 *
 * @author ruth
 */
public class Respuesta39113 {

    private int codigoEstado;
    private long codigoRecepcion;
    private List<Integer> listaCodigosRespuestas;
    private List<String> listaDescripcionesRespuestas;
    private List<RecepcionErrorDetalleDto> listaErroresDetalles;
    private boolean transaccion;

    /**
     * @return the codigoEstado
     */
    public int getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param codigoEstado the codigoEstado to set
     */
    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     * @return the codigoRecepcion
     */
    public long getCodigoRecepcion() {
        return codigoRecepcion;
    }

    /**
     * @param codigoRecepcion the codigoRecepcion to set
     */
    public void setCodigoRecepcion(long codigoRecepcion) {
        this.codigoRecepcion = codigoRecepcion;
    }

    /**
     * @return the listaCodigosRespuestas
     */
    public List<Integer> getListaCodigosRespuestas() {
        return listaCodigosRespuestas;
    }

    /**
     * @param listaCodigosRespuestas the listaCodigosRespuestas to set
     */
    public void setListaCodigosRespuestas(List<Integer> listaCodigosRespuestas) {
        this.listaCodigosRespuestas = listaCodigosRespuestas;
    }

    /**
     * @return the listaDescripcionesRespuestas
     */
    public List<String> getListaDescripcionesRespuestas() {
        return listaDescripcionesRespuestas;
    }

    /**
     * @param listaDescripcionesRespuestas the listaDescripcionesRespuestas to set
     */
    public void setListaDescripcionesRespuestas(List<String> listaDescripcionesRespuestas) {
        this.listaDescripcionesRespuestas = listaDescripcionesRespuestas;
    }

    /**
     * @return the listaErroresDetalles
     */
    public List<RecepcionErrorDetalleDto> getListaErroresDetalles() {
        return listaErroresDetalles;
    }

    /**
     * @param listaErroresDetalles the listaErroresDetalles to set
     */
    public void setListaErroresDetalles(List<RecepcionErrorDetalleDto> listaErroresDetalles) {
        this.listaErroresDetalles = listaErroresDetalles;
    }

    /**
     * @return the transaccion
     */
    public boolean isTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(boolean transaccion) {
        this.transaccion = transaccion;
    }
}
