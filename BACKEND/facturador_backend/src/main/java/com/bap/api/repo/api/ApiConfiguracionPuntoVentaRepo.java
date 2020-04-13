/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiConfiguracionPuntoVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiConfiguracionPuntoVentaRepo extends JpaRepository<ApiConfiguracionPuntoVenta, Long> {

    //@Query(value = "SELECT distinct b.id_configuracion, b.cufd, b.fecha_vigencia,  b.fecha_hora  FROM api_configuracion_punto_venta a  inner join api_configuracion b on a.id_configuracion = a.id_configuracion  where b.estado_configuracion = 'VGTE' and a.id_punto_venta = :idPuntoVenta", nativeQuery = true)
//   @Query(value = "SELECT distinct b.id_configuracion, b.cufd, CAST(b.fecha_vigencia AS char) t_fecha_vigencia, CAST(b.fecha_hora AS char) t_fecha_hora FROM api_configuracion_punto_venta a inner join api_configuracion b on a.id_configuracion = a.id_configuracion where b.estado_configuracion = 'VGTE' and a.id_punto_venta = :idPuntoVenta", nativeQuery = true)
    @Query(value = "SELECT distinct b.id_configuracion FROM api_configuracion_punto_venta a inner join api_configuracion b on a.id_configuracion = a.id_configuracion where b.estado_configuracion = 'VGTE' and a.id_punto_venta = :idPuntoVenta", nativeQuery = true)
    List<Object[]> findConfiguracionVigente(@Param("idPuntoVenta") Long idPuntoVenta);
}
