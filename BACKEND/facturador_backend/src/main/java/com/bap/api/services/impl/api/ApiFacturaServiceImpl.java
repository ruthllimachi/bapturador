/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.par.ParCondicion;
import com.bap.api.repo.api.ApiFacturaRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiClienteService;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
import com.bap.api.services.api.ApiFacturaService;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.api.ApiSucursalService;
import com.bap.api.services.par.ParCondicionService;
import com.bap.api.services.par.ParTipoDocumentoFiscalService;
import com.bap.api.services.par.ParTipoDocumentoSectorService;
import com.bap.api.services.par.ParTipoEmisionService;
import com.bap.api.services.par.ParTipoMetodoPagoService;
import com.bap.api.services.par.ParTipoMonedaService;
import com.bap.api.utils.FechaUtils;
import com.bap.api.utils.NumberUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ClassPathResource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ruth
 */
@Service
public class ApiFacturaServiceImpl implements ApiFacturaService {

    @Autowired
    private ApiFacturaRepo repo;

    @Autowired
    AdmConsultasService admConsultasService;

    @Autowired
    ApiConfiguracionService apiConfiguracionService;

    @Autowired
    ApiDosificacionService apiDosificacionService;

    @Autowired
    ApiClienteService apiClienteService;

    @Autowired
    ParTipoMetodoPagoService parTipoMetodoPagoService;

    @Autowired
    ParTipoMonedaService parTipoMonedaService;

    @Autowired
    ParTipoDocumentoSectorService parTipoDocumentoSectorService;

    @Autowired
    ParTipoEmisionService parTipoEmisionService;

    @Autowired
    ParTipoDocumentoFiscalService parTipoDocumentoFiscalService;

    @Autowired
    ApiSucursalService apiSucursalService;

    @Autowired
    ApiPuntoVentaService apiPuntoVentaService;

    @Autowired
    ApiConfiguracionSucursalService apiConfiguracionSucursalService;

    @Autowired
    ApiDosificacionSucursalService apiDosificacionSucursalService;

    @Autowired
    ApiConfiguracionPuntoVentaService apiConfiguracionPuntoVentaService;

    @Autowired
    ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;
    
    @Autowired
    ParCondicionService parCondicionService;

    @Override
    public ApiFactura registrar(ApiFactura t) {
        ParCondicion parCondicion =parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
        t.setParCondicion(parCondicion);
        t.getApiFacturaDetalle().forEach(det -> {
            det.setApiFactura(t);
        });
        return repo.save(t);
    }

