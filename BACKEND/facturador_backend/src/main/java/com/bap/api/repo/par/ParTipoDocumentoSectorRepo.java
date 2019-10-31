package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoDocumentoSector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoDocumentoSectorRepo extends JpaRepository<ParTipoDocumentoSector, Long> {

    ParTipoDocumentoSector findByCodigo(Long codigo);
}
