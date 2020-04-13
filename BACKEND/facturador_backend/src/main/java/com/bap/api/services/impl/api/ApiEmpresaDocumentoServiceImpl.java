/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.model.api.ApiEmpresaDocumento;
import com.bap.api.repo.api.ApiEmpresaDocumentoRepo;
import com.bap.api.services.api.ApiEmpresaDocumentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ApiEmpresaDocumentoServiceImpl implements ApiEmpresaDocumentoService {

    @Autowired
    private ApiEmpresaDocumentoRepo repo;

    @Override
    public ApiEmpresaDocumento registrar(ApiEmpresaDocumento t) {
        return repo.save(t);
    }

    @Override
    public ApiEmpresaDocumento modificar(ApiEmpresaDocumento t) {
        return repo.save(t);
    }

    @Override
    public ApiEmpresaDocumento leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public ApiEmpresaDocumento leerPorTipos(Long idEmpresa, Long codigoDocumentoFiscal, Long codigoDocumentoSector, Long codigoTipoModalidad) {
        try {
            return repo.findPorEmpresaAndTipo(idEmpresa, codigoDocumentoFiscal, codigoDocumentoSector, codigoTipoModalidad).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<ApiEmpresaDocumento> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(ApiEmpresaDocumento t) {
        repo.save(t);
    }

}
