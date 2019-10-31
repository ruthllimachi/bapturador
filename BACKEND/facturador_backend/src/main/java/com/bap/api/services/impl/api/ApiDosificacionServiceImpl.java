/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39117;
import com.bap.api.dto.Entidad;
import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiDosificacionPuntoVenta;
import com.bap.api.model.api.ApiDosificacionSucursal;
import com.bap.api.model.par.ParEstado;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiDosificacionRepo;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
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
public class ApiDosificacionServiceImpl implements ApiDosificacionService {

    @Autowired
    private ApiDosificacionRepo repo;

    @Autowired
    ConsumerWS39117 consumerWS39117;

    @Autowired
    private ParMensajeServicioService parMensajeServicioService;

    @Autowired
    private ParEstadoService parEstadoService;

    @Autowired
    private ApiDosificacionSucursalService apiDosificacionSucursalService;

    @Autowired
    private ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;

    @Override
    public ApiDosificacion registrar(ApiDosificacion t) {
        t.setUsuarioAlta("admin");
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiDosificacion modificar(ApiDosificacion t) {
        t.setUsuarioModificacion("admin");
        t.setFechaModificacion(new Date());
        return repo.save(t);
    }

    @Override
    public ApiDosificacion leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiDosificacion> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiDosificacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ApiDosificacion getDosificacionVigenteBySucursal(Long idSucursal) {
        Entidad entidad = apiDosificacionSucursalService.getDosificacionSucursalVigte(idSucursal);
        return leerPorId(entidad.getIdDosificacion());
    }

    @Override
    public ApiDosificacion getDosificacionVigenteByPuntoVenta(Long idPuntoVenta) {
        Entidad entidad = apiDosificacionPuntoVentaService.getDosificacionPuntoVentaVigte(idPuntoVenta);
        return leerPorId(entidad.getIdDosificacion());
    }

    @Override
    public Respuesta39117 solicitudCuis(SolicitudCliente t) {
        long codigo = consumerWS39117.verificarComunicacion();
        Respuesta39117 respuestaSincronizacion39117 = new Respuesta39117();
        List<ParMensajeServicio> lista = new ArrayList<>();
        if (codigo == 66) {
            respuestaSincronizacion39117 = consumerWS39117.solicitudCuis(t);
            if (respuestaSincronizacion39117.isTransaccion()) {
                ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_VIGENTE.getCodigo());
                ApiDosificacion apiDosificacion = new ApiDosificacion();
                apiDosificacion.setCuis(respuestaSincronizacion39117.getCodigoCuis());
                apiDosificacion.setEstadoDosificacion(parEstado);
                apiDosificacion.setParTipoModalidad(t.getParTipoModalidad());
                if (t.getCodigoPuntoVenta() == 0) {
                    ApiDosificacionSucursal apiDosificacionSucursal = new ApiDosificacionSucursal();
                    apiDosificacionSucursal.setApiSucursal(t.getApiSucursal());
                    apiDosificacionSucursal.setApiDosificacion(apiDosificacion);
                    apiDosificacionSucursalService.registrar(apiDosificacionSucursal);
                } else {
                    ApiDosificacionPuntoVenta apiDosificacionPuntoVenta = new ApiDosificacionPuntoVenta();
                    apiDosificacionPuntoVenta.setApiPuntoVenta(t.getApiPuntoVenta());
                    apiDosificacionPuntoVenta.setApiDosificacion(apiDosificacion);
                    apiDosificacionPuntoVentaService.registrar(apiDosificacionPuntoVenta);
                }
            } else {
                respuestaSincronizacion39117.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                    long r = res.getCodigoMensaje();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                    lista.add(parMensajeServicio);
                });
            }
        } else {
            ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
            lista.add(parMensajeServicio);
        }
        respuestaSincronizacion39117.setListaParMensajeServicio(lista);
        return respuestaSincronizacion39117;
    }

}
