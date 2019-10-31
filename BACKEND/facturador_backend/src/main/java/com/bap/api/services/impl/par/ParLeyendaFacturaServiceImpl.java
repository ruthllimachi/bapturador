/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.repo.par.ParLeyendaFacturaRepo;
import com.bap.api.services.par.ParLeyendaFacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParLeyendaFacturaServiceImpl implements ParLeyendaFacturaService {

    @Autowired
    private ParLeyendaFacturaRepo repo;
 

    @Override
    public List<ParLeyendaFactura> listar() {
        return repo.findAll();
    }

    @Override
    public ParLeyendaFactura leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
