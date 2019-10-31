/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiDosificacionSucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiDosificacionSucursalRepo extends JpaRepository<ApiDosificacionSucursal, Long> {

    @Query(value = "select b.cuis, b.tipo_modalidad, b.id_dosificacion from api_dosificacion_sucursal a inner join api_dosificacion b on a.id_dosificacion = b.id_dosificacion where b.estado_dosificacion = 'VGTE' and a.id_sucursal = :idSucursal", nativeQuery = true)
    List<Object[]> findDosificacionVigente(@Param("idSucursal") Long idSucursal);
   
    @Query("select u from ApiDosificacionSucursal u  where u.apiSucursal.idEmpresa = :idEmpresa")
    List<ApiDosificacionSucursal> findDosificacionPorEmpresa(@Param("idEmpresa") Long idEmpresa);
}
