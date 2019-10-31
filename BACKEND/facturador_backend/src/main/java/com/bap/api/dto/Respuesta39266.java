package com.bap.api.dto;

import com.api.fesc_39266.RespuestaCodigosMensajesSoapDto;
import com.bap.api.model.par.ParMensajeServicio;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import java.util.List;

public class Respuesta39266 {

    @JsonSerialize(using = ToStringSerializer.class) // ISODate    
    private LocalDateTime fechaHora;
    
    private List<ParMensajeServicio> listaParMensajeServicio;
    
    private List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto;
    
    private boolean transaccion;

    /**
     * @return the fechaHora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * @param fechaHora the fechaHora to set
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
     * @param listaRespuestaCodigosMensajesSoapDto the listaRespuestaCodigosMensajesSoapDto to set
     */
    public void setListaRespuestaCodigosMensajesSoapDto(List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto) {
        this.listaRespuestaCodigosMensajesSoapDto = listaRespuestaCodigosMensajesSoapDto;
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
