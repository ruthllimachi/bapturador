/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import com.bap.api.dto.CabeceraDTO;
import com.bap.api.dto.Entidad;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.util.zip.GZIPOutputStream;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.LoggerFactory;
import java.util.Base64;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ruth
 */
public class FacturaUtils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FacturaUtils.class);

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String xml = leerFileXMLToString(null);
        byte[] encodeBase64 = encodeBase64(xml);
        //5)Comprimir el arreglo Base64 en formato Gzip        
        byte[] comprimido = compress(encodeBase64);
        System.out.println("comprimido " + new String(comprimido));
        byte[] descomprimido = decompress(comprimido);
        System.out.println("descomprimido " + new String(descomprimido));
        byte[] decodificado = decodeBase64(descomprimido);
        String resultado = new String(decodificado, StandardCharsets.UTF_8);
        System.out.println(" descomprimdo decodificado " + resultado);

        System.out.println("sdgasgasg");

//        Entidad entidad = area51(xml);
//        reptiliano(entidad);
//        byte[] encodeBase64 = encodeBase64(xml);
//        System.out.println("encodeBase64 " + new String(encodeBase64));
//        byte[] decodeBase64 = decodeBase64(encodeBase64);
//        System.out.println("decodeBase64: " + new String(decodeBase64));
//        byte[] compress = compress(encodeBase64);
//        //System.out.println("compressSegundaForma: " + compress);
//        byte[] decompress = decompress(compress);
        //System.out.println("decompressSegundaForma: " + new String(decompress));
//        byte[] hash = compressToEncodeBase64(compress);
        //System.out.println("hash " + hash);
//        String archivo = new String(hash);
        //System.out.println("archivo: " + archivo);
//        Entidad entidad = new Entidad();
//        entidad.setHash(hash);
//        entidad.setArchivo(archivo);
        System.out.println("sadgasgasgasg");

//    compressSegundaForma
        //          decompressSegundaForma
//        String valor = "hello World";
//        System.out.println("Original " + valor);
//        String encode64 = encodeBase64(valor);
//        System.out.println("encode64 " + encode64);
//        String decode64 = decodeBase64(encode64);
//        System.out.println("decode64 " + decode64);
//
//        System.out.println("############################################");
//        EncodeAndDecodeSegundaForma();
        //Entidad entidad = Compress(encode64);
