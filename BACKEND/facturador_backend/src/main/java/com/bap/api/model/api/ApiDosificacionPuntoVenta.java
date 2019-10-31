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
@Table(name = "api_dosificacion_punto_venta")
public class ApiDosificacionPuntoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dosificacion_punto_venta")
    private Long idDosificacionPuntoVenta;

    @ManyToOne()
    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion", nullable = true)
    private ApiDosificacion apiDosificacion;

    @ManyToOne()
    @JoinColumn(name = "id_punto_venta", referencedColumnName = "id_punto_venta", nullable = true)
    private ApiPuntoVenta apiPuntoVenta;

    /**
     * @return the idDosificacionPuntoVenta
     */
    public Long getIdDosificacionPuntoVenta() {
        return idDosificacionPuntoVenta;
    }

    /**
     * @param idDosificacionPuntoVenta the idDosificacionPuntoVenta to set
     */
    public void setIdDosificacionPuntoVenta(Long idDosificacionPuntoVenta) {
        this.idDosificacionPuntoVenta = idDosificacionPuntoVenta;
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
