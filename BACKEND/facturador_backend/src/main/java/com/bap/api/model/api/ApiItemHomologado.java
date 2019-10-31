/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParUnidadMedida;

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
@Table(name = "api_item_homologado")
public class ApiItemHomologado extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_homologado")
    private Long idItemHomologado;

    @Column(name = "codigo_producto")
    private String codigoProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "unidad_medida", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParUnidadMedida parUnidadMedida;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "numero_imei")
    private String numeroImei;
    
    @Column(name = "codigo_nandina")
    private String codigoNandina;

    @ManyToOne()
    @JoinColumn(name = "id_item", referencedColumnName = "id_item", nullable = true)
    private ApiItem apiItem;

    /**
     * @return the idItemHomologado
     */
    public Long getIdItemHomologado() {
        return idItemHomologado;
    }

    /**
     * @param idItemHomologado the idItemHomologado to set
     */
    public void setIdItemHomologado(Long idItemHomologado) {
        this.idItemHomologado = idItemHomologado;
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
     * @return the parUnidadMedida
     */
    public ParUnidadMedida getParUnidadMedida() {
        return parUnidadMedida;
    }

    /**
     * @param parUnidadMedida the parUnidadMedida to set
     */
    public void setParUnidadMedida(ParUnidadMedida parUnidadMedida) {
        this.parUnidadMedida = parUnidadMedida;
    }

    /**
     * @return the numeroSerie
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * @param numeroSerie the numeroSerie to set
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * @return the apiItem
     */
    public ApiItem getApiItem() {
        return apiItem;
    }

    /**
     * @param apiItem the apiItem to set
     */
    public void setApiItem(ApiItem apiItem) {
        this.apiItem = apiItem;
    }

    /**
     * @return the numeroImei
     */
    public String getNumeroImei() {
        return numeroImei;
    }

    /**
     * @param numeroImei the numeroImei to set
     */
    public void setNumeroImei(String numeroImei) {
        this.numeroImei = numeroImei;
    }

    /**
     * @return the codigoProducto
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * @param codigoProducto the codigoProducto to set
     */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
     * @return the codigoNandina
     */
    public String getCodigoNandina() {
        return codigoNandina;
    }

    /**
     * @param codigoNandina the codigoNandina to set
     */
    public void setCodigoNandina(String codigoNandina) {
        this.codigoNandina = codigoNandina;
    }
  

}
