package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoTransaccionRepo extends JpaRepository<ParTipoTransaccion, Long> {

    ParTipoTransaccion findByCodigo(Long codigo);

}
