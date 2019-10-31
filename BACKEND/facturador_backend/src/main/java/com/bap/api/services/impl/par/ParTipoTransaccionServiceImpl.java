/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoTransaccion;
import com.bap.api.repo.par.ParTipoTransaccionRepo;
import com.bap.api.services.par.ParTipoTransaccionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoTransaccionServiceImpl implements ParTipoTransaccionService {

    @Autowired
    private ParTipoTransaccionRepo repo;

    @Override
    public List<ParTipoTransaccion> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoTransaccion leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
    
}
