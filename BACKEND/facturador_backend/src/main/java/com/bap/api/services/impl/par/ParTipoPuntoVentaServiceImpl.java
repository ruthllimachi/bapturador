/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoPuntoVenta;
import com.bap.api.repo.par.ParTipoPuntoVentaRepo;
import com.bap.api.services.par.ParTipoPuntoVentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoPuntoVentaServiceImpl implements ParTipoPuntoVentaService {

    @Autowired
    private ParTipoPuntoVentaRepo repo;
    @Override
    public List<ParTipoPuntoVenta> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoPuntoVenta leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
