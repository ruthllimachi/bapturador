/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_actividad")
public class ApiActividad extends AbstractAudit {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long idActividad;

    @Column(name = "id_empresa")
    private Long idEmpresa;
    
    @Column(name = "codigo_actividad")
    private Long codigoActividad;

    @Column(name = "descripcion")
    private String descripcion;

    
    /**
     * @return the idEmpresa
     */
    public Long getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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
     * @return the idActividad
     */
    public Long getIdActividad() {
        return idActividad;
    }

    /**
     * @param idActividad the idActividad to set
     */
    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * @return the codigoActividad
     */
    public Long getCodigoActividad() {
        return codigoActividad;
    }

    /**
     * @param codigoActividad the codigoActividad to set
     */
    public void setCodigoActividad(Long codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

}
