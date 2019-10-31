/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiFacturaComercialExportacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiFacturaComercialExportacionRepo extends JpaRepository<ApiFacturaComercialExportacion, Long> {

    //@Query("select c from ApiFacturaComercialExportacion c join fetch c.apiApiFacturaComercialExportacionDetalle where c.idFacturaExportacion = :idFacturaExportacion")
    ApiFacturaComercialExportacion findByIdFacturaExportacion(@Param("idFacturaExportacion") Long idFacturaExportacion);

    @Query("select c from ApiFacturaComercialExportacion c join fetch c.apiFacturaComercialExportacionDetalle")
    List<ApiFacturaComercialExportacion> findAllFacturaComercialExportacion();

    @Query(value = "select count(1) from api_factura_comercial_exportacion as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento where id_sucursal = :idSucursal  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_factura = :numeroFactura group by a.numero_factura having count(1) > 0", nativeQuery = true)
    long verificaRepiteFacturaComercialExportacionPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroFactura") Long numeroFactura);

    @Query(value = "select count(1) from api_factura_comercial_exportacion as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento where id_punto_venta = :idPuntoVenta  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_factura = :numeroFactura group by a.numero_factura having count(1) > 0", nativeQuery = true)
    long verificaRepiteFacturaComercialExportacionPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroFactura") Long numeroFactura);

    @Query("select distinct c from ApiFacturaComercialExportacion c join fetch c.apiFacturaComercialExportacionDetalle where c.apiSucursal.idSucursal = :idSucursal and  c.apiEmpresaDocumento.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and c.apiEmpresaDocumento.parTipoDocumentoSector.codigo = :tipoDocumentoSector and c.apiEmpresaDocumento.parTipoModalidad.codigo = :tipoModalidad")
    List<ApiFacturaComercialExportacion> findPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    @Query("select distinct c from ApiFacturaComercialExportacion c join fetch c.apiFacturaComercialExportacionDetalle where c.apiPuntoVenta.idPuntoVenta = :idPuntoVenta and  c.apiEmpresaDocumento.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and c.apiEmpresaDocumento.parTipoDocumentoSector.codigo = :tipoDocumentoSector and c.apiEmpresaDocumento.parTipoModalidad.codigo =:tipoModalidad")
    List<ApiFacturaComercialExportacion> findPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    @Query("select c from ApiFacturaComercialExportacion c join fetch c.apiFacturaComercialExportacionDetalle where c.numeroFactura = :numeroFactura and c.cuf = :numeroAutorizacionCuf and c.apiSucursal.idSucursal = :idSucursal and c.utcFechaEmision = :fechaEmisionFactura")
    ApiFacturaComercialExportacion findApiFacturaComercialExportacionPorSucursal(@Param("numeroFactura") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idSucursal") Long idSucursal, @Param("fechaEmisionFactura") String fechaEmisionFactura);

    @Query("select c from ApiFacturaComercialExportacion c join fetch c.apiFacturaComercialExportacionDetalle where c.numeroFactura = :numeroFactura and c.cuf = :numeroAutorizacionCuf and c.apiPuntoVenta.idPuntoVenta = :idPuntoVenta and c.utcFechaEmision = :fechaEmisionFactura")
    ApiFacturaComercialExportacion findApiFacturaComercialExportacionPorPuntoVenta(@Param("numeroFactura") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idPuntoVenta") Long idPuntoVenta, @Param("fechaEmisionFactura") String fechaEmisionFactura);

}
