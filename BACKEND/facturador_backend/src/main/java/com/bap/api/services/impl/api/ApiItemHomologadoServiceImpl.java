/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiItemHomologado;
import com.bap.api.repo.api.ApiItemHomologadoRepo;
import com.bap.api.services.api.ApiItemHomologadoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiItemHomologadoServiceImpl implements ApiItemHomologadoService {

    @Autowired
    private ApiItemHomologadoRepo repo;

    @Override
    public ApiItemHomologado registrar(ApiItemHomologado t) {
        t.setUsuarioAlta("admin");
        t.setFechaAlta(new Date());
        return repo.save(t);
    }

    @Override
    public ApiItemHomologado modificar(ApiItemHomologado t) {
        t.setUsuarioModificacion("admin");
        t.setFechaModificacion(new Date());
        return repo.save(t);
    }

    @Override
    public ApiItemHomologado leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<ApiItemHomologado> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiItemHomologado t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public ApiItemHomologado leerPorIdEmpresaAndCodigoProducto(Long idEmpresa, String codigoProducto) {
        try {
            ApiItemHomologado obj = repo.findByIdEmpresaAndCodigoProducto(idEmpresa, codigoProducto);
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<ApiItemHomologado> getHomologadoByItem(Long idItem) throws Exception {
        try {
            List<ApiItemHomologado> lista = repo.listaByIdItem(idItem);
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
    

}
