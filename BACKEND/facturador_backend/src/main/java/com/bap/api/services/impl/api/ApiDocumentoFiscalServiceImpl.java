/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39112;
import com.bap.api.consumer.ConsumerWS39113;
import com.bap.api.consumer.ConsumerWS39115;
import com.bap.api.consumer.ConsumerWS39116;
import com.bap.api.consumer.ConsumerWS39124;
import com.bap.api.consumer.ConsumerWS39125;
import com.bap.api.dto.CabeceraDTO;
import com.bap.api.dto.DetalleDTO;
import com.bap.api.dto.DocumentoFiscalDTO;
import com.bap.api.dto.Entidad;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.Respuesta39112;
import com.bap.api.dto.Respuesta39113;
import com.bap.api.dto.Respuesta39115;
import com.bap.api.dto.Respuesta39116;
import com.bap.api.dto.Respuesta39124;
import com.bap.api.dto.Respuesta39125;
import com.bap.api.dto.RespuestaSincronizacion;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.enums.EnumParTipoEmision;
import com.bap.api.enums.EnumParTipoModalidad;
import com.bap.api.enums.EnumParTipoTransaccion;
import com.bap.api.model.api.ApiActividad;
import com.bap.api.model.api.ApiCliente;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiEmpresaDocumento;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.api.ApiFacturaDetalle;
import com.bap.api.model.api.ApiFacturaComercialExportacion;
import com.bap.api.model.api.ApiFacturaComercialExportacionDetalle;
import com.bap.api.model.api.ApiItemHomologado;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebitoDetalle;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.model.par.ParMotivoAnulacion;
import com.bap.api.model.par.ParPaisOrigen;
import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.model.par.ParTipoMoneda;
import com.bap.api.model.par.ParTipoTransaccion;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiActividadService;
import com.bap.api.services.api.ApiClienteService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiDocumentoFiscalService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiEmpresaDocumentoService;
import com.bap.api.services.api.ApiFacturaService;
import com.bap.api.services.api.ApiItemHomologadoService;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.api.ApiSucursalService;
import com.bap.api.services.par.ParLeyendaFacturaService;
import com.bap.api.services.par.ParMensajeFacturadorService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.services.par.ParPaisOrigenService;
import com.bap.api.services.par.ParTipoDocumentoFiscalService;
import com.bap.api.services.par.ParTipoDocumentoSectorService;
import com.bap.api.services.par.ParTipoEmisionService;
import com.bap.api.services.par.ParTipoMetodoPagoService;
import com.bap.api.services.par.ParTipoMonedaService;
import com.bap.api.utils.FacturaUtils;
import com.bap.api.utils.FechaUtils;
import com.bap.api.utils.FirmadorUtils;
import com.bap.api.utils.GeneratorXML;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import com.bap.api.services.api.ApiFacturaComercialExportacionService;
import com.bap.api.services.api.ApiHistoricoService;
import com.bap.api.services.api.ApiNotaFiscalCreditoDebitoService;
import com.bap.api.services.par.ParCondicionService;
import com.bap.api.services.par.ParMotivoAnulacionService;
import com.bap.api.services.par.ParTipoTransaccionService;

/**
 *
 * @author ruth
 */
@Service
public class ApiDocumentoFiscalServiceImpl implements ApiDocumentoFiscalService {

    @Autowired
    AdmConsultasService admConsultasService;

    @Autowired
    ApiSucursalService apiSucursalService;

    @Autowired
    ApiPuntoVentaService apiPuntoVentaService;

    @Autowired
    ApiClienteService apiClienteService;

    @Autowired
    ApiItemHomologadoService apiItemHomologadoService;

    @Autowired
    ApiConfiguracionService apiConfiguracionService;

    @Autowired
    ApiDosificacionService apiDosificacionService;

    @Autowired
    ParTipoMetodoPagoService parTipoMetodoPagoService;

    @Autowired
    ParTipoMonedaService parTipoMonedaService;

    @Autowired
    ParLeyendaFacturaService parLeyendaFacturaService;

    @Autowired
    ParTipoDocumentoSectorService parTipoDocumentoSectorService;

    @Autowired
    ParTipoEmisionService parTipoEmisionService;

    @Autowired
    ParTipoDocumentoFiscalService parTipoDocumentoFiscalService;

    @Autowired
    ApiFacturaService apiFacturaService;

    @Autowired
    ApiFacturaComercialExportacionService apiFacturaComercialExportacionService;

    @Autowired
    ApiNotaFiscalCreditoDebitoService apiNotaCreditoFiscalService;

    @Autowired
    ApiEmpresaDocumentoService apiEmpresaDocumentoService;

    @Autowired
    ConsumerWS39113 consumerWS39113;

    @Autowired
    ConsumerWS39112 consumerWS39112;

    @Autowired
    ConsumerWS39116 consumerWS39116; //FacturaComputarizadaComercialExportacion

    @Autowired
    ConsumerWS39124 consumerWS39124; //FacturaElectronicaComercialExportacion

    @Autowired
    ConsumerWS39115 consumerWS39115; //NotaFiscalComputarizadaCreditoDebito

    @Autowired
    ConsumerWS39125 consumerWS39125; //FacturaElectronicaComercialExportacion

    @Autowired
    ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParMensajeFacturadorService parMensajeFacturadorService;

    @Autowired
    private ApiActividadService apiActividadService;

    @Autowired
    private ApiFacturaComercialExportacionService apiFacturaExportacionService;

    @Autowired
    ParPaisOrigenService parPaisOrigenService;

    @Autowired
    ParTipoTransaccionService parTipoTransaccionService;

    @Autowired
    ParCondicionService parCondicionService;

    @Autowired
    ApiHistoricoService apiHistoricoService;

    @Autowired
    ParMotivoAnulacionService parMotivoAnulacionService;

