package com.bap.api.repo.par;

import com.bap.api.model.par.ParMensajeFacturador;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParMensajeFacturadorRepo extends JpaRepository<ParMensajeFacturador, String> {
    
    ParMensajeFacturador findByCodigo(String codigo);
}
