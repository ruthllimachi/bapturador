/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiFactura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiFacturaRepo extends JpaRepository<ApiFactura, Long> {

    @Query("select c from ApiFactura c join fetch c.apiFacturaDetalle where c.idFactura = :idFactura")
    ApiFactura findByIdFactura(@Param("idFactura") Long idFactura);

    @Query("select c from ApiFactura c join fetch c.apiFacturaDetalle")
    List<ApiFactura> findAllFactura();

    @Query(value = "select count(1) from api_factura as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento where id_sucursal = :idSucursal  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_factura = :numeroFactura group by a.numero_factura having count(1) > 0", nativeQuery = true)
    long verificaRepiteFacturaPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroFactura") Long numeroFactura);

    @Query(value = "select count(1) from api_factura as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento where id_punto_venta = :idPuntoVenta  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_factura = :numeroFactura group by a.numero_factura having count(1) > 0", nativeQuery = true)
    long verificaRepiteFacturaPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroFactura") Long numeroFactura);

    @Query("select distinct c from ApiFactura c join fetch c.apiFacturaDetalle where c.apiSucursal.idSucursal = :idSucursal and  c.apiEmpresaDocumento.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and c.apiEmpresaDocumento.parTipoDocumentoSector.codigo = :tipoDocumentoSector and c.apiEmpresaDocumento.parTipoModalidad.codigo = :tipoModalidad")
    List<ApiFactura> findPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    @Query("select distinct c from ApiFactura c join fetch c.apiFacturaDetalle where c.apiPuntoVenta.idPuntoVenta = :idPuntoVenta and  c.apiEmpresaDocumento.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and c.apiEmpresaDocumento.parTipoDocumentoSector.codigo = :tipoDocumentoSector and c.apiEmpresaDocumento.parTipoModalidad.codigo =:tipoModalidad")
    List<ApiFactura> findPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    @Query("select c from ApiFactura c where c.apiCliente.idCliente = :idCliente")
    ApiFactura findFacturaByIdCliente(@Param("idCliente") Long idCliente);

//    @Query("select c from ApiFactura c join fetch c.apiFacturaDetalle where c.numeroFactura = :numeroFactura and c.cuf = :numeroAutorizacionCuf and c.utcFechaEmision = :fechaEmisionFactura")
//    ApiFactura findFactura(@Param("numeroFactura") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("fechaEmisionFactura") String fechaEmisionFactura);
    
    @Query("select c from ApiFactura c join fetch c.apiFacturaDetalle where c.numeroFactura = :numeroFactura and c.cuf = :numeroAutorizacionCuf and c.apiSucursal.idSucursal = :idSucursal and c.utcFechaEmision = :fechaEmisionFactura")
    ApiFactura findFacturaPorSucursal(@Param("numeroFactura") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idSucursal") Long idSucursal, @Param("fechaEmisionFactura") String fechaEmisionFactura);
    
    @Query("select c from ApiFactura c join fetch c.apiFacturaDetalle where c.numeroFactura = :numeroFactura and c.cuf = :numeroAutorizacionCuf and c.apiPuntoVenta.idPuntoVenta = :idPuntoVenta and c.utcFechaEmision = :fechaEmisionFactura")
    ApiFactura findFacturaPorPuntoVenta(@Param("numeroFactura") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idPuntoVenta") Long idPuntoVenta, @Param("fechaEmisionFactura") String fechaEmisionFactura);
}
