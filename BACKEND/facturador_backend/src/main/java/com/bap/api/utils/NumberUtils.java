/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ruth
 */
public class NumberUtils {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);
    
    private static final String[] UNIDADES1 = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private static final String[] DECENAS1 = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private static final String[] CENTENAS1 = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

    public static void main(String[] args) {
        try {
            BigDecimal numero = new BigDecimal(150.5f);
            BigDecimal porcentaje = new BigDecimal(330L);
            BigDecimal resultado = numero.subtract(porcentaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Float redondeaFloat(Float numero, int cantidadDecimales) throws Exception {
        try {
            BigDecimal bd = new BigDecimal(Float.toString(numero));
            bd = bd.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();
        } catch (Exception e) {
            throw e;
        }
    }

    public static BigDecimal redondeaBigDecimal(BigDecimal numero, int cantidadDecimales) throws Exception {
        try {
            numero = numero.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
            return numero;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String decimalFormatBigDecimal(BigDecimal numero, int cantidadDecimales) throws Exception {
        numero = numero.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);
//        log.info("Locale.getDefault()::: "+Locale.getDefault());
//        log.info("Locale.getDefault().getLanguage():::::: "+Locale.getDefault().getLanguage());

        Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        //log.info("bLocale::: "+bLocale.getLanguage());

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(bLocale);
        DecimalFormat df = null;
        if (cantidadDecimales == 5) {
            df = new DecimalFormat("###.00000", unusualSymbols); // Set your desired format here.
        } else {
            df = new DecimalFormat("###.00", unusualSymbols); // Set your desired format here.
        }
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(numero);
    }

    public static String decimalFormatBigDecimal(BigDecimal numero) throws Exception {
        numero = numero.setScale(0, BigDecimal.ROUND_HALF_UP);
//        log.info("Locale.getDefault()::: "+Locale.getDefault());
//        log.info("Locale.getDefault().getLanguage():::::: "+Locale.getDefault().getLanguage());

        Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        //log.info("bLocale::: "+bLocale.getLanguage());

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(bLocale);
        DecimalFormat df = new DecimalFormat("###", unusualSymbols); // Set your desired format here.
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(numero);
    }

    public static String decimalFormat(Float numero, int cantidadDecimales) throws Exception {
        log.info("numero:: " + numero);

        BigDecimal bd = new BigDecimal(Float.toString(numero));
        bd = bd.setScale(cantidadDecimales, BigDecimal.ROUND_HALF_UP);

        DecimalFormat df = new DecimalFormat("###.00"); // Set your desired format here.
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        return df.format(bd);
    }

    /**
     * Retorna el digito numerico en la posicion indicada de derecha a izquierda
     *
     * @param origin Cadena en la cual se busca el digito
     * @param position Posicion de derecha a izquierda a retornar
     * @return Digito ubicado en la posicion indicada
     */
    private static int getDigitAt(String origin, int position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - 48;
        }
        return 0;
    }

    public static String convertirLiteral(BigDecimal valor, String tipoMoneda, boolean mayusculas) {
        BigDecimal redondeado = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
        String numero = redondeado.toString();
        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",000000";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
            parte_decimal = Num[1] + "/100 ";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal + parte_decimal + tipoMoneda).toUpperCase();
            } else {
                return (literal + parte_decimal + tipoMoneda);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /*
     * funciones para convertir los numeros a literales
     */
    private static String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES1[Integer.parseInt(num)];
    }

    private static String getDecenas(String num) {// 99
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS1[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS1[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS1[n - 10];
        }
    }

    private static String getCentenas(String num) {// 999 o 099
        if (Integer.parseInt(num) > 99) {//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                return CENTENAS1[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            }
        } else {//por Ej. 099
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private static String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private static String getMillones(String numero) { //000 000 000
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + " millones ";
        } else {
            if (!millon.equals("1")) {
                n = getUnidades(millon) + " millones ";
            } else {
                n = getUnidades(millon) + " millon ";
            }
        }
        return n + getMiles(miles);
    }

    public static boolean validRange(BigDecimal input, BigDecimal low, BigDecimal high) {
        return (input.compareTo(low) >= 0 && input.compareTo(high) <= 0);
    }

}
