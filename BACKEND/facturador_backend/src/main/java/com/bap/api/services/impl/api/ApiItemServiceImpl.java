/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.api.ApiItemRepo;
import com.bap.api.services.api.ApiItemService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ruth
 */
@Service
public class ApiItemServiceImpl implements ApiItemService {

    @Autowired
    private ApiItemRepo repo;

    @Override
    public ApiItem registrar(ApiItem t) {
        t.setUsuarioAlta("admin");
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiItem modificar(ApiItem t) {      
        return repo.save(t);
    }

    @Override
    public ApiItem leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiItem> listar() {
        return repo.findAll();
    }

    @Override
    public List<ApiItem> listarPorIdEmpresa(Long idEmpresa) {
        return repo.listaByIdEmpresa(idEmpresa);
    }

    @Override
    public void eliminar(ApiItem t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public List<Object[]> listarDatos(Long idEmpresa) {
        return repo.listaItemDatosByIdEmpresa(idEmpresa);
    }
    
    @Override
    public ApiItem leerPorCodigoSolicitud(Long idEmpresa, Long codigoSolicitud) {
        return repo.leerPorCodigoSolicitud(idEmpresa, codigoSolicitud);
    }

}
