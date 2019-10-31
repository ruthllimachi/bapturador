package com.bap.api.dto;

import com.api.fesc_39268.RespuestaCodigosMensajesSoapDto;
import com.bap.api.model.par.ParMensajeServicio;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import java.util.List;

public class Respuesta39268 {

    private String codigoCufd;

    @JsonSerialize(using = ToStringSerializer.class) // ISODate
    private LocalDateTime fechaVigencia;

    private List<ParMensajeServicio> listaParMensajeServicio;

    private List<RespuestaCodigosMensajesSoapDto> listaRespuestaCodigosMensajesSoapDto;

    private boolean transaccion;

    public String getCodigoCufd() {
        return codigoCufd;
    }

    public void setCodigoCufd(String codigoCufd) {
        this.codigoCufd = codigoCufd;
    }

//    public int getCodigoMensaje() {
//        return codigoMensaje;
//    }
//
//    public void setCodigoMensaje(int codigoMensaje) {
//        this.codigoMensaje = codigoMensaje;
//    }
//    /**
//     * @return the descripcion
//     */
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    /**
//     * @param descripcion the descripcion to set
//     */
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
    /**
     * @return the fechaVigencia
     */
    public LocalDateTime getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia the fechaVigencia to set
     */
    public void setFechaVigencia(LocalDateTime fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
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
