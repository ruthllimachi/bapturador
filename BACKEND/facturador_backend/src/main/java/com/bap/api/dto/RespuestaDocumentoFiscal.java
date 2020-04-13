/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.par.ParMensajeFacturador;

/**
 *
 * @author ruth
 */
public class RespuestaDocumentoFiscal {

    private boolean transaccion;
    private ParMensajeFacturador parMensajeFacturador;
    private ApiFactura apiFactura;
    private SolicitudCliente solicitud;

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

    /**
     * @return the parMensajeFacturador
     */
    public ParMensajeFacturador getParMensajeFacturador() {
        return parMensajeFacturador;
    }

    /**
     * @param parMensajeFacturador the parMensajeFacturador to set
     */
    public void setParMensajeFacturador(ParMensajeFacturador parMensajeFacturador) {
        this.parMensajeFacturador = parMensajeFacturador;
    }

    /**
     * @return the apiFactura
     */
    public ApiFactura getApiFactura() {
        return apiFactura;
    }

    /**
     * @param apiFactura the apiFactura to set
     */
    public void setApiFactura(ApiFactura apiFactura) {
        this.apiFactura = apiFactura;
    }

    /**
     * @return the solicitud
     */
    public SolicitudCliente getSolicitud() {
        return solicitud;
    }

    /**
     * @param solicitud the solicitud to set
     */
    public void setSolicitud(SolicitudCliente solicitud) {
        this.solicitud = solicitud;
    }
}
