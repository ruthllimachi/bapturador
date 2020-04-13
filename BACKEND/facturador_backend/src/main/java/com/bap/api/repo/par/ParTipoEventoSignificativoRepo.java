package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoEventoSignificativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoEventoSignificativoRepo extends JpaRepository<ParTipoEventoSignificativo, String> {

    ParTipoEventoSignificativo findByCodigo(String codigo);
}
