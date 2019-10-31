/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParTipoDepartamento;
import com.bap.api.repo.par.ParTipoDepartamentoRepo;
import com.bap.api.services.par.ParTipoDepartamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParTipoDepartamentoServiceImpl implements ParTipoDepartamentoService {

    @Autowired
    private ParTipoDepartamentoRepo repo;

    

    @Override
    public List<ParTipoDepartamento> listar() {
        return repo.findAll();
    }

    @Override
    public ParTipoDepartamento leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }

}
