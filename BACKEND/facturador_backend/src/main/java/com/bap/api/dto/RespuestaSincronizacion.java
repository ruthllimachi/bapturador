package com.bap.api.dto;

import com.bap.api.model.par.ParActividad;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import java.util.List;

public class RespuestaSincronizacion {

    private Long codigo;
    private String descripcion;
    private String grupo;
    private ParActividad parActividad;
    private List<ParMensajeServicio> listaParMensajeServicio;
    private boolean transaccion;
    private long codigoRecepcion;
    private ParMensajeFacturador parMensajeFacturador;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaEnvio;
    private String utcFechaEnvio;
    private String fileXMLSinFirma;
    private String fileXMLFirmado;
    private String cuf;
    private Long codigoSolicitud;

    public String getCuf() {
        return cuf;
    }

    public void setCuf(String cuf) {
        this.cuf = cuf;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the parActividad
     */
    public ParActividad getParActividad() {
        return parActividad;
    }

    /**
     * @param parActividad the parActividad to set
     */
    public void setParActividad(ParActividad parActividad) {
        this.parActividad = parActividad;
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
     * @return the utcFechaEnvio
     */
    public String getUtcFechaEnvio() {
        return utcFechaEnvio;
    }

    /**
     * @param utcFechaEnvio the utcFechaEnvio to set
     */
    public void setUtcFechaEnvio(String utcFechaEnvio) {
        this.utcFechaEnvio = utcFechaEnvio;
    }    

    /**
     * @return the fileXMLSinFirma
     */
    public String getFileXMLSinFirma() {
        return fileXMLSinFirma;
    }

    /**
     * @param fileXMLSinFirma the fileXMLSinFirma to set
     */
    public void setFileXMLSinFirma(String fileXMLSinFirma) {
        this.fileXMLSinFirma = fileXMLSinFirma;
    }

    /**
     * @return the fileXMLFirmado
     */
    public String getFileXMLFirmado() {
        return fileXMLFirmado;
    }

    /**
     * @param fileXMLFirmado the fileXMLFirmado to set
     */
    public void setFileXMLFirmado(String fileXMLFirmado) {
        this.fileXMLFirmado = fileXMLFirmado;
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
     * @return the fechaEnvio
     */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

  
}
