/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.repo.par.ParTipoEmisionRepo;
import com.bap.api.services.par.ParTipoEmisionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoEmisionServiceImpl implements ParTipoEmisionService {

    @Autowired
    private ParTipoEmisionRepo repo;
    
    @Override
    public List<ParTipoEmision> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoEmision leerPorCodigo(Long codigo) {
      return repo.findByCodigo(codigo);
    }
}
