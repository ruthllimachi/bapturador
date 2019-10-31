/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParItem;
import com.bap.api.repo.par.ParItemRepo;
import com.bap.api.services.par.ParItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ruth
 */
public class ParItemServiceImpl implements ParItemService {

    @Autowired
    private ParItemRepo repo;

    @Override
    public List<ParItem> listar() {
        return repo.findAll();
    }

    @Override
    public ParItem leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }

}