//        try {
//            String[][] matriz = {
//                {"123456789", "20190113163721231", "0", "1", "1", "1", "1", "1", "0", "159FFE6FB1986A24BB32DBE5A2A34214B245A6A3", ""},
//                {"123456789", "20190113163721238", "0", "2", "1", "2", "2", "2", "0", "159FFE6FB1986A24BB32EAB8592FA23EE2CA4D48", ""},
//                {"123456789", "20190113163721238", "0", "1", "1", "3", "3", "3", "0", "159FFE6FB1986A24BB32EAB857CFF35D787AF3E4", ""},
//                {"123456789", "20190113163721239", "0", "2", "1", "1", "4", "4", "0", "159FFE6FB1986A24BB32ECD6730CF0AB8D2C1A89", ""},
//                {"123456789", "20190113163721239", "0", "1", "1", "2", "5", "5", "0", "159FFE6FB1986A24BB32ECD671AD41CA22DCC125", ""},
//                {"123456789", "20190113163721239", "0", "2", "1", "3", "6", "6", "0", "159FFE6FB1986A24BB32ECD673141DD973A167C5", ""},
//                {"123456789", "20190113163721240", "0", "1", "1", "1", "7", "7", "0", "159FFE6FB1986A24BB32EEF48B8A9036CD3E8E61", ""},
//                {"123456789", "20190113163721241", "0", "2", "1", "2", "8", "8", "0", "159FFE6FB1986A24BB32F112A6D23600D0433506", ""},
//                {"123456789", "20190113163721241", "0", "1", "1", "3", "9", "9", "0", "159FFE6FB1986A24BB32F112A572871F65F3DBA2", ""},
//                {"123456789", "20190113163721242", "0", "2", "1", "1", "10", "10", "0", "159FFE6FB1986A24BB32F330C0AF846D7AA50240", ""},
//                {"123456789", "20190113163721242", "0", "1", "1", "2", "11", "11", "0", "159FFE6FB1986A24BB32F330BF4FD58C1055A8E7", ""},
//                {"123456789", "20190113163721242", "0", "2", "1", "3", "12", "12", "0", "159FFE6FB1986A24BB32F330C0B6B19B611A4F87", ""},
//                {"123456789", "20190113163721243", "0", "1", "1", "1", "13", "13", "0", "159FFE6FB1986A24BB32F54ED92D23F8BAB77628", ""},
//                {"123456789", "20190113163721243", "0", "2", "1", "2", "14", "14", "0", "159FFE6FB1986A24BB32F54EDA9400080B7C1CC8", ""},
//                {"123456789", "20190113163721243", "0", "1", "1", "3", "15", "15", "0", "159FFE6FB1986A24BB32F54ED9345126A12CC364", ""},
//                {"123456789", "20190113163721244", "0", "2", "1", "1", "16", "16", "0", "159FFE6FB1986A24BB32F76CF4714E74B5DDEA09", ""},
//                {"123456789", "20190113163721244", "0", "1", "1", "2", "17", "17", "0", "159FFE6FB1986A24BB32F76CF3119F934B8E90A5", ""},
//                {"123456789", "20190113163721245", "0", "2", "1", "3", "18", "18", "0", "159FFE6FB1986A24BB32F98B0E59455D4E933741", ""},
//                {"123456789", "20190113163721245", "0", "1", "1", "1", "19", "19", "0", "159FFE6FB1986A24BB32F98B0CEEEDFFF5F05DE6", ""},
//                {"123456789", "20190113163721246", "0", "2", "1", "2", "20", "20", "0", "159FFE6FB1986A24BB32FBA9283693C9F8F50486", ""},
//                {"123456789", "20190113163721246", "0", "1", "1", "3", "21", "21", "0", "159FFE6FB1986A24BB32FBA926D6E4E88EA5AB22", ""},
//                {"123456789", "20190113163721247", "0", "2", "1", "1", "22", "22", "0", "159FFE6FB1986A24BB32FDC74213E236A356D1C7", ""},
//                {"123456789", "20190113163721247", "0", "1", "1", "2", "23", "23", "0", "159FFE6FB1986A24BB32FDC740B4335539077863", ""},
//                {"123456789", "20190113163721247", "0", "2", "1", "3", "1", "24", "0", "159FFE6FB1986A24BB32FDC7421A3E357D7FBF04", ""},
//                {"123456789", "20190113163721248", "0", "1", "1", "1", "2", "25", "0", "159FFE6FB1986A24BB32FFE55A90B092D71CE5A5", ""},
//                {"123456789", "20190113163721248", "0", "2", "2", "2", "3", "26", "0", "159FFE6FB1986A24BB32FFE55C1B139497A28C43", ""},
//                {"123456789", "20190113163721248", "0", "1", "2", "3", "4", "27", "0", "159FFE6FB1986A24BB32FFE55ABB64B32D5332E1", ""},
//                {"123456789", "20190113163721249", "0", "2", "2", "1", "5", "28", "0", "159FFE6FB1986A24BB33020375F8620142045984", ""},
//                {"123456789", "20190113163721249", "0", "1", "2", "2", "6", "29", "0", "159FFE6FB1986A24BB3302037498B31FD7B50020", ""},
//                {"123456789", "20190113163721249", "0", "2", "2", "3", "7", "30", "0", "159FFE6FB1986A24BB33020375FF8F2F2879A6C2", ""},
//                {"123456789", "20190113163721250", "0", "1", "2", "1", "8", "31", "0", "159FFE6FB1986A24BB3304218E76018C8216CD67", ""},
//                {"123456789", "20190113163721250", "0", "2", "2", "2", "9", "32", "0", "159FFE6FB1986A24BB3304218FDCDD9BD2DB7407", ""},
//                {"123456789", "20190113163721251", "0", "1", "2", "3", "10", "33", "0", "159FFE6FB1986A24BB33063FA85DF8751ACC1AA1", ""},
//                {"123456789", "20190113163721251", "0", "2", "2", "1", "11", "34", "0", "159FFE6FB1986A24BB33063FA9BA2C087D3D4141", ""},
//                {"123456789", "20190113163721251", "0", "1", "2", "2", "12", "35", "0", "159FFE6FB1986A24BB33063FA85A7D2712EDE7E6", ""},
//                {"123456789", "20190113163721252", "0", "2", "2", "3", "13", "36", "0", "159FFE6FB1986A24BB33085DC3A222F115F28E82", ""},
//                {"123456789", "20190113163721252", "0", "1", "2", "1", "14", "37", "0", "159FFE6FB1986A24BB33085DC237CB93BD4FB527", ""},
//                {"123456789", "20190113163721253", "0", "2", "2", "2", "15", "38", "0", "159FFE6FB1986A24BB330A7BDD7F715DC0545BC3", ""},
//                {"123456789", "20190113163721253", "0", "1", "2", "3", "16", "39", "0", "159FFE6FB1986A24BB330A7BDC1FC27C56050261", ""},
//                {"123456789", "20190113163721253", "0", "2", "2", "1", "17", "40", "0", "159FFE6FB1986A24BB330A7BDD7BF60FB8762901", ""},
//                {"123456789", "20190113163721254", "0", "1", "2", "2", "18", "41", "0", "159FFE6FB1986A24BB330C99F5FD10E90066CFA2", ""},
//                {"123456789", "20190113163721254", "0", "2", "2", "3", "19", "42", "0", "159FFE6FB1986A24BB330C99F763ECF8512B7642", ""},
//                {"123456789", "20190113163721254", "0", "1", "2", "1", "20", "43", "0", "159FFE6FB1986A24BB330C99F5F9959AF8889CE9", ""},
//                {"123456789", "20190113163721255", "0", "2", "2", "2", "21", "44", "0", "159FFE6FB1986A24BB330EB811413B64FB8D4385", ""},
//                {"123456789", "20190113163721255", "0", "1", "2", "3", "22", "45", "0", "159FFE6FB1986A24BB330EB80FE18C83913DEA21", ""},
//                {"123456789", "20190113163721256", "0", "2", "2", "1", "23", "46", "0", "159FFE6FB1986A24BB3310D62B1E89D1A5EF10C6", ""},
//                {"123456789", "20190113163721256", "0", "1", "2", "2", "1", "47", "0", "159FFE6FB1986A24BB3310D629BE09C12F535763", ""},
//                {"123456789", "20190113163721257", "0", "2", "2", "3", "2", "48", "0", "159FFE6FB1986A24BB3312F44505AF8B3257FE01", ""},
//                {"123456789", "20190113163721257", "0", "1", "2", "1", "3", "49", "0", "159FFE6FB1986A24BB3312F4439B582DD9B524A4", ""},
//                {"123456789", "20190113163721258", "0", "2", "2", "2", "4", "50", "0", "159FFE6FB1986A24BB3315125EE2FDF7DCB9CB42", ""}};
//
//            String resultado;
//            String[] dato = new String[9];
//            for (int i = 0; i < 50; i++) {
//                for (int j = 0; j < 10; j++) {
//                    if (j < 9) {
//                        dato[j] = matriz[i][j];
//                    }
//                    if (j == 9) {
//                        resultado = generaCUF(dato);
//                        matriz[i][10] = resultado;
//                    }
//                }
//                System.out.println("##########################################");
//                System.out.println(matriz[i][9]);
//                System.out.println(matriz[i][10]);
//                System.out.println((matriz[i][9]).trim().equals((matriz[i][10]).trim()));
//                System.out.println("##########################################");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static Long ofuscarNumeroTarjeta(String valor) {
        char[] cadena = valor.toCharArray();
        String a = "", b = "", c = "";
        int minLast = 0, maxLast = 0;
        if (valor.length() > 4) {
            maxLast = valor.length() - 1;
            if ((valor.length() - 4) < 4) {
                minLast = 4;
            } else {
                minLast = (valor.length() - 4) - 1;
            }
        }

        for (int i = 0; i < valor.length(); i++) {
            if (i >= 0 && i <= 3) {
                a = a + cadena[i];
            }
            if (i >= minLast && i <= maxLast) {
                c = c + cadena[i];
            }
        }
        int n = 16 - (a.trim().length() + c.trim().length());
        for (int i = 0; i < n; i++) {
            b = b + "0";
        }
        String z = a + b + c;
        Long resultado = Long.valueOf(z);
        return resultado;
    }

    public static String generaCUF(String[] dato) {
        String pCadena = completeSize(dato).trim();
        String hex = null;
        if (pCadena.length() == 51) {
            log.info("Cadena Completo: " + pCadena);
            String modulo11 = base11(pCadena.trim(), 1, 9, false);
            log.info("Base 11: " + modulo11);
            String cadena = pCadena.trim().concat(modulo11.trim());
            if (cadena.trim().length() == 52) {
                log.info("Cadena + Base 11: " + cadena);
                hex = Base16(cadena.trim());
                log.info("Base 16: " + hex);
            }
        }
        return hex;
    }

    //Se completa cada campo según la longitud definida con ceros a la izquierda
    //y Se concatena los campos
    private static String completeSize(String[] dato) {
        ///String[] probar = new String[9];
        int[] longitud = new int[9];
        longitud[0] = 13; //nit
        longitud[1] = 17;//fechaHora 
        longitud[2] = 4; //sucursal
        longitud[3] = 1;//modalidad
        longitud[4] = 1; //tipoEmision
        longitud[5] = 1; //codigoFiscal;
        longitud[6] = 2; //tipoDocumentoSecto
        longitud[7] = 8;//numeroFactura
        longitud[8] = 4;//puntoVenta

        int n, m;
        String d, r1, r2, resp = "";
        for (int i = 0; i < 9; i++) {
            n = longitud[i];
            d = dato[i];
            m = n - d.trim().length();
            r1 = "";
            for (int j = 0; j < m; j++) {
                r1 = r1.trim().concat("0");
            }
            r2 = r1.trim().concat(dato[i]);
            resp = resp.trim().concat(r2);
            ////probar[i] = r2;
        }
        return resp;
    }

    private static String base11(String dado, int numDig, int limMult, boolean x10) {
        int mult, soma, i, n, dig;

        if (!x10) {
            numDig = 1;
        }

        for (n = 1; n <= numDig; n++) {
            soma = 0;

            mult = 2;
            for (i = dado.length() - 1; i >= 0; i--) {
                soma += (mult * Integer.parseInt(dado.substring(i, i + 1)));
                if (++mult > limMult) {
                    mult = 2;
                }
            }
            if (x10) {
                dig = ((soma * 10) % 11) % 10;
            } else {
                dig = soma % 11;
            }
            if (x10) {
                dig = ((soma * 10) % 11) % 10;
            } else {
                dig = soma % 11;
            }
            if (dig == 10) {
                dado += "1";
            }
            if (dig == 11) {
                dado += "0";
            }
            if (dig < 10) {
                dado += String.valueOf(dig);
            }
        }
        return dado.substring(dado.length() - numDig, dado.length());
    }

