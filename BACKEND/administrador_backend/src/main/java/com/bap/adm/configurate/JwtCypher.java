/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bap.adm.configurate;

import com.bap.adm.dto.UserToken;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import java.util.Date;

/**
 *
 * @author ruth
 */
public class JwtCypher {

    private static final String KEY = "SECRET";

    public static String getToken(UserToken userToken) {
        String compactJwt = Jwts.builder().setIssuedAt(new Date()).setSubject(userToken.toString()).signWith(HS512, KEY).compact();

        return compactJwt;
    }

    public static boolean verifyToken(String token) {
        return true;
    }
}
