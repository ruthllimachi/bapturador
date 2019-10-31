/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParActividad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "api_item")
public class ApiItem extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long idItem;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "codigo_producto_sin")
    private Long codigoProductoSin;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "codigo_actividad", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParActividad parActividad;

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
     * @return the idItem
     */
    public Long getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    

    /**
     * @return the parActividad
     */
    public ParActividad getParActividad() {
        return parActividad;
    }

    /**
     * @param parActividad the parActividad to set
     */
    public void setParActividad(ParActividad parActividad) {
        this.parActividad = parActividad;
    }   

    /**
     * @return the codigoProductoSin
     */
    public Long getCodigoProductoSin() {
        return codigoProductoSin;
    }

    /**
     * @param codigoProductoSin the codigoProductoSin to set
     */
    public void setCodigoProductoSin(Long codigoProductoSin) {
        this.codigoProductoSin = codigoProductoSin;
    }

  
}
