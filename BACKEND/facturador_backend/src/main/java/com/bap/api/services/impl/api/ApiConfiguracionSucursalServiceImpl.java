/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiConfiguracionSucursal;
import com.bap.api.repo.api.ApiConfiguracionSucursalRepo;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.utils.FechaUtils;
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
public class ApiConfiguracionSucursalServiceImpl implements ApiConfiguracionSucursalService {

    @Autowired
    private ApiConfiguracionSucursalRepo repo;

    @Autowired
    private ApiConfiguracionService servicio;

    @Transactional
    @Override
    public ApiConfiguracionSucursal registrar(ApiConfiguracionSucursal t) {
        ApiConfiguracion apiConfiguracion = servicio.registrar(t.getApiConfiguracion());
        t.setApiConfiguracion(apiConfiguracion);
        return repo.save(t);
    }

    @Override
    public List<ApiConfiguracionSucursal> listar() {
        return repo.findAll();
    }
    
    @Override
    public Entidad getConfiguracionSucursalVigte(Long idSucursal) {
        List<Entidad> lista = new ArrayList<>();
        repo.findConfiguracionVigente(idSucursal).forEach(x -> {
            Entidad entidad = new Entidad();
            entidad.setIdConfiguracion(Long.parseLong((String.valueOf(x[0]))));
            entidad.setCufd(String.valueOf(x[1]));           
            String fechaVigencia = String.valueOf(x[2]);                                    
            entidad.setFechaVigencia(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(fechaVigencia));
//            String fechaHora = String.valueOf(x[3]);                                    
//            entidad.setFechaHora(FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(fechaHora));
            lista.add(entidad);
        });
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

}
