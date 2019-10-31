package com.bap.api.repo.par;

import com.bap.api.model.par.ParActividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParActividadRepo extends JpaRepository<ParActividad, Long> {

    ParActividad findByCodigo(Long codigo);
}
