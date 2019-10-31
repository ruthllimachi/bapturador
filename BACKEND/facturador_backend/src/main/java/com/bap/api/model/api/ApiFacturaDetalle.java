/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.model.par.ParCondicion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

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
@Table(name = "api_factura_detalle")
public class ApiFacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_detalle")
    private Long idFacturaDetalle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_factura", nullable = false)
    private ApiFactura apiFactura;        

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
     * @return the idFacturaDetalle
     */
    public Long getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    /**
     * @param idFacturaDetalle the idFacturaDetalle to set
     */
    public void setIdFacturaDetalle(Long idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    /**
     * @return the apiFactura
     */
    public ApiFactura getApiFactura() {
        return apiFactura;
    }

    /**
     * @param apiFactura the apiFactura to set
     */
    public void setApiFactura(ApiFactura apiFactura) {
        this.apiFactura = apiFactura;
    }

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

}
