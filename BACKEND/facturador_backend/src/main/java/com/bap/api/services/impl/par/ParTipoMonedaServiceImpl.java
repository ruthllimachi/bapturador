/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoMoneda;
import com.bap.api.repo.par.ParTipoMonedaRepo;
import com.bap.api.services.par.ParTipoMonedaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoMonedaServiceImpl implements ParTipoMonedaService {

    @Autowired
    private ParTipoMonedaRepo repo;

    @Override
    public List<ParTipoMoneda> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoMoneda leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
