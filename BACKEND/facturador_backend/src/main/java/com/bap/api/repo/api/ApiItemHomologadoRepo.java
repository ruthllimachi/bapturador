/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiItemHomologado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiItemHomologadoRepo extends JpaRepository<ApiItemHomologado, Long> {

    @Query("select u from ApiItemHomologado u  where u.apiItem.idEmpresa = :idEmpresa and u.codigoProducto = :codigoProducto")
    ApiItemHomologado findByIdEmpresaAndCodigoProducto(@Param("idEmpresa") Long idEmpresa, @Param("codigoProducto") String codigoProducto);
    
    @Query("select o from ApiItemHomologado o where o.apiItem.idItem = :idItem and o.fechaBaja IS NULL")
    List<ApiItemHomologado> listaByIdItem(@Param("idItem") Long idItem);

}
