/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParTipoPuntoVenta;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_punto_venta")
public class ApiPuntoVenta extends AbstractAudit {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punto_venta")
    private Long idPuntoVenta;
    
    @ManyToOne()
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    private ApiSucursal apiSucursal;

    @Max(value = 9999, message = "El codigo de punto de venta no debe ser mayor a 4 digitos")
    @Column(name = "codigo_punto_venta", nullable = false)
    private Long codigoPuntoVenta;

    @Column(name = "nombre_punto_venta", nullable = false, length = 100)
    private String nombrePuntoVenta;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "tipo_punto_venta", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoPuntoVenta parTipoPuntoVenta;
    
    @ManyToOne()
    @JoinColumn(name = "tiene_cuis", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCondicion tieneCuis;
    
    @Transient
    private String usuario;

    /**
     * @return the idPuntoVenta
     */
    public Long getIdPuntoVenta() {
        return idPuntoVenta;
    }

    /**
     * @param idPuntoVenta the idPuntoVenta to set
     */
    public void setIdPuntoVenta(Long idPuntoVenta) {
        this.idPuntoVenta = idPuntoVenta;
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

    /**
     * @return the nombrePuntoVenta
     */
    public String getNombrePuntoVenta() {
        return nombrePuntoVenta;
    }

    /**
     * @param nombrePuntoVenta the nombrePuntoVenta to set
     */
    public void setNombrePuntoVenta(String nombrePuntoVenta) {
        this.nombrePuntoVenta = nombrePuntoVenta;
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
     * @return the parTipoPuntoVenta
     */
    public ParTipoPuntoVenta getParTipoPuntoVenta() {
        return parTipoPuntoVenta;
    }

    /**
     * @param parTipoPuntoVenta the parTipoPuntoVenta to set
     */
    public void setParTipoPuntoVenta(ParTipoPuntoVenta parTipoPuntoVenta) {
        this.parTipoPuntoVenta = parTipoPuntoVenta;
    }

    /**
     * @return the codigoPuntoVenta
     */
    public Long getCodigoPuntoVenta() {
        return codigoPuntoVenta;
    }

    /**
     * @param codigoPuntoVenta the codigoPuntoVenta to set
     */
    public void setCodigoPuntoVenta(Long codigoPuntoVenta) {
        this.codigoPuntoVenta = codigoPuntoVenta;
    }

    /**
     * @return the tieneCuis
     */
    public ParCondicion getTieneCuis() {
        return tieneCuis;
    }

    /**
     * @param tieneCuis the tieneCuis to set
     */
    public void setTieneCuis(ParCondicion tieneCuis) {
        this.tieneCuis = tieneCuis;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
