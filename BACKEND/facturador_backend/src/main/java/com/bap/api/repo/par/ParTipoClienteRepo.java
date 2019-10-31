package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoClienteRepo extends JpaRepository<ParTipoCliente, String> {

    
    ParTipoCliente findByCodigo(String codigo);
}
