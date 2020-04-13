/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiConfiguracionPuntoVenta;
import com.bap.api.repo.api.ApiConfiguracionPuntoVentaRepo;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiConfiguracionPuntoVentaServiceImpl implements ApiConfiguracionPuntoVentaService {

    @Autowired
    private ApiConfiguracionPuntoVentaRepo repo;

    @Autowired
    private ApiConfiguracionService servicio;

    @Transactional
    @Override
    public ApiConfiguracionPuntoVenta registrar(ApiConfiguracionPuntoVenta t) {
        ApiConfiguracion apiDosificacion = servicio.registrar(t.getApiConfiguracion());
        t.setApiConfiguracion(apiDosificacion);
        return repo.save(t);
    }

    @Override
    public List<ApiConfiguracionPuntoVenta> listar() {
        return repo.findAll();
    }

    @Override
    public ApiConfiguracion getConfiguracionPuntoVentaVigte(Long idPuntoVenta) {
        List<Entidad> lista = new ArrayList<>();
        repo.findConfiguracionVigente(idPuntoVenta).forEach(x -> {
            Entidad entidad = new Entidad();
            entidad.setIdConfiguracion(Long.parseLong((String.valueOf(x[0]))));
            lista.add(entidad);
        });
        if (lista.isEmpty()) {
            return null;
        } else {
            ApiConfiguracion apiConfiguracion = servicio.leerPorId(lista.get(0).getIdConfiguracion());
            return apiConfiguracion;
        }
    }

//    @Override
//    public Entidad getConfiguracionPuntoVentaVigte(Long idPuntoVenta) {
//        List<Entidad> lista = new ArrayList<>();
//        repo.findConfiguracionVigente(idPuntoVenta).forEach(x -> {
//            Entidad entidad = new Entidad();
//            entidad.setIdConfiguracion(Long.parseLong((String.valueOf(x[0]))));
//            entidad.setCufd(String.valueOf(x[1]));
//            String fechaVigencia = String.valueOf(x[2]);
//            entidad.setFechaVigencia(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(fechaVigencia));
//            String fechaHora = String.valueOf(x[3]);                                    
//            entidad.setFechaHora(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(fechaHora));
//            lista.add(entidad);
//        });
//        if (lista.isEmpty()) {
//            return null;
//        } else {
//            return lista.get(0);
//        }
//    }
}
