/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_historico")
public class ApiHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Long idHistorico;

    @Column(name = "nit_emisor")
    private Long nitEmisor;

    @Column(name = "numero_factura")
    private Long numeroFactura;

    @Column(name = "cuf")
    private String cuf;

    @Column(name = "utc_fecha_emision")
    private String utcFechaEmision;

    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "id_punto_venta")
    private Long idPuntoVenta;

    @Column(name = "id_empresa_documento")
    private Long idEmpresaDocumento;

    @OneToMany(mappedBy = "apiHistorico", cascade = {CascadeType.ALL, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApiHistoricoDetalle> apiHistoricoDetalle;

    @Column(name = "fecha_alta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "usuario_alta", length = 30, nullable = false)
    private String usuarioAlta;

    /**
     * @return the idHistorico
     */
    public Long getIdHistorico() {
        return idHistorico;
    }

    /**
     * @param idHistorico the idHistorico to set
     */
    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    /**
     * @return the nitEmisor
     */
    public Long getNitEmisor() {
        return nitEmisor;
    }

    /**
     * @param nitEmisor the nitEmisor to set
     */
    public void setNitEmisor(Long nitEmisor) {
        this.nitEmisor = nitEmisor;
    }

    /**
     * @return the numeroFactura
     */
    public Long getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * @param numeroFactura the numeroFactura to set
     */
    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * @return the cuf
     */
    public String getCuf() {
        return cuf;
    }

    /**
     * @param cuf the cuf to set
     */
    public void setCuf(String cuf) {
        this.cuf = cuf;
    }

    /**
     * @return the utcFechaEmision
     */
    public String getUtcFechaEmision() {
        return utcFechaEmision;
    }

    /**
     * @param utcFechaEmision the utcFechaEmision to set
     */
    public void setUtcFechaEmision(String utcFechaEmision) {
        this.utcFechaEmision = utcFechaEmision;
    }

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
     * @return the apiHistoricoDetalle
     */
    public List<ApiHistoricoDetalle> getApiHistoricoDetalle() {
        return apiHistoricoDetalle;
    }

    /**
     * @param apiHistoricoDetalle the apiHistoricoDetalle to set
     */
    public void setApiHistoricoDetalle(List<ApiHistoricoDetalle> apiHistoricoDetalle) {
        this.apiHistoricoDetalle = apiHistoricoDetalle;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the usuarioAlta
     */
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    /**
     * @param usuarioAlta the usuarioAlta to set
     */
    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    /**
     * @return the idEmpresaDocumento
     */
    public Long getIdEmpresaDocumento() {
        return idEmpresaDocumento;
    }

    /**
     * @param idEmpresaDocumento the idEmpresaDocumento to set
     */
    public void setIdEmpresaDocumento(Long idEmpresaDocumento) {
        this.idEmpresaDocumento = idEmpresaDocumento;
    }

}
