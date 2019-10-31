package com.bap.api.repo.par;

import com.bap.api.model.par.ParMotivoAnulacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParMotivoAnulacionRepo extends JpaRepository<ParMotivoAnulacion, Long> {

    ParMotivoAnulacion findByCodigo(Long codigo);
}
