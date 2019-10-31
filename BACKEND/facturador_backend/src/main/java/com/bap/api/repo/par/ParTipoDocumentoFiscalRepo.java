package com.bap.api.repo.par;
import com.bap.api.model.par.ParTipoDocumentoFiscal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParTipoDocumentoFiscalRepo extends JpaRepository<ParTipoDocumentoFiscal, Long> {

    ParTipoDocumentoFiscal findByCodigo(Long codigo);
}
