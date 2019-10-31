/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParEventoSignificativo;
import com.bap.api.repo.par.ParEventoSignificativoRepo;
import com.bap.api.services.par.ParEventoSignificativoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParEventoSignificativoServiceImpl implements ParEventoSignificativoService {

    @Autowired
    private ParEventoSignificativoRepo repo;

    @Override
    public ParEventoSignificativo leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }

    @Override
    public List<ParEventoSignificativo> listar() {
        return repo.findAll();
    }

}
