package com.bap.api.consumer;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39268.ObjectFactory;
import com.api.fesc_39268.SolicitudCufd;
import com.api.fesc_39268.SolicitudCufdResponse;
import com.api.fesc_39268.SolicitudOperaciones;
import com.api.fesc_39268.VerificarComunicacion;
import com.api.fesc_39268.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39268;
import com.bap.api.dto.SolicitudCliente;
import com.bap.api.utils.FechaUtils;

@SuppressWarnings("unchecked")
@Component("consumerWS39268")
public class ConsumerWS39268 {

    @Autowired
    @Qualifier("webServiceTemplate39268")
    private WebServiceTemplate webServiceTemplate;

    public int verificarComunicacion() {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            VerificarComunicacion request = new VerificarComunicacion();
            JAXBElement<VerificarComunicacion> jaxbRequest = objectFactory.createVerificarComunicacion(request);
            JAXBElement<VerificarComunicacionResponse> response = (JAXBElement<VerificarComunicacionResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            VerificarComunicacionResponse respuesta = response.getValue();
            int codigo = respuesta.getReturn();
            return codigo;
        } catch (Exception e) {
            System.out.println("#####Error " + e.getMessage());
            return -1;
        }
    }

    public Respuesta39268 solicitudCufd(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        SolicitudOperaciones solicitudOperaciones = objectFactory.createSolicitudOperaciones();
        solicitudOperaciones.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        //solicitudOperaciones.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudOperaciones.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudOperaciones.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudOperaciones.setCodigoSucursal(solicitud.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudOperacionesCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudOperaciones.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudOperaciones.setNit(solicitud.getNitEmpresa());
        solicitudOperaciones.setCuis(solicitud.getApiDosificacion().getCuis());

        SolicitudCufd request = objectFactory.createSolicitudCufd();
        request.setSolicitudOperaciones(solicitudOperaciones);
        JAXBElement<SolicitudCufd> jaxbRequest = objectFactory.createSolicitudCufd(request);
        JAXBElement<SolicitudCufdResponse> solicitudCufd = (JAXBElement<SolicitudCufdResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        SolicitudCufdResponse response = solicitudCufd.getValue();

        Respuesta39268 respuestaSincronizacion = new Respuesta39268();
        respuestaSincronizacion.setTransaccion(response.getRespuestaCufd().isTransaccion());
        if (respuestaSincronizacion.isTransaccion()) {
            respuestaSincronizacion.setCodigoCufd(response.getRespuestaCufd().getCodigo());
            Date fechaVigencia = response.getRespuestaCufd().getFechaVigencia().toGregorianCalendar().getTime();
            respuestaSincronizacion.setFechaVigencia(FechaUtils.convertDateToLocalDateTime(fechaVigencia));
        }
        respuestaSincronizacion.setListaRespuestaCodigosMensajesSoapDto(response.getRespuestaCufd().getListaCodigosRespuestas());
        return respuestaSincronizacion;
    }

}
