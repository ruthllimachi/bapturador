package com.bap.api.repo.api;

import com.bap.api.model.api.ApiActividad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiActividadRepo extends JpaRepository<ApiActividad, Long> {

    @Query("select o from ApiActividad o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL")
    List<ApiActividad> listaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("select o from ApiActividad o where o.idEmpresa = :idEmpresa and o.codigoActividad = :codigoActividad")
    ApiActividad findByIdEmpresaAndActividad(@Param("idEmpresa") Long idEmpresa, @Param("codigoActividad") Long codigoActividad);
}
