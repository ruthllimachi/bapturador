package com.bap.api.dto;

import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import java.util.List;

public class Respuesta {

    private List<ParMensajeServicio> listaParMensajeServicio;
    private boolean transaccion;
    private ParMensajeFacturador parMensajeFacturador;

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

}
