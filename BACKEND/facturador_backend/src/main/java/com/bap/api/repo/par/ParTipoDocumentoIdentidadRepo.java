package com.bap.api.repo.par;

import com.bap.api.model.par.ParTipoDocumentoIdentidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParTipoDocumentoIdentidadRepo extends JpaRepository<ParTipoDocumentoIdentidad, Long> {

    ParTipoDocumentoIdentidad findByCodigo(Long codigo);
}
