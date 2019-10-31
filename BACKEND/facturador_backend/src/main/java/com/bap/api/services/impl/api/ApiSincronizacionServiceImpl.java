/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.api.fesc_39118.ActividadesDto;
import com.api.fesc_39118.ParametricasDto;
import com.api.fesc_39118.ProductosDto;
import com.bap.api.consumer.ConsumerWS39118;
import com.bap.api.consumer.ConsumerWS39268;
import com.bap.api.dto.RespuestaSincronizacion;
import com.bap.api.dto.Respuesta39118;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.model.api.ApiActividad;
import com.bap.api.model.api.ApiItem;
import com.bap.api.model.api.ApiSincronizacion;
import com.bap.api.model.par.ParEventoSignificativo;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.model.par.ParMotivoAnulacion;
import com.bap.api.model.par.ParPaisOrigen;
import com.bap.api.model.par.ParSincronizacion;
import com.bap.api.model.par.ParTipoAmbiente;
import com.bap.api.model.par.ParTipoComponente;
import com.bap.api.model.par.ParTipoDepartamento;
import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.model.par.ParTipoDocumentoIdentidad;
import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.model.par.ParTipoModalidad;
import com.bap.api.model.par.ParTipoMoneda;
import com.bap.api.model.par.ParTipoPuntoVenta;
import com.bap.api.model.par.ParUnidadMedida;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.bap.api.services.api.ApiSincronizacionService;
import com.bap.api.repo.api.ApiSincronizacionRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.par.ParEventoSignificativoService;
import com.bap.api.services.par.ParLeyendaFacturaService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.services.par.ParMotivoAnulacionService;
import com.bap.api.services.par.ParPaisOrigenService;
import com.bap.api.services.par.ParSincronizacionService;
import com.bap.api.services.par.ParTipoAmbienteService;
import com.bap.api.services.par.ParTipoComponenteService;
import com.bap.api.services.par.ParTipoDepartamentoService;
import com.bap.api.services.par.ParTipoDocumentoFiscalService;
import com.bap.api.services.par.ParTipoDocumentoIdentidadService;
import com.bap.api.services.par.ParTipoDocumentoSectorService;
import com.bap.api.services.par.ParTipoEmisionService;
import com.bap.api.services.par.ParTipoMetodoPagoService;
import com.bap.api.services.par.ParTipoModalidadService;
import com.bap.api.services.par.ParTipoMonedaService;
import com.bap.api.services.par.ParTipoPuntoVentaService;
import com.bap.api.services.par.ParUnidadMedidaService;
import java.util.ArrayList;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bap.api.services.api.ApiActividadService;
import com.bap.api.services.api.ApiItemService;
import com.bap.api.services.par.ParActividadService;

/**
 *
 * @author ruth
 */
