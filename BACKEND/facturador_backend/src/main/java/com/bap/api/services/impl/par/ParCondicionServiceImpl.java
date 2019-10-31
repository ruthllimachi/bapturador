/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParCondicion;
import com.bap.api.repo.par.ParCondicionRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.services.par.ParCondicionService;

/**
 *
 * @author ruth
 */
@Service
public class ParCondicionServiceImpl implements ParCondicionService {

    @Autowired
    private ParCondicionRepo repo;

    @Override
    public ParCondicion leerPorCodigo(String codigo) {
       return repo.findByCodigo(codigo);
    }

    @Override
    public List<ParCondicion> listar() {
        return repo.findAll();
    }
    
    
    
}
