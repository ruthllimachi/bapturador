package com.bap.api.repo.api;

import com.bap.api.model.api.ApiEventoSignificativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiEventoSignificativoRepo extends JpaRepository<ApiEventoSignificativo, Long> {

//    @Query("select o from ApiEventoSignificativo o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL and o.codigoRecepcion = :codigoRecepcion")
//    ApiEventoSignificativo leerPorCodigoRecepcion(@Param("idEmpresa") Long idEmpresa, @Param("codigoRecepcion") Long codigoRecepcion);


//    @Query("select o from ApiEventoSignificativo o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL and o.codigoRecepcion = :codigoRecepcion")
//    ApiEventoSignificativo leerPorCodigoRecepcion(@Param("idEmpresa") Long idEmpresa, @Param("codigoRecepcion") Long codigoRecepcion);

    @Query("select o from ApiEventoSignificativo o where o.fechaBaja IS NULL and o.codigoRecepcion = :codigoRecepcion")
    ApiEventoSignificativo leerPorCodigoRecepcion(@Param("codigoRecepcion") Long codigoRecepcion);
}
