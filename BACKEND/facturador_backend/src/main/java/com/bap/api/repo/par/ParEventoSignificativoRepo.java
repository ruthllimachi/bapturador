package com.bap.api.repo.par;

import com.bap.api.model.par.ParEventoSignificativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParEventoSignificativoRepo extends JpaRepository<ParEventoSignificativo, Long> {

    ParEventoSignificativo findByCodigo(Long codigo);
}
