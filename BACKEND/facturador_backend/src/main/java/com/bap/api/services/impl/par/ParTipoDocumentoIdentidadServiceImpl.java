/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParTipoDocumentoIdentidad;
import com.bap.api.repo.par.ParTipoDocumentoIdentidadRepo;
import com.bap.api.services.par.ParTipoDocumentoIdentidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoDocumentoIdentidadServiceImpl implements ParTipoDocumentoIdentidadService {

    @Autowired
    private ParTipoDocumentoIdentidadRepo repo;

    @Override
    public List<ParTipoDocumentoIdentidad> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoDocumentoIdentidad leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
