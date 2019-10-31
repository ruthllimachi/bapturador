package com.bap.api.consumer;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bap.api.dto.Respuesta39266;
import com.bap.api.dto.SolicitudCliente;
import com.api.fesc_39266.ObjectFactory;
import com.api.fesc_39266.SincronizarFechaHora;
import com.api.fesc_39266.SincronizarFechaHoraResponse;
import com.api.fesc_39266.SolicitudSincronizacion;
import com.api.fesc_39266.VerificarComunicacion;
import com.api.fesc_39266.VerificarComunicacionResponse;
import com.bap.api.utils.FechaUtils;

@SuppressWarnings("unchecked")
@Component("consumerWS39266")
public class ConsumerWS39266 {

    @Autowired
    @Qualifier("webServiceTemplate39266")
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

    public Respuesta39266 sincronizarFechaHora(SolicitudCliente solicitudCliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudSincronizacion solicitudSincronizacion = objectFactory.createSolicitudSincronizacion();
        solicitudSincronizacion.setCodigoAmbiente(solicitudCliente.getCodigoAmbiente());
        solicitudSincronizacion.setCodigoSistema(solicitudCliente.getCodigoSistema());
        solicitudSincronizacion.setCodigoSucursal(solicitudCliente.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudSincronizacionCodigoPuntoVenta(solicitudCliente.getCodigoPuntoVenta());
        solicitudSincronizacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudSincronizacion.setNit(solicitudCliente.getNitEmpresa());
        solicitudSincronizacion.setCuis(solicitudCliente.getCuis());

        SincronizarFechaHora request = objectFactory.createSincronizarFechaHora();
        request.setSolicitudSincronizacion(solicitudSincronizacion);
        JAXBElement<SincronizarFechaHora> jaxbRequest = objectFactory.createSincronizarFechaHora(request);
        JAXBElement<SincronizarFechaHoraResponse> sincronizarFechaHoraResponse = (JAXBElement<SincronizarFechaHoraResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SincronizarFechaHoraResponse response = sincronizarFechaHoraResponse.getValue();

        Respuesta39266 respuestaSincronizacion = new Respuesta39266();
        respuestaSincronizacion.setTransaccion(response.getRespuestaFechaHora().isTransaccion());
        if (respuestaSincronizacion.isTransaccion()) {
            String fechaHora = response.getRespuestaFechaHora().getFechaHora();
            respuestaSincronizacion.setFechaHora(FechaUtils.convertStringToLocalDateTime(fechaHora));
        }
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaFechaHora().getListaCodigosRespuestas());
        return respuestaSincronizacion;
    }

}
