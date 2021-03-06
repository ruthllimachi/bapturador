/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParUnidadMedida;
import com.bap.api.repo.par.ParUnidadMedidaRepo;
import com.bap.api.services.par.ParUnidadMedidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParUnidadMedidaServiceImpl implements ParUnidadMedidaService {

    @Autowired
    private ParUnidadMedidaRepo repo;

    @Override
    public List<ParUnidadMedida> listar() {
        return repo.findAll();
    }

    @Override
    public ParUnidadMedida leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
}
