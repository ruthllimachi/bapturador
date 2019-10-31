/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ruth
 */
public class FechaUtils {

    public static void main(String[] args) throws ParseException, DatatypeConfigurationException {
        System.out.println( "¡Hola pap\u00e1!\nYa puedo escribir bien.\n\u00d1a\u00f1a\u00f1a\u00f1a" );
 

 


//        //sin
//        BigInteger x = hexToDecimal("159FFE6FB199C9A7344BBC92A10A653BF28AC9E6");
//        System.out.println("hexadecima a decimal es " + x.toString());
//        
//        String y = Base16("123456789201908261100122080000111010000101100006");
//        System.out.println("decimal a hexadecimal es " + y);

//mio
//        BigInteger x = hexToDecimal("159FFE6FB199C9A7344BBC92A10A653BF28AC9E6");
//        System.out.println("decimal es " + x.toString());

/////
        //Base16_2("123456789201908261100122080000111010000101100006");
//        Base16_2("7000");

//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("Before : " + now);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        String formatDateTime = now.format(formatter);
//        System.out.println("After : " + formatDateTime);
//        XMLGregorianCalendar x = convertLocalDateTimeToXMLGregorianCalendar(now);
//        System.out.println("Fecha tiene " + x.toString());
//        Date date = new Date();
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println("UTC Time is: " + dateFormat1.format(date));
//
//        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println("UTC Time is Millesecundos: " + dateFormat2.format(date));
//
//        //Convert date into UTC
//        Date dt = new Date();
//
////        SimpleDateFormat dateFormatter = new SimpleDateFormat(
////                "yyyy-MM-dd'T'HH:mm:ss'Z'");
//
//        SimpleDateFormat dateFormatter = new SimpleDateFormat(
//                "yyyy-MM-dd'T'HH:mm:ss.SSS");
//
//        String strUTCDate = dateFormatter.format(dt);
//        System.out.println("Convert date into UTC: " + strUTCDate);
//
//        //Convert UTC String to date
//        String input = "2015-01-04T19:50:26Z";
//        TimeZone utc = TimeZone.getTimeZone("UTC");
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        f.setTimeZone(utc);
//        GregorianCalendar cal = new GregorianCalendar(utc);
//
//        cal.setTime(f.parse(input));
//        System.out.println("Convert UTC String to date: "  + cal.getTime());
//                
//                Date utcDate = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
//                System.out.println("UTC Millesegundo is: " + utcDate.toString());
    }

//    public static LocalDateTime convertToLocalDateTime(String dateToConvert) {
    public static LocalDateTime convertStringToLocalDateTime(String dateToConvert) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return LocalDateTime.parse(dateToConvert, formatter);
    }

//    //public static LocalDateTime convertStringtLocalDateTime(String dateToConvert) {
    public static LocalDateTime convertStringToLocalDateTimeWithoutMillisecond(String dateToConvert) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateToConvert, formatter);
        return dateTime;
    }

    //convierte date a localdatetime
//    public static LocalDateTime convertLocalDateTime(Date dateToConvert) {
    public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
        LocalDateTime ldt = LocalDateTime.ofInstant(dateToConvert.toInstant(),
                ZoneId.systemDefault());
        return ldt;
    }

//    public static String convertLocalDateTimeToString(LocalDateTime fecha) {
    public static String convertLocalDateTimeToFormatNumberMillesecond(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formatDateTime = fecha.format(formatter);
        return formatDateTime;
    }

    public static String convertLocalDateTimeToFormatStringMillesecond(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String formatDateTime = fecha.format(formatter);
        return formatDateTime;
    }
    
      public static String convertLocalDateTimeToFormatReport(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDateTime = fecha.format(formatter);
        return formatDateTime;
    }

    public static XMLGregorianCalendar convertLocalDateTimeToXMLGregorianCalendar(String f) throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(f);
        return xmlGregoriaFechaEmision;

    }

//    public static XMLGregorianCalendar convertLocalDateTimeToXMLGregorianCalendar(LocalDateTime date) throws DatatypeConfigurationException {
//        String f = convertLocalDateTimeToFormatStringMillesecond(date);
//        XMLGregorianCalendar xmlGregoriaFechaEmision = DatatypeFactory.newInstance().newXMLGregorianCalendar(f);
//         
//        return xmlGregoriaFechaEmision;
//
//    }
//    public static LocalDateTime getLocalDateTimeInUTC() {
//        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
//        return nowUTC.toLocalDateTime();
//    }
//
//    public static LocalDateTime convertToUtc(LocalDateTime time) {
//        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
//    }
//    public static long convertToMilliseconds(LocalDateTime localDateTime) {
//        long millis = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        return millis;
//    }
    public static BigInteger hexToDecimal(String hex) {
        return new BigInteger(hex, 16);
    }

//    public static BigInteger decimalToHex(String dec) {
//         return new BigInteger(dec,16);
//    }
//    private static String Base16(String dato) {
//        BigInteger f = new BigInteger(dato);
//        String h = f.toString(16).toUpperCase();
//        return h;
//    }

//    private static String Base16_2(String dato) {
//        char digitosH[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
//        String hexadecimal2 = "";
//        int resto, aux = decimal;
//         
//        while(aux>0){
//            resto = aux % 16;
//            hexadecimal2 = digitosH[resto] + hexadecimal2;
//            aux /= 16; 
//        }
//    }
//    public static void Base16_2(String dato) {
//        char digitosH[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//        BigInteger val = new BigInteger(dato);
//        BigInteger MOD = new BigInteger("16");
////        
////         BigInteger result = val.mod(MOD); 
////         System.out.println("valor es " + result.toString());
//        String hexadecimal2 = "";
//        BigInteger resto, aux = val;
//        int indice;
//        
//        while (aux.compareTo(BigInteger.ZERO) > 0) {
//            resto = aux.mod(MOD);        
//            indice = resto.intValue();
//            hexadecimal2 = digitosH[indice] + hexadecimal2;
//            aux = aux.divide(MOD);
//            //aux /= 16;
//        }
//        System.out.println("valor es "+ hexadecimal2);
//    }
}
