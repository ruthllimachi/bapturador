/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import com.bap.api.model.api.ApiFactura;
import com.bap.api.model.api.ApiFacturaDetalle;
import com.bap.api.model.api.ApiFacturaComercialExportacion;
import com.bap.api.model.api.ApiFacturaComercialExportacionDetalle;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebito;
import com.bap.api.model.api.ApiNotaFiscalCreditoDebitoDetalle;
import com.bap.api.ws.FacturaComputarizadaComercialExportacion;
import com.bap.api.ws.FacturaComputarizadaEstandar;
import com.bap.api.ws.FacturaElectronicaComercialExportacion;
import com.bap.api.ws.FacturaElectronicaEstandar;
import com.bap.api.ws.NotaFiscalComputarizadaCreditoDebito;
import com.bap.api.ws.NotaFiscalElectronicaCreditoDebito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ruth
 */
public class GeneratorXML {

    public static String facturaElectronicaEstandarToXML(ApiFactura apiFactura, String nombreFileXml) {
        try {
            FacturaElectronicaEstandar.Cabecera cabecera = new FacturaElectronicaEstandar.Cabecera();
            cabecera.setNitEmisor(apiFactura.getNitEmisor());
            cabecera.setNumeroFactura(apiFactura.getNumeroFactura().intValue());
            cabecera.setCuf(apiFactura.getCuf());
            cabecera.setCufd(apiFactura.getApiConfiguracion().getCufd());
            if (apiFactura.getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiFactura.getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiFactura.getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiFactura.getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiFactura.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiFactura.getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiFactura.getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiFactura.getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiFactura.getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiFactura.getApiCliente().getCodigoCliente());
            cabecera.setCodigoMetodoPago(apiFactura.getParTipoMetodoPago().getCodigo().intValue());
            cabecera.setNumeroTarjeta(apiFactura.getNumeroTarjeta());
            cabecera.setMontoTotal(apiFactura.getMontoTotal());
            cabecera.setMontoDescuento(apiFactura.getMontoDescuento());
            cabecera.setCodigoMoneda(apiFactura.getParTipoMoneda().getCodigo().intValue());
            cabecera.setTipoCambio(apiFactura.getTipoCambio());
            cabecera.setMontoTotalMoneda(apiFactura.getMontoTotalMoneda());
            cabecera.setLeyenda(apiFactura.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiFactura.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            FacturaElectronicaEstandar facturaElectronicaEstandar = new FacturaElectronicaEstandar();
            List<ApiFacturaDetalle> listaApiFacturaDetalle = apiFactura.getApiFacturaDetalle();
            FacturaElectronicaEstandar.Detalle detalle;
            for (ApiFacturaDetalle apiFacturaDetalle : listaApiFacturaDetalle) {
                detalle = new FacturaElectronicaEstandar.Detalle();
                detalle.setActividadEconomica(apiFacturaDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiFacturaDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin());
                detalle.setCodigoProducto(apiFacturaDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiFacturaDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCantidad(apiFacturaDetalle.getCantidad());
                detalle.setPrecioUnitario(apiFacturaDetalle.getPrecioUnitario());
                detalle.setMontoDescuento(apiFacturaDetalle.getMontoDescuento());
                detalle.setSubTotal(apiFacturaDetalle.getSubTotal());
                detalle.setNumeroSerie(apiFacturaDetalle.getApiItemHomologado().getNumeroSerie());
                detalle.setNumeroImei(apiFacturaDetalle.getApiItemHomologado().getNumeroImei());
                detalle.setUnidadMedida(apiFacturaDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                facturaElectronicaEstandar.getDetalle().add(detalle);
            }
            facturaElectronicaEstandar.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaElectronicaEstandar.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(facturaElectronicaEstandar, sw);
            //jaxbMarshaller.marshal(facturaElectronicaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<facturaElectronicaEstandar xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\">";
            replacement = "<facturaElectronicaEstandar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;

        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String facturaComputarizadaEstandarToXML(ApiFactura apiFactura, String nombreFileXml) {
        try {
            //Cabecera cabecera = new Cabecera();
            FacturaComputarizadaEstandar.Cabecera cabecera = new FacturaComputarizadaEstandar.Cabecera();
            cabecera.setNitEmisor(apiFactura.getNitEmisor());
            cabecera.setNumeroFactura(apiFactura.getNumeroFactura().intValue());
            cabecera.setCuf(apiFactura.getCuf());
            cabecera.setCufd(apiFactura.getApiConfiguracion().getCufd());
            if (apiFactura.getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiFactura.getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiFactura.getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiFactura.getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiFactura.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiFactura.getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiFactura.getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiFactura.getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiFactura.getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiFactura.getApiCliente().getCodigoCliente());
            cabecera.setCodigoMetodoPago(apiFactura.getParTipoMetodoPago().getCodigo().intValue());
            cabecera.setNumeroTarjeta(apiFactura.getNumeroTarjeta());
            cabecera.setMontoTotal(apiFactura.getMontoTotal());
            cabecera.setMontoDescuento(apiFactura.getMontoDescuento());
            cabecera.setCodigoMoneda(apiFactura.getParTipoMoneda().getCodigo().intValue());
            cabecera.setTipoCambio(apiFactura.getTipoCambio());
            cabecera.setMontoTotalMoneda(apiFactura.getMontoTotalMoneda());
            cabecera.setLeyenda(apiFactura.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiFactura.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            FacturaComputarizadaEstandar.Detalle detalle;
            FacturaComputarizadaEstandar facturaComputarizadaEstandar = new FacturaComputarizadaEstandar();
            List<ApiFacturaDetalle> listaApiFacturaDetalle = apiFactura.getApiFacturaDetalle();
            for (ApiFacturaDetalle apiFacturaDetalle : listaApiFacturaDetalle) {
                detalle = new FacturaComputarizadaEstandar.Detalle();
                detalle.setActividadEconomica(apiFacturaDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiFacturaDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin());
                detalle.setCodigoProducto(apiFacturaDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiFacturaDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCantidad(apiFacturaDetalle.getCantidad());
                detalle.setPrecioUnitario(apiFacturaDetalle.getPrecioUnitario());
                detalle.setMontoDescuento(apiFacturaDetalle.getMontoDescuento());
                detalle.setSubTotal(apiFacturaDetalle.getSubTotal());
                detalle.setNumeroSerie(apiFacturaDetalle.getApiItemHomologado().getNumeroSerie());
                detalle.setNumeroImei(apiFacturaDetalle.getApiItemHomologado().getNumeroImei());
                detalle.setUnidadMedida(apiFacturaDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                facturaComputarizadaEstandar.getDetalle().add(detalle);
            }
            facturaComputarizadaEstandar.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaComputarizadaEstandar.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(facturaComputarizadaEstandar, sw);
            //jaxbMarshaller.marshal(facturaComputarizadaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<facturaComputarizadaEstandar>";
            replacement = "<facturaComputarizadaEstandar xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;
        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String facturaElectronicaComercialExportacionToXML(ApiFacturaComercialExportacion apiFacturaComercialExportacion, String nombreFileXml) {
        try {
            FacturaElectronicaComercialExportacion.Cabecera cabecera = new FacturaElectronicaComercialExportacion.Cabecera();
            cabecera.setNitEmisor(apiFacturaComercialExportacion.getNitEmisor());
            cabecera.setNumeroFactura(apiFacturaComercialExportacion.getNumeroFactura().intValue());
            cabecera.setCuf(apiFacturaComercialExportacion.getCuf());
            cabecera.setCufd(apiFacturaComercialExportacion.getApiConfiguracion().getCufd());
            if (apiFacturaComercialExportacion.getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiFacturaComercialExportacion.getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiFacturaComercialExportacion.getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiFacturaComercialExportacion.getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiFacturaComercialExportacion.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiFacturaComercialExportacion.getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiFacturaComercialExportacion.getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiFacturaComercialExportacion.getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiFacturaComercialExportacion.getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiFacturaComercialExportacion.getApiCliente().getCodigoCliente());
            cabecera.setDireccionComprador(apiFacturaComercialExportacion.getDireccionComprador());
            cabecera.setIncoterm(apiFacturaComercialExportacion.getIncoterm());
            cabecera.setPuertoDestino(apiFacturaComercialExportacion.getPuertoDestino());
            cabecera.setLugarDestino(apiFacturaComercialExportacion.getLugarDestino());
            cabecera.setCodigoPais(apiFacturaComercialExportacion.getParPaisOrigen().getCodigo().intValue());
            cabecera.setCodigoMetodoPago(apiFacturaComercialExportacion.getParTipoMetodoPago().getCodigo().intValue());
            cabecera.setNumeroTarjeta(apiFacturaComercialExportacion.getNumeroTarjeta());
            cabecera.setMontoTotal(apiFacturaComercialExportacion.getMontoTotal());
            cabecera.setMontoTotalPuerto(apiFacturaComercialExportacion.getMontoTotalPuerto());
            cabecera.setPrecioValorBruto(apiFacturaComercialExportacion.getPrecioValorBruto());
            cabecera.setGastosTransporteFrontera(apiFacturaComercialExportacion.getGastosTransporteFrontera());
            cabecera.setGastosSeguroFrontera(apiFacturaComercialExportacion.getGastosSeguroFrontera());
            cabecera.setTotalFobFrontera(apiFacturaComercialExportacion.getTotalFobFrontera());
            cabecera.setMontoTransporteFrontera(apiFacturaComercialExportacion.getMontoTransporteFrontera());
            cabecera.setMontoSeguroInternacional(apiFacturaComercialExportacion.getMontoSeguroInternacional());
            cabecera.setOtrosMontos(apiFacturaComercialExportacion.getOtrosMontos());
            cabecera.setMontoDescuento(apiFacturaComercialExportacion.getMontoDescuento());
            cabecera.setCodigoMoneda(apiFacturaComercialExportacion.getParTipoMoneda().getCodigo().intValue());
            cabecera.setTipoCambio(apiFacturaComercialExportacion.getTipoCambio());
            cabecera.setMontoTotalMoneda(apiFacturaComercialExportacion.getMontoTotalMoneda());
            cabecera.setLeyenda(apiFacturaComercialExportacion.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiFacturaComercialExportacion.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            FacturaElectronicaComercialExportacion facturaElectronicaComercialExportacion = new FacturaElectronicaComercialExportacion();
            List<ApiFacturaComercialExportacionDetalle> listaApiFacturaExportacionDetalle = apiFacturaComercialExportacion.getApiFacturaComercialExportacionDetalle();
            FacturaElectronicaComercialExportacion.Detalle detalle;
            for (ApiFacturaComercialExportacionDetalle apiFacturaComercialExportacionDetalle : listaApiFacturaExportacionDetalle) {
                detalle = new FacturaElectronicaComercialExportacion.Detalle();
                detalle.setActividadEconomica(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin().intValue());
                detalle.setCodigoProducto(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCodigoNandina(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getCodigoNandina());
                detalle.setCantidad(apiFacturaComercialExportacionDetalle.getCantidad());
                detalle.setPrecioUnitario(apiFacturaComercialExportacionDetalle.getPrecioUnitario());
                detalle.setMontoDescuento(apiFacturaComercialExportacionDetalle.getMontoDescuento());
                detalle.setSubTotal(apiFacturaComercialExportacionDetalle.getSubTotal());
                detalle.setUnidadMedida(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                facturaElectronicaComercialExportacion.getDetalle().add(detalle);
            }
            facturaElectronicaComercialExportacion.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaElectronicaComercialExportacion.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(facturaElectronicaComercialExportacion, sw);
            //jaxbMarshaller.marshal(facturaComputarizadaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<facturaElectronicaComercialExportacion>";
            replacement = "<facturaElectronicaComercialExportacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;

        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String facturaComputarizadaComercialExportacionToXML(ApiFacturaComercialExportacion apiFacturaComercialExportacion, String nombreFileXml) {
        try {
            //Cabecera cabecera = new Cabecera();
            FacturaComputarizadaComercialExportacion.Cabecera cabecera = new FacturaComputarizadaComercialExportacion.Cabecera();
            cabecera.setNitEmisor(apiFacturaComercialExportacion.getNitEmisor());
            cabecera.setNumeroFactura(apiFacturaComercialExportacion.getNumeroFactura().intValue());
            cabecera.setCuf(apiFacturaComercialExportacion.getCuf());
            cabecera.setCufd(apiFacturaComercialExportacion.getApiConfiguracion().getCufd());
            if (apiFacturaComercialExportacion.getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiFacturaComercialExportacion.getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiFacturaComercialExportacion.getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiFacturaComercialExportacion.getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiFacturaComercialExportacion.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiFacturaComercialExportacion.getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiFacturaComercialExportacion.getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiFacturaComercialExportacion.getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiFacturaComercialExportacion.getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiFacturaComercialExportacion.getApiCliente().getCodigoCliente());
            cabecera.setDireccionComprador(apiFacturaComercialExportacion.getDireccionComprador());
            cabecera.setIncoterm(apiFacturaComercialExportacion.getIncoterm());
            cabecera.setPuertoDestino(apiFacturaComercialExportacion.getPuertoDestino());
            cabecera.setLugarDestino(apiFacturaComercialExportacion.getLugarDestino());
            cabecera.setCodigoPais(apiFacturaComercialExportacion.getParPaisOrigen().getCodigo().intValue());
            cabecera.setCodigoMetodoPago(apiFacturaComercialExportacion.getParTipoMetodoPago().getCodigo().intValue());
            cabecera.setNumeroTarjeta(apiFacturaComercialExportacion.getNumeroTarjeta());
            cabecera.setMontoTotal(apiFacturaComercialExportacion.getMontoTotal());
            cabecera.setMontoTotalPuerto(apiFacturaComercialExportacion.getMontoTotalPuerto());
            cabecera.setPrecioValorBruto(apiFacturaComercialExportacion.getPrecioValorBruto());
            cabecera.setGastosTransporteFrontera(apiFacturaComercialExportacion.getGastosTransporteFrontera());
            cabecera.setGastosSeguroFrontera(apiFacturaComercialExportacion.getGastosSeguroFrontera());
            cabecera.setTotalFobFrontera(apiFacturaComercialExportacion.getTotalFobFrontera());
            cabecera.setMontoTransporteFrontera(apiFacturaComercialExportacion.getMontoTransporteFrontera());
            cabecera.setMontoSeguroInternacional(apiFacturaComercialExportacion.getMontoSeguroInternacional());
            cabecera.setOtrosMontos(apiFacturaComercialExportacion.getOtrosMontos());
            cabecera.setMontoDescuento(apiFacturaComercialExportacion.getMontoDescuento());
            cabecera.setCodigoMoneda(apiFacturaComercialExportacion.getParTipoMoneda().getCodigo().intValue());
            cabecera.setTipoCambio(apiFacturaComercialExportacion.getTipoCambio());
            cabecera.setMontoTotalMoneda(apiFacturaComercialExportacion.getMontoTotalMoneda());
            cabecera.setLeyenda(apiFacturaComercialExportacion.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiFacturaComercialExportacion.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiFacturaComercialExportacion.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            FacturaComputarizadaComercialExportacion.Detalle detalle;
            FacturaComputarizadaComercialExportacion facturaComputarizadaComercialExportacion = new FacturaComputarizadaComercialExportacion();
            List<ApiFacturaComercialExportacionDetalle> listaApiFacturaComercialExportacionDetalle = apiFacturaComercialExportacion.getApiFacturaComercialExportacionDetalle();
            for (ApiFacturaComercialExportacionDetalle apiFacturaComercialExportacionDetalle : listaApiFacturaComercialExportacionDetalle) {
                detalle = new FacturaComputarizadaComercialExportacion.Detalle();
                detalle.setActividadEconomica(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin().intValue());
                detalle.setCodigoProducto(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCodigoNandina(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getCodigoNandina());
                detalle.setCantidad(apiFacturaComercialExportacionDetalle.getCantidad());
                detalle.setPrecioUnitario(apiFacturaComercialExportacionDetalle.getPrecioUnitario());
                detalle.setMontoDescuento(apiFacturaComercialExportacionDetalle.getMontoDescuento());
                detalle.setSubTotal(apiFacturaComercialExportacionDetalle.getSubTotal());
                detalle.setUnidadMedida(apiFacturaComercialExportacionDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                facturaComputarizadaComercialExportacion.getDetalle().add(detalle);
            }
            facturaComputarizadaComercialExportacion.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(FacturaComputarizadaComercialExportacion.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(facturaComputarizadaComercialExportacion, sw);
            //jaxbMarshaller.marshal(facturaComputarizadaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<facturaComputarizadaComercialExportacion>";
            replacement = "<facturaComputarizadaComercialExportacion xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;
        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String notaFiscalElectronicaCreditoDebitoToXML(ApiNotaFiscalCreditoDebito apiNotaCreditoDebito, String nombreFileXml) {
        try {
            NotaFiscalElectronicaCreditoDebito.Cabecera cabecera = new NotaFiscalElectronicaCreditoDebito.Cabecera();
            cabecera.setNitEmisor(apiNotaCreditoDebito.getApiFactura().getNitEmisor());
            cabecera.setNumeroNotaCreditoDebito(apiNotaCreditoDebito.getNumeroNotaCreditoDebito().intValue());
            cabecera.setCuf(apiNotaCreditoDebito.getCuf());
            cabecera.setCufd(apiNotaCreditoDebito.getApiConfiguracion().getCufd());
            if (apiNotaCreditoDebito.getApiFactura().getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiNotaCreditoDebito.getApiFactura().getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiNotaCreditoDebito.getApiFactura().getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiNotaCreditoDebito.getApiFactura().getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiNotaCreditoDebito.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiNotaCreditoDebito.getApiFactura().getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiNotaCreditoDebito.getApiFactura().getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiNotaCreditoDebito.getApiFactura().getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiNotaCreditoDebito.getApiFactura().getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiNotaCreditoDebito.getApiFactura().getApiCliente().getCodigoCliente());
            cabecera.setNumeroFactura(apiNotaCreditoDebito.getApiFactura().getNumeroFactura().intValue());
            cabecera.setNumeroAutorizacionCuf(apiNotaCreditoDebito.getApiFactura().getCuf());
            cabecera.setFechaEmisionFactura(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiNotaCreditoDebito.getApiFactura().getUtcFechaEmision().trim()));
            cabecera.setMontoTotalOriginal(apiNotaCreditoDebito.getMontoTotalOriginal());
            cabecera.setMontoTotalDevuelto(apiNotaCreditoDebito.getMontoTotalDevuelto());
            cabecera.setMontoEfectivoCreditoDebito(apiNotaCreditoDebito.getMontoEfectivoCreditoDebito());
            cabecera.setLeyenda(apiNotaCreditoDebito.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiNotaCreditoDebito.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiNotaCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            NotaFiscalElectronicaCreditoDebito notaFiscalElectronicaCreditoDebito = new NotaFiscalElectronicaCreditoDebito();
            List<ApiNotaFiscalCreditoDebitoDetalle> listaApiNotaCreditoDebitoDetalle = apiNotaCreditoDebito.getApiNotaFiscalCreditoDebitoDetalle();
            NotaFiscalElectronicaCreditoDebito.Detalle detalle;
            for (ApiNotaFiscalCreditoDebitoDetalle apiNotaCreditoDebitoDetalle : listaApiNotaCreditoDebitoDetalle) {
                detalle = new NotaFiscalElectronicaCreditoDebito.Detalle();
                detalle.setActividadEconomica(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin().intValue());
                detalle.setCodigoProducto(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCantidad(apiNotaCreditoDebitoDetalle.getCantidad());
                detalle.setPrecioUnitario(apiNotaCreditoDebitoDetalle.getPrecioUnitario());
                detalle.setSubTotal(apiNotaCreditoDebitoDetalle.getSubTotal());
                detalle.setUnidadMedida(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                detalle.setCodigoDetalleTransaccion(apiNotaCreditoDebitoDetalle.getParTipoTransaccion().getCodigo().intValue());
                notaFiscalElectronicaCreditoDebito.getDetalle().add(detalle);
            }
            notaFiscalElectronicaCreditoDebito.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(NotaFiscalElectronicaCreditoDebito.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(notaFiscalElectronicaCreditoDebito, sw);
            //jaxbMarshaller.marshal(facturaElectronicaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<notaFiscalElectronicaCreditoDebito xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\">";
            replacement = "<notaFiscalElectronicaCreditoDebito xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;

        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String notaFiscalComputarizadaCreditoDebitoToXML(ApiNotaFiscalCreditoDebito apiNotaCreditoDebito, String nombreFileXml) {
        try {
            NotaFiscalComputarizadaCreditoDebito.Cabecera cabecera = new NotaFiscalComputarizadaCreditoDebito.Cabecera();
            cabecera.setNitEmisor(apiNotaCreditoDebito.getApiFactura().getNitEmisor());
            cabecera.setNumeroNotaCreditoDebito(apiNotaCreditoDebito.getNumeroNotaCreditoDebito().intValue());
            cabecera.setCuf(apiNotaCreditoDebito.getCuf());
            cabecera.setCufd(apiNotaCreditoDebito.getApiConfiguracion().getCufd());
            if (apiNotaCreditoDebito.getApiFactura().getApiPuntoVenta() == null) {
                cabecera.setCodigoSucursal(apiNotaCreditoDebito.getApiFactura().getApiSucursal().getCodigoSucursal().intValue());
                cabecera.setDireccion(apiNotaCreditoDebito.getApiFactura().getApiSucursal().getDireccion());
            } else {
                cabecera.setCodigoPuntoVenta(apiNotaCreditoDebito.getApiFactura().getApiPuntoVenta().getCodigoPuntoVenta().intValue());
            }
            cabecera.setFechaEmision(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiNotaCreditoDebito.getUtcFechaEmision().trim()));
            cabecera.setNombreRazonSocial(apiNotaCreditoDebito.getApiFactura().getApiCliente().getNombreRazonSocial());
            cabecera.setCodigoTipoDocumentoIdentidad(apiNotaCreditoDebito.getApiFactura().getApiCliente().getParTipoDocumentoIdentidad().getCodigo().intValue());
            cabecera.setNumeroDocumento(apiNotaCreditoDebito.getApiFactura().getApiCliente().getNumeroDocumento());
            cabecera.setComplemento(apiNotaCreditoDebito.getApiFactura().getApiCliente().getComplemento());
            cabecera.setCodigoCliente(apiNotaCreditoDebito.getApiFactura().getApiCliente().getCodigoCliente());
            cabecera.setNumeroFactura(apiNotaCreditoDebito.getApiFactura().getNumeroFactura().intValue());
            cabecera.setNumeroAutorizacionCuf(apiNotaCreditoDebito.getApiFactura().getCuf());
            cabecera.setFechaEmisionFactura(FechaUtils.convertLocalDateTimeToXMLGregorianCalendar(apiNotaCreditoDebito.getApiFactura().getUtcFechaEmision().trim()));
            cabecera.setMontoTotalOriginal(apiNotaCreditoDebito.getMontoTotalOriginal());
            cabecera.setMontoTotalDevuelto(apiNotaCreditoDebito.getMontoTotalDevuelto());
            cabecera.setMontoEfectivoCreditoDebito(apiNotaCreditoDebito.getMontoEfectivoCreditoDebito());
            cabecera.setLeyenda(apiNotaCreditoDebito.getParLeyendaFactura().getDescripcion());
            cabecera.setUsuario(apiNotaCreditoDebito.getUsuario());
            cabecera.setCodigoDocumentoSector(new BigInteger(apiNotaCreditoDebito.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString()));

            NotaFiscalComputarizadaCreditoDebito notaFiscalComputarizadaCreditoDebito = new NotaFiscalComputarizadaCreditoDebito();
            List<ApiNotaFiscalCreditoDebitoDetalle> listaApiNotaCreditoDebitoDetalle = apiNotaCreditoDebito.getApiNotaFiscalCreditoDebitoDetalle();
            NotaFiscalComputarizadaCreditoDebito.Detalle detalle;
            for (ApiNotaFiscalCreditoDebitoDetalle apiNotaCreditoDebitoDetalle : listaApiNotaCreditoDebitoDetalle) {
                detalle = new NotaFiscalComputarizadaCreditoDebito.Detalle();
                detalle.setActividadEconomica(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getParActividad().getCodigo().intValue());
                detalle.setCodigoProductoSin(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getCodigoProductoSin().intValue());
                detalle.setCodigoProducto(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getCodigoProducto());
                detalle.setDescripcion(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getApiItem().getDescripcion());
                detalle.setCantidad(apiNotaCreditoDebitoDetalle.getCantidad());
                detalle.setPrecioUnitario(apiNotaCreditoDebitoDetalle.getPrecioUnitario());
                detalle.setSubTotal(apiNotaCreditoDebitoDetalle.getSubTotal());
                detalle.setUnidadMedida(apiNotaCreditoDebitoDetalle.getApiItemHomologado().getParUnidadMedida().getCodigo().intValue());
                detalle.setCodigoDetalleTransaccion(apiNotaCreditoDebitoDetalle.getParTipoTransaccion().getCodigo().intValue());
                notaFiscalComputarizadaCreditoDebito.getDetalle().add(detalle);
            }
            notaFiscalComputarizadaCreditoDebito.setCabecera(cabecera);

            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(NotaFiscalComputarizadaCreditoDebito.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N           
            //Print XML String to Console
            StringWriter sw = new StringWriter();
            //Write XML to StringWriter
            jaxbMarshaller.marshal(notaFiscalComputarizadaCreditoDebito, sw);
            //jaxbMarshaller.marshal(facturaComputarizadaEstandar, new File(nombreFileXml));          
            //String master = sw.toString();

            String master = sw.toString();
            String target = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
            String replacement = "";
            String xml = master.replace(target, replacement);

            target = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
            replacement = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
            xml = xml.replace(target, replacement);

            target = "<notaFiscalComputarizadaCreditoDebito>";
            replacement = "<notaFiscalComputarizadaCreditoDebito xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
            xml = xml.replace(target, replacement);
            guardarXML(xml, nombreFileXml);
            return xml;

        } catch (DatatypeConfigurationException | JAXBException ex) {
            Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean guardarXML(String xml, String fileName) {
        try {
            byte[] datos = xml.getBytes(StandardCharsets.UTF_8);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            factory.setNamespaceAware(true);
            builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new ByteArrayInputStream(datos));
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            //Write XML to file
            FileOutputStream outStream = new FileOutputStream(new File(fileName));
            transformer.transform(new DOMSource(documento), new StreamResult(outStream));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            //Logger.getLogger(GeneratorXML.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error " + ex.getMessage());
        }
        return false;
    }
}
