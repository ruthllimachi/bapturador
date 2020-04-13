package com.bap.api.consumer;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39117.CierreOperacionesSistema;
import com.api.fesc_39117.CierreOperacionesSistemaResponse;
import com.api.fesc_39117.CierrePuntoVenta;
import com.api.fesc_39117.CierrePuntoVentaResponse;
import com.api.fesc_39117.ConsultaPuntoVenta;
import com.api.fesc_39117.ConsultaPuntoVentaResponse;
import com.api.fesc_39117.ObjectFactory;
import com.api.fesc_39117.RegistroPuntoVenta;
import com.api.fesc_39117.RegistroPuntoVentaResponse;
import com.api.fesc_39117.SolicitudCierrePuntoVenta;
import com.api.fesc_39117.SolicitudConsultaPuntoVenta;
import com.api.fesc_39117.SolicitudCuis;
import com.api.fesc_39117.SolicitudCuisResponse;
import com.api.fesc_39117.SolicitudOperaciones;
import com.api.fesc_39117.SolicitudOperacionesCuis;
import com.api.fesc_39117.SolicitudRegistroPuntoVenta;
import com.api.fesc_39117.VerificarComunicacion;
import com.api.fesc_39117.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39117;
import com.bap.api.dto.SolicitudCliente;

@SuppressWarnings("unchecked")
@Component("consumerWS39117")
public class ConsumerWS39117 {

    @Autowired
    @Qualifier("webServiceTemplate39117")
    private WebServiceTemplate webServiceTemplate;

    public int verificarComunicacion() {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            VerificarComunicacion request = new VerificarComunicacion();
            JAXBElement<VerificarComunicacion> jaxbRequest = objectFactory.createVerificarComunicacion(request);
            JAXBElement<VerificarComunicacionResponse> response = (JAXBElement<VerificarComunicacionResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            VerificarComunicacionResponse verificarComunicacionResponse = response.getValue();
            int codigo = verificarComunicacionResponse.getReturn();
            return codigo;
        } catch (Exception e) {
            System.out.println("#####Error " + e.getMessage());
            return -1;
        }
    }

    public Respuesta39117 registroPuntoVenta(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        
        RegistroPuntoVenta request = objectFactory.createRegistroPuntoVenta();
        SolicitudRegistroPuntoVenta solicitudRegistroPuntoVenta = new SolicitudRegistroPuntoVenta();
        solicitudRegistroPuntoVenta.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudRegistroPuntoVenta.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudRegistroPuntoVenta.setCodigoSistema(solicitud.getCodigoSistema());
//        solicitudRegistroPuntoVenta.setCodigoSistemaProveedor(null);
        solicitudRegistroPuntoVenta.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudRegistroPuntoVenta.setCodigoTipoPuntoVenta(solicitud.getCodigoTipoPuntoVenta());
        solicitudRegistroPuntoVenta.setCuis(solicitud.getCuis());
        solicitudRegistroPuntoVenta.setDescripcion(solicitud.getDescripcion());
        solicitudRegistroPuntoVenta.setNit(solicitud.getNitEmpresa());
        solicitudRegistroPuntoVenta.setNombrePuntoVenta(solicitud.getNombrePuntoVenta());

        request.setSolicitudRegistroPuntoVenta(solicitudRegistroPuntoVenta);
        JAXBElement<RegistroPuntoVenta> jaxbRequest = objectFactory.createRegistroPuntoVenta(request);
        JAXBElement<RegistroPuntoVentaResponse> registroPuntoVentaResponse = (JAXBElement<RegistroPuntoVentaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        RegistroPuntoVentaResponse response = registroPuntoVentaResponse.getValue();
        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        respuestaSincronizacion.setTransaccion(response.getRespuestaRegistroPuntoVenta().isTransaccion());
        respuestaSincronizacion.setCodigoPuntoVenta(response.getRespuestaRegistroPuntoVenta().getCodigoPuntoVenta());
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaRegistroPuntoVenta().getListaCodigosRespuestas());
        return respuestaSincronizacion;
    }

    public Respuesta39117 consultaPuntoVenta(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        
        ConsultaPuntoVenta request = objectFactory.createConsultaPuntoVenta();
        SolicitudConsultaPuntoVenta solicitudConsultaPuntoVenta = new SolicitudConsultaPuntoVenta();
        solicitudConsultaPuntoVenta.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudConsultaPuntoVenta.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudConsultaPuntoVenta.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudConsultaPuntoVenta.setCuis(solicitud.getCuis());
        solicitudConsultaPuntoVenta.setNit(solicitud.getNitEmpresa());

        request.setSolicitudConsultaPuntoVenta(solicitudConsultaPuntoVenta);
        JAXBElement<ConsultaPuntoVenta> jaxbRequest = objectFactory.createConsultaPuntoVenta(request);
        JAXBElement<ConsultaPuntoVentaResponse> consultaPuntoVentaResponse = (JAXBElement<ConsultaPuntoVentaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ConsultaPuntoVentaResponse response = consultaPuntoVentaResponse.getValue();
        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        respuestaSincronizacion.setTransaccion(response.getRespuestaConsultaPuntoVenta().isTransaccion());
        respuestaSincronizacion.setListaPuntosVentas(response.getRespuestaConsultaPuntoVenta().getListaPuntosVentas());
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaConsultaPuntoVenta().getListaCodigosRespuestas());
        return respuestaSincronizacion;
    }

