/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39266;
import com.bap.api.consumer.ConsumerWS39268;
import com.bap.api.dto.Respuesta39266;
import com.bap.api.dto.Respuesta39268;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiConfiguracionPuntoVenta;
import com.bap.api.model.api.ApiConfiguracionSucursal;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiConfiguracionRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.services.par.ParEstadoService;
import com.bap.api.services.par.ParMensajeFacturadorService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.utils.FechaUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.dto.Respuesta;

/**
 *
 * @author ruth
 */
@Service
public class ApiConfiguracionServiceImpl implements ApiConfiguracionService {

    @Autowired
    private ApiConfiguracionRepo repo;

    @Autowired
    ConsumerWS39266 consumerWS39266;

    @Autowired
    ConsumerWS39268 consumerWS39268;

    @Autowired
    ParMensajeServicioService parMensajeServicioService;

    @Autowired
    ParMensajeFacturadorService parMensajeFacturadorService;

    @Autowired
    ParEstadoService parEstadoService;

    @Autowired
    AdmConsultasService admConsultasService;

    @Autowired
    ApiConfiguracionSucursalService apiConfiguracionSucursalService;

    @Autowired
    ApiConfiguracionPuntoVentaService apiConfiguracionPuntoVentaService;

    @Autowired
    ApiConfiguracionService apiConfiguracionService;

    @Override
    public ApiConfiguracion registrar(ApiConfiguracion t) {
        t.setFechaAlta(new Date());
        t.setUsuarioAlta("admin");
        return repo.save(t);
    }

    @Override
    public ApiConfiguracion modificar(ApiConfiguracion t) {
        t.setFechaModificacion(new Date());       
        return repo.save(t);
    }

    @Override
    public ApiConfiguracion leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiConfiguracion> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiConfiguracion t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public ApiConfiguracion getConfiguracionVigenteBySucursal(Long idSucursal) {
        return apiConfiguracionSucursalService.getConfiguracionSucursalVigte(idSucursal);
    }

    @Override
    public ApiConfiguracion getConfiguracionVigenteByPuntoVenta(Long idPuntoVenta) {
        return apiConfiguracionPuntoVentaService.getConfiguracionPuntoVentaVigte(idPuntoVenta);
    }

    @Override
    public Respuesta solicitudCUFD(String login) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        if (admConsultasService.cudfVigente(login)) {
            respuesta = new Respuesta();
            ParMensajeFacturador parMensajeFacturador = parMensajeFacturadorService.leerPorCodigo("61");
            respuesta.setParMensajeFacturador(parMensajeFacturador);
            respuesta.setTransaccion(false);
        } else {
            long codigo = consumerWS39268.verificarComunicacion();
            if (codigo == 66) {
                SolicitudCliente solicitudCliente = admConsultasService.wsSin(login);
                if (solicitudCliente != null) {
                    Respuesta39268 respuesta39268 = consumerWS39268.solicitudCufd(solicitudCliente);
                    if (respuesta39268.isTransaccion()) {
                        ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_VIGENTE.getCodigo());
                        ApiConfiguracion apiConfiguracion = new ApiConfiguracion();
                        apiConfiguracion.setCufd(respuesta39268.getCodigoCufd());
                        apiConfiguracion.setFechaVigencia(respuesta39268.getFechaVigencia());
                        apiConfiguracion.setUtcFechaVigencia(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(respuesta39268.getFechaVigencia()));
                        apiConfiguracion.setEstadoConfiguracion(parEstado);
                        
                        if (solicitudCliente.getCodigoPuntoVenta() == 0) {
                            ApiConfiguracionSucursal apiConfiguracionSucursal = new ApiConfiguracionSucursal();
                            apiConfiguracionSucursal.setApiSucursal(solicitudCliente.getApiSucursal());
                            apiConfiguracionSucursal.setApiConfiguracion(apiConfiguracion);
                            apiConfiguracionSucursalService.registrar(apiConfiguracionSucursal);
                        } else {
                            ApiConfiguracionPuntoVenta apiConfiguracionPuntoVenta = new ApiConfiguracionPuntoVenta();
                            apiConfiguracionPuntoVenta.setApiPuntoVenta(solicitudCliente.getApiPuntoVenta());
                            apiConfiguracionPuntoVenta.setApiConfiguracion(apiConfiguracion);
                            apiConfiguracionPuntoVentaService.registrar(apiConfiguracionPuntoVenta);
                        }
                        respuesta = new Respuesta();
                        respuesta.setTransaccion(true);
                    } else {
                        respuesta39268.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                            long r = res.getCodigoMensaje();
                            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                            lista.add(parMensajeServicio);
                        });
                        respuesta = new Respuesta();
                        respuesta.setListaParMensajeServicio(lista);
                    }
                }
            } else {
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                lista.add(parMensajeServicio);
                respuesta = new Respuesta();
                respuesta.setListaParMensajeServicio(lista);
            }
        }
        return respuesta;
    }

    @Override
    public Respuesta sincronizarFechaHora(String login) {
        SolicitudCliente solicitudCliente = admConsultasService.wsSin(login);
        Respuesta respuesta = null;
        if (solicitudCliente != null) {
            long codigo = consumerWS39266.verificarComunicacion();
            List<ParMensajeServicio> lista = new ArrayList<>();
            if (codigo == 66) {
                Respuesta39266 respuesta39266 = consumerWS39266.sincronizarFechaHora(solicitudCliente);
                if (respuesta39266.isTransaccion()) {
                    ApiConfiguracion apiConfiguracion = solicitudCliente.getApiConfiguracion();
                    apiConfiguracion.setFechaHora(respuesta39266.getFechaHora());
                    apiConfiguracion.setUtcFechaHora(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(respuesta39266.getFechaHora()));
                    apiConfiguracionService.modificar(apiConfiguracion);
                } else {
                    respuesta39266.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                        long r = res.getCodigoMensaje();
                        ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                        lista.add(parMensajeServicio);
                    });
                    respuesta = new Respuesta();
                    respuesta.setListaParMensajeServicio(lista);
                }
                respuesta = new Respuesta();
                respuesta.setTransaccion(true);
            } else {

                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                lista.add(parMensajeServicio);
                respuesta = new Respuesta();
                respuesta.setListaParMensajeServicio(lista);
            }
        }
        return respuesta;
    }

}
