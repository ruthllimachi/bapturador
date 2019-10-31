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
@Table(name = "api_configuracion_punto_venta")
public class ApiConfiguracionPuntoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_punto_venta")
    private Long idConfiguracionPuntoVenta;
    
    @ManyToOne()
    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion", nullable = true)
    private ApiConfiguracion apiConfiguracion;
    
    
    @ManyToOne()
    @JoinColumn(name = "id_punto_venta", referencedColumnName = "id_punto_venta", nullable = true)
    private ApiPuntoVenta apiPuntoVenta;

    /**
     * @return the idConfiguracionPuntoVenta
     */
    public Long getIdConfiguracionPuntoVenta() {
        return idConfiguracionPuntoVenta;
    }

    /**
     * @param idConfiguracionPuntoVenta the idConfiguracionPuntoVenta to set
     */
    public void setIdConfiguracionPuntoVenta(Long idConfiguracionPuntoVenta) {
        this.idConfiguracionPuntoVenta = idConfiguracionPuntoVenta;
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
     * @return the apiPuntoVenta
     */
    public ApiPuntoVenta getApiPuntoVenta() {
        return apiPuntoVenta;
    }

    /**
     * @param apiPuntoVenta the apiPuntoVenta to set
     */
    public void setApiPuntoVenta(ApiPuntoVenta apiPuntoVenta) {
        this.apiPuntoVenta = apiPuntoVenta;
    }

    
}
