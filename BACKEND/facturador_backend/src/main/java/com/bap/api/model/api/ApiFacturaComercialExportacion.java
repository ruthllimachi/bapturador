/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.model.api;

import com.bap.api.configurate.AbstractAudit;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParMotivoAnulacion;
import com.bap.api.model.par.ParPaisOrigen;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.model.par.ParTipoMoneda;
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
@Table(name = "api_factura_comercial_exportacion")
public class ApiFacturaComercialExportacion extends AbstractAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_exportacion")
    private Long idFacturaExportacion;

    @Column(name = "nit_emisor")
    private Long nitEmisor;

    @Column(name = "numero_factura")
    private Long numeroFactura;

    @Column(name = "cuf")
    private String cuf;

    @ManyToOne()
    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion", nullable = true)
    private ApiConfiguracion apiConfiguracion;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "utc_fecha_emision")
    private String utcFechaEmision;

    @ManyToOne()
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = true)
    private ApiCliente apiCliente;

    @ManyToOne()
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = true)
    private ApiSucursal apiSucursal;

    @ManyToOne()
    @JoinColumn(name = "id_punto_venta", referencedColumnName = "id_punto_venta", nullable = true)
    private ApiPuntoVenta apiPuntoVenta;

    @ManyToOne()
    @JoinColumn(name = "id_dosificacion", referencedColumnName = "id_dosificacion", nullable = true)
    private ApiDosificacion apiDosificacion;

    @ManyToOne()
    @JoinColumn(name = "id_empresa_documento", referencedColumnName = "id_empresa_documento", nullable = true)
    private ApiEmpresaDocumento apiEmpresaDocumento;

    @Column(name = "direccion_comprador", nullable = false, length = 300)
    private String direccionComprador;

    @Column(name = "incoterm", nullable = false, length = 100)
    private String incoterm;

    @Column(name = "puerto_destino", nullable = false, length = 100)
    private String puertoDestino;

    @Column(name = "lugar_destino", nullable = false, length = 100)
    private String lugarDestino;

    @ManyToOne()
    @JoinColumn(name = "pais_origen", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParPaisOrigen parPaisOrigen;

    //codigoMetodoPago
    @ManyToOne()
    @JoinColumn(name = "codigo_metodo_pago", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMetodoPago parTipoMetodoPago;

    @ManyToOne()
    @JoinColumn(name = "tipo_emision", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoEmision parTipoEmision;

    @Column(name = "numero_tarjeta")
    private Long numeroTarjeta;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @Column(name = "monto_total_puerto")
    private BigDecimal montoTotalPuerto;

    @Column(name = "precio_valor_bruto")
    private BigDecimal precioValorBruto;

    @Column(name = "gastos_transporte_frontera")
    private BigDecimal gastosTransporteFrontera;

    @Column(name = "gastos_seguro_frontera")
    private BigDecimal gastosSeguroFrontera;

    @Column(name = "total_fob_frontera")
    private BigDecimal totalFobFrontera;

    @Column(name = "monto_transporte_frontera")
    private BigDecimal montoTransporteFrontera;

    @Column(name = "monto_seguro_internacional")
    private BigDecimal montoSeguroInternacional;

    @Column(name = "otros_montos")
    private BigDecimal otrosMontos;

    @Column(name = "monto_descuento")
    private BigDecimal montoDescuento;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "utc_fecha_envio")
    private String utcFechaEnvio;

    //codigoMoneda
    @ManyToOne()
    @JoinColumn(name = "codigo_moneda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParTipoMoneda parTipoMoneda;

    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;

    @Column(name = "monto_total_moneda")
    private BigDecimal montoTotalMoneda;

    //leyenda    
    @ManyToOne()
    @JoinColumn(name = "leyenda", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParLeyendaFactura parLeyendaFactura;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "codigo_recepcion")
    private Long codigoRecepcion;

    @Column(name = "nombre_archivo_xml")
    private String nombreArchivoXml;

    @Column(name = "nombre_archivo_xml_firmado")
    private String nombreArchivoXmlFirmado;

    @ManyToOne()
    @JoinColumn(name = "id_actividad", referencedColumnName = "id_actividad", nullable = true)
    private ApiActividad apiActividad;

    @ManyToOne()
    @JoinColumn(name = "anulado", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParCondicion parCondicion;

    @Column(name = "codigo_recepcion_anulado")
    private Long codigoRecepcionAnulado;

    @ManyToOne()
    @JoinColumn(name = "codigo_motivo_anulacion", referencedColumnName = "codigo", nullable = true, foreignKey = @ForeignKey(name = "none"))
    private ParMotivoAnulacion parMotivoAnulacion;

    @OneToMany(mappedBy = "apiFacturaComercialExportacion", cascade = {CascadeType.ALL, CascadeType.MERGE,
        CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApiFacturaComercialExportacionDetalle> apiFacturaComercialExportacionDetalle;

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
     * @return the apiActividad
     */
    public ApiActividad getApiActividad() {
        return apiActividad;
    }

    /**
     * @param apiActividad the apiActividad to set
     */
    public void setApiActividad(ApiActividad apiActividad) {
        this.apiActividad = apiActividad;
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
     * @return the parPaisOrigen
     */
    public ParPaisOrigen getParPaisOrigen() {
        return parPaisOrigen;
    }

    /**
     * @param parPaisOrigen the parPaisOrigen to set
     */
    public void setParPaisOrigen(ParPaisOrigen parPaisOrigen) {
        this.parPaisOrigen = parPaisOrigen;
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
     * @return the idFacturaExportacion
     */
    public Long getIdFacturaExportacion() {
        return idFacturaExportacion;
    }

    /**
     * @param idFacturaExportacion the idFacturaExportacion to set
     */
    public void setIdFacturaExportacion(Long idFacturaExportacion) {
        this.idFacturaExportacion = idFacturaExportacion;
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
     * @return the apiFacturaComercialExportacionDetalle
     */
    public List<ApiFacturaComercialExportacionDetalle> getApiFacturaComercialExportacionDetalle() {
        return apiFacturaComercialExportacionDetalle;
    }

    /**
     * @param apiFacturaComercialExportacionDetalle the
     * apiFacturaComercialExportacionDetalle to set
     */
    public void setApiFacturaComercialExportacionDetalle(List<ApiFacturaComercialExportacionDetalle> apiFacturaComercialExportacionDetalle) {
        this.apiFacturaComercialExportacionDetalle = apiFacturaComercialExportacionDetalle;
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

    /**
     * @return the parMotivoAnulacion
     */
    public ParMotivoAnulacion getParMotivoAnulacion() {
        return parMotivoAnulacion;
    }

    /**
     * @param parMotivoAnulacion the parMotivoAnulacion to set
     */
    public void setParMotivoAnulacion(ParMotivoAnulacion parMotivoAnulacion) {
        this.parMotivoAnulacion = parMotivoAnulacion;
    }
}