    public Respuesta39117 cierrePuntoVenta(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        
        CierrePuntoVenta request = objectFactory.createCierrePuntoVenta();
        SolicitudCierrePuntoVenta solicitudCierrePuntoVenta = new SolicitudCierrePuntoVenta();
        solicitudCierrePuntoVenta.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudCierrePuntoVenta.setCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudCierrePuntoVenta.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudCierrePuntoVenta.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudCierrePuntoVenta.setCuis(solicitud.getCuis());
        solicitudCierrePuntoVenta.setNit(solicitud.getNitEmpresa());

        request.setSolicitudCierrePuntoVenta(solicitudCierrePuntoVenta);
        JAXBElement<CierrePuntoVenta> jaxbRequest = objectFactory.createCierrePuntoVenta(request);
        JAXBElement<CierrePuntoVentaResponse> cierrePuntoVentaResponse = (JAXBElement<CierrePuntoVentaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        CierrePuntoVentaResponse response = cierrePuntoVentaResponse.getValue();
        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        respuestaSincronizacion.setTransaccion(response.getRespuestaCierrePuntoVenta().isTransaccion());
        respuestaSincronizacion.setCodigoPuntoVenta(response.getRespuestaCierrePuntoVenta().getCodigoPuntoVenta().longValue());
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaCierrePuntoVenta().getListaCodigosRespuestas());
        return respuestaSincronizacion;
    }

    public Respuesta39117 solicitudCuis(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        
        SolicitudCuis request = objectFactory.createSolicitudCuis();        
        SolicitudOperacionesCuis solicitudOperacionesCuis = new SolicitudOperacionesCuis();        
        solicitudOperacionesCuis.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudOperacionesCuis.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudOperacionesCuis.setNit(solicitud.getNitEmpresa());
        solicitudOperacionesCuis.setCodigoModalidad(solicitud.getParTipoModalidad().getCodigo().intValue());                      
        solicitudOperacionesCuis.setCodigoSucursal(solicitud.getCodigoSucursal());    
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudOperacionesCuisCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudOperacionesCuis.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudOperacionesCuis.setLogin(solicitud.getLogin());
        solicitudOperacionesCuis.setPassword(solicitud.getPassword());
                         
        request.setSolicitudOperacionesCuis(solicitudOperacionesCuis);
        JAXBElement<SolicitudCuis> jaxbRequest = objectFactory.createSolicitudCuis(request);
        JAXBElement<SolicitudCuisResponse> solicitudCuisResponse = (JAXBElement<SolicitudCuisResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SolicitudCuisResponse response = solicitudCuisResponse.getValue();
        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        respuestaSincronizacion.setTransaccion(response.getRespuestaCuis().isTransaccion());
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaCuis().getListaCodigosRespuestas());
        respuestaSincronizacion.setCodigoCuis(response.getRespuestaCuis().getCodigo());
        return respuestaSincronizacion;
    }

    public Respuesta39117 cierreOperacionesSistema(SolicitudCliente t) {
        ObjectFactory objectFactory = new ObjectFactory();
        
        CierreOperacionesSistema request = objectFactory.createCierreOperacionesSistema();
        SolicitudOperaciones  solicitudOperaciones  = new SolicitudOperaciones();
        solicitudOperaciones.setCodigoAmbiente(t.getCodigoAmbiente());
        solicitudOperaciones.setCodigoSistema(t.getCodigoSistema());
        solicitudOperaciones.setNit(t.getNitEmpresa());        
        solicitudOperaciones.setCodigoModalidad(t.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudOperaciones.setCuis(t.getApiDosificacion().getCuis());
        solicitudOperaciones.setCodigoSucursal(t.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudOperacionesCodigoPuntoVenta(t.getCodigoPuntoVenta());        
        solicitudOperaciones.setCodigoPuntoVenta(codigoPuntoVenta);
                
        request.setSolicitudOperaciones(solicitudOperaciones);
        JAXBElement<CierreOperacionesSistema> jaxbRequest = objectFactory.createCierreOperacionesSistema(request);
        JAXBElement<CierreOperacionesSistemaResponse> cierreOperacionesSistemaResponse = (JAXBElement<CierreOperacionesSistemaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        CierreOperacionesSistemaResponse response = cierreOperacionesSistemaResponse.getValue();
        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        respuestaSincronizacion.setTransaccion(response.getRespuestaCierreSistemas().isTransaccion());
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaCierreSistemas().getListaCodigosRespuestas());
        respuestaSincronizacion.setCodigoSistema(response.getRespuestaCierreSistemas().getCodigoSistema());
        return respuestaSincronizacion;        
    }

}
