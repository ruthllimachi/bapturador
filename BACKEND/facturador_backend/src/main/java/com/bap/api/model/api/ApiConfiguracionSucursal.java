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
@Table(name = "api_configuracion_sucursal")
public class ApiConfiguracionSucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_sucursal")
    private Long idConfiguracionSucursal;
    
    @ManyToOne()
    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion", nullable = true)
    private ApiConfiguracion apiConfiguracion;
    
    
    @ManyToOne()
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    private ApiSucursal apiSucursal;

    /**
     * @return the idConfiguracionSucursal
     */
    public Long getIdConfiguracionSucursal() {
        return idConfiguracionSucursal;
    }

    /**
     * @param idConfiguracionSucursal the idConfiguracionSucursal to set
     */
    public void setIdConfiguracionSucursal(Long idConfiguracionSucursal) {
        this.idConfiguracionSucursal = idConfiguracionSucursal;
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
