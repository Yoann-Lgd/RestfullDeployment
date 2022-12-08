package fr.yl.restfulldeployment.security;

import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

import java.util.*;

public class SecurityTools {

    private SecurityTools() {
    }

    public static String hash(String stringToHash) {
        Pbkdf2PasswordHash pbkdf2PasswordHash = new Pbkdf2PasswordHashImpl();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
        parameters.put("Pbkdf2PasswordHash.Iterations", "300000");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        parameters.put("Pbkdf2PasswordHash.KeySizeBytes", "64");
        pbkdf2PasswordHash.initialize(parameters);
        return pbkdf2PasswordHash.generate(stringToHash.toCharArray());
    }


}