/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39112;
import com.bap.api.consumer.ConsumerWS39113;
import com.bap.api.dto.CabeceraDTO;
import com.bap.api.dto.ConsultaParametros;
import com.bap.api.dto.DetalleDTO;
import com.bap.api.dto.DocumentoFiscalDTO;
import com.bap.api.dto.Entidad;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.Respuesta39112;
import com.bap.api.dto.Respuesta39113;
import com.bap.api.dto.RespuestaDocumentoFiscal;
import com.bap.api.dto.RespuestaSincronizacion;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstadoDocumento;
import com.bap.api.enums.EnumParTipoEmision;
import com.bap.api.enums.EnumParTipoModalidad;
import com.bap.api.model.api.ApiActividad;
import com.bap.api.model.api.ApiCliente;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiEmpresaDocumento;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.api.ApiFacturaDetalle;
import com.bap.api.model.api.ApiItemHomologado;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParEstadoDocumento;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.model.par.ParTipoModalidad;
import com.bap.api.model.par.ParTipoMoneda;
import com.bap.api.repo.api.ApiFacturaRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiActividadService;
import com.bap.api.services.api.ApiClienteService;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
import com.bap.api.services.api.ApiEmpresaDocumentoService;
import com.bap.api.services.api.ApiFacturaService;
import com.bap.api.services.api.ApiItemHomologadoService;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.api.ApiSucursalService;
import com.bap.api.services.par.ParCondicionService;
import com.bap.api.services.par.ParLeyendaFacturaService;
import com.bap.api.services.par.ParMensajeFacturadorService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.services.par.ParTipoDocumentoFiscalService;
import com.bap.api.services.par.ParTipoDocumentoSectorService;
import com.bap.api.services.par.ParTipoEmisionService;
import com.bap.api.services.par.ParTipoMetodoPagoService;
import com.bap.api.services.par.ParTipoMonedaService;
import com.bap.api.utils.ConnectionUtil;
import com.bap.api.utils.FacturaUtils;
import com.bap.api.utils.FechaUtils;
import com.bap.api.utils.FirmadorUtils;
import com.bap.api.utils.GeneratorXML;
import com.bap.api.utils.NumberUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ClassPathResource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.xml.sax.SAXException;
import com.bap.api.services.par.ParEstadoDocumentoService;

/**
 *
 * @author ruth
 */
@Service
public class ApiFacturaServiceImpl implements ApiFacturaService {

    @Autowired
    private ApiFacturaRepo repo;

    @Autowired
    AdmConsultasService admConsultasService;

    @Autowired
    ApiConfiguracionService apiConfiguracionService;

    @Autowired
    ApiDosificacionService apiDosificacionService;

    @Autowired
    ApiClienteService apiClienteService;

    @Autowired
    ParTipoMetodoPagoService parTipoMetodoPagoService;

    @Autowired
    ParTipoMonedaService parTipoMonedaService;

    @Autowired
    ParTipoDocumentoSectorService parTipoDocumentoSectorService;

    @Autowired
    ParTipoEmisionService parTipoEmisionService;

    @Autowired
    ParTipoDocumentoFiscalService parTipoDocumentoFiscalService;

    @Autowired
    ApiSucursalService apiSucursalService;

    @Autowired
    ApiPuntoVentaService apiPuntoVentaService;

    @Autowired
    ApiConfiguracionSucursalService apiConfiguracionSucursalService;

    @Autowired
    ApiDosificacionSucursalService apiDosificacionSucursalService;

    @Autowired
    ApiConfiguracionPuntoVentaService apiConfiguracionPuntoVentaService;

    @Autowired
    ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;

    @Autowired
    ParCondicionService parCondicionService;

    @Autowired
    ConsumerWS39113 consumerWS39113;

    @Autowired
    ConsumerWS39112 consumerWS39112;

    @Autowired
    ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParMensajeFacturadorService parMensajeFacturadorService;

    @Autowired
    private ApiActividadService apiActividadService;

    @Autowired
    ParLeyendaFacturaService parLeyendaFacturaService;

    @Autowired
    ApiEmpresaDocumentoService apiEmpresaDocumentoService;

    @Autowired
    ApiItemHomologadoService apiItemHomologadoService;

    @Autowired
    ParEstadoDocumentoService parEstadoDocumentoService;

