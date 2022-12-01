package fr.yl.restfulldeployment.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;


public class MyToken {

    private String token;
    private Algorithm algorithm = Algorithm.HMAC256("MySecretK3Y");

    public MyToken(String user, String password) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.MINUTE, 1);
        Date expiration = calendar.getTime();
        token = "Bearer ";
        token += JWT.create()
                .withClaim("user", user)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
    }

    public MyToken(String tokenHeader) {
        if (tokenHeader != null && tokenHeader.startsWith("Bearer "))
            token = tokenHeader.substring(7);
        else
            token = "";
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }

    public boolean isValide() {
        boolean retour = false;
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT jwt = verifier.verify(token);
            retour = !jwt.getExpiresAt().before(now);
        } catch (Exception e) {

        }
        return retour;
    }
}
