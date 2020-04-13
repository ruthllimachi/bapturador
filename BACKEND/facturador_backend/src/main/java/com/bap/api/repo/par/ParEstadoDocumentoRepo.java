package com.bap.api.repo.par;

import com.bap.api.model.par.ParEstado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParEstadoRepo extends JpaRepository<ParEstado, String> {

    ParEstado findByCodigo(String codigo);
}
