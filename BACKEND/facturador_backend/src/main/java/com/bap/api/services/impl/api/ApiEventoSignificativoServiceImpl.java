/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39127;
import com.bap.api.dto.Respuesta;
import com.bap.api.dto.Respuesta39127;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParTipoEventoSignificativo;
import com.bap.api.model.api.ApiEventoSignificativo;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.model.par.ParTipoEventoSignificativo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.api.ApiEventoSignificativoRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiEventoSignificativoService;
import com.bap.api.services.par.ParMensajeServicioService;
import com.bap.api.services.par.ParTipoEventoSignificativoService;
import com.bap.api.utils.FechaUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ruth
 */
@Service
public class ApiEventoSignificativoServiceImpl implements ApiEventoSignificativoService {

    @Autowired
    private ApiEventoSignificativoRepo repo;

    @Autowired
    private ConsumerWS39127 consumerWS39127;

    @Autowired
    private AdmConsultasService admConsultasService;

    @Autowired
    private ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParTipoEventoSignificativoService parTipoEventoSignificativoService;

    @Override
    public ApiEventoSignificativo registrar(ApiEventoSignificativo t) {
        return repo.save(t);
    }

    @Override
    public ApiEventoSignificativo modificar(ApiEventoSignificativo t) {
        return repo.save(t);
    }

    @Override
    public void eliminar(ApiEventoSignificativo t) {
        repo.save(t);
    }

    @Override
    public ApiEventoSignificativo leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiEventoSignificativo> listar() {
        return repo.findAll();
    }

    @Override
    public ApiEventoSignificativo leerPorCodigoRecepcion(Long codigoRecepcion) {
        return repo.leerPorCodigoRecepcion(codigoRecepcion);
    }

    @Override
    public Respuesta registroInicioEventoSignificativo(SolicitudCliente t) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        SolicitudCliente solicitud = admConsultasService.wsSin(t.getLogin());
        if (solicitud != null) {
            long codigo = consumerWS39127.verificarComunicacion();
            if (codigo == 66) {
                solicitud.setCodigoEvento(t.getCodigoEvento());
                solicitud.setDescripcion(t.getDescripcion());
                String utcFechaEvento = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(t.getFechaEnvio());
                solicitud.setFechaEnvio(t.getFechaEnvio());
                solicitud.setUtcFechaEnvio(utcFechaEvento);
                Respuesta39127 respuesta39127 = consumerWS39127.registroInicioEventoSignificativo(solicitud);
                if (respuesta39127.isTransaccion()) {
                    ApiEventoSignificativo apiEventoSignificativo = new ApiEventoSignificativo();
                    apiEventoSignificativo.setApiConfiguracion(solicitud.getApiConfiguracion());
                    apiEventoSignificativo.setEventoSignificativo(t.getParEventoSignificativo());
                    apiEventoSignificativo.setFechaEventoInicio(t.getFechaEnvio());
                    apiEventoSignificativo.setUtcFechaEventoInicio(utcFechaEvento);
                    apiEventoSignificativo.setDescripcion(t.getDescripcion());
                    apiEventoSignificativo.setCodigoRecepcion(respuesta39127.getCodigoRecepcionEventoSignificativo());
                    ParTipoEventoSignificativo parTipoEventoSignificativo = null;
                    if (respuesta39127.getCodigoRecepcionEventoSignificativo() == null) {
                        parTipoEventoSignificativo = parTipoEventoSignificativoService.leerPorCodigo(EnumParTipoEventoSignificativo.EVENTO_SIGNIFICATIVO_INFORMATIVO.getCodigo());
                    } else {
                        parTipoEventoSignificativo = parTipoEventoSignificativoService.leerPorCodigo(EnumParTipoEventoSignificativo.EVENTO_SIGNIFICATIVO_CONTINGENCIA.getCodigo());
                    }
                    apiEventoSignificativo.setTipoEventoSignificativo(parTipoEventoSignificativo);
                    apiEventoSignificativo.setUsuarioAlta(t.getLogin());
                    apiEventoSignificativo.setFechaAlta(new Date());
                    registrar(apiEventoSignificativo);
                    respuesta = new Respuesta();
                    respuesta.setTransaccion(true);
                } else {
                    respuesta39127.getListaCodigosRespuestas().forEach(res -> {
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
        }
        return respuesta;
    }

    @Override
    public Respuesta registroFinEventoSignificativo(SolicitudCliente t) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        SolicitudCliente solicitud = admConsultasService.wsSin(t.getLogin());
        if (solicitud != null) {
            long codigo = consumerWS39127.verificarComunicacion();
            if (codigo == 66) {
                String utcFechaEvento = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(t.getFechaEnvio());
                solicitud.setFechaEnvio(t.getFechaEnvio());
                solicitud.setUtcFechaEnvio(utcFechaEvento);
                solicitud.setCodigoRecepcion(t.getCodigoRecepcion());
                Respuesta39127 respuesta39127 = consumerWS39127.registroFinEventoSignificativo(solicitud);
                if (respuesta39127.isTransaccion()) {
                    ApiEventoSignificativo apiEventoSignificativo = leerPorCodigoRecepcion(t.getCodigoRecepcion());
                    if (apiEventoSignificativo != null) {
                        apiEventoSignificativo.setFechaEventoFin(t.getFechaEnvio());
                        apiEventoSignificativo.setUtcFechaEventoFin(utcFechaEvento);
                        apiEventoSignificativo.setUsuarioModificacion(t.getLogin());
                        apiEventoSignificativo.setFechaModificacion(new Date());
                        modificar(apiEventoSignificativo);
                    }
                    respuesta = new Respuesta();
                    respuesta.setTransaccion(true);
                } else {
                    respuesta39127.getListaCodigosRespuestas().forEach(res -> {
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
        }
        return respuesta;
    }

    @Override
    public Respuesta consultaEventoSignificativo(String login) {
        Respuesta respuesta = null;
        List<ParMensajeServicio> lista = new ArrayList<>();
        SolicitudCliente solicitud = admConsultasService.wsSin(login);
        if (solicitud != null) {
            long codigo = consumerWS39127.verificarComunicacion();
            if (codigo == 66) {
                String utcFechaEvento = FechaUtils.convertLocalDateTimeToFormatStringMillesecond(LocalDateTime.now());                
                solicitud.setUtcFechaEnvio(utcFechaEvento);
                Respuesta39127 respuesta39127 = consumerWS39127.consultaEventoSignificativo(solicitud);
                List<ApiEventoSignificativo> listaApiEventoSignificativo = new ArrayList<ApiEventoSignificativo>();
                if (respuesta39127.isTransaccion()) {                    
                    respuesta39127.getListaCodigos().forEach( res -> {
                        ApiEventoSignificativo apiEventoSignificativo = new ApiEventoSignificativo();
                        apiEventoSignificativo.setCodigoEvento(res.getCodigoEvento().longValue());
                        apiEventoSignificativo.setDescripcion(res.getDescripcion());
                        apiEventoSignificativo.setUtcFechaEventoInicio(res.getFecha().toString());
                        
                        listaApiEventoSignificativo.add(apiEventoSignificativo);
                    });
                    respuesta = new Respuesta();
                    respuesta.setListaApiEventoSignificativo(listaApiEventoSignificativo);;
                    respuesta.setTransaccion(true);
                } else {
                    respuesta39127.getListaCodigosRespuestas().forEach(res -> {
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
        }
        return respuesta;
    }
}
