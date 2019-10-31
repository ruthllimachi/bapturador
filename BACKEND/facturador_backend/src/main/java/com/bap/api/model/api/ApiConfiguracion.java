/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParEstado;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_configuracion")
public class ApiConfiguracion extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion")
    private Long idConfiguracion;

    @Size(max = 100, message = "CUFD no debe ser mayor a 100 caracteres")
    @Column(name = "cufd")
    private String cufd;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_vigencia", nullable = false)
    private LocalDateTime fechaVigencia;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "utc_fecha_vigencia", nullable = false)
    private String utcFechaVigencia;

    @Column(name = "utc_fecha_hora", nullable = false)
    private String utcFechaHora;

    @ManyToOne()
    @JoinColumn(name = "estado_configuracion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEstado estadoConfiguracion;

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
     * @return the estadoConfiguracion
     */
    public ParEstado getEstadoConfiguracion() {
        return estadoConfiguracion;
    }

    /**
     * @param estadoConfiguracion the estadoConfiguracion to set
     */
    public void setEstadoConfiguracion(ParEstado estadoConfiguracion) {
        this.estadoConfiguracion = estadoConfiguracion;
    }

    /**
     * @return the utcFechaVigencia
     */
    public String getUtcFechaVigencia() {
        return utcFechaVigencia;
    }

    /**
     * @param utcFechaVigencia the utcFechaVigencia to set
     */
    public void setUtcFechaVigencia(String utcFechaVigencia) {
        this.utcFechaVigencia = utcFechaVigencia;
    }

    /**
     * @return the utcFechaHora
     */
    public String getUtcFechaHora() {
        return utcFechaHora;
    }

    /**
     * @param utcFechaHora the utcFechaHora to set
     */
    public void setUtcFechaHora(String utcFechaHora) {
        this.utcFechaHora = utcFechaHora;
    }

}
