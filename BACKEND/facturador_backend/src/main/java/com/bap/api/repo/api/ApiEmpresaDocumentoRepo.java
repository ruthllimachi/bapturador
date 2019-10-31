package com.bap.api.repo.api;

import com.bap.api.model.api.ApiEmpresaDocumento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiEmpresaDocumentoRepo extends JpaRepository<ApiEmpresaDocumento, Long> {

    @Query("select u from ApiEmpresaDocumento u  where u.idEmpresa = :idEmpresa and u.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and u.parTipoDocumentoSector.codigo = :tipoDocumentoSector and u.parTipoModalidad.codigo = :tipoModalidad")
    List<ApiEmpresaDocumento> findPorEmpresaAndTipo(@Param("idEmpresa") Long idEmpresa, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

}
