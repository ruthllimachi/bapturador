/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParTipoDocumentoSector;
import com.bap.api.repo.par.ParTipoDocumentoSectorRepo;
import com.bap.api.services.par.ParTipoDocumentoSectorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoDocumentoSectorServiceImpl implements ParTipoDocumentoSectorService {

    @Autowired
    private ParTipoDocumentoSectorRepo repo;
   
    @Override
    public List<ParTipoDocumentoSector> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoDocumentoSector leerPorCodigo(Long codigo) {
       return repo.findByCodigo(codigo);
    }
}