//    private static String Bas16(String dato) {
//        BigInteger f = new BigInteger(dato);
//        String h = f.toString(16).toUpperCase();
//        return h;
//    }
    public static String Base16(String dato) {
        char digitosH[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        BigInteger val = new BigInteger(dato);
        BigInteger MOD = new BigInteger("16");

        String hexadecimal2 = "";
        BigInteger resto, aux = val;
        int indice;

        while (aux.compareTo(BigInteger.ZERO) > 0) {
            resto = aux.mod(MOD);
            indice = resto.intValue();
            hexadecimal2 = digitosH[indice] + hexadecimal2;
            aux = aux.divide(MOD);
            //aux /= 16;
        }
        return hexadecimal2;
    }

    private static String algoritmoHash(byte[] pArchivo, String algorithm) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            messageDigest.update(pArchivo);

            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (Exception e) {
            System.out.println("Error generando Hash");
        }
        return hashValue;
    }

    private String calCrc32(byte[] data) {
        Checksum checksum = new CRC32();
        checksum.update(data, 0, data.length);
        long checksumValue = checksum.getValue();
        String hex = Long.toHexString(checksumValue).toUpperCase();
        while (hex.length() < 8) {
            hex = "0" + hex;
        }
        return hex;
    }

//    //Ejemplo consumo de metodos
    public String obtenerMD5(byte[] archivo) {
        String vHash = algoritmoHash(archivo, "MD5");

        return vHash;
    }

    public static String obtenerSHA2(byte[] archivo) {
        String vSha2 = algoritmoHash(archivo, "SHA-256");
        return vSha2;
    }

    public String obtenerCRC32(byte[] archivo) {
        String vCrc = calCrc32(archivo);
        return vCrc;
    }

    private static byte[] encodeBase64(String valor) {
        try {
            return Base64.getEncoder().encode(valor.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("mensaje " + e.getMessage());
        }
        return null;
    }

    private static byte[] decodeBase64(byte[] valor) {
        try {
            return Base64.getDecoder().decode(new String(valor).getBytes("UTF-8"));
            //System.out.println(new String(decodedString));
        } catch (Exception e) {
            System.out.println("mensaje " + e.getMessage());
        }
        return null;
    }

    private static byte[] compress(byte[] content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] decompress(byte[] contentBytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    private static byte[] compressToEncodeBase64(byte[] compressed) {
        // Convert to base64
        return Base64.getEncoder().encode(compressed);
    }

    public static Entidad area51(String xml) {
        //4) Obtener el arreglo de bytes Base64 del archivo generado
        byte[] encodeBase64 = encodeBase64(xml);
        //5)Comprimir el arreglo Base64 en formato Gzip        
        byte[] compress = compress(encodeBase64);
        System.out.println("compress " + new String(compress));
        //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)        
        byte[] base64Compress = compressToEncodeBase64(compress);
        String archivo = new String(base64Compress, StandardCharsets.UTF_8);
        System.out.println("Archivo " + archivo);
        //7) Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)
        System.out.println("base64Compress " + base64Compress);
        String hash = obtenerSHA2(base64Compress);
        ////String hash =  obtenerSHA2(archivo.getBytes());
        System.out.println("hash " + hash);
        Entidad entidad = new Entidad();
        entidad.setHash(hash);
        entidad.setArchivo(archivo);
        return entidad;
    }

    public static void antiArea51(Entidad entidad) {
        System.out.println("entidad.getArchivo().getBytes() " + entidad.getArchivo().getBytes());
        //byte[] base64Compress = Base64.getDecoder().decode(entidad.getArchivo().getBytes());
        byte[] base64Compress = entidad.getArchivo().getBytes();
        System.out.println("base64Compress " + base64Compress);
        byte[] compress1 = decodeBase64(base64Compress);
        System.out.println("compress1 " + compress1);
        byte[] decompress1 = decompress(compress1);
        byte[] descompress1Decode = Base64.getDecoder().decode(decompress1);
        String xml = new String(descompress1Decode);
        System.out.println("xml " + xml);
        System.out.println("sadfasgsagag");
//        //byte[] Base64.getEncoder().encode
//        //7) Descomprimir 
//        
//        //4) Obtener el arreglo de bytes Base64 del archivo generado
//        byte[] encodeBase64 = encodeBase64(xml);
//        //5)Comprimir el arreglo Base64 en formato Gzip        
//        byte[] compress = compress(encodeBase64);
//        //6) Obtener el arreglo Base64 del comprimido Gzip (Cadena que se envía en la etiqueta archivo)        
//        byte[] base64Compress = compressToEncodeBase64(compress);
//        //7) Obtener el HASH del arreglo (Se envía en la etiqueta hashArchivo)
//        String archivo = new String(base64Compress);
//        String hash = obtenerSHA2(base64Compress);
//        Entidad entidad = new Entidad();
//        entidad.setHash(hash);
//        entidad.setArchivo(archivo);
//        return entidad;

    }

    public static boolean cufdVigente(LocalDateTime fechaVigencia) {
        LocalDateTime fechaSystem = LocalDateTime.now();
        boolean sw = fechaSystem.isBefore(fechaVigencia);
        return sw;
    }

    public static String leerFileXMLToString(String nameFile) throws ParserConfigurationException, SAXException, IOException {
        try {
            File xmlFile = new File(nameFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(xmlFile);
            DOMSource domSource = new DOMSource(documento);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerConfigurationException ex) {
            java.util.logging.Logger.getLogger(FirmadorUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            java.util.logging.Logger.getLogger(FirmadorUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //    private String generaFacturaCUF(ApiFactura apiFactura) {
//        try {
//            String[] dato = new String[9];
//            dato[0] = apiFactura.getNitEmisor().toString();//NIT emisor
//            LocalDateTime fechaEmision = FechaUtils.convertStringToLocalDateTime(apiFactura.getUtcFechaEmision());
//            String milli = FechaUtils.convertLocalDateTimeToFormatNumberMillesecond(fechaEmision);
//            dato[1] = milli.trim();//FECHA HORA            
//            dato[2] = apiFactura.getApiSucursal().getCodigoSucursal().toString();//SUCURSAL
//            dato[3] = apiFactura.getApiDosificacion().getParTipoModalidad().getCodigo().toString();//MODALIDAD
//            dato[4] = apiFactura.getParTipoEmision().getCodigo().toString();//TIPO EMISION
//            dato[5] = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoFiscal().getCodigo().toString();//CODIGO FISCAL
//            dato[6] = apiFactura.getApiEmpresaDocumento().getParTipoDocumentoSector().getCodigo().toString();//DOCUMENTO SECTOR
//            dato[7] = apiFactura.getNumeroFactura().toString();//NUMERO FACTURA
//            if (apiFactura.getApiPuntoVenta() != null) {
//                dato[8] = apiFactura.getApiPuntoVenta().getCodigoPuntoVenta().toString();//CODIGO PUNTO 
//            } else {
//                dato[8] = "0";//CODIGO PUNTO 
//            }
//            return FacturaUtils.generaCUF(dato);
//        } catch (Exception e) {
//            return null;
//        }
//    }
    
    
    public static String generaFacturaCUF(CabeceraDTO cabeceraDTO) {
        try {            
            String[] dato = new String[9];
            dato[0] = cabeceraDTO.getNitEmisor().toString();//NIT emisor
            LocalDateTime fechaEmision = cabeceraDTO.getFechaEmision();
            String milli = FechaUtils.convertLocalDateTimeToFormatNumberMillesecond(fechaEmision);
            dato[1] = milli.trim();//FECHA HORA            
            dato[2] = cabeceraDTO.getCodigoSucursal().toString();//SUCURSAL
            dato[3] = cabeceraDTO.getCodigoTipoModalidad().toString();//MODALIDAD
            dato[4] = cabeceraDTO.getTipoEmision().toString();//TIPO EMISION
            dato[5] = cabeceraDTO.getCodigoDocumentoFiscal().toString();//CODIGO FISCAL
            dato[6] = cabeceraDTO.getCodigoDocumentoSector().toString(); //DOCUMENTO SECTOR
            dato[7] = cabeceraDTO.getNumeroDocumentoFiscal().toString(); //NUMERO FACTURA
            if (cabeceraDTO.getCodigoPuntoVenta() != null) {
                dato[8] = cabeceraDTO.getCodigoPuntoVenta().toString();//CODIGO PUNTO 
            } else {
                dato[8] = "0";//CODIGO PUNTO 
            }
            return generaCUF(dato);
        } catch (Exception e) {
            return null;
        }
    }

}
