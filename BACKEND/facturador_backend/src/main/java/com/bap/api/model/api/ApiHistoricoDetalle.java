/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_historico_detalle")
public class ApiHistoricoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_detalle")
    private Long idHistoricoDetalle;
 
    @Column(name = "codigo_mensaje")
    private Long codigoMensaje;   
    
    @Column(name = "mensaje")
    private String mensaje;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_historico", nullable = false)
    private ApiHistorico apiHistorico;

    /**
     * @return the idHistoricoDetalle
     */
    public Long getIdHistoricoDetalle() {
        return idHistoricoDetalle;
    }

    /**
     * @param idHistoricoDetalle the idHistoricoDetalle to set
     */
    public void setIdHistoricoDetalle(Long idHistoricoDetalle) {
        this.idHistoricoDetalle = idHistoricoDetalle;
    }
    
    /**
     * @return the codigoMensaje
     */
    public Long getCodigoMensaje() {
        return codigoMensaje;
    }

    /**
     * @param codigoMensaje the codigoMensaje to set
     */
    public void setCodigoMensaje(Long codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    /**
     * @return the apiHistorico
     */
    public ApiHistorico getApiHistorico() {
        return apiHistorico;
    }

    /**
     * @param apiHistorico the apiHistorico to set
     */
    public void setApiHistorico(ApiHistorico apiHistorico) {
        this.apiHistorico = apiHistorico;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
