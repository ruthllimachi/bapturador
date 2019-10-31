/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParTipoEmision;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ruth
 */
@Entity
@Table(name = "api_nota_fiscal_credito_debito")
public class ApiNotaFiscalCreditoDebito extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_credito_debito")
    private Long idNotaCreditoDebito;

    @Column(name = "numero_nota_credito_debito")
    private Long numeroNotaCreditoDebito;

    @Column(name = "cuf")
    private String cuf;

    @ManyToOne()
    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion", nullable = true)
    private ApiConfiguracion apiConfiguracion;

    @ManyToOne()
    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion", nullable = true)
    private ApiDosificacion apiDosificacion;

    @ManyToOne()
    @JoinColumn(name = "id_empresa_documento", referencedColumnName = "id_empresa_documento", nullable = true)
    private ApiEmpresaDocumento apiEmpresaDocumento;

    @ManyToOne()
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura", nullable = true)
    private ApiFactura apiFactura;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "utc_fecha_emision")
    private String utcFechaEmision;

    @Column(name = "monto_total_original")
    private BigDecimal montoTotalOriginal;

    @Column(name = "monto_total_devuelto")
    private BigDecimal montoTotalDevuelto;

    @Column(name = "monto_efectivo_credito_debito")
    private BigDecimal montoEfectivoCreditoDebito;

    @ManyToOne()
    @JoinColumn(name = "leyenda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParLeyendaFactura parLeyendaFactura;

    @ManyToOne()
    @JoinColumn(name = "tipo_emision", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoEmision parTipoEmision;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "utc_fecha_envio")
    private String utcFechaEnvio;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "codigo_recepcion")
    private Long codigoRecepcion;

    @Column(name = "nombre_archivo_xml")
    private String nombreArchivoXml;

    @Column(name = "nombre_archivo_xml_firmado")
    private String nombreArchivoXmlFirmado;

    @ManyToOne()
    @JoinColumn(name = "anulado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCondicion parCondicion;

    @Column(name = "codigo_recepcion_anulado")
    private Long codigoRecepcionAnulado;

    @OneToMany(mappedBy = "apiNotaCreditoDebito", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApiNotaFiscalCreditoDebitoDetalle> apiNotaFiscalCreditoDebitoDetalle;

    /**
     * @return the idNotaCreditoDebito
     */
    public Long getIdNotaCreditoDebito() {
        return idNotaCreditoDebito;
    }

    /**
     * @param idNotaCreditoDebito the idNotaCreditoDebito to set
     */
    public void setIdNotaCreditoDebito(Long idNotaCreditoDebito) {
        this.idNotaCreditoDebito = idNotaCreditoDebito;
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
     * @return the apiEmpresaDocumento
     */
    public ApiEmpresaDocumento getApiEmpresaDocumento() {
        return apiEmpresaDocumento;
    }

    /**
     * @param apiEmpresaDocumento the apiEmpresaDocumento to set
     */
    public void setApiEmpresaDocumento(ApiEmpresaDocumento apiEmpresaDocumento) {
        this.apiEmpresaDocumento = apiEmpresaDocumento;
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
     * @return the numeroNotaCreditoDebito
     */
    public Long getNumeroNotaCreditoDebito() {
        return numeroNotaCreditoDebito;
    }

    /**
     * @param numeroNotaCreditoDebito the numeroNotaCreditoDebito to set
     */
    public void setNumeroNotaCreditoDebito(Long numeroNotaCreditoDebito) {
        this.numeroNotaCreditoDebito = numeroNotaCreditoDebito;
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
     * @return the nombreArchivoXml
     */
    public String getNombreArchivoXml() {
        return nombreArchivoXml;
    }

    /**
     * @param nombreArchivoXml the nombreArchivoXml to set
     */
    public void setNombreArchivoXml(String nombreArchivoXml) {
        this.nombreArchivoXml = nombreArchivoXml;
    }

    /**
     * @return the nombreArchivoXmlFirmado
     */
    public String getNombreArchivoXmlFirmado() {
        return nombreArchivoXmlFirmado;
    }

    /**
     * @param nombreArchivoXmlFirmado the nombreArchivoXmlFirmado to set
     */
    public void setNombreArchivoXmlFirmado(String nombreArchivoXmlFirmado) {
        this.nombreArchivoXmlFirmado = nombreArchivoXmlFirmado;
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
     * @return the apiNotaFiscalCreditoDebitoDetalle
     */
    public List<ApiNotaFiscalCreditoDebitoDetalle> getApiNotaFiscalCreditoDebitoDetalle() {
        return apiNotaFiscalCreditoDebitoDetalle;
    }

    /**
     * @param apiNotaFiscalCreditoDebitoDetalle the
     * apiNotaFiscalCreditoDebitoDetalle to set
     */
    public void setApiNotaFiscalCreditoDebitoDetalle(List<ApiNotaFiscalCreditoDebitoDetalle> apiNotaFiscalCreditoDebitoDetalle) {
        this.apiNotaFiscalCreditoDebitoDetalle = apiNotaFiscalCreditoDebitoDetalle;
    }

    /**
     * @return the parCondicion
     */
    public ParCondicion getParCondicion() {
        return parCondicion;
    }

    /**
     * @param parCondicion the parCondicion to set
     */
    public void setParCondicion(ParCondicion parCondicion) {
        this.parCondicion = parCondicion;
    }

    /**
     * @return the codigoRecepcionAnulado
     */
    public Long getCodigoRecepcionAnulado() {
        return codigoRecepcionAnulado;
    }

    /**
     * @param codigoRecepcionAnulado the codigoRecepcionAnulado to set
     */
    public void setCodigoRecepcionAnulado(Long codigoRecepcionAnulado) {
        this.codigoRecepcionAnulado = codigoRecepcionAnulado;
    }

}
