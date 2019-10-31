/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiSucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiSucursalRepo extends JpaRepository<ApiSucursal, Long> {

    @Query("select o from ApiSucursal o where o.idEmpresa = :idEmpresa")
    List<ApiSucursal> listaByIdEmpresa(@Param("idEmpresa") Long idEmpresa);

    @Query("select o from ApiSucursal o where o.idEmpresa = :idEmpresa and o.tieneCuis.codigo = 'NO'")
    List<ApiSucursal> listaBySucursalNoTieneCuis(@Param("idEmpresa") Long idEmpresa);

    @Query(value = "select distinct a.id_sucursal from api_punto_venta a inner join api_sucursal b on a.id_sucursal = b.id_sucursal where a.tiene_cuis = 'NO' and b.id_empresa = :idEmpresa ", nativeQuery = true)
    List<Object[]> listaByPuntoVentaNoTieneCuis(@Param("idEmpresa") Long idEmpresa);

    @Query(value = "select c.id_sucursal, b.cuis, b.tipo_modalidad  from api_dosificacion_sucursal a  inner join api_dosificacion b on a.id_dosificacion = b.id_dosificacion inner join api_sucursal c on a.id_sucursal = c.id_sucursal where b.estado_dosificacion = 'VGTE' and c.tiene_cuis = 'SI' and c.id_empresa = :idEmpresa", nativeQuery = true)
    List<Object[]> listaByEmpresaTieneCuisVigente(@Param("idEmpresa") Long idEmpresa);

    @Query("select u from ApiSucursal u  where u.idEmpresa = :idEmpresa and u.codigoSucursal = :codigoSucursal")
    ApiSucursal findByIdEmpresaAndCodigoSucursal(@Param("idEmpresa") Long idEmpresa, @Param("codigoSucursal") Long codigoSucursal);

}
