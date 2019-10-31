/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParMensajeServicio;
import com.bap.api.repo.par.ParMensajeServicioRepo;
import com.bap.api.services.par.ParMensajeServicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParMensajeServicioServiceImpl implements ParMensajeServicioService {

    @Autowired
    private ParMensajeServicioRepo repo;
    
    @Override
    public List<ParMensajeServicio> listar() {
        return repo.findAll();
    }    

    @Override
    public ParMensajeServicio leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
   
}
