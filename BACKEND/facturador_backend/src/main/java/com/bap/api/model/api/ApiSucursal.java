/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParCondicion;
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
import javax.validation.constraints.Size;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_sucursal")
public class ApiSucursal extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Max(value = 9999, message = "El numero de sucursal no debe ser mayor a 4 digitos")
    @Column(name = "codigo_sucursal", nullable = false)
    private Long codigoSucursal;

    @Column(name = "nombre_sucursal", nullable = false, length = 200)
    private String nombreSucursal;

    @Size(max = 300, message = "Direccion no debe ser mayor a 300 caracteres")
    @Column(name = "direccion", nullable = false, length = 300)
    private String direccion;

    @ManyToOne()
    @JoinColumn(name = "tiene_cuis", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCondicion tieneCuis;

    @Column(name = "zona", nullable = false)
    private String zona;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "telefonos", nullable = false)
    private String telefonos;

    @Transient
    private String cuis;

    @Transient
    private int codigoModalidad;

    /**
     * @return the idSucursal
     */
    public Long getIdSucursal() {
        return idSucursal;
    }

    /**
     * @param idSucursal the idSucursal to set
     */
    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the codigoSucursal
     */
    public Long getCodigoSucursal() {
        return codigoSucursal;
    }

    /**
     * @param codigoSucursal the codigoSucursal to set
     */
    public void setCodigoSucursal(Long codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    /**
     * @return the nombreSucursal
     */
    public String getNombreSucursal() {
        return nombreSucursal;
    }

    /**
     * @param nombreSucursal the nombreSucursal to set
     */
    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
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
     * @return the cuis
     */
    public String getCuis() {
        return cuis;
    }

    /**
     * @param cuis the cuis to set
     */
    public void setCuis(String cuis) {
        this.cuis = cuis;
    }

    /**
     * @return the codigoModalidad
     */
    public int getCodigoModalidad() {
        return codigoModalidad;
    }

    /**
     * @param codigoModalidad the codigoModalidad to set
     */
    public void setCodigoModalidad(int codigoModalidad) {
        this.codigoModalidad = codigoModalidad;
    }

    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the telefonos
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * @param telefonos the telefonos to set
     */
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

}
