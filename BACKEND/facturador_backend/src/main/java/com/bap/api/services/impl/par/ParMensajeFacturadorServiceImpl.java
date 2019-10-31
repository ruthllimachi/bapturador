package com.bap.api.services.impl.par;

import com.bap.api.model.par.ParMensajeFacturador;
import com.bap.api.repo.par.ParMensajeFacturadorRepo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.services.par.ParMensajeFacturadorService;

@Service
public class ParMensajeFacturadorServiceImpl implements ParMensajeFacturadorService {

    @Autowired
    private ParMensajeFacturadorRepo repo;

    @Override
    public List<ParMensajeFacturador> listar() {
        return repo.findAll();
    }

    @Override
    public ParMensajeFacturador leerPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);
    }

}
