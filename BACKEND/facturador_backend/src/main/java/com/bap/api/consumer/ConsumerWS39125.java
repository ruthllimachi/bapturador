package com.bap.api.consumer;

import com.api.fesc_39125.AnulacionNotaFiscalElectronicaCreditoDebito;
import com.api.fesc_39125.AnulacionNotaFiscalElectronicaCreditoDebitoResponse;
import com.api.fesc_39125.ObjectFactory;
import com.api.fesc_39125.RecepcionNotaFiscalElectronicaCreditoDebito;

import com.api.fesc_39125.RecepcionNotaFiscalElectronicaCreditoDebitoResponse;
import com.api.fesc_39125.SolicitudAnulacion;
import com.api.fesc_39125.SolicitudRecepcion;
import com.api.fesc_39125.SolicitudValidacionAnulacion;
import com.api.fesc_39125.SolicitudValidacionRecepcion;
import com.api.fesc_39125.ValidacionAnulacionNotaFiscalElectronicaCreditoDebito;
import com.api.fesc_39125.ValidacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse;
import com.api.fesc_39125.ValidacionRecepcionNotaFiscalElectronicaCreditoDebito;
import com.api.fesc_39125.ValidacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse;
import com.api.fesc_39125.VerificarComunicacion;
import com.api.fesc_39125.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39125;
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

//NotaFiscalElectronicaCreditoDebito
@SuppressWarnings("unchecked")
@Component("consumerWS39125")
public class ConsumerWS39125 {

    @Autowired
    @Qualifier("webServiceTemplate39125")
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

    public Respuesta39125 recepcionNotaFiscalElectronicaCreditoDebito(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            RecepcionNotaFiscalElectronicaCreditoDebito request = objectFactory.createRecepcionNotaFiscalElectronicaCreditoDebito();

            SolicitudRecepcion solicitudRecepcion = new SolicitudRecepcion();
            solicitudRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
            solicitudRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
            solicitudRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
            solicitudRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
            solicitudRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
            JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
            solicitudRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
            solicitudRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
            solicitudRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
            solicitudRecepcion.setCufd(solicitud.getCufd());
            solicitudRecepcion.setCuis(solicitud.getCuis());
            XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(solicitud.getFechaEnvio().toString());
            solicitudRecepcion.setFechaEnvio(xmlGregoriaFechaEmision);
            solicitudRecepcion.setNit(solicitud.getNitEmpresa());
            solicitudRecepcion.setArchivo(solicitud.getArchivo());
            solicitudRecepcion.setHashArchivo(solicitud.getHashArchivo());
            request.setSolicitudServicioRecepcion(solicitudRecepcion);

            JAXBElement<RecepcionNotaFiscalElectronicaCreditoDebito> jaxbRequest = objectFactory.createRecepcionNotaFiscalElectronicaCreditoDebito(request);
            JAXBElement<RecepcionNotaFiscalElectronicaCreditoDebitoResponse> recepcionNotaFiscalElectronicaCreditoDebitoResponse = (JAXBElement<RecepcionNotaFiscalElectronicaCreditoDebitoResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);

            RecepcionNotaFiscalElectronicaCreditoDebitoResponse response = recepcionNotaFiscalElectronicaCreditoDebitoResponse.getValue();

            Respuesta39125 respuesta = new Respuesta39125();
            respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
            respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
            respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
            respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
            respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
            respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
            return respuesta;

        } catch (DatatypeConfigurationException ex) {
            System.out.println("error es " + ex.getMessage());
            Logger.getLogger(ConsumerWS39112.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Respuesta39125 validacionRecepcionNotaFiscalElectronicaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionRecepcionNotaFiscalElectronicaCreditoDebito request = objectFactory.createValidacionRecepcionNotaFiscalElectronicaCreditoDebito();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionRecepcion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionRecepcion.setCuis(solicitud.getCuis());
        solicitudValidacionRecepcion.setCufd(solicitud.getCufd());
        solicitudValidacionRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());

        solicitudValidacionRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionRecepcion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        request.setSolicitudServicioValidacionRecepcion(solicitudValidacionRecepcion);

        JAXBElement<ValidacionRecepcionNotaFiscalElectronicaCreditoDebito> jaxbRequest = objectFactory.createValidacionRecepcionNotaFiscalElectronicaCreditoDebito(request);

        JAXBElement<ValidacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse> validacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse = (JAXBElement<ValidacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse response = validacionRecepcionNotaFiscalElectronicaCreditoDebitoResponse.getValue();

        Respuesta39125 respuesta = new Respuesta39125();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39125 anulaNotaFiscalElectronicaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        AnulacionNotaFiscalElectronicaCreditoDebito request = objectFactory.createAnulacionNotaFiscalElectronicaCreditoDebito();
        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudAnulacion.setCufd(solicitud.getCufd());
        solicitudAnulacion.setCuf(solicitud.getCuf());
        solicitudAnulacion.setCuis(solicitud.getCuis());
        solicitudAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());
        request.setSolicitudServicioAnulacion(solicitudAnulacion);
        JAXBElement<AnulacionNotaFiscalElectronicaCreditoDebito> jaxbRequest = objectFactory.createAnulacionNotaFiscalElectronicaCreditoDebito(request);
        JAXBElement<AnulacionNotaFiscalElectronicaCreditoDebitoResponse> anulacionNotaFiscalElectronicaCreditoDebitoResponse = (JAXBElement<AnulacionNotaFiscalElectronicaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        AnulacionNotaFiscalElectronicaCreditoDebitoResponse response = anulacionNotaFiscalElectronicaCreditoDebitoResponse.getValue();

        Respuesta39125 respuesta = new Respuesta39125();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39125 validaAnulacionNotaFiscalElectronicaCreditoDebito(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionAnulacionNotaFiscalElectronicaCreditoDebito request = objectFactory.createValidacionAnulacionNotaFiscalElectronicaCreditoDebito();
        SolicitudValidacionAnulacion solicitudValidacionAnulacion = new SolicitudValidacionAnulacion();
        solicitudValidacionAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
        solicitudValidacionAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionAnulacion.setCodigoMotivo(solicitud.getCodigoMotivo());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());
        solicitudValidacionAnulacion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionAnulacion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        solicitudValidacionAnulacion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionAnulacion.setCodigoSucursal(solicitud.getCodigoSucursal());
        solicitudValidacionAnulacion.setCuf(solicitud.getCuf());
        solicitudValidacionAnulacion.setCufd(solicitud.getCufd());
        solicitudValidacionAnulacion.setCuis(solicitud.getCuis());
        solicitudValidacionAnulacion.setNit(solicitud.getNitEmpresa());
        solicitudValidacionAnulacion.setNumeroDocumentoFiscal(solicitud.getNumeroDocumentoFiscal());

        request.setSolicitudServicioValidacionAnulacion(solicitudValidacionAnulacion);
        JAXBElement<ValidacionAnulacionNotaFiscalElectronicaCreditoDebito> jaxbRequest = objectFactory.createValidacionAnulacionNotaFiscalElectronicaCreditoDebito(request);

        JAXBElement<ValidacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse> validacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse = (JAXBElement<ValidacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);

        ValidacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse response = validacionAnulacionNotaFiscalElectronicaCreditoDebitoResponse.getValue();

        Respuesta39125 respuesta = new Respuesta39125();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }
}
