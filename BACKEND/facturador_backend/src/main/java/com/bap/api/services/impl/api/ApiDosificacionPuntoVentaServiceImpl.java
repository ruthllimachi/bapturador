/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.Entidad;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiDosificacionPuntoVenta;
import com.bap.api.repo.api.ApiDosificacionPuntoVentaRepo;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
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
public class ApiDosificacionPuntoVentaServiceImpl implements ApiDosificacionPuntoVentaService {

    @Autowired
    private ApiDosificacionPuntoVentaRepo repo;

    @Autowired
    private ApiDosificacionService servicio;

    @Transactional
    @Override
    public ApiDosificacionPuntoVenta registrar(ApiDosificacionPuntoVenta t) {
        ApiDosificacion apiDosificacion = servicio.registrar(t.getApiDosificacion());
        t.setApiDosificacion(apiDosificacion);
        return repo.save(t);
    }

    @Override
    public List<ApiDosificacionPuntoVenta> listar() {
        return repo.findAll();
    }

    @Override
    public ApiDosificacion getDosificacionPuntoVentaVigte(Long idPuntoVenta) {
        List<Entidad> lista = new ArrayList<>();
        repo.findDosificacionVigente(idPuntoVenta).forEach(x -> {
            Entidad entidad = new Entidad();
            entidad.setCuis(String.valueOf(x[0]));
            entidad.setCodigoModalidad((Integer.parseInt(String.valueOf(x[1]))));
            lista.add(entidad);
        });
        if (lista.isEmpty()) {
            return null;
        } else {
            ApiDosificacion apiDosificacion = servicio.leerPorId(lista.get(0).getIdDosificacion());
            return apiDosificacion;
        }
    }

    @Override
    public List<ApiDosificacionPuntoVenta> getDosificacionPuntoVentaPorIdEmpresa(Long idEmpresa) {
        return repo.findDosificacionPorEmpresa(idEmpresa);
    }

}
