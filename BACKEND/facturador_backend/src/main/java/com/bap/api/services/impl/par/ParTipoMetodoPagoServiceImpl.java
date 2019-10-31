/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoMetodoPago;
import com.bap.api.repo.par.ParTipoMetodoPagoRepo;
import com.bap.api.services.par.ParTipoMetodoPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoMetodoPagoServiceImpl implements ParTipoMetodoPagoService {

    @Autowired
    private ParTipoMetodoPagoRepo repo;

    @Override
    public List<ParTipoMetodoPago> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoMetodoPago leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
