/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.xml.security.Init;
import org.apache.xml.security.algorithms.MessageDigestAlgorithm;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author ruth
 */
public class FirmadorUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmadorUtils.class);
    private static FirmadorUtils instancia;

    static {
        Init.init();
        Security.addProvider(new BouncyCastleProvider());
    }

//    /**
//     * Obtener un firmador por defecto.
//     *
//     ** @return un FirmadorUtils.
//     */
//    private static FirmadorUtils getInstance() {
//        if (instancia == null) {
//            instancia = new FirmadorUtils();
//        }
//        return instancia;
//    }
    private FirmadorUtils() {
    }
//// Todo: Colocar en un solo directorio la llave privada con la publica

    /**
     * Esta funcion añade una firma a un documento XML.
     *
     * @param datos Documento a firmar <i>XML</i>.
     * @param priv Clave privada.
     * @param cert Certificado del firmante.
     * @return Retorna el documento con una firma.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XMLSecurityException
     */
    private static boolean firmarDsig(String fileXmlFirmado, byte[] datos, PrivateKey priv, X509Certificate... cert) {
        try {
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "");
            Document documento = leerXML(datos);
            Element root = (Element) documento.getFirstChild();
            documento.setXmlStandalone(false);
            XMLSignature signature = new XMLSignature(documento, null,
                    XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
            root.appendChild(signature.getElement());
            Transforms transforms = new Transforms(documento);
            transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
            transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);
            signature.addDocument("", transforms, MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA256);
            if (cert != null) {
                signature.addKeyInfo(cert[0]);
            }
            signature.sign(priv);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(fileXmlFirmado));
            Source input = new DOMSource(documento);
            transformer.transform(input, output);
            return true;
        } catch (XMLSecurityException | IOException | SAXException | TransformerException | ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(FirmadorUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    private static Document leerXML(byte datos[]) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        factory.setNamespaceAware(true);
        builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(datos));
    }

    private static String getKey(String filename) throws IOException {
        // Read key from file
        String strKeyPEM = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            strKeyPEM += line + "\n";
        }
        br.close();
        return strKeyPEM;
    }

    private static RSAPrivateKey leerPrivateKey(String filename) throws IOException, GeneralSecurityException {
        String privateKeyPEM = getKey(filename);
        return getPrivateKeyFromString(privateKeyPEM);
    }

    private static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
        String privateKeyPEM = key;
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----\n", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
        byte[] encoded = Base64.decodeBase64(privateKeyPEM);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        return privKey;
    }

    private static RSAPublicKey getPublicKey(String filename) throws IOException, GeneralSecurityException {
        String publicKeyPEM = getKey(filename);
        return getPublicKeyFromString(publicKeyPEM);
    }

    private static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
        String publicKeyPEM = key;
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
        byte[] encoded = Base64.decodeBase64(publicKeyPEM);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
        return pubKey;
    }

    private static X509Certificate leerX509Certificate(String filename) throws IOException, CertificateException {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream(filename);
        X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
        //PublicKey key = cer.getPublicKey();
        return cer;
    }

    public static boolean firmarXML(String xml, String clavePrivada, String certificado, String nombreFileXMLFirmado)
            throws URISyntaxException, ParserConfigurationException, XMLSecurityException, org.xml.sax.SAXException {

        byte[] datos = xml.getBytes(StandardCharsets.UTF_8);
        try {
            PrivateKey privateKey = FirmadorUtils.leerPrivateKey(clavePrivada);
            X509Certificate cert = FirmadorUtils.leerX509Certificate(certificado);
            boolean verifica = FirmadorUtils.firmarDsig(nombreFileXMLFirmado, datos, privateKey, cert);
            return verifica;
        } catch (IOException | GeneralSecurityException ex) {
            LOGGER.info(ex.getMessage());
        }
        return false;
    }

    public static Date getFechaExpiracionCertificate(String nameFile) throws CertificateException, IOException {
        X509Certificate cert = leerX509Certificate(nameFile);
        Date expiresOn = cert.getNotAfter();
        return expiresOn;
    }

}
