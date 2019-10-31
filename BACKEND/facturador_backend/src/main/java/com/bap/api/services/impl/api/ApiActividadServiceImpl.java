/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiActividad;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.api.ApiActividadRepo;
import com.bap.api.services.api.ApiActividadService;

/**
 *
 * @author ruth
 */
@Service
public class ApiActividadServiceImpl implements ApiActividadService {

    @Autowired
    private ApiActividadRepo repo;

    @Override
    public ApiActividad registrar(ApiActividad t) {
        t.setUsuarioAlta("admin");
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiActividad modificar(ApiActividad t) {
        t.setUsuarioModificacion("admin");
        t.setFechaModificacion(new Date());
        return repo.save(t);
    }

    @Override
    public ApiActividad leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public ApiActividad leerPorIdEmpresaAndCodigoActividad(Long id, Long codigoActividad) {
        return repo.findByIdEmpresaAndActividad(id, codigoActividad);
    }

    @Override
    public List<ApiActividad> listar() {
        return repo.findAll();
    }

    @Override
    public List<ApiActividad> listarPorIdEmpresa(Long idEmpresa) {
        return repo.listaByIdEmpresa(idEmpresa);
    }

    @Override
    public void eliminar(ApiActividad t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

}
