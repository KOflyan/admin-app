package com.admin.adminapi.security.filters;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import static com.admin.adminapi.utils.Constants.*;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        String username = obtainUsername(req);
        String password = obtainPassword(req);

        if (username == null && password == null) {

            JsonObject credentials = getCredentialsFromRequest(req);

            if (!validateJsonObject(credentials)) {
                throw new BadCredentialsException("No credentials provided");
            }
            username = credentials.get("username").getAsString();
            password = credentials.get("password").getAsString();

        }


        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth) {

        User user = (User) auth.getPrincipal();

        String subject = user.getUsername() + "," + user.getAuthorities().iterator().next().toString();

        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    private JsonObject getCredentialsFromRequest(HttpServletRequest req) {
        StringBuilder builder = new StringBuilder();
        try {

            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JsonParser parser = new JsonParser();

        return parser.parse(builder.toString()).getAsJsonObject();
    }

    private boolean validateJsonObject(JsonObject obj) {
        return obj.has("username") && obj.has("password");
    }
}