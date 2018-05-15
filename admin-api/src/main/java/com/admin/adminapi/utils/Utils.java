package com.admin.adminapi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.GenericTypeResolver;

import java.sql.Date;

import static com.admin.adminapi.utils.Constants.EXPIRATION_TIME;
import static com.admin.adminapi.utils.Constants.SECRET;
import static com.admin.adminapi.utils.Constants.TOKEN_PREFIX;


public class Utils {

    @SuppressWarnings("unchecked")
    public static <T> Class<T> resolveClassOfT(Class<?> fromClass, Class forClass) {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(fromClass, forClass);
    }

    public static String getClassName(Class clazz) {
        String className = clazz.getName();

        if (className.contains(".")) {
            className = className.substring(className.lastIndexOf('.') + 1);
        }

        // This is needed in case of @MappedSuperclass (AbstractAccount, AbstractUser) - you cannot query it directly
        if (className.contains("Account")) {
            className = "Account";
        } else if (className.contains("User")) {
            className = "User";
        }
        return className;
    }

    public static String generateToken(String username, String role) {

        String token = Jwts.builder()
                .setSubject(username + "," + role)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        return TOKEN_PREFIX + token;
    }
}
