package dev.fcodeapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.fcodeapi.security.SecurityConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TokenAuthencationService {
    public static final String SECRET = "Fcode";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username, boolean isAdmin) {
        String token = JWT.create().withSubject(username).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).withClaim("isAdmin", isAdmin).sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);

    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }
}
