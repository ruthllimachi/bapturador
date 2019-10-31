/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoDocumentoFiscal;
import com.bap.api.repo.par.ParTipoDocumentoFiscalRepo;
import com.bap.api.services.par.ParTipoDocumentoFiscalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoDocumentoFiscalServiceImpl implements ParTipoDocumentoFiscalService {

    @Autowired
    private ParTipoDocumentoFiscalRepo repo;
 
    @Override
    public List<ParTipoDocumentoFiscal> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoDocumentoFiscal leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
