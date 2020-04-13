/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.api.utils;

import java.net.*;

/**
 *
 * @author ruth
 */
public class ConnectionUtil {

    public static int checkInternet() {
        int result = 973;
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            result = 66;
        } catch (Exception e) {
            System.out.println("No Internet Connection ");            
        }
        return result;
    }
}