@Service
public class ApiSincronizacionServiceImpl implements ApiSincronizacionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiSincronizacionServiceImpl.class);

    @Autowired
    private ApiSincronizacionRepo repo;

    @Autowired
    ConsumerWS39118 consumerWS39118;

    @Autowired
    ConsumerWS39268 consumerWS39268;

    @Autowired
    private ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParSincronizacionService parSincronizacionService;

    @Autowired
    ParEventoSignificativoService parEventoSignificativoService;

    @Autowired
    ParLeyendaFacturaService parLeyendaFacturaService;

    @Autowired
    ParMotivoAnulacionService parMotivoAnulacionService;

    @Autowired
    ParPaisOrigenService parPaisOrigenService;

    @Autowired
    ParTipoAmbienteService parTipoAmbienteService;

    @Autowired
    ParTipoComponenteService parTipoComponenteService;

    @Autowired
    ParTipoDepartamentoService parTipoDepartamentoService;

    @Autowired
    ParTipoDocumentoFiscalService parTipoDocumentoFiscalService;

    @Autowired
    ParTipoDocumentoIdentidadService parTipoDocumentoIdentidadService;

    @Autowired
    ParTipoDocumentoSectorService parTipoDocumentoSectorService;

    @Autowired
    ParTipoEmisionService parTipoEmisionService;

    @Autowired
    ParTipoMetodoPagoService parTipoMetodoPagoService;

    @Autowired
    ParTipoModalidadService parTipoModalidadService;

    @Autowired
    ParTipoMonedaService parTipoMonedaService;

    @Autowired
    ParUnidadMedidaService parUnidadMedidaService;

    @Autowired
    ParTipoPuntoVentaService parTipoPuntoVentaService;

    @Autowired
    ApiActividadService apiActividadService;

    @Autowired
    ApiItemService apiItemService;

    @Autowired
    ParActividadService parActividadService;

    @Autowired
    AdmConsultasService admConsultasService;

    @Override
    public ApiSincronizacion registrar(ApiSincronizacion t) {
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiSincronizacion modificar(ApiSincronizacion t) {
        t.setFechaModificacion(new Date());
        return repo.save(t);
    }

    @Override
    public ApiSincronizacion leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiSincronizacion> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiSincronizacion t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public List<RespuestaSincronizacion> codigosCatalogos(String grupo) {
        List<RespuestaSincronizacion> listaRespuestaSincronizacion = new ArrayList<RespuestaSincronizacion>();
        Long idEmpresa = 1l;
        switch (grupo.trim()) {
            case "EVENTO_SIGNIFICATIVO":
                parEventoSignificativoService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "LEYENDA_FACTURA":
                parLeyendaFacturaService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "MENSAJE_SERVICIO":
                parMensajeServicioService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "MOTIVO_ANULACION":
                parMotivoAnulacionService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "PAIS_ORIGEN":
                parPaisOrigenService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;

            case "TIPO_AMBIENTE":
                parTipoAmbienteService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_COMPONENTE":
                parTipoComponenteService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_DEPARTAMENTO":
                parTipoDepartamentoService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_DOCUMENTO_FISCAL":
                parTipoDocumentoFiscalService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_DOCUMENTO_IDENTIDAD":
                parTipoDocumentoIdentidadService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_DOCUMENTO_SECTOR":
                parTipoDocumentoSectorService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_EMISION":
                parTipoEmisionService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_METODO_PAGO":
                parTipoMetodoPagoService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_MODALIDAD":
                parTipoModalidadService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_MONEDA":
                parTipoMonedaService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "UNIDAD_MEDIDA":
                parUnidadMedidaService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "TIPO_PUNTO_VENTA":
                parTipoPuntoVentaService.listar().forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigo());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "ACTIVIDADES":
                apiActividadService.listarPorIdEmpresa(idEmpresa).forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(Long.valueOf(dato.getCodigoActividad()));
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
            case "PRODUCTO_SERVICIO":
                apiItemService.listarPorIdEmpresa(idEmpresa).forEach(dato -> {
                    RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                    respuestaSincronizacion.setCodigo(dato.getCodigoProductoSin());
                    respuestaSincronizacion.setDescripcion(dato.getDescripcion());
                    respuestaSincronizacion.setParActividad(dato.getParActividad());
                    listaRespuestaSincronizacion.add(respuestaSincronizacion);
                });
                break;
        }

        return listaRespuestaSincronizacion;
    }

    @Override
    public List<RespuestaSincronizacion> sincronizacionDiaria(String login) {
        SolicitudCliente solicitudCliente = admConsultasService.wsSin(login, null);
        List<RespuestaSincronizacion> listaRespuestaSincronizacion = new ArrayList<RespuestaSincronizacion>();
        long codigo = consumerWS39118.verificarComunicacion();
        if (codigo == 66l) {
            List<ParSincronizacion> listaParSincronizacion = parSincronizacionService.listar();
            for (ParSincronizacion parSincronizacion : listaParSincronizacion) {
                solicitudCliente.setGrupo(parSincronizacion.getGrupo());
                solicitudCliente.setCodigoAutorizacion(0);
                solicitudCliente.setUsuario(login);
                switch (parSincronizacion.getGrupo().trim()) {
                    case "EVENTO_SIGNIFICATIVO":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarEventoSignificativo(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "LEYENDA_FACTURA":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarLeyendaFactura(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "MENSAJE_SERVICIO":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarMensajeServicio(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "ACTIVIDADES":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarActividad(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "PRODUCTO_SERVICIO":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarProductoServicio(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "MOTIVO_ANULACION":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarMotivoAnulacion(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "PAIS_ORIGEN":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarPaisOrigen(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_AMBIENTE":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoAmbiente(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_COMPONENTE":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoComponente(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_DEPARTAMENTO":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoDepartamento(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_DOCUMENTO_FISCAL":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoDocumentoFiscal(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_DOCUMENTO_IDENTIDAD":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoDocumentoIdentidad(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_DOCUMENTO_SECTOR":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoDocuementoSector(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_EMISION":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoEmision(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_METODO_PAGO":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion
                                .add(sincronizarTipoMetodoPago(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_MODALIDAD":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoModalidad(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_MONEDA":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoMoneda(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "UNIDAD_MEDIDA":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarUnidadMedida(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                    case "TIPO_PUNTO_VENTA":
                        LOGGER.info("ingreso a sincronizar " + parSincronizacion.getGrupo());
                        listaRespuestaSincronizacion.add(sincronizarTipoPuntoVenta(solicitudCliente));
                        LOGGER.info("##Fin Sincronizacion " + parSincronizacion.getGrupo());
                        break;
                }
            }
        } else {
            if (codigo != -1) {
                ParMensajeServicio a = parMensajeServicioService.leerPorCodigo(codigo);
                List<ParMensajeServicio> l = new ArrayList<>();
                l.add(a);
                RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
                respuestaSincronizacion.setListaParMensajeServicio(l);
                listaRespuestaSincronizacion.add(respuestaSincronizacion);
            }
        }
        return listaRespuestaSincronizacion;
    }

    @Override
    public RespuestaSincronizacion sincronizacionCodigoAutorizacion(String grupo, String login, int codigoAutorizacion) {
        SolicitudCliente solicitud = admConsultasService.wsSin(login, null);
        solicitud.setGrupo(grupo);
        solicitud.setCodigoAutorizacion(codigoAutorizacion);

        long codigo = consumerWS39118.verificarComunicacion();
        RespuestaSincronizacion respuestaSincronizacion = null;
        if (codigo == 66l) {
            switch (grupo.trim()) {
                case "EVENTO_SIGNIFICATIVO":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarEventoSignificativo(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "LEYENDA_FACTURA":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarLeyendaFactura(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "MENSAJE_SERVICIO":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarMensajeServicio(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "ACTIVIDADES":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarActividad(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "PRODUCTO_SERVICIO":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarProductoServicio(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "MOTIVO_ANULACION":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarMotivoAnulacion(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "PAIS_ORIGEN":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarPaisOrigen(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_AMBIENTE":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoAmbiente(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_COMPONENTE":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoComponente(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_DEPARTAMENTO":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoDepartamento(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_DOCUMENTO_FISCAL":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoDocumentoFiscal(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_DOCUMENTO_IDENTIDAD":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoDocumentoIdentidad(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_DOCUMENTO_SECTOR":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoDocuementoSector(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_EMISION":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoEmision(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_METODO_PAGO":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoMetodoPago(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_MODALIDAD":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoModalidad(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_MONEDA":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoMoneda(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "UNIDAD_MEDIDA":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarUnidadMedida(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
                case "TIPO_PUNTO_VENTA":
                    LOGGER.info("ingreso a sincronizar " + grupo);
                    respuestaSincronizacion = sincronizarTipoPuntoVenta(solicitud);
                    LOGGER.info("##Fin Sincronizacion " + grupo);
                    break;
            }

        } else {
            if (codigo != -1) {
                ParMensajeServicio a = parMensajeServicioService.leerPorCodigo(codigo);
                List<ParMensajeServicio> l = new ArrayList<>();
                l.add(a);
                RespuestaSincronizacion r = new RespuestaSincronizacion();
                r.setTransaccion(false);
                r.setListaParMensajeServicio(l);
            }
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarMensajeServicio(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118.sincronizarMensajeServicio(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParMensajeServicio> listaDB = parMensajeServicioService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParMensajeServicio parMensajeServicio : listaDB) {
                    if (parametricasDto.getCodigoClasificador().compareTo(parMensajeServicio.getCodigo().intValue()) == 0) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }

        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoDocumentoIdentidad(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();

        Respuesta39118 respuesta = consumerWS39118.sincronizarParametricaTipoDocumentoIdentidad(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {

            List<ParTipoDocumentoIdentidad> listaDB = parTipoDocumentoIdentidadService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoDocumentoIdentidad parTipoDocumentoIdentidad : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoDocumentoIdentidad.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarLeyendaFactura(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118.sincronizarLeyendaFactura(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParLeyendaFactura> listaDB = parLeyendaFacturaService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParLeyendaFactura parLeyendaApitura : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parLeyendaApitura.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarEventoSignificativo(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaEventoSignificativo(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParEventoSignificativo> listaDB = parEventoSignificativoService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParEventoSignificativo parEventoSignificativo : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parEventoSignificativo.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }

        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarMotivoAnulacion(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();

        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaMotivoAnulacion(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParMotivoAnulacion> listaDB = parMotivoAnulacionService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParMotivoAnulacion parMotivoAnulacion : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parMotivoAnulacion.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarPaisOrigen(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();

        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaPaisOrigen(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParPaisOrigen> listaDB = parPaisOrigenService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParPaisOrigen parPaisOrigen : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parPaisOrigen.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoAmbiente(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();

        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoAmbiente(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoAmbiente> listaDB = parTipoAmbienteService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoAmbiente parTipoAmbiente : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoAmbiente.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoComponente(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoComponente(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoComponente> listaDB = parTipoComponenteService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoComponente parTipoComponente : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoComponente.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoDepartamento(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoDepartamento(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {

            List<ParTipoDepartamento> listaDB = parTipoDepartamentoService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoDepartamento parTipoDepartamento : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoDepartamento.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;

    }

    private RespuestaSincronizacion sincronizarTipoDocumentoFiscal(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();

        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoDocumentoFiscal(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {

            List<ParTipoDocumentoFiscal> listaDB = parTipoDocumentoFiscalService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoDocumentoFiscal parTipoDocumentoFiscal : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoDocumentoFiscal.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoDocuementoSector(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoDocumentoSector(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoDocumentoSector> listaDB = parTipoDocumentoSectorService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoDocumentoSector parTipoDocumentoSector : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoDocumentoSector.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoEmision(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoEmision(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoEmision> listaDB = parTipoEmisionService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoEmision parTipoEmision : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoEmision.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoMetodoPago(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118.sincronizarParametricaTipoMetodoPago(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoMetodoPago> listaDB = parTipoMetodoPagoService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoMetodoPago parTipoMetodoPago : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoMetodoPago.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoModalidad(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoModalidad(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoModalidad> listaDB = parTipoModalidadService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoModalidad parTipoModalidad : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoModalidad.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoMoneda(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoMoneda(solicitud);
        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParTipoMoneda> listaDB = parTipoMonedaService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoMoneda parTipoMoneda : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoMoneda.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }

        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarUnidadMedida(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaUnidadMedida(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {
            List<ParUnidadMedida> listaDB = parUnidadMedidaService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParUnidadMedida parUnidadMedida : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parUnidadMedida.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarTipoPuntoVenta(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118
                .sincronizarParametricaTipoPuntoVenta(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaParametricasDto().isEmpty()) {

            List<ParTipoPuntoVenta> listaDB = parTipoPuntoVentaService.listar();
            boolean swExiste;
            for (ParametricasDto parametricasDto : respuesta.getListaParametricasDto()) {
                swExiste = true;
                for (ParTipoPuntoVenta parTipoPuntoVenta : listaDB) {
                    if (parametricasDto.getCodigoClasificador().longValue() == parTipoPuntoVenta.getCodigo()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiSincronizacion apiSincronizacion = new ApiSincronizacion();
                    apiSincronizacion.setCodigo(parametricasDto.getCodigoClasificador().longValue());
                    apiSincronizacion.setDescripcion(parametricasDto.getDescripcion());
                    apiSincronizacion.setGrupo(solicitud.getGrupo());
                    apiSincronizacion.setUsuarioAlta(solicitud.getUsuario());
                    apiSincronizacion.setFechaAlta(new Date());
                    registrar(apiSincronizacion);
                }
            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarActividad(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118.sincronizarActividades(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaActividadesDto().isEmpty()) {

            List<ApiActividad> listaDB = apiActividadService.listarPorIdEmpresa(solicitud.getIdEmpresa());
            boolean swExiste;

            for (ActividadesDto actividadesDto : respuesta.getListaActividadesDto()) {
                swExiste = true;
                long codigoCaeb = Long.parseLong(actividadesDto.getCodigoCaeb());
                for (ApiActividad actividad : listaDB) {
                    if (codigoCaeb == actividad.getCodigoActividad()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiActividad actividad = new ApiActividad();
                    actividad.setCodigoActividad(codigoCaeb);
                    actividad.setDescripcion(actividadesDto.getDescripcion());
                    actividad.setUsuarioAlta(solicitud.getUsuario());
                    actividad.setIdEmpresa(solicitud.getIdEmpresa());
                    actividad.setFechaAlta(new Date());
                    apiActividadService.registrar(actividad);
                }

            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    private RespuestaSincronizacion sincronizarProductoServicio(SolicitudCliente solicitud) {
        RespuestaSincronizacion respuestaSincronizacion = new RespuestaSincronizacion();
        respuestaSincronizacion.setGrupo(solicitud.getGrupo());
        List<ParMensajeServicio> lista = new ArrayList<>();
        Respuesta39118 respuesta = consumerWS39118.sincronizarListaProductoServicio(solicitud);

        respuestaSincronizacion.setTransaccion(respuesta.isTransaccion());
        if (respuesta.isTransaccion() && !respuesta.getListaProductosDto().isEmpty()) {
            List<ApiItem> listaDB = apiItemService.listarPorIdEmpresa(solicitud.getIdEmpresa());
            boolean swExiste;

            for (ProductosDto productosDto : respuesta.getListaProductosDto()) {
                swExiste = true;
                long codigoCaeb = productosDto.getCodigoProducto();
                for (ApiItem item : listaDB) {
                    if (codigoCaeb == item.getCodigoProductoSin()) {
                        swExiste = false;
                        break;
                    }
                }
                if (swExiste) {
                    ApiItem item = new ApiItem();
                    item.setCodigoProductoSin(codigoCaeb);
                    item.setDescripcion(productosDto.getDescripcionProducto());
                    item.setParActividad(parActividadService.leerPorCodigo(Long.valueOf(productosDto.getCodigoActividad())));
                    item.setUsuarioAlta(solicitud.getUsuario());
                    item.setIdEmpresa(solicitud.getIdEmpresa());
                    item.setFechaAlta(new Date());
                    apiItemService.registrar(item);
                }

            }
        } else {
            respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                long r = res.getCodigoMensaje();
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                lista.add(parMensajeServicio);
            });
            respuestaSincronizacion.setListaParMensajeServicio(lista);
        }
        return respuestaSincronizacion;
    }

    @Override
    public Respuesta39118 solicitudNuevoProducto(String login) {
        SolicitudCliente solicitudCliente = admConsultasService.wsSin(login, null);
        Respuesta39118 respuesta = null;
        if (solicitudCliente != null) {
            long codigo = consumerWS39118.verificarComunicacion();
            respuesta = new Respuesta39118();
            List<ParMensajeServicio> lista = new ArrayList<>();
            if (codigo == 66) {
                respuesta = consumerWS39118.recepcionSolicitudNuevoValorProducto(solicitudCliente);
                if (!respuesta.isTransaccion()) {
                    respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                        long r = res.getCodigoMensaje();
                        ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                        lista.add(parMensajeServicio);
                    });
                }
            } else {
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                lista.add(parMensajeServicio);
            }
        }
        return respuesta;
    }

    @Override
    public Respuesta39118 validacionSolicitudNuevoProducto(String login, int codigoSolicitud) {
        SolicitudCliente solicitud = admConsultasService.wsSin(login, null);
        solicitud.setCodigoSolicitud(codigoSolicitud);
        Respuesta39118 respuesta = null;
        if (solicitud != null) {
            long codigo = consumerWS39118.verificarComunicacion();
            respuesta = new Respuesta39118();
            List<ParMensajeServicio> lista = new ArrayList<>();
            if (codigo == 66) {
                respuesta = consumerWS39118.validacionSolicitudNuevoValorProducto(solicitud);
                if (respuesta.isTransaccion()) {
                    List<ApiItem> listaDB = apiItemService.listarPorIdEmpresa(solicitud.getIdEmpresa());
                    boolean swExiste = true;
                    for (ApiItem item : listaDB) {
                        if (respuesta.getCodigoProducto().longValue() == item.getCodigoProductoSin().longValue()) {
                            swExiste = false;
                            break;
                        }
                    }
                    if (swExiste) {
                        ApiItem item = new ApiItem();
                        item.setCodigoProductoSin(respuesta.getCodigoProducto());
                        item.setDescripcion(respuesta.getDescripcionProducto());
                        item.setParActividad(parActividadService.leerPorCodigo(Long.valueOf(respuesta.getCodigoActividad())));
                        item.setUsuarioAlta(login);
                        item.setIdEmpresa(solicitud.getIdEmpresa());
                        item.setFechaAlta(new Date());
                        apiItemService.registrar(item);
                    }

                } else {
                    respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                        long r = res.getCodigoMensaje();
                        ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                        lista.add(parMensajeServicio);
                    });
                }
            } else {
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                lista.add(parMensajeServicio);
            }
        }
        return respuesta;
    }

}
