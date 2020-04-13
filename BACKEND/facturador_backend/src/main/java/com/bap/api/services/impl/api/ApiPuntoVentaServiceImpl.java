/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39117;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiPuntoVentaRepo;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.par.ParCondicionService;
import com.bap.api.services.par.ParEstadoService;
import com.bap.api.services.par.ParMensajeServicioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiPuntoVentaServiceImpl implements ApiPuntoVentaService {

    @Autowired
    private ApiPuntoVentaRepo repo;

    @Autowired
    ConsumerWS39117 consumerWS39117;

    @Autowired
    private ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParCondicionService parCondicionService;
    
    @Autowired
    private ParEstadoService parEstadoService;

    @Autowired
    private ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;
    
    @Autowired
    private ApiDosificacionService apiDosificacionService;

    @Override
    public ApiPuntoVenta registrar(ApiPuntoVenta t) {
        return repo.save(t);
    }

    @Override
    public ApiPuntoVenta modificar(ApiPuntoVenta t) {
        return repo.save(t);
    }

    @Override
    public void eliminar(ApiPuntoVenta t) {
        repo.save(t);
    }

    @Override
    public ApiPuntoVenta leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiPuntoVenta> listar() {
        return repo.findAll();
    }

    @Override
    public List<ApiPuntoVenta> listarPorIdEmpresa(Long idEmpresa) {
        return repo.listaByIdEmpresa(idEmpresa);
    }

    @Override
        public List<ApiPuntoVenta> listarPorIdSucursal(Long IdSucursal) {
        return repo.listaByIdSucursal(IdSucursal);
    }

    @Override
    public Respuesta registroPuntoVenta(SolicitudCliente t) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        long codigo = consumerWS39117.verificarComunicacion();
        if (codigo == 66) {
            Respuesta39117 respuesta39117 = consumerWS39117.registroPuntoVenta(t);
            if (respuesta39117.isTransaccion()) {
                ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
                ApiPuntoVenta apiPuntoVenta = new ApiPuntoVenta();
                apiPuntoVenta.setCodigoPuntoVenta(respuesta39117.getCodigoPuntoVenta());
                apiPuntoVenta.setNombrePuntoVenta(t.getNombrePuntoVenta());
                apiPuntoVenta.setDescripcion(t.getDescripcion());
                apiPuntoVenta.setParTipoPuntoVenta(t.getParTipoPuntoVenta());
                apiPuntoVenta.setTieneCuis(parCondicion);
                apiPuntoVenta.setApiSucursal(t.getApiSucursal());
                apiPuntoVenta.setUsuarioAlta(t.getLogin());
                apiPuntoVenta.setFechaAlta(new Date());
                registrar(apiPuntoVenta);
                respuesta = new Respuesta();
                respuesta.setTransaccion(true);
            } else {
                respuesta39117.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                    long r = res.getCodigoMensaje();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                    lista.add(parMensajeServicio);
                });
                respuesta = new Respuesta();
                respuesta.setListaParMensajeServicio(lista);
            }

        } else {
            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
            lista.add(parMensajeServicio);
            respuesta = new Respuesta();
            respuesta.setListaParMensajeServicio(lista);
        }
        return respuesta;
    }

    @Override
    public Respuesta consultaPuntoVenta(SolicitudCliente t) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        long codigo = consumerWS39117.verificarComunicacion();
        if (codigo == 66) {
            Respuesta39117 respuesta39117 = consumerWS39117.consultaPuntoVenta(t);
            if (respuesta39117.isTransaccion()) {
                List<ApiPuntoVenta> listaApiPuntoVenta = new ArrayList<>();
                respuesta39117.getListaPuntosVentas().forEach(res -> {
                    ApiPuntoVenta apiPuntoVenta = new ApiPuntoVenta();
                    apiPuntoVenta.setCodigoPuntoVenta(res.getCodigoPuntoVenta());
                    apiPuntoVenta.setNombrePuntoVenta(res.getNombrePuntoVenta());
                    apiPuntoVenta.setDescripcion(res.getTipoPuntoVenta());
                    listaApiPuntoVenta.add(apiPuntoVenta);
                });
                respuesta = new Respuesta();
                respuesta.setTransaccion(true);
                respuesta.setListaApiPuntoVenta(listaApiPuntoVenta);
            } else {
                respuesta39117.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                    long r = res.getCodigoMensaje();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                    lista.add(parMensajeServicio);
                });
                respuesta = new Respuesta();
                respuesta.setListaParMensajeServicio(lista);
            }

        } else {
            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
            lista.add(parMensajeServicio);
            respuesta = new Respuesta();
            respuesta.setListaParMensajeServicio(lista);
        }
        return respuesta;
    }

    @Override
    public Respuesta cierrePuntoVenta(SolicitudCliente t) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        long codigo = consumerWS39117.verificarComunicacion();
        if (codigo == 66) {
            Respuesta39117 respuesta39117 = consumerWS39117.cierrePuntoVenta(t);
            if (respuesta39117.isTransaccion()) {
                ApiPuntoVenta apiPuntoVenta = leerPorIdSucursalAndCodigoPuntoVenta(t.getApiSucursal().getIdSucursal(), Long.valueOf(t.getCodigoPuntoVenta()));
                if (apiPuntoVenta != null) {
                    apiPuntoVenta.setUsuarioBaja(t.getLogin());
                    apiPuntoVenta.setFechaBaja(new Date());
                    eliminar(apiPuntoVenta);

                    ApiDosificacion apiDosificacion = apiDosificacionPuntoVentaService.getDosificacionPuntoVentaVigte(apiPuntoVenta.getIdPuntoVenta());
                    if (apiDosificacion != null) {
                        ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_NO_VIGENTE.getCodigo());
                        apiDosificacion.setEstadoDosificacion(parEstado);
                        apiDosificacionService.modificar(apiDosificacion);
                    }
                }
                respuesta = new Respuesta();
                respuesta.setTransaccion(true);
            } else {
                respuesta39117.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                    long r = res.getCodigoMensaje();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                    lista.add(parMensajeServicio);
                });
                respuesta = new Respuesta();
                respuesta.setListaParMensajeServicio(lista);
            }

        } else {
            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
            lista.add(parMensajeServicio);
            respuesta = new Respuesta();
            respuesta.setListaParMensajeServicio(lista);
        }
        return respuesta;
    }

    @Override
    public ApiPuntoVenta leerPorIdSucursalAndCodigoPuntoVenta(Long idSucursal, Long codigoPuntoVenta) {
        return repo.findByIdSucursalAndCodigoPuntoVenta(idSucursal, codigoPuntoVenta);
    }
}
