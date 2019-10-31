/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.model.par.ParTipoTransaccion;
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
@Table(name = "api_nota_fiscal_credito_debito_detalle")
public class ApiNotaFiscalCreditoDebitoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_credito_debito_detalle")
    private Long idNotaCreditoDebitoDetalle;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_nota_credito_debito", nullable = false)
    private ApiNotaFiscalCreditoDebito apiNotaCreditoDebito;

    //actividadEconomica, codigoProductoSin, codigoProducto, descripcion, numeroSerie, numeroImei
    @ManyToOne()
    @JoinColumn(name = "id_item_homologado", referencedColumnName = "id_item_homologado", nullable = true)
    private ApiItemHomologado apiItemHomologado;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @ManyToOne()
    @JoinColumn(name = "tipo_transaccion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
        private ParTipoTransaccion parTipoTransaccion;

    /**
     * @return the idNotaCreditoDebitoDetalle
     */
    public Long getIdNotaCreditoDebitoDetalle() {
        return idNotaCreditoDebitoDetalle;
    }

    /**
     * @param idNotaCreditoDebitoDetalle the idNotaCreditoDebitoDetalle to set
     */
    public void setIdNotaCreditoDebitoDetalle(Long idNotaCreditoDebitoDetalle) {
        this.idNotaCreditoDebitoDetalle = idNotaCreditoDebitoDetalle;
    }

    /**
     * @return the apiNotaCreditoDebito
     */
    public ApiNotaFiscalCreditoDebito getApiNotaCreditoDebito() {
        return apiNotaCreditoDebito;
    }

    /**
     * @param apiNotaCreditoDebito the apiNotaCreditoDebito to set
     */
    public void setApiNotaCreditoDebito(ApiNotaFiscalCreditoDebito apiNotaCreditoDebito) {
        this.apiNotaCreditoDebito = apiNotaCreditoDebito;
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
     * @return the parTipoTransaccion
     */
    public ParTipoTransaccion getParTipoTransaccion() {
        return parTipoTransaccion;
    }

    /**
     * @param parTipoTransaccion the parTipoTransaccion to set
     */
    public void setParTipoTransaccion(ParTipoTransaccion parTipoTransaccion) {
        this.parTipoTransaccion = parTipoTransaccion;
    }
}
