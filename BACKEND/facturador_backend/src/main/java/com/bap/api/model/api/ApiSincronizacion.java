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
@Table(name = "api_sincronizacion")
public class ApiSincronizacion extends AbstractAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sincronizacion")
    private Long idSincronizacion;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "grupo")
    private String grupo;

    
    /**
     * @return the idSincronizacion
     */
    public Long getIdSincronizacion() {
        return idSincronizacion;
    }

    /**
     * @param idSincronizacion the idSincronizacion to set
     */
    public void setIdSincronizacion(Long idSincronizacion) {
        this.idSincronizacion = idSincronizacion;
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

}
