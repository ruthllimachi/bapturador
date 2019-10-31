/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParPaisOrigen;
import com.bap.api.repo.par.ParPaisOrigenRepo;
import com.bap.api.services.par.ParPaisOrigenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParPaisOrigenServiceImpl implements ParPaisOrigenService {

    @Autowired
    private ParPaisOrigenRepo repo;  

    @Override
    public List<ParPaisOrigen> listar() {
        return repo.findAll();
    }

    @Override
    public ParPaisOrigen leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
