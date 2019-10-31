package com.bap.api.consumer;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39117.CierreOperacionesSistema;
import com.api.fesc_39117.CierreOperacionesSistemaResponse;
import com.api.fesc_39117.ObjectFactory;
import com.api.fesc_39117.RegistroPuntoVenta;
import com.api.fesc_39117.RegistroPuntoVentaResponse;
import com.api.fesc_39117.RespuestaCodigosMensajesSoapDto;
import com.api.fesc_39117.SolicitudCuis;
import com.api.fesc_39117.SolicitudCuisResponse;
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

    public Respuesta39117 solicitudCuis(SolicitudCliente solicitudSincronizacion) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudCuis request = objectFactory.createSolicitudCuis();

        SolicitudOperacionesCuis solicitudOperacionesCuis = new SolicitudOperacionesCuis();
        solicitudOperacionesCuis.setCodigoAmbiente(solicitudSincronizacion.getCodigoAmbiente());
        solicitudOperacionesCuis.setCodigoSistema(solicitudSincronizacion.getCodigoSistema());
        solicitudOperacionesCuis.setCodigoSucursal(solicitudSincronizacion.getCodigoSucursal());
        //solicitudOperacionesCuis.setCodigoPuntoVenta(solicitudSincronizacion.getCodigoPuntoVenta());        
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudOperacionesCuisCodigoPuntoVenta(solicitudSincronizacion.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta.getValue());

        solicitudOperacionesCuis.setCodigoModalidad(solicitudSincronizacion.getCodigoModalidad());
        solicitudOperacionesCuis.setNit(solicitudSincronizacion.getNitEmpresa());
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

    /**
     * Modificado con buenas practicas
     *
     * @param solicitudSincronizacion
     * @return
     */
    public Respuesta39117 registroPuntoVenta(SolicitudCliente solicitudSincronizacion) {
        ObjectFactory objectFactory = new ObjectFactory();
        RegistroPuntoVenta request = objectFactory.createRegistroPuntoVenta();
        SolicitudRegistroPuntoVenta solicitudRegistroPuntoVenta = new SolicitudRegistroPuntoVenta();
        solicitudRegistroPuntoVenta.setCodigoAmbiente(solicitudSincronizacion.getCodigoAmbiente());
        solicitudRegistroPuntoVenta.setCodigoModalidad(solicitudSincronizacion.getCodigoModalidad());
        solicitudRegistroPuntoVenta.setCodigoSistema(solicitudSincronizacion.getCodigoSistema());
        solicitudRegistroPuntoVenta.setCodigoSucursal(solicitudSincronizacion.getCodigoSucursal());
        solicitudRegistroPuntoVenta.setCodigoTipoPuntoVenta(solicitudSincronizacion.getCodigoTipoPuntoVenta());
        solicitudRegistroPuntoVenta.setCuis(solicitudSincronizacion.getCuis());
        solicitudRegistroPuntoVenta.setNit(solicitudSincronizacion.getNitEmpresa());
        solicitudRegistroPuntoVenta.setDescripcion(solicitudSincronizacion.getDescripcion());
        solicitudRegistroPuntoVenta.setNombrePuntoVenta(solicitudSincronizacion.getNombrePuntoVenta());
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

    public Respuesta39117 cierreOperacionesSistema(SolicitudCliente solicitudSincronizacion) {
        ObjectFactory objectFactory = new ObjectFactory();
        CierreOperacionesSistema request = objectFactory.createCierreOperacionesSistema();

        request.getSolicitudOperaciones().setCodigoAmbiente(solicitudSincronizacion.getCodigoAmbiente());
        request.getSolicitudOperaciones().setCodigoModalidad(solicitudSincronizacion.getCodigoModalidad());
        //request.getSolicitudOperaciones().setCodigoPuntoVenta(solicitudSincronizacion.getCodigoPuntoVenta());		
        request.getSolicitudOperaciones().setCodigoSistema(solicitudSincronizacion.getCodigoSistema());
        request.getSolicitudOperaciones().setCodigoSucursal(solicitudSincronizacion.getCodigoSucursal());
        request.getSolicitudOperaciones().setCuis(solicitudSincronizacion.getCuis());
        request.getSolicitudOperaciones().setNit(solicitudSincronizacion.getNitEmpresa());

        JAXBElement<CierreOperacionesSistema> jaxbRequest = objectFactory.createCierreOperacionesSistema(request);
        JAXBElement<CierreOperacionesSistemaResponse> cierreOperacionesSistemaResponse = (JAXBElement<CierreOperacionesSistemaResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        CierreOperacionesSistemaResponse response = cierreOperacionesSistemaResponse.getValue();

        List<RespuestaCodigosMensajesSoapDto> listMensajeSoapDto = response.getRespuestaCierreSistemas().getListaCodigosRespuestas();
        String codigoSistema = response.getRespuestaCierreSistemas().getCodigoSistema();

        Respuesta39117 respuestaSincronizacion = new Respuesta39117();
        if (!listMensajeSoapDto.isEmpty()) {
            ///ojo se quito codigoMensaje porque no lanza un solo mensaje puede evnicar n - mensajes
            /////   respuestaSincronizacion.setCodigoMensaje(listMensajeSoapDto.get(0).getCodigoMensaje());
        }
        respuestaSincronizacion.setCodigoSistema(codigoSistema);
        return respuestaSincronizacion;
    }

}
