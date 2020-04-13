package com.bap.api.consumer;

import com.api.fesc_39115.AnulacionNotaFiscalComputarizadaCreditoDebito;
import com.api.fesc_39115.AnulacionNotaFiscalComputarizadaCreditoDebitoResponse;
import com.api.fesc_39115.ObjectFactory;
import com.api.fesc_39115.RecepcionNotaFiscalComputarizadaCreditoDebito;
import com.api.fesc_39115.RecepcionNotaFiscalComputarizadaCreditoDebitoResponse;
import com.api.fesc_39115.SolicitudAnulacion;
import com.api.fesc_39115.SolicitudRecepcion;
import com.api.fesc_39115.SolicitudValidacionAnulacion;
import com.api.fesc_39115.SolicitudValidacionRecepcion;
import com.api.fesc_39115.ValidacionAnulacionNotaFiscalComputarizadaCreditoDebito;
import com.api.fesc_39115.ValidacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse;
import com.api.fesc_39115.ValidacionRecepcionNotaFiscalComputarizadaCreditoDebito;
import com.api.fesc_39115.ValidacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse;
import com.api.fesc_39115.VerificarComunicacion;
import com.api.fesc_39115.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39115;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.bap.api.dto.SolicitudCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

//NotaFiscalComputarizadaCreditoDebito
@SuppressWarnings("unchecked")
@Component("consumerWS39115")
public class ConsumerWS39115 {

    @Autowired
    @Qualifier("webServiceTemplate39115")
    private WebServiceTemplate webServiceTemplate;

    public int verificaComunicacion() {
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

    public Respuesta39115 recepcionNotaFiscalComputarizadaCreditoDebito(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            RecepcionNotaFiscalComputarizadaCreditoDebito request = objectFactory.createRecepcionNotaFiscalComputarizadaCreditoDebito();
            SolicitudRecepcion solicitudRecepcion = new SolicitudRecepcion();
            solicitudRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
            solicitudRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
            solicitudRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
            solicitudRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
            //solicitudRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
            solicitudRecepcion.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
            JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
            solicitudRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
            solicitudRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
            solicitudRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
            solicitudRecepcion.setCufd(solicitud.getCufd());
            solicitudRecepcion.setCuis(solicitud.getApiDosificacion().getCuis());
            XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(solicitud.getFechaEnvio().toString());
            solicitudRecepcion.setFechaEnvio(xmlGregoriaFechaEmision);
            solicitudRecepcion.setNit(solicitud.getNitEmpresa());
            solicitudRecepcion.setArchivo(solicitud.getArchivo());
            solicitudRecepcion.setHashArchivo(solicitud.getHashArchivo());
            request.setSolicitudServicioRecepcion(solicitudRecepcion);

            JAXBElement<RecepcionNotaFiscalComputarizadaCreditoDebito> jaxbRequest = objectFactory.createRecepcionNotaFiscalComputarizadaCreditoDebito(request);
            JAXBElement<RecepcionNotaFiscalComputarizadaCreditoDebitoResponse> recepcionNotaFiscalComputarizadaCreditoDebitoResponse = (JAXBElement<RecepcionNotaFiscalComputarizadaCreditoDebitoResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            RecepcionNotaFiscalComputarizadaCreditoDebitoResponse response = recepcionNotaFiscalComputarizadaCreditoDebitoResponse.getValue();

            Respuesta39115 respuesta = new Respuesta39115();
            respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
            respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
            respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
            respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
            respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
            respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
            return respuesta;

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ConsumerWS39112.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Respuesta39115 validacionRecepcionNotaFiscalComputarizadaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionRecepcionNotaFiscalComputarizadaCreditoDebito request = objectFactory.createValidacionRecepcionNotaFiscalComputarizadaCreditoDebito();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
        //solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudValidacionRecepcion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionRecepcion.setCuis(solicitud.getApiDosificacion().getCuis());
        solicitudValidacionRecepcion.setCufd(solicitud.getCufd());
        solicitudValidacionRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());

        solicitudValidacionRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionRecepcion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        request.setSolicitudServicioValidacionRecepcion(solicitudValidacionRecepcion);

        JAXBElement<ValidacionRecepcionNotaFiscalComputarizadaCreditoDebito> jaxbRequest = objectFactory.createValidacionRecepcionNotaFiscalComputarizadaCreditoDebito(request);

        JAXBElement<ValidacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse> validacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse = (JAXBElement<ValidacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse response = validacionRecepcionNotaFiscalComputarizadaCreditoDebitoResponse.getValue();

        Respuesta39115 respuesta = new Respuesta39115();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39115 anulaNotaFiscalComputarizadaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        AnulacionNotaFiscalComputarizadaCreditoDebito request = objectFactory.createAnulacionNotaFiscalComputarizadaCreditoDebito();
        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        //solicitudAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudAnulacion.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudAnulacion.setCufd(solicitud.getCufd());
        solicitudAnulacion.setCuf(solicitud.getCuf());
        solicitudAnulacion.setCuis(solicitud.getApiDosificacion().getCuis());
        solicitudAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());
        request.setSolicitudServicioAnulacion(solicitudAnulacion);
        JAXBElement<AnulacionNotaFiscalComputarizadaCreditoDebito> jaxbRequest = objectFactory.createAnulacionNotaFiscalComputarizadaCreditoDebito(request);
        JAXBElement<AnulacionNotaFiscalComputarizadaCreditoDebitoResponse> anulacionNotaFiscalComputarizadaCreditoDebitoResponse = (JAXBElement<AnulacionNotaFiscalComputarizadaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        AnulacionNotaFiscalComputarizadaCreditoDebitoResponse response = anulacionNotaFiscalComputarizadaCreditoDebitoResponse.getValue();

        Respuesta39115 respuesta = new Respuesta39115();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39115 validaAnulacionNotaFiscalComputarizadaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionAnulacionNotaFiscalComputarizadaCreditoDebito request = objectFactory.createValidacionAnulacionNotaFiscalComputarizadaCreditoDebito();
        SolicitudValidacionAnulacion solicitudValidacionAnulacion = new SolicitudValidacionAnulacion();
        solicitudValidacionAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        //solicitudValidacionAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionAnulacion.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudValidacionAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudValidacionAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionAnulacion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        solicitudValidacionAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudValidacionAnulacion.setCuf(solicitud.getCuf());
        solicitudValidacionAnulacion.setCufd(solicitud.getCufd());
        solicitudValidacionAnulacion.setCuis(solicitud.getApiDosificacion().getCuis());
        solicitudValidacionAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());

        request.setSolicitudServicioValidacionAnulacion(solicitudValidacionAnulacion);
        JAXBElement<ValidacionAnulacionNotaFiscalComputarizadaCreditoDebito> jaxbRequest = objectFactory.createValidacionAnulacionNotaFiscalComputarizadaCreditoDebito(request);

        JAXBElement<ValidacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse> validacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse = (JAXBElement<ValidacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);

        ValidacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse response = validacionAnulacionNotaFiscalComputarizadaCreditoDebitoResponse.getValue();

        Respuesta39115 respuesta = new Respuesta39115();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

}
