/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open  the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiDosificacionSucursal;
import com.bap.api.repo.api.ApiDosificacionSucursalRepo;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
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
public class ApiDosificacionSucursalServiceImpl implements ApiDosificacionSucursalService {

    @Autowired
    private ApiDosificacionSucursalRepo repo;

    @Autowired
    private ApiDosificacionService servicio;

    @Transactional
    @Override
    public ApiDosificacionSucursal registrar(ApiDosificacionSucursal t) {
        ApiDosificacion apiDosificacion = servicio.registrar(t.getApiDosificacion());
        t.setApiDosificacion(apiDosificacion);
        return repo.save(t);
    }

    @Override
    public List<ApiDosificacionSucursal> listar() {
        return repo.findAll();
    }

    @Override
    public ApiDosificacion getDosificacionSucursalVigte(Long idSucursal) {
        List<Entidad> lista = new ArrayList<>();
        repo.findDosificacionVigente(idSucursal).forEach(x -> {
            Entidad entidad = new Entidad();
            entidad.setIdDosificacion((Long.parseLong(String.valueOf(x[0]))));
            lista.add(entidad);
        });
        if (lista.isEmpty()) {
            return null;
        } else {
            Long idDosificacion = lista.get(0).getIdDosificacion();
            ApiDosificacion apiDosificacion = servicio.leerPorId(idDosificacion);
            return apiDosificacion;
        }

    }

    @Override
    public List<ApiDosificacionSucursal> getDosificacionSucursalPorIdEmpresa(Long idEmpresa) {
        return repo.findDosificacionPorEmpresa(idEmpresa);

    }
}
