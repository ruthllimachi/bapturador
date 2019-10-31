/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.par;


import com.bap.api.model.par.ParMotivoAnulacion;
import com.bap.api.repo.par.ParMotivoAnulacionRepo;
import com.bap.api.services.par.ParMotivoAnulacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class ParMotivoAnulacionServiceImpl implements ParMotivoAnulacionService {

    @Autowired
    private ParMotivoAnulacionRepo repo;
   
    @Override
    public List<ParMotivoAnulacion> listar() {
        return repo.findAll();
    }

    @Override
    public ParMotivoAnulacion leerPorCodigo(Long codigo) {
        return repo.findByCodigo(codigo);
    }
    
}
