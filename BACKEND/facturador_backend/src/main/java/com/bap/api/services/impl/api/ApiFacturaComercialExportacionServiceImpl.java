/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.enums.EnumParCondicion;
import com.bap.api.model.api.ApiFacturaComercialExportacion;
import com.bap.api.model.par.ParCondicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bap.api.repo.api.ApiFacturaComercialExportacionRepo;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiFacturaComercialExportacionService;
import com.bap.api.services.par.ParCondicionService;
import java.util.Date;

/**
 *
 * @author ruth
 */
@Service
public class ApiFacturaComercialExportacionServiceImpl implements ApiFacturaComercialExportacionService {

    @Autowired
    private ApiFacturaComercialExportacionRepo repo;

    @Autowired
    private AdmConsultasService admConsultasService;

    @Autowired
    ParCondicionService parCondicionService;

    @Override
    public ApiFacturaComercialExportacion registrar(ApiFacturaComercialExportacion t) {
        ParCondicion parCondicion = parCondicionService.leerPorCodigo(EnumParCondicion.CONDICION_NO.getCodigo());
        t.setParCondicion(parCondicion);
        t.getApiFacturaComercialExportacionDetalle().forEach(det -> {
            det.setApiFacturaComercialExportacion(t);
        });
        return repo.save(t);
    }

    @Override
    public ApiFacturaComercialExportacion modificar(ApiFacturaComercialExportacion t) {
        ApiFacturaComercialExportacion obj = leerPorId(t.getIdFacturaExportacion());
        if (obj != null) {
            obj.setParCondicion(t.getParCondicion());
            obj.setCodigoRecepcionAnulado(t.getCodigoRecepcionAnulado());
            obj.setUsuarioModificacion("admin");
            obj.setFechaModificacion(new Date());
            return repo.save(t);
        }
        return null;
    }

    @Override
    public ApiFacturaComercialExportacion leerPorId(Long id) {
        ApiFacturaComercialExportacion apiFacturaComercialExportacion = repo.findByIdFacturaExportacion(id);
        return apiFacturaComercialExportacion;
    }

    @Override
    public List<ApiFacturaComercialExportacion> listar() {
        return repo.findAllFacturaComercialExportacion();
    }

    @Override
    public void eliminar(ApiFacturaComercialExportacion t) {
        t.setFechaBaja(new Date());
        t.setUsuarioBaja("admin");
        repo.save(t);
    }

    @Override
    public long verificaRepiteFacturaComercialExportacionPorSucursal(Long idSucursal, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaComercialExportacionPorSucursal(idSucursal, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long verificaRepiteFacturaComercialExportacionPorPuntoVenta(Long idPuntoVenta, Long tipoDocumentoFiscal, Long tipoDocumentoSector, Long tipoModalidad, Long numeroFactura) {
        try {
            return repo.verificaRepiteFacturaComercialExportacionPorPuntoVenta(idPuntoVenta, tipoDocumentoFiscal, tipoDocumentoSector, tipoModalidad, numeroFactura);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<ApiFacturaComercialExportacion> leerPorLogin(String login, Long codigoTipoDocumentoFiscal, Long codigoTipoDocumentoSector, Long codigoTipoModalidad) {
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
    public ApiFacturaComercialExportacion findFacturaComercialExportacionPorSucursal(Long numeroFactura, String numeroAutorizacionCuf, Long idSucursal, String fechaEmisionFactura) {
        ApiFacturaComercialExportacion apiFacturaComercialExportacion = repo.findApiFacturaComercialExportacionPorSucursal(numeroFactura, numeroAutorizacionCuf, idSucursal, fechaEmisionFactura);
        return apiFacturaComercialExportacion;
    }

    @Override
    public ApiFacturaComercialExportacion findFacturaComercialExportacionPorPuntoVenta(Long numeroFactura, String numeroAutorizacionCuf, Long idPuntoVenta, String fechaEmisionFactura) {
        ApiFacturaComercialExportacion apiFacturaComercialExportacion = repo.findApiFacturaComercialExportacionPorPuntoVenta(numeroFactura, numeroAutorizacionCuf, idPuntoVenta, fechaEmisionFactura);
        return apiFacturaComercialExportacion;
    }

}
