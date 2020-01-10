/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiEmpresaDocumento;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.api.ApiItemHomologado;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebitoDetalle;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.model.par.ParLeyendaFactura;
import com.bap.api.model.par.ParTipoEmision;
import com.bap.api.model.par.ParTipoTransaccion;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.api.ApiNotaFiscalCreditoDebitoRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiEmpresaDocumentoService;
import com.bap.api.services.api.ApiFacturaService;
import com.bap.api.services.api.ApiItemHomologadoService;
import com.bap.api.services.api.ApiNotaFiscalCreditoDebitoService;
import com.bap.api.services.par.ParCondicionService;
import com.bap.api.services.par.ParLeyendaFacturaService;
import com.bap.api.services.par.ParTipoEmisionService;
import com.bap.api.services.par.ParTipoTransaccionService;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author ruth
 */
@Service
public class ApiNotaFiscalCreditoDebitoServiceImpl implements ApiNotaFiscalCreditoDebitoService {

    @Autowired
    private ApiNotaFiscalCreditoDebitoRepo repo;

    @Autowired
    private AdmConsultasService admConsultasService;

    @Autowired
    private ApiConfiguracionService apiConfiguracionService;

    @Autowired
    private ApiDosificacionService apiDosificacionService;

    @Autowired
    private ApiEmpresaDocumentoService apiEmpresaDocumentoService;

    @Autowired
    private ApiFacturaService apiFacturaService;

    @Autowired
    private ParLeyendaFacturaService parLeyendaFacturaService;

    @Autowired
    private ParTipoEmisionService parTipoEmisionService;

    @Autowired
    private ApiItemHomologadoService apiItemHomologadoService;

    @Autowired
    private ParTipoTransaccionService parTipoTransaccionService;

    @Autowired
    ParCondicionService parCondicionService;

    @Override
    public ApiNotaFiscalCreditoDebito registrar(ApiNotaFiscalCreditoDebito t) {
        ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
        t.setParCondicion(parCondicion);
        t.getApiNotaFiscalCreditoDebitoDetalle().forEach(det -> {
            det.setApiNotaCreditoDebito(t);
        });
        return repo.save(t);
    }

    @Override
    public ApiNotaFiscalCreditoDebito modificar(ApiNotaFiscalCreditoDebito t) {
        ApiNotaFiscalCreditoDebito obj = leerPorId(t.getIdNotaCreditoDebito());
        if (obj != null) {
            obj.setParCondicion(t.getParCondicion());
            obj.setCodigoRecepcionAnulado(t.getCodigoRecepcionAnulado());
            obj.setParMotivoAnulacion(t.getParMotivoAnulacion());
            obj.setUsuarioModificacion("admin");
            obj.setFechaModificacion(new Date());
            return repo.save(t);
        }
        return null;
    }

    @Override
    public ApiNotaFiscalCreditoDebito leerPorId(Long id) {
        ApiNotaFiscalCreditoDebito apiNotaCreditoDebito = repo.findByIdNotaCreditoDebito(id);
        return apiNotaCreditoDebito;
    }

