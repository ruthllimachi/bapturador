package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParTipoAmbienteRepo extends JpaRepository<ParTipoAmbiente, Long> {

    ParTipoAmbiente findByCodigo(Long codigo);
}
