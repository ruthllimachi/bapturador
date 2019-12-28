/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.repo.api;

import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ruth
 */
public interface ApiNotaFiscalCreditoDebitoRepo extends JpaRepository<ApiNotaFiscalCreditoDebito, Long> {

    ApiNotaFiscalCreditoDebito findByIdNotaCreditoDebito(@Param("idNotaCreditoDebito") Long idNotaCreditoDebito);

    @Query(value = "select count(1) from api_nota_fiscal_credito_debito as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento inner join api_factura as c on a.id_factura = c.id_factura  where c.id_sucursal = :idSucursal  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_nota_credito_debito = :numeroNotaCreditoDebito group by a.numero_nota_credito_debito having count(1) > 0", nativeQuery = true)
    long verificaRepiteNotaFiscalCreditoDebitoPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroNotaCreditoDebito") Long numeroNotaCreditoDebito);

    @Query(value = "select count(1) from api_nota_fiscal_credito_debito as a inner join api_empresa_documento as b on a.id_empresa_documento = b.id_empresa_documento inner join api_factura as c on a.id_factura = c.id_factura  where c.id_punto_venta = :idPuntoVenta  and  b.tipo_documento_fiscal = :tipoDocumentoFiscal and b.tipo_documento_sector = :tipoDocumentoSector and b.tipo_modalidad = :tipoModalidad  and a.numero_nota_credito_debito = :numeroNotaCreditoDebito group by a.numero_nota_credito_debito having count(1) > 0", nativeQuery = true)
    long verificaRepiteNotaFiscalCreditoDebitoPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad, @Param("numeroNotaCreditoDebito") Long numeroNotaCreditoDebito);

//    @Query("select distinct c from ApiNotaFiscalCreditoDebito c  where c.apiFactura.apiSucursal.idSucursal = :idSucursal and  c.apiEmpresaDocumento.parTipoDocumentoFiscal.codigo = :tipoDocumentoFiscal and c.apiEmpresaDocumento.parTipoDocumentoSector.codigo = :tipoDocumentoSector and c.apiEmpresaDocumento.parTipoModalidad.codigo = :tipoModalidad")
    //@Query(value = "SELECT a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio FROM api_nota_fiscal_credito_debito as a  inner join api_factura as b on a.id_factura = a.id_factura inner join api_empresa_documento as c on a.id_empresa_documento = c.id_empresa_documento where b.id_sucursal = :idSucursal and  c.tipo_documento_fiscal = :tipoDocumentoFiscal and c.tipo_documento_sector = :tipoDocumentoSector and c.tipo_modalidad = :tipoModalidad ;", nativeQuery = true)
    @Query(value = "SELECT distinct a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio,a.anulado FROM api_nota_fiscal_credito_debito as a  inner join api_factura as b on a.id_factura = a.id_factura inner join api_empresa_documento as c on a.id_empresa_documento = c.id_empresa_documento where b.id_sucursal = :idSucursal and  c.tipo_documento_fiscal = :tipoDocumentoFiscal and c.tipo_documento_sector = :tipoDocumentoSector and c.tipo_modalidad = :tipoModalidad ;", nativeQuery = true)
    List<Object[]> findPorSucursal(@Param("idSucursal") Long idSucursal, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    //@Query(value = "SELECT a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio FROM api_nota_fiscal_credito_debito as a  inner join api_factura as b on a.id_factura = a.id_factura inner join api_empresa_documento as c on a.id_empresa_documento = c.id_empresa_documento where b.id_punto_venta = :idPuntoVenta and  c.tipo_documento_fiscal = :tipoDocumentoFiscal and c.tipo_documento_sector = :tipoDocumentoSector and c.tipo_modalidad = :tipoModalidad ;", nativeQuery = true)
    @Query(value = "SELECT distinct a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio,a.anulado FROM api_nota_fiscal_credito_debito as a  inner join api_factura as b on a.id_factura = a.id_factura inner join api_empresa_documento as c on a.id_empresa_documento = c.id_empresa_documento where b.id_punto_venta = :idPuntoVenta and  c.tipo_documento_fiscal = :tipoDocumentoFiscal and c.tipo_documento_sector = :tipoDocumentoSector and c.tipo_modalidad = :tipoModalidad ;", nativeQuery = true)
    List<Object[]> findPorPuntoVenta(@Param("idPuntoVenta") Long idPuntoVenta, @Param("tipoDocumentoFiscal") Long tipoDocumentoFiscal, @Param("tipoDocumentoSector") Long tipoDocumentoSector, @Param("tipoModalidad") Long tipoModalidad);

    @Query(value = "SELECT a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio FROM api_nota_fiscal_credito_debito as a;", nativeQuery = true)
    List<Object[]> findAllNotaFiscalCreditoDebito();

    @Query(value = "SELECT id_nota_credito_debito_detalle, id_nota_credito_debito, id_item_homologado, cantidad, precio_unitario, sub_total, tipo_transaccion  FROM api_nota_fiscal_credito_debito_detalle  where id_nota_credito_debito = :idNotaCreditoDebito ;", nativeQuery = true)
    List<Object[]> findAllNotaFiscalCreditoDebitoDetalle(@Param("idNotaCreditoDebito") Long idNotaCreditoDebito);

    @Query("select c from ApiNotaFiscalCreditoDebito c join fetch c.apiNotaFiscalCreditoDebitoDetalle where c.numeroNotaCreditoDebito = :numeroNotaCreditoDebito and c.cuf = :numeroAutorizacionCuf and c.apiFactura.apiSucursal.idSucursal = :idSucursal and c.utcFechaEmision = :fechaEmision")
    ApiNotaFiscalCreditoDebito findApiNotaFiscalCreditoDebitoPorSucursal(@Param("numeroNotaCreditoDebito") Long numeroFactura, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idSucursal") Long idSucursal, @Param("fechaEmision") String fechaEmision);

    @Query("select c from ApiNotaFiscalCreditoDebito c join fetch c.apiNotaFiscalCreditoDebitoDetalle where c.numeroNotaCreditoDebito = :numeroNotaCreditoDebito and c.cuf = :numeroAutorizacionCuf and c.apiFactura.apiPuntoVenta.idPuntoVenta = :idPuntoVenta and c.utcFechaEmision = :fechaEmision")
    ApiNotaFiscalCreditoDebito findApiNotaFiscalCreditoDebitoPorPuntoVenta(@Param("numeroNotaCreditoDebito") Long numeroNotaCreditoDebito, @Param("numeroAutorizacionCuf") String numeroAutorizacionCuf, @Param("idPuntoVenta") Long idPuntoVenta, @Param("fechaEmision") String fechaEmision);

}
