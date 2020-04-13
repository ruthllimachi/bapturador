/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiDosificacionPuntoVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiDosificacionPuntoVentaRepo extends JpaRepository<ApiDosificacionPuntoVenta, Long> {

//    @Query(value = "select b.cuis, b.tipo_modalidad, b.id_dosificacion from api_dosificacion_punto_venta a inner join api_dosificacion b on a.id_dosificacion = b.id_dosificacion where b.estado_dosificacion = 'VGTE' and a.id_punto_venta = :idPuntoVenta and b.tipo_modalidad = :tipoModalidad", nativeQuery = true)
//    List<Object[]> findDosificacionVigente(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoModalidad") Long tipoModalidad);
    @Query(value = "select b.cuis, b.tipo_modalidad, b.id_dosificacion from api_dosificacion_punto_venta a inner join api_dosificacion b on a.id_dosificacion = b.id_dosificacion where b.estado_dosificacion = 'VGTE' and a.id_punto_venta = :idPuntoVenta", nativeQuery = true)
    List<Object[]> findDosificacionVigente(@Param("idPuntoVenta") Long idPuntoVenta);

    @Query("select u from ApiDosificacionPuntoVenta u  where u.apiPuntoVenta.apiSucursal.idEmpresa = :idEmpresa")
    List<ApiDosificacionPuntoVenta> findDosificacionPorEmpresa(@Param("idEmpresa") Long idEmpresa);

}