    @Override
    public Respuesta registroFacturaEstandarIndividual(DocumentoFiscalDTO documentoFiscalDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            CabeceraDTO cabeceraDTO = documentoFiscalDTO.getCabeceraDTO();
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                    //Factura Estandar
                    if (cabeceraDTO.getCodigoDocumentoSector() == 1) {
                        ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPotEmpresaAndTipo(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad());
                        if (apiEmpresaDocumento != null) {
                            ApiActividad apiActividad = apiActividadService.leerPorIdEmpresaAndCodigoActividad(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoActividadEconomica());
                            if (apiActividad != null) {
                                ApiSucursal apiSucursal = null;
                                ApiPuntoVenta apiPuntoVenta = null;
                                ApiDosificacion apiDosificacion = null;
                                ApiConfiguracion apiConfiguracion = null;
                                boolean swContinuar = true;
                                if (solicitud.getCodigoPuntoVenta() == 0) {
                                    if (cabeceraDTO.getCodigoSucursal() != solicitud.getCodigoSucursal()) {
                                        swContinuar = false;
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("6");
                                    } else {
                                        apiSucursal = solicitud.getApiSucursal();
                                    }
                                } else if (cabeceraDTO.getCodigoPuntoVenta() != solicitud.getCodigoPuntoVenta()) {
                                    swContinuar = false;
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("7");
                                } else {
                                    apiPuntoVenta = solicitud.getApiPuntoVenta();
                                }
                                if (swContinuar) {
                                    if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                        apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                        if (apiDosificacion != null) {
                                            apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                            if (apiConfiguracion != null) {
                                                if (cabeceraDTO.getNumeroDocumentoFiscal() != null) {
                                                    long cantidad = 0;
                                                    if (solicitud.getCodigoPuntoVenta() == 0) {
                                                        cantidad = apiFacturaService.verificaRepiteFacturaPorSucursal(apiSucursal.getIdSucursal(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());

                                                    } else {
                                                        cantidad = apiFacturaService.verificaRepiteFacturaPorPuntoVenta(apiPuntoVenta.getIdPuntoVenta(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());
                                                    }
                                                    if (cantidad > 0) {
                                                        swContinuar = false;
                                                    }
                                                    if (swContinuar) {
                                                        if (cabeceraDTO.getFechaEmision() != null) {
                                                            ApiCliente apiCliente = apiClienteService.leerPorIdEmpresaAndCodigoCliente(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoCliente());
                                                            if (apiCliente != null) {
                                                                ParTipoMetodoPago parTipoMetodoPago = parTipoMetodoPagoService.leerPorCodigo(cabeceraDTO.getCodigoMetodoPago());
                                                                if (parTipoMetodoPago != null) {
                                                                    ParTipoMoneda parTipoMoneda = parTipoMonedaService.leerPorCodigo(cabeceraDTO.getCodigoMoneda());
                                                                    if (parTipoMoneda != null) {
                                                                        ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(cabeceraDTO.getLeyenda());
                                                                        if (parLeyendaFactura != null) {
                                                                            ParTipoDocumentoSector parTipoDocumentoSector = parTipoDocumentoSectorService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoSector());
                                                                            if (parTipoDocumentoSector != null) {
                                                                                ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(cabeceraDTO.getTipoEmision());
                                                                                if (parTipoEmision != null) {
                                                                                    ParTipoDocumentoFiscal parTipoDocumentoFiscal = parTipoDocumentoFiscalService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoFiscal());
                                                                                    if (parTipoDocumentoFiscal != null) {
                                                                                        if (cabeceraDTO.getCodigoMetodoPago() == 2) {
                                                                                            if (cabeceraDTO.getNumeroTarjeta() == null) {
                                                                                                swContinuar = false;
                                                                                            }
                                                                                        }
                                                                                        if (swContinuar) {
                                                                                            if (cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                if (cabeceraDTO.getMontoTotal().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                    if (cabeceraDTO.getMontoTotalMoneda().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                        if (cabeceraDTO.getCodigoMoneda() == 688) {
                                                                                                            if (cabeceraDTO.getMontoTotal().compareTo(cabeceraDTO.getMontoTotalMoneda()) != 0) {
                                                                                                                swContinuar = false;
                                                                                                            }
                                                                                                            if (cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ONE) != 0) {
                                                                                                                swContinuar = false;
                                                                                                            }
                                                                                                        }
                                                                                                        if (swContinuar) {
                                                                                                            List<DetalleDTO> listaDetalleDTO = documentoFiscalDTO.getListaDetalleDTO();

                                                                                                            List<ApiFacturaDetalle> listaApiFacturaDetalle = new ArrayList<>();
                                                                                                            ApiFacturaDetalle apiFacturaDetalle;
                                                                                                            for (DetalleDTO detalleDTO : listaDetalleDTO) {
                                                                                                                ApiItemHomologado apiItemHomologado = apiItemHomologadoService.leerPorIdEmpresaAndCodigoProducto(solicitud.getIdEmpresa(), detalleDTO.getCodigoProducto());
                                                                                                                if (apiItemHomologado == null) {
                                                                                                                    swContinuar = false;
                                                                                                                    break;
                                                                                                                } else {
                                                                                                                    if (detalleDTO.getCantidad().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                        swContinuar = false;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    if (detalleDTO.getPrecioUnitario().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                        swContinuar = false;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    if (detalleDTO.getSubTotal().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                        swContinuar = false;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    apiFacturaDetalle = new ApiFacturaDetalle();
                                                                                                                    apiFacturaDetalle.setApiItemHomologado(apiItemHomologado);
                                                                                                                    apiFacturaDetalle.setCantidad(detalleDTO.getCantidad());
                                                                                                                    apiFacturaDetalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                                                                                                                    apiFacturaDetalle.setMontoDescuento(detalleDTO.getMontoDescuento());
//                                                                                                            apiFacturaDetalle.setSubTotal(detalleDTO.getSubTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
                                                                                                                    apiFacturaDetalle.setSubTotal(detalleDTO.getSubTotal());
                                                                                                                    listaApiFacturaDetalle.add(apiFacturaDetalle);
                                                                                                                }
                                                                                                            }
                                                                                                            if (swContinuar) {
                                                                                                                ApiFactura apiFactura = new ApiFactura();
                                                                                                                apiFactura.setNitEmisor(cabeceraDTO.getNitEmisor());
                                                                                                                apiFactura.setNumeroFactura(cabeceraDTO.getNumeroDocumentoFiscal());
                                                                                                                if (cabeceraDTO.getCodigoPuntoVenta() == 0) {
                                                                                                                    apiFactura.setApiSucursal(solicitud.getApiSucursal());
                                                                                                                } else {
                                                                                                                    apiFactura.setApiPuntoVenta(solicitud.getApiPuntoVenta());
                                                                                                                }
                                                                                                                apiFactura.setFechaEmision(cabeceraDTO.getFechaEmision());
                                                                                                                apiFactura.setUtcFechaEmision(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(cabeceraDTO.getFechaEmision()));
//                                                                                                        apiFactura.setMontoTotal(cabeceraDTO.getMontoTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
                                                                                                                apiFactura.setMontoTotal(cabeceraDTO.getMontoTotal());
                                                                                                                apiFactura.setMontoDescuento(cabeceraDTO.getMontoDescuento());
//                                                                                                        apiFactura.setMontoTotalMoneda(cabeceraDTO.getMontoTotalMoneda().setScale(2, BigDecimal.ROUND_HALF_UP));
                                                                                                                apiFactura.setMontoTotalMoneda(cabeceraDTO.getMontoTotalMoneda());
                                                                                                                apiFactura.setTipoCambio(cabeceraDTO.getTipoCambio());
                                                                                                                apiFactura.setUsuario(cabeceraDTO.getUsuario());
                                                                                                                if (cabeceraDTO.getCodigoMetodoPago() == 2) {
                                                                                                                    apiFactura.setNumeroTarjeta(cabeceraDTO.getNumeroTarjetaOfuscado());
                                                                                                                }
                                                                                                                apiFactura.setApiDosificacion(apiDosificacion);
                                                                                                                apiFactura.setApiConfiguracion(apiConfiguracion);
                                                                                                                apiFactura.setApiCliente(apiCliente);
                                                                                                                apiFactura.setParLeyendaFactura(parLeyendaFactura);
                                                                                                                apiFactura.setApiEmpresaDocumento(apiEmpresaDocumento);
                                                                                                                apiFactura.setParTipoMetodoPago(parTipoMetodoPago);
                                                                                                                apiFactura.setParTipoMoneda(parTipoMoneda);
                                                                                                                apiFactura.setParTipoEmision(parTipoEmision);
                                                                                                                apiFactura.setTipoCambio(cabeceraDTO.getTipoCambio());
                                                                                                                apiFactura.setApiActividad(apiActividad);

                                                                                                                apiFactura.setApiFacturaDetalle(listaApiFacturaDetalle);
                                                                                                                apiFactura.setUsuarioAlta(cabeceraDTO.getUsuario());
                                                                                                                apiFactura.setFechaAlta(new Date());
                                                                                                                String cuf = FacturaUtils.generaFacturaCUF(cabeceraDTO);
                                                                                                                if (cuf != null) {
                                                                                                                    apiFactura.setCuf(cuf);
                                                                                                                    RespuestaSincronizacion respuestaRecepcion = recepcionFacturaEstandarIndividual(apiFactura, solicitud);
                                                                                                                    if (respuestaRecepcion != null) {
                                                                                                                        if (respuestaRecepcion.isTransaccion()) {
                                                                                                                            apiFactura.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());
                                                                                                                            apiFactura.setNombreArchivoXml(respuestaRecepcion.getFileXMLSinFirma());
                                                                                                                            apiFactura.setNombreArchivoXmlFirmado(respuestaRecepcion.getFileXMLFirmado());
                                                                                                                            apiFactura.setFechaEnvio(respuestaRecepcion.getFechaEnvio());
                                                                                                                            apiFactura.setUtcFechaEnvio(respuestaRecepcion.getUtcFechaEnvio());
                                                                                                                            Thread.sleep(1000);
                                                                                                                            RespuestaSincronizacion respuestaValida = validaFacturaEstandarIndividual(apiFactura, solicitud);
                                                                                                                            if (respuestaValida != null) {
                                                                                                                                if (respuestaValida.isTransaccion()) {
                                                                                                                                    apiFacturaService.registrar(apiFactura);
                                                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("37");
                                                                                                                                    respuesta = new Respuesta();
                                                                                                                                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                                                                                    respuesta.setTransaccion(true);
                                                                                                                                    return respuesta;
                                                                                                                                } else {
                                                                                                                                    respuesta = new Respuesta();
                                                                                                                                    respuesta.setTransaccion(false);
                                                                                                                                    respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                                                                                    apiHistoricoService.registraHistorico(apiFactura, respuestaValida.getListaParMensajeServicio());
                                                                                                                                    return respuesta;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                //Error validacion
                                                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("36");
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            respuesta = new Respuesta();
                                                                                                                            respuesta.setTransaccion(false);
                                                                                                                            respuesta.setListaParMensajeServicio(respuestaRecepcion.getListaParMensajeServicio());
                                                                                                                            return respuesta;
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        //Error recepcion
                                                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("35");
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    //error CUF
                                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("13");
                                                                                                                }
                                                                                                            } else {
                                                                                                                //falta monto total moneda
                                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("29");
                                                                                                            }
                                                                                                        } else {
                                                                                                            //falta monto total moneda
                                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("4");
                                                                                                        }
                                                                                                    } else {
                                                                                                        //falta monto total moneda
                                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("16");
                                                                                                    }
                                                                                                } else {
                                                                                                    //falta monto total
                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("12");
                                                                                                }

                                                                                            } else {
                                                                                                //falt tipo cambio
                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("26");
                                                                                            }

                                                                                        } else {
                                                                                            //numero de tarjeta
                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("28");
                                                                                        }
                                                                                    } else {
                                                                                        //Tipo documento fiscal
                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("20");
                                                                                    }
                                                                                } else {
                                                                                    //Tipo emision
                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("19");
                                                                                }

                                                                            } else {
                                                                                //Tipo documento sector
                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("18");
                                                                            }
                                                                        } else {
                                                                            //leyenda
                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("17");
                                                                        }
                                                                    } else {
                                                                        //tipo de moneda
                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("14");
                                                                    }
                                                                } else {
                                                                    //metodo de pago
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("11");
                                                                }
                                                            } else {
                                                                //error en cliente
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("10");
                                                            }

                                                        } else {
                                                            //falta fecha emision
                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("32");
                                                        }
                                                    } else {
                                                        //no tiene de documento 
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                                    }
                                                } else {
                                                    //falta numero de documento
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                                }

                                            } else {
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                            }
                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                                    }
                                }
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("40");
                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception e) {
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("39");
            System.out.println("Error: " + e.getMessage());
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion recepcionFacturaEstandarIndividual(ApiFactura apiFactura, SolicitudCliente solicitud) {
        try {
            //1)Generar Archivo XML asociado al Documento Fiscal Electrónico                        
            String xml = null;
            String nombreFileXMLFirmado = null;
            String nombreFileXML = apiFactura.getApiDosificacion().getDirectorioXml().trim() + "/" + apiFactura.getCuf().trim() + "_SIN_FIRMA.xml";
            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiFactura.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.facturaElectronicaEstandarToXML(apiFactura, nombreFileXML);
            }
            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiFactura.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.facturaComputarizadaEstandarToXML(apiFactura, nombreFileXML);
            }
            if (xml != null) {
//                System.out.println("XML SIN FIRMA" + xml);                

                String xmlProcesada = null;
                if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiFactura.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //2-Firmar el archivo obtenido conforme estándar XMLDSig (Sólo en el caso de la Modalidad Electrónica en Línea)
                    //3)     Validar contra el XSD asociado
                    String clavePrivada = apiFactura.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiFactura.getApiDosificacion().getArchivoClavePrivada().trim();
                    String certificado = apiFactura.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiFactura.getApiDosificacion().getArchivoCertificado().trim();
                    nombreFileXMLFirmado = apiFactura.getApiDosificacion().getDirectorioXml().trim() + "/" + apiFactura.getCuf().trim() + "_FIRMADO.xml";
                    boolean swFirmado = FirmadorUtils.firmarXML(xml, clavePrivada, certificado, nombreFileXMLFirmado);
                    if (swFirmado) {
                        xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXMLFirmado);
                    }
                }
                if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiFactura.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //xmlProcesada = xml;
                    xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXML);
                }
                if (xmlProcesada != null) {
                    //System.out.println("XML CON FIRMA" + xmlProcesada);    
                    //4) Obtener el arreglo de bytes Base64 del archivo generado                
                    //5) Comprimir el arreglo Base64 en formato Gzip
                    //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)
                    //7)     Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)

                    Entidad entidad = FacturaUtils.area51(xmlProcesada);

                    //datos para solicitud de ws
                    solicitud.setCodigoDocumentoFiscal(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                    solicitud.setCodigoDocumentoSector(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                    solicitud.setCodigoEmision(apiFactura.getParTipoEmision().getCodigo().intValue());
                    solicitud.setFechaEnvio(LocalDateTime.now());
                    String utcFechaEnvio = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(LocalDateTime.now());
                    solicitud.setUtcFechaEnvio(utcFechaEnvio);
                    solicitud.setCufd(apiFactura.getApiConfiguracion().getCufd());
                    solicitud.setArchivo(entidad.getArchivo());
                    solicitud.setHashArchivo(entidad.getHash());
                    if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
//                        solicitud.setArchivo(entidad.getArchivo());
//                        solicitud.setHashArchivo(entidad.getHash());
                        Respuesta39113 respuesta39113 = consumerWS39113.recepcionFacturaElectronicaEstandar(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39113.isTransaccion());
                        if (respuesta39113.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39113.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLFirmado(nombreFileXMLFirmado);
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39113.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39113.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39113.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                    if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                        Respuesta39112 respuesta39112 = consumerWS39112.recepcionFacturaComputarizadaEstandar(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39112.isTransaccion());
                        if (respuesta39112.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39112.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39112.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39112.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39112.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                } else {
                    RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                    respuesta.setDescripcion("Error en la FIRMA XML");
                    return respuesta;
                }
            } else {
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setDescripcion("Error XML invalido");
                return respuesta;
            }

        } // TODO Auto-generated catch block
        // TODO Auto-generated catch block
        catch (IOException | URISyntaxException | ParserConfigurationException | XMLSecurityException | SAXException ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion validaFacturaEstandarIndividual(ApiFactura apiFactura, SolicitudCliente solicitud) {
        try {
            solicitud.setCufd(apiFactura.getApiConfiguracion().getCufd());
            solicitud.setCodigoRecepcion(apiFactura.getCodigoRecepcion());

            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39113 respuesta39113 = consumerWS39113.validacionRecepcionFacturaElectronicaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39113.isTransaccion());
                if (respuesta39113.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39113.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39113.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39113.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39113.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39112 respuesta39112 = consumerWS39112.validacionRecepcionFacturaComputarizadaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39112.isTransaccion());
                if (respuesta39112.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39112.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39112.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39112.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39112.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Respuesta registroFacturaComercialExportacion(DocumentoFiscalDTO documentoFiscalDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            CabeceraDTO cabeceraDTO = documentoFiscalDTO.getCabeceraDTO();
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                //Factura Exportacion
                if (cabeceraDTO.getCodigoDocumentoSector() == 12) {
                    if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                        ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPotEmpresaAndTipo(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad());
                        if (apiEmpresaDocumento != null) {
                            ApiActividad apiActividad = apiActividadService.leerPorIdEmpresaAndCodigoActividad(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoActividadEconomica());
                            if (apiActividad != null) {
                                ApiSucursal apiSucursal = null;
                                ApiPuntoVenta apiPuntoVenta = null;
                                ApiDosificacion apiDosificacion = null;
                                ApiConfiguracion apiConfiguracion = null;
                                boolean swContinuar = true;
                                if (solicitud.getCodigoPuntoVenta() == 0) {
                                    if (cabeceraDTO.getCodigoSucursal() != solicitud.getCodigoSucursal()) {
                                        swContinuar = false;
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("6");
                                    } else {
                                        apiSucursal = solicitud.getApiSucursal();
                                    }
                                } else if (cabeceraDTO.getCodigoPuntoVenta() != solicitud.getCodigoPuntoVenta()) {
                                    swContinuar = false;
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("7");
                                } else {
                                    apiPuntoVenta = solicitud.getApiPuntoVenta();
                                }
                                if (swContinuar) {
                                    if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                        apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                        if (apiDosificacion != null) {
                                            apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                            if (apiConfiguracion != null) {
                                                if (cabeceraDTO.getNumeroDocumentoFiscal() != null) {
                                                    long cantidad = 0;
                                                    if (solicitud.getCodigoPuntoVenta() == 0) {
                                                        cantidad = apiFacturaExportacionService.verificaRepiteFacturaComercialExportacionPorSucursal(apiSucursal.getIdSucursal(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());

                                                    } else {
                                                        cantidad = apiFacturaExportacionService.verificaRepiteFacturaComercialExportacionPorPuntoVenta(apiPuntoVenta.getIdPuntoVenta(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());
                                                    }
                                                    if (cantidad > 0) {
                                                        swContinuar = false;
                                                    }
                                                    if (swContinuar) {
                                                        if (cabeceraDTO.getFechaEmision() != null) {
                                                            ApiCliente apiCliente = apiClienteService.leerPorIdEmpresaAndCodigoCliente(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoCliente());
                                                            if (apiCliente != null) {
                                                                ParTipoMetodoPago parTipoMetodoPago = parTipoMetodoPagoService.leerPorCodigo(cabeceraDTO.getCodigoMetodoPago());
                                                                if (parTipoMetodoPago != null) {
                                                                    ParTipoMoneda parTipoMoneda = parTipoMonedaService.leerPorCodigo(cabeceraDTO.getCodigoMoneda());
                                                                    if (parTipoMoneda != null) {
                                                                        ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(cabeceraDTO.getLeyenda());
                                                                        if (parLeyendaFactura != null) {
                                                                            ParTipoDocumentoSector parTipoDocumentoSector = parTipoDocumentoSectorService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoSector());
                                                                            if (parTipoDocumentoSector != null) {
                                                                                ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(cabeceraDTO.getTipoEmision());
                                                                                if (parTipoEmision != null) {
                                                                                    ParTipoDocumentoFiscal parTipoDocumentoFiscal = parTipoDocumentoFiscalService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoFiscal());
                                                                                    if (parTipoDocumentoFiscal != null) {
                                                                                        ParPaisOrigen parPaisOrigen = parPaisOrigenService.leerPorCodigo(cabeceraDTO.getCodigoPaisOrigen());
                                                                                        if (parPaisOrigen != null) {
                                                                                            if (cabeceraDTO.getDireccionComprador() != null && cabeceraDTO.getIncoterm() != null && cabeceraDTO.getPuertoDestino() != null && cabeceraDTO.getLugarDestino() != null) {
                                                                                                if (cabeceraDTO.getCodigoMetodoPago() == 2) {
                                                                                                    if (cabeceraDTO.getNumeroTarjeta() == null) {
                                                                                                        swContinuar = false;
                                                                                                    }
                                                                                                }
                                                                                                if (swContinuar) {
                                                                                                    if (cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                        if (cabeceraDTO.getMontoTotal().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoTotalPuerto().compareTo(BigDecimal.ZERO) > 0
                                                                                                                && cabeceraDTO.getPrecioValorBruto().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getGastosTransporteFrontera().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getGastosSeguroFrontera().compareTo(BigDecimal.ZERO) > 0
                                                                                                                && cabeceraDTO.getTotalFobFrontera().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoTransporteFrontera().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoSeguroInternacional().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                            if (cabeceraDTO.getMontoTotalMoneda().compareTo(BigDecimal.ZERO) > 0) {
                                                                                                                if (cabeceraDTO.getCodigoMoneda() == 688) {
                                                                                                                    if (cabeceraDTO.getMontoTotal().compareTo(cabeceraDTO.getMontoTotalMoneda()) != 0) {
                                                                                                                        swContinuar = false;
                                                                                                                    }
                                                                                                                    if (cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ONE) != 0) {
                                                                                                                        swContinuar = false;
                                                                                                                    }
                                                                                                                }

                                                                                                                if (swContinuar) {
                                                                                                                    List<DetalleDTO> listaDetalleDTO = documentoFiscalDTO.getListaDetalleDTO();
                                                                                                                    List<ApiFacturaComercialExportacionDetalle> listaApiFacturaComercialExportacionDetalle = new ArrayList<>();
                                                                                                                    ApiFacturaComercialExportacionDetalle apiFacturaComercialExportacionDetalle;
                                                                                                                    for (DetalleDTO detalleDTO : listaDetalleDTO) {
                                                                                                                        ApiItemHomologado apiItemHomologado = apiItemHomologadoService.leerPorIdEmpresaAndCodigoProducto(solicitud.getIdEmpresa(), detalleDTO.getCodigoProducto());
                                                                                                                        if (apiItemHomologado == null) {
                                                                                                                            swContinuar = false;
                                                                                                                            break;
                                                                                                                        } else {
                                                                                                                            if (detalleDTO.getCantidad().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                                swContinuar = false;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            if (detalleDTO.getPrecioUnitario().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                                swContinuar = false;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            if (detalleDTO.getSubTotal().compareTo(BigDecimal.ZERO) < 1) {
                                                                                                                                swContinuar = false;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                            apiFacturaComercialExportacionDetalle = new ApiFacturaComercialExportacionDetalle();
                                                                                                                            apiFacturaComercialExportacionDetalle.setApiItemHomologado(apiItemHomologado);
                                                                                                                            apiFacturaComercialExportacionDetalle.setCantidad(detalleDTO.getCantidad());
                                                                                                                            apiFacturaComercialExportacionDetalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                                                                                                                            apiFacturaComercialExportacionDetalle.setMontoDescuento(detalleDTO.getMontoDescuento());
                                                                                                                            apiFacturaComercialExportacionDetalle.setSubTotal(detalleDTO.getSubTotal());
                                                                                                                            listaApiFacturaComercialExportacionDetalle.add(apiFacturaComercialExportacionDetalle);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    if (swContinuar) {
                                                                                                                        ApiFacturaComercialExportacion apiFacturaComercialExportacion = new ApiFacturaComercialExportacion();
                                                                                                                        apiFacturaComercialExportacion.setNitEmisor(cabeceraDTO.getNitEmisor());
                                                                                                                        apiFacturaComercialExportacion.setNumeroFactura(cabeceraDTO.getNumeroDocumentoFiscal());
                                                                                                                        if (cabeceraDTO.getCodigoPuntoVenta() == 0) {
                                                                                                                            apiFacturaComercialExportacion.setApiSucursal(solicitud.getApiSucursal());
                                                                                                                        } else {
                                                                                                                            apiFacturaComercialExportacion.setApiPuntoVenta(solicitud.getApiPuntoVenta());
                                                                                                                        }
                                                                                                                        apiFacturaComercialExportacion.setFechaEmision(cabeceraDTO.getFechaEmision());
                                                                                                                        apiFacturaComercialExportacion.setUtcFechaEmision(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(cabeceraDTO.getFechaEmision()));

                                                                                                                        apiFacturaComercialExportacion.setDireccionComprador(cabeceraDTO.getDireccionComprador());
                                                                                                                        apiFacturaComercialExportacion.setIncoterm(cabeceraDTO.getIncoterm());
                                                                                                                        apiFacturaComercialExportacion.setPuertoDestino(cabeceraDTO.getPuertoDestino());
                                                                                                                        apiFacturaComercialExportacion.setLugarDestino(cabeceraDTO.getLugarDestino());
                                                                                                                        apiFacturaComercialExportacion.setParPaisOrigen(parPaisOrigen);
                                                                                                                        apiFacturaComercialExportacion.setMontoTotal(cabeceraDTO.getMontoTotal());
                                                                                                                        apiFacturaComercialExportacion.setMontoTotalPuerto(cabeceraDTO.getMontoTotalPuerto());
                                                                                                                        apiFacturaComercialExportacion.setPrecioValorBruto(cabeceraDTO.getPrecioValorBruto());
                                                                                                                        apiFacturaComercialExportacion.setGastosTransporteFrontera(cabeceraDTO.getGastosTransporteFrontera());
                                                                                                                        apiFacturaComercialExportacion.setGastosSeguroFrontera(cabeceraDTO.getGastosSeguroFrontera());
                                                                                                                        apiFacturaComercialExportacion.setTotalFobFrontera(cabeceraDTO.getTotalFobFrontera());
                                                                                                                        apiFacturaComercialExportacion.setMontoTransporteFrontera(cabeceraDTO.getMontoTransporteFrontera());
                                                                                                                        apiFacturaComercialExportacion.setMontoSeguroInternacional(cabeceraDTO.getMontoSeguroInternacional());
                                                                                                                        apiFacturaComercialExportacion.setOtrosMontos(cabeceraDTO.getOtrosMontos());
                                                                                                                        apiFacturaComercialExportacion.setMontoDescuento(cabeceraDTO.getMontoDescuento());
                                                                                                                        apiFacturaComercialExportacion.setMontoTotalMoneda(cabeceraDTO.getMontoTotalMoneda());
                                                                                                                        apiFacturaComercialExportacion.setTipoCambio(cabeceraDTO.getTipoCambio());
                                                                                                                        apiFacturaComercialExportacion.setUsuario(cabeceraDTO.getUsuario());
                                                                                                                        if (cabeceraDTO.getCodigoMetodoPago() == 2) {
                                                                                                                            apiFacturaComercialExportacion.setNumeroTarjeta(cabeceraDTO.getNumeroTarjetaOfuscado());
                                                                                                                        }
                                                                                                                        apiFacturaComercialExportacion.setApiDosificacion(apiDosificacion);
                                                                                                                        apiFacturaComercialExportacion.setApiConfiguracion(apiConfiguracion);
                                                                                                                        apiFacturaComercialExportacion.setApiCliente(apiCliente);
                                                                                                                        apiFacturaComercialExportacion.setParLeyendaFactura(parLeyendaFactura);
                                                                                                                        apiFacturaComercialExportacion.setApiEmpresaDocumento(apiEmpresaDocumento);
                                                                                                                        apiFacturaComercialExportacion.setParTipoMetodoPago(parTipoMetodoPago);
                                                                                                                        apiFacturaComercialExportacion.setParTipoMoneda(parTipoMoneda);
                                                                                                                        apiFacturaComercialExportacion.setParTipoEmision(parTipoEmision);
                                                                                                                        apiFacturaComercialExportacion.setTipoCambio(cabeceraDTO.getTipoCambio());
                                                                                                                        apiFacturaComercialExportacion.setApiActividad(apiActividad);

                                                                                                                        apiFacturaComercialExportacion.setApiFacturaComercialExportacionDetalle(listaApiFacturaComercialExportacionDetalle);
                                                                                                                        apiFacturaComercialExportacion.setUsuarioAlta(cabeceraDTO.getUsuario());
                                                                                                                        apiFacturaComercialExportacion.setFechaAlta(new Date());
                                                                                                                        String cuf = FacturaUtils.generaFacturaCUF(cabeceraDTO);
                                                                                                                        if (cuf != null) {
                                                                                                                            apiFacturaComercialExportacion.setCuf(cuf);
                                                                                                                            RespuestaSincronizacion respuestaRecepcion = recepcionFacturaComercialEexportacion(apiFacturaComercialExportacion, solicitud);
                                                                                                                            if (respuestaRecepcion != null) {
                                                                                                                                if (respuestaRecepcion.isTransaccion()) {
                                                                                                                                    apiFacturaComercialExportacion.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());
                                                                                                                                    apiFacturaComercialExportacion.setNombreArchivoXml(respuestaRecepcion.getFileXMLSinFirma());
                                                                                                                                    apiFacturaComercialExportacion.setNombreArchivoXmlFirmado(respuestaRecepcion.getFileXMLFirmado());
                                                                                                                                    /////apiFacturaComercialExportacion.setFechaEnvio(respuestaRecepcion.getFechaEnvio());
                                                                                                                                    //apiFacturaComercialExportacion.setUtcFechaEnvio(respuestaRecepcion.getUtcFechaEnvio());

                                                                                                                                    Thread.sleep(1000);
                                                                                                                                    RespuestaSincronizacion respuestaValida = validaFacturaComercialExportacion(apiFacturaComercialExportacion, solicitud);
                                                                                                                                    if (respuestaValida != null) {
                                                                                                                                        if (respuestaValida.isTransaccion()) {
                                                                                                                                            apiFacturaComercialExportacionService.registrar(apiFacturaComercialExportacion);
                                                                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("37");
                                                                                                                                            respuesta = new Respuesta();
                                                                                                                                            respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                                                                                            respuesta.setTransaccion(true);
                                                                                                                                            return respuesta;
                                                                                                                                        } else {
                                                                                                                                            respuesta = new Respuesta();
                                                                                                                                            respuesta.setTransaccion(false);
                                                                                                                                            respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                                                                                            return respuesta;
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        //Error validacion
                                                                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("36");
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    respuesta = new Respuesta();
                                                                                                                                    respuesta.setTransaccion(false);
                                                                                                                                    respuesta.setListaParMensajeServicio(respuestaRecepcion.getListaParMensajeServicio());
                                                                                                                                    return respuesta;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                //Error recepcion
                                                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("35");
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            //error CUF
                                                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("13");
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        //falta monto total moneda
                                                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("29");
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    //falta monto total moneda
                                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("4");
                                                                                                                }
                                                                                                            } else {
                                                                                                                //falta monto total moneda
                                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("16");
                                                                                                            }
                                                                                                        } else {
                                                                                                            //falta monto total
                                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("12");
                                                                                                        }

                                                                                                    } else {
                                                                                                        //falt tipo cambio
                                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("26");
                                                                                                    }
                                                                                                } else {
                                                                                                    //numero de tarjeta
                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("28");
                                                                                                }
                                                                                                ///////////////////////////////////////////////////////////////////////////////
                                                                                            } else {
                                                                                                //Error direccion origen
                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("45");
                                                                                            }
                                                                                        } else {
                                                                                            //Falta Pais Origen
                                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("44");
                                                                                        }

                                                                                    } else {
                                                                                        //Tipo documento fiscal
                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("20");
                                                                                    }
                                                                                } else {
                                                                                    //Tipo emision
                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("19");
                                                                                }

                                                                            } else {
                                                                                //Tipo documento sector
                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("18");
                                                                            }
                                                                        } else {
                                                                            //leyenda
                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("17");
                                                                        }
                                                                    } else {
                                                                        //tipo de moneda
                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("14");
                                                                    }
                                                                } else {
                                                                    //metodo de pago
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("11");
                                                                }
                                                            } else {
                                                                //error en cliente
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("10");
                                                            }

                                                        } else {
                                                            //falta fecha emision
                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("32");
                                                        }
                                                    } else {
                                                        //no tiene de documento 
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                                    }
                                                } else {
                                                    //falta numero de documento
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                                }

                                            } else {
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                            }
                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                                    }
                                }
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("40");
                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception e) {
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("39");
            System.out.println("Error: " + e.getMessage());
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion recepcionFacturaComercialEexportacion(ApiFacturaComercialExportacion apiFacturaComercialExportacion, SolicitudCliente solicitud) {
        try {
            //1)Generar Archivo XML asociado al Documento Fiscal Electrónico                        
            String xml = null;
            String nombreFileXMLFirmado = null;
            String nombreFileXML = apiFacturaComercialExportacion.getApiDosificacion().getDirectorioXml().trim() + "/" + apiFacturaComercialExportacion.getCuf().trim() + "_SIN_FIRMA.xml";
            if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiFacturaComercialExportacion.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.facturaElectronicaComercialExportacionToXML(apiFacturaComercialExportacion, nombreFileXML);
            }
            if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiFacturaComercialExportacion.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.facturaComputarizadaComercialExportacionToXML(apiFacturaComercialExportacion, nombreFileXML);
            }
            if (xml != null) {
//                System.out.println("XML SIN FIRMA" + xml);                

                String xmlProcesada = null;
                if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiFacturaComercialExportacion.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //2-Firmar el archivo obtenido conforme estándar XMLDSig (Sólo en el caso de la Modalidad Electrónica en Línea)
                    //3)     Validar contra el XSD asociado
                    String clavePrivada = apiFacturaComercialExportacion.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiFacturaComercialExportacion.getApiDosificacion().getArchivoClavePrivada().trim();
                    String certificado = apiFacturaComercialExportacion.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiFacturaComercialExportacion.getApiDosificacion().getArchivoCertificado().trim();
                    nombreFileXMLFirmado = apiFacturaComercialExportacion.getApiDosificacion().getDirectorioXml().trim() + "/" + apiFacturaComercialExportacion.getCuf().trim() + "_FIRMADO.xml";
                    boolean swFirmado = FirmadorUtils.firmarXML(xml, clavePrivada, certificado, nombreFileXMLFirmado);
                    if (swFirmado) {
                        xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXMLFirmado);
                    }
                }
                if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiFacturaComercialExportacion.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //xmlProcesada = xml;
                    xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXML);
                }
                if (xmlProcesada != null) {
                    //System.out.println("XML CON FIRMA" + xmlProcesada);    
                    //4) Obtener el arreglo de bytes Base64 del archivo generado                
                    //5) Comprimir el arreglo Base64 en formato Gzip
                    //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)
                    //7)     Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)

                    Entidad entidad = FacturaUtils.area51(xmlProcesada);

                    //datos para solicitud de ws
                    solicitud.setCodigoDocumentoFiscal(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                    solicitud.setCodigoDocumentoSector(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                    solicitud.setCodigoEmision(apiFacturaComercialExportacion.getParTipoEmision().getCodigo().intValue());
                    solicitud.setFechaEnvio(LocalDateTime.now());
                    String utcFechaEnvio = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(LocalDateTime.now());
                    solicitud.setUtcFechaEnvio(utcFechaEnvio);
                    solicitud.setCufd(apiFacturaComercialExportacion.getApiConfiguracion().getCufd());
                    solicitud.setArchivo(entidad.getArchivo());
                    solicitud.setHashArchivo(entidad.getHash());
                    if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
//                        solicitud.setArchivo(entidad.getArchivo());
//                        solicitud.setHashArchivo(entidad.getHash());
                        Respuesta39124 respuesta39124 = consumerWS39124.recepcionFacturaElectronicaComercialExportacion(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39124.isTransaccion());
                        if (respuesta39124.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39124.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLFirmado(nombreFileXMLFirmado);
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39124.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39124.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39124.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                    if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                        Respuesta39116 respuesta39116 = consumerWS39116.recepcionFacturaComputarizadaComercialExportacion(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39116.isTransaccion());
                        if (respuesta39116.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39116.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39116.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39116.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39116.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                } else {
                    RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                    respuesta.setDescripcion("Error en la FIRMA XML");
                    return respuesta;
                }
            } else {
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setDescripcion("Error XML invalido");
                return respuesta;
            }

        } // TODO Auto-generated catch block
        // TODO Auto-generated catch block
        catch (IOException | URISyntaxException | ParserConfigurationException | XMLSecurityException | SAXException ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion validaFacturaComercialExportacion(ApiFacturaComercialExportacion apiFacturaComercialExportacion, SolicitudCliente solicitud) {
        try {
            solicitud.setCufd(apiFacturaComercialExportacion.getApiConfiguracion().getCufd());
            solicitud.setCodigoRecepcion(apiFacturaComercialExportacion.getCodigoRecepcion());

            if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39124 respuesta39124 = consumerWS39124.validacionRecepcionFacturaElectronicaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39124.isTransaccion());
                if (respuesta39124.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39124.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39124.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39124.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39124.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (apiFacturaComercialExportacion.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39116 respuesta39116 = consumerWS39116.validacionRecepcionFacturaComputarizadaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39116.isTransaccion());
                if (respuesta39116.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39116.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39116.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39116.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39116.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Respuesta registroNotaFiscalCreditoDebito(DocumentoFiscalDTO documentoFiscalDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            CabeceraDTO cabeceraDTO = documentoFiscalDTO.getCabeceraDTO();
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                    //Nota credito debito                    
                    if (cabeceraDTO.getCodigoDocumentoSector() == 18) {
                        ApiFactura apiFactura = null;
                        if (solicitud.getApiPuntoVenta() == null) {
                            apiFactura = apiFacturaService.findFacturaPorSucursal(cabeceraDTO.getNumeroFactura(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiSucursal().getIdSucursal(), cabeceraDTO.getFechaEmisionFactura());
                        } else {
                            apiFactura = apiFacturaService.findFacturaPorPuntoVenta(cabeceraDTO.getNumeroFactura(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiPuntoVenta().getIdPuntoVenta(), cabeceraDTO.getFechaEmisionFactura());
                        }
                        if (apiFactura != null) {
                            ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPotEmpresaAndTipo(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad());
                            if (apiEmpresaDocumento != null) {
                                boolean swContinuar = true;
                                if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                    ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                    if (apiDosificacion != null) {
                                        ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                        if (apiConfiguracion != null) {
                                            if (cabeceraDTO.getNumeroDocumentoFiscal() != null) {
//                                                    long cantidad = 0;
//                                                    if (solicitud.getCodigoPuntoVenta() == 0) {
//                                                        cantidad = apiFacturaService.verificaRepiteFacturaPorSucursal(apiSucursal.getIdSucursal(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());
//
//                                                    } else {
//                                                        cantidad = apiFacturaService.verificaRepiteFacturaPorPuntoVenta(apiPuntoVenta.getIdPuntoVenta(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());
//                                                    }
//                                                    if (cantidad > 0) {
//                                                        swContinuar = false;
//                                                    }
                                                if (swContinuar) {
                                                    if (cabeceraDTO.getFechaEmision() != null) {
                                                        ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(cabeceraDTO.getLeyenda());
                                                        if (parLeyendaFactura != null) {
                                                            ParTipoDocumentoSector parTipoDocumentoSector = parTipoDocumentoSectorService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoSector());
                                                            if (parTipoDocumentoSector != null) {
                                                                ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(cabeceraDTO.getTipoEmision());
                                                                if (parTipoEmision != null) {
                                                                    ParTipoDocumentoFiscal parTipoDocumentoFiscal = parTipoDocumentoFiscalService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoFiscal());
                                                                    if (parTipoDocumentoFiscal != null) {
                                                                        if (cabeceraDTO.getMontoTotalOriginal().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoTotalDevuelto().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoEfectivoCreditoDebito().compareTo(BigDecimal.ZERO) > 0) {
                                                                            List<DetalleDTO> listaDetalleDTO = documentoFiscalDTO.getListaDetalleDTO();

                                                                            ParTipoTransaccion parTipoTransaccion = parTipoTransaccionService.leerPorCodigo(Long.valueOf(EnumParTipoTransaccion.TRANSACCION_ORIGINAL.getCodigo()));
                                                                            List<ApiNotaFiscalCreditoDebitoDetalle> listaApiNotaCreditoDebitoDetalle = new ArrayList<>();
                                                                            ApiNotaFiscalCreditoDebitoDetalle apiNotaFiscalCreditoDebitoDetalle = null;
                                                                            for (ApiFacturaDetalle apiFacturaDetalle : apiFactura.getApiFacturaDetalle()) {
                                                                                apiNotaFiscalCreditoDebitoDetalle = new ApiNotaFiscalCreditoDebitoDetalle();
                                                                                apiNotaFiscalCreditoDebitoDetalle.setApiItemHomologado(apiFacturaDetalle.getApiItemHomologado());
                                                                                apiNotaFiscalCreditoDebitoDetalle.setCantidad(apiFacturaDetalle.getCantidad());
                                                                                apiNotaFiscalCreditoDebitoDetalle.setPrecioUnitario(apiFacturaDetalle.getPrecioUnitario());
                                                                                apiNotaFiscalCreditoDebitoDetalle.setSubTotal(apiFacturaDetalle.getSubTotal());
                                                                                apiNotaFiscalCreditoDebitoDetalle.setParTipoTransaccion(parTipoTransaccion);
                                                                                listaApiNotaCreditoDebitoDetalle.add(apiNotaFiscalCreditoDebitoDetalle);
                                                                            }

                                                                            parTipoTransaccion = parTipoTransaccionService.leerPorCodigo(Long.valueOf(EnumParTipoTransaccion.TRANSACCION_DEVOLUCION.getCodigo()));
                                                                            for (DetalleDTO detalleDTO : listaDetalleDTO) {
                                                                                ApiItemHomologado apiItemHomologado = apiItemHomologadoService.leerPorIdEmpresaAndCodigoProducto(solicitud.getIdEmpresa(), detalleDTO.getCodigoProducto());
                                                                                if (apiItemHomologado == null) {
                                                                                    swContinuar = false;
                                                                                    break;
                                                                                } else {
                                                                                    if (detalleDTO.getCantidad().compareTo(BigDecimal.ZERO) < 1) {
                                                                                        swContinuar = false;
                                                                                        break;
                                                                                    }
                                                                                    if (detalleDTO.getPrecioUnitario().compareTo(BigDecimal.ZERO) < 1) {
                                                                                        swContinuar = false;
                                                                                        break;
                                                                                    }
                                                                                    if (detalleDTO.getSubTotal().compareTo(BigDecimal.ZERO) < 1) {
                                                                                        swContinuar = false;
                                                                                        break;
                                                                                    }
                                                                                    apiNotaFiscalCreditoDebitoDetalle = new ApiNotaFiscalCreditoDebitoDetalle();
                                                                                    apiNotaFiscalCreditoDebitoDetalle.setApiItemHomologado(apiItemHomologado);
                                                                                    apiNotaFiscalCreditoDebitoDetalle.setCantidad(detalleDTO.getCantidad());
                                                                                    apiNotaFiscalCreditoDebitoDetalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                                                                                    apiNotaFiscalCreditoDebitoDetalle.setSubTotal(detalleDTO.getSubTotal());
                                                                                    apiNotaFiscalCreditoDebitoDetalle.setParTipoTransaccion(parTipoTransaccion);
                                                                                    listaApiNotaCreditoDebitoDetalle.add(apiNotaFiscalCreditoDebitoDetalle);
                                                                                }
                                                                            }
                                                                            if (swContinuar) {
                                                                                ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = new ApiNotaFiscalCreditoDebito();
                                                                                apiNotaFiscalCreditoDebito.setNumeroNotaCreditoDebito(cabeceraDTO.getNumeroDocumentoFiscal());
                                                                                apiNotaFiscalCreditoDebito.setApiFactura(apiFactura);
                                                                                apiNotaFiscalCreditoDebito.setApiConfiguracion(apiConfiguracion);
                                                                                apiNotaFiscalCreditoDebito.setApiDosificacion(apiDosificacion);
                                                                                apiNotaFiscalCreditoDebito.setApiEmpresaDocumento(apiEmpresaDocumento);
                                                                                apiNotaFiscalCreditoDebito.setFechaEmision(cabeceraDTO.getFechaEmision());
                                                                                apiNotaFiscalCreditoDebito.setUtcFechaEmision(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(cabeceraDTO.getFechaEmision()));
                                                                                apiNotaFiscalCreditoDebito.setMontoTotalOriginal(cabeceraDTO.getMontoTotalOriginal());
                                                                                apiNotaFiscalCreditoDebito.setMontoTotalDevuelto(cabeceraDTO.getMontoTotalDevuelto());
                                                                                apiNotaFiscalCreditoDebito.setMontoEfectivoCreditoDebito(cabeceraDTO.getMontoEfectivoCreditoDebito());
                                                                                apiNotaFiscalCreditoDebito.setUsuario(cabeceraDTO.getUsuario());
                                                                                apiNotaFiscalCreditoDebito.setParLeyendaFactura(parLeyendaFactura);
                                                                                apiNotaFiscalCreditoDebito.setApiEmpresaDocumento(apiEmpresaDocumento);
                                                                                apiNotaFiscalCreditoDebito.setApiNotaFiscalCreditoDebitoDetalle(listaApiNotaCreditoDebitoDetalle);
                                                                                apiNotaFiscalCreditoDebito.setUsuarioAlta(cabeceraDTO.getUsuario());
                                                                                apiNotaFiscalCreditoDebito.setParTipoEmision(parTipoEmision);
                                                                                apiNotaFiscalCreditoDebito.setFechaAlta(new Date());

                                                                                cabeceraDTO.setCodigoSucursal(apiFactura.getApiSucursal().getCodigoSucursal());
                                                                                if (apiFactura.getApiPuntoVenta() != null) {
                                                                                    cabeceraDTO.setCodigoPuntoVenta(apiFactura.getApiPuntoVenta().getCodigoPuntoVenta());
                                                                                }
                                                                                cabeceraDTO.setNitEmisor(apiFactura.getNitEmisor());

                                                                                String cuf = FacturaUtils.generaFacturaCUF(cabeceraDTO);
                                                                                if (cuf != null) {
                                                                                    apiNotaFiscalCreditoDebito.setCuf(cuf);
                                                                                    RespuestaSincronizacion respuestaRecepcion = recepcionNotaFiscalCreditoDebito(apiNotaFiscalCreditoDebito, solicitud);
                                                                                    if (respuestaRecepcion != null) {
                                                                                        if (respuestaRecepcion.isTransaccion()) {
                                                                                            apiNotaFiscalCreditoDebito.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());
                                                                                            apiNotaFiscalCreditoDebito.setNombreArchivoXml(respuestaRecepcion.getFileXMLSinFirma());
                                                                                            apiNotaFiscalCreditoDebito.setNombreArchivoXmlFirmado(respuestaRecepcion.getFileXMLFirmado());
                                                                                            apiNotaFiscalCreditoDebito.setFechaEnvio(respuestaRecepcion.getFechaEnvio());
                                                                                            apiNotaFiscalCreditoDebito.setUtcFechaEnvio(respuestaRecepcion.getUtcFechaEnvio());

                                                                                            Thread.sleep(1000);
                                                                                            RespuestaSincronizacion respuestaValida = validaNotaFiscalCreditoDebito(apiNotaFiscalCreditoDebito, solicitud);
                                                                                            if (respuestaValida != null) {
                                                                                                if (respuestaValida.isTransaccion()) {
                                                                                                    apiNotaCreditoFiscalService.registrar(apiNotaFiscalCreditoDebito);
                                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("37");
                                                                                                    respuesta = new Respuesta();
                                                                                                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                                                    respuesta.setTransaccion(true);
                                                                                                    return respuesta;
                                                                                                } else {
                                                                                                    respuesta = new Respuesta();
                                                                                                    respuesta.setTransaccion(false);
                                                                                                    respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                                                    return respuesta;
                                                                                                }
                                                                                            } else {
                                                                                                //Error validacion
                                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("36");
                                                                                            }
                                                                                        } else {
                                                                                            respuesta = new Respuesta();
                                                                                            respuesta.setTransaccion(false);
                                                                                            respuesta.setListaParMensajeServicio(respuestaRecepcion.getListaParMensajeServicio());
                                                                                            return respuesta;
                                                                                        }
                                                                                    } else {
                                                                                        //Error recepcion
                                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("35");
                                                                                    }
                                                                                } else {
                                                                                    //error CUF
                                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("13");
                                                                                }
                                                                            } else {
                                                                                //falta cantidad o precio
                                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("29");
                                                                            }

                                                                        } else {
                                                                            //falta montos totales
                                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("46");
                                                                        }

                                                                    } else {
                                                                        //Tipo documento fiscal
                                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("20");
                                                                    }
                                                                } else {
                                                                    //Tipo emision
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("19");
                                                                }

                                                            } else {
                                                                //Tipo documento sector
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("18");
                                                            }
                                                        } else {
                                                            //leyenda
                                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("17");
                                                        }

                                                    } else {
                                                        //falta fecha emision
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("32");
                                                    }
                                                } else {
                                                    //no tiene de documento 
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                                }
                                            } else {
                                                //falta numero de documento
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");
                                            }

                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                    }
                                } else {
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                                }

                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                            }

                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception e) {
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("39");
            System.out.println("Error: " + e.getMessage());
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion recepcionNotaFiscalCreditoDebito(ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito, SolicitudCliente solicitud) {
        try {
            //1)Generar Archivo XML asociado al Documento Fiscal Electrónico                        
            String xml = null;
            String nombreFileXMLFirmado = null;
            String nombreFileXML = apiNotaFiscalCreditoDebito.getApiDosificacion().getDirectorioXml().trim() + "/" + apiNotaFiscalCreditoDebito.getCuf().trim() + "_SIN_FIRMA.xml";
            if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.notaFiscalElectronicaCreditoDebitoToXML(apiNotaFiscalCreditoDebito, nombreFileXML);
            }
            if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                xml = GeneratorXML.notaFiscalComputarizadaCreditoDebitoToXML(apiNotaFiscalCreditoDebito, nombreFileXML);
            }
            if (xml != null) {
                String xmlProcesada = null;
                if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo() && apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //2-Firmar el archivo obtenido conforme estándar XMLDSig (Sólo en el caso de la Modalidad Electrónica en Línea)
                    //3)     Validar contra el XSD asociado
                    String clavePrivada = apiNotaFiscalCreditoDebito.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiNotaFiscalCreditoDebito.getApiDosificacion().getArchivoClavePrivada().trim();
                    String certificado = apiNotaFiscalCreditoDebito.getApiDosificacion().getDirectorioCertificado().trim() + "/" + apiNotaFiscalCreditoDebito.getApiDosificacion().getArchivoCertificado().trim();
                    nombreFileXMLFirmado = apiNotaFiscalCreditoDebito.getApiDosificacion().getDirectorioXml().trim() + "/" + apiNotaFiscalCreditoDebito.getCuf().trim() + "_FIRMADO.xml";
                    boolean swFirmado = FirmadorUtils.firmarXML(xml, clavePrivada, certificado, nombreFileXMLFirmado);
                    if (swFirmado) {
                        xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXMLFirmado);
                    }
                }
                if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo() && apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo() == EnumParTipoEmision.ON_LINE.getCodigo()) {
                    //xmlProcesada = xml;
                    xmlProcesada = FacturaUtils.leerFileXMLToString(nombreFileXML);
                }
                if (xmlProcesada != null) {
                    //System.out.println("XML CON FIRMA" + xmlProcesada);    
                    //4) Obtener el arreglo de bytes Base64 del archivo generado                
                    //5) Comprimir el arreglo Base64 en formato Gzip
                    //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)
                    //7)     Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)

                    Entidad entidad = FacturaUtils.area51(xmlProcesada);

                    //datos para solicitud de ws
                    solicitud.setCodigoDocumentoFiscal(apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                    solicitud.setCodigoDocumentoSector(apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                    solicitud.setCodigoEmision(apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo().intValue());
                    solicitud.setFechaEnvio(LocalDateTime.now());
                    String utcFechaEnvio = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(LocalDateTime.now());
                    solicitud.setUtcFechaEnvio(utcFechaEnvio);
                    solicitud.setCufd(apiNotaFiscalCreditoDebito.getApiConfiguracion().getCufd());
                    solicitud.setArchivo(entidad.getArchivo());
                    solicitud.setHashArchivo(entidad.getHash());
                    if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
//                        solicitud.setArchivo(entidad.getArchivo());
//                        solicitud.setHashArchivo(entidad.getHash());
                        Respuesta39125 respuesta39125 = consumerWS39125.recepcionNotaFiscalElectronicaCreditoDebito(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39125.isTransaccion());
                        if (respuesta39125.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39125.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLFirmado(nombreFileXMLFirmado);
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39125.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39125.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39125.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                    if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                        Respuesta39115 respuesta39115 = consumerWS39115.recepcionNotaFiscalComputarizadaCreditoDebito(solicitud);
                        RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                        respuesta.setTransaccion(respuesta39115.isTransaccion());
                        if (respuesta39115.isTransaccion()) {
                            respuesta.setCodigoRecepcion(respuesta39115.getCodigoRecepcion());
                            respuesta.setFechaEnvio(solicitud.getFechaEnvio());
                            respuesta.setUtcFechaEnvio(solicitud.getUtcFechaEnvio());
                            respuesta.setFileXMLSinFirma(nombreFileXML);
                        } else {
                            ///respuesta.getListaParMensajeServicio()                     
                            List<ParMensajeServicio> lista = new ArrayList<>();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39115.getCodigoEstado()));
                            lista.add(parMensajeServicio);
                            String mensajeServicio = "";
                            for (int i = 0; i < respuesta39115.getListaCodigosRespuestas().size(); i++) {
                                parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39115.getListaCodigosRespuestas().get(i).longValue());
                                lista.add(parMensajeServicio);
                                mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                            }
                            respuesta.setListaParMensajeServicio(lista);
                        }
                        return respuesta;
                    }
                } else {
                    RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                    respuesta.setDescripcion("Error en la FIRMA XML");
                    return respuesta;
                }
            } else {
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setDescripcion("Error XML invalido");
                return respuesta;
            }

        } // TODO Auto-generated catch block
        // TODO Auto-generated catch block
        catch (IOException | URISyntaxException | ParserConfigurationException | XMLSecurityException | SAXException ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion validaNotaFiscalCreditoDebito(ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito, SolicitudCliente solicitud) {
        try {
            solicitud.setCufd(apiNotaFiscalCreditoDebito.getApiConfiguracion().getCufd());
            solicitud.setCodigoRecepcion(apiNotaFiscalCreditoDebito.getCodigoRecepcion());

            if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39125 respuesta39125 = consumerWS39125.validacionRecepcionNotaFiscalElectronicaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39125.isTransaccion());
                if (respuesta39125.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39125.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39125.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39125.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39125.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (apiNotaFiscalCreditoDebito.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39115 respuesta39115 = consumerWS39115.validacionRecepcionNotaFiscalComputarizadaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39115.isTransaccion());
                if (respuesta39115.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39115.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39115.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39115.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39115.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Respuesta anularFacturaEstandar(CabeceraDTO cabeceraDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                    //Factura Estandar
                    if (cabeceraDTO.getCodigoDocumentoSector() == 1) {
                        ApiFactura apiFactura = null;
                        if (solicitud.getApiPuntoVenta() == null) {
                            apiFactura = apiFacturaService.findFacturaPorSucursal(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiSucursal().getIdSucursal(), cabeceraDTO.getFechaEmisionFactura());
                        } else {
                            apiFactura = apiFacturaService.findFacturaPorPuntoVenta(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiPuntoVenta().getIdPuntoVenta(), cabeceraDTO.getFechaEmisionFactura());
                        }
                        if (apiFactura != null) {
                            if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                if (apiDosificacion != null) {
                                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                    if (apiConfiguracion != null) {

                                        boolean swContinuar = false;
                                        if (solicitud.getApiPuntoVenta() == null) {
                                            if (apiFactura.getApiSucursal().getCodigoSucursal().longValue() == solicitud.getCodigoSucursal()) {
                                                swContinuar = true;
                                            }
                                        } else {
                                            if (apiFactura.getApiPuntoVenta().getCodigoPuntoVenta().longValue() == solicitud.getCodigoPuntoVenta()) {
                                                swContinuar = true;
                                            }
                                        }
                                        if (swContinuar) {
                                            if (apiFactura.getNitEmisor() == cabeceraDTO.getNitEmisor().longValue()
                                                    && apiFactura.getApiDosificacion().getCuis().equals(solicitud.getCuis())
                                                    && apiFactura.getCuf().equals(cabeceraDTO.getNumeroAutorizacionCuf())
                                                    && apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoFiscal()
                                                    && apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoSector()
                                                    && apiFactura.getApiEmpresaDocumento().getParTipoModalidad().getCodigo().longValue() == cabeceraDTO.getCodigoTipoModalidad().longValue()) {
                                                if (cabeceraDTO.getCodigoMotivo() != 0) {
                                                    solicitud.setCodigoModalidad(cabeceraDTO.getCodigoTipoModalidad().intValue());
                                                    solicitud.setNitEmpresa(apiFactura.getNitEmisor());
                                                    solicitud.setCuis(apiDosificacion.getCuis());
                                                    solicitud.setCufd(apiConfiguracion.getCufd());
                                                    solicitud.setCodigoDocumentoFiscal(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                                                    solicitud.setCodigoDocumentoSector(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                                                    solicitud.setNumeroDocumentoFiscal(apiFactura.getNumeroFactura());
                                                    solicitud.setCuf(apiFactura.getCuf());
                                                    solicitud.setCodigoMotivo(cabeceraDTO.getCodigoMotivo());
                                                    solicitud.setUsuario(cabeceraDTO.getUsuario());
                                                    solicitud.setCodigoEmision(apiFactura.getParTipoEmision().getCodigo().intValue());
                                                    RespuestaSincronizacion repuestaAnulacionFactura = solicitudAnulaFacturaEstandar(solicitud);
                                                    if (repuestaAnulacionFactura != null) {
                                                        if (repuestaAnulacionFactura.isTransaccion()) {
                                                            ParMotivoAnulacion parMotivoAnulacion = parMotivoAnulacionService.leerPorCodigo(Long.valueOf(solicitud.getCodigoMotivo()));
                                                            apiFactura.setCodigoRecepcionAnulado(repuestaAnulacionFactura.getCodigoRecepcion());
                                                            apiFactura.setParMotivoAnulacion(parMotivoAnulacion);
                                                            solicitud.setCodigoRecepcion(repuestaAnulacionFactura.getCodigoRecepcion());
                                                            RespuestaSincronizacion respuestaValida = verificaAnulacionFacturaEstandar(solicitud);
                                                            if (respuestaValida != null) {
                                                                if (respuestaValida.isTransaccion()) {
                                                                    apiFactura.setUsuarioModificacion(cabeceraDTO.getUsuario());
                                                                    ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_SI.getCodigo());
                                                                    apiFactura.setParCondicion(parCondicion);
                                                                    apiFacturaService.modificar(apiFactura);
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("51");
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                    respuesta.setTransaccion(true);
                                                                    return respuesta;
                                                                } else {
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setTransaccion(false);
                                                                    respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                    return respuesta;
                                                                }
                                                            } else {
                                                                //Error validacion
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("50");
                                                            }

                                                        } else {
                                                            respuesta = new Respuesta();
                                                            respuesta.setTransaccion(false);
                                                            respuesta.setListaParMensajeServicio(repuestaAnulacionFactura.getListaParMensajeServicio());
                                                            return respuesta;
                                                        }
                                                    } else {
                                                        //Error validacion
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("49");
                                                    }
                                                } else {
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("48");
                                                }
                                            } else {
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                            }

                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                    }
                                } else {
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                }
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion solicitudAnulaFacturaEstandar(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39113 respuesta39113 = consumerWS39113.anulaFacturaElectronicaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39113.isTransaccion());
                if (respuesta39113.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39113.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39113.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39113.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39113.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39112 respuesta39112 = consumerWS39112.anulaFacturaComputarizadaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39112.isTransaccion());
                if (respuesta39112.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39112.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39112.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39112.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39112.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion verificaAnulacionFacturaEstandar(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39113 respuesta39113 = consumerWS39113.validaAnulacionFacturaElectronicaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39113.isTransaccion());
                if (respuesta39113.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39113.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39113.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39113.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39113.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39112 respuesta39112 = consumerWS39112.validaAnulacionFacturaComputarizadaEstandar(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39112.isTransaccion());
                if (respuesta39112.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39112.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39112.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39112.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39112.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
/////////////////////////

    @Override
    public Respuesta anularFacturaComercialExportacion(CabeceraDTO cabeceraDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                    //Factura Estandar
                    if (cabeceraDTO.getCodigoDocumentoSector() == 12) {
                        ApiFacturaComercialExportacion apiFacturaComercialExportacion = null;
                        if (solicitud.getApiPuntoVenta() == null) {
                            apiFacturaComercialExportacion = apiFacturaComercialExportacionService.findFacturaComercialExportacionPorSucursal(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiSucursal().getIdSucursal(), cabeceraDTO.getFechaEmisionFactura());
                        } else {
                            apiFacturaComercialExportacion = apiFacturaComercialExportacionService.findFacturaComercialExportacionPorPuntoVenta(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiPuntoVenta().getIdPuntoVenta(), cabeceraDTO.getFechaEmisionFactura());
                        }
                        if (apiFacturaComercialExportacion != null) {
                            if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                if (apiDosificacion != null) {
                                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                    if (apiConfiguracion != null) {

                                        boolean swContinuar = false;
                                        if (solicitud.getApiPuntoVenta() == null) {
                                            if (apiFacturaComercialExportacion.getApiSucursal().getCodigoSucursal().longValue() == solicitud.getCodigoSucursal()) {
                                                swContinuar = true;
                                            }
                                        } else {
                                            if (apiFacturaComercialExportacion.getApiPuntoVenta().getCodigoPuntoVenta().longValue() == solicitud.getCodigoPuntoVenta()) {
                                                swContinuar = true;
                                            }
                                        }
                                        if (swContinuar) {
                                            if (apiFacturaComercialExportacion.getNitEmisor() == cabeceraDTO.getNitEmisor().longValue()
                                                    && apiFacturaComercialExportacion.getApiDosificacion().getCuis().equals(solicitud.getCuis())
                                                    && apiFacturaComercialExportacion.getCuf().equals(cabeceraDTO.getNumeroAutorizacionCuf())
                                                    && apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoFiscal()
                                                    && apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoSector()
                                                    && apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoModalidad().getCodigo().longValue() == cabeceraDTO.getCodigoTipoModalidad().longValue()) {
                                                if (cabeceraDTO.getCodigoMotivo() != 0) {
                                                    solicitud.setCodigoModalidad(cabeceraDTO.getCodigoTipoModalidad().intValue());
                                                    solicitud.setNitEmpresa(apiFacturaComercialExportacion.getNitEmisor());
                                                    solicitud.setCuis(apiDosificacion.getCuis());
                                                    solicitud.setCufd(apiConfiguracion.getCufd());
                                                    solicitud.setCodigoDocumentoFiscal(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                                                    solicitud.setCodigoDocumentoSector(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                                                    solicitud.setNumeroDocumentoFiscal(apiFacturaComercialExportacion.getNumeroFactura());
                                                    solicitud.setCuf(apiFacturaComercialExportacion.getCuf());
                                                    solicitud.setCodigoMotivo(cabeceraDTO.getCodigoMotivo());
                                                    solicitud.setUsuario(cabeceraDTO.getUsuario());
                                                    solicitud.setCodigoEmision(apiFacturaComercialExportacion.getParTipoEmision().getCodigo().intValue());
                                                    RespuestaSincronizacion repuestaAnulacionFacturaComercialExportacion = solicitudAnulaFacturaComercialExportacion(solicitud);
                                                    if (repuestaAnulacionFacturaComercialExportacion != null) {
                                                        if (repuestaAnulacionFacturaComercialExportacion.isTransaccion()) {
                                                            apiFacturaComercialExportacion.setCodigoRecepcionAnulado(repuestaAnulacionFacturaComercialExportacion.getCodigoRecepcion());
                                                            solicitud.setCodigoRecepcion(repuestaAnulacionFacturaComercialExportacion.getCodigoRecepcion());
                                                            RespuestaSincronizacion respuestaValida = verificaAnulacionFacturaComercialExportacion(solicitud);
                                                            if (respuestaValida != null) {
                                                                if (respuestaValida.isTransaccion()) {
                                                                    ParMotivoAnulacion parMotivoAnulacion = parMotivoAnulacionService.leerPorCodigo(Long.valueOf(solicitud.getCodigoMotivo()));
                                                                    apiFacturaComercialExportacion.setParMotivoAnulacion(parMotivoAnulacion);
                                                                    apiFacturaComercialExportacion.setUsuarioModificacion(cabeceraDTO.getUsuario());
                                                                    ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_SI.getCodigo());
                                                                    apiFacturaComercialExportacion.setParCondicion(parCondicion);
                                                                    apiFacturaComercialExportacionService.modificar(apiFacturaComercialExportacion);
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("51");
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                    respuesta.setTransaccion(true);
                                                                    return respuesta;
                                                                } else {
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setTransaccion(false);
                                                                    respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                    return respuesta;
                                                                }
                                                            } else {
                                                                //Error validacion
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("50");
                                                            }

                                                        } else {
                                                            respuesta = new Respuesta();
                                                            respuesta.setTransaccion(false);
                                                            respuesta.setListaParMensajeServicio(repuestaAnulacionFacturaComercialExportacion.getListaParMensajeServicio());
                                                            return respuesta;
                                                        }
                                                    } else {
                                                        //Error validacion
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("49");
                                                    }
                                                } else {
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("48");
                                                }
                                            } else {
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                            }

                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                    }
                                } else {
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                }
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion solicitudAnulaFacturaComercialExportacion(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39124 respuesta39124 = consumerWS39124.anulaFacturaElectronicaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39124.isTransaccion());
                if (respuesta39124.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39124.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39124.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39124.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39124.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39116 respuesta39116 = consumerWS39116.anulaFacturaComputarizadaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39116.isTransaccion());
                if (respuesta39116.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39116.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39116.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39116.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39116.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion verificaAnulacionFacturaComercialExportacion(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39124 respuesta39124 = consumerWS39124.validaFacturaElectronicaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39124.isTransaccion());
                if (respuesta39124.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39124.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39124.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39124.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39124.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39116 respuesta39116 = consumerWS39116.validaFacturaComputarizadaComercialExportacion(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39116.isTransaccion());
                if (respuesta39116.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39116.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39116.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39116.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39116.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //////////////
    /////////////
    ////
    @Override
    public Respuesta anularNotaFiscalCreditoDebito(CabeceraDTO cabeceraDTO) {
        Respuesta respuesta = null;
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario(), null);
            if (solicitud != null) {
                if (solicitud.getCodigoModalidad() == cabeceraDTO.getCodigoTipoModalidad()) {
                    //Nota debito credito
                    if (cabeceraDTO.getCodigoDocumentoSector() == 18) {
                        ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = null;
                        if (solicitud.getApiPuntoVenta() == null) {
                            apiNotaFiscalCreditoDebito = apiNotaCreditoFiscalService.findNotaFiscalCreditoDebitoPorSucursal(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiSucursal().getIdSucursal(), cabeceraDTO.getFechaEmisionFactura());
                        } else {
                            apiNotaFiscalCreditoDebito = apiNotaCreditoFiscalService.findNotaFiscalCreditoDebitoPorPuntoVenta(cabeceraDTO.getNumeroDocumentoFiscal(), cabeceraDTO.getNumeroAutorizacionCuf(), solicitud.getApiPuntoVenta().getIdPuntoVenta(), cabeceraDTO.getFechaEmisionFactura());
                        }
                        if (apiNotaFiscalCreditoDebito != null) {
                            if (FacturaUtils.cufdVigente(solicitud.getFechaVigencia())) {
                                ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(solicitud.getIdDosificacion());
                                if (apiDosificacion != null) {
                                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(solicitud.getIdConfiguracion());
                                    if (apiConfiguracion != null) {

                                        boolean swContinuar = false;
                                        if (solicitud.getApiPuntoVenta() == null) {
                                            if (apiNotaFiscalCreditoDebito.getApiFactura().getApiSucursal().getCodigoSucursal().longValue() == solicitud.getCodigoSucursal()) {
                                                swContinuar = true;
                                            }
                                        } else {
                                            if (apiNotaFiscalCreditoDebito.getApiFactura().getApiPuntoVenta().getCodigoPuntoVenta().longValue() == solicitud.getCodigoPuntoVenta()) {
                                                swContinuar = true;
                                            }
                                        }
                                        if (swContinuar) {
                                            if (apiNotaFiscalCreditoDebito.getApiFactura().getNitEmisor() == cabeceraDTO.getNitEmisor().longValue()
                                                    && apiNotaFiscalCreditoDebito.getApiDosificacion().getCuis().equals(solicitud.getCuis())
                                                    && apiNotaFiscalCreditoDebito.getCuf().equals(cabeceraDTO.getNumeroAutorizacionCuf())
                                                    && apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoFiscal()
                                                    && apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue() == cabeceraDTO.getCodigoDocumentoSector()
                                                    && apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoModalidad().getCodigo().longValue() == cabeceraDTO.getCodigoTipoModalidad().longValue()) {
                                                if (cabeceraDTO.getCodigoMotivo() != 0) {
                                                    solicitud.setCodigoModalidad(cabeceraDTO.getCodigoTipoModalidad().intValue());
                                                    solicitud.setNitEmpresa(apiNotaFiscalCreditoDebito.getApiFactura().getNitEmisor());
                                                    solicitud.setCuis(apiDosificacion.getCuis());
                                                    solicitud.setCufd(apiConfiguracion.getCufd());
                                                    solicitud.setCodigoDocumentoFiscal(apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().intValue());
                                                    solicitud.setCodigoDocumentoSector(apiNotaFiscalCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().intValue());
                                                    solicitud.setNumeroDocumentoFiscal(apiNotaFiscalCreditoDebito.getNumeroNotaCreditoDebito());
                                                    solicitud.setCuf(apiNotaFiscalCreditoDebito.getCuf());
                                                    solicitud.setCodigoMotivo(cabeceraDTO.getCodigoMotivo());
                                                    solicitud.setUsuario(cabeceraDTO.getUsuario());
                                                    solicitud.setCodigoEmision(apiNotaFiscalCreditoDebito.getParTipoEmision().getCodigo().intValue());
                                                    RespuestaSincronizacion repuestaAnulacionNotaFiscalCreditoDebito = solicitudAnulaNotaFiscalCreditoDebito(solicitud);
                                                    if (repuestaAnulacionNotaFiscalCreditoDebito != null) {
                                                        if (repuestaAnulacionNotaFiscalCreditoDebito.isTransaccion()) {
                                                            ParMotivoAnulacion parMotivoAnulacion = parMotivoAnulacionService.leerPorCodigo(Long.valueOf(solicitud.getCodigoMotivo()));
                                                            apiNotaFiscalCreditoDebito.setParMotivoAnulacion(parMotivoAnulacion);
                                                            apiNotaFiscalCreditoDebito.setCodigoRecepcionAnulado(repuestaAnulacionNotaFiscalCreditoDebito.getCodigoRecepcion());
                                                            solicitud.setCodigoRecepcion(repuestaAnulacionNotaFiscalCreditoDebito.getCodigoRecepcion());
                                                            RespuestaSincronizacion respuestaValida = verificaAnulacionNotaFiscalCreditoDebito(solicitud);
                                                            if (respuestaValida != null) {
                                                                if (respuestaValida.isTransaccion()) {
                                                                    apiNotaFiscalCreditoDebito.setUsuarioModificacion(cabeceraDTO.getUsuario());
                                                                    ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_SI.getCodigo());
                                                                    apiNotaFiscalCreditoDebito.setParCondicion(parCondicion);
                                                                    apiNotaCreditoFiscalService.modificar(apiNotaFiscalCreditoDebito);
                                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("51");
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                                                                    respuesta.setTransaccion(true);
                                                                    return respuesta;
                                                                } else {
                                                                    respuesta = new Respuesta();
                                                                    respuesta.setTransaccion(false);
                                                                    respuesta.setListaParMensajeServicio(respuestaValida.getListaParMensajeServicio());
                                                                    return respuesta;
                                                                }
                                                            } else {
                                                                //Error validacion
                                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("50");
                                                            }

                                                        } else {
                                                            respuesta = new Respuesta();
                                                            respuesta.setTransaccion(false);
                                                            respuesta.setListaParMensajeServicio(repuestaAnulacionNotaFiscalCreditoDebito.getListaParMensajeServicio());
                                                            return respuesta;
                                                        }
                                                    } else {
                                                        //Error validacion
                                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("49");
                                                    }
                                                } else {
                                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("48");
                                                }
                                            } else {
                                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                            }

                                        } else {
                                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                                        }
                                    } else {
                                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("3");
                                    }
                                } else {
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("2");
                                }
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("47");
                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("43");
                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("42");
                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");
            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        respuesta = new Respuesta();
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    private RespuestaSincronizacion solicitudAnulaNotaFiscalCreditoDebito(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39125 respuesta39125 = consumerWS39125.anulaNotaFiscalElectronicaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39125.isTransaccion());
                if (respuesta39125.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39125.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39125.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39125.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39125.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39115 respuesta39115 = consumerWS39115.anulaNotaFiscalComputarizadaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39115.isTransaccion());
                if (respuesta39115.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39115.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39115.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39115.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39115.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion verificaAnulacionNotaFiscalCreditoDebito(SolicitudCliente solicitud) {
        try {
            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                Respuesta39125 respuesta39125 = consumerWS39125.validaAnulacionNotaFiscalElectronicaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39125.isTransaccion());
                if (respuesta39125.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39125.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39125.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39125.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39125.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;
            }

            if (solicitud.getCodigoModalidad() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                Respuesta39115 respuesta39115 = consumerWS39115.validaAnulacionNotaFiscalComputarizadaCreditoDebito(solicitud);
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                respuesta.setTransaccion(respuesta39115.isTransaccion());
                if (respuesta39115.isTransaccion()) {
                    respuesta.setCodigoRecepcion(respuesta39115.getCodigoRecepcion());
                } else {
                    String mensajeServicio = "";
                    List<ParMensajeServicio> lista = new ArrayList<>();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(Long.valueOf(respuesta39115.getCodigoEstado()));
                    lista.add(parMensajeServicio);
                    for (int i = 0; i < respuesta39115.getListaCodigosRespuestas().size(); i++) {
                        parMensajeServicio = parMensajeServicioService.leerPorCodigo(respuesta39115.getListaCodigosRespuestas().get(i).longValue());
                        lista.add(parMensajeServicio);
                        mensajeServicio = mensajeServicio + parMensajeServicio.getCodigo() + " - " + parMensajeServicio.getDescripcion() + "\n";
                    }
                    respuesta.setListaParMensajeServicio(lista);
                }
                return respuesta;

            }
        } catch (Exception ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
