/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39266;
import com.bap.api.consumer.ConsumerWS39268;
import com.bap.api.dto.Entidad;
import com.bap.api.dto.Respuesta39266;
import com.bap.api.dto.Respuesta39268;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.enums.EnumWS;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiConfiguracionPuntoVenta;
import com.bap.api.model.api.ApiConfiguracionSucursal;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiConfiguracionRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.services.par.ParEstadoService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.utils.FechaUtils;
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
        t.setFechaAlta(new Date());
        t.setUsuarioAlta("admin");
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
        Entidad entidad = apiConfiguracionSucursalService.getConfiguracionSucursalVigte(idSucursal);
        return leerPorId(entidad.getIdConfiguracion());
    }
    
    @Override
    public ApiConfiguracion getConfiguracionVigenteByPuntoVenta(Long idPuntoVenta) {
        Entidad entidad = apiConfiguracionPuntoVentaService.getConfiguracionPuntoVentaVigte(idPuntoVenta);
        return leerPorId(entidad.getIdConfiguracion());
    }
    
    @Override
    public Respuesta39268 solicitudCUFD(String login) {
        SolicitudCliente solicitudCliente = admConsultasService.wsSin(login, EnumWS.SOLICITUD_CUFD.getCodigo());
        Respuesta39268 respuesta = null;
        if (solicitudCliente != null) {
            long codigo = consumerWS39268.verificarComunicacion();
            respuesta = new Respuesta39268();
            List<ParMensajeServicio> lista = new ArrayList<>();
            if (codigo == 66) {
                respuesta = consumerWS39268.solicitudCufd(solicitudCliente);
                if (respuesta.isTransaccion()) {
                    ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_VIGENTE.getCodigo());
                    ApiConfiguracion apiConfiguracion = new ApiConfiguracion();
                    apiConfiguracion.setCufd(respuesta.getCodigoCufd());
                    apiConfiguracion.setFechaVigencia(respuesta.getFechaVigencia());
                    apiConfiguracion.setUtcFechaVigencia(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(respuesta.getFechaVigencia()));
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
                } else {
                    respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                        long r = res.getCodigoMensaje();
                        ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                        lista.add(parMensajeServicio);
                    });
                }
            } else {
                if (codigo != -1) {
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                    lista.add(parMensajeServicio);
                }
            }
            respuesta.setListaParMensajeServicio(lista);
        } else {
            respuesta = new Respuesta39268();
            respuesta.setCodigoCufd("CUFD AUN VIGENTE");
            respuesta.setTransaccion(true);
        }
        return respuesta;
    }
    
    @Override
    public Respuesta39266 sincronizarFechaHora(String login) {
        SolicitudCliente solicitudCliente = admConsultasService.wsSin(login, null);
        Respuesta39266 respuesta = null;
        if (solicitudCliente != null) {
            long codigo = consumerWS39266.verificarComunicacion();
            respuesta = new Respuesta39266();
            List<ParMensajeServicio> lista = new ArrayList<>();
            if (codigo == 66) {
                respuesta = consumerWS39266.sincronizarFechaHora(solicitudCliente);
                if (respuesta.isTransaccion()) {                    
                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(solicitudCliente.getIdConfiguracion());
                    apiConfiguracion.setFechaHora(respuesta.getFechaHora());
                    apiConfiguracion.setUtcFechaHora(FechaUtils.convertLocalDateTimeToFormatStringMillesecond(respuesta.getFechaHora()));
                    apiConfiguracionService.modificar(apiConfiguracion);
                } else {
                    respuesta.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                        long r = res.getCodigoMensaje();
                        ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                        lista.add(parMensajeServicio);
                    });
                }
            } else {
                if (codigo != -1) {
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                    lista.add(parMensajeServicio);
                }
            }
            respuesta.setListaParMensajeServicio(lista);
        }
        return respuesta;
    }
    
}