    @Override
    public ApiFactura registrar(ApiFactura t) {
//        ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
//        t.setParCondicion(parCondicion);
        t.getApiFacturaDetalle().forEach(det -> {
            det.setApiFactura(t);
        });
        return repo.save(t);
    }

    @Override
    public ApiFactura modificar(ApiFactura t) {
        try {
            t.setUsuarioModificacion("admin");
            t.setFechaModificacion(new Date());
            return repo.save(t);
        } catch (Exception e) {
            System.out.println("Factura  tiene " + e.getMessage());
            return null;
        }

    }

//    @Override
//    public ApiFactura modificar(ApiFactura t) {
//        ApiFactura obj = leerPorId(t.getIdFactura());
//        if (obj != null) {
//            obj.setParCondicion(t.getParCondicion());
//            obj.setCodigoRecepcionAnulado(t.getCodigoRecepcionAnulado());
//            obj.setParMotivoAnulacion(t.getParMotivoAnulacion());
//            obj.setUsuarioModificacion("admin");
//            obj.setFechaModificacion(new Date());
//            return repo.save(t);
//        }
//        return null;
//    }
    @Override
    public ApiFactura leerPorId(Long id) {
        return repo.findByIdFactura(id);
    }

    @Override
    public List<ApiFactura> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad) {
        try {
            ConsultaParametros consultaParametros = admConsultasService.consultarDatosUsuario(login);
            if (consultaParametros.getIdPuntoVenta() == null) {
                return repo.findPorSucursal(consultaParametros.getIdSucursal(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
            } else {
                return repo.findPorPuntoVenta(consultaParametros.getIdPuntoVenta(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<ApiFactura> listar() {
        return repo.findAllFactura();
    }

    @Override
    public void eliminar(ApiFactura t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public long verificaRepiteFacturaPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaPorSucursal(idSucursal, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long verificaRepiteFacturaPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaPorPuntoVenta(idPuntoVenta, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ApiFactura getFacturaByCliente(Long idCliente) throws Exception {
        return repo.findFacturaByIdCliente(idCliente);
    }

    @Override
    public ApiFactura findFacturaPorSucursal(Long numeroFactura, String numeroAutorizacionCuf, Long idSucursal, String fechaEmisionFactura) {
        return repo.findFacturaPorSucursal(numeroFactura, numeroAutorizacionCuf, idSucursal, fechaEmisionFactura);
    }

    @Override
    public ApiFactura findFacturaPorPuntoVenta(Long numeroFactura, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmisionFactura) {
        return repo.findFacturaPorPuntoVenta(numeroFactura, numeroAutorizacionCuf, idPuntoVenta, fechaEmisionFactura);
    }

    @Override
    public RespuestaDocumentoFiscal validaFacturaEstandar(DocumentoFiscalDTO documentoFiscalDTO) {
        RespuestaDocumentoFiscal respuesta = new RespuestaDocumentoFiscal();
        ParMensajeFacturador parMensajeFacturador = null;
        CabeceraDTO cabeceraDTO = documentoFiscalDTO.getCabeceraDTO();

        SolicitudCliente solicitud = admConsultasService.wsSin(cabeceraDTO.getUsuario());
        boolean sw = true;
        if (solicitud != null) {
            ApiSucursal apiSucursal = null;
            ApiPuntoVenta apiPuntoVenta = null;
            if (solicitud.getCodigoPuntoVenta() == 0) {
                if (cabeceraDTO.getCodigoSucursal() == solicitud.getCodigoSucursal()) {
                    apiSucursal = solicitud.getApiSucursal();
                }
            } else if (cabeceraDTO.getCodigoPuntoVenta() == solicitud.getCodigoPuntoVenta()) {
                apiPuntoVenta = solicitud.getApiPuntoVenta();
            }
            ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPorTipos(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad());
//            if (FacturaUtils.cufdVigente(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(solicitud.getApiConfiguracion().getUtcFechaVigencia()))) {

            ApiCliente apiCliente = apiClienteService.leerPorIdEmpresaAndCodigoCliente(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoCliente());
            ParTipoMetodoPago parTipoMetodoPago = parTipoMetodoPagoService.leerPorCodigo(cabeceraDTO.getCodigoMetodoPago());
            ParTipoMoneda parTipoMoneda = parTipoMonedaService.leerPorCodigo(cabeceraDTO.getCodigoMoneda());
            ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(cabeceraDTO.getLeyenda());
            ParTipoDocumentoSector parTipoDocumentoSector = parTipoDocumentoSectorService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoSector());
            ParTipoDocumentoFiscal parTipoDocumentoFiscal = parTipoDocumentoFiscalService.leerPorCodigo(cabeceraDTO.getCodigoDocumentoFiscal());
            ApiActividad apiActividad = apiActividadService.leerPorIdEmpresaAndCodigoActividad(solicitud.getIdEmpresa(), cabeceraDTO.getCodigoActividadEconomica());
            if (apiEmpresaDocumento != null && apiActividad != null && apiCliente != null && parTipoMetodoPago != null
                    && parTipoMoneda != null && parLeyendaFactura != null
                    && parTipoDocumentoSector != null && parTipoDocumentoFiscal != null
                    && cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ZERO) > 0 && cabeceraDTO.getMontoTotal().compareTo(BigDecimal.ZERO) > 0
                    && cabeceraDTO.getMontoTotalMoneda().compareTo(BigDecimal.ZERO) > 0
                    && cabeceraDTO.getNumeroDocumentoFiscal() != null) {
                if (cabeceraDTO.getCodigoMetodoPago() == 2) {
                    if (cabeceraDTO.getNumeroTarjeta() == null) {
                        sw = false;
                    }
                }
                if (sw) {
                    if (cabeceraDTO.getCodigoMoneda() == 688) {
                        if (cabeceraDTO.getMontoTotal().compareTo(cabeceraDTO.getMontoTotalMoneda()) != 0) {
                            sw = false;
                        }
                        if (cabeceraDTO.getTipoCambio().compareTo(BigDecimal.ONE) != 0) {
                            sw = false;
                        }
                    }
                    if (sw) {
                        long repiteNumeroDocumento = 0;
                        if (solicitud.getCodigoPuntoVenta() == 0) {
                            repiteNumeroDocumento = verificaRepiteFacturaPorSucursal(apiSucursal.getIdSucursal(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());

                        } else {
                            repiteNumeroDocumento = verificaRepiteFacturaPorPuntoVenta(apiPuntoVenta.getIdPuntoVenta(), cabeceraDTO.getCodigoDocumentoFiscal(), cabeceraDTO.getCodigoDocumentoSector(), cabeceraDTO.getCodigoTipoModalidad(), cabeceraDTO.getNumeroDocumentoFiscal());
                        }
                        if (repiteNumeroDocumento > 0) {
                            sw = false;
                        }
                        if (sw) {
                            List<DetalleDTO> listaDetalleDTO = documentoFiscalDTO.getListaDetalleDTO();

                            List<ApiFacturaDetalle> listaApiFacturaDetalle = new ArrayList<>();
                            ApiFacturaDetalle apiFacturaDetalle;
                            for (DetalleDTO detalleDTO : listaDetalleDTO) {
                                ApiItemHomologado apiItemHomologado = apiItemHomologadoService.leerPorIdEmpresaAndCodigoProducto(solicitud.getIdEmpresa(), detalleDTO.getCodigoProducto());
                                if (apiItemHomologado == null) {
                                    sw = false;
                                    break;
                                } else {
                                    if (detalleDTO.getCantidad().compareTo(BigDecimal.ZERO) < 1) {
                                        sw = false;
                                        break;
                                    }
                                    if (detalleDTO.getPrecioUnitario().compareTo(BigDecimal.ZERO) < 1) {
                                        sw = false;
                                        break;
                                    }
                                    if (detalleDTO.getSubTotal().compareTo(BigDecimal.ZERO) < 1) {
                                        sw = false;
                                        break;
                                    }
                                    apiFacturaDetalle = new ApiFacturaDetalle();
                                    apiFacturaDetalle.setApiItemHomologado(apiItemHomologado);
                                    apiFacturaDetalle.setCantidad(detalleDTO.getCantidad());
                                    apiFacturaDetalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                                    apiFacturaDetalle.setMontoDescuento(detalleDTO.getMontoDescuento());
                                    //apiFacturaDetalle.setSubTotal(detalleDTO.getSubTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
                                    apiFacturaDetalle.setSubTotal(detalleDTO.getSubTotal());
                                    listaApiFacturaDetalle.add(apiFacturaDetalle);
                                }
                            }
                            if (sw) {
                                ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.valueOf(EnumParTipoEmision.OFF_LINE.getCodigo()));
                                ApiDosificacion apiDosificacion = solicitud.getApiDosificacion();
                                ApiConfiguracion apiConfiguracion = solicitud.getApiConfiguracion();
                                ApiFactura apiFactura = new ApiFactura();
                                apiFactura.setNitEmisor(cabeceraDTO.getNitEmisor());
                                apiFactura.setNumeroFactura(cabeceraDTO.getNumeroDocumentoFiscal());
                                if (cabeceraDTO.getCodigoPuntoVenta() == 0) {
                                    apiFactura.setApiSucursal(solicitud.getApiSucursal());
                                } else {
                                    apiFactura.setApiPuntoVenta(solicitud.getApiPuntoVenta());
                                }
                                apiFactura.setFechaEmision(cabeceraDTO.getFechaEmision());
                                apiFactura.setUtcFechaEmision(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(FechaUtils.convertToLocalDateTime(cabeceraDTO.getFechaEmision())));
                                //apiFactura.setMontoTotal(cabeceraDTO.getMontoTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
                                apiFactura.setMontoTotal(cabeceraDTO.getMontoTotal());
                                apiFactura.setMontoDescuento(cabeceraDTO.getMontoDescuento());
                                //apiFactura.setMontoTotalMoneda(cabeceraDTO.getMontoTotalMoneda().setScale(2, BigDecimal.ROUND_HALF_UP));
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
                                ParEstadoDocumento parEstadoFactura = parEstadoDocumentoService.leerPorCodigo(EnumParEstadoDocumento.DOCUMENTO_VALIDO.getCodigo());
                                apiFactura.setParEstadoDocumento(parEstadoFactura);
                                registrar(apiFactura);
                                respuesta.setApiFactura(apiFactura);
                                respuesta.setSolicitud(solicitud);
                                respuesta.setTransaccion(true);
                            } else {
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("58");

                            }
                        } else {
                            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("5");

                        }
                    } else {
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("38");

                    }
                } else {
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("28");

                }
            } else {
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("57");
            }
        } else {
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("54");

        }
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    @Override
    public Respuesta verficaComunicacion(ParTipoModalidad parTipoModalidad) {
        int SIN_CONNECTION = 0, INTERNET_CONNECTION = 0;
        Respuesta respuesta = new Respuesta();
        ParMensajeFacturador parMensajeFacturador = null;
        INTERNET_CONNECTION = ConnectionUtil.checkInternet();
        if (INTERNET_CONNECTION == 66) {
            if (parTipoModalidad.getCodigo().intValue() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                SIN_CONNECTION = consumerWS39113.verificaComunicacion();
            } else {
                SIN_CONNECTION = consumerWS39112.verificaComunicacion();
            }
            if (SIN_CONNECTION == 66) {
                respuesta.setTransaccion(true);
                return respuesta;
            }
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("60");
            respuesta.setTransaccion(false);
        } else {
            parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("59");
            respuesta.setTransaccion(false);
        }
        respuesta.setParMensajeFacturador(parMensajeFacturador);
        return respuesta;
    }

    @Override
    public Respuesta enviaFacturaEstandar(ApiFactura apiFactura, SolicitudCliente solicitud, CabeceraDTO cabeceraDTO) {
        Respuesta respuesta = new Respuesta();
        ParMensajeFacturador parMensajeFacturador = null;
        try {
            boolean swCufVigente = FacturaUtils.cufdVigente(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(solicitud.getApiConfiguracion().getUtcFechaVigencia()));
            if (swCufVigente) {
                LocalDateTime fechaEmision = FechaUtils.convertToLocalDateTime(cabeceraDTO.getFechaEmision());
                cabeceraDTO.setUtfFechaEmision(fechaEmision);
                String utcFechaEmision = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(fechaEmision);
                ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.valueOf(EnumParTipoEmision.ON_LINE.getCodigo()));
                apiFactura.setParTipoEmision(parTipoEmision);
                cabeceraDTO.setTipoEmision(parTipoEmision.getCodigo());
                String cuf = FacturaUtils.generaFacturaCUF(cabeceraDTO);
                if (cuf != null) {
                    apiFactura.setCuf(cuf);
                    RespuestaSincronizacion respuestaRecepcion = recepcionFacturaEstandar(apiFactura, solicitud);
                    if (respuestaRecepcion != null) {
                        if (respuestaRecepcion.isTransaccion()) {
                            apiFactura.setUtcFechaEmision(utcFechaEmision);
                            apiFactura.setCodigoRecepcion(respuestaRecepcion.getCodigoRecepcion());
                            apiFactura.setNombreArchivoXml(respuestaRecepcion.getFileXMLSinFirma());
                            apiFactura.setNombreArchivoXmlFirmado(respuestaRecepcion.getFileXMLFirmado());
                            Thread.sleep(1000);
                            RespuestaSincronizacion respuestaValidacion = validaRecepcionFacturaEstandar(apiFactura, solicitud);
                            if (respuestaValidacion != null) {
                                if (respuestaValidacion.isTransaccion()) {
                                    ParEstadoDocumento parEstadoDocumento = parEstadoDocumentoService.leerPorCodigo(EnumParEstadoDocumento.DOCUMENTO_RECEPCIONADA.getCodigo());
                                    //ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.valueOf(EnumParTipoEmision.ON_LINE.getCodigo()));
                                    apiFactura.setParEstadoDocumento(parEstadoDocumento);                                    
                                    modificar(apiFactura);
                                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("37");
                                    respuesta.setTransaccion(true);
                                } else {
                                     respuesta.setParMensajeFacturador(respuestaRecepcion.getParMensajeFacturador());
                                    respuesta.setListaParMensajeServicio(respuestaValidacion.getListaParMensajeServicio());
                                }
                            } else {                               
                                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("36");
                            }
                        } else {
                            respuesta.setParMensajeFacturador(respuestaRecepcion.getParMensajeFacturador());
                            respuesta.setListaParMensajeServicio(respuestaRecepcion.getListaParMensajeServicio());
                        }
                    } else {
                        //Error recepcion
                        parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("35");
                        respuesta.setParMensajeFacturador(parMensajeFacturador);
                    }
                } else {
                    //error CUF
                    parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("13");
                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                }
            } else {
                //CUFD fuera de tolerancia
                parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("1");
                respuesta.setParMensajeFacturador(parMensajeFacturador);
            }
            
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return respuesta;
    }

    private RespuestaSincronizacion recepcionFacturaEstandar(ApiFactura apiFactura, SolicitudCliente solicitud) {
        try {
            //1)Generar Archivo XML asociado al Documento Fiscal Electrónico                        
            String xml = null;
            String nombreFileXMLFirmado = null;
            String nombreFileXML = apiFactura.getApiDosificacion().getDirectorioXml().trim() + "/" + apiFactura.getCuf().trim() + "_SIN_FIRMA.xml";
            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                xml = GeneratorXML.facturaElectronicaEstandarToXML(apiFactura, nombreFileXML);
            }
            if (apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo() == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                xml = GeneratorXML.facturaComputarizadaEstandarToXML(apiFactura, nombreFileXML);
            }
            if (xml != null) {
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
                    ParMensajeFacturador parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("34");
                    respuesta.setParMensajeFacturador(parMensajeFacturador);
                    return respuesta;
                }
            } else {
                RespuestaSincronizacion respuesta = new RespuestaSincronizacion();
                ParMensajeFacturador parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("33");
                respuesta.setParMensajeFacturador(parMensajeFacturador);
                return respuesta;
            }

        } // TODO Auto-generated catch block
        // TODO Auto-generated catch block
        catch (IOException | URISyntaxException | ParserConfigurationException | XMLSecurityException | SAXException ex) {
            Logger.getLogger(ApiDocumentoFiscalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private RespuestaSincronizacion validaRecepcionFacturaEstandar(ApiFactura apiFactura, SolicitudCliente solicitud) {
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
    public byte[] facturaEstandarPdf(Long idFactura) {
        ApiFactura apiFactura = this.leerPorId(idFactura);
        String jasperReportFileName = "facturaEstandar.jasper";
        return generarFacturaEstandar(apiFactura, jasperReportFileName);
    }

    private byte[] generarFacturaEstandar(ApiFactura apiFactura, String jasperReportFileName) {
        byte[] data = null;

        byte[] qrCode = getQRCode(apiFactura);
        InputStream qrInputStream = new ByteArrayInputStream(qrCode);
//        String fechaEmision = FechaUtils.convertLocalDateTimeToFormatReport(apiFactura.getFechaEnvio());
        String fechaEmision = FechaUtils.convertLocalDateToFormatReport(apiFactura.getFechaEmision());
        String razonSocialEmpresa = admConsultasService.consultarDatosEmpresa(apiFactura.getNitEmisor()).getRazonSocial();
        Map<String, Object> reportParam = new HashMap<String, Object>();
        reportParam.put("razonSocialEmisor", razonSocialEmpresa);
        reportParam.put("sucursal", "Sucursal No. " + apiFactura.getApiSucursal().getCodigoSucursal());
        reportParam.put("direccion", apiFactura.getApiSucursal().getDireccion());
        reportParam.put("zona", apiFactura.getApiSucursal().getZona());
        reportParam.put("telefonos", "Telefono " + apiFactura.getApiSucursal().getTelefonos());
        reportParam.put("departamento", apiFactura.getApiSucursal().getDepartamento());
        reportParam.put("nitEmisor", "NIT: " + apiFactura.getNitEmisor());
        reportParam.put("numeroDocumentoFiscal", "Documento Fiscal Nro.: " + apiFactura.getNumeroFactura());
        reportParam.put("cuf", "CUF: " + apiFactura.getCuf());
        reportParam.put("actividadEconomica", apiFactura.getApiActividad().getDescripcion());
        reportParam.put("tipoModalidad", apiFactura.getApiDosificacion().getParTipoModalidad().getDescripcion());
        reportParam.put("fechaEmision", "Fecha de Emision: " + fechaEmision);
        reportParam.put("nitCliente", "NIT/CI: " + apiFactura.getApiCliente().getNumeroDocumento());
        reportParam.put("nombreRazonSocialCliente", "Nombre/Razon Social: " + apiFactura.getApiCliente().getNombreRazonSocial());
        reportParam.put("codigoCliente", "Codigo Cliente " + apiFactura.getApiCliente().getCodigoCliente());
        reportParam.put("TOTAL", apiFactura.getMontoTotal());
        BigDecimal descuento = BigDecimal.ZERO;
        if (apiFactura.getMontoDescuento() != null) {
            descuento = apiFactura.getMontoDescuento();
        }
        reportParam.put("DESCUENTO", descuento);
        BigDecimal subtotal = apiFactura.getMontoTotal().subtract(descuento).setScale(2, BigDecimal.ROUND_HALF_UP);
        reportParam.put("TOTAL_PAGAR", subtotal);
        reportParam.put("IMPORTE_BASE", subtotal);
        String LITERAL = "Son: " + NumberUtils.convertirLiteral(subtotal, "bolivianos", false);
        String LEYENDA = "Ley N° 453: " + apiFactura.getParLeyendaFactura().getDescripcion();
        reportParam.put("LITERAL", LITERAL);
        reportParam.put("LEYENDA", LEYENDA);
        reportParam.put("QR_DATA", qrInputStream);
        try {
            File file = new ClassPathResource("/reports/" + jasperReportFileName.trim()).getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), reportParam, new JRBeanCollectionDataSource(apiFactura.getApiFacturaDetalle()));
            data = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private byte[] getQRCode(ApiFactura apiFactura) {
        try {
            String var_0 = "http://dominio.impuestos.gob.bo/servicio/pagina.xhtml";
            String var_1 = apiFactura.getCuf();
            String var_2 = apiFactura.getNumeroFactura().toString();
            String var_3 = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().toString();
            String var_4 = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString();

            String qrData = var_0 + "|" + var_1 + "|" + var_2 + "|" + var_3 + "|" + var_4;

            ByteArrayOutputStream out = QRCode.from(qrData).to(ImageType.JPG).withSize(250, 250).stream();

            byte[] qrCode = out.toByteArray();
            //log.info("qrCode::::: " + qrCode);
            return qrCode;

        } catch (Exception e) {
        }
        return null;

    }

}
