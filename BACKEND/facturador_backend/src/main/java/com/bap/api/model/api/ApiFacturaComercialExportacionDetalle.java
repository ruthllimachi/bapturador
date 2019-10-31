/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

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
@Table(name = "api_factura_comercial_exportacion_detalle")
public class ApiFacturaComercialExportacionDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_exportacion_detalle")
    private Long idFacturaExportacionDetalle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_factura_exportacion", nullable = false)
    private ApiFacturaComercialExportacion apiFacturaComercialExportacion;        

    //actividadEconomica, codigoProductoSin, codigoProducto, descripcion, numeroSerie, numeroImei
    @ManyToOne()
    @JoinColumn(name = "id_item_homologado", referencedColumnName = "id_item_homologado", nullable = true)
    private ApiItemHomologado  apiItemHomologado;    
   
    @Column(name = "cantidad")
    private BigDecimal cantidad;
   

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "monto_descuento")
    private BigDecimal montoDescuento;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

  
    /**
     * @return the cantidad
     */
    public BigDecimal getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnitario
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the montoDescuento
     */
    public BigDecimal getMontoDescuento() {
        return montoDescuento;
    }

    /**
     * @param montoDescuento the montoDescuento to set
     */
    public void setMontoDescuento(BigDecimal montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    /**
     * @return the subTotal
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the apiItemHomologado
     */
    public ApiItemHomologado getApiItemHomologado() {
        return apiItemHomologado;
    }

    /**
     * @param apiItemHomologado the apiItemHomologado to set
     */
    public void setApiItemHomologado(ApiItemHomologado apiItemHomologado) {
        this.apiItemHomologado = apiItemHomologado;
    }    

    /**
     * @return the idFacturaExportacionDetalle
     */
    public Long getIdFacturaExportacionDetalle() {
        return idFacturaExportacionDetalle;
    }

    /**
     * @param idFacturaExportacionDetalle the idFacturaExportacionDetalle to set
     */
    public void setIdFacturaExportacionDetalle(Long idFacturaExportacionDetalle) {
        this.idFacturaExportacionDetalle = idFacturaExportacionDetalle;
    }

    /**
     * @return the apiFacturaComercialExportacion
     */
    public ApiFacturaComercialExportacion getApiFacturaComercialExportacion() {
        return apiFacturaComercialExportacion;
    }

    /**
     * @param apiFacturaComercialExportacion the apiFacturaComercialExportacion to set
     */
    public void setApiFacturaComercialExportacion(ApiFacturaComercialExportacion apiFacturaComercialExportacion) {
        this.apiFacturaComercialExportacion = apiFacturaComercialExportacion;
    }
}
