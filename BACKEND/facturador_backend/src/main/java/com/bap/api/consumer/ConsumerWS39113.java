package com.bap.api.consumer;

import com.api.fesc_39113.AnulacionFacturaElectronicaEstandar;
import com.api.fesc_39113.AnulacionFacturaElectronicaEstandarResponse;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.api.fesc_39113.ObjectFactory;
import com.api.fesc_39113.RecepcionFacturaElectronicaEstandar;
import com.api.fesc_39113.RecepcionFacturaElectronicaEstandarResponse;
import com.api.fesc_39113.SolicitudAnulacion;
import com.api.fesc_39113.SolicitudRecepcion;
import com.api.fesc_39113.SolicitudValidacionAnulacion;
import com.api.fesc_39113.SolicitudValidacionRecepcion;
import com.api.fesc_39113.ValidacionAnulacionFacturaElectronicaEstandar;
import com.api.fesc_39113.ValidacionAnulacionFacturaElectronicaEstandarResponse;
import com.api.fesc_39113.ValidacionRecepcionFacturaElectronicaEstandar;
import com.api.fesc_39113.ValidacionRecepcionFacturaElectronicaEstandarResponse;
import com.api.fesc_39113.VerificarComunicacion;
import com.api.fesc_39113.VerificarComunicacionResponse;
import com.bap.api.dto.Respuesta39113;
import com.bap.api.dto.SolicitudCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@SuppressWarnings("unchecked")
@Component("consumerWS39113")
public class ConsumerWS39113 {

    @Autowired
    @Qualifier("webServiceTemplate39113")
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

