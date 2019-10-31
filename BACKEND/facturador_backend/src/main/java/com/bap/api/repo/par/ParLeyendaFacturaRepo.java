package com.bap.api.repo.par;

import com.bap.api.model.par.ParLeyendaFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParLeyendaFacturaRepo extends JpaRepository<ParLeyendaFactura, Long> {

    ParLeyendaFactura findByCodigo(Long codigo);
}
