package com.bap.api.repo.api;

import com.bap.api.model.api.ApiItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApiItemRepo extends JpaRepository<ApiItem, Long> {

    @Query("select o from ApiItem o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL")
    List<ApiItem> listaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query(value = "select o.id_item,concat(o.codigo_producto_sin,' ',o.descripcion) as codigo from ApiItem o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL", nativeQuery = true)
    List<Object[]> listaItemDatosByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("select o from ApiItem o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL and o.codigoSolicitud = :codigoSolicitud")
    ApiItem leerPorCodigoSolicitud(@Param("idEmpresa") Long idEmpresa, @Param("codigoSolicitud") Long codigoSolicitud);

}
