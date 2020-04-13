/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.par.ParEstadoRepo;
import com.bap.api.services.par.ParEstadoService;

/**
 *
 * @author ruth
 */
@Service
public class ParEstadoServiceImpl implements ParEstadoService {

    @Autowired
    private ParEstadoRepo repo;
    
    @Override
    public List<ParEstado> listar() {
        return repo.findAll();
    }

    @Override
    public ParEstado leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);
        
    }
    
}
