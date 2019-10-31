/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiPuntoVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiPuntoVentaRepo extends JpaRepository<ApiPuntoVenta, Long> {

    @Query("select o from ApiPuntoVenta o where o.apiSucursal.idEmpresa = :idEmpresa")
    List<ApiPuntoVenta> listaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("select o from ApiPuntoVenta o where o.apiSucursal.idSucursal = :idSucursal")
    List<ApiPuntoVenta> listaByIdSucursal(@Param("idSucursal") Long idSucursal);    
    
    @Query("select u from ApiPuntoVenta u  where u.apiSucursal.idSucursal = :idSucursal and u.codigoPuntoVenta = :codigoPuntoVenta")
    ApiPuntoVenta findByIdSucursalAndCodigoPuntoVenta(@Param("idSucursal") Long idSucursal, @Param("codigoPuntoVenta") Long codigoPuntoVenta);
   
}
