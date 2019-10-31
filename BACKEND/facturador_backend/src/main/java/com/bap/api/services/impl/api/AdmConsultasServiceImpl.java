/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.services.impl.api;

import com.bap.api.dto.ConsultaParametros;
import com.bap.api.dto.Entidad;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.enums.EnumParEstado;
import com.bap.api.enums.EnumWS;
import com.bap.api.model.api.ApiConfiguracion;
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
    private ApiConfiguracionService apiConfiguracionService;

    @Autowired
    private ApiSucursalService apiSucursalService;

    @Autowired
    private ApiPuntoVentaService apiPuntoVentaService;

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
    public SolicitudCliente wsSin(String login, String ws) {
        //Obtenemos Datos de Sistema y Empresa de administrador_backend                
        ConsultaParametros consultaParametros = consultarDatosUsuario(login);
        Entidad entidadConfiguracion = null;
        Entidad entidadDosificacion = null;
        //traemos configuracion vigente sucursal o punto de venta del USUARIO OPERADOR
        if (consultaParametros.getIdPuntoVenta() == null) {
            entidadConfiguracion = apiConfiguracionSucursalService.getConfiguracionSucursalVigte(consultaParametros.getIdSucursal());
            entidadDosificacion = apiDosificacionSucursalService.getDosificacionSucursalVigte(consultaParametros.getIdSucursal());

        } else {
            entidadConfiguracion = apiConfiguracionPuntoVentaService.getConfiguracionPuntoVentaVigte(consultaParametros.getIdPuntoVenta());
            entidadDosificacion = apiDosificacionPuntoVentaService.getDosificacionPuntoVentaVigte(consultaParametros.getIdPuntoVenta());
        }
        if (ws != null) {
            if (entidadConfiguracion != null) {
                if (ws.compareTo(EnumWS.SOLICITUD_CUFD.getCodigo()) == 0) {
                    boolean sw = FacturaUtils.cufdVigente(entidadConfiguracion.getFechaVigencia());
                    if (!sw) {
                        ParEstado parEstado = parEstadoService.leerPorCodigo(EnumParEstado.ESTADO_NO_VIGENTE.getCodigo());
                        ApiConfiguracion apiConfiguracion = apiConfiguracionService.leerPorId(entidadConfiguracion.getIdConfiguracion());
                        apiConfiguracion.setEstadoConfiguracion(parEstado);
                        apiConfiguracionService.modificar(apiConfiguracion);
                    } else {
                        return null;
                    }
                }
            }
        }
        if (entidadDosificacion != null) {
            ApiSucursal apiSucursal = apiSucursalService.leerPorId(consultaParametros.getIdSucursal());
            Long codigoPuntoVenta = 0l;
            ApiPuntoVenta apiPuntoVenta = null;
            if (consultaParametros.getIdPuntoVenta() != null) {
                apiPuntoVenta = apiPuntoVentaService.leerPorId(consultaParametros.getIdPuntoVenta());
                codigoPuntoVenta = apiPuntoVenta.getCodigoPuntoVenta();
            }
            SolicitudCliente solicitudCliente = new SolicitudCliente();
            solicitudCliente.setCodigoAmbiente(consultaParametros.getCodigoAmbiente());
            solicitudCliente.setCodigoModalidad(entidadDosificacion.getCodigoModalidad());
            solicitudCliente.setCodigoSistema(consultaParametros.getCodigoSistema());
            solicitudCliente.setCodigoSucursal(apiSucursal.getCodigoSucursal().intValue());
            solicitudCliente.setCodigoPuntoVenta(codigoPuntoVenta.intValue());
            solicitudCliente.setCuis(entidadDosificacion.getCuis());
            solicitudCliente.setNitEmpresa(consultaParametros.getNitEmpresa());
            solicitudCliente.setIdEmpresa(consultaParametros.getIdEmpresa());
            solicitudCliente.setApiSucursal(apiSucursal);
            solicitudCliente.setApiPuntoVenta(apiPuntoVenta);
            solicitudCliente.setIdDosificacion(entidadDosificacion.getIdDosificacion());
            if (entidadConfiguracion != null) {
                solicitudCliente.setIdConfiguracion(entidadConfiguracion.getIdConfiguracion());
                solicitudCliente.setFechaVigencia(entidadConfiguracion.getFechaVigencia());
            }
            solicitudCliente.setUsuario(login);
            return solicitudCliente;
        }
        return null;
    }

}
