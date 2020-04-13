/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.model.api.ApiConfiguracion;
import com.bap.api.model.api.ApiDosificacion;
import com.bap.api.model.api.ApiPuntoVenta;
import com.bap.api.model.api.ApiSucursal;
import com.bap.api.model.par.ParEstado;
import com.bap.api.services.api.AdmConsultasService;
import com.bap.api.services.api.ApiConfiguracionPuntoVentaService;
import com.bap.api.services.api.ApiConfiguracionService;
import com.bap.api.services.api.ApiConfiguracionSucursalService;
import com.bap.api.services.api.ApiDosificacionPuntoVentaService;
import com.bap.api.services.api.ApiDosificacionSucursalService;
import com.bap.api.services.api.ApiPuntoVentaService;
import com.bap.api.services.api.ApiSucursalService;
import com.bap.api.services.par.ParEstadoService;
import com.bap.api.utils.FacturaUtils;
import com.bap.api.utils.FechaUtils;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ruth
 */
@Service
public class AdmConsultasServiceImpl implements AdmConsultasService {

    @Value("${service.url}")
    private String urls;

    @Autowired
    private ApiDosificacionSucursalService apiDosificacionSucursalService;

    @Autowired
    private ApiDosificacionPuntoVentaService apiDosificacionPuntoVentaService;

    @Autowired
    private ApiConfiguracionSucursalService apiConfiguracionSucursalService;

    @Autowired
    private ApiConfiguracionPuntoVentaService apiConfiguracionPuntoVentaService;

    @Autowired
    private ApiSucursalService apiSucursalService;

    @Autowired
    private ApiPuntoVentaService apiPuntoVentaService;

    @Autowired
    private ApiConfiguracionService apiConfiguracionService;

    @Autowired
    ParEstadoService parEstadoService;

    @Override
    public ConsultaParametros consultarDatosUsuario(String login) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = urls + "/admUsuario/getParametros/" + login.trim();
            ConsultaParametros consultaParametros = restTemplate.getForObject(uri, ConsultaParametros.class);
            return consultaParametros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ConsultaParametros consultarDatosEmpresa(Long nitEmpresa) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = urls + "admEmpresa/leerPorNitEmpresa/" + nitEmpresa.toString();
            ConsultaParametros consultaParametros = restTemplate.getForObject(uri, ConsultaParametros.class);
            return consultaParametros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean cudfVigente(String login) {
        ConsultaParametros consultaParametros = consultarDatosUsuario(login);
        ApiConfiguracion apiConfiguracion = null;
        if (consultaParametros.getIdPuntoVenta() == null) {
            apiConfiguracion = apiConfiguracionSucursalService.getConfiguracionSucursalVigte(consultaParametros.getIdSucursal());
        } else {
            apiConfiguracion = apiConfiguracionPuntoVentaService.getConfiguracionPuntoVentaVigte(consultaParametros.getIdPuntoVenta());
        }

        if (apiConfiguracion != null) {
            LocalDateTime fechaVigente = FechaUtils.convertStringToLocalDateTimeWithoutMillisecond(apiConfiguracion.getUtcFechaVigencia());
            boolean sw = FacturaUtils.cufdVigente(fechaVigente);
            //no es vigente 
            if (!sw){
                ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_NO_VIGENTE.getCodigo());
                apiConfiguracion.setEstadoConfiguracion(parEstado);
                apiConfiguracion.setUsuarioModificacion(login);
                apiConfiguracionService.modificar(apiConfiguracion);
            }
            
            return sw;
        }
        return false;
    }

    @Override
    public SolicitudCliente wsSin(String login) {
        ConsultaParametros consultaParametros = consultarDatosUsuario(login);
        if (consultaParametros != null) {
            ApiConfiguracion apiConfiguracion = null;
            ApiDosificacion apiDosificacion = null;
            if (consultaParametros.getIdPuntoVenta() == null) {
                apiConfiguracion = apiConfiguracionSucursalService.getConfiguracionSucursalVigte(consultaParametros.getIdSucursal());
                apiDosificacion = apiDosificacionSucursalService.getDosificacionSucursalVigte(consultaParametros.getIdSucursal());

            } else {
                apiConfiguracion = apiConfiguracionPuntoVentaService.getConfiguracionPuntoVentaVigte(consultaParametros.getIdPuntoVenta());
                apiDosificacion = apiDosificacionPuntoVentaService.getDosificacionPuntoVentaVigte(consultaParametros.getIdPuntoVenta());
            }

            if (apiDosificacion != null) {
                ApiSucursal apiSucursal = apiSucursalService.leerPorId(consultaParametros.getIdSucursal());
                Long codigoPuntoVenta = 0l;
                ApiPuntoVenta apiPuntoVenta = null;
                if (consultaParametros.getIdPuntoVenta() != null) {
                    apiPuntoVenta = apiPuntoVentaService.leerPorId(consultaParametros.getIdPuntoVenta());
                    codigoPuntoVenta = apiPuntoVenta.getCodigoPuntoVenta();
                }

                SolicitudCliente solicitudCliente = new SolicitudCliente();
                solicitudCliente.setCodigoAmbiente(consultaParametros.getCodigoAmbiente());
                solicitudCliente.setCodigoSistema(consultaParametros.getCodigoSistema());
                solicitudCliente.setCodigoSucursal(apiSucursal.getCodigoSucursal().intValue());
                solicitudCliente.setCodigoPuntoVenta(codigoPuntoVenta.intValue());//            
                solicitudCliente.setNitEmpresa(consultaParametros.getNitEmpresa());
                solicitudCliente.setIdEmpresa(consultaParametros.getIdEmpresa());
                solicitudCliente.setApiSucursal(apiSucursal);
                solicitudCliente.setApiPuntoVenta(apiPuntoVenta);
                solicitudCliente.setApiDosificacion(apiDosificacion);
                solicitudCliente.setApiConfiguracion(apiConfiguracion);
                solicitudCliente.setLogin(login);
                ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_NO_VIGENTE.getCodigo());
                solicitudCliente.setParEstado(parEstado);
                return solicitudCliente;
            }
        }
        return null;
    }

}
