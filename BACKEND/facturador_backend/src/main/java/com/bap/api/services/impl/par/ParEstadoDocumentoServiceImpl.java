/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParEstadoDocumento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.par.ParEstadoDocumentoRepo;
import com.bap.api.services.par.ParEstadoDocumentoService;

/**
 *
 * @author ruth
 */
@Service
public class ParEstadoDocumentoServiceImpl implements ParEstadoDocumentoService {

    @Autowired
    private ParEstadoDocumentoRepo repo;

    @Override
    public List<ParEstadoDocumento> listar() {
        return repo.findAll();
    }

    @Override
    public ParEstadoDocumento leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);

    }

}
