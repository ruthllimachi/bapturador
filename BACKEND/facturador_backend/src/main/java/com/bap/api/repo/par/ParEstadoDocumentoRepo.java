package com.bap.api.repo.par;

import com.bap.api.model.par.ParEstadoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParEstadoDocumentoRepo extends JpaRepository<ParEstadoDocumento, String> {

    ParEstadoDocumento findByCodigo(String codigo);
}
