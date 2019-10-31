package com.bap.api.dto;

import com.api.fesc_39117.RespuestaCodigosMensajesSoapDto;
import com.bap.api.model.par.ParMensajeServicio;
import java.util.List;

public class Respuesta39117 {

    private String codigoCuis;
    private Long codigoPuntoVenta;
    private String codigoSistema;
    private List<ParMensajeServicio> listaParMensajeServicio;
    private List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto;
    private boolean transaccion;

    public String getCodigoCuis() {
        return codigoCuis;
    }

    public void setCodigoCuis(String codigoCuis) {
        this.codigoCuis = codigoCuis;
    }

    public Long getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }

    public void setCodigoPuntoVenta(Long codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
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
     * @return the listaRespuestaCodigosMensajesSoapDto
     */
    public List<RespuestaCodigosMensajesSoapDto> getListaRespuestaCodigosMensajesSoapDto() {
        return listaRespuestaCodigosMensajesSoapDto;
    }

    /**
     * @param listaRespuestaCodigosMensajesSoapDto the
     * listaRespuestaCodigosMensajesSoapDto to set
     */
    public void setListaRespuestaCodigosMensajesSoapDto(List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto) {
        this.listaRespuestaCodigosMensajesSoapDto = listaRespuestaCodigosMensajesSoapDto;
    }

}
