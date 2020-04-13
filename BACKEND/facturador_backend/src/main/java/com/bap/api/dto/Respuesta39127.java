package com.bap.api.dto;

import com.api.fesc_39127.EventosSignificativosDto;
import com.api.fesc_39127.RespuestaCodigosMensajesSoapDto;
import java.util.List;

public class Respuesta39127 {

    private Long codigoRecepcionEventoSignificativo;
    private List<EventosSignificativosDto> listaCodigos;
    private List<RespuestaCodigosMensajesSoapDto> listaCodigosRespuestas;
    private boolean transaccion;

    /**
     * @return the codigoRecepcionEventoSignificativo
     */
    public Long getCodigoRecepcionEventoSignificativo() {
        return codigoRecepcionEventoSignificativo;
    }

    /**
     * @param codigoRecepcionEventoSignificativo the
     * codigoRecepcionEventoSignificativo to set
     */
    public void setCodigoRecepcionEventoSignificativo(Long codigoRecepcionEventoSignificativo) {
        this.codigoRecepcionEventoSignificativo = codigoRecepcionEventoSignificativo;
    }

    /**
     * @return the listaCodigos
     */
    public List<EventosSignificativosDto> getListaCodigos() {
        return listaCodigos;
    }

    /**
     * @param listaCodigos the listaCodigos to set
     */
    public void setListaCodigos(List<EventosSignificativosDto> listaCodigos) {
        this.listaCodigos = listaCodigos;
    }

    /**
     * @return the listaCodigosRespuestas
     */
    public List<RespuestaCodigosMensajesSoapDto> getListaCodigosRespuestas() {
        return listaCodigosRespuestas;
    }

    /**
     * @param listaCodigosRespuestas the listaCodigosRespuestas to set
     */
    public void setListaCodigosRespuestas(List<RespuestaCodigosMensajesSoapDto> listaCodigosRespuestas) {
        this.listaCodigosRespuestas = listaCodigosRespuestas;
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
