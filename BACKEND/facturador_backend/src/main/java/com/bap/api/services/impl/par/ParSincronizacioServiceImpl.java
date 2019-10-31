package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParSincronizacion;
import com.bap.api.repo.par.ParSincronizacionRepo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.services.par.ParSincronizacionService;

@Service
public class ParSincronizacioServiceImpl implements ParSincronizacionService {

    @Autowired
    private ParSincronizacionRepo repo;

    @Override
    public List<ParSincronizacion> listar() {
        return repo.findAll();
    }

    @Override
    public ParSincronizacion leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);
    }

}
