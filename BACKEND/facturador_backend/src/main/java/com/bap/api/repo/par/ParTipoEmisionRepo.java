package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoEmision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoEmisionRepo extends JpaRepository<ParTipoEmision, Long> {

    ParTipoEmision findByCodigo(Long codigo);
}
