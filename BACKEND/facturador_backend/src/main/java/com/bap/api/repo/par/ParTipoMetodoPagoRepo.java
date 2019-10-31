package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoMetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoMetodoPagoRepo extends JpaRepository<ParTipoMetodoPago, Long> {

    ParTipoMetodoPago findByCodigo(Long codigo);

}