    @Override
    public ApiFactura modificar(ApiFactura t) {
        ApiFactura obj = leerPorId(t.getIdFactura());
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
    public ApiFactura leerPorId(Long id) {
        ApiFactura apiFactura = repo.findByIdFactura(id);
        return apiFactura;
    }

    @Override
    public List<ApiFactura> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad) {
        try {
            ConsultaParametros consultaParametros = admConsultasService.consultarDatosUsuario(login);
            if (consultaParametros.getIdPuntoVenta() == null) {
                return repo.findPorSucursal(consultaParametros.getIdSucursal(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
            } else {
                return repo.findPorPuntoVenta(consultaParametros.getIdPuntoVenta(), codigoTipoDocumentoFiscal, codigoTipoDocumentoSector, codigoTipoModalidad);
            }
        } catch (Exception e) {
            return null;
        }

    }

    @Override  
    public List<ApiFactura> listar() {
//        return repo.findAll();
        return repo.findAllFactura();
    }

    @Override
    public void eliminar(ApiFactura t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public long verificaRepiteFacturaPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaPorSucursal(idSucursal, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long verificaRepiteFacturaPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaPorPuntoVenta(idPuntoVenta, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ApiFactura getFacturaByCliente(Long idCliente) throws Exception {
        ApiFactura apiFactura = new ApiFactura();
        apiFactura = repo.findFacturaByIdCliente(idCliente);
        return apiFactura;
    }   
    
    @Override
    public ApiFactura findFacturaPorSucursal(Long numeroFactura, String numeroAutorizacionCuf, Long idSucursal, String fechaEmisionFactura){        
        ApiFactura apiFactura = repo.findFacturaPorSucursal(numeroFactura, numeroAutorizacionCuf, idSucursal, fechaEmisionFactura);     
        return apiFactura;
    }
    
    @Override
    public ApiFactura findFacturaPorPuntoVenta(Long numeroFactura, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmisionFactura){        
        ApiFactura apiFactura = repo.findFacturaPorPuntoVenta(numeroFactura, numeroAutorizacionCuf, idPuntoVenta, fechaEmisionFactura);     
        return apiFactura;
    }

    @Override
    public byte[] facturaEstandarPdf(Long idFactura) {
        ApiFactura apiFactura = this.leerPorId(idFactura);
        String jasperReportFileName = "facturaEstandar.jasper";
        return generarFacturaEstandar(apiFactura, jasperReportFileName);
    }

    private byte[] generarFacturaEstandar(ApiFactura apiFactura, String jasperReportFileName) {
        byte[] data = null;

        byte[] qrCode = getQRCode(apiFactura);
        InputStream qrInputStream = new ByteArrayInputStream(qrCode);
        String fechaEmision = FechaUtils.convertLocalDateTimeToFormatReport(apiFactura.getFechaEnvio());
        String razonSocialEmpresa = admConsultasService.consultarDatosEmpresa(apiFactura.getNitEmisor()).getRazonSocial();
        Map<String, Object> reportParam = new HashMap<String, Object>();
        reportParam.put("razonSocialEmisor", razonSocialEmpresa);
        reportParam.put("sucursal", "Sucursal No. " + apiFactura.getApiSucursal().getCodigoSucursal());
        reportParam.put("direccion", apiFactura.getApiSucursal().getDireccion());
        reportParam.put("zona", apiFactura.getApiSucursal().getZona());
        reportParam.put("telefonos", "Telefono " + apiFactura.getApiSucursal().getTelefonos());
        reportParam.put("departamento", apiFactura.getApiSucursal().getDepartamento());
        reportParam.put("nitEmisor", "NIT: " + apiFactura.getNitEmisor());
        reportParam.put("numeroDocumentoFiscal", "Documento Fiscal Nro.: " + apiFactura.getNumeroFactura());
        reportParam.put("cuf", "CUF: " + apiFactura.getCuf());
        reportParam.put("actividadEconomica", apiFactura.getApiActividad().getDescripcion());
        reportParam.put("tipoModalidad", apiFactura.getApiDosificacion().getParTipoModalidad().getDescripcion());
        reportParam.put("fechaEmision", "Fecha de Emision: " + fechaEmision);
        reportParam.put("nitCliente", "NIT/CI: " + apiFactura.getApiCliente().getNumeroDocumento());
        reportParam.put("nombreRazonSocialCliente", "Nombre/Razon Social: " + apiFactura.getApiCliente().getNombreRazonSocial());
        reportParam.put("codigoCliente", "Codigo Cliente " + apiFactura.getApiCliente().getCodigoCliente());
        reportParam.put("TOTAL", apiFactura.getMontoTotal());
        BigDecimal descuento = BigDecimal.ZERO;
        if(apiFactura.getMontoDescuento() != null){
            descuento = apiFactura.getMontoDescuento();
        }
        reportParam.put("DESCUENTO", descuento);
        BigDecimal subtotal = apiFactura.getMontoTotal().subtract(descuento).setScale(2, BigDecimal.ROUND_HALF_UP);
        reportParam.put("TOTAL_PAGAR", subtotal);
        reportParam.put("IMPORTE_BASE", subtotal);
        String LITERAL = "Son: " + NumberUtils.convertirLiteral(subtotal, "bolivianos", false);
        String LEYENDA = "Ley N° 453: " + apiFactura.getParLeyendaFactura().getDescripcion();
        reportParam.put("LITERAL", LITERAL);
        reportParam.put("LEYENDA", LEYENDA);
        reportParam.put("QR_DATA", qrInputStream);
        try {
            File file = new ClassPathResource("/reports/" + jasperReportFileName.trim()).getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), reportParam, new JRBeanCollectionDataSource(apiFactura.getApiFacturaDetalle()));
            data = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private byte[] getQRCode(ApiFactura apiFactura) {
        try {
            String var_0 = "http://dominio.impuestos.gob.bo/servicio/pagina.xhtml";
            String var_1 = apiFactura.getCuf();
            String var_2 = apiFactura.getNumeroFactura().toString();
            String var_3 = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().toString();
            String var_4 = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString();

            String qrData = var_0 + "|" + var_1 + "|" + var_2 + "|" + var_3 + "|" + var_4;

            ByteArrayOutputStream out = QRCode.from(qrData).to(ImageType.JPG).withSize(250, 250).stream();

            byte[] qrCode = out.toByteArray();
            //log.info("qrCode::::: " + qrCode);
            return qrCode;

        } catch (Exception e) {
        }
        return null;

    }
}
