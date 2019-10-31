/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

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
@Table(name = "api_dosificacion_sucursal")
public class ApiDosificacionSucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dosificacion_sucursal")
    private Long idDosificacionSucursal;
    
    @ManyToOne()
    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion", nullable = true)
    private ApiDosificacion apiDosificacion;
    
    
    @ManyToOne()
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    private ApiSucursal apiSucursal;

    /**
     * @return the idDosificacionSucursal
     */
    public Long getIdDosificacionSucursal() {
        return idDosificacionSucursal;
    }

    /**
     * @param idDosificacionSucursal the idDosificacionSucursal to set
     */
    public void setIdDosificacionSucursal(Long idDosificacionSucursal) {
        this.idDosificacionSucursal = idDosificacionSucursal;
    }

    /**
     * @return the apiDosificacion
     */
    public ApiDosificacion getApiDosificacion() {
        return apiDosificacion;
    }

    /**
     * @param apiDosificacion the apiDosificacion to set
     */
    public void setApiDosificacion(ApiDosificacion apiDosificacion) {
        this.apiDosificacion = apiDosificacion;
    }

    /**
     * @return the apiSucursal
     */
    public ApiSucursal getApiSucursal() {
        return apiSucursal;
    }

    /**
     * @param apiSucursal the apiSucursal to set
     */
    public void setApiSucursal(ApiSucursal apiSucursal) {
        this.apiSucursal = apiSucursal;
    }
    

}
