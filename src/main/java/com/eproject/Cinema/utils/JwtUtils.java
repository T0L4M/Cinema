package com.eproject.Cinema.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eproject.Cinema.entities.Account;

import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class JwtUtils {
    public static final String SECRET_KEY = "T12210E1";
    
    public String generateToken(Account account) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", account.getEmail());
		return createToken(claims, account.getEmail());
	}

    private String createToken(Map<String, Object> claims, String email) {
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
		return JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 24))
				.withClaim("UserInfo", claims)
				.sign(algorithm);
	}
}
