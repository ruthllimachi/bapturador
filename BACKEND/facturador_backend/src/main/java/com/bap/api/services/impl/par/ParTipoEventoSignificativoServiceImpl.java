/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoEventoSignificativo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.services.par.ParTipoEventoSignificativoService;
import com.bap.api.repo.par.ParTipoEventoSignificativoRepo;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoEventoSignificativoServiceImpl implements ParTipoEventoSignificativoService {

    @Autowired
    private ParTipoEventoSignificativoRepo repo;

    @Override
    public List<ParTipoEventoSignificativo> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoEventoSignificativo leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);

    }

}
