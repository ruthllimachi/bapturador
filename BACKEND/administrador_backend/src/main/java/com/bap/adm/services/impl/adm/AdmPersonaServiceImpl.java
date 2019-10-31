/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.impl.adm;

import com.bap.adm.model.adm.AdmPersona;
import com.bap.adm.repo.adm.AdmPersonaRepo;
import com.bap.adm.services.adm.AdmPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */

@Service
public class AdmPersonaServiceImpl implements AdmPersonaService {

    @Autowired
    private AdmPersonaRepo repo;

    @Override
    public AdmPersona registrar(AdmPersona t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmPersona modificar(AdmPersona t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmPersona leerPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdmPersona> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(AdmPersona t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
