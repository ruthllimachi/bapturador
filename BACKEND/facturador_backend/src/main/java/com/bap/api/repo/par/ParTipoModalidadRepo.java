package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoModalidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoModalidadRepo extends JpaRepository<ParTipoModalidad, Long> {

    ParTipoModalidad findByCodigo(Long codigo);
}
