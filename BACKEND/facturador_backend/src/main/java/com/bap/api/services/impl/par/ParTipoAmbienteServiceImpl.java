/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParTipoAmbiente;
import com.bap.api.repo.par.ParTipoAmbienteRepo;
import com.bap.api.services.par.ParTipoAmbienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoAmbienteServiceImpl implements ParTipoAmbienteService {

    @Autowired
    private ParTipoAmbienteRepo repo;   

    @Override
    public List<ParTipoAmbiente> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoAmbiente leerPorCodigo(Long codigo) {
       return repo.findByCodigo(codigo);
    }   
}
