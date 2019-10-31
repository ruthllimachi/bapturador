/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.consumer.ConsumerWS39117;
import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.api.ApiPuntoVentaRepo;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.par.ParCondicionService;
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

    @Override
    public ApiPuntoVenta registrar(ApiPuntoVenta t) {
        ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
        t.setTieneCuis(parCondicion);
        t.setFechaAlta(new Date());
        t.setUsuarioAlta("admin");
        return repo.save(t);
    }

    @Override
    public ApiPuntoVenta modificar(ApiPuntoVenta t) {
        ApiPuntoVenta obj = leerPorId(t.getIdPuntoVenta());
        obj.setDescripcion(t.getDescripcion());
        obj.setFechaModificacion(new Date());
        obj.setUsuarioModificacion("admin");
        return repo.save(obj);
    }

    @Override
    public void eliminar(ApiPuntoVenta t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
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
    public Respuesta39117 registroPuntoVenta(SolicitudCliente t) {
        long codigo = consumerWS39117.verificarComunicacion();
        Respuesta39117 respuestaSincronizacion39117 = new Respuesta39117();
        List<ParMensajeServicio> lista = new ArrayList<>();
        if (codigo == 66) {
            respuestaSincronizacion39117 = consumerWS39117.registroPuntoVenta(t);
            if (respuestaSincronizacion39117.isTransaccion()) {
                ApiPuntoVenta apiPuntoVenta = new ApiPuntoVenta();
                apiPuntoVenta.setApiSucursal(t.getApiSucursal());
                apiPuntoVenta.setCodigoPuntoVenta(respuestaSincronizacion39117.getCodigoPuntoVenta());
                apiPuntoVenta.setNombrePuntoVenta(t.getNombrePuntoVenta());
                apiPuntoVenta.setDescripcion(t.getDescripcion());
                apiPuntoVenta.setParTipoPuntoVenta(t.getParTipoPuntoVenta());
                registrar(apiPuntoVenta);
            } else {
                respuestaSincronizacion39117.getListaRespuestaCodigosMensajesSoapDto().forEach(res -> {
                    long r = res.getCodigoMensaje();
                    ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(r);
                    lista.add(parMensajeServicio);
                });
                respuestaSincronizacion39117.setListaParMensajeServicio(lista);
            }
        } else {
            if (codigo != -1) {
                ParMensajeServicio parMensajeServicio = parMensajeServicioService.leerPorCodigo(codigo);
                lista.add(parMensajeServicio);
                respuestaSincronizacion39117.setListaParMensajeServicio(lista);
            }
        }
        return respuestaSincronizacion39117;
    }

    @Override
    public ApiPuntoVenta leerPorIdSucursalAndCodigoPuntoVenta(Long idSucursal, Long codigoPuntoVenta) {
        return repo.findByIdSucursalAndCodigoPuntoVenta(idSucursal, codigoPuntoVenta);
    }
}
