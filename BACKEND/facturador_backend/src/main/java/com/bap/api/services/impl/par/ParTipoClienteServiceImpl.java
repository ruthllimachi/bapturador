package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParTipoCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.par.ParTipoClienteRepo;
import com.bap.api.services.par.ParTipoClienteService;


@Service
public class ParTipoClienteServiceImpl implements ParTipoClienteService {

    @Autowired
    private ParTipoClienteRepo repo;
    
    @Override
    public ParTipoCliente leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);
    }

    @Override
    public List<ParTipoCliente> listar() {
        return repo.findAll();
    }
    
}
