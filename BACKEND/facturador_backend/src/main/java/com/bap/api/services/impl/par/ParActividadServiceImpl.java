/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParActividad;
import com.bap.api.repo.par.ParActividadRepo;
import com.bap.api.services.par.ParActividadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParActividadServiceImpl implements ParActividadService {

    @Autowired
    private ParActividadRepo repo;

    @Override
    public ParActividad leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }

    @Override
    public List<ParActividad> listar() {
        return repo.findAll();
    }
   
}
