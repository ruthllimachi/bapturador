/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiConfiguracionSucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiConfiguracionSucursalRepo extends JpaRepository<ApiConfiguracionSucursal, Long> {

    @Query(value = "SELECT distinct b.id_configuracion, b.cufd, CAST(b.fecha_vigencia AS char) t_fecha_vigencia, CAST(b.fecha_hora AS char) t_fecha_hora FROM api_configuracion_sucursal a inner join api_configuracion b on a.id_configuracion = b.id_configuracion where b.estado_configuracion = 'VGTE' and a.id_sucursal = :idSucursal", nativeQuery = true)
    List<Object[]> findConfiguracionVigente(@Param("idSucursal") Long idSucursal);
}
