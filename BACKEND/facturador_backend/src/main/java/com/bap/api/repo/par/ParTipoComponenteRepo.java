package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoComponente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParTipoComponenteRepo extends JpaRepository<ParTipoComponente, Long> {

    ParTipoComponente findByCodigo(Long codigo);
}
