/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import com.bap.api.enums.EnumParTipoModalidad;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 *
 * @author ruth
 */
public class ValidatorXSD {

    public static void main(String[] args) {
//        XMLValidator XMLValidator = new XMLValidator();
//        boolean valid = XMLValidator.validate(XML_FILE, SCHEMA_FILE);
//
//        System.out.printf("%s validation = %b.", XML_FILE, valid);
    }

    public boolean validate(String xml, long tipoModalidad) {
        try {
            File initialFile = null;
            if (tipoModalidad == EnumParTipoModalidad.ELECTRONICA.getCodigo()) {
                initialFile = new File("/home/ruth/Development/Project_Bap/XSD/facturaElectronicaEstandar.xsd");
            }
            if (tipoModalidad == EnumParTipoModalidad.COMPUTARIZADA.getCodigo()) {
                initialFile = new File("/home/ruth/Development/Project_Bap/XSD/facturaComputarizadaEstandar.xsd");
            }
            if (initialFile != null) {
                InputStream xsd = new FileInputStream(initialFile);

                SchemaFactory factory
                        = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = factory.newSchema(new StreamSource(xsd));
                Validator validator = schema.newValidator();
                validator.validate(new StreamSource(new StringReader(xml)));
                return true;
            }
        } catch (SAXException e) {
            //e.printStackTrace();
            System.out.println("Error de validacion en XML: " + e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException: " + e.getMessage());
        }
        return false;
    }

}
