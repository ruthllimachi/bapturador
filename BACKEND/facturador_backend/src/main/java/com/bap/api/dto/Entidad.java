/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;

/**
 *
 * @author ruth
 */
public class Entidad {

    private Long idConfiguracion;
    private Long idDosificacion;
    private int codigoModalidad;
    private String cuis;
    private String cufd;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaVigencia;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fechaHora;   
    private String archivo;
    private String hash;
  

    /**
     * @return the codigoModalidad
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }

    /**
     * @param codigoModalidad the codigoModalidad to set
     */
    public void setCodigoModalidad(int codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    /**
     * @return the cuis
     */
    public String getCuis() {
        return cuis;
    }

    /**
     * @param cuis the cuis to set
     */
    public void setCuis(String cuis) {
        this.cuis = cuis;
    }

    /**
     * @return the cufd
     */
    public String getCufd() {
        return cufd;
    }

    /**
     * @param cufd the cufd to set
     */
    public void setCufd(String cufd) {
        this.cufd = cufd;
    }

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
     * @return the idConfiguracion
     */
    public Long getIdConfiguracion() {
        return idConfiguracion;
    }

    /**
     * @param idConfiguracion the idConfiguracion to set
     */
    public void setIdConfiguracion(Long idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    /**
     * @return the idDosificacion
     */
    public Long getIdDosificacion() {
        return idDosificacion;
    }

    /**
     * @param idDosificacion the idDosificacion to set
     */
    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

 
}
