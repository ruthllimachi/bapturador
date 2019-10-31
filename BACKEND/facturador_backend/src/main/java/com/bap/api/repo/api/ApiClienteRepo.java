/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiCliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiClienteRepo extends JpaRepository<ApiCliente, Long> {

    @Query("select u from ApiCliente u  where u.idEmpresa = :idEmpresa and u.codigoCliente = :codigoCliente")
    ApiCliente findByIdEmpresaAndCodigoCliente(@Param("idEmpresa") Long idEmpresa, @Param("codigoCliente") String codigoCliente);

    @Query("select o from ApiCliente o where o.idEmpresa = :idEmpresa and o.fechaBaja IS NULL")
    List<ApiCliente> listarClienteByIdEmpresa(@Param("idEmpresa") Long idEmpresa);
    
//    @Query("select o from ApiCliente o where o.nitEmisor = :nitEmisor and o.fechaBaja IS NULL")
//    ApiCliente getClienteByNit (@Param("nitEmisor") Long nitEmisor);
    
    @Query("select o from ApiCliente o where o.numeroDocumento = :numeroDocumento and o.fechaBaja IS NULL")
    ApiCliente getClienteByNroDocumento (@Param("numeroDocumento") String numeroDocumento);

}
