package com.bap.api.dto;

import com.bap.api.model.api.ApiEventoSignificativo;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import java.util.List;

public class Respuesta {

    private List<ParMensajeServicio> listaParMensajeServicio;
    private boolean transaccion;
    private ParMensajeFacturador parMensajeFacturador;
    private Long idDocumentoFiscal;    
    private List<ApiPuntoVenta> listaApiPuntoVenta;
    private List<ApiEventoSignificativo> listaApiEventoSignificativo;

    /**
     * @return the listaParMensajeServicio
     */
    public List<ParMensajeServicio> getListaParMensajeServicio() {
        return listaParMensajeServicio;
    }

    /**
     * @param listaParMensajeServicio the listaParMensajeServicio to set
     */
    public void setListaParMensajeServicio(List<ParMensajeServicio> listaParMensajeServicio) {
        this.listaParMensajeServicio = listaParMensajeServicio;
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
     * @return the idDocumentoFiscal
     */
    public Long getIdDocumentoFiscal() {
        return idDocumentoFiscal;
    }

    /**
     * @param idDocumentoFiscal the idDocumentoFiscal to set
     */
    public void setIdDocumentoFiscal(Long idDocumentoFiscal) {
        this.idDocumentoFiscal = idDocumentoFiscal;
    }

    /**
     * @return the listaApiPuntoVenta
     */
    public List<ApiPuntoVenta> getListaApiPuntoVenta() {
        return listaApiPuntoVenta;
    }

    /**
     * @param listaApiPuntoVenta the listaApiPuntoVenta to set
     */
    public void setListaApiPuntoVenta(List<ApiPuntoVenta> listaApiPuntoVenta) {
        this.listaApiPuntoVenta = listaApiPuntoVenta;
    }

    /**
     * @return the listaApiEventoSignificativo
     */
    public List<ApiEventoSignificativo> getListaApiEventoSignificativo() {
        return listaApiEventoSignificativo;
    }

    /**
     * @param listaApiEventoSignificativo the listaApiEventoSignificativo to set
     */
    public void setListaApiEventoSignificativo(List<ApiEventoSignificativo> listaApiEventoSignificativo) {
        this.listaApiEventoSignificativo = listaApiEventoSignificativo;
    }

}
