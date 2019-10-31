package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoMoneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoMonedaRepo extends JpaRepository<ParTipoMoneda, Long> {

    ParTipoMoneda findByCodigo(Long codigo);

}
