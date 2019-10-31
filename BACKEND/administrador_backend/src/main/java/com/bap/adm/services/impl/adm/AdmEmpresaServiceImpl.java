/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.services.impl.adm;

import com.bap.adm.dto.ConsultaParametros;
import com.bap.adm.model.adm.AdmEmpresa;
import com.bap.adm.repo.adm.AdmEmpresaRepo;
import com.bap.adm.services.adm.AdmEmpresaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruth
 */
@Service
public class AdmEmpresaServiceImpl implements AdmEmpresaService {

    @Autowired
    private AdmEmpresaRepo repo;

    @Override
    public AdmEmpresa registrar(AdmEmpresa t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmEmpresa modificar(AdmEmpresa t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmEmpresa leerPorId(Long id) {
        if (repo.findById(id).isPresent()) {
            return repo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<AdmEmpresa> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(AdmEmpresa t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsultaParametros leerPorNitEmpresa(Long nitEmpresa) {
        ConsultaParametros consultaParametros = null;
        AdmEmpresa obj = repo.findByNitEmpresa(nitEmpresa);
        try {
            if (obj != null) {
                consultaParametros = new ConsultaParametros();
                consultaParametros.setIdEmpresa(obj.getIdEmpresa());
                consultaParametros.setCodigoAmbiente(obj.getCodigoAmbiente());
                consultaParametros.setNitEmpresa(nitEmpresa);
                consultaParametros.setRazonSocial(obj.getRazonSocial());
                
            }
            return consultaParametros;
        } catch (Exception e) {
            return null;
        }

    }

}
