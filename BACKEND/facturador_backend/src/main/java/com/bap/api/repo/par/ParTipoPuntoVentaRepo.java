package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoPuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoPuntoVentaRepo extends JpaRepository<ParTipoPuntoVenta, Long> {

    ParTipoPuntoVenta findByCodigo(Long codigo);

}
