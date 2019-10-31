/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.impl.adm;

import com.bap.adm.model.adm.AdmSistema;
import com.bap.adm.repo.adm.AdmSistemaRepo;
import com.bap.adm.services.adm.AdmSistemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class AdmSistemaServiceImpl implements AdmSistemaService{
    
    @Autowired
    private AdmSistemaRepo repo;

    @Override
    public AdmSistema registrar(AdmSistema t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmSistema modificar(AdmSistema t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmSistema leerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdmSistema> listar() {
         return repo.findAll();
    }

    @Override
    public void eliminar(AdmSistema t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