    //a.id_nota_credito_debito, a.id_configuracion, a.id_dosificacion, a.id_empresa_documento, a.id_factura, a.numero_nota_credito_debito, 
//a.cuf,a.utc_fecha_emision,a.monto_total_original,a.monto_total_devuelto,a.monto_efectivo_credito_debito,
//a.leyenda,a.usuario,a.tipo_emision,a.codigo_recepcion,a.utc_fecha_envio 
    @Override
    public List<ApiNotaFiscalCreditoDebito> listar() {
        List<ApiNotaFiscalCreditoDebito> lista = new ArrayList<>();
        repo.findAllNotaFiscalCreditoDebito().forEach(x -> {
            ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = new ApiNotaFiscalCreditoDebito();
            apiNotaFiscalCreditoDebito.setIdNotaCreditoDebito(Long.parseLong((String.valueOf(x[0]))));
            ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(Long.parseLong((String.valueOf(x[1]))));
            apiNotaFiscalCreditoDebito.setApiConfiguracion(apiConfiguracion);
            ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(Long.parseLong((String.valueOf(x[2]))));
            apiNotaFiscalCreditoDebito.setApiDosificacion(apiDosificacion);
            ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPorId(Long.parseLong((String.valueOf(x[3]))));
            apiNotaFiscalCreditoDebito.setApiEmpresaDocumento(apiEmpresaDocumento);
            ApiFactura apiFactura = apiFacturaService.leerPorId(Long.parseLong((String.valueOf(x[4]))));
            apiNotaFiscalCreditoDebito.setApiFactura(apiFactura);
            apiNotaFiscalCreditoDebito.setNumeroNotaCreditoDebito(Long.parseLong((String.valueOf(x[5]))));
            apiNotaFiscalCreditoDebito.setCuf(String.valueOf(x[6]));
            apiNotaFiscalCreditoDebito.setUtcFechaEmision(String.valueOf(x[7]));
            apiNotaFiscalCreditoDebito.setMontoTotalOriginal(new BigDecimal(String.valueOf(x[8])));
            apiNotaFiscalCreditoDebito.setMontoTotalDevuelto(new BigDecimal(String.valueOf(x[9])));
            apiNotaFiscalCreditoDebito.setMontoEfectivoCreditoDebito(new BigDecimal(String.valueOf(x[10])));
            ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(Long.parseLong((String.valueOf(x[11]))));
            apiNotaFiscalCreditoDebito.setParLeyendaFactura(parLeyendaFactura);
            apiNotaFiscalCreditoDebito.setUsuario(String.valueOf(x[12]));
            ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.parseLong((String.valueOf(x[13]))));
            apiNotaFiscalCreditoDebito.setParTipoEmision(parTipoEmision);
            apiNotaFiscalCreditoDebito.setCodigoRecepcion(Long.parseLong((String.valueOf(x[14]))));
            apiNotaFiscalCreditoDebito.setUtcFechaEnvio(String.valueOf(x[15]));
            List<ApiNotaFiscalCreditoDebitoDetalle> listarDetalle = listarApiNotaFiscalCreditoDebitoDetalle(Long.parseLong((String.valueOf(x[0]))));
            apiNotaFiscalCreditoDebito.setApiNotaFiscalCreditoDebitoDetalle(listarDetalle);
            lista.add(apiNotaFiscalCreditoDebito);
        });
        return lista;
    }

    //id_nota_credito_debito_detalle, id_nota_credito_debito, id_item_homologado, cantidad, precio_unitario, sub_total, tipo_transaccion
    private List<ApiNotaFiscalCreditoDebitoDetalle> listarApiNotaFiscalCreditoDebitoDetalle(Long idNotaFiscalCreditoDebito) {
        List<ApiNotaFiscalCreditoDebitoDetalle> lista = new ArrayList<>();
        repo.findAllNotaFiscalCreditoDebitoDetalle(idNotaFiscalCreditoDebito).forEach(x -> {
            ApiNotaFiscalCreditoDebitoDetalle apiNotaFiscalCreditoDebitoDetalle = new ApiNotaFiscalCreditoDebitoDetalle();
            apiNotaFiscalCreditoDebitoDetalle.setIdNotaCreditoDebitoDetalle(Long.parseLong((String.valueOf(x[0]))));
            ApiItemHomologado apiItemHomologado = apiItemHomologadoService.leerPorId(Long.parseLong((String.valueOf(x[2]))));
            apiNotaFiscalCreditoDebitoDetalle.setApiItemHomologado(apiItemHomologado);
            apiNotaFiscalCreditoDebitoDetalle.setCantidad(new BigDecimal(String.valueOf(x[3])));
            apiNotaFiscalCreditoDebitoDetalle.setPrecioUnitario(new BigDecimal(String.valueOf(x[4])));
            apiNotaFiscalCreditoDebitoDetalle.setSubTotal(new BigDecimal(String.valueOf(x[5])));
            ParTipoTransaccion parTipoTransaccion = parTipoTransaccionService.leerPorCodigo(Long.parseLong((String.valueOf(x[6]))));
            apiNotaFiscalCreditoDebitoDetalle.setParTipoTransaccion(parTipoTransaccion);
            lista.add(apiNotaFiscalCreditoDebitoDetalle);
        });
        return lista;
    }

    @Override
    public void eliminar(ApiNotaFiscalCreditoDebito t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public long verificaRepiteNotaFiscalCreditoDebitoPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroNotaCreditoDebito) {
        try {
            return repo.verificaRepiteNotaFiscalCreditoDebitoPorSucursal(idSucursal, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroNotaCreditoDebito);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public long verificaRepiteNotaFiscalCreditoDebitoPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroNotaCreditoDebito) {
        try {
            return repo.verificaRepiteNotaFiscalCreditoDebitoPorPuntoVenta(idPuntoVenta, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroNotaCreditoDebito);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public List<ApiNotaFiscalCreditoDebito> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad) {
        try {
            ConsultaParametros consultaParametros = admConsultasService.consultarDatosUsuario(login);
            if (consultaParametros.getIdPuntoVenta() == null) {
                //return repo.findPorSucursal(consultaParametros.getIdSucursal(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
                List<ApiNotaFiscalCreditoDebito> lista = new ArrayList<>();
                repo.findPorSucursal(consultaParametros.getIdSucursal(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad).forEach(x -> {
                    ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = new ApiNotaFiscalCreditoDebito();
                    apiNotaFiscalCreditoDebito.setIdNotaCreditoDebito(Long.parseLong((String.valueOf(x[0]))));
                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(Long.parseLong((String.valueOf(x[1]))));
                    apiNotaFiscalCreditoDebito.setApiConfiguracion(apiConfiguracion);
                    ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(Long.parseLong((String.valueOf(x[2]))));
                    apiNotaFiscalCreditoDebito.setApiDosificacion(apiDosificacion);
                    ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPorId(Long.parseLong((String.valueOf(x[3]))));
                    apiNotaFiscalCreditoDebito.setApiEmpresaDocumento(apiEmpresaDocumento);
                    ApiFactura apiFactura = apiFacturaService.leerPorId(Long.parseLong((String.valueOf(x[4]))));
                    apiNotaFiscalCreditoDebito.setApiFactura(apiFactura);
                    apiNotaFiscalCreditoDebito.setNumeroNotaCreditoDebito(Long.parseLong((String.valueOf(x[5]))));
                    apiNotaFiscalCreditoDebito.setCuf(String.valueOf(x[6]));
                    apiNotaFiscalCreditoDebito.setUtcFechaEmision(String.valueOf(x[7]));
                    apiNotaFiscalCreditoDebito.setMontoTotalOriginal(new BigDecimal(String.valueOf(x[8])));
                    apiNotaFiscalCreditoDebito.setMontoTotalDevuelto(new BigDecimal(String.valueOf(x[9])));
                    apiNotaFiscalCreditoDebito.setMontoEfectivoCreditoDebito(new BigDecimal(String.valueOf(x[10])));
                    ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(Long.parseLong((String.valueOf(x[11]))));
                    apiNotaFiscalCreditoDebito.setParLeyendaFactura(parLeyendaFactura);
                    apiNotaFiscalCreditoDebito.setUsuario(String.valueOf(x[12]));
                    ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.parseLong((String.valueOf(x[13]))));
                    apiNotaFiscalCreditoDebito.setParTipoEmision(parTipoEmision);
                    apiNotaFiscalCreditoDebito.setCodigoRecepcion(Long.parseLong((String.valueOf(x[14]))));
                    apiNotaFiscalCreditoDebito.setUtcFechaEnvio(String.valueOf(x[15]));
                    ParCondicion parCondicion = parCondicionService.leerPorCodigo(String.valueOf(x[16]));
                    apiNotaFiscalCreditoDebito.setParCondicion(parCondicion);
                    List<ApiNotaFiscalCreditoDebitoDetalle> listarDetalle = listarApiNotaFiscalCreditoDebitoDetalle(Long.parseLong((String.valueOf(x[0]))));
                    apiNotaFiscalCreditoDebito.setApiNotaFiscalCreditoDebitoDetalle(listarDetalle);
                    lista.add(apiNotaFiscalCreditoDebito);
                });
                return lista;
            } else {
                //return repo.findPorPuntoVenta(consultaParametros.getIdPuntoVenta(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
                List<ApiNotaFiscalCreditoDebito> lista = new ArrayList<>();
                repo.findPorPuntoVenta(consultaParametros.getIdPuntoVenta(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad).forEach(x -> {
                    ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = new ApiNotaFiscalCreditoDebito();
                    apiNotaFiscalCreditoDebito.setIdNotaCreditoDebito(Long.parseLong((String.valueOf(x[0]))));
                    ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(Long.parseLong((String.valueOf(x[1]))));
                    apiNotaFiscalCreditoDebito.setApiConfiguracion(apiConfiguracion);
                    ApiDosificacion apiDosificacion = apiDosificacionService.leerPorId(Long.parseLong((String.valueOf(x[2]))));
                    apiNotaFiscalCreditoDebito.setApiDosificacion(apiDosificacion);
                    ApiEmpresaDocumento apiEmpresaDocumento = apiEmpresaDocumentoService.leerPorId(Long.parseLong((String.valueOf(x[3]))));
                    apiNotaFiscalCreditoDebito.setApiEmpresaDocumento(apiEmpresaDocumento);
                    ApiFactura apiFactura = apiFacturaService.leerPorId(Long.parseLong((String.valueOf(x[4]))));
                    apiNotaFiscalCreditoDebito.setApiFactura(apiFactura);
                    apiNotaFiscalCreditoDebito.setNumeroNotaCreditoDebito(Long.parseLong((String.valueOf(x[5]))));
                    apiNotaFiscalCreditoDebito.setCuf(String.valueOf(x[6]));
                    apiNotaFiscalCreditoDebito.setUtcFechaEmision(String.valueOf(x[7]));
                    apiNotaFiscalCreditoDebito.setMontoTotalOriginal(new BigDecimal(String.valueOf(x[8])));
                    apiNotaFiscalCreditoDebito.setMontoTotalDevuelto(new BigDecimal(String.valueOf(x[9])));
                    apiNotaFiscalCreditoDebito.setMontoEfectivoCreditoDebito(new BigDecimal(String.valueOf(x[10])));
                    ParLeyendaFactura parLeyendaFactura = parLeyendaFacturaService.leerPorCodigo(Long.parseLong((String.valueOf(x[11]))));
                    apiNotaFiscalCreditoDebito.setParLeyendaFactura(parLeyendaFactura);
                    apiNotaFiscalCreditoDebito.setUsuario(String.valueOf(x[12]));
                    ParTipoEmision parTipoEmision = parTipoEmisionService.leerPorCodigo(Long.parseLong((String.valueOf(x[13]))));
                    apiNotaFiscalCreditoDebito.setParTipoEmision(parTipoEmision);
                    apiNotaFiscalCreditoDebito.setCodigoRecepcion(Long.parseLong((String.valueOf(x[14]))));
                    apiNotaFiscalCreditoDebito.setUtcFechaEnvio(String.valueOf(x[15]));
                    ParCondicion parCondicion = parCondicionService.leerPorCodigo(String.valueOf(x[16]));
                    apiNotaFiscalCreditoDebito.setParCondicion(parCondicion);
                    List<ApiNotaFiscalCreditoDebitoDetalle> listarDetalle = listarApiNotaFiscalCreditoDebitoDetalle(Long.parseLong((String.valueOf(x[0]))));
                    apiNotaFiscalCreditoDebito.setApiNotaFiscalCreditoDebitoDetalle(listarDetalle);
                    lista.add(apiNotaFiscalCreditoDebito);
                });
                return lista;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ApiNotaFiscalCreditoDebito findNotaFiscalCreditoDebitoPorSucursal(Long numeroNotaCreditoDebito, String numeroAutorizacionCuf, Long idSucursal, String fechaEmision) {
        ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = repo.findApiNotaFiscalCreditoDebitoPorSucursal(numeroNotaCreditoDebito, numeroAutorizacionCuf, idSucursal, fechaEmision);
        return apiNotaFiscalCreditoDebito;
    }

    @Override
    public ApiNotaFiscalCreditoDebito findNotaFiscalCreditoDebitoPorPuntoVenta(Long numeroNotaCreditoDebito, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmision) {
        ApiNotaFiscalCreditoDebito apiNotaFiscalCreditoDebito = repo.findApiNotaFiscalCreditoDebitoPorPuntoVenta(numeroNotaCreditoDebito, numeroAutorizacionCuf, idPuntoVenta, fechaEmision);
        return apiNotaFiscalCreditoDebito;
    }    
    

}
