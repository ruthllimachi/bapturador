/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ruth
 */
public class CabeceraDTO implements Serializable {   
    
    private Long nitEmisor;
    private Long numeroDocumentoFiscal;
    private Long codigoSucursal;
    private Long codigoPuntoVenta;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate fechaEmision;
    private LocalDateTime utfFechaEmision;    
    private String codigoCliente;
    private Long codigoMetodoPago;
    private Long numeroTarjeta;
    private Long numeroTarjetaOfuscado;
    private BigDecimal montoTotal;
    private BigDecimal montoDescuento;
    private Long codigoMoneda;
    private BigDecimal tipoCambio;
    private BigDecimal montoTotalMoneda;
    private Long leyenda;
    private String usuario;
    private Long codigoDocumentoSector;
    private Long codigoExcepcionDocumento;
    private Long codigoDocumentoFiscal;
    private Long tipoEmision;
//    private String cuf;
    private Long codigoTipoModalidad;
    private Long codigoActividadEconomica;
    private String direccionComprador;
    private String incoterm;
    private String puertoDestino;
    private String lugarDestino;
    private BigDecimal montoTotalPuerto;
    private BigDecimal precioValorBruto;
    private Long codigoPaisOrigen;
    private BigDecimal gastosTransporteFrontera;
    private BigDecimal gastosSeguroFrontera;
    private BigDecimal totalFobFrontera;
    private BigDecimal montoTransporteFrontera;
    private BigDecimal montoSeguroInternacional;
    private BigDecimal otrosMontos;
    private String numeroAutorizacionCuf;
    private BigDecimal montoTotalOriginal;
    private BigDecimal montoTotalDevuelto;
    private BigDecimal montoEfectivoCreditoDebito;
    private Long numeroFactura;    
    private String fechaEmisionFactura;
    private int codigoMotivo;

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
     * @return the codigoCliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the codigoMetodoPago
     */
    public Long getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    /**
     * @param codigoMetodoPago the codigoMetodoPago to set
     */
    public void setCodigoMetodoPago(Long codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    /**
     * @return the numeroTarjeta
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the montoTotal
     */
    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
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
     * @return the codigoMoneda
     */
    public Long getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * @param codigoMoneda the codigoMoneda to set
     */
    public void setCodigoMoneda(Long codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    /**
     * @return the tipoCambio
     */
    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    /**
     * @param tipoCambio the tipoCambio to set
     */
    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    /**
     * @return the montoTotalMoneda
     */
    public BigDecimal getMontoTotalMoneda() {
        return montoTotalMoneda;
    }

    /**
     * @param montoTotalMoneda the montoTotalMoneda to set
     */
    public void setMontoTotalMoneda(BigDecimal montoTotalMoneda) {
        this.montoTotalMoneda = montoTotalMoneda;
    }

    /**
     * @return the leyenda
     */
    public Long getLeyenda() {
        return leyenda;
    }

    /**
     * @param leyenda the leyenda to set
     */
    public void setLeyenda(Long leyenda) {
        this.leyenda = leyenda;
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

    /**
     * @return the codigoDocumentoSector
     */
    public Long getCodigoDocumentoSector() {
        return codigoDocumentoSector;
    }

    /**
     * @param codigoDocumentoSector the codigoDocumentoSector to set
     */
    public void setCodigoDocumentoSector(Long codigoDocumentoSector) {
        this.codigoDocumentoSector = codigoDocumentoSector;
    }

    /**
     * @return the codigoExcepcionDocumento
     */
    public Long getCodigoExcepcionDocumento() {
        return codigoExcepcionDocumento;
    }

    /**
     * @param codigoExcepcionDocumento the codigoExcepcionDocumento to set
     */
    public void setCodigoExcepcionDocumento(Long codigoExcepcionDocumento) {
        this.codigoExcepcionDocumento = codigoExcepcionDocumento;
    }

    /**
     * @return the numeroTarjetaOfuscado
     */
    public Long getNumeroTarjetaOfuscado() {
        return numeroTarjetaOfuscado;
    }

    /**
     * @param numeroTarjetaOfuscado the numeroTarjetaOfuscado to set
     */
    public void setNumeroTarjetaOfuscado(Long numeroTarjetaOfuscado) {
        this.numeroTarjetaOfuscado = numeroTarjetaOfuscado;
    }

    /**
     * @return the codigoDocumentoFiscal
     */
    public Long getCodigoDocumentoFiscal() {
        return codigoDocumentoFiscal;
    }

    /**
     * @param codigoDocumentoFiscal the codigoDocumentoFiscal to set
     */
    public void setCodigoDocumentoFiscal(Long codigoDocumentoFiscal) {
        this.codigoDocumentoFiscal = codigoDocumentoFiscal;
    }

    /**
     * @return the tipoEmision
     */
    public Long getTipoEmision() {
        return tipoEmision;
    }

    /**
     * @param tipoEmision the tipoEmision to set
     */
    public void setTipoEmision(Long tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

//    /**
//     * @return the cuf
//     */
//    public String getCuf() {
//        return cuf;
//    }
//
//    /**
//     * @param cuf the cuf to set
//     */
//    public void setCuf(String cuf) {
//        this.cuf = cuf;
//    }

    /**
     * @return the numeroDocumentoFiscal
     */
    public Long getNumeroDocumentoFiscal() {
        return numeroDocumentoFiscal;
    }

    /**
     * @param numeroDocumentoFiscal the numeroDocumentoFiscal to set
     */
    public void setNumeroDocumentoFiscal(Long numeroDocumentoFiscal) {
        this.numeroDocumentoFiscal = numeroDocumentoFiscal;
    }

    /**
     * @return the codigoTipoModalidad
     */
    public Long getCodigoTipoModalidad() {
        return codigoTipoModalidad;
    }

    /**
     * @param codigoTipoModalidad the codigoTipoModalidad to set
     */
    public void setCodigoTipoModalidad(Long codigoTipoModalidad) {
        this.codigoTipoModalidad = codigoTipoModalidad;
    }

    /**
     * @return the codigoActividadEconomica
     */
    public Long getCodigoActividadEconomica() {
        return codigoActividadEconomica;
    }

    /**
     * @param codigoActividadEconomica the codigoActividadEconomica to set
     */
    public void setCodigoActividadEconomica(Long codigoActividadEconomica) {
        this.codigoActividadEconomica = codigoActividadEconomica;
    }

    /**
     * @return the direccionComprador
     */
    public String getDireccionComprador() {
        return direccionComprador;
    }

    /**
     * @param direccionComprador the direccionComprador to set
     */
    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    /**
     * @return the incoterm
     */
    public String getIncoterm() {
        return incoterm;
    }

    /**
     * @param incoterm the incoterm to set
     */
    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    /**
     * @return the puertoDestino
     */
    public String getPuertoDestino() {
        return puertoDestino;
    }

    /**
     * @param puertoDestino the puertoDestino to set
     */
    public void setPuertoDestino(String puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    /**
     * @return the lugarDestino
     */
    public String getLugarDestino() {
        return lugarDestino;
    }

    /**
     * @param lugarDestino the lugarDestino to set
     */
    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    /**
     * @return the precioValorBruto
     */
    public BigDecimal getPrecioValorBruto() {
        return precioValorBruto;
    }

    /**
     * @param precioValorBruto the precioValorBruto to set
     */
    public void setPrecioValorBruto(BigDecimal precioValorBruto) {
        this.precioValorBruto = precioValorBruto;
    }

    /**
     * @return the codigoPaisOrigen
     */
    public Long getCodigoPaisOrigen() {
        return codigoPaisOrigen;
    }

    /**
     * @param codigoPaisOrigen the codigoPaisOrigen to set
     */
    public void setCodigoPaisOrigen(Long codigoPaisOrigen) {
        this.codigoPaisOrigen = codigoPaisOrigen;
    }

    /**
     * @return the gastosTransporteFrontera
     */
    public BigDecimal getGastosTransporteFrontera() {
        return gastosTransporteFrontera;
    }

    /**
     * @param gastosTransporteFrontera the gastosTransporteFrontera to set
     */
    public void setGastosTransporteFrontera(BigDecimal gastosTransporteFrontera) {
        this.gastosTransporteFrontera = gastosTransporteFrontera;
    }

    /**
     * @return the gastosSeguroFrontera
     */
    public BigDecimal getGastosSeguroFrontera() {
        return gastosSeguroFrontera;
    }

    /**
     * @param gastosSeguroFrontera the gastosSeguroFrontera to set
     */
    public void setGastosSeguroFrontera(BigDecimal gastosSeguroFrontera) {
        this.gastosSeguroFrontera = gastosSeguroFrontera;
    }

    /**
     * @return the totalFobFrontera
     */
    public BigDecimal getTotalFobFrontera() {
        return totalFobFrontera;
    }

    /**
     * @param totalFobFrontera the totalFobFrontera to set
     */
    public void setTotalFobFrontera(BigDecimal totalFobFrontera) {
        this.totalFobFrontera = totalFobFrontera;
    }

    /**
     * @return the montoTransporteFrontera
     */
    public BigDecimal getMontoTransporteFrontera() {
        return montoTransporteFrontera;
    }

    /**
     * @param montoTransporteFrontera the montoTransporteFrontera to set
     */
    public void setMontoTransporteFrontera(BigDecimal montoTransporteFrontera) {
        this.montoTransporteFrontera = montoTransporteFrontera;
    }

    /**
     * @return the montoSeguroInternacional
     */
    public BigDecimal getMontoSeguroInternacional() {
        return montoSeguroInternacional;
    }

    /**
     * @param montoSeguroInternacional the montoSeguroInternacional to set
     */
    public void setMontoSeguroInternacional(BigDecimal montoSeguroInternacional) {
        this.montoSeguroInternacional = montoSeguroInternacional;
    }

    /**
     * @return the otrosMontos
     */
    public BigDecimal getOtrosMontos() {
        return otrosMontos;
    }

    /**
     * @param otrosMontos the otrosMontos to set
     */
    public void setOtrosMontos(BigDecimal otrosMontos) {
        this.otrosMontos = otrosMontos;
    }

    /**
     * @return the montoTotalPuerto
     */
    public BigDecimal getMontoTotalPuerto() {
        return montoTotalPuerto;
    }

    /**
     * @param montoTotalPuerto the montoTotalPuerto to set
     */
    public void setMontoTotalPuerto(BigDecimal montoTotalPuerto) {
        this.montoTotalPuerto = montoTotalPuerto;
    }
 
    /**
     * @return the montoTotalDevuelto
     */
    public BigDecimal getMontoTotalDevuelto() {
        return montoTotalDevuelto;
    }

    /**
     * @param montoTotalDevuelto the montoTotalDevuelto to set
     */
    public void setMontoTotalDevuelto(BigDecimal montoTotalDevuelto) {
        this.montoTotalDevuelto = montoTotalDevuelto;
    }

    /**
     * @return the montoEfectivoCreditoDebito
     */
    public BigDecimal getMontoEfectivoCreditoDebito() {
        return montoEfectivoCreditoDebito;
    }

    /**
     * @param montoEfectivoCreditoDebito the montoEfectivoCreditoDebito to set
     */
    public void setMontoEfectivoCreditoDebito(BigDecimal montoEfectivoCreditoDebito) {
        this.montoEfectivoCreditoDebito = montoEfectivoCreditoDebito;
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
     * @return the numeroAutorizacionCuf
     */
    public String getNumeroAutorizacionCuf() {
        return numeroAutorizacionCuf;
    }

    /**
     * @param numeroAutorizacionCuf the numeroAutorizacionCuf to set
     */
    public void setNumeroAutorizacionCuf(String numeroAutorizacionCuf) {
        this.numeroAutorizacionCuf = numeroAutorizacionCuf;
    }

    /**
     * @param fechaEmisionFactura the fechaEmisionFactura to set
     */
    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    /**
     * @return the fechaEmisionFactura
     */
    public String getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    /**
     * @return the montoTotalOriginal
     */
    public BigDecimal getMontoTotalOriginal() {
        return montoTotalOriginal;
    }

    /**
     * @param montoTotalOriginal the montoTotalOriginal to set
     */
    public void setMontoTotalOriginal(BigDecimal montoTotalOriginal) {
        this.montoTotalOriginal = montoTotalOriginal;
    }

    /**
     * @return the codigoMotivo
     */
    public int getCodigoMotivo() {
        return codigoMotivo;
    }

    /**
     * @param codigoMotivo the codigoMotivo to set
     */
    public void setCodigoMotivo(int codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    /**
     * @return the fechaEmision
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the utfFechaEmision
     */
    public LocalDateTime getUtfFechaEmision() {
        return utfFechaEmision;
    }

    /**
     * @param utfFechaEmision the utfFechaEmision to set
     */
    public void setUtfFechaEmision(LocalDateTime utfFechaEmision) {
        this.utfFechaEmision = utfFechaEmision;
    }

}