    public Respuesta39113 recepcionFacturaElectronicaEstandar(SolicitudCliente solicitud) {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            RecepcionFacturaElectronicaEstandar request = objectFactory.createRecepcionFacturaElectronicaEstandar();

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
            //solicitudRecepcion.setCuis(solicitud.getCuis());
            solicitudRecepcion.setCuis(solicitud.getApiDosificacion().getCuis());
            XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(solicitud.getUtcFechaEnvio());
            solicitudRecepcion.setFechaEnvio(xmlGregoriaFechaEmision);
            solicitudRecepcion.setNit(solicitud.getNitEmpresa());
            solicitudRecepcion.setArchivo(solicitud.getArchivo());
            solicitudRecepcion.setHashArchivo(solicitud.getHashArchivo());
            request.setSolicitudServicioRecepcion(solicitudRecepcion);

            JAXBElement<RecepcionFacturaElectronicaEstandar> jaxbRequest = objectFactory.createRecepcionFacturaElectronicaEstandar(request);
            JAXBElement<RecepcionFacturaElectronicaEstandarResponse> recepcionFacturaElectronicaEstandarResponse = (JAXBElement<RecepcionFacturaElectronicaEstandarResponse>) webServiceTemplate
                    .marshalSendAndReceive(jaxbRequest);
            RecepcionFacturaElectronicaEstandarResponse response = recepcionFacturaElectronicaEstandarResponse.getValue();

            Respuesta39113 respuesta = new Respuesta39113();
            respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
            respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
            respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
            respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
            respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
            respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
            return respuesta;

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ConsumerWS39113.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Respuesta39113 validacionRecepcionFacturaElectronicaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionRecepcionFacturaElectronicaEstandar request = objectFactory.createValidacionRecepcionFacturaElectronicaEstandar();

        SolicitudValidacionRecepcion solicitudValidacionRecepcion = new SolicitudValidacionRecepcion();
        solicitudValidacionRecepcion.setCodigoSistema(solicitud.getCodigoSistema());
        solicitudValidacionRecepcion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudValidacionRecepcion.setCodigoEmision(solicitud.getCodigoEmision());
        //solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getCodigoModalidad());
        solicitudValidacionRecepcion.setCodigoModalidad(solicitud.getApiDosificacion().getParTipoModalidad().getCodigo().intValue());
        solicitudValidacionRecepcion.setNit(solicitud.getNitEmpresa());
//        solicitudValidacionRecepcion.setCuis(solicitud.getCuis());
        solicitudValidacionRecepcion.setCuis(solicitud.getApiDosificacion().getCuis());
        solicitudValidacionRecepcion.setCufd(solicitud.getCufd());
        solicitudValidacionRecepcion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudValidacionRecepcion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudValidacionRecepcion.setCodigoSucursal(solicitud.getCodigoSucursal());
        JAXBElement<Integer> codigoPuntoVenta = objectFactory.createSolicitudValidacionRecepcionCodigoPuntoVenta(solicitud.getCodigoPuntoVenta());

        solicitudValidacionRecepcion.setCodigoPuntoVenta(codigoPuntoVenta);
        solicitudValidacionRecepcion.setCodigoRecepcion(solicitud.getCodigoRecepcion());
        request.setSolicitudServicioValidacionRecepcion(solicitudValidacionRecepcion);

        JAXBElement<ValidacionRecepcionFacturaElectronicaEstandar> jaxbRequest = objectFactory.createValidacionRecepcionFacturaElectronicaEstandar(request);

        JAXBElement<ValidacionRecepcionFacturaElectronicaEstandarResponse> validacionRecepcionFacturaElectronicaEstandarResponse = (JAXBElement<ValidacionRecepcionFacturaElectronicaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        ValidacionRecepcionFacturaElectronicaEstandarResponse response = validacionRecepcionFacturaElectronicaEstandarResponse.getValue();

        Respuesta39113 respuesta = new Respuesta39113();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39113 anulaFacturaElectronicaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        AnulacionFacturaElectronicaEstandar request = objectFactory.createAnulacionFacturaElectronicaEstandar();
        SolicitudAnulacion solicitudAnulacion = new SolicitudAnulacion();
        solicitudAnulacion.setCodigoAmbiente(solicitud.getCodigoAmbiente());
        solicitudAnulacion.setCodigoDocumentoFiscal(solicitud.getCodigoDocumentoFiscal());
        solicitudAnulacion.setCodigoDocumentoSector(solicitud.getCodigoDocumentoSector());
        solicitudAnulacion.setCodigoEmision(solicitud.getCodigoEmision());
//        solicitudAnulacion.setCodigoModalidad(solicitud.getCodigoModalidad());
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

        JAXBElement<AnulacionFacturaElectronicaEstandar> jaxbRequest = objectFactory.createAnulacionFacturaElectronicaEstandar(request);

        JAXBElement<AnulacionFacturaElectronicaEstandarResponse> anulacionFacturaElectronicaEstandarResponse = (JAXBElement<AnulacionFacturaElectronicaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);
        AnulacionFacturaElectronicaEstandarResponse response = anulacionFacturaElectronicaEstandarResponse.getValue();

        Respuesta39113 respuesta = new Respuesta39113();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }

    public Respuesta39113 validaAnulacionFacturaElectronicaEstandar(SolicitudCliente solicitud) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidacionAnulacionFacturaElectronicaEstandar request = objectFactory.createValidacionAnulacionFacturaElectronicaEstandar();
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
        JAXBElement<ValidacionAnulacionFacturaElectronicaEstandar> jaxbRequest = objectFactory.createValidacionAnulacionFacturaElectronicaEstandar(request);

        JAXBElement<ValidacionAnulacionFacturaElectronicaEstandarResponse> validacionAnulacionFacturaComputarizadaEstandarResponse = (JAXBElement<ValidacionAnulacionFacturaElectronicaEstandarResponse>) webServiceTemplate
                .marshalSendAndReceive(jaxbRequest);

        ValidacionAnulacionFacturaElectronicaEstandarResponse response = validacionAnulacionFacturaComputarizadaEstandarResponse.getValue();

        Respuesta39113 respuesta = new Respuesta39113();
        respuesta.setTransaccion(response.getRespuestaServicioFacturacion().isTransaccion());
        respuesta.setCodigoEstado(response.getRespuestaServicioFacturacion().getCodigoEstado());
        respuesta.setCodigoRecepcion(response.getRespuestaServicioFacturacion().getCodigoRecepcion());
        respuesta.setListaCodigosRespuestas(response.getRespuestaServicioFacturacion().getListaCodigosRespuestas());
        respuesta.setListaDescripcionesRespuestas(response.getRespuestaServicioFacturacion().getListaDescripcionesRespuestas());
        respuesta.setListaErroresDetalles(response.getRespuestaServicioFacturacion().getListaErroresDetalles());
        return respuesta;
    }
}
