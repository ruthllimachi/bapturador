/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.dto;

import com.bap.api.model.api.ApiCliente;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.model.par.ParTipoMoneda;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author ruth
 */
public class ApiFacturaDTO {

    private Long idFactura;
    private String nombreRazonSocial;
    private Long numeroFactura;
    private String cuf;
    private ApiConfiguracion apiConfiguracion;
    private LocalDateTime fechaEmision;
    private String utcFechaEmision;
    private LocalDateTime fechaEnvio;
    private String utcFechaEnvio;
    private ApiCliente apiCliente;
    private ApiSucursal apiSucursal;
    private ApiPuntoVenta apiPuntoVenta;
    private ApiDosificacion apiDosificacion;
    private ParTipoMetodoPago parTipoMetodoPago;
    private ParTipoEmision parTipoEmision;
    private Long numeroTarjeta;
    private BigDecimal montoTotal;
    private BigDecimal montoDescuento;
    private ParTipoMoneda parTipoMoneda;
    private BigDecimal tipoCambio;
    private BigDecimal montoTotalMoneda;
    private ParLeyendaFactura parLeyendaFactura;
    private String usuario;
    private ParTipoDocumentoSector parTipoDocumentoSector;
    private ParTipoDocumentoFiscal parTipoDocumentoFiscal;
    private Integer codigoExcepcionDocumento;
    private Long codigoRecepcion;    
    private String mensajeServicio;

    /**
     * @return the idFactura
     */
    public Long getIdFactura() {
        return idFactura;
    }

    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }
  

    /**
     * @return the nombreRazonSocial
     */
    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    /**
     * @param nombreRazonSocial the nombreRazonSocial to set
     */
    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
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
     * @return the apiConfiguracion
     */
    public ApiConfiguracion getApiConfiguracion() {
        return apiConfiguracion;
    }

    /**
     * @param apiConfiguracion the apiConfiguracion to set
     */
    public void setApiConfiguracion(ApiConfiguracion apiConfiguracion) {
        this.apiConfiguracion = apiConfiguracion;
    }

    /**
     * @return the fechaEmision
     */
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
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
     * @return the fechaEnvio
     */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * @param fechaEnvio the fechaEnvio to set
     */
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * @return the utcFechaEnvio
     */
    public String getUtcFechaEnvio() {
        return utcFechaEnvio;
    }

    /**
     * @param utcFechaEnvio the utcFechaEnvio to set
     */
    public void setUtcFechaEnvio(String utcFechaEnvio) {
        this.utcFechaEnvio = utcFechaEnvio;
    }

    /**
     * @return the apiCliente
     */
    public ApiCliente getApiCliente() {
        return apiCliente;
    }

    /**
     * @param apiCliente the apiCliente to set
     */
    public void setApiCliente(ApiCliente apiCliente) {
        this.apiCliente = apiCliente;
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
     * @return the apiPuntoVenta
     */
    public ApiPuntoVenta getApiPuntoVenta() {
        return apiPuntoVenta;
    }

    /**
     * @param apiPuntoVenta the apiPuntoVenta to set
     */
    public void setApiPuntoVenta(ApiPuntoVenta apiPuntoVenta) {
        this.apiPuntoVenta = apiPuntoVenta;
    }

    /**
     * @return the apiDosificacion
     */
    public ApiDosificacion getApiDosificacion() {
        return apiDosificacion;
    }

    /**
     * @param apiDosificacion the apiDosificacion to set
     */
    public void setApiDosificacion(ApiDosificacion apiDosificacion) {
        this.apiDosificacion = apiDosificacion;
    }

    /**
     * @return the parTipoMetodoPago
     */
    public ParTipoMetodoPago getParTipoMetodoPago() {
        return parTipoMetodoPago;
    }

    /**
     * @param parTipoMetodoPago the parTipoMetodoPago to set
     */
    public void setParTipoMetodoPago(ParTipoMetodoPago parTipoMetodoPago) {
        this.parTipoMetodoPago = parTipoMetodoPago;
    }

    /**
     * @return the parTipoEmision
     */
    public ParTipoEmision getParTipoEmision() {
        return parTipoEmision;
    }

    /**
     * @param parTipoEmision the parTipoEmision to set
     */
    public void setParTipoEmision(ParTipoEmision parTipoEmision) {
        this.parTipoEmision = parTipoEmision;
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
     * @return the parTipoMoneda
     */
    public ParTipoMoneda getParTipoMoneda() {
        return parTipoMoneda;
    }

    /**
     * @param parTipoMoneda the parTipoMoneda to set
     */
    public void setParTipoMoneda(ParTipoMoneda parTipoMoneda) {
        this.parTipoMoneda = parTipoMoneda;
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
     * @return the parLeyendaFactura
     */
    public ParLeyendaFactura getParLeyendaFactura() {
        return parLeyendaFactura;
    }

    /**
     * @param parLeyendaFactura the parLeyendaFactura to set
     */
    public void setParLeyendaFactura(ParLeyendaFactura parLeyendaFactura) {
        this.parLeyendaFactura = parLeyendaFactura;
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
     * @return the parTipoDocumentoSector
     */
    public ParTipoDocumentoSector getParTipoDocumentoSector() {
        return parTipoDocumentoSector;
    }

    /**
     * @param parTipoDocumentoSector the parTipoDocumentoSector to set
     */
    public void setParTipoDocumentoSector(ParTipoDocumentoSector parTipoDocumentoSector) {
        this.parTipoDocumentoSector = parTipoDocumentoSector;
    }

    /**
     * @return the parTipoDocumentoFiscal
     */
    public ParTipoDocumentoFiscal getParTipoDocumentoFiscal() {
        return parTipoDocumentoFiscal;
    }

    /**
     * @param parTipoDocumentoFiscal the parTipoDocumentoFiscal to set
     */
    public void setParTipoDocumentoFiscal(ParTipoDocumentoFiscal parTipoDocumentoFiscal) {
        this.parTipoDocumentoFiscal = parTipoDocumentoFiscal;
    }

    /**
     * @return the codigoExcepcionDocumento
     */
    public Integer getCodigoExcepcionDocumento() {
        return codigoExcepcionDocumento;
    }

    /**
     * @param codigoExcepcionDocumento the codigoExcepcionDocumento to set
     */
    public void setCodigoExcepcionDocumento(Integer codigoExcepcionDocumento) {
        this.codigoExcepcionDocumento = codigoExcepcionDocumento;
    }

    /**
     * @return the codigoRecepcion
     */
    public Long getCodigoRecepcion() {
        return codigoRecepcion;
    }

    /**
     * @param codigoRecepcion the codigoRecepcion to set
     */
    public void setCodigoRecepcion(Long codigoRecepcion) {
        this.codigoRecepcion = codigoRecepcion;
    }
   
    /**
     * @return the mensajeServicio
     */
    public String getMensajeServicio() {
        return mensajeServicio;
    }

    /**
     * @param mensajeServicio the mensajeServicio to set
     */
    public void setMensajeServicio(String mensajeServicio) {
        this.mensajeServicio = mensajeServicio;
    }

}
