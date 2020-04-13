/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParEventoSignificativo;
import com.bap.api.model.par.ParTipoEventoSignificativo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.persistence.Transient;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_evento_significativo")
public class ApiEventoSignificativo extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_significativo")
    private Long idEventoSignificativo;

    @ManyToOne()
    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion", nullable = true)
    private ApiConfiguracion apiConfiguracion;

    @ManyToOne()
    @JoinColumn(name = "evento_significativo", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParEventoSignificativo eventoSignificativo;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_evento_inicio")
    private LocalDateTime fechaEventoInicio;

    @Column(name = "utc_fecha_evento_inicio")
    private String utcFechaEventoInicio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "codigo_recepcion")
    private Long codigoRecepcion;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_evento_fin")
    private LocalDateTime fechaEventoFin;

    @Column(name = "utc_fecha_evento_fin")
    private String utcFechaEventoFin;

    @ManyToOne()
    @JoinColumn(name = "tipo_evento_significativo", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoEventoSignificativo tipoEventoSignificativo;
    
    @Transient
    private Long codigoEvento;

    /**
     * @return the idEventoSignificativo
     */
    public Long getIdEventoSignificativo() {
        return idEventoSignificativo;
    }

    /**
     * @param idEventoSignificativo the idEventoSignificativo to set
     */
    public void setIdEventoSignificativo(Long idEventoSignificativo) {
        this.idEventoSignificativo = idEventoSignificativo;
    }

    /**
     * @return the eventoSignificativo
     */
    public ParEventoSignificativo getEventoSignificativo() {
        return eventoSignificativo;
    }

    /**
     * @param eventoSignificativo the eventoSignificativo to set
     */
    public void setEventoSignificativo(ParEventoSignificativo eventoSignificativo) {
        this.eventoSignificativo = eventoSignificativo;
    }

    /**
     * @return the fechaEventoInicio
     */
    public LocalDateTime getFechaEventoInicio() {
        return fechaEventoInicio;
    }

    /**
     * @param fechaEventoInicio the fechaEventoInicio to set
     */
    public void setFechaEventoInicio(LocalDateTime fechaEventoInicio) {
        this.fechaEventoInicio = fechaEventoInicio;
    }

    /**
     * @return the utcFechaEventoInicio
     */
    public String getUtcFechaEventoInicio() {
        return utcFechaEventoInicio;
    }

    /**
     * @param utcFechaEventoInicio the utcFechaEventoInicio to set
     */
    public void setUtcFechaEventoInicio(String utcFechaEventoInicio) {
        this.utcFechaEventoInicio = utcFechaEventoInicio;
    }  

    /**
     * @return the fechaEventoFin
     */
    public LocalDateTime getFechaEventoFin() {
        return fechaEventoFin;
    }

    /**
     * @param fechaEventoFin the fechaEventoFin to set
     */
    public void setFechaEventoFin(LocalDateTime fechaEventoFin) {
        this.fechaEventoFin = fechaEventoFin;
    }

    /**
     * @return the utcFechaEventoFin
     */
    public String getUtcFechaEventoFin() {
        return utcFechaEventoFin;
    }

    /**
     * @param utcFechaEventoFin the utcFechaEventoFin to set
     */
    public void setUtcFechaEventoFin(String utcFechaEventoFin) {
        this.utcFechaEventoFin = utcFechaEventoFin;
    }

    /**
     * @return the tipoEventoSignificativo
     */
    public ParTipoEventoSignificativo getTipoEventoSignificativo() {
        return tipoEventoSignificativo;
    }

    /**
     * @param tipoEventoSignificativo the tipoEventoSignificativo to set
     */
    public void setTipoEventoSignificativo(ParTipoEventoSignificativo tipoEventoSignificativo) {
        this.tipoEventoSignificativo = tipoEventoSignificativo;
    }

    /**
     * @return the apiConfiguracion
     */
    public ApiConfiguracion getApiConfiguracion() {
        return apiConfiguracion;
    }

    /**
     * @param apiConfiguracion the apiConfiguracion to set
     */
    public void setApiConfiguracion(ApiConfiguracion apiConfiguracion) {
        this.apiConfiguracion = apiConfiguracion;
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
     * @return the codigoRecepcion
     */
    public Long getCodigoRecepcion() {
        return codigoRecepcion;
    }

    /**
     * @param codigoRecepcion the codigoRecepcion to set
     */
    public void setCodigoRecepcion(Long codigoRecepcion) {
        this.codigoRecepcion = codigoRecepcion;
    }

    /**
     * @return the codigoEvento
     */
    public Long getCodigoEvento() {
        return codigoEvento;
    }

    /**
     * @param codigoEvento the codigoEvento to set
     */
    public void setCodigoEvento(Long codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

}
